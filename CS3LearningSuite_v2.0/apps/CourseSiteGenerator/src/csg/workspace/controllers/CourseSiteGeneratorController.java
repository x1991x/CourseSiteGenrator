package csg.workspace.controllers;

import static csg.CourseSiteGeneratorPropertyType.OH_TA_EDIT_DIALOG;
import csg.data.CourseSiteGeneratorData;
import csg.data.TAType;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.transactions.AddTA_Transaction;
import csg.transactions.EditTA_Transaction;
import csg.transactions.ToggleOfficeHours_Transaction;
import csg.workspace.dialogs.TADialog;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import javafx.collections.ObservableList;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_LABS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_LECTURES_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_END_TIME_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_START_TIME_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_EXPORT_DIR_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NUMBER_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SEMESTER_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_FAVICON_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SUBJECT_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_YEAR_COMBO_BOX;
import csg.data.MeetingTimesPrototype;
import csg.data.MyImage;
import csg.transactions.AddLab_Transaction;
import csg.transactions.AddLecture_Transaction;
import csg.transactions.AddRecitation_Transaction;
import csg.transactions.EditADTextArea_Transaction;
import csg.transactions.EditDesTextArea_Transaction;
import csg.transactions.EditEmailTextField_Transaction;
import csg.transactions.EditGCTextArea_Transaction;
import csg.transactions.EditGNTextArea_Transaction;
import csg.transactions.EditHomePageTextField_Transaction;
import csg.transactions.EditLectureTableCell_Transaction;
import csg.transactions.EditNameTextField_Transaction;
import csg.transactions.EditOHTextArea_Transaction;
import csg.transactions.EditOutTextArea_Transaction;
import csg.transactions.EditPreTextArea_Transaction;
import csg.transactions.EditRoomTextField_Transaction;
import csg.transactions.EditSATextArea_Transaction;
import csg.transactions.EditTexTextArea_Transaction;
import csg.transactions.EditTitleTextField_Transaction;
import csg.transactions.EditTopTextArea_Transaction;
import csg.transactions.SelecteOHStartTime_Transaction;
import csg.transactions.RemoveLab_Transaction;
import csg.transactions.RemoveLecture_Transaction;
import csg.transactions.RemoveRecitation_Transaction;
import csg.transactions.RemoveTA_Transaction;
import csg.transactions.SelectHWs_Transaction;
import csg.transactions.SelectHome_Transaction;
import csg.transactions.SelectSchedule_Transaction;
import csg.transactions.SelectSyllabus_Transaction;
import csg.transactions.SetFavicon_Transaction;
import csg.transactions.SetLeftFooter_Transaction;
import csg.transactions.SetNavbar_Transaction;
import csg.transactions.SelectNumber_Transaction;
import csg.transactions.SetRightFooter_Transaction;
import csg.transactions.SelectSemester_Transaction;
import csg.transactions.SelectStyle_Transaction;
import csg.transactions.SelectSubject_Transaction;
import csg.transactions.SelectYear_Transaction;
import static djf.AppPropertyType.LOAD_IMAGE_TITLE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import static csg.CourseSiteGeneratorPropertyType.CSG_FOOLPROOF_SETTINGS;
import static csg.CourseSiteGeneratorPropertyType.CSG_INVALID_DATE_CONTENT;
import static csg.CourseSiteGeneratorPropertyType.CSG_INVALID_DATE_TITLE;
import static csg.CourseSiteGeneratorPropertyType.CSG_LOAD_NUMBER_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_LOAD_SEMESTER_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_LOAD_SUBJECT_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_LOAD_YEAR_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_PICKER;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ITEMS_ADD_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_ITEMS_UPDATE_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_LINK_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TITLE_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TOPIC_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_DEFAULT_OPTION;
import csg.data.ScheduleType;
import csg.data.SchedulesPrototype;
import csg.transactions.AddScheduleItem_Transaction;
import csg.transactions.EditLabTableCell_Transaction;
import csg.transactions.EditRecitationTableCell_Transaction;
import csg.transactions.EditScheduleItem_Transaction;
import csg.transactions.RemoveScheduleItem_Transaction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import properties_manager.PropertiesManager;
import static csg.CourseSiteGeneratorPropertyType.CSG_NO_TA_SELECTED_TITLE;
import static csg.CourseSiteGeneratorPropertyType.CSG_NO_TA_SELECTED_CONTENT;
import csg.transactions.ClearScheduleItem_Transaction;
import csg.transactions.SelectEndDate_Transaction;
import csg.transactions.SelectStartDate_Transaction;
import javafx.beans.value.ChangeListener;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER;
import csg.transactions.SelecteOHEndTime_Transaction;

