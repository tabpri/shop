package net.malta.beans;
import org.apache.struts.upload.FormFile;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
public class PaymentMethodForm
    extends org.apache.struts.validator.ValidatorForm
    implements java.io.Serializable
{
private static final java.text.DateFormat simpleformat = new java.text.SimpleDateFormat("yyyy/MM/dd");private static final java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy/MM/dd hh:mm:ss");static { format.setLenient(true); }private String note;
public void setNote(String note){
this.note = note;
}
public String getNote(){
return this.note;
}
private Integer id;
public void setId(Integer id){
this.id = id;
}
public Integer getId(){
return this.id;
}
private String name;
public void setName(String name){
this.name = name;
}
public String getName(){
return this.name;
}
FormFile[] formFiles = new FormFile[10];
public void setFormFiles(FormFile[] formFiles){
this.formFiles = formFiles;
}
public FormFile[] getFormFiles(){
return this.formFiles;
}
public void reset(org.apache.struts.action.ActionMapping mapping, javax.servlet.http.HttpServletRequest request){}
}
