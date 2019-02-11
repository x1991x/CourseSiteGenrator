package csg.data;

import javafx.collections.ObservableList;
import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_LABS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_LECTURES_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_ALL_RADIO_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_GRADUATE_RADIO_BUTTON;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_START_TIME_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_TAS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_EMAIL_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_HOME_PAGE_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NAME_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NUMBER_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_NUMBER_DEFAULT_OPTION;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_OFFICE_HOURS_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PAGES_HOME_CHECK_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PAGES_HWS_CHECK_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PAGES_SCHEDULE_CHECK_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_PAGES_SYLLABUS_CHECK_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_ROOM_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SEMESTER_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SEMESTER_DEFAULT_OPTION;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_FAVICON_DEFAULTE_IMAGE_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_FAVICON_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_IMAGE_HREF;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_LEFTE_FOOTER_DEFAULTE_IMAGE_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_NAVBAR_DEFAULTE_IMAGE_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_RIGHT_FOOTER_DEFAULTE_IMAGE_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_SHEET_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_STYLE_SHEET_DEFAULT_OPTION;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_SUBJECT_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_TITLE_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_YEAR_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_DESCRIPTION_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_OUTCOMES_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_PREREQUISITES_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA;
import static csg.CourseSiteGeneratorPropertyType.CSG_SYLLABUS_TOPICS_TEXT_AREA;
import csg.data.TimeSlot.DayOfWeek;
import static djf.AppPropertyType.APP_PATH_IMAGES;
import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import properties_manager.PropertiesManager;
import static csg.CourseSiteGeneratorPropertyType.CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW;
import static csg.CourseSiteGeneratorPropertyType.CSG_OFFICE_HOURS_END_TIME_COMBO_BOX;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_DATE_PICKER;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_LINK_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TITLE_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TOPIC_TEXT_FIELD;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_TYPE_COMBO_BOX;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import static csg.CourseSiteGeneratorPropertyType.CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER;
import csg.workspace.CourseSiteGeneratorWorkspace;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * This is the data component for TAManagerApp. It has all the data needed
 * to be set by the user via the User Interface and file I/O can set and get
 * all the data from this object
 * 
 * @author Jie Dai
 */
public class CourseSiteGeneratorData implements AppDataComponent {

    // WE'LL NEED ACCESS TO THE APP TO NOTIFY THE GUI WHEN DATA CHANGES
    CourseSiteGeneratorApp app;
    //SITE DATA
    Banner banner;
    HashMap<String, Log> logList;
    ArrayList<Page> pageList;
    String styleSheet;
    Instructor instructor;
    //SYLLABUS DATA
    HashMap<String, String> syllabusList;
    
    //MEETING TIMES DATA
    ObservableList<MeetingTimesPrototype> lecturesList;
    ObservableList<MeetingTimesPrototype> recitationsList;
    ObservableList<MeetingTimesPrototype> labsList;
    
    //OFFICE HOURS DATA
    HashMap<TAType, ArrayList<TeachingAssistantPrototype>> allTAs;
    private ArrayList<TimeSlot> allTimeSlots;
    ObservableList<TeachingAssistantPrototype> teachingAssistants;
    ObservableList<TimeSlot> officeHours;    
    int startHour;
    int endHour;
    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 20;
    
    //SCHEDULE DATA
    int startingMondayMonth;
    int startingMondayDay;
    int startingMondayYear;
    int endingFridayMonth;
    int endingFridayDay;
    int endingFridayYear;
    HashMap<ScheduleType, ArrayList<SchedulesPrototype>> allSchedules;
    ObservableList<SchedulesPrototype> schedulesList;
        
    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    
    /**
     * This constructor will setup the required data structures for
     * use, but will have to wait on the office hours grid, since
     * it receives the StringProperty objects from the Workspace.
     * 
     * @param initApp The application this data manager belongs to. 
     */
    public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        // SETUP THE DATA STRUCTURES
        //INITIAL SITE PAGE DATA
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        ComboBox numberBox = (ComboBox)gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        banner = new Banner();
        banner.setSubject((String)subjectBox.getValue());
        banner.setNumber((String)numberBox.getValue());
        banner.setSemester((String)semesterBox.getValue());
        banner.setYear((String)yearBox.getValue());

