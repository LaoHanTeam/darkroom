package org.shareGame.controler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 仅用于控制小屋内的温度,单例模式
 * @author baihui.lbh
 * @version $Id: TempControler.java, v 0.1 2014年8月19日 上午11:18:50 baihui.lbh Exp $
 */
public class TempControler {
    private static Log                 log           = LogFactory.getLog(TempControler.class);
    //温度范围在-10到100内变化
    //private static int                 temperature   = 0;
    private final Temperature          temperature   = Temperature.getInstance();
    private final Thread               coolingThread = new Thread() {
                                                         @Override
                                                         public void run() {
                                                             while (true)
                                                                 coolDown();
                                                         }
                                                     };
    private final Thread               warmingThread = new Thread() {
                                                         @Override
                                                         public void run() {
                                                             while (true)
                                                                 heatUp();
                                                         }
                                                     };
    private final static TempControler instance      = new TempControler();

    //private final static Object        controler     = new Object();

    private TempControler() {

    }

    public void warming() {
        warmingThread.start();
    }

    public void cooling() {
        coolingThread.start();
    }

    //降温方法
    private void coolDown() {

        if (temperature.getTemp() > 0) {
            temperature.selfSub();
            temperature.cooled();
        } else
            temperature.readyToHeatUp();
    }

    private void heatUp() {
        if (temperature.getTemp() <= 0) {
            temperature.selfPlus();
            temperature.heated();
        } else
            temperature.readyToCoolDown();

    }

    public static TempControler getInstance() {
        return instance;
    }
}
