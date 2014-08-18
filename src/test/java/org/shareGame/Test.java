/**
 * 
 */
package org.shareGame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shareGame.core.Event;
import org.shareGame.core.EventDispatcher;
import org.shareGame.core.EventHandler;
import org.shareGame.core.Stage;
import org.shareGame.core.StageManger;
import org.shareGame.core.Wood;
import org.shareGame.core.WorldClock;

/**
 * @author ledkk
 *
 */
public class Test {

	private static Log log = LogFactory.getLog(Test.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("----------------------");
//		testStageManager();
		testWood();
//		testWorldClock();
		log.info("----------------------");
	}
	
	
	public static void testWorldClock(){
		WorldClock wc = WorldClock.get();
	}
	
	
	public static void testStageManager(){
		long curTime = WorldClock.get().getCurTime();
		System.out.println(curTime);
		StageManger manger = StageManger.get();
		Stage darkRoom = new Stage("darkRoom");
		manger.addStage(darkRoom);
		Stage forest = new Stage("forest");
		manger.addStage(forest);
		manger.setCurrentStage(forest);
		manger.putDaemon(darkRoom);
		curTime = WorldClock.get().getCurTime();
		System.out.println(manger + "  curTime : "+curTime);
		manger.save();
		
		Stage river = new Stage("river");
		manger.addStage(river);
		manger.setCurrentStage(river);
		
		curTime = WorldClock.get().getCurTime();
		System.out.println(manger + "  curTime : "+curTime);
		try {
			Thread.sleep(10*60*1000);
		} catch (InterruptedException e) {
		}
		manger.load();
		curTime = WorldClock.get().getCurTime();
		System.out.println(manger + "  curTime : "+curTime);
		
	}
	
	
	public static void testWood(){
		Wood wood = new Wood();
		wood.fire();
	}
	
	
	public static void testEventDispatcher(){
		A a = new A("click");
		EventDispatcher.get().registerEvent(a, new EventHandler<A>() {

			public boolean handle(A event) {
				System.out.println(event.getEventType()+" ------  "+event.getData() +"  1");
				return true;
			}
		});
		
		EventDispatcher.get().registerEvent(a, new EventHandler<A>() {

			public boolean handle(A event) {
				System.out.println(event.getEventType()+" ------  "+event.getData() +"  2");
				return false;
			}
		});
		
		
		EventDispatcher.get().registerEvent(a, new EventHandler<A>() {

			public boolean handle(A event) {
				System.out.println(event.getEventType()+" ------  "+event.getData()+"  3");
				return true;
			}
		});
		
		
		EventDispatcher.get().dispatch(a);
	}
	
	
	public static class A extends Event{
		
		private Object data;

		public A(String eventType) {
			super(eventType);
			this.data = "12312312";
		}
		
		
		public Object getData(){
			return data;
		}
		
	}
	
	public static class B extends A{

		public B(String eventType) {
			super(eventType);
		}
		
		
	}

}
