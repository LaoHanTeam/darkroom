/**
 * 
 */
package org.shareGame.core;

import java.io.Serializable;

/**
 * @author ledkk
 *
 */
public class Stage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long firewoodNum = 100; //初始木柴100
	
	private long temp = 20; // 初始温度
	
	
	
	private String name ;
	
	public Stage(String name){
		this.name = name;
	}

	
	public String getName(){
		return name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Stage other = (Stage) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Stage [name=" + name + "]";
	}
}
