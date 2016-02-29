/**
 * @author SB
 */
package net.malta.error;

import net.malta.error.IError;

public class ValidationError extends Error implements IError {

	public ValidationError(String errorCode, Object... values) {
		this.errorCode = errorCode;
		this.values = values;
	}

	public ValidationError(String errorCode, String errorMessage,Object... values) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.values = values;
	}			
	
}