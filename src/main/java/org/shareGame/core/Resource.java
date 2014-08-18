/**
 * 
 */
package org.shareGame.core;

import java.io.Serializable;

/**
 * @author ledkk
 *
 */
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String type;
	
	public Resource (String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Resource other = (Resource) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public String desc(){
		return "";
	}
	
	
}
