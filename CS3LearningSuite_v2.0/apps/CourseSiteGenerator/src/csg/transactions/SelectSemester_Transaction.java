
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.workspace.CourseSiteGeneratorWorkspace;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelectSemester_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    ComboBox semesterBox;
    String oldVal, newVal;
    ChangeListener semesterListener;

    public SelectSemester_Transaction(CourseSiteGeneratorData data, ComboBox semesterBox, String oldVal, 
                    String newVal, ChangeListener semesterListener) {
        this.data = data;
        this.semesterBox = semesterBox;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.semesterListener = semesterListener;
    }

    @Override
    public void doTransaction() {
        semesterBox.getSelectionModel().selectedItemProperty().removeListener(semesterListener);
        data.setSemesterBox(newVal);
        semesterBox.getSelectionModel().selectedItemProperty().addListener(semesterListener);
    }

    @Override
    public void undoTransaction() {
        semesterBox.getSelectionModel().selectedItemProperty().removeListener(semesterListener);
        data.setSemesterBox(oldVal);
        semesterBox.getSelectionModel().selectedItemProperty().addListener(semesterListener);
    }
}
