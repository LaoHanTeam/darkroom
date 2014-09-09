package org.shareGame.controler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shareGame.core.EventDispatcher;
import org.shareGame.core.event.TempChangeEvent;

/**
 * 温度类包含降温和升温的同步控制
 * 降温：启动——温度>0
 *     挂起——温度<=0
 * 升温：启动——手动启动
 *     挂起——完成升温过程后挂起
 * @author baihui.lbh
 * @version $Id: Temperature.java, v 0.1 2014年9月8日 下午5:02:49 baihui.lbh Exp $
 */
public class Temperature {
    private static Log               log         = LogFactory.getLog(Temperature.class);
    //温度范围在-10到100内变化
    private static int               temperature = 0;
    private final static Temperature instance    = new Temperature();

    //用于同步的变量，表示已加热/未加热的状态
    private boolean                  isHeatUp    = false;

    //私有的构造器
    private Temperature() {

    }

    //获得单例对象
    public static Temperature getInstance() {
        return instance;
    }

    //开始降温
    public synchronized void readyToCoolDown() {
        if (temperature > 0 && isHeatUp == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }

    //降温结束
    public synchronized void cooled() {
        if (temperature <= 0) {
            isHeatUp = false;
            notifyAll();
        }

    }

    //开始升温
    public synchronized void readyToHeatUp() {
        try {
            if (isHeatUp == false) {
                wait();
            }
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

    //升温结束
    public synchronized void heated() {
        isHeatUp = true;
        notifyAll();
    }

    public int getTemp() {
        return temperature;
    }

    public void setTemp(int temp) {
        temperature = temp;
    }

    //温度的自减
    public synchronized void selfSub() {
        while (temperature > 0) {
            temperature--;
            tempReport();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("", e);
            }

        }

    }

    //曲线升温
    public synchronized void selfPlus() {
        int[] heatCurve = { 0, 3, 2, 2, 1, 1, 1 };
        for (int i = 0; i < heatCurve.length; ++i) {
            temperature += heatCurve[i];
            tempReport();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("", e);
            }
        }
    }

    public void tempReport() {
        TempChangeEvent event = new TempChangeEvent();
        event.setTemp(temperature);
        EventDispatcher.get().dispatch(event);
    }

}
