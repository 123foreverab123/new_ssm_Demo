package com.qf.vo;

public class RoleInfo {
    private int roleid;
    private String rolename;
    private String shortname;
    private boolean selected;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleid=" + roleid +
                ", rolename='" + rolename + '\'' +
                ", shortname='" + shortname + '\'' +
                ", selected=" + selected +
                '}';
    }
}
