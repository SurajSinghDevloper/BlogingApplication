package com.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int roleId;
    private String roleName;
    private String roleDescription;


    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getter and Setter methods...


    public String getRoleName() {
        return roleName;
    }

    public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
    
}
