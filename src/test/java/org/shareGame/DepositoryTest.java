package org.shareGame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author baihui.lbh
 * @version $Id: DepositoryTest.java, v 0.1 2014年8月18日 下午4:24:20 baihui.lbh Exp $
 */
public class DepositoryTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new MeatProduce());
        exec.execute(new WoodsConsume());
        exec.execute(new MeatConsume());
//        exec.execute(new WoodsProduce());
        System.out.println("end");
        exec.shutdown();
    }
}
