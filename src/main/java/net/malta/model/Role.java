package net.malta.model;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {
	
	private static final long serialVersionUID = -98214963121284302L;
	
	private Integer id;
	private String roleName;
	private String roleDescription;

	private List<AppFunction>  appFunctions;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public List<AppFunction> getAppFunctions() {
		return appFunctions;
	}
	public void setAppFunctions(List<AppFunction> appFunctions) {
		this.appFunctions = appFunctions;
	}
	
}