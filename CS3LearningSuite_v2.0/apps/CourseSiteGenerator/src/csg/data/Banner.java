/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author New User
 */
public class Banner {
    String subject;
    String semester;
    String number;
    String year;
    String title;
    StringProperty exportDir;
    
    public Banner() {
        title = "";
        exportDir = new SimpleStringProperty("");
    }
    
    public String getSubject() {
        return this.subject;
    }
    
    public String getNumber() {
        return this.number;
    }
    
    public String getSemester() {
        return this.semester;
    }
    
    public String getYear() {
        return this.year;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getExportDir() {
        return exportDir.get();
    }
    
    public void setSubject(String initSubject) {
        this.subject = initSubject;
    }
    
    public void setNumber(String initNumber) {
        this.number = initNumber;
    }
    
    public void setSemester(String initSemester) {
        this.semester = initSemester;
    }
    
    public void setYear(String initYear) {
        this.year = initYear;
    }
    
    public void setTitle(String initTitle) {
        this.title = initTitle;
    }
    
    public void setExportDir(String initExportDir) {
        this.exportDir.set(initExportDir);
    }
}
