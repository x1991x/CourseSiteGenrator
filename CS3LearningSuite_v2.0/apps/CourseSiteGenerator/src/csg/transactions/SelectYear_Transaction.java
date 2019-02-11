
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
public class SelectYear_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    ComboBox yearBox;
    String oldVal, newVal;
    ChangeListener yearListener;

    public SelectYear_Transaction(CourseSiteGeneratorData data, ComboBox yearBox, String oldVal, 
                    String newVal, ChangeListener yearListener) {
        this.data = data;
        this.yearBox = yearBox;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.yearListener = yearListener;
    }

    @Override
    public void doTransaction() {
        yearBox.getSelectionModel().selectedItemProperty().removeListener(yearListener);
        data.setYearBox(newVal);
        yearBox.getSelectionModel().selectedItemProperty().addListener(yearListener);
    }

    @Override
    public void undoTransaction() {
        yearBox.getSelectionModel().selectedItemProperty().removeListener(yearListener);
        data.setYearBox(oldVal);
        yearBox.getSelectionModel().selectedItemProperty().addListener(yearListener);
    }
}
