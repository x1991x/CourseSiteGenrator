/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

/**
 *
 * @author New User
 */
public class Page {
    String name;
    String link;
    
    public Page() {
        name = "";
        link = "";
    }
    
    public Page(String initName, String initLink) {
        this.name = initName;
        this.link = initLink;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public void setName(String initName) {
        this.name = initName;
    }
    
    public void setLink(String initLink) {
        this.link = initLink;
    }
}
