package com.qf.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

public class RoleMenuDto {
    int roleId;
    ArrayList<Integer> newmenuIds;
    ArrayList<Integer> oldmenuIds;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ArrayList<Integer> getNewmenuIds() {
        return newmenuIds;
    }

    public void setNewmenuIds(ArrayList<Integer> newmenuIds) {
        this.newmenuIds = newmenuIds;
    }

    public ArrayList<Integer> getOldmenuIds() {
        return oldmenuIds;
    }

    public void setOldmenuIds(ArrayList<Integer> oldmenuIds) {
        this.oldmenuIds = oldmenuIds;
    }

    @Override
    public String toString() {
        return "RoleMenuDto{" +
                "roleId=" + roleId +
                ", newmenuIds=" + newmenuIds +
                ", oldmenuIds=" + oldmenuIds +
                '}';
    }
}
