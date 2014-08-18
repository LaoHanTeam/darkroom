/**
 * 
 */
package org.shareGame.core;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ledkk
 *
 */
public class Wood extends Resource {
	public static final long fireLength = 10*WorldClock.timeScale;
	
	/**
	 * 可燃烧的时长
	 */
	private double proce = 100;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wood() {
		super("wood");
	}

	@Override
	public String desc() {
		return "你是我的小呀小火柴，怎么烧你也不嫌多~~~~" +
				"红红的小脸儿温暖我的心窝"+
				"点亮我生命的火 火火火火";
	}
	
	/**
	 * 点燃火柴
	 */
	public void fire(){
		Thread fireThread = new Thread("FIRE"){

			@Override
			public void run() {
				try {
					while(proce >=0 ){
						proce --;
						if(proce <=0){
							System.out.println("我靠，烧完了~~~~~~~");
							return ;
						}
						System.out.println("我烧烧烧~~~~~~~ oh ~~ 还剩"+proce+" 可以烧了！！！");
						Thread.sleep(100/fireLength);
					}
				} catch (InterruptedException e) {
				}
			}
			
		};
		
		fireThread.setDaemon(true);
		fireThread.start();
	}
}
