package org.shareGame.core.event;

import org.shareGame.core.Event;

/**
 * 温度变化事件
 * @author baihui.lbh
 * @version $Id: TempChangeEvent.java, v 0.1 2014年8月20日 下午2:20:37 baihui.lbh Exp $
 */
public class TempChangeEvent extends Event {
    private int temperature;

    public TempChangeEvent() {
        super("TEMP_CHANGED");

    }

    public int getTemp() {
        return temperature;
    }

    public void setTemp(int temp) {
        temperature = temp;
    }
}
