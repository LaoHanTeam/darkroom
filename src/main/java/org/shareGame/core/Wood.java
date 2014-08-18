/**
 * 
 */
package org.shareGame.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shareGame.core.event.GameOverEvent;


/**
 * @author ledkk
 *
 */
public class Wood extends Resource {
	private static final Log logger = LogFactory.getLog(Wood.class);
	
	public static final long fireLength = (long)0.5*WorldClock.timeScale;
	
	private static final Event fireOverE = new Event("FIRE_OVER");
	
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
		EventDispatcher.get().registerEvent(fireOverE, new EventHandler<Event>() {

			@Override
			public boolean handle(Event event) {
				logger.info("火烧完了。。。 又要变冷了。。。。。");
				logger.info("-------GAME OVER----------");
				EventDispatcher.get().dispatch(new GameOverEvent());
				return false;
			}
		});
		Thread fireThread = new Thread("FIRE"){

			@Override
			public void run() {
				try {
					while(proce >=0 ){
						proce --;
						if(proce <=0){
							System.out.println(WorldClock.get().getCurTime()+"我靠，烧完了~~~~~~~");
							EventDispatcher.get().dispatch(fireOverE);
							return ;
						}
						System.out.println(WorldClock.get().getCurTime()+"我烧烧烧~~~~~~~ oh ~~ 还剩"+proce+" 可以烧了！！！");
						Thread.sleep(fireLength/100);
					}
				} catch (InterruptedException e) {
				}
			}
			
		};
		
//		fireThread.setDaemon(true);
		fireThread.start();
	}
}
