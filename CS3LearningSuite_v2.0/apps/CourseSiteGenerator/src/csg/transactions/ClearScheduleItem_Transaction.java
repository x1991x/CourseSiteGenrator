
package csg.transactions;

import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_DEFAULT_OPTION;
import csg.data.CourseSiteGeneratorData;
import csg.data.SchedulesPrototype;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import jtps.jTPS_Transaction;
import properties_manager.PropertiesManager;

/**
 *
 * @author Jie Dai
 */
public class ClearScheduleItem_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    SchedulesPrototype selectedItem;
    SchedulesPrototype itemBeforeClear;
    String typeDefaultOption;
    ComboBox typeBox;
    DatePicker datePicker;
    TextField titleTF, topicTF, linkTF;
    TableView schedulesTable;

    public ClearScheduleItem_Transaction(CourseSiteGeneratorData data, SchedulesPrototype selectedItem, 
                    SchedulesPrototype itemBeforeClear, String typeDefaultOption, ComboBox typeBox, DatePicker datePicker, 
                    TextField titleTF, TextField topicTF, TextField linkTF, TableView schedulesTable) {
        this.data = data;
        this.selectedItem = selectedItem;
        this.itemBeforeClear = itemBeforeClear;
        this.typeDefaultOption = typeDefaultOption;
        this.typeBox = typeBox;
        this.datePicker = datePicker;
        this.titleTF = titleTF;
        this.topicTF = topicTF;
        this.linkTF = linkTF;
        this.schedulesTable = schedulesTable;
    }
    
    
    @Override
    public void doTransaction() {
        this.typeBox.setValue(typeDefaultOption);
        this.datePicker.setValue(LocalDate.now());
        this.titleTF.setText("");
        this.topicTF.setText("");
        this.linkTF.setText("");
        schedulesTable.getSelectionModel().select(null);
    }

    @Override
    public void undoTransaction() {
        this.typeBox.setValue(this.itemBeforeClear.getType());
        this.datePicker.setValue(this.itemBeforeClear.getLocalDate());
        this.titleTF.setText(this.itemBeforeClear.getTitle());
        this.topicTF.setText(this.itemBeforeClear.getTopic());
        this.linkTF.setText(this.itemBeforeClear.getLink());
        schedulesTable.getSelectionModel().select(selectedItem);
    }
    
}
