package net.malta.model;

import java.util.Date;

public interface IAuditInfo {

	Integer getCreatedBy();

	void setCreatedBy(Integer createdBy);

	Integer getUpdatedBy();

	void setUpdatedBy(Integer updatedBy);

	Date getUpdatedDate();

	void setUpdatedDate(Date updatedDate);

	Date getCreatedDate();

	void setCreatedDate(Date createdDate);

}