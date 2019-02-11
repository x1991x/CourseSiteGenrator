
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.workspace.CourseSiteGeneratorWorkspace;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;

/**
 *
 * @author New User
 */
public class SelectNumber_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    ComboBox numberBox;
    String oldVal, newVal;
    ChangeListener numberListener;

    public SelectNumber_Transaction(CourseSiteGeneratorData data, ComboBox numberBox, String oldVal, 
                    String newVal, ChangeListener numberListener) {
        this.data = data;
        this.numberBox = numberBox;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.numberListener = numberListener;
    }
    
    @Override
    public void doTransaction() {
        numberBox.getSelectionModel().selectedItemProperty().removeListener(numberListener);
        data.setNumberBox(newVal);
        numberBox.getSelectionModel().selectedItemProperty().addListener(numberListener);
    }

    @Override
    public void undoTransaction() {
        numberBox.getSelectionModel().selectedItemProperty().removeListener(numberListener);
        data.setNumberBox(oldVal);
        numberBox.getSelectionModel().selectedItemProperty().addListener(numberListener);
    }
}
