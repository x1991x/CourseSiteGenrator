
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
public class SelectEndDate_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    DatePicker endDate;
    LocalDate endDateOldVal, endDateNewVal;
    LocalDate startDateVal;
    ChangeListener endDateListener;
    public SelectEndDate_Transaction(CourseSiteGeneratorData data, DatePicker endDate, LocalDate endDateOldVal, 
                    LocalDate endDateNewVal, LocalDate startDateVal, ChangeListener endDateListener) {
        this.data = data;
        this.endDate = endDate;
        this.endDateOldVal = endDateOldVal;
        this.endDateNewVal = endDateNewVal;
        this.startDateVal = startDateVal;
        this.endDateListener = endDateListener;
    }
    
    
    @Override
    public void doTransaction() {
        endDate.valueProperty().removeListener(endDateListener);
        data.setEndDatePickerBox(endDateNewVal);
        data.selectDateRange(startDateVal, endDateNewVal);
        endDate.valueProperty().addListener(endDateListener);
    }

    @Override
    public void undoTransaction() {
        endDate.valueProperty().removeListener(endDateListener);
        data.setEndDatePickerBox(endDateOldVal);
        data.selectDateRange(startDateVal, endDateOldVal);
        endDate.valueProperty().addListener(endDateListener);
    }
    
}
