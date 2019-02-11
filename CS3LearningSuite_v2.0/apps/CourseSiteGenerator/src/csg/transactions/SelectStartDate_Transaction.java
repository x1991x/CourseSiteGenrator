
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.DatePicker;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelectStartDate_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    DatePicker startDate;
    LocalDate startDateOldVal, startDateNewVal;
    LocalDate endDateVal;
    ChangeListener startDateListener;
    public SelectStartDate_Transaction(CourseSiteGeneratorData data, DatePicker startDate, LocalDate startDateOldVal,
                     LocalDate startDateNewVal, LocalDate endDateVal, ChangeListener startDateListener) {
        this.data = data;
        this.startDate = startDate;
        this.startDateOldVal = startDateOldVal;
        this.startDateNewVal = startDateNewVal;
        this.endDateVal = endDateVal;
        this.startDateListener = startDateListener;
    }
    
    @Override
    public void doTransaction() {
        startDate.valueProperty().removeListener(startDateListener);
        data.setStartDatePickerBox(startDateNewVal);
        data.selectDateRange(startDateNewVal, endDateVal);
        startDate.valueProperty().addListener(startDateListener);
    }

    @Override
    public void undoTransaction() {
        startDate.valueProperty().removeListener(startDateListener);
        data.setStartDatePickerBox(startDateOldVal);
        data.selectDateRange(startDateOldVal, endDateVal);
        startDate.valueProperty().addListener(startDateListener);
    }
    
}
