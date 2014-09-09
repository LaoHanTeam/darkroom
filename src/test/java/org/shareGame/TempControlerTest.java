/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package org.shareGame;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.shareGame.controler.TempControler;
import org.shareGame.core.Event;
import org.shareGame.core.EventDispatcher;
import org.shareGame.core.EventHandler;
import org.shareGame.core.event.TempChangeEvent;

/**
 * 
 * @author baihui.lbh
 * @version $Id: TempControlerTest.java, v 0.1 2014年8月20日 下午2:29:38 baihui.lbh Exp $
 */
public class TempControlerTest {

    public static void main(String[] args) {
        EventDispatcher.get().registerEvent(new TempChangeEvent(), new EventHandler<Event>() {

            @Override
            public boolean handle(Event event) {
                System.out.println("temperature:" + ((TempChangeEvent) event).getTemp());
                return false;
            }
        });
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                TempControler tc = TempControler.getInstance();
                tc.cooling();
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                TempControler tc = TempControler.getInstance();

                tc.warming();
            }
        });
        exec.shutdown();
    }
}
