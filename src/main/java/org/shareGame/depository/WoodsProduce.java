/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.shareGame.depository;

/**
 * 
 * @author baihui.lbh
 * @version $Id: WoodsProduce.java, v 0.1 2014年8月18日 下午7:02:35 baihui.lbh Exp $
 */
public class WoodsProduce implements Runnable {

    /** 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        ResourceItem woods = new ResourceItem("木材", 3);
        Depository instance = Depository.getInstance();//获得单例仓库
        for (int i = 0; i < 10; ++i)
            instance.produceDepository(woods);
    }

}
