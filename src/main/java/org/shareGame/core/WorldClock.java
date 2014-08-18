/**
 * 
 */
package org.shareGame.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 游戏中的时钟
 * @author ledkk
 *
 */
public class WorldClock {
	
	private static final Log logger = LogFactory.getLog(WorldClock.class);
	/**
	 * 游戏时间和电脑时间之间的差距
	 */
	public static final long timeScale = 5*1000*60;
	
	private volatile long curTime ;
	
	
	private Thread clock;
	
	private static final WorldClock instance = new WorldClock();
	
	public static WorldClock get(){
		return instance;
	}
	
	
	private WorldClock(){
		clock = new Thread("WorldClock"){
			@Override
			public void run() {
				while(!isInterrupted()){
					synchronized (instance) {
							curTime ++;
					}
					System.out.println(curTime);
					try {
						Thread.sleep(timeScale);
					} catch (InterruptedException e) {
						logger.info("--------------结束------");
						return ;
					}
				}
			}
			
		};
//		clock.setDaemon(true);
		clock.start();
	}
	
	
	public void stop(){
		clock.interrupt();
	}
	
	public long getCurTime(){
		return curTime;
	}
	
	void setCurTime(long curTime){
		synchronized (instance) {
			this.curTime = curTime;
		}
	}
	
	
}
