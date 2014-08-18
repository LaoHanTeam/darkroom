package org.shareGame.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author baihui.lbh
 * @version $Id: CsvParser.java, v 0.1 2014年8月18日 下午7:39:18 baihui.lbh Exp $
 */
public class CsvParser {
    private static Log          log          = LogFactory.getLog(CsvParser.class);
    private static final String DEFAULT_PATH = "/src/main/resources/";

    public File getCsvFile(String csvPath) {
        File file = null;
        if (csvPath != null && !csvPath.isEmpty()) {
            String filePath = System.getProperty("user.dir") + DEFAULT_PATH + csvPath;
            file = new File(filePath);
            //log.info("读取文件：" + filePath);
        }
        return file;
    }

    public List<String[]> readFromCsv(File file) throws Exception {
        //初始化读入文件
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            log.error("读入文件【" + file.getName() + "】初始化失败", e);
        }

        //读取文件内容
        List<String[]> tableList = null;
        try {
            InputStreamReader isr = new InputStreamReader(inputStream);
            CSVReader csvReader = new CSVReader(isr);
            tableList = csvReader.readAll();
        } catch (Exception e) {
            log.error("通过CSV文件流读入数据失败", e);
        }
        return tableList;
    }
}
