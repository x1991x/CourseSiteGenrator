
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelecteOHStartTime_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    ComboBox startTimeBox;
    String oldStartTime, newStartTime;
    String endTime;
    ChangeListener startTimeListener;

    public SelecteOHStartTime_Transaction(CourseSiteGeneratorData data, ComboBox startTimeBox, String oldStartTime, String newStartTime, String endTime, ChangeListener startTimeListener) {
        this.data = data;
        this.startTimeBox = startTimeBox;
        this.oldStartTime = oldStartTime;
        this.newStartTime = newStartTime;
        this.endTime = endTime;
        this.startTimeListener = startTimeListener;
    }
    
    @Override
    public void doTransaction() {
        startTimeBox.getSelectionModel().selectedItemProperty().removeListener(startTimeListener);
        data.setStartTimeBox(newStartTime);
        data.changeTimeRange(newStartTime, endTime);
        startTimeBox.getSelectionModel().selectedItemProperty().addListener(startTimeListener);
    }

    @Override
    public void undoTransaction() {
        startTimeBox.getSelectionModel().selectedItemProperty().removeListener(startTimeListener);
        data.setStartTimeBox(oldStartTime);
        data.changeTimeRange(oldStartTime, endTime);
        startTimeBox.getSelectionModel().selectedItemProperty().addListener(startTimeListener);
    }
}