        pageList = new ArrayList();
        for (int i = 0; i < 4; i++) 
            pageList.add(null);
        logList = new HashMap();
        Log faviconLog = new Log();
        Log navbarLog = new Log();
        Log leftFooterLog = new Log();
        Log rightFooterLog = new Log();
        String href = props.getProperty(CSG_SITE_STYLE_IMAGE_HREF);
        faviconLog.setHref(props.getProperty(CSG_SITE_STYLE_FAVICON_DEFAULTE_IMAGE_PATH));
        navbarLog.setSrc(props.getProperty(CSG_SITE_STYLE_NAVBAR_DEFAULTE_IMAGE_PATH));
        leftFooterLog.setSrc(props.getProperty(CSG_SITE_STYLE_LEFTE_FOOTER_DEFAULTE_IMAGE_PATH));
        rightFooterLog.setSrc(props.getProperty(CSG_SITE_STYLE_RIGHT_FOOTER_DEFAULTE_IMAGE_PATH));
        navbarLog.setHref(href);
        leftFooterLog.setHref(href);
        rightFooterLog.setHref(href);
        logList.put("favicon", faviconLog);
        logList.put("navbar", navbarLog);
        logList.put("left_footer", leftFooterLog);
        logList.put("right_footer", rightFooterLog);
        styleSheet = props.getProperty(CSG_SITE_STYLE_SHEET_DEFAULT_OPTION);
        instructor = new Instructor();
        //INITIAL SYLLABUS PAGE DATA
        syllabusList = new HashMap();
        syllabusList.put("description", "");
        syllabusList.put("topics", "");
        syllabusList.put("prerequisites", "");
        syllabusList.put("outcomes", "");
        syllabusList.put("textbooks", "");
        syllabusList.put("gradedComponents", "");
        syllabusList.put("gradingNote", "");
        syllabusList.put("academicDishonesty", "");
        syllabusList.put("specialAssistance", "");
        //INITIAL MEETING TIME PAGE DATA
        lecturesList = ((TableView)gui.getGUINode(CSG_MEETING_TIMES_LECTURES_TABLE_VIEW)).getItems();
        recitationsList = ((TableView)gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW)).getItems();
        labsList = ((TableView)gui.getGUINode(CSG_MEETING_TIMES_LABS_TABLE_VIEW)).getItems();
        //INITIAL OFFICE HOURS PAGE DATA
        allTAs = new HashMap();
        allTAs.put(TAType.Graduate, new ArrayList());
        allTAs.put(TAType.Undergraduate, new ArrayList());
        allTimeSlots = new ArrayList<>();
        TableView<TeachingAssistantPrototype> taTableView = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
        teachingAssistants = taTableView.getItems();
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        //INITIAL SCHEDULE PAGE DATA
        allSchedules = new HashMap();
        allSchedules.put(ScheduleType.Holiday, new ArrayList());
        allSchedules.put(ScheduleType.Lecture, new ArrayList());
        allSchedules.put(ScheduleType.Recitation, new ArrayList());
        allSchedules.put(ScheduleType.Reference, new ArrayList());
        allSchedules.put(ScheduleType.HW, new ArrayList());
        schedulesList = ((TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW)).getItems();
        resetOfficeHours();
    }
    
    // ACCESSOR METHODS
    public Banner getBanner() {
        return this.banner;
    }
    
    public HashMap<String, Log>  getLogList() {
        return this.logList;
    }
    
    public String getStyleSheet() {
        return this.styleSheet;
    }
    public ArrayList<Page> getPageList() {
        return this.pageList;
    }
    
    public Instructor getInstructor() {
        return this.instructor;
    }
    
    public HashMap<String, String> getSyllabusList() {
        return this.syllabusList;
    }
    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartingMondayMonth() {
        return startingMondayMonth;
    }

    public int getStartingMondayDay() {
        return startingMondayDay;
    }

    public int getStartingMondayYear() {
        return startingMondayYear;
    }
    
    public int getEndingFridayMonth() {
        return endingFridayMonth;
    }

    public int getEndingFridayDay() {
        return endingFridayDay;
    }
    
    public int getEndingFridayYear() {
        return endingFridayYear;
    }
    //MUTATOR
    //SET COMBO BOX TEXT
    public void setSubjectBox(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        subjectBox.setValue(newVal);
        this.banner.setSubject(newVal);
    }
   
    public void setNumberBox(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox numberBox = (ComboBox)gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        numberBox.setValue(newVal);
        this.banner.setNumber(newVal);
    }
    
    public void setSemesterBox(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        semesterBox.setValue(newVal);
        this.banner.setSemester(newVal);
    }

    public void setYearBox(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        yearBox.setValue(newVal);
        this.banner.setYear(newVal);
    }
 
    public void setStyleSheetBox(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox styleSheetBox = (ComboBox)gui.getGUINode(CSG_SITE_STYLE_SHEET_COMBO_BOX);
        styleSheetBox.setValue(newVal);
        this.styleSheet = newVal;
    }
    
    public void setStartTimeBox(String startTime) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox startTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_START_TIME_COMBO_BOX);
        startTimeBox.setValue(startTime);
        int colonIndex = startTime.indexOf(":");
        this.startHour = Integer.valueOf(startTime.substring(0, colonIndex));
    }
    
    public void setEndTimeBox(String endTime) {
        AppGUIModule gui = app.getGUIModule();
        ComboBox endTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_END_TIME_COMBO_BOX);
        endTimeBox.setValue(endTime);
        int colonIndex = endTime.indexOf(":");
        this.endHour = Integer.valueOf(endTime.substring(0, colonIndex));
    }
        
    //SET DATE PICKER
    public void setStartDatePickerBox(LocalDate date) {
         AppGUIModule gui = app.getGUIModule();
         DatePicker startDate = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER);
         startDate.setValue(date);
         if (date != null) {
            this.startingMondayMonth = date.getMonthValue();
            this.startingMondayDay = date.getDayOfMonth();
            this.startingMondayYear = date.getYear();
         }
    }
    
    public void setEndDatePickerBox(LocalDate date) {
         AppGUIModule gui = app.getGUIModule();
         DatePicker endDate = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER);
         endDate.setValue(date);
         if (date != null) {
            this.endingFridayMonth = date.getMonthValue();
            this.endingFridayDay = date.getDayOfMonth();
            this.endingFridayYear = date.getYear();
         }
    }
    
    //SET CHECK BOXES
    public void selectHomeCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox homeCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HOME_CHECK_BOX);
        if (homeCheckBox.isFocused()) {
            if (homeCheckBox.isSelected()) {
                this.pageList.set(0, new Page("Home", "index.html"));
                homeCheckBox.setSelected(true);
            }
            else {
               this.pageList.set(0, null);
               homeCheckBox.setSelected(false);
            }
        }
        else {
            if (homeCheckBox.isSelected()) {
               this.pageList.set(0, null);
               homeCheckBox.setSelected(false);
            }
            else {
               this.pageList.set(0, new Page("Home", "index.html"));
                homeCheckBox.setSelected(true);
            }
        }
    }
    
    
    public void selectSyllabusCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox syllabusCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SYLLABUS_CHECK_BOX);
        if (syllabusCheckBox.isFocused()) {
            if (syllabusCheckBox.isSelected()) {
                this.pageList.set(1, new Page("Syllabus", "syllabus.html"));
                syllabusCheckBox.setSelected(true);
            }
            else {
               this.pageList.set(1, null);
               syllabusCheckBox.setSelected(false);
            }
        }
        else {
            if (syllabusCheckBox.isSelected()) {
               this.pageList.set(1, null);
               syllabusCheckBox.setSelected(false);
            }
            else {
               this.pageList.set(1, new Page("Syllabus", "syllabus.html"));
               syllabusCheckBox.setSelected(true);
            }
        }
    }
    
    public void selectScheduleCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox scheduleCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SCHEDULE_CHECK_BOX);
        if (scheduleCheckBox.isFocused()) {
            if (scheduleCheckBox.isSelected()) {
                this.pageList.set(2, new Page("Schedule", "schedule.html"));
                scheduleCheckBox.setSelected(true);
            }
            else {
               this.pageList.set(2, null);
               scheduleCheckBox.setSelected(false);
            }
        }
        else {
            if (scheduleCheckBox.isSelected()) {
               this.pageList.set(2, null);
               scheduleCheckBox.setSelected(false);
            }
            else {
               this.pageList.set(2, new Page("Schedule", "schedule.html"));
               scheduleCheckBox.setSelected(true);
            }
        }
    }
    
    public void selectHWsCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox hwsCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HWS_CHECK_BOX);
        if (hwsCheckBox.isFocused()) {
            if (hwsCheckBox.isSelected()) {
                this.pageList.set(3, new Page("HWs", "hws.html"));
                hwsCheckBox.setSelected(true);
            }
            else {
               this.pageList.set(3, null);
               hwsCheckBox.setSelected(false);
            }
        }
        else {
            if (hwsCheckBox.isSelected()) {
               this.pageList.set(3, null);
               hwsCheckBox.setSelected(false);
            }
            else {
               this.pageList.set(3, new Page("HWs", "hws.html"));
               hwsCheckBox.setSelected(true);
            }
        }
    }
    public void setHomeCheckBox(boolean isCheck) {
        AppGUIModule gui = app.getGUIModule();
        CheckBox homeCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HOME_CHECK_BOX);
        homeCheckBox.setSelected(isCheck);
        if (isCheck) 
            this.pageList.set(0, new Page("Home", "index.html"));
        else
            this.pageList.set(0, null);
    }
    
    public void setSyllabusCheckBox(boolean isCheck) {
        AppGUIModule gui = app.getGUIModule();
        CheckBox syllabusCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SYLLABUS_CHECK_BOX);
        syllabusCheckBox.setSelected(isCheck);
        if (isCheck) 
            this.pageList.set(1, new Page("Syllabus", "syllabus.html"));
        else
            this.pageList.set(1, null);
        
    }
    
    public void setScheduleCheckBox(boolean isCheck) {
        AppGUIModule gui = app.getGUIModule();
        CheckBox scheduleCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SCHEDULE_CHECK_BOX);
        scheduleCheckBox.setSelected(isCheck);
        if (isCheck)
            this.pageList.set(2, new Page("Schedule", "schedule.html"));
        else 
            this.pageList.set(2, null);
    }
    
    public void setHwsCheckBox(boolean isCheck) {
        AppGUIModule gui = app.getGUIModule();
        CheckBox hwsCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HWS_CHECK_BOX);
        hwsCheckBox.setSelected(isCheck);
        if (isCheck)
            this.pageList.set(3, new Page("HWs", "hws.html"));
        else
            this.pageList.set(3, null);
    }
    
    public void undoSelectHomeCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox homeCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HOME_CHECK_BOX);
        if (homeCheckBox.isSelected()) {
           this.pageList.set(0, null);
           homeCheckBox.setSelected(false);
        }
        else {
           this.pageList.set(0, new Page("Home", "index.html"));
           homeCheckBox.setSelected(true);
        }
    }
    
    public void undoSelectSyllabusBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox syllabusCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SYLLABUS_CHECK_BOX);
        if (syllabusCheckBox.isSelected()) {
           this.pageList.set(1, null);
           syllabusCheckBox.setSelected(false);
        }
        else {
           this.pageList.set(1, new Page("Syllabus", "syllabus.html"));
           syllabusCheckBox.setSelected(true);
        }
    }
    
    public void undoSelectScheduleCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox scheduleCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SCHEDULE_CHECK_BOX);
        if (scheduleCheckBox.isSelected()) {
           this.pageList.set(2, null);
           scheduleCheckBox.setSelected(false);
        }
        else {
           this.pageList.set(2, new Page("Schedule", "schedule.html"));
           scheduleCheckBox.setSelected(true);
        }
    }
    
    public void undoSelectHWsCheckBox() {
        AppGUIModule gui = app.getGUIModule();
        CheckBox hwsCheckBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HWS_CHECK_BOX);
        if (hwsCheckBox.isSelected()) {
           this.pageList.set(3, null);
           hwsCheckBox.setSelected(false);
        }
        else {
           this.pageList.set(3, new Page("HWs", "hws.html"));
           hwsCheckBox.setSelected(true);
        }
    }
    
    //SET IMAGES
    private String absoluteToRelative(MyImage image, String base) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String path = image.getUrl();
        String relative = "." + path.substring(path.lastIndexOf(base.substring(1)));
        return relative;
    }
    public void changeImagesBase(String base) {
        String faviconPath = this.logList.get("favicon").getHref();
        String navbarPath =  this.logList.get("navbar").getSrc();
        String leftFooterPath =  this.logList.get("left_footer")
                .getSrc();
        String rightFooterPath =  this.logList.get("right_footer").getSrc();
        int indexOfFaviconSlash = faviconPath.lastIndexOf("/");
        int indexOfNavbarSlash = navbarPath.lastIndexOf("/");
        int indexOfLeftFooterSlash = leftFooterPath.lastIndexOf("/");
        int indexOfRightFooterSlash = rightFooterPath.lastIndexOf("/");
        faviconPath = base + faviconPath.substring(indexOfFaviconSlash + 1);
        navbarPath = base + navbarPath.substring(indexOfNavbarSlash + 1);
        leftFooterPath = base + leftFooterPath.substring(indexOfLeftFooterSlash + 1);
        rightFooterPath = base + rightFooterPath.substring(indexOfRightFooterSlash + 1);
        this.logList.get("favicon").setHref(faviconPath);
        this.logList.get("navbar").setSrc(navbarPath);
        this.logList.get("left_footer").setSrc(leftFooterPath);
        this.logList.get("right_footer").setSrc(rightFooterPath);
    }
    public void setFaviconImage(MyImage newImage) {
        if (newImage != null) {
            AppGUIModule gui = app.getGUIModule();
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String base = props.getProperty(APP_PATH_IMAGES);
            ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_FAVICON_IMAGE_VIEW);
            imageView.setImage(newImage);
            String relative = absoluteToRelative(newImage, base);
            this.logList.get("favicon").setHref(relative);
        }
        else
            this.logList.get("favicon").setHref("");
    }
    
    public void setNavbarImage(MyImage newImage) {
        if (newImage != null) {
            AppGUIModule gui = app.getGUIModule();
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String base = props.getProperty(APP_PATH_IMAGES);
            ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW);
            imageView.setImage(newImage);
            String relative = absoluteToRelative(newImage, base);
            this.logList.get("navbar").setSrc(relative);
        }
        else
            this.logList.get("navbar").setSrc("");
    }
    
    public void setLeftFooterImage(MyImage newImage) {
        if (newImage != null) {
            AppGUIModule gui = app.getGUIModule();
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String base = props.getProperty(APP_PATH_IMAGES);
            ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW);
            imageView.setImage(newImage);
            String relative = absoluteToRelative(newImage, base);
            this.logList.get("left_footer").setSrc(relative);
        }
        else
            this.logList.get("left_footer").setSrc("");
    }
    
    public void setRightFooterImage(MyImage newImage) {
        if (newImage != null) {
            AppGUIModule gui = app.getGUIModule();
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String base = props.getProperty(APP_PATH_IMAGES);
            ImageView imageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW);
            imageView.setImage(newImage);
            String relative = absoluteToRelative(newImage, base);
            this.logList.get("right_footer") .setSrc(relative);
        }
        else
            this.logList.get("right_footer") .setSrc("");
    }
    
    //EDIT TEXT FIELD
    public void editTitleTextField(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        TextField titleTextField = ((TextField) gui.getGUINode(CSG_SITE_TITLE_TEXT_FIELD));
        this.banner.setTitle(newVal);
        titleTextField.setText(newVal);
    }
    
    public void editNameTextField(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        TextField nameTextField = ((TextField) gui.getGUINode(CSG_SITE_NAME_TEXT_FIELD));
        this.instructor.setName(newVal);
        nameTextField.setText(newVal);
    }
    
    public void editRoomTextField(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        TextField roomTextField = ((TextField) gui.getGUINode(CSG_SITE_ROOM_TEXT_FIELD));
        this.instructor.setRoom(newVal);
        roomTextField.setText(newVal);
    }
    
    public void editEmailTextField(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        TextField emailTextField = ((TextField) gui.getGUINode(CSG_SITE_EMAIL_TEXT_FIELD));
        this.instructor.setEmail(newVal);
        emailTextField.setText(newVal);
    }
    
    public void editHomePageTextField(String newVal) {
        AppGUIModule gui = app.getGUIModule();
        TextField homePageTextField = ((TextField) gui.getGUINode(CSG_SITE_HOME_PAGE_TEXT_FIELD));
        this.instructor.setLink(newVal);
        homePageTextField.setText(newVal);
    }
    
    //EDIT TEXT AREA
    public void editOHTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea instructorOHTextArea = ((TextArea) gui.getGUINode(CSG_SITE_OFFICE_HOURS_TEXT_AREA));
         this.instructor.setHours(newVal);
         instructorOHTextArea.setText(newVal);
    }
    
    public void editDesTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea desTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TEXT_AREA));
         this.syllabusList.replace("description", newVal);
         desTextArea.setText(newVal);
    }
    
    public void editTopTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea topTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_TOPICS_TEXT_AREA));
         this.syllabusList.replace("topics", newVal);
         topTextArea.setText(newVal);
    }
    
    public void editPreTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea preTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TEXT_AREA));
         this.syllabusList.replace("prerequisites", newVal);
         preTextArea.setText(newVal);
    }
    
    public void editOutTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea outTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TEXT_AREA));
         this.syllabusList.replace("outcomes", newVal);
         outTextArea.setText(newVal);
    }
    
    public void editTexTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea texTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA));
         this.syllabusList.replace("textbooks", newVal);
         texTextArea.setText(newVal);
    }   
    
    public void editGCTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea gcTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA));
         this.syllabusList.replace("gradedComponents", newVal);
         gcTextArea.setText(newVal);
    }
    
    public void editGNTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea gnTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA));
         this.syllabusList.replace("gradingNote", newVal);
         gnTextArea.setText(newVal);
    }
    
    public void editADTextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea adTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA));
         this.syllabusList.replace("academicDishonesty", newVal);
         adTextArea.setText(newVal);
    }
    
    public void editSATextArea(String newVal) {
         AppGUIModule gui = app.getGUIModule();
         TextArea saTextArea = ((TextArea) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA));
         this.syllabusList.replace("specialAssistance", newVal);
         saTextArea.setText(newVal);
    }
     
    // PRIVATE HELPER METHODS
    
    private void sortTAs() {
        Collections.sort(teachingAssistants);
    }
    
    private void sortScheduls() {
        Collections.sort(schedulesList);
    }
    
    private void resetOfficeHours() {
        //THIS WILL STORE OUR OFFICE HOURS
        AppGUIModule gui = app.getGUIModule();
        TableView<TimeSlot> officeHoursTableView = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        officeHours = officeHoursTableView.getItems(); 
        officeHours.clear();
        for (int i = startHour; i <= endHour; i++) {
            TimeSlot timeSlot = new TimeSlot(this.getTimeString(i, true),
                                                this.getTimeString(i, false));
            officeHours.add(timeSlot);
            allTimeSlots.add(timeSlot);
            TimeSlot halfTimeSlot = new TimeSlot(this.getTimeString(i, false),
                                                    this.getTimeString(i+1, true));
            officeHours.add(halfTimeSlot);
            allTimeSlots.add(halfTimeSlot);
        }
    }
    
    private String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }
    
    // METHODS TO OVERRIDE
        
    /**
     * Called each time new work is created or loaded, it resets all data
     * and data structures such that they can be used for new values.
     */
    @Override
    public void reset() {
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        workspace.reset();
        resetSiteTab();
        resetSyllabusTab();
        resetMeetingTimesTab();
        resetOfficeHoursTab();
        resetScheduleTab();
    }
    
    private void resetSiteTab() {
        AppGUIModule gui = app.getGUIModule();
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        ComboBox numberBox = (ComboBox)gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        banner.setSubject((String)subjectBox.getValue());
        banner.setNumber((String)numberBox.getValue());
        banner.setSemester((String)semesterBox.getValue());
        banner.setYear((String)yearBox.getValue());
        banner.setTitle("");
        for (int i = 0; i < 4; i++) 
            pageList.add(null);
        ImageView faviconImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_FAVICON_IMAGE_VIEW);
        ImageView navbarImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW);
        ImageView leftFooterImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW);
        ImageView rightFooterImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW);
        ComboBox styleSheetBox = (ComboBox)gui.getGUINode(CSG_SITE_STYLE_SHEET_COMBO_BOX);
        setFaviconImage((MyImage)faviconImageView.getImage());
        setNavbarImage((MyImage)navbarImageView.getImage());
        setLeftFooterImage((MyImage)leftFooterImageView.getImage());
        setRightFooterImage((MyImage)rightFooterImageView.getImage());
        styleSheet = (String) styleSheetBox.getValue();
        instructor.clear();
    }
    
    private void resetSyllabusTab() {
        syllabusList.put("description", "");
        syllabusList.put("topics", "");
        syllabusList.put("prerequisites", "");
        syllabusList.put("outcomes", "");
        syllabusList.put("textbooks", "");
        syllabusList.put("gradedComponents", "");
        syllabusList.put("gradingNote", "");
        syllabusList.put("academicDishonesty", "");
        syllabusList.put("specialAssistance", "");
    }
    
    private void resetMeetingTimesTab() {
        lecturesList.clear();
        recitationsList.clear();
        labsList.clear();
    }
    
    private void resetOfficeHoursTab() {
        //RESET OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        allTAs.get(TAType.Graduate).clear();
        allTAs.get(TAType.Undergraduate).clear();
        for (TimeSlot timeSlot : officeHours) {
            timeSlot.reset();
        }
        allTimeSlots.clear();
    }
   
    private void resetScheduleTab() {
        this.initScheduleCalendar("0", "0", "0", "0", "0", "0");
        schedulesList.clear();
        allSchedules.get(ScheduleType.Holiday).clear();
        allSchedules.get(ScheduleType.Lecture).clear();
        allSchedules.get(ScheduleType.Recitation).clear();
        allSchedules.get(ScheduleType.Reference).clear();
        allSchedules.get(ScheduleType.HW).clear();
    }
    // SERVICE METHODS
    
    public void initOfficeHoursTime(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if (initStartHour <= initEndHour) {
            // THESE ARE VALID HOURS SO KEEP THEM
            // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
            startHour = initStartHour;
            endHour = initEndHour;
        }
        resetOfficeHours();
    }
    
    public void initScheduleCalendar(String startingMondayMonth, String startingMondayDay, String startingMondayYear,
                        String endingFridayMonth, String endingFridayDay, String endingFridayYear) {
        this.startingMondayMonth = Integer.parseInt(startingMondayMonth);
        this.startingMondayDay = Integer.parseInt(startingMondayDay);
        this.endingFridayMonth = Integer.parseInt(endingFridayMonth);
        this.endingFridayDay = Integer.parseInt(endingFridayDay);
        AppGUIModule gui = app.getGUIModule();
        DatePicker startDatePicker = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER);
        DatePicker endDatePicker = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if (startingMondayMonth.equals("0") || startingMondayYear.equals("0")) 
            startDatePicker.setValue(null);
        else {
            String startDate = formatDate(startingMondayMonth, startingMondayDay, startingMondayYear);
            startDatePicker.setValue(LocalDate.parse(startDate, formatter));
        }
        if (endingFridayMonth.equals("0") || endingFridayYear.equals("0"))
            endDatePicker.setValue(null);
        else {
            String endDate = formatDate(endingFridayMonth, endingFridayDay, endingFridayYear);
            endDatePicker.setValue(LocalDate.parse(endDate, formatter));
        }
    }
    
    public String formatDate(String month, String day, String year) {
        if (day.length() < 2)
            day = "0" + day;
        if (month.length() < 2)
            month = "0" + month;
        return month.trim() + "/" + day.trim() + "/" + year.trim();
    }
    public void addLecture(MeetingTimesPrototype lecture) {
        lecturesList.add(lecture);
    }   
    public void addRecitation(MeetingTimesPrototype recitation) {       
        recitationsList.add(recitation);
    }
    public void addLab(MeetingTimesPrototype lab) {
        labsList.add(lab);
    }
    public void removeLecture(MeetingTimesPrototype lecture) {
        if (lecturesList.contains(lecture)) 
            lecturesList.remove(lecture);
    }
    public void removeRecitation(MeetingTimesPrototype recitation) {
        if (recitationsList.contains(recitation))
            recitationsList.remove(recitation);
    }
    public void removeLab(MeetingTimesPrototype lab) {
        if (labsList.contains(lab))
            labsList.remove(lab);
    }

    public void addHoliday(SchedulesPrototype holiday) {
        ArrayList<SchedulesPrototype> holidayScheduleList = allSchedules.get(ScheduleType.Holiday);
        if (!holidayScheduleList.contains(holiday)) 
            holidayScheduleList.add(holiday);
        if (!schedulesList.contains(holiday))
            schedulesList.add(holiday);
    }
    
    public void addLecture(SchedulesPrototype lecture) {
        ArrayList<SchedulesPrototype> lectureScheduleList = allSchedules.get(ScheduleType.Lecture);
        if (!lectureScheduleList.contains(lecture)) 
            lectureScheduleList.add(lecture);
        if (!schedulesList.contains(lecture))
            schedulesList.add(lecture);
    }
    
    public void addReference(SchedulesPrototype reference) {
        ArrayList<SchedulesPrototype> referenceScheduleList = allSchedules.get(ScheduleType.Reference);
        if (!referenceScheduleList.contains(reference)) 
            referenceScheduleList.add(reference);
        if (!schedulesList.contains(reference))
            schedulesList.add(reference);
    }
    
    public void addRecitation(SchedulesPrototype recitation) {
        ArrayList<SchedulesPrototype> recitationScheduleList = allSchedules.get(ScheduleType.Recitation);
        if (!recitationScheduleList.contains(recitation)) 
            recitationScheduleList.add(recitation);
        if (!schedulesList.contains(recitation))
            schedulesList.add(recitation);
    }
    
    public void addHw(SchedulesPrototype hw) {
        ArrayList<SchedulesPrototype> hwScheduleList = allSchedules.get(ScheduleType.HW);
        if (!hwScheduleList.contains(hw)) 
            hwScheduleList.add(hw);
        if (!schedulesList.contains(hw))
            schedulesList.add(hw);
    }
    
    public void addTA(TeachingAssistantPrototype ta) {
        if (!hasTA(ta)) {
            TAType taType = TAType.valueOf(ta.getType());
            ArrayList<TeachingAssistantPrototype> tas = allTAs.get(taType);
            tas.add(ta);
            this.updateTAs();
        }
    }

    public void addTA(TeachingAssistantPrototype ta, HashMap<TimeSlot, ArrayList<DayOfWeek>> officeHours) {
        addTA(ta);
        for (TimeSlot timeSlot : officeHours.keySet()) {
            ArrayList<DayOfWeek> days = officeHours.get(timeSlot);
            for (DayOfWeek dow : days) {
                timeSlot.addTA(dow, ta);
            }
        }
    }
    
    public void addScheduleItem(SchedulesPrototype newItem) {
        if (!hasScheduleItem(newItem)) {
            ScheduleType scheduleType = ScheduleType.valueOf(newItem.getType());
            ArrayList<SchedulesPrototype> arrayList = allSchedules.get(scheduleType);
            arrayList.add(newItem);
            this.schedulesList.add(newItem);
            sortScheduls();
        } 
    }
    
    public void removeScheduleItem(SchedulesPrototype item) {
        ScheduleType scheduleType = ScheduleType.valueOf(item.getType());
        allSchedules.get(scheduleType).remove(item);
        schedulesList.remove(item);
    }
    public void removeTA(TeachingAssistantPrototype ta) {
        // REMOVE THE TA FROM THE LIST OF TAs
        TAType taType = TAType.valueOf(ta.getType());
        allTAs.get(taType).remove(ta);
        
        // REMOVE THE TA FROM ALL OF THEIR OFFICE HOURS
        for (TimeSlot timeSlot : officeHours) {
            timeSlot.removeTA(ta);
        }
        
        for (TimeSlot timeSlot : allTimeSlots) {
            timeSlot.removeTA(ta);
        }
        // AND REFRESH THE TABLES
        this.updateTAs();
    }

    public void removeTA(TeachingAssistantPrototype ta, HashMap<TimeSlot, ArrayList<DayOfWeek>> officeHours) {
        removeTA(ta);
        for (TimeSlot timeSlot : officeHours.keySet()) {
            ArrayList<DayOfWeek> days = officeHours.get(timeSlot);
            for (DayOfWeek dow : days) {
                timeSlot.removeTA(dow, ta);
            }
        }    
    }
    
    public void undoRemove (TeachingAssistantPrototype taSelected, ArrayList<TimeSlot> timeSlotList) {
        this.addTA(taSelected);
        for (TimeSlot timeSlot : timeSlotList) {
             for (TimeSlot.DayOfWeek dow : TimeSlot.DayOfWeek.values()) {
                if (timeSlot.getTasHashMap().get(dow).contains(taSelected))
                    this.getTimeSlot(timeSlot.getStartTime().replace(":", "_"))
                            .getTasHashMap()
                            .get(dow)
                            .add(taSelected);
             }
         }
        this.updateTAs();
    }
    
    public DayOfWeek getColumnDayOfWeek(int columnNumber) {
        return TimeSlot.DayOfWeek.values()[columnNumber-2];
    }

    public TeachingAssistantPrototype getTAWithName(String name) {
        Iterator<TeachingAssistantPrototype> taIterator = teachingAssistants.iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getName().equals(name))
                return ta;
        }
        return null;
    }

    public TeachingAssistantPrototype getTAWithEmail(String email) {
        Iterator<TeachingAssistantPrototype> taIterator = teachingAssistants.iterator();
        while (taIterator.hasNext()) {
            TeachingAssistantPrototype ta = taIterator.next();
            if (ta.getEmail().equals(email))
                return ta;
        }
        return null;
    }
    
    public ArrayList<TimeSlot> getAllTimeSlots() {
        return allTimeSlots;
    }
    
    public TimeSlot getTimeSlot(String startTime) {
        Iterator<TimeSlot> timeSlotsIterator = officeHours.iterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            String timeSlotStartTime = timeSlot.getStartTime().replace(":", "_");
            if (timeSlotStartTime.equals(startTime))
                return timeSlot;
        }
        return null;
    }

    public TAType getSelectedType() {
        RadioButton allRadio = (RadioButton)app.getGUIModule().getGUINode(CSG_OFFICE_HOURS_ALL_RADIO_BUTTON);
        if (allRadio.isSelected())
            return TAType.All;
        RadioButton gradRadio = (RadioButton)app.getGUIModule().getGUINode(CSG_OFFICE_HOURS_GRADUATE_RADIO_BUTTON);
        if (gradRadio.isSelected())
            return TAType.Graduate;
        else
            return TAType.Undergraduate;
    }
    
    public TeachingAssistantPrototype getSelectedTA() {
        AppGUIModule gui = app.getGUIModule();
        TableView<TeachingAssistantPrototype> tasTable = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
        return tasTable.getSelectionModel().getSelectedItem();
    }
    
    public HashMap<TimeSlot, ArrayList<DayOfWeek>> getTATimeSlots(TeachingAssistantPrototype ta) {
        HashMap<TimeSlot, ArrayList<DayOfWeek>> timeSlots = new HashMap();
        for (TimeSlot timeSlot : officeHours) {
            if (timeSlot.hasTA(ta)) {
                ArrayList<DayOfWeek> daysForTA = timeSlot.getDaysForTA(ta);
                timeSlots.put(timeSlot, daysForTA);
            }
        }
        return timeSlots;
    }
    
    private boolean hasTA(TeachingAssistantPrototype testTA) {
        return allTAs.get(TAType.Graduate).contains(testTA)
                ||
                allTAs.get(TAType.Undergraduate).contains(testTA);
    }
    private boolean hasScheduleItem(SchedulesPrototype item) {
         return allSchedules.get(ScheduleType.Holiday).contains(item)
                ||
                allSchedules.get(ScheduleType.Lecture).contains(item)
                ||
                allSchedules.get(ScheduleType.Recitation).contains(item)
                ||
                allSchedules.get(ScheduleType.Reference).contains(item)
                ||
                allSchedules.get(ScheduleType.HW).contains(item);
    }
    public boolean isLectureSelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView lectureTable = (TableView)gui.getGUINode(CSG_MEETING_TIMES_LECTURES_TABLE_VIEW);
        return lectureTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean isRecitationSelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView recitationTable = (TableView)gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW);
        return recitationTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean isLabSelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView labTable = (TableView)gui.getGUINode(CSG_MEETING_TIMES_LABS_TABLE_VIEW);
        return labTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean isTASelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView tasTable = (TableView)gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
        return tasTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean isScheduleItemSelected() {
        AppGUIModule gui = app.getGUIModule();
        TableView scheduleItemsTable = (TableView)gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
        return scheduleItemsTable.getSelectionModel().getSelectedItem() != null;
    }
    public boolean isLegalNewTA(String name, String email) {
        if ((name.trim().length() > 0)
                && (email.trim().length() > 0)) {
            // MAKE SURE NO TA ALREADY HAS THE SAME NAME
            TAType type = this.getSelectedType();
            TeachingAssistantPrototype testTA = new TeachingAssistantPrototype(name, email, type);
            if (this.teachingAssistants.contains(testTA))
                return false;
            if (this.isLegalNewEmail(email)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isLegalNewName(String testName) {
        if (testName.trim().length() > 0) {
            for (TeachingAssistantPrototype testTA: this.allTAs.get(TAType.Graduate)) {
                 if (testTA.getName().equals(testName))
                    return false;
            }
            
             for (TeachingAssistantPrototype testTA: this.allTAs.get(TAType.Graduate)) {
                 if (testTA.getName().equals(testName))
                    return false;
            }
             
            for (TeachingAssistantPrototype testTA : this.teachingAssistants) {
                if (testTA.getName().equals(testName))
                    return false;
            }
            return true;
        }
        return false;
    }
    
    public boolean isLegalNewEmail(String email) {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
                "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        if (matcher.find()) {
            for (TeachingAssistantPrototype testTA: this.allTAs.get(TAType.Graduate)) {
                 if (testTA.getEmail().equals(email.trim()))
                    return false;
            }
            
             for (TeachingAssistantPrototype testTA: this.allTAs.get(TAType.Graduate)) {
                 if (testTA.getEmail().equals(email.trim()))
                    return false;
            }
            for (TeachingAssistantPrototype ta : this.teachingAssistants) {
                if (ta.getEmail().equals(email.trim()))
                    return false;
            }
            return true;
        }
        else return false;
    }
    
    public boolean isDayOfWeekColumn(int columnNumber) {
        return columnNumber >= 2;
    }
    
    public boolean isTATypeSelected() {
        AppGUIModule gui = app.getGUIModule();
        RadioButton allRadioButton = (RadioButton)gui.getGUINode(CSG_OFFICE_HOURS_ALL_RADIO_BUTTON);
        return !allRadioButton.isSelected();
    }
    
    public boolean isValidTAEdit(TeachingAssistantPrototype taToEdit, String name, String email) {
        if (!taToEdit.getName().equals(name)) {
            if (!this.isLegalNewName(name))
                return false;
        }
        if (!taToEdit.getEmail().equals(email)) {
            if (!this.isLegalNewEmail(email))
                return false;
        }
        return true;
    }

    public boolean isValidNameEdit(TeachingAssistantPrototype taToEdit, String name) {
        if (!taToEdit.getName().equals(name)) {
            if (!this.isLegalNewName(name))
                return false;
        }
        return true;
    }

    public boolean isValidEmailEdit(TeachingAssistantPrototype taToEdit, String email) {
        if (!taToEdit.getEmail().equals(email)) {
            if (!this.isLegalNewEmail(email))
                return false;
        }
        return true;
    }    

    public void updateTAs() {
        TAType type = getSelectedType();
        selectTAs(type);
    }
    
    public void selectTAs(TAType type) {
        teachingAssistants.clear();
        Iterator<TeachingAssistantPrototype> tasIt = this.teachingAssistantsIterator();
        while (tasIt.hasNext()) {
            TeachingAssistantPrototype ta = tasIt.next();
            if (type.equals(TAType.All)) {
                teachingAssistants.add(ta);
            }
            else if (ta.getType().equals(type.toString())) {
                teachingAssistants.add(ta);
            }
        }
        
        // SORT THEM BY NAME
        sortTAs();

        // CLEAR ALL THE OFFICE HOURS
        Iterator<TimeSlot> officeHoursIt = officeHours.iterator();
        while (officeHoursIt.hasNext()) {
            TimeSlot timeSlot = officeHoursIt.next();
            timeSlot.filter(type);
        }
        
        app.getFoolproofModule().updateAll();
    }
    
    public Iterator<TimeSlot> officeHoursIterator() {
        return officeHours.iterator();
    }

    public Iterator<TeachingAssistantPrototype> teachingAssistantsIterator() {
        return new AllTAsIterator();
    }
    
    public Iterator<MeetingTimesPrototype> lecturesIterator() {
        return lecturesList.iterator();
    }
    
    public Iterator<MeetingTimesPrototype> recitationsIterator() {
        return recitationsList.iterator();
    }
    
    public Iterator<MeetingTimesPrototype> labsIterator() {
        return labsList.iterator();
    }
    
    public Iterator<SchedulesPrototype>  schedulesIterator() {
        return new AllSchedulesIterator();
    }
    
    private class AllTAsIterator implements Iterator {
        Iterator gradIt = allTAs.get(TAType.Graduate).iterator();
        Iterator undergradIt = allTAs.get(TAType.Undergraduate).iterator();

        public AllTAsIterator() {}
        
        @Override
        public boolean hasNext() {
            if (gradIt.hasNext() || undergradIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (gradIt.hasNext())
                return gradIt.next();
            else if (undergradIt.hasNext())
                return undergradIt.next();
            else
                return null;
        }
    }
    
    private class AllSchedulesIterator implements Iterator {
        Iterator holidayIt = allSchedules.get(ScheduleType.Holiday).iterator();
        Iterator lectureIt = allSchedules.get(ScheduleType.Lecture).iterator();
        Iterator recitationIt = allSchedules.get(ScheduleType.Recitation).iterator();
        Iterator referenceIt = allSchedules.get(ScheduleType.Reference).iterator();
        Iterator hwIt = allSchedules.get(ScheduleType.HW).iterator();
        
        public AllSchedulesIterator() {}
        
        @Override
        public boolean hasNext() {
            if (holidayIt.hasNext() || lectureIt.hasNext() || recitationIt.hasNext() || referenceIt.hasNext() || hwIt.hasNext())
                return true;
            else
                return false;                
        }

        @Override
        public Object next() {
            if (holidayIt.hasNext())
                return holidayIt.next();
            else if (lectureIt.hasNext())
                return lectureIt.next();
            else if (recitationIt.hasNext())
                return recitationIt.next();
            else if (referenceIt.hasNext())
                return referenceIt.next();
            else if (hwIt.hasNext())
                return hwIt.next();
            else
                return null;
        }
    }
    
    public void changeTimeRange(String startTime, String endTime) {
        officeHours.clear();
        String startTimeTerm = startTime.substring(startTime.length() - 2);
        int startColonIndex = startTime.indexOf(":");
        int startTimeMin = Integer.valueOf(startTime.substring(startColonIndex + 1, startColonIndex + 3));
        int startTimeHour = Integer.valueOf(startTime.substring(0, startColonIndex));
        String endTimeTerm = endTime.substring(endTime.length() - 2);
        int endColonIndex = endTime.indexOf(":");
        int endTimeMin = Integer.valueOf(endTime.substring(endColonIndex + 1, endColonIndex + 3));
        int endTimeHour = Integer.valueOf(endTime.substring(0, endColonIndex));
        for(TimeSlot timeSlot : allTimeSlots) {
            String itemStartTime = timeSlot.getStartTime();
            String itemStartTimeTerm = itemStartTime.substring(itemStartTime.length() - 2);
            int itemStartColonIndex = itemStartTime.indexOf(":");
            int itemStartMin = Integer.valueOf(itemStartTime.substring(itemStartColonIndex + 1, itemStartColonIndex + 3));
            int itemStartHour = Integer.valueOf(itemStartTime.substring(0, itemStartColonIndex));
            String itemEndTime = timeSlot.getEndTime();
            String itemEndTimeTerm = itemEndTime.substring(itemEndTime.length() - 2);
            int itemEndColonIndex = itemEndTime.indexOf(":");
            int itemEndMin = Integer.valueOf(itemEndTime.substring(itemEndColonIndex + 1, itemEndColonIndex + 3));
            int itemEndHour = Integer.valueOf(itemEndTime.substring(0, itemEndColonIndex)); 
            
            if (startTimeTerm.equals("am") && endTimeTerm.equals("am") 
                    && itemStartTimeTerm.equals("am") && itemEndTimeTerm.equals("am")) {
                if ((itemStartHour > startTimeHour || (itemStartHour == startTimeHour && itemStartMin >= startTimeMin)) 
                        && ((itemEndHour < endTimeHour) || (itemEndHour == endTimeHour && itemEndMin <= endTimeMin))) {
                    officeHours.add(timeSlot);
                }
            }
            else if (startTimeTerm.equals("am") && endTimeTerm.equals("pm")){
                    if (itemStartTimeTerm.equals("am") && itemEndTimeTerm.equals("am")
                            && (itemStartHour > startTimeHour || (itemStartHour == startTimeHour && itemStartMin >= startTimeMin))) {
                           officeHours.add(timeSlot);
                    }
                    else if (itemStartTimeTerm.equals("am") && itemEndTimeTerm.equals("pm")) {
                            if (itemEndHour == 12 && (itemEndMin == 0))
                                officeHours.add(timeSlot);
                            
                    }
                    else if (itemStartTimeTerm.equals("pm") && itemEndTimeTerm.equals("pm"))    {
                           if (endTimeHour == 12 && itemEndHour == 12 
                                   && ((itemEndHour < endTimeHour) || (itemEndHour == endTimeHour && itemEndMin <= endTimeMin)))
                                officeHours.add(timeSlot);
                           else if (endTimeHour != 12 && itemEndHour == 12)
                               officeHours.add(timeSlot);
                           else if (endTimeHour != 12 && itemEndHour != 12
                                   && ((itemEndHour < endTimeHour) || (itemEndHour == endTimeHour && itemEndMin <= endTimeMin)))
                               officeHours.add(timeSlot);
                    }
            }
            else if (startTimeTerm.equals("pm") && endTimeTerm.equals("pm")
                    && itemStartTimeTerm.equals("pm") && itemEndTimeTerm.equals("pm")) {
                if (startTimeHour == 12 && endTimeHour == 12 && itemStartHour == 12 && itemEndHour == 12) {
                    officeHours.add(timeSlot);
                }
                else if (startTimeHour == 12 && endTimeHour != 12 && ((itemStartHour == 12 && itemStartMin >= startTimeMin) 
                        || itemStartHour < startTimeHour) 
                        && ((itemEndHour < endTimeHour) || (itemEndHour == endTimeHour && itemEndMin <= endTimeMin)
                        ||  itemEndHour == 12)) {
                     officeHours.add(timeSlot);
                }
                else if (startTimeHour != 12 && endTimeHour != 12 && itemStartHour != 12 && itemEndHour != 12 && (itemStartHour > startTimeHour || (itemStartHour == startTimeHour && itemStartMin >= startTimeMin)) 
                        && ((itemEndHour < endTimeHour) || (itemEndHour == endTimeHour && itemEndMin <= endTimeMin))) {
                    officeHours.add(timeSlot);
                }
            }
        }
    }
    
    public void selectDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate != null && endDate != null && startDate.isBefore(endDate)) {
            schedulesList.clear();
            Iterator<SchedulesPrototype> schedulsIt = this.schedulesIterator();
            while (schedulsIt.hasNext()) {
                SchedulesPrototype schedule = schedulsIt.next();
                LocalDate localDate = schedule.getLocalDate();
                if ((localDate.isAfter(startDate) || localDate.isEqual(startDate)) 
                        && (localDate.isBefore(endDate) || localDate.isEqual(endDate))) {
                    schedulesList.add(schedule);
                }
            }
            // SORT THEM BY DATE
            sortScheduls();
        }
        else if (startDate != null && endDate == null) {
            schedulesList.clear();
            Iterator<SchedulesPrototype> schedulsIt = this.schedulesIterator();
            while (schedulsIt.hasNext()) {
                SchedulesPrototype schedule = schedulsIt.next();
                LocalDate localDate = schedule.getLocalDate();
                if ((localDate.isAfter(startDate) || localDate.isEqual(startDate))) 
                    schedulesList.add(schedule);
            }
            // SORT THEM BY DATE
            sortScheduls();
        }
        else if (startDate == null && endDate != null) {
            schedulesList.clear();
            Iterator<SchedulesPrototype> schedulsIt = this.schedulesIterator();
            while (schedulsIt.hasNext()) {
                SchedulesPrototype schedule = schedulsIt.next();
                LocalDate localDate = schedule.getLocalDate();
                if ((localDate.isBefore(endDate) || localDate.isEqual(endDate))) 
                    schedulesList.add(schedule);
            }
            // SORT THEM BY DATE
            sortScheduls();
        }
        else
            showAllScheduleItems();
    }
    
    public void showAllScheduleItems() {
        schedulesList.clear();
        Iterator<SchedulesPrototype> schedulsIt = this.schedulesIterator();
        while (schedulsIt.hasNext()) {
            SchedulesPrototype schedule = schedulsIt.next();
            schedulesList.add(schedule);
        }
        sortScheduls();
    }
}