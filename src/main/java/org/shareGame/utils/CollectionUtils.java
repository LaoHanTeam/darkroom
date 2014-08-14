/**
 * 
 */
package org.shareGame.utils;

import java.util.Collection;

/**
 * @author ledkk
 *
 */
public class CollectionUtils {
	
	/**
	 * 针对集合判断是否为空
	 * null    true
	 * []      true
	 * [?]     false
	 * @param col
	 * @return
	 */
	public static boolean isEmpty(Collection<?> col){
		return col == null || col.isEmpty();
	}
}
