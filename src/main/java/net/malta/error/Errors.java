/**
 * @author SB
 */
package net.malta.error;

import java.util.ArrayList;

public class Errors extends ArrayList<IError>{

	private static final long serialVersionUID = 2618231088181379107L;

	public Errors() {
		super();
	}

	public boolean hasErrors() {
		return this.size() > 0;
	}
}
