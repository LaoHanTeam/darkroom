/**
 * 
 */
package org.shareGame.core;

/**
 * 
 * 游戏系统中的事件类，用于包装系统中出现的事件,
 *	开发人员可以对不同的事件，定义不同的事件子类
 * @author ledkk
 *	
 */
public class Event {
	
	/**
	 * 事件区分的唯一标识，用于实践触发及其他处理
	 */
	private String eventType;
	
	public Event(String eventType){
		this.eventType = eventType;
	}
	
	public String getEventType(){
		return eventType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [eventType=" + eventType + "]";
	}
}
