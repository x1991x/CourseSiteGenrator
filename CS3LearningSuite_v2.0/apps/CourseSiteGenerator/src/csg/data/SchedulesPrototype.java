
package csg.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jie Dai
 */
public class SchedulesPrototype <E extends Comparable<E>> implements Comparable<E>{
    private StringProperty type;
    private StringProperty date;
    private StringProperty title;
    private StringProperty topic;
    private String link;
    private LocalDate localDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public SchedulesPrototype(){
        this.localDate = LocalDate.now();
        this.type = new SimpleStringProperty("");
        this.date = new SimpleStringProperty(localDate.format(formatter));
        this.title = new SimpleStringProperty("");
        this.topic = new SimpleStringProperty("");
        this.link = "none";
    }
    
    public SchedulesPrototype(ScheduleType type, String date, String title, String topic, String link) {
        this.type = new SimpleStringProperty(type.toString());
        this.date = new SimpleStringProperty(date);
        this.title = new SimpleStringProperty(title);
        this.topic = new SimpleStringProperty(topic);
         if ((link.isEmpty() || link == null) && !type.equals("Holiday"))
            this.link = "none";
         else if ((link.isEmpty() || link == null) && type.equals("Holiday"))
             this.link ="";
         else
            this.link = link;
        this.localDate = LocalDate.parse(date, formatter);
    }
    
    public String getType() {
        return type.get();
    }

    public String getDate() {
        return date.get();
    }
    
    public String getMonth() {
        return String.valueOf(this.localDate.getMonthValue());
    }
    
    public String getDay() {
        return String.valueOf(this.localDate.getDayOfMonth());
    }
    
    public String getYear() {
        return String.valueOf(this.localDate.getYear());
    }
    
    public String getTitle() {
        return title.get();
    }

    public String getTopic() {
        return topic.get();
    }

    public String getLink() {
        return link;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
    
    public void setType(String type) {
         if ((link.isEmpty() || link == null) && !type.equals("Holiday"))
            this.link = "none";
         else if ((link.isEmpty() || link == null) && type.equals("Holiday"))
             this.link ="";
         else
            this.link = link;
        this.type.setValue(type);
    }

    public void setDate(String date) {
        this.date.setValue(date);
        this.localDate.parse(date, formatter);
    }

    public void setDate(LocalDate localDate) {
        String date = localDate.format(formatter);
        this.date.setValue(date);
        this.localDate = localDate;
        
    }
    
    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public void setTopic(String topic) {
        this.topic.setValue(topic);
    }

    public void setLink(String link) {
         if ((link.isEmpty() || link == null) && !type.equals("Holiday"))
            this.link = "none";
         else if ((link.isEmpty() || link == null) && type.equals("Holiday"))
             this.link ="";
         else
            this.link = link;
    }

    @Override
    public int compareTo(E otherSchedule) {
        LocalDate otherDate = ((SchedulesPrototype)otherSchedule).getLocalDate();
        if (this.localDate.isBefore(otherDate))
            return -1;
        else if (this.localDate.isAfter(otherDate))
            return 1;
        else
            return 0;
    }
    
    
}
