package org.shareGame.depository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用于作为资源公式的节点，也可以做实际资源存储的单元
 * @author baihui.lbh
 * @version $Id: ResourceItem.java, v 0.1 2014年8月16日 下午2:45:35 baihui.lbh Exp $
 */
public class ResourceItem {
    private static Log log = LogFactory.getLog(ResourceItem.class);
    private String     ResourceName;
    private int        ResourceNum;
    private boolean    available;

    public ResourceItem() {

    }

    public ResourceItem(String name, int num) {
        if (name != null && !name.isEmpty()) {
            ResourceName = name;
        } else {
            log.error("资源名称不能为空", new IllegalArgumentException());
        }
        if (num > 0) {
            ResourceNum = num;
        } else {
            log.error("资源数目必须大于0", new IllegalArgumentException());
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean val) {
        available = val;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public void setResourceName(String name) {
        if (name != null && !name.isEmpty()) {
            ResourceName = name;
        } else {
            log.error("资源名称不能为空", new IllegalArgumentException());
        }
    }

    public int getResourceNum() {
        return ResourceNum;
    }

    public void setResourceNum(int num) {
        if (num > 0) {
            ResourceNum = num;
        } else {
            log.error("资源数目必须大于0", new IllegalArgumentException());
        }
    }

    public boolean isItemEmpty() {
        boolean flag = false;
        if (ResourceName == null || ResourceName.isEmpty())
            flag = true;
        return flag;
    }

    @Override
    public String toString() {
        return ResourceName + "×" + ResourceNum;
    }
}
