/**
 * 
 */
package org.shareGame.core;

/**
 * 事件的处理类，用户对相关事件经常反馈<br>
 *  T  对应的事件类型
 * @author ledkk
 *
 */
public interface EventHandler<T extends Event> {
	/**
	 * 对具体事件进行处理
	 * @param event  发生的事件
	 * @return  是否继续处理
	 */
	boolean handle(T event);
}
