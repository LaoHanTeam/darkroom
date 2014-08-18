package org.shareGame.depository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 资源公式类
 * @author baihui.lbh
 * @version $Id: Formula.java, v 0.1 2014年8月18日 下午7:53:52 baihui.lbh Exp $
 */
public class Formula {
    private static Log               log       = LogFactory.getLog(Formula.class);
    //输入资源链
    private final List<ResourceItem> inputList = new ArrayList<ResourceItem>();
    //产出资源，强制只产出一种资源
    private final ResourceItem       output    = new ResourceItem();

    /**
     * 记录一条公式
     * @param str
     * @return
     */
    public boolean record(String[] str) {
        boolean flag = false;
        if (str != null && str.length != 0) {
            for (int i = 0; i < str.length; ++i) {
                //按*分隔开资源名称和数量
                if (str[i] != null && !str[i].isEmpty()) {
                    List<String> tempList = Arrays.asList(str[i].split("\\*", -1));
                    if (i == 0) {
                        output.setResourceName(tempList.get(0));
                        output.setResourceNum(Integer.valueOf(tempList.get(1)).intValue());
                    } else {
                        ResourceItem resItem = new ResourceItem();
                        resItem.setResourceName(tempList.get(0));
                        resItem.setResourceNum(Integer.valueOf(tempList.get(1)).intValue());
                        inputList.add(resItem);
                    }
                }
            }
            flag = true;
        } else {
            log.error("输入字符串数组不可为空！", new IllegalArgumentException());
        }
        return flag;
    }

    public ResourceItem getOutput() {
        return output;
    }

    public String getOutputName() {
        return output.getResourceName();
    }

    public List<ResourceItem> getInput() {
        return inputList;
    }

    @Override
    public String toString() {
        return output.toString() + "<=" + inputList.toString();
    }

}
