
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
public class SelectStyle_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    ComboBox styleBox;
    String oldVal, newVal;
    ChangeListener styleListener;

    public SelectStyle_Transaction(CourseSiteGeneratorData data, ComboBox styleBox, String oldVal, String newVal, ChangeListener styleListener) {
        this.data = data;
        this.styleBox = styleBox;
        this.oldVal = oldVal;
        this.newVal = newVal;
        this.styleListener = styleListener;
    }
    
    @Override
    public void doTransaction() {
        styleBox.getSelectionModel().selectedItemProperty().removeListener(styleListener);
        data.setStyleSheetBox(newVal);
        styleBox.getSelectionModel().selectedItemProperty().addListener(styleListener);
    }

    @Override
    public void undoTransaction() {
        styleBox.getSelectionModel().selectedItemProperty().removeListener(styleListener);
        data.setStyleSheetBox(oldVal);
        styleBox.getSelectionModel().selectedItemProperty().addListener(styleListener);
    }
}
