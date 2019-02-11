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
public class MeetingTimesPrototype {
    StringProperty section;
    StringProperty days;
    StringProperty time;
    StringProperty room;
    StringProperty daysAndTime;
    StringProperty ta1;
    StringProperty ta2;

    public MeetingTimesPrototype(String section, String days, String time, String room) {
        this.section = new SimpleStringProperty(section);
        this.days = new SimpleStringProperty(days);
        this.time = new SimpleStringProperty(time);
        this.room = new SimpleStringProperty(room);
        this.daysAndTime = new SimpleStringProperty("");
        this.ta1 = new SimpleStringProperty("");
        this.ta2 = new SimpleStringProperty("");
    }

    public MeetingTimesPrototype(String section, String daysAndTime, String room, String ta1, String ta2) {
        this.section = new SimpleStringProperty(section);
        this.days = new SimpleStringProperty("");
        this.time = new SimpleStringProperty("");
        this.room = new SimpleStringProperty(room);
        this.daysAndTime = new SimpleStringProperty(daysAndTime);
        this.ta1 = new SimpleStringProperty(ta1);
        this.ta2 = new SimpleStringProperty(ta2);
    }
    
    public void setSection(String initSection) {
        this.section = new SimpleStringProperty(initSection);
    }
    public String getSection() {
        return section.get();
    }
    public void setDays(String initDays) {
        this.days = new SimpleStringProperty(initDays);
    }
    public String getDays() {
        return days.get();
    }
    public void setTime(String initTime) {
        this.time = new SimpleStringProperty(initTime);
    }
    public String getTime() {
        return time.get();
    }
    public void setRoom(String initRoom) {
        this.room = new SimpleStringProperty(initRoom);
    }
    public String getRoom() {
        return room.get();
    }
    public void setDaysAndTime(String initDaysAndTime) {
        this.daysAndTime = new SimpleStringProperty(initDaysAndTime);
    }
    public String getDaysAndTime() {
        return daysAndTime.get();
    }
    public void setTa1(String initTa1) {
        this.ta1 = new SimpleStringProperty(initTa1);
    }
    public String getTa1() {
        return ta1.get();
    }
     public void setTa2(String initTa2) {
        this.ta2 = new SimpleStringProperty(initTa2);
    }
    public String getTa2() {
        return ta2.get();
    }
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof MeetingTimesPrototype))
            return false;
        MeetingTimesPrototype mtp = (MeetingTimesPrototype)obj;
        String section = mtp.getSection();
        String days = mtp.getDays();
        String time = mtp.getTime();
        String daysAndTime = mtp.getDaysAndTime();
        String room = mtp.getRoom();
        String ta1 = mtp.getTa1();
        String ta2 = mtp.getTa2();
        if (this.getSection().equals(section) && this.getDaysAndTime().equals(daysAndTime) 
                    && this.getRoom().equals(room) && this.getTa1().equals(ta1) && this.getTa2().equals(ta2))
            return true;
        else if (this.getSection().equals(section) && this.getDays().equals(days) && this.getTime().equals(time)
                    && this.getRoom().equals(room))
            return true;
        else 
            return false;
    }
}
