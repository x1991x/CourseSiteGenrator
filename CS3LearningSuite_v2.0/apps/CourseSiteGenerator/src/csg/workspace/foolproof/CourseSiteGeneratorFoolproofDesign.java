package csg.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_LABS_REMOVE_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_LECTURES_REMOVE_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_RECITATIONS_REMOVE_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_ADD_TA_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_END_TIME_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_REMOVE_TA_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_START_TIME_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ITEMS_ADD_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_SCHEDULE_ITEMS_REMOVE_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_DEFAULT_OPTION;
import static csg.CourseSiteGeneratorPropertyType.CSG_TABPANE;
import static csg.CourseSiteGeneratorPropertyType.OH_TA_DIALOG_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.OH_TA_DIALOG_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.OH_TA_DIALOG_OK_BUTTON;
import csg.data.CourseSiteGeneratorData;
import static csg.workspace.style.CSGStyle.CLASS_OH_TEXT_FIELD;
import static csg.workspace.style.CSGStyle.CLASS_OH_TEXT_FIELD_ERROR;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import properties_manager.PropertiesManager;


public class CourseSiteGeneratorFoolproofDesign implements FoolproofDesign {

    CourseSiteGeneratorApp app;
    private final String[] startTimeComboBox
                = {"9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm",
                     "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm", "7:00pm", "7:30pm", "8:00pm", "8:30pm",
                    }; 
    private final String[] endTimeComboBox 
                = {"9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm","1:30pm", "2:00pm", "2:30pm",
                     "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm", "7:00pm", "7:30pm", "8:00pm", "8:30pm",
                    "9:00pm"};            
    public CourseSiteGeneratorFoolproofDesign(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
        TabPane tabPane = ((TabPane) gui.getGUINode(CSG_TABPANE));
        if (tabPane != null) {
            int selectedTabIndex = tabPane.getSelectionModel().getSelectedIndex();
            switch (selectedTabIndex) {
                case 2: updateRemoveMeetingTimesItemsFoolproofDesign(); 
                            break;
                case 3: updateAddTAFoolproofDesign();
                            updateEditTAFoolproofDesign();
                            updateRemoveTAFoolproofDesign();
                            updateSelecteTimeRangeFoolProofDesign();
                            break;
                case 4: updateRemoveScheduleItemFoolproofDesign();
                            updateAddScheduleItemFoolproofDesign();
                            break;
            }    
        }
    }
    private void updateRemoveMeetingTimesItemsFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        Button removeLectureBt = ((Button) gui.getGUINode(CSG_MEETING_TIMES_LECTURES_REMOVE_BUTTON));
        Button removeRecitationBt = ((Button) gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_REMOVE_BUTTON));
        Button removeLabBt = ((Button) gui.getGUINode(CSG_MEETING_TIMES_LABS_REMOVE_BUTTON));
        if(data.isLectureSelected()) {
            removeLectureBt.setDisable(false);
        }
        else {
            removeLectureBt.setDisable(true);
        }
        
        if(data.isRecitationSelected()) {
            removeRecitationBt.setDisable(false);
        }
        else {
            removeRecitationBt.setDisable(true);
        }
        
        if(data.isLabSelected()) {
            removeLabBt.setDisable(false);
        }
        else {
            removeLabBt.setDisable(true);
        }
    }
    
    private void updateAddScheduleItemFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox typeBox = (ComboBox)gui.getGUINode(CSG_SCHEDULE_TYPE_COMBO_BOX);
        Button addButton = (Button)gui.getGUINode(CSG_SCHEDULE_ITEMS_ADD_BUTTON);
        String selectedType = (String)typeBox.getSelectionModel().getSelectedItem();
        String defaultItem = props.getProperty(CSG_SCHEDULE_TYPE_DEFAULT_OPTION);
        if (selectedType.equals(defaultItem))
            addButton.setDisable(true);
        else
            addButton.setDisable(false);
    }
    private void updateAddTAFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        // FOOLPROOF DESIGN STUFF FOR ADD TA BUTTON
        TextField nameTextField = (TextField)gui.getGUINode(CSG_OFFICE_HOURS_NAME_TEXT_FIELD);
        TextField emailTextField = (TextField)gui.getGUINode(CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD);
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        Button addTAButton = (Button)gui.getGUINode(CSG_OFFICE_HOURS_ADD_TA_BUTTON);

        // FIRST, IF NO TYPE IS SELECTED WE'LL JUST DISABLE
        // THE CONTROLS AND BE DONE WITH IT
        boolean isTypeSelected = data.isTATypeSelected();
        if (!isTypeSelected) {
            nameTextField.setDisable(true);
            emailTextField.setDisable(true);
            addTAButton.setDisable(true);
            return;
        } // A TYPE IS SELECTED SO WE'LL CONTINUE
        else {
            nameTextField.setDisable(false);
            emailTextField.setDisable(false);
            addTAButton.setDisable(false);
        }

        // NOW, IS THE USER-ENTERED DATA GOOD?
        boolean isLegalNewTA = data.isLegalNewTA(name, email);

        // ENABLE/DISABLE THE CONTROLS APPROPRIATELY
        addTAButton.setDisable(!isLegalNewTA);
        if (isLegalNewTA) {
            nameTextField.setOnAction(addTAButton.getOnAction());
            emailTextField.setOnAction(addTAButton.getOnAction());
        } else {
            nameTextField.setOnAction(null);
            emailTextField.setOnAction(null);
        }

        // UPDATE THE CONTROL TEXT DISPLAY APPROPRIATELY
        boolean isLegalNewName = data.isLegalNewName(name);
        boolean isLegalNewEmail = data.isLegalNewEmail(email);
        foolproofTextField(nameTextField, isLegalNewName);
        foolproofTextField(emailTextField, isLegalNewEmail);
    }
    
    public void updateEditTAFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        
        // FOOLPROOF DESIGN STUFF FOR OK BUTTON
        TextField nameTextField = (TextField)gui.getGUINode(OH_TA_DIALOG_NAME_TEXT_FIELD);
        TextField emailTextField = (TextField)gui.getGUINode(OH_TA_DIALOG_EMAIL_TEXT_FIELD);
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        Button addTAButton = (Button)gui.getGUINode(OH_TA_DIALOG_OK_BUTTON);
       
        boolean isLegalNewTA = data.isLegalNewTA(name, email);

        // ENABLE/DISABLE THE CONTROLS APPROPRIATELY
        addTAButton.setDisable(!isLegalNewTA);
        if (isLegalNewTA) {
            nameTextField.setOnAction(addTAButton.getOnAction());
            emailTextField.setOnAction(addTAButton.getOnAction());
        } else {
            nameTextField.setOnAction(null);
            emailTextField.setOnAction(null);
        }

        // UPDATE THE CONTROL TEXT DISPLAY APPROPRIATELY
        boolean isLegalNewName = data.isLegalNewName(name);
        boolean isLegalNewEmail = data.isLegalNewEmail(email);
        foolproofTextField(nameTextField, isLegalNewName);
        foolproofTextField(emailTextField, isLegalNewEmail);
    }
    
    public void foolproofTextField(TextField textField, boolean hasLegalData) {
        if (hasLegalData) {
            textField.getStyleClass().remove(CLASS_OH_TEXT_FIELD_ERROR);
            if (!textField.getStyleClass().contains(CLASS_OH_TEXT_FIELD)) {
                textField.getStyleClass().add(CLASS_OH_TEXT_FIELD);
            }
        } else {
            textField.getStyleClass().remove(CLASS_OH_TEXT_FIELD);
            if (!textField.getStyleClass().contains(CLASS_OH_TEXT_FIELD_ERROR)) {
                textField.getStyleClass().add(CLASS_OH_TEXT_FIELD_ERROR);
            }
        }
    }
    public void updateRemoveTAFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        Button removeTABt = ((Button) gui.getGUINode(CSG_OFFICE_HOURS_REMOVE_TA_BUTTON));
        if(data.isTASelected()) {
            removeTABt.setDisable(false);
        }
        else {
            removeTABt.setDisable(true);
        }
    }
    public void updateSelecteTimeRangeFoolProofDesign() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox startTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_START_TIME_COMBO_BOX);
        ComboBox endTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_END_TIME_COMBO_BOX);        
        String startTime = (String)startTimeBox.getValue();
        String endTime = (String)endTimeBox.getValue();
        int startTimeIndex = 0;
        int endTimeIndex = 0;
        
        startTimeBox.getItems().clear();
        endTimeBox.getItems().clear();
        for (int i = 0; i < startTimeComboBox.length; i++) {
            if (startTime.equals(startTimeComboBox[i])) 
                startTimeIndex = i;
        }
        for (int i = 0; i < endTimeComboBox.length; i++) {
            if (endTime.equals(endTimeComboBox[i]))
                 endTimeIndex = i;
        }
         
        for (int i = 0; i <= endTimeIndex; i++) {
                startTimeBox.getItems().add(startTimeComboBox[i]);
        }
        for (int i = startTimeIndex; i < endTimeComboBox.length; i++) {
                endTimeBox.getItems().add(endTimeComboBox[i]);
        }
        startTimeBox.setValue(startTime);
        endTimeBox.setValue(endTime);
    }
    public void updateRemoveScheduleItemFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        Button removeTABt = ((Button) gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_REMOVE_BUTTON));
        if(data.isScheduleItemSelected()) {
            removeTABt.setDisable(false);
        }
        else {
            removeTABt.setDisable(true);
        }
    }
}
