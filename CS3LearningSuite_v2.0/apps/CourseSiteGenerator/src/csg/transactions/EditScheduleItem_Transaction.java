
package csg.transactions;

import csg.data.SchedulesPrototype;
import javafx.scene.control.TableView;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class EditScheduleItem_Transaction implements jTPS_Transaction {
    TableView schedulesTable;
    SchedulesPrototype selectedItem;
    String oldType, newType;
    String oldDate, newDate;
    String oldTitle, newTitle;
    String oldTopic, newTopic;
    String oldLink, newLink;

    public EditScheduleItem_Transaction(TableView schedulesTable, SchedulesPrototype selectedItem, String newType, String newDate, 
                    String newTitle, String newTopic, String newLink) {
        this.schedulesTable = schedulesTable;
        this.selectedItem = selectedItem;
        this.oldType = selectedItem.getType();
        this.oldDate = selectedItem.getDate();
        this.oldTitle = selectedItem.getTitle();
        this.oldTopic = selectedItem.getTopic();
        this.oldLink = selectedItem.getLink();
        this.newType = newType;
        this.newDate = newDate;
        this.newTitle = newTitle;
        this.newTopic = newTopic;
        this.newLink = newLink;
    }

    @Override
    public void doTransaction() {
        selectedItem.setType(newType);
        selectedItem.setDate(newDate);
        selectedItem.setTitle(newTitle);
        selectedItem.setTopic(newTopic);
        selectedItem.setLink(newLink);
        schedulesTable.refresh();
    }

    @Override
    public void undoTransaction() {
        selectedItem.setType(oldType);
        selectedItem.setDate(oldDate);
        selectedItem.setTitle(oldTitle);
        selectedItem.setTopic(oldTopic);
        selectedItem.setLink(oldLink);
        schedulesTable.refresh();
    }
    
    
}
