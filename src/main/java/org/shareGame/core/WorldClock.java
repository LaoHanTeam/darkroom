/**
 * 
 */
package org.shareGame.core;


/**
 * 游戏中的时钟
 * @author ledkk
 *
 */
public class WorldClock {
	/**
	 * 游戏时间和电脑时间之间的差距
	 */
	public static final long timeScale = 5*1000*60;
	
	private transient long curTime ;
	
	
	private Thread clock;
	
	private static final WorldClock instance = new WorldClock();
	
	public static WorldClock get(){
		return instance;
	}
	
	
	private WorldClock(){
		clock = new Thread("WorldClock"){
			@Override
			public void run() {
				try {
					while(!isInterrupted()){
						synchronized (instance) {
								curTime ++;
						}
						System.out.println(curTime);
						try {
							Thread.sleep(timeScale);
						} catch (InterruptedException e) {
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};
		clock.setDaemon(true);
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
