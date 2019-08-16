package com.qf.pojo;

import lombok.Data;

import java.util.List;
@Data
public class MenuInfo {
    private int mid;
    private String menuName;
    private String menuIcon;
    private String menuUrl;
     private int parentId;
    private List<MenuInfo> childMenus;
    private List<RoleInfo> roleInfoList;

    public String getMenuUrl() {
        return menuUrl==null?"":menuUrl;
    }

}
