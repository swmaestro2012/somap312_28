// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package sla.model;

import java.util.Date;
import sla.model.Page;

privileged aspect Page_Roo_JavaBean {
    
    public String Page.getTitle() {
        return this.title;
    }
    
    public void Page.setTitle(String title) {
        this.title = title;
    }
    
    public String Page.getContent() {
        return this.content;
    }
    
    public void Page.setContent(String content) {
        this.content = content;
    }
    
    public String Page.getPassword() {
        return this.password;
    }
    
    public void Page.setPassword(String password) {
        this.password = password;
    }
    
    public Date Page.getCreateDate() {
        return this.createDate;
    }
    
    public void Page.setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
