
package csg.transactions;

import csg.data.MeetingTimesPrototype;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class EditRecitationTableCell_Transaction implements jTPS_Transaction{
    MeetingTimesPrototype mtToEdit;
    int columnIndex;
    String oldValue;
    String newValue;
    TableView table;
    public EditRecitationTableCell_Transaction(MeetingTimesPrototype mtToEdit,  
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
               case 1: mtToEdit.setDaysAndTime(newValue);  break;
               case 2: mtToEdit.setRoom(newValue);  break;
               case 3: mtToEdit.setTa1(newValue);  break;
               case 4: mtToEdit.setTa2(newValue);  break;
       }
       table.refresh();
    }

    @Override
    public void undoTransaction() {
        switch(columnIndex) {
               case 0: mtToEdit.setSection(oldValue);   break;
               case 1: mtToEdit.setDaysAndTime(oldValue);  break;
               case 2: mtToEdit.setRoom(oldValue);  break;
               case 3: mtToEdit.setTa1(oldValue);  break;
               case 4: mtToEdit.setTa2(oldValue);  break;
       }
       table.refresh();
    }
}
