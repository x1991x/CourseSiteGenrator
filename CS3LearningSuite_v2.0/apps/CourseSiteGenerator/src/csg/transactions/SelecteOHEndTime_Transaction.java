
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelecteOHEndTime_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    ComboBox endTimeBox;
    String oldEndTime, newEndTime;
    String startTime;
    ChangeListener endTimeListener;

    public SelecteOHEndTime_Transaction(CourseSiteGeneratorData data, ComboBox endTimeBox, String oldEndTime, 
                    String newEndTime, String startTime, ChangeListener endTimeListener) {
        this.data = data;
        this.endTimeBox = endTimeBox;
        this.oldEndTime = oldEndTime;
        this.newEndTime = newEndTime;
        this.startTime = startTime;
        this.endTimeListener = endTimeListener;
    }
    
    @Override
    public void doTransaction() {
        endTimeBox.getSelectionModel().selectedItemProperty().removeListener(endTimeListener);
        data.setEndTimeBox(newEndTime);
        data.changeTimeRange(startTime, newEndTime);
        endTimeBox.getSelectionModel().selectedItemProperty().addListener(endTimeListener);
    }

    @Override
    public void undoTransaction() {
        endTimeBox.getSelectionModel().selectedItemProperty().removeListener(endTimeListener);
        data.setEndTimeBox(oldEndTime);
        data.changeTimeRange(startTime, oldEndTime);
        endTimeBox.getSelectionModel().selectedItemProperty().addListener(endTimeListener);
    }
}
