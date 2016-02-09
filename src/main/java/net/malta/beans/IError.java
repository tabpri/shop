/**
 * @author SB
 */
package net.malta.beans;

public interface IError {

	public void setErrorCode(String errorCode);
	public String getErrorCode();
	public void setValues(Object... values);
	public Object[] getValues();
	
}
