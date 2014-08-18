/**
 * 
 */
package org.shareGame.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.shareGame.core.event.GameOverEvent;
import org.shareGame.utils.CollectionUtils;

/**
 *  事件总线，用于事件分发
 * @author ledkk
 * 
 */
public class EventDispatcher {
	private static Log logger = LogFactory.getLog(EventDispatcher.class);
	private static final EventDispatcher instance = new EventDispatcher();
	
	/**
	 * 存放事件处理相关的类
	 */
	private Map<String,List<EventHandler<? super Event>>> handleStore = new HashMap<String, List<EventHandler<? super Event>>>();
	
	private EventDispatcher(){
	}
	
	public static EventDispatcher get(){
		return instance;
	}
	
	/**
	 * 分发事件
	 * @param event
	 */
	public void dispatch(Event event){
		String eventType = event.getEventType();
		List<EventHandler<? super Event>> handles = handleStore.get(eventType);
		if(CollectionUtils.isEmpty(handles)){
			return ;
		}
		
		for(EventHandler<? super Event> handle : handles){
			boolean isContinue = handle.handle(event);
			if(!isContinue) break;
		}
	}
	
	
	/**
	 * 注册事件处理
	 * @param event
	 * @param handler
	 */
	@SuppressWarnings("unchecked")
	public <T extends Event> void registerEvent(T event , EventHandler<T> handler){
		String eventType = event.getEventType();
		List<EventHandler<? super Event>> handles = handleStore.get(eventType);
		if(handles == null){
			handles = new ArrayList<EventHandler<? super Event>>();
			handleStore.put(eventType, handles);
		}
		handles.add((EventHandler<? super Event>) handler);
	}
	
	
	
	static{
		EventDispatcher.get().registerEvent(new GameOverEvent(), new EventHandler<GameOverEvent>() {

			@Override
			public boolean handle(GameOverEvent event) {
				logger.info("------------game over ------------------");
				WorldClock.get().stop();
				return false;
			}
		});
	}
}
