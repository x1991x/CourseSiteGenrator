
package csg.transactions;

import csg.data.MeetingTimesPrototype;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class EditLectureTableCell_Transaction implements jTPS_Transaction{
    MeetingTimesPrototype mtToEdit;
    int columnIndex;
    String oldValue;
    String newValue;
    TableView table;
    public EditLectureTableCell_Transaction(MeetingTimesPrototype mtToEdit,  
                    String oldValue, String newValue, int columnIndex, TableView table) {
        this.mtToEdit = mtToEdit;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.columnIndex = columnIndex;
        this.table = table;
    }
 
    @Override
    public void doTransaction() {
       switch(columnIndex) {
               case 0: mtToEdit.setSection(newValue);   break;
               case 1: mtToEdit.setDays(newValue);  break;
               case 2: mtToEdit.setTime(newValue);  break;
               case 3:mtToEdit.setRoom(newValue);  break;
       }
       table.refresh();
    }

    @Override
    public void undoTransaction() {
        switch(columnIndex) {
               case 0: mtToEdit.setSection(oldValue);  break;
               case 1: mtToEdit.setDays(oldValue);  break;
               case 2: mtToEdit.setTime(oldValue);  break;
               case 3:mtToEdit.setRoom(oldValue);  break;
       }
        table.refresh();
    }
}
