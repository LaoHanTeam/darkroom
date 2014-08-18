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
	 * 针对集合判断是否为空 <br>
	 * null    true <br>
	 * []      true <br>
	 * [?]     false <br>
	 * @param col
	 * @return
	 */
	public static boolean isEmpty(Collection<?> col){
		return col == null || col.isEmpty();
	}
}
