
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
public class SelectSubject_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    ComboBox subjectBox;
    String oldVal, newVal;
    ChangeListener subjectListener;

    public SelectSubject_Transaction(CourseSiteGeneratorData data, ComboBox subjectBox, String oldVal, String newVal, ChangeListener subjectListener) {
        this.data = data;
        this.subjectBox = subjectBox;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.subjectListener = subjectListener;
    }

    @Override
    public void doTransaction() {
          subjectBox.getSelectionModel().selectedItemProperty().removeListener(subjectListener);
          data.setSubjectBox(newVal);
          subjectBox.getSelectionModel().selectedItemProperty().addListener(subjectListener);
    }

    @Override
    public void undoTransaction() {
       subjectBox.getSelectionModel().selectedItemProperty().removeListener(subjectListener);
       data.setSubjectBox(oldVal);
       subjectBox.getSelectionModel().selectedItemProperty().addListener(subjectListener);
    }
}
