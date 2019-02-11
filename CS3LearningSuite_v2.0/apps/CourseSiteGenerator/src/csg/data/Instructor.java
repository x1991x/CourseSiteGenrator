
package csg.data;

/**
 *
 * @author New User
 */
public class Instructor {
    private String name;
    private String link;
    private String email;
    private String room;
    private String photoPath;
    private String hours;
    
    public Instructor() {
        name = "";
        link = "";
        email = "";
        room = "";
        photoPath = "";
        hours = "";
    }
    
    public void setName(String initName) {
        this.name = initName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setEmail(String initEmail) {
        this.email = initEmail;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setRoom(String initRoom) {
        this.room = initRoom;
    }
    public String getRoom() {
        return this.room;
    }
    
    public void setLink(String initLink) {
            this.link = initLink;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public void setPhotoPath(String initPhotoPath) {
        this.photoPath = initPhotoPath;
    }
    
    public String getPhotoPath() {
        return this.photoPath;
    }
    
    public void setHours(String initHours) {
        this.hours = initHours;
    }
    
    public String getHours() {
        return this.hours;
    }
    
    public void clear() {
        name = "";
        link = "";
        email = "";
        room = "";
        photoPath = "";
        hours = "";
    }
}