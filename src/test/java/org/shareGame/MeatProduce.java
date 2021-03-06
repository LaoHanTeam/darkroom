package org.shareGame;

import org.shareGame.depository.Depository;
import org.shareGame.depository.ResourceItem;

/**
 * 
 * @author baihui.lbh
 * @version $Id: MeatProduce.java, v 0.1 2014年8月18日 下午7:20:58 baihui.lbh Exp $
 */
public class MeatProduce implements Runnable {

    /** 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        ResourceItem meat = new ResourceItem("生肉", 1);
        Depository instance = Depository.getInstance();//获得单例仓库
        for (int i = 0; i < 10; ++i)
            instance.produceDepository(meat);
    }

}
