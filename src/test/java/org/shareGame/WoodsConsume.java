package org.shareGame;

import org.shareGame.depository.Depository;
import org.shareGame.depository.ResourceItem;

/**
 * 
 * @author baihui.lbh
 * @version $Id: WoodsConsume.java, v 0.1 2014年8月18日 下午7:01:29 baihui.lbh Exp $
 */
public class WoodsConsume implements Runnable {

    /** 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        ResourceItem woods = new ResourceItem("木材", 1);
        Depository instance = Depository.getInstance();//获得单例仓库
        for (int i = 0; i < 10; ++i)
            instance.consumeDepository(woods);
    }

}
