
package djf.ui.controllers;

import java.io.IOException;

/**
 *
 * @author Jie Dai
 */
public class NoPageSelectedException extends IOException{
    public NoPageSelectedException(){}
    
    public NoPageSelectedException(String msg) {
        super(msg);
    }
}
