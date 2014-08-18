package org.shareGame.depository;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shareGame.utils.CsvParser;

/**
 * 资源公式库，单例模式
 * @author baihui.lbh
 * @version $Id: FormulaLibrary.java, v 0.1 2014年8月18日 下午7:49:18 baihui.lbh Exp $
 */
public class FormulaLibrary {
    private static Log                            log            = LogFactory
                                                                     .getLog(FormulaLibrary.class);
    //资源公式库
    private final static HashMap<String, Formula> formulaLibrary = new HashMap<String, Formula>();

    private final static FormulaLibrary           instance       = new FormulaLibrary();

    //csv文件路径
    private final static String                   csvPath        = "csvfiles/formula.csv";

    private FormulaLibrary() {
        CsvParser csvParser = new CsvParser();
        File myFile = csvParser.getCsvFile(csvPath);
        List<String[]> tableList = null;
        try {
            tableList = csvParser.readFromCsv(myFile);
        } catch (Exception e) {
            log.error("读取csv文件出错", e);
        }

        for (int i = 1; i < tableList.size(); ++i) {
            //第一行文件头不读 
            Formula newFormula = new Formula();
            newFormula.record(tableList.get(i));
            formulaLibrary.put(newFormula.getOutputName(), newFormula);
        }
    }

    /**
     * 根据资源名称获取对应公式
     * @param key
     * @return
     */
    public Formula getFormula(String key) {
        Formula formula = new Formula();
        if (key != null && !key.isEmpty()) {
            if (formulaLibrary.containsKey(key)) {
                formula = formulaLibrary.get(key);
            } else {
                log.info("找不到对应公式！");
            }
        } else {
            log.error("查询入参不能为空", new IllegalArgumentException());
        }
        return formula;
    }

    public static FormulaLibrary getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return formulaLibrary.toString();
    }
}
