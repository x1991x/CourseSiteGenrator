
package csg.data;

import javafx.scene.image.Image;

/**
 *
 * @author Jie Dai
 */
public class MyImage extends Image {
    private final String url;
    
    public MyImage(String url) {
        super(url);
        this.url = url;
    }
    
    public String getUrl() {
        return this.url;
    }
}
