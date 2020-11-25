package com.revature.models;

import java.io.Serializable;

public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int roleId;
	private String role;
	
	
	public Roles() {
		super();
	}


	public Roles(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + roleId;
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
		Roles other = (Roles) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (roleId != other.roleId)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", role=" + role + "]";
	}
	
	

}