/**
 *
 * @author Jie Dai
 */
public class CourseSiteGeneratorController {

    CourseSiteGeneratorApp app;
    private static final String JSON_SUBJECT = "subject";
    private static final String JSON_NUMBER = "number";
    private static final String JSON_SEMESTER = "semester";
    private static final String JSON_YEAR = "year";
    private static final String JSON_ITEM = "item";
    public CourseSiteGeneratorController(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }
    /*********************************************
    *
    *SITE
    *
    *********************************************/
    public void processSelectSubject(ComboBox subjectBox, String oldVal, String newVal, ChangeListener subjectListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!newVal.equals(oldVal)) {
            SelectSubject_Transaction transaction = new SelectSubject_Transaction(data, subjectBox, oldVal, newVal, subjectListener);
            app.processTransaction(transaction);
        }
    }
    
    public void processSelectNumber(ComboBox numberBox, String oldVal, String newVal, ChangeListener numberListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!newVal.equals(oldVal)) {
            SelectNumber_Transaction transaction = new SelectNumber_Transaction(data, numberBox, oldVal, newVal, numberListener);
            app.processTransaction(transaction);
        }
    }
    
    public void processSelectSemester(ComboBox semesterBox, String oldVal, String newVal, ChangeListener semesterListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!newVal.equals(oldVal)) {
            SelectSemester_Transaction transaction = new SelectSemester_Transaction(data, semesterBox, oldVal, newVal, semesterListener);
            app.processTransaction(transaction);
        }
    }
    
    public void processSelectYear(ComboBox yearBox, String oldVal, String newVal, ChangeListener yearListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!newVal.equals(oldVal)) {
            SelectYear_Transaction transaction = new SelectYear_Transaction(data, yearBox, oldVal, newVal, yearListener);
            app.processTransaction(transaction);
        }
    }
    
    public void processSelectStyle(ComboBox styleSheetBox, String oldVal, String newVal, ChangeListener styleSheetListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!newVal.equals(oldVal)) {
            SelectStyle_Transaction transaction = new SelectStyle_Transaction(data, styleSheetBox, oldVal, newVal, styleSheetListener);
            app.processTransaction(transaction);
        }
    }
    
    public boolean processAddItemIntoSubjectBox() throws FileNotFoundException {
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        String currentItem = (String)subjectBox.getValue();
        ObservableList subjectList  = subjectBox.getItems();
        if (currentItem != null && !currentItem.isEmpty() && !subjectList.contains(currentItem)) {
           subjectList.add(currentItem);
           Iterator subjectListIterator =  subjectList.iterator();
           JsonArrayBuilder subjectArrayBuilder = Json.createArrayBuilder();
           while(subjectListIterator.hasNext()) {
               JsonObject subjectJson = Json.createObjectBuilder()
                       .add(JSON_ITEM, (String)subjectListIterator.next())
                       .build();
               subjectArrayBuilder.add(subjectJson);
           }
           JsonArray subjectArray = subjectArrayBuilder.build();
           JsonObject subjectJSO = Json.createObjectBuilder()
                    .add(JSON_SUBJECT, subjectArray)
                    .build();
           
           Map<String, Object> properties = new HashMap<>(1);
           properties.put(JsonGenerator.PRETTY_PRINTING, true);
           JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
           StringWriter sw = new StringWriter();
           JsonWriter jsonWriter = writerFactory.createWriter(sw);
           jsonWriter.writeObject(subjectJSO);
           jsonWriter.close(); 
           
            String saveSubjectPath = props.getProperty(CSG_LOAD_SUBJECT_PATH);
           OutputStream os = new FileOutputStream(saveSubjectPath);
           JsonWriter jsonFileWriter = Json.createWriter(os);
           jsonFileWriter.writeObject(subjectJSO);
           String prettyPrinted = sw.toString();
           PrintWriter pw = new PrintWriter(saveSubjectPath);
           pw.write(prettyPrinted);
           pw.close();
           return true;
        }
        else 
            return false;
    }
    
    public boolean processAddItemIntoNumberBox() throws FileNotFoundException {
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox numberBox = (ComboBox)gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        String currentItem = (String)numberBox.getValue();
        ObservableList numberList  = numberBox.getItems();
        if (currentItem != null && !currentItem.isEmpty() && !numberList.contains(currentItem)) {
            numberList.add(currentItem);
           Iterator numberListIterator =  numberList.iterator();
           JsonArrayBuilder numberArrayBuilder = Json.createArrayBuilder();
           
           while(numberListIterator.hasNext()) {
               JsonObject numberJson = Json.createObjectBuilder()
                       .add(JSON_ITEM, (String)numberListIterator.next())
                       .build();
               numberArrayBuilder.add(numberJson);
           }
           
           JsonArray numberArray = numberArrayBuilder.build();
           JsonObject numberJSO = Json.createObjectBuilder()
                    .add(JSON_NUMBER, numberArray)
                    .build();
           
           Map<String, Object> properties = new HashMap<>(1);
           properties.put(JsonGenerator.PRETTY_PRINTING, true);
           JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
           StringWriter sw = new StringWriter();
           JsonWriter jsonWriter = writerFactory.createWriter(sw);
           jsonWriter.writeObject(numberJSO);
           jsonWriter.close(); 
           
           String saveNumberPath = props.getProperty(CSG_LOAD_NUMBER_PATH);
           OutputStream os = new FileOutputStream(saveNumberPath);
           JsonWriter jsonFileWriter = Json.createWriter(os);
           jsonFileWriter.writeObject(numberJSO);
           String prettyPrinted = sw.toString();
           PrintWriter pw = new PrintWriter(saveNumberPath);
           pw.write(prettyPrinted);
           pw.close();
           return true;
        }
        else
            return false;
    }
    
    public boolean processAddItemIntoSemesterBox() throws FileNotFoundException {
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox semesterrBox = (ComboBox)gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        String currentItem = (String)semesterrBox.getValue();
        ObservableList semesterList  = semesterrBox.getItems();
        if (currentItem != null && !currentItem.isEmpty() && !semesterList.contains(currentItem)) {
            semesterList.add(currentItem);
           Iterator semesterListIterator =  semesterList.iterator();
           JsonArrayBuilder semesterArrayBuilder = Json.createArrayBuilder();
           
           while(semesterListIterator.hasNext()) {
               JsonObject semesterJson = Json.createObjectBuilder()
                       .add(JSON_ITEM, (String)semesterListIterator.next())
                       .build();
               semesterArrayBuilder.add(semesterJson);
           }
           
           JsonArray semesterArray = semesterArrayBuilder.build();
           JsonObject semesterJSO = Json.createObjectBuilder()
                    .add(JSON_SEMESTER, semesterArray)
                    .build();
           
           Map<String, Object> properties = new HashMap<>(1);
           properties.put(JsonGenerator.PRETTY_PRINTING, true);
           JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
           StringWriter sw = new StringWriter();
           JsonWriter jsonWriter = writerFactory.createWriter(sw);
           jsonWriter.writeObject(semesterJSO);
           jsonWriter.close(); 
           
           String saveSemesterPath = props.getProperty(CSG_LOAD_SEMESTER_PATH);
           OutputStream os = new FileOutputStream(saveSemesterPath);
           JsonWriter jsonFileWriter = Json.createWriter(os);
           jsonFileWriter.writeObject(semesterJSO);
           String prettyPrinted = sw.toString();
           PrintWriter pw = new PrintWriter(saveSemesterPath);
           pw.write(prettyPrinted);
           pw.close();
           return true;
        }
        else
            return false;
    }
    
    public boolean processAddItemIntoYearBox() throws FileNotFoundException {
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        String currentItem = (String)yearBox.getValue();
        ObservableList yearList  = yearBox.getItems();
        if (currentItem != null && !currentItem.isEmpty() && !yearList.contains(currentItem)) {
            yearList.add(currentItem);
           Iterator yearListIterator =  yearList.iterator();
           JsonArrayBuilder yearArrayBuilder = Json.createArrayBuilder();
           
           while(yearListIterator.hasNext()) {
               JsonObject yearJson = Json.createObjectBuilder()
                       .add(JSON_ITEM, (String)yearListIterator.next())
                       .build();
               yearArrayBuilder.add(yearJson);
           }
           
           JsonArray yearArray = yearArrayBuilder.build();
           JsonObject yearJSO = Json.createObjectBuilder()
                    .add(JSON_YEAR, yearArray)
                    .build();
           
           Map<String, Object> properties = new HashMap<>(1);
           properties.put(JsonGenerator.PRETTY_PRINTING, true);
           JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
           StringWriter sw = new StringWriter();
           JsonWriter jsonWriter = writerFactory.createWriter(sw);
           jsonWriter.writeObject(yearJSO);
           jsonWriter.close(); 
           
           String saveYearPath = props.getProperty(CSG_LOAD_YEAR_PATH);
           OutputStream os = new FileOutputStream(saveYearPath);
           JsonWriter jsonFileWriter = Json.createWriter(os);
           jsonFileWriter.writeObject(yearJSO);
           String prettyPrinted = sw.toString();
           PrintWriter pw = new PrintWriter(saveYearPath);
           pw.write(prettyPrinted);
           pw.close();
           return true;
        }
        else
            return false;
    }
    //CHECK BOX
    public void processSetHomeCheckBox() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        SelectHome_Transaction transaction = new SelectHome_Transaction(data);
        app.processTransaction(transaction);          
    }
    
    public void processSetSyllabusCheckBox() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        SelectSyllabus_Transaction transaction = new SelectSyllabus_Transaction(data);
        app.processTransaction(transaction);
    }
    
    public void processSetScheduleCheckBox() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        SelectSchedule_Transaction transaction = new SelectSchedule_Transaction(data);
        app.processTransaction(transaction);
    }
    
    public void processSetHWsCheckBox() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        SelectHWs_Transaction transaction = new SelectHWs_Transaction(data);
        app.processTransaction(transaction);
    }
    
    //IMPORT IMAGE BUTTON
    public void processImportFavicon() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_FAVICON_IMAGE_VIEW);
        File file = AppDialogsFacade.showImageInputDialog(app.getGUIModule().getWindow(), LOAD_IMAGE_TITLE);
        if (file != null) {
            MyImage oldImage = (MyImage)imageView.getImage();      
            MyImage newImage = new MyImage(file.toURI().toString());
            SetFavicon_Transaction transaction = new SetFavicon_Transaction(data, oldImage, newImage);
            app.processTransaction(transaction);
        }
    }
    
    public void processImportNavbar() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW);
        File file = AppDialogsFacade.showImageInputDialog(app.getGUIModule().getWindow(), LOAD_IMAGE_TITLE);
        if (file != null) {
            MyImage oldImage = (MyImage)imageView.getImage();
            MyImage newImage = new MyImage(file.toURI().toString());
            SetNavbar_Transaction transaction = new SetNavbar_Transaction(data, oldImage, newImage);
            app.processTransaction(transaction);
        }
    }
    
    public void processImportLefterFooter() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW);
        File file = AppDialogsFacade.showImageInputDialog(app.getGUIModule().getWindow(), LOAD_IMAGE_TITLE);
        if (file != null) {
            MyImage oldImage = (MyImage)imageView.getImage();
            MyImage newImage = new MyImage(file.toURI().toString());
            SetLeftFooter_Transaction transaction = new SetLeftFooter_Transaction(data, oldImage, newImage);
            app.processTransaction(transaction);
        }
    }
    
    public void processImportRightFooter() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW);
        File file = AppDialogsFacade.showImageInputDialog(app.getGUIModule().getWindow(), LOAD_IMAGE_TITLE);
        if (file != null) {
            MyImage oldImage = (MyImage)imageView.getImage();
            MyImage newImage = new MyImage(file.toURI().toString());
            SetRightFooter_Transaction transaction = new SetRightFooter_Transaction(data, oldImage, newImage);
            app.processTransaction(transaction);
        }
    }
    
    //TEXT FIELD
    public void processEditSiteTitle(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
             EditTitleTextField_Transaction  transaction = new EditTitleTextField_Transaction(data, oldVal, newVal);
             app.processTransaction(transaction);
        }
    }
    
    public void processEditInstructorName(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditNameTextField_Transaction transaction = new EditNameTextField_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditInstructorRoom(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditRoomTextField_Transaction transaction = new EditRoomTextField_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditInstructorEmail(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditEmailTextField_Transaction transaction = new EditEmailTextField_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditInstructorHPage(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditHomePageTextField_Transaction transaction = new EditHomePageTextField_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditInstructorOH(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditOHTextArea_Transaction transaction = new EditOHTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    //LABEL
    public void processUpdateExportDir() {
        AppGUIModule gui = app.getGUIModule();
        Label exportDir = (Label)gui.getGUINode(CSG_SITE_EXPORT_DIR_PATH);
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        ComboBox numberBox = (ComboBox)gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        String subject = (String)subjectBox.getValue();
        String number = (String)numberBox.getValue();
        String semester = (String)semesterBox.getValue();
        String year = (String)yearBox.getValue();
        exportDir.setText(".\\export\\" + subject + "_" + number + "_" + semester + "_" + year + "\\public_html");
    }
    /*********************************************
    *
    *SYLLABUS
    *
    *********************************************/
    public void processEditDescription(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditDesTextArea_Transaction transaction = new EditDesTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditTopics(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditTopTextArea_Transaction transaction = new EditTopTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
     
    public void processEditPrerequisites(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditPreTextArea_Transaction transaction = new EditPreTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
      
    public void processEditOutcomes(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
               EditOutTextArea_Transaction transaction = new EditOutTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditTextbooks(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditTexTextArea_Transaction transaction = new EditTexTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
     
    public void processEditGradedComponents(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditGCTextArea_Transaction transaction = new EditGCTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
      
    public void processEditGradingNote(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditGNTextArea_Transaction transaction = new EditGNTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
      
    public void processEditAcademicDishonesty(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditADTextArea_Transaction transaction = new EditADTextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
      
    public void processEditSpecialAssistance(String oldVal, String newVal) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        if (!oldVal.equals(newVal)) {
            EditSATextArea_Transaction transaction = new EditSATextArea_Transaction(data, oldVal, newVal);
            app.processTransaction(transaction);
        }
    }
    /*********************************************
    *
    *MEETING TIMES
    *
    *********************************************/
    public void processAddLecture() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        MeetingTimesPrototype newLecture = new MeetingTimesPrototype("?", "?", "?", "?");
        AddLecture_Transaction transaction = new AddLecture_Transaction(data, newLecture);
        app.processTransaction(transaction);
    }    
    
    public void processAddRecitation() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        MeetingTimesPrototype newRecitation = new MeetingTimesPrototype("?", "?", "?", "?", "?");
        AddRecitation_Transaction transaction = new AddRecitation_Transaction(data, newRecitation);
        app.processTransaction(transaction);
    }
    
    public void processAddLab() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        MeetingTimesPrototype newLab = new MeetingTimesPrototype("?", "?", "?", "?","?");
        AddLab_Transaction transaction = new AddLab_Transaction(data, newLab);
        app.processTransaction(transaction);
    }
    
    public void processRemoveLecture() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TableView lectureTable = (TableView)gui.getGUINode(CSG_MEETING_TIMES_LECTURES_TABLE_VIEW);
        MeetingTimesPrototype selectedItem = (MeetingTimesPrototype)lectureTable.getSelectionModel().getSelectedItem();
        RemoveLecture_Transaction transaction = new RemoveLecture_Transaction(data, selectedItem);
        app.processTransaction(transaction);
    }
    
    public void processRemoveRecitation() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TableView recitationTable = (TableView)gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW);
        MeetingTimesPrototype selectedItem = (MeetingTimesPrototype)recitationTable.getSelectionModel().getSelectedItem();
        RemoveRecitation_Transaction transaction = new RemoveRecitation_Transaction(data, selectedItem);
        app.processTransaction(transaction);
    }
    
    public void processRemoveLab() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TableView labTable = (TableView)gui.getGUINode(CSG_MEETING_TIMES_LABS_TABLE_VIEW);
        MeetingTimesPrototype selectedItem = (MeetingTimesPrototype)labTable.getSelectionModel().getSelectedItem();
        RemoveLab_Transaction transaction = new RemoveLab_Transaction(data, selectedItem);
        app.processTransaction(transaction);
    }
    
    public void processEditLecturesTableView(MeetingTimesPrototype item, String oldV, String newV, int columnIndex) {
        AppGUIModule gui = app.getGUIModule();
        TableView table = (TableView)gui.getGUINode(CSG_MEETING_TIMES_LECTURES_TABLE_VIEW);
        EditLectureTableCell_Transaction transaction = 
                new EditLectureTableCell_Transaction(item, oldV, newV, columnIndex, table);
        app.processTransaction(transaction);
    }
    
    public void processEditRecitationsTableView(MeetingTimesPrototype item, String oldV, String newV, int columnIndex) {
        AppGUIModule gui = app.getGUIModule();
        TableView table = (TableView)gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW);
        EditRecitationTableCell_Transaction transaction = 
                new EditRecitationTableCell_Transaction(item, oldV, newV, columnIndex, table);
        app.processTransaction(transaction);
    }
    
    public void processEditLabsTableView(MeetingTimesPrototype item, String oldV, String newV, int columnIndex) {
        AppGUIModule gui = app.getGUIModule();
        TableView table = (TableView)gui.getGUINode(CSG_MEETING_TIMES_LABS_TABLE_VIEW);
        EditLabTableCell_Transaction transaction = 
                new EditLabTableCell_Transaction(item, oldV, newV, columnIndex, table);
        app.processTransaction(transaction);
    }
    /*********************************************
    *
    *OFFICE HOURS
    *
    *********************************************/
    public void processAddTA() {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTF = (TextField) gui.getGUINode(CSG_OFFICE_HOURS_NAME_TEXT_FIELD);
        String name = nameTF.getText();
        TextField emailTF = (TextField) gui.getGUINode(CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD);
        String email = emailTF.getText();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        TAType type = data.getSelectedType();
        if (data.isLegalNewTA(name, email)) {
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name.trim(), email.trim(), type);
            AddTA_Transaction addTATransaction = new AddTA_Transaction(data, ta);
            app.processTransaction(addTATransaction);

            // NOW CLEAR THE TEXT FIELDS
            nameTF.setText("");
            emailTF.setText("");
            nameTF.requestFocus();
        }
        app.getFoolproofModule().updateControls(CSG_FOOLPROOF_SETTINGS);
    }
    
    public void processRemoveTA() {
        AppGUIModule gui = app.getGUIModule();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        TeachingAssistantPrototype selectedTa = taTableView.getSelectionModel().getSelectedItem();
        if (selectedTa != null) {
            ArrayList timeSlotList = new ArrayList<>();
            for (TimeSlot timeSlot : data.getAllTimeSlots()) {
             for (TimeSlot.DayOfWeek dow : TimeSlot.DayOfWeek.values()) {
                if (timeSlot.getTasHashMap().get(dow).contains(selectedTa)) {
                    TimeSlot newTimeSlot = new TimeSlot(timeSlot.getStartTime(), timeSlot.getEndTime());
                     newTimeSlot.getTasHashMap()
                        .get(dow)
                        .add(selectedTa);
                     timeSlotList.add(newTimeSlot);
                }
             }
         }
            RemoveTA_Transaction removeTATransaction = new RemoveTA_Transaction(data, selectedTa, timeSlotList);
            app.processTransaction(removeTATransaction);
        }
            taTableView.refresh();
            officeHoursTableView.refresh();
    }

    public void processToggleOfficeHours() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        ObservableList<TablePosition> selectedCells = officeHoursTableView.getSelectionModel().getSelectedCells();
        if (selectedCells.size() > 0) {
            TablePosition cell = selectedCells.get(0);
            int cellColumnNumber = cell.getColumn();
            CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
            if (data.isDayOfWeekColumn(cellColumnNumber)) {
                DayOfWeek dow = data.getColumnDayOfWeek(cellColumnNumber);
                TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
                TeachingAssistantPrototype ta = taTableView.getSelectionModel().getSelectedItem();
                if (ta != null) {
                    TimeSlot timeSlot = officeHoursTableView.getSelectionModel().getSelectedItem();
                    ToggleOfficeHours_Transaction transaction = new ToggleOfficeHours_Transaction(data, timeSlot, dow, ta);
                    app.processTransaction(transaction);
                }
                else {
                    Stage window = app.getGUIModule().getWindow();
                    AppDialogsFacade.showMessageDialog(window, CSG_NO_TA_SELECTED_TITLE, CSG_NO_TA_SELECTED_CONTENT);
                }
            }
            cell.getTableView().refresh();
        }
    }

    public void processFoolproofDesign() {
        app.getFoolproofModule().updateControls(CSG_FOOLPROOF_SETTINGS);
    }

    public void processEditTA() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView) gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
        if (data.isTASelected()) {
            TeachingAssistantPrototype taToEdit = data.getSelectedTA();
            TADialog taDialog = (TADialog)app.getGUIModule().getDialog(OH_TA_EDIT_DIALOG);
            taDialog.showEditDialog(taToEdit);
            TeachingAssistantPrototype editTA = taDialog.getEditTA();
            if (editTA != null) {
                EditTA_Transaction transaction = new EditTA_Transaction(taToEdit, data, editTA.getName(), editTA.getEmail(), editTA.getType());
                app.processTransaction(transaction);
            }
        }
        officeHoursTableView.refresh();
        taTableView.refresh();
    }
    //RADIO BUTTON
    public void processSelectAllTAs() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.selectTAs(TAType.All);
    }

    public void processSelectGradTAs() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.selectTAs(TAType.Graduate);
    }

    public void processSelectUndergradTAs() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        data.selectTAs(TAType.Undergraduate);
    }

    public void processSelectTA() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        officeHoursTableView.refresh();
    }
    
    public void processOHSelectStartTime(String oldStartTime, String newStartTime, String endTime, ChangeListener startTimeListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox startTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_START_TIME_COMBO_BOX);
        SelecteOHStartTime_Transaction transaction = new SelecteOHStartTime_Transaction(data, startTimeBox, 
                        oldStartTime, newStartTime, endTime, startTimeListener);
        app.processTransaction(transaction);
    }
    
    public void processOHSelectEndTime(String oldEndTime, String newEndTime, String startTime, ChangeListener endTimeListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData)app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        ComboBox endTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_END_TIME_COMBO_BOX);
        SelecteOHEndTime_Transaction transaction = new SelecteOHEndTime_Transaction(data, endTimeBox, 
                        oldEndTime, newEndTime, startTime, endTimeListener);
        app.processTransaction(transaction);
    }
    /*********************************************
    *
    *SCHEDULE
    *
    *********************************************/
    public void processUnselectScheduleItem() {
        AppGUIModule gui = app.getGUIModule();
        TableView scheduleTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
        scheduleTable.getSelectionModel().select(null);
    }
    public void processStartDateInvalidTimeRangeAlert() {
         AppGUIModule gui = app.getGUIModule();
         DatePicker startingMonday = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER);
         DatePicker endingFriday = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER);
         LocalDate startingDate = startingMonday.getValue();
         LocalDate endingDate = endingFriday.getValue();
         if (startingDate != null && endingDate != null && (startingDate.isAfter(endingDate) || startingDate.isEqual(endingDate))) {
             Stage window = app.getGUIModule().getWindow();
             AppDialogsFacade.showMessageDialog(window, CSG_INVALID_DATE_TITLE, CSG_INVALID_DATE_CONTENT);
             startingMonday.setValue(null);
         }
    }
    
    public void processEndDateInvalidTimeRangeAlert() {
         AppGUIModule gui = app.getGUIModule();
         DatePicker startingMonday = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER);
         DatePicker endingFriday = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER);
         LocalDate startingDate = startingMonday.getValue();
         LocalDate endingDate = endingFriday.getValue();
         if (startingDate != null && endingDate != null && (startingDate.isAfter(endingDate) || startingDate.isEqual(endingDate))) {
             CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
             Stage window = app.getGUIModule().getWindow();
             AppDialogsFacade.showMessageDialog(window, CSG_INVALID_DATE_TITLE, CSG_INVALID_DATE_CONTENT);
             endingFriday.setValue(null);
         }
    }
    
    public void processSelectStartDate(DatePicker startDate, LocalDate startDateOldVal, 
                    LocalDate startDateNewVal, LocalDate endDateVal, ChangeListener startDateListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        SelectStartDate_Transaction transaction 
                = new SelectStartDate_Transaction(data, startDate, startDateOldVal, startDateNewVal, endDateVal, startDateListener);
        app.processTransaction(transaction);
    }
    
    public void processSelectEndDate(DatePicker endDate,LocalDate endDateOldVal, 
                    LocalDate endDateNewVal, LocalDate startDateVal, ChangeListener endDateListener) {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        SelectEndDate_Transaction transaction 
                = new SelectEndDate_Transaction(data, endDate, endDateOldVal, endDateNewVal, startDateVal, endDateListener);
        app.processTransaction(transaction);
    }
    
    //ADD SCHEDULE ITEM
    public void processAddScheduleItem() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ComboBox typeBox = (ComboBox)gui.getGUINode(CSG_SCHEDULE_TYPE_COMBO_BOX);
        DatePicker datePicker = (DatePicker)gui.getGUINode(CSG_SCHEDULE_DATE_PICKER);
        TextField titleTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_TITLE_TEXT_FIELD);
        TextField topicTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_TOPIC_TEXT_FIELD);
        TextField linkTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_LINK_TEXT_FIELD);
        Button addButton = (Button)gui.getGUINode(CSG_SCHEDULE_ITEMS_ADD_BUTTON);
        String addButtonText = addButton.getText();
        String type = (String)typeBox.getValue();
        String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String title = titleTextField.getText();
        String topic = topicTextField.getText();
        String link = linkTextField.getText();
        String addText = props.getProperty(CSG_SCHEDULE_ITEMS_ADD_BUTTON + "_TEXT");
        String updateText = props.getProperty(CSG_SCHEDULE_ITEMS_UPDATE_BUTTON + "_TEXT");
        String defaultItem = props.getProperty(CSG_SCHEDULE_TYPE_DEFAULT_OPTION);
        if (addButtonText.equals(addText) && !type.equals(defaultItem)) { // ADD ITEM
            SchedulesPrototype item = new SchedulesPrototype(ScheduleType.valueOf(type), date, title, topic, link);
            AddScheduleItem_Transaction transaction = new AddScheduleItem_Transaction(data, item);
            app.processTransaction(transaction);
            processClearScheduleTextFields();
        }
        else if (addButtonText.equals(updateText) && !type.equals(defaultItem)){// UPDATE ITEM
            TableView schedulesTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
            SchedulesPrototype selectedItem = (SchedulesPrototype)schedulesTable.getSelectionModel().getSelectedItem();
            EditScheduleItem_Transaction transaction = new EditScheduleItem_Transaction(schedulesTable, selectedItem, type, date, title, topic, link);
            app.processTransaction(transaction);
            processClearScheduleTextFields();
        }
    }
    
    public void processLoadSelectedItem() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox typeBox = (ComboBox)gui.getGUINode(CSG_SCHEDULE_TYPE_COMBO_BOX);
        DatePicker datePicker = (DatePicker)gui.getGUINode(CSG_SCHEDULE_DATE_PICKER);
        TextField titleTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_TITLE_TEXT_FIELD);
        TextField topicTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_TOPIC_TEXT_FIELD);
        TextField linkTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_LINK_TEXT_FIELD);
        TableView schedulesTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
        SchedulesPrototype selectedItem = (SchedulesPrototype)schedulesTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            typeBox.setValue(selectedItem.getType());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            datePicker.setValue(LocalDate.parse(selectedItem.getDate(), formatter));
            titleTextField.setText(selectedItem.getTitle());
            topicTextField.setText(selectedItem.getTopic());
            linkTextField.setText(selectedItem.getLink());
        }
    }    
    
    public void processChangeAddButtonText() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        AppGUIModule gui = app.getGUIModule();
        String addText = props.getProperty(CSG_SCHEDULE_ITEMS_ADD_BUTTON + "_TEXT");
        String updateText = props.getProperty(CSG_SCHEDULE_ITEMS_UPDATE_BUTTON + "_TEXT");
        Button addButton = (Button)gui.getGUINode(CSG_SCHEDULE_ITEMS_ADD_BUTTON);
        if (data.isScheduleItemSelected()) 
            addButton.setText(updateText);
        else
            addButton.setText(addText);
    }
    
    public void processRemoveScheduleItem() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        if (data.isScheduleItemSelected()) {
            TableView schedulesTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
            SchedulesPrototype selectedItem = (SchedulesPrototype)schedulesTable.getSelectionModel().getSelectedItem();
            RemoveScheduleItem_Transaction transaction = new RemoveScheduleItem_Transaction(data, selectedItem);
            app.processTransaction(transaction);
            processClearScheduleTextFields();
        }
    }
    
    public void processClearScheduleTextFields() {
        AppGUIModule gui = app.getGUIModule();
        TableView schedulesTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
        TextField titleTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_TITLE_TEXT_FIELD);
        TextField topicTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_TOPIC_TEXT_FIELD);
        TextField linkTextField = (TextField)gui.getGUINode(CSG_SCHEDULE_LINK_TEXT_FIELD);
        titleTextField.clear();
        topicTextField.clear();
        linkTextField.clear();
        schedulesTable.getSelectionModel().select(null);
    }
      
    public void processClearEditItem() {
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TableView schedulesTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
        ComboBox typeBox = (ComboBox)gui.getGUINode(CSG_SCHEDULE_TYPE_COMBO_BOX);
        DatePicker datePicker = (DatePicker)gui.getGUINode(CSG_SCHEDULE_DATE_PICKER);
        TextField titleTF = (TextField)gui.getGUINode(CSG_SCHEDULE_TITLE_TEXT_FIELD);
        TextField topicTF = (TextField)gui.getGUINode(CSG_SCHEDULE_TOPIC_TEXT_FIELD);
        TextField linkTF = (TextField)gui.getGUINode(CSG_SCHEDULE_LINK_TEXT_FIELD);
        String typeDefaultOption = props.getProperty(CSG_SCHEDULE_TYPE_DEFAULT_OPTION);
        String typeItem = (String)typeBox.getValue();
        LocalDate localDate = datePicker.getValue();
        String titleText = titleTF.getText();
        String topicText = topicTF.getText();
        String linkText = linkTF.getText();
        if (!(typeItem.equals(typeDefaultOption) && localDate.isEqual(LocalDate.now()) && titleText.equals("") 
                    && topicText.equals("") && linkText.equals(""))) {
            SchedulesPrototype selectedItem = (SchedulesPrototype)schedulesTable.getSelectionModel().getSelectedItem();
            SchedulesPrototype itemBeforeClear = new SchedulesPrototype();
            itemBeforeClear.setType(typeItem);
            itemBeforeClear.setDate(localDate);
            itemBeforeClear.setTitle(titleText);
            itemBeforeClear.setTopic(topicText);
            itemBeforeClear.setLink(linkText);
            ClearScheduleItem_Transaction transaction = new ClearScheduleItem_Transaction(data, selectedItem, 
                        itemBeforeClear, typeDefaultOption, typeBox, datePicker, titleTF, topicTF, linkTF, schedulesTable);
            app.processTransaction(transaction);
        }
    }
}