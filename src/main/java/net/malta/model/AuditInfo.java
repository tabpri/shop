package net.malta.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AuditInfo implements IAuditInfo {

	@NotNull
	private Integer createdBy;
	@NotNull	
	private Integer updatedBy;
	@NotNull	
	private Date updatedDate;
	@NotNull	
	private Date createdDate;
	
	@Override
	public Integer getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	@Override
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public Date getUpdatedDate() {
		return updatedDate;
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public Date getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}	
}