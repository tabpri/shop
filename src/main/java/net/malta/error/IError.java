/**
 * @author SB
 */
package net.malta.error;

public interface IError {
	
	public void setErrorCode(String errorCode);
	public String getErrorCode();
	public void setErrorMessage(String errorMessage);
	public String getErrorMessage();	
	public void setValues(Object... values);
	public Object[] getValues();
	
}
