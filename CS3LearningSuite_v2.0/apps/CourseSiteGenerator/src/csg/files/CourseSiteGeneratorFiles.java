package csg.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.CSG_FAILED_TO_LOAD;
import static csg.CourseSiteGeneratorPropertyType.CSG_INVALID_FILE;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_EXPORT_DIR_PATH;
import static csg.CourseSiteGeneratorPropertyType.CSG_SITE_YEAR_COMBO_BOX;
import csg.data.MeetingTimesPrototype;
import csg.data.CourseSiteGeneratorData;
import csg.data.MyImage;
import csg.data.Page;
import csg.data.ScheduleType;
import csg.data.SchedulesPrototype;
import csg.data.TeachingAssistantPrototype;


import csg.data.TimeSlot;
import csg.data.TimeSlot.DayOfWeek;
import csg.data.TAType;
import static csg.files.CourseSiteGeneratorFiles.JSON_SUBJECT;
import static djf.AppPropertyType.APP_EXPORT_PAGE;
import djf.ui.controllers.NoPageSelectedException;
import static djf.AppPropertyType.APP_PATH_APP_DATA;
import static djf.AppPropertyType.APP_PATH_EXPORT;
import static djf.AppPropertyType.APP_PATH_EXPORT_DATA;
import static djf.AppPropertyType.APP_PATH_EXPORT_DATA_CSS;
import static djf.AppPropertyType.APP_PATH_EXPORT_DATA_IMAGES;
import static djf.AppPropertyType.APP_PATH_IMAGES;
import static djf.AppPropertyType.APP_PATH_LOCAL_CSS;
import static djf.AppPropertyType.APP_PATH_STYLE_SHEET;
import djf.modules.AppGUIModule;
import djf.ui.dialogs.AppDialogsFacade;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import properties_manager.PropertiesManager;


/**
 * This class serves as the file component for the TA
 * manager app. It provides all saving and loading 
 * services for the application.
 * 
 * @author Richard McKenna
 */
public class CourseSiteGeneratorFiles implements AppFileComponent {
    // THIS IS THE APP ITSELF
    CourseSiteGeneratorApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_SUBJECT = "subject";
    static final String JSON_NUMBER = "number";
    static final String JSON_SEMESTER = "semester";
    static final String JSON_MONTH = "month";
    static final String JSON_DAY = "day";
    static final String JSON_YEAR = "year";
    static final String JSON_YEAR_ITEMS = "year_items";
    static final String JSON_ITEM = "item";
    static final String JSON_TITLE = "title";
    static final String JSON_LOGOS = "logos";
    static final String JSON_FAVICON = "favicon";
    static final String JSON_NAVBAR = "navbar";
    static final String JSON_BOTTOM_LEFT = "bottom_left";
    static final String JSON_BOTTOM_RIGHT = "bottom_right";
    static final String JSON_HREF = "href";
    static final String JSON_SRC = "src";
    static final String JSON_INSTRUCTOR = "instructor";
    static final String JSON_NAME = "name";
    static final String JSON_EMAIL = "email";
    static final String JSON_TOPIC = "topic";
    static final String JSON_LINK = "link";
    static final String JSON_PAGES = "pages";
    static final String JSON_PAGES_NAME = "pages_name";
    static final String JSON_PAGES_LINK = "pages_link";

    static final String JSON_DESCRIPTION = "description";
    static final String JSON_TOPICS = "topics";
    static final String JSON_PREREQUISITES = "prerequisites";
    static final String JSON_OUTCOMES = "outcomes";
    static final String JSON_TEXTBOOKS = "textbooks";
    static final String JSON_GRADED_COMPONENTS = "gradedComponents";
    static final String JSON_GRADING_NOTE = "gradingNote";
    static final String JSON_ACADEMIC_DISHONESTY = "academicDishonesty";
    static final String JSON_SPECIAL_ASSISTANCE = "specialAssistance";
    static final String JSON_HOLIDAYS = "holidays";
    static final String JSON_REFERENCES = "references";
    static final String JSON_HWS = "hws";
    static final String JSON_LECTURES = "lectures";
    static final String JSON_LABS = "labs";
    static final String JSON_RECITATIONS = "recitations";
    static final String JSON_SECTION = "section";
    static final String JSON_DAYS = "days";
    static final String JSON_TIME = "time";
    static final String JSON_ROOM = "room";
    static final String JSON_PHOTO = "photo";
    static final String JSON_HOURS = "hours";
    static final String JSON_DAY_TIME = "day_time";
    static final String JSON_LOCATION = "location";
    static final String JSON_TA1 = "ta_1";
    static final String JSON_TA2 = "ta_2";
    static final String JSON_GRAD_TAS = "grad_tas";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_TA_NAME = "name";
    static final String JSON_TA_EMAIL = "email";
    static final String JSON_TYPE = "type";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_START_TIME = "time";
    static final String JSON_DAY_OF_WEEK = "day";
    static final String JSON_STARTING_MONDAY_MONTH = "startingMondayMonth";
    static final String JSON_STARTING_MONDAY_DAY = "startingMondayDay";
    static final String JSON_STARTING_MONDAY_YEAR = "startingMondayYear";
    static final String JSON_ENDING_FRIDAY_MONTH = "endingFridayMonth";
    static final String JSON_ENDING_FRIDAY_DAY = "endingFridayDay"; 
    static final String JSON_ENDING_FRIDAY_YEAR = "endingFridayYear";
    static final String JSON_INSTRUCTOR_NAME = "instructor_name";
    static final String JSON_INSTRUCTOR_LINK = "instructor_link";
    static final String JSON_INSTRUCTOR_EMAIL = "instructor_email";
    static final String JSON_INSTRUCTOR_ROOM = "instructor_room";
    static final String JSON_INSTRUCTOR_PHOTO = "instructor_photo";
    static final String JSON_INSTRUCTOR_HOURS = "instructor_hours";
    static final String JSON_OH_NAME = "name";
    static final String JSON_SCHEDULE_HOLIDAYS = "schedule_holidays";
    static final String JSON_SCHEDULE_LECTURES = "schedule_lectures";
    static final String JSON_SCHEDULE_REFERENCES = "schedule_references";
    static final String JSON_SCHEDULE_RECITATIONS = "schedule_recitations";
    static final String JSON_SCHEDULE_HWS = "schedule_hws";
    static final String JSON_SCHEDULE_MONTH = "month";
    static final String JSON_SCHEDULE_DAY = "day";
     static final String JSON_SCHEDULE_YEAR = "year";
    static final String JSON_SCHEDULE_TITLE = "title";
    static final String JSON_SCHEDULE_TOPIC = "topic";
    static final String JSON_SCHEDULE_LINK = "link";
    public CourseSiteGeneratorFiles(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) {
        try {
            // CLEAR THE OLD DATA OUT
            CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
            dataManager.reset();
            AppGUIModule gui = app.getGUIModule();
            // LOAD THE JSON FILE WITH ALL THE DATA
            JsonObject json = loadJSONFile(filePath);
            
            //LOAD SITE  PAGE
            loadBanner(dataManager, json, gui);
            loadLogos(dataManager, json);
            loadInstructor(dataManager, json);
            loadPages(dataManager, json);
            //LOAD SYLLABUS PAGE
            loadSyllabus(dataManager, json);
            //LOAD MEETING TIMES PAGE
            loadLectures(dataManager, json);
            loadRecitations(dataManager, json);
            loadLabs(dataManager, json);
            //LOAD OFFICE HOURS PAGE
            // LOAD THE START AND END HOURS
            String startHour = json.getString(JSON_START_HOUR);
            String endHour = json.getString(JSON_END_HOUR);
            dataManager.initOfficeHoursTime(startHour, endHour);
            loadTAs(dataManager, json, JSON_GRAD_TAS);
            loadTAs(dataManager, json, JSON_UNDERGRAD_TAS);
            // AND THEN ALL THE OFFICE HOURS
            loadOHs(dataManager, json);
            String startingMondayMonth = json.getString(JSON_STARTING_MONDAY_MONTH);
            String startingMondayDay = json.getString(JSON_STARTING_MONDAY_DAY);
            String startingMondayYear = json.getString(JSON_STARTING_MONDAY_YEAR);
            String endingFridayMonth = json.getString(JSON_ENDING_FRIDAY_MONTH); 
            String endingFridayDay = json.getString(JSON_ENDING_FRIDAY_DAY);
            String endingFridayYear = json.getString(JSON_ENDING_FRIDAY_YEAR);
            dataManager.initScheduleCalendar(startingMondayMonth, startingMondayDay, startingMondayYear, 
                                                                      endingFridayMonth, endingFridayDay, endingFridayYear);
            loadSchedules(dataManager, json);
             
        } catch (Exception ex) {
            AppDialogsFacade.showStackTraceDialog(app.getGUIModule().getWindow(), ex, CSG_INVALID_FILE, CSG_FAILED_TO_LOAD);
        }
    }
    private void loadBanner(CourseSiteGeneratorData data, JsonObject json, AppGUIModule gui) {
        String subject = json.getString(JSON_SUBJECT);
        String number = json.getString(JSON_NUMBER);
        String semester = json.getString(JSON_SEMESTER);
        String year = json.getString(JSON_YEAR);
        String title = json.getString(JSON_TITLE);
        ArrayList<String> yearItemsList = new ArrayList();
        JsonArray yearItemsArray = json.getJsonArray(JSON_YEAR_ITEMS);
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        data.getBanner().setSubject(subject);
        data.getBanner().setNumber(number);
        data.getBanner().setSemester(semester);
        data.getBanner().setYear(year);
        data.getBanner().setTitle(title);
        data.setSubjectBox(subject);
        data.setNumberBox(number);
        data.setYearBox(year);
        data.setSemesterBox(semester);
        data.editTitleTextField(title);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        yearItemsList.add(String.valueOf(currentYear));
        yearItemsList.add(String.valueOf(currentYear + 1));
        for (int i = 0; i < yearItemsArray.size(); i++) {
            JsonObject jsonItem = yearItemsArray.getJsonObject(i);
            String item = jsonItem.getString(JSON_ITEM);
            if (item != null && !item.equals("") && !yearItemsList.contains(item))
                yearItemsList.add(item);
        }
        yearBox.setItems(FXCollections.observableArrayList(yearItemsList));
    }
    private void loadLogos(CourseSiteGeneratorData data, JsonObject json) {
        try {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            JsonObject logosJsonObject = json.getJsonObject(JSON_LOGOS);
            JsonObject jsonfavicon = logosJsonObject.getJsonObject(JSON_FAVICON);
            JsonObject jsonNavbar = logosJsonObject.getJsonObject(JSON_NAVBAR);
            JsonObject jsonBottomLeft = logosJsonObject.getJsonObject(JSON_BOTTOM_LEFT);
            JsonObject jsonBottomRight = logosJsonObject.getJsonObject(JSON_BOTTOM_RIGHT);
            String faviconHref = jsonfavicon.getString(JSON_HREF);
            String navbarHref = jsonNavbar.getString(JSON_HREF);
            String navbarScr = jsonNavbar.getString(JSON_SRC);
            String bottomLeftHref = jsonBottomLeft.getString(JSON_HREF);
            String bottomLeftSrc = jsonBottomLeft.getString(JSON_SRC);
            String bottomRightHref = jsonBottomRight.getString(JSON_HREF);
            String bottomRightSrc = jsonBottomRight.getString(JSON_SRC);
            data.getLogList().get("navbar").setHref(navbarHref);
            data.getLogList().get("left_footer").setHref(bottomLeftHref);
            data.getLogList().get("right_footer").setHref(bottomRightHref);
            String base = props.getProperty(APP_PATH_IMAGES);
            String pathFavicon = relativeToAbsolute(faviconHref, base);
            String pathNavbar = relativeToAbsolute(navbarScr, base);
            String pathLF = relativeToAbsolute(bottomLeftSrc, base);
            String pathRF = relativeToAbsolute(bottomRightSrc, base);
            pathFavicon = pathFavicon.replace("\\", "/");
            pathNavbar = pathNavbar.replace("\\", "/");
            pathLF  = pathLF.replace("\\", "/");
            pathRF = pathRF.replace("\\", "/");
            data.setFaviconImage(new MyImage("file:/" + pathFavicon));
            data.setNavbarImage(new MyImage("file:/" + pathNavbar));
            data.setLeftFooterImage(new MyImage("file:/" + pathLF));
            data.setRightFooterImage(new MyImage("file:/" + pathRF));
        } catch (IOException ex) {
            Logger.getLogger(CourseSiteGeneratorFiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void  loadInstructor(CourseSiteGeneratorData data, JsonObject json) {
        JsonObject jsonInstructor = json.getJsonObject(JSON_INSTRUCTOR);        
        data.editNameTextField(jsonInstructor.getString(JSON_INSTRUCTOR_NAME));
        data.editRoomTextField(jsonInstructor.getString(JSON_INSTRUCTOR_ROOM));
        data.editEmailTextField(jsonInstructor.getString(JSON_INSTRUCTOR_EMAIL));
        data.editHomePageTextField(jsonInstructor.getString(JSON_INSTRUCTOR_LINK));
        data.editOHTextArea(jsonInstructor.getString(JSON_INSTRUCTOR_HOURS));
    }    
    private void loadPages(CourseSiteGeneratorData data, JsonObject json) {
        JsonArray jsonPageArray = json.getJsonArray(JSON_PAGES);
        boolean[] a = {false, false, false, false}; 
        for (int i = 0; i < jsonPageArray.size(); i++) {
            JsonObject jsonPage = jsonPageArray.getJsonObject(i);
            String name = jsonPage.getString(JSON_PAGES_NAME);
            switch (name) {
                    case "Home": a[0] = true;
                    break;
                    case "Syllabus": a[1] = true;
                    break;
                    case "Schedule": a[2] = true;
                    break;
                    case "HWs": a[3] = true;
                    break;
            }
        }
        data.setHomeCheckBox(a[0]);
        data.setSyllabusCheckBox(a[1]);
        data.setScheduleCheckBox(a[2]);
        data.setHwsCheckBox(a[3]);
    }
    private void loadSyllabus(CourseSiteGeneratorData data, JsonObject json) {
        String des = json.getString(JSON_DESCRIPTION);
        String top = json.getString(JSON_TOPICS);
        String pre = json.getString(JSON_PREREQUISITES);
        String out = json.getString(JSON_OUTCOMES);
        String tex = json.getString(JSON_TEXTBOOKS);
        String gC = json.getString(JSON_GRADED_COMPONENTS);
        String gN = json.getString(JSON_GRADING_NOTE);
        String aD = json.getString(JSON_ACADEMIC_DISHONESTY);
        String sA = json.getString(JSON_SPECIAL_ASSISTANCE);
        data.editDesTextArea(des);
        data.editTopTextArea(top);
        data.editPreTextArea(pre);
        data.editOutTextArea(out);
        data.editTexTextArea(tex);
        data.editGCTextArea(gC);
        data.editGNTextArea(gN);
        data.editADTextArea(aD);
        data.editSATextArea(sA);
    }
    private void loadLectures(CourseSiteGeneratorData data, JsonObject json) {
        JsonArray jsonLectureArray = json.getJsonArray(JSON_LECTURES);
        for (int i = 0; i < jsonLectureArray.size(); i++) {
            JsonObject jsonLecture = jsonLectureArray.getJsonObject(i);
            String section = jsonLecture.getString(JSON_SECTION);
            String days = jsonLecture.getString(JSON_DAYS);
            String time = jsonLecture.getString(JSON_TIME);
            String room = jsonLecture.getString(JSON_ROOM);
            MeetingTimesPrototype mt = new MeetingTimesPrototype(section, days, time, room);
            data.addLecture(mt);
        }        
    }
    private void loadRecitations(CourseSiteGeneratorData data, JsonObject json) {
        JsonArray jsonRecitaionsArray = json.getJsonArray(JSON_RECITATIONS);
        for (int i = 0; i < jsonRecitaionsArray.size(); i++) {
            JsonObject jsonRecitaion = jsonRecitaionsArray.getJsonObject(i);
            String section = jsonRecitaion.getString(JSON_SECTION);
            String dayTime = jsonRecitaion.getString(JSON_DAY_TIME);
            String location = jsonRecitaion.getString(JSON_LOCATION);
            String ta1 = jsonRecitaion.getString(JSON_TA1);
            String ta2 = jsonRecitaion.getString(JSON_TA2);
            MeetingTimesPrototype mt = new MeetingTimesPrototype(section, dayTime, location, ta1, ta2);
            data.addRecitation(mt);
        }        
    }
    private void loadLabs(CourseSiteGeneratorData data, JsonObject json) {
       JsonArray jsonLabsArray = json.getJsonArray(JSON_LABS);
       for (int i = 0; i < jsonLabsArray.size(); i++) {
           JsonObject jsonLab = jsonLabsArray.getJsonObject(i);
           String section = jsonLab.getString(JSON_SECTION);
           String dayTime = jsonLab.getString(JSON_DAY_TIME);
           String location = jsonLab.getString(JSON_LOCATION);
           String ta1 = jsonLab.getString(JSON_TA1);
           String ta2 = jsonLab.getString(JSON_TA2);
           MeetingTimesPrototype mt = new MeetingTimesPrototype(section, dayTime, location, ta1, ta2);
           data.addLab(mt);
       }        
   }
    private void loadTAs(CourseSiteGeneratorData data, JsonObject json, String tas) {
        JsonArray jsonTAArray = json.getJsonArray(tas);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_TA_NAME);
            String email = jsonTA.getString(JSON_TA_EMAIL);
            TAType type = TAType.valueOf(jsonTA.getString(JSON_TYPE));
            TeachingAssistantPrototype ta = new TeachingAssistantPrototype(name, email, type);
            data.addTA(ta);
        }     
    }
    private void  loadOHs(CourseSiteGeneratorData data, JsonObject json) {
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
            for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
                JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
                String startTime = jsonOfficeHours.getString(JSON_START_TIME);
                DayOfWeek dow = DayOfWeek.valueOf(jsonOfficeHours.getString(JSON_DAY_OF_WEEK));
                String name = jsonOfficeHours.getString(JSON_OH_NAME);
                TeachingAssistantPrototype ta = data.getTAWithName(name);
                TimeSlot timeSlot = data.getTimeSlot(startTime);
                timeSlot.toggleTA(dow, ta);
            }
    } 
    private void loadSchedules(CourseSiteGeneratorData data, JsonObject json) {
        JsonArray jsonHolidaysArray = json.getJsonArray(JSON_SCHEDULE_HOLIDAYS);
        for (int i = 0; i < jsonHolidaysArray.size(); i++) {
                JsonObject jsonHoliday = jsonHolidaysArray.getJsonObject(i);
                String month = jsonHoliday.getString(JSON_SCHEDULE_MONTH);
                String day = jsonHoliday.getString(JSON_SCHEDULE_DAY);
                String year = jsonHoliday.getString(JSON_SCHEDULE_YEAR);
                String date = data.formatDate(month, day, year);
                String title = jsonHoliday.getString(JSON_SCHEDULE_TITLE);
                String link = jsonHoliday.getString(JSON_SCHEDULE_LINK);
                SchedulesPrototype holiday = new SchedulesPrototype(ScheduleType.Holiday, date, title, "", link);
                data.addHoliday(holiday);
         }
        JsonArray jsonLecturesArray = json.getJsonArray(JSON_SCHEDULE_LECTURES);
        for (int i = 0; i < jsonLecturesArray.size(); i++) {
                JsonObject jsonLecture = jsonLecturesArray.getJsonObject(i);
                String month = jsonLecture.getString(JSON_SCHEDULE_MONTH);
                String day = jsonLecture.getString(JSON_SCHEDULE_DAY);
                String year = jsonLecture.getString(JSON_SCHEDULE_YEAR);
                String date = data.formatDate(month, day, year);
                String title = jsonLecture.getString(JSON_SCHEDULE_TITLE);
                String topic = jsonLecture.getString(JSON_SCHEDULE_TOPIC);
                String link = jsonLecture.getString(JSON_SCHEDULE_LINK);
                SchedulesPrototype lecture = new SchedulesPrototype(ScheduleType.Lecture, date, title, topic, link);
                data.addLecture(lecture);
         }
        JsonArray jsonReferencesArray = json.getJsonArray(JSON_SCHEDULE_REFERENCES);
        for (int i = 0; i < jsonReferencesArray.size(); i++) {
                JsonObject jsonReference = jsonReferencesArray.getJsonObject(i);
                String month = jsonReference.getString(JSON_SCHEDULE_MONTH);
                String day = jsonReference.getString(JSON_SCHEDULE_DAY);
                String year = jsonReference.getString(JSON_SCHEDULE_YEAR);
                String date = data.formatDate(month, day, year);
                String title = jsonReference.getString(JSON_SCHEDULE_TITLE);
                String topic = jsonReference.getString(JSON_SCHEDULE_TOPIC);
                String link = jsonReference.getString(JSON_SCHEDULE_LINK);
                SchedulesPrototype reference = new SchedulesPrototype(ScheduleType.Reference, date, title, topic, link);
                data.addReference(reference);
         }
        JsonArray jsonRecitationsArray = json.getJsonArray(JSON_SCHEDULE_RECITATIONS);
        for (int i = 0; i < jsonRecitationsArray.size(); i++) {
                JsonObject jsonRecitation = jsonRecitationsArray.getJsonObject(i);
                String month = jsonRecitation.getString(JSON_SCHEDULE_MONTH);
                String day = jsonRecitation.getString(JSON_SCHEDULE_DAY);
                String year = jsonRecitation.getString(JSON_SCHEDULE_YEAR);
                String date = data.formatDate(month, day, year);
                String title = jsonRecitation.getString(JSON_SCHEDULE_TITLE);
                String topic = jsonRecitation.getString(JSON_SCHEDULE_TOPIC);
                String link = jsonRecitation.getString(JSON_SCHEDULE_LINK);
                SchedulesPrototype recitation = new SchedulesPrototype(ScheduleType.Recitation, date, title, topic, link);
                data.addRecitation(recitation);
         }
        JsonArray jsonHwsArray = json.getJsonArray(JSON_SCHEDULE_HWS);
        for (int i = 0; i < jsonHwsArray.size(); i++) {
                JsonObject jsonHw = jsonHwsArray.getJsonObject(i);
                String month = jsonHw.getString(JSON_SCHEDULE_MONTH);
                String day = jsonHw.getString(JSON_SCHEDULE_DAY);
                String year = jsonHw.getString(JSON_SCHEDULE_YEAR);
                String date = data.formatDate(month, day, year);
                String title = jsonHw.getString(JSON_SCHEDULE_TITLE);
                String topic = jsonHw.getString(JSON_SCHEDULE_TOPIC);
                String link = jsonHw.getString(JSON_SCHEDULE_LINK);
                SchedulesPrototype hw = new SchedulesPrototype(ScheduleType.HW, date, title, topic, link);
                data.addHw(hw);
         }
    }

    private String relativeToAbsolute(String relativePath, String referenceDirPath) throws IOException {
        File a = new File(referenceDirPath);
        File parentFolder = new File(a.getParent());
        File b = new File(parentFolder, relativePath);
        String absolute = b.getCanonicalPath();
        return absolute;
    }
      
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
        // GET THE DATA
        CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        AppGUIModule gui = app.getGUIModule();
        //*
        //*SITE PAGE
        //*
        JsonArrayBuilder yearItemsArrayBuilder = Json.createArrayBuilder();
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        for (Object item : yearBox.getItems()) {
            if (item != null) {
                JsonObject jsonItem = Json.createObjectBuilder()
                        .add(JSON_ITEM, (String)item)
                        .build();
                yearItemsArrayBuilder.add(jsonItem);
            }
        }
        JsonArray yearItemsArray = yearItemsArrayBuilder.build();
        
        JsonArrayBuilder pagesArrayBuilder = Json.createArrayBuilder();
        for (Page page : dataManager.getPageList()) {
            if (page != null) {
                JsonObject jsonPage = Json.createObjectBuilder()
                        .add(JSON_PAGES_NAME, page.getName())
                        .add(JSON_PAGES_LINK, page.getLink())
                        .build();
                pagesArrayBuilder.add(jsonPage);
            }
        }
       JsonArray pagesArray =  pagesArrayBuilder.build();          
        //*
        //*MEETING TIMES PAGE
        //*
        JsonArray lecturesArray = saveLecturesTable(dataManager);
        JsonArray recitationsArray = saveRecitationsTable(dataManager);
        JsonArray labsArray = saveLabsTable(dataManager);

        //*
        //*OFFICE HOURS
        //*
        // NOW BUILD THE TA JSON OBJCTS TO SAVE        
        JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
        Iterator<TeachingAssistantPrototype> tasIterator = dataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
            JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_TA_NAME, ta.getName())
                    .add(JSON_TA_EMAIL, ta.getEmail())
                    .add(JSON_TYPE, ta.getType().toString()).build();
            if (ta.getType().equals(TAType.Graduate.toString()))
                gradTAsArrayBuilder.add(taJson);
            else
                undergradTAsArrayBuilder.add(taJson);
        }
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
        JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();

        // NOW BUILD THE OFFICE HOURS JSON OBJCTS TO SAVE
        JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = dataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                        .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                        .add(JSON_DAY_OF_WEEK, dow.toString())
                        .add(JSON_OH_NAME, ta.getName()).build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
        }
        JsonArray officeHoursArray = officeHoursArrayBuilder.build();

        //*
        //*SCHEDULE 
        //*
        JsonArrayBuilder schHolidaysArrayBuilder = Json.createArrayBuilder(); 
        JsonArrayBuilder schLecturesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schRecitationsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schReferencesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schHwsArrayBuilder = Json.createArrayBuilder();
        Iterator<SchedulesPrototype> scheduleIterator = dataManager.schedulesIterator();
        while (scheduleIterator.hasNext()) {
            SchedulesPrototype schedule = scheduleIterator.next();
            if (schedule.getType().equals(ScheduleType.Holiday.toString())) {
                JsonObject scheduleJson = Json.createObjectBuilder()
                        .add(JSON_SCHEDULE_MONTH, schedule.getMonth())
                        .add(JSON_SCHEDULE_DAY, schedule.getDay())
                        .add(JSON_SCHEDULE_YEAR, schedule.getYear())
                        .add(JSON_SCHEDULE_TITLE, schedule.getTitle())
                        .add(JSON_SCHEDULE_LINK, schedule.getLink())
                        .build();
                schHolidaysArrayBuilder.add(scheduleJson);
            }
            else {
                 JsonObject scheduleJson = Json.createObjectBuilder()
                        .add(JSON_SCHEDULE_MONTH, schedule.getMonth())
                        .add(JSON_SCHEDULE_DAY, schedule.getDay())
                        .add(JSON_SCHEDULE_YEAR, schedule.getYear())
                        .add(JSON_SCHEDULE_TITLE, schedule.getTitle())
                        .add(JSON_SCHEDULE_TOPIC, schedule.getTopic()) 
                        .add(JSON_SCHEDULE_LINK, schedule.getLink())
                        .build();
                if (schedule.getType().equals(ScheduleType.Lecture.toString())) 
                    schLecturesArrayBuilder.add(scheduleJson);
                else if (schedule.getType().equals(ScheduleType.Recitation.toString())) 
                    schRecitationsArrayBuilder.add(scheduleJson);
                else if (schedule.getType().equals(ScheduleType.Reference.toString()))
                    schReferencesArrayBuilder.add(scheduleJson);
                else if (schedule.getType().equals(ScheduleType.HW.toString()))
                    schHwsArrayBuilder.add(scheduleJson);
            }
        }

        JsonArray schHolidaysArray = schHolidaysArrayBuilder.build();
        JsonArray schLecturesArray = schLecturesArrayBuilder.build();
        JsonArray schRecitationsArray = schRecitationsArrayBuilder.build();
        JsonArray schReferencesArray = schReferencesArrayBuilder.build();
        JsonArray schHwsArray = schHwsArrayBuilder.build();
        // THEN PUT IT ALL TOGETHER IN A JsonObject
        JsonObject dataManagerJSO = Json.createObjectBuilder()
            .add(JSON_SUBJECT, dataManager.getBanner().getSubject())
            .add(JSON_NUMBER, dataManager.getBanner().getNumber())
            .add(JSON_SEMESTER, dataManager.getBanner().getSemester())
            .add(JSON_YEAR, dataManager.getBanner().getYear())
            .add(JSON_YEAR_ITEMS, yearItemsArray)
            .add(JSON_TITLE, dataManager.getBanner().getTitle())
            .add(JSON_LOGOS, Json.createObjectBuilder()
                    .add(JSON_FAVICON, Json.createObjectBuilder()
                            .add(JSON_HREF,  dataManager.getLogList().get("favicon").getHref())
                    )
                    .add(JSON_NAVBAR, Json.createObjectBuilder()
                            .add(JSON_HREF, dataManager.getLogList().get("navbar").getHref())
                            .add(JSON_SRC,  dataManager.getLogList().get("navbar").getSrc())
                    )
                    .add(JSON_BOTTOM_LEFT, Json.createObjectBuilder()
                            .add(JSON_HREF, dataManager.getLogList().get("left_footer").getHref())
                            .add(JSON_SRC, dataManager.getLogList().get("left_footer").getSrc())
                    )
                    .add(JSON_BOTTOM_RIGHT, Json.createObjectBuilder()
                            .add(JSON_HREF, dataManager.getLogList().get("right_footer").getHref())
                            .add(JSON_SRC, dataManager.getLogList().get("right_footer").getSrc())
                    )
            )
            .add(JSON_INSTRUCTOR, Json.createObjectBuilder()
                    .add(JSON_INSTRUCTOR_NAME, dataManager.getInstructor().getName())
                    .add(JSON_INSTRUCTOR_LINK, dataManager.getInstructor().getLink())
                    .add(JSON_INSTRUCTOR_EMAIL, dataManager.getInstructor().getEmail())
                    .add(JSON_INSTRUCTOR_ROOM,  dataManager.getInstructor().getRoom())
                    .add(JSON_INSTRUCTOR_PHOTO, dataManager.getInstructor().getPhotoPath())
                    .add(JSON_INSTRUCTOR_HOURS, dataManager.getInstructor().getHours())
            )
            .add(JSON_PAGES, pagesArray)
            .add(JSON_DESCRIPTION, dataManager.getSyllabusList().get("description"))
            .add(JSON_TOPICS, dataManager.getSyllabusList().get("topics"))
            .add(JSON_PREREQUISITES, dataManager.getSyllabusList().get("prerequisites"))
            .add(JSON_OUTCOMES, dataManager.getSyllabusList().get("outcomes"))
            .add(JSON_TEXTBOOKS, dataManager.getSyllabusList().get("textbooks"))
            .add(JSON_GRADED_COMPONENTS, dataManager.getSyllabusList().get("gradedComponents"))
            .add(JSON_GRADING_NOTE, dataManager.getSyllabusList().get("gradingNote")) 
            .add(JSON_ACADEMIC_DISHONESTY, dataManager.getSyllabusList().get("academicDishonesty"))
            .add(JSON_SPECIAL_ASSISTANCE, dataManager.getSyllabusList().get("specialAssistance"))
            .add(JSON_LECTURES, lecturesArray)
            .add(JSON_LABS, labsArray)
            .add(JSON_RECITATIONS, recitationsArray)
            .add(JSON_START_HOUR, "" + dataManager.getStartHour())
            .add(JSON_END_HOUR, "" + dataManager.getEndHour())
            .add(JSON_GRAD_TAS, gradTAsArray)
            .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
            .add(JSON_OFFICE_HOURS, officeHoursArray)
            .add(JSON_STARTING_MONDAY_MONTH, "" + dataManager.getStartingMondayMonth())
            .add(JSON_STARTING_MONDAY_DAY, "" + dataManager.getStartingMondayDay())
            .add(JSON_STARTING_MONDAY_YEAR, "" + dataManager.getStartingMondayYear())
            .add(JSON_ENDING_FRIDAY_MONTH, "" + dataManager.getEndingFridayMonth())
            .add(JSON_ENDING_FRIDAY_DAY, "" + dataManager.getEndingFridayDay())
            .add(JSON_ENDING_FRIDAY_YEAR, "" + dataManager.getEndingFridayYear())
            .add(JSON_SCHEDULE_HOLIDAYS, schHolidaysArray)
            .add(JSON_SCHEDULE_LECTURES, schLecturesArray)
            .add(JSON_SCHEDULE_REFERENCES, schReferencesArray)
            .add(JSON_SCHEDULE_RECITATIONS, schRecitationsArray)
            .add(JSON_SCHEDULE_HWS, schHwsArray)
            .build();  
        exportJSonFile(dataManagerJSO, filePath);
    }
    
    private JsonArray saveLecturesTable(CourseSiteGeneratorData dataManager) {
         JsonArrayBuilder lecturesArrayBuilder = Json.createArrayBuilder();
         Iterator<MeetingTimesPrototype> lecturesIterator = dataManager.lecturesIterator();
          while (lecturesIterator.hasNext()) {
            MeetingTimesPrototype lecture = lecturesIterator.next();
            JsonObject jsonLecture = Json.createObjectBuilder()
                    .add(JSON_SECTION, lecture.getSection())
                    .add(JSON_DAYS, lecture.getDays())
                    .add(JSON_TIME, lecture.getTime())
                    .add(JSON_ROOM, lecture.getRoom())
                    .build();
            lecturesArrayBuilder.add(jsonLecture);
        }
        JsonArray lecturesArray = lecturesArrayBuilder.build();
        return lecturesArray;
    }
    
     private JsonArray saveRecitationsTable(CourseSiteGeneratorData dataManager) {
          JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();
          Iterator<MeetingTimesPrototype> recitationsIterator = dataManager.recitationsIterator();
          while (recitationsIterator.hasNext()) {
            MeetingTimesPrototype recitation = recitationsIterator.next();
            JsonObject jsonRecitation = Json.createObjectBuilder()
                    .add(JSON_SECTION, "" + recitation.getSection())
                    .add(JSON_DAY_TIME, "" + recitation.getDaysAndTime())
                    .add(JSON_LOCATION, "" + recitation.getRoom())
                    .add(JSON_TA1, "" + recitation.getTa1())
                    .add(JSON_TA2, "" + recitation.getTa2())
                    .build();
            recitationsArrayBuilder.add(jsonRecitation);
        }
          JsonArray recitationsArray = recitationsArrayBuilder.build();
          return recitationsArray;
     }
     
     private JsonArray saveLabsTable(CourseSiteGeneratorData dataManager) {
         JsonArrayBuilder labsArrayBuilder = Json.createArrayBuilder();
         Iterator<MeetingTimesPrototype> labsIterator = dataManager.labsIterator();
        while (labsIterator.hasNext()) {
            MeetingTimesPrototype lab = labsIterator.next();
            JsonObject jsonLab = Json.createObjectBuilder()
                    .add(JSON_SECTION, "" + lab.getSection())
                    .add(JSON_DAY_TIME, "" + lab.getDaysAndTime())
                    .add(JSON_LOCATION, "" + lab.getRoom())
                    .add(JSON_TA1, "" + lab.getTa1())
                    .add(JSON_TA2, "" + lab.getTa2())
                    .build();
            labsArrayBuilder.add(jsonLab);
        }
        JsonArray labsArray = labsArrayBuilder.build();
        return labsArray;
     }
     
     
    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws NoPageSelectedException, IOException{
        // GET THE DATA
        CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        //*
        //*SITE PAGE
        //*       
        ArrayList<Page> pageList = dataManager.getPageList();
        JsonArrayBuilder pagesArrayBuilder = Json.createArrayBuilder();
        int index = 0;
        boolean isEmpty = true;
        boolean isFirstPage = false;
        for (int i = 0; i < pageList.size(); i++) {
            if (pageList.get(i) != null) {
                if (!isFirstPage) { // GET THE INDEX OF THE FIRST SELECTED PAGE 
                    isFirstPage = true;
                    index = i;
                }
                isEmpty = false;
                JsonObject jsonPage = Json.createObjectBuilder()
                        .add(JSON_NAME, pageList.get(i).getName())
                        .add(JSON_LINK, pageList.get(i).getLink())
                        .build();
                pagesArrayBuilder.add(jsonPage);
            }
        }
        //CHECK IF AT LEAST ONE CHECK BOX HAS BEEN SELECTED
        //IF THERE IS NO CHECK BOX SELECTED, THROW EXCEPTION.
        if (isEmpty)
            throw new NoPageSelectedException();
        String exportPage = pageList.get(index).getLink();
        props.setProperty(APP_EXPORT_PAGE, exportPage);
        JsonArray pagesArray =  pagesArrayBuilder.build();
        String ohHours = dataManager.getInstructor().getHours();
        JsonReader instructorOHReader = Json.createReader(new StringReader(ohHours));
        JsonReader instructorOHReader2 = Json.createReader(new StringReader(ohHours));
        JsonArray instructorOHJsonArray = instructorOHReader.readArray();
        JsonArray instructorOHJsonArray2 = instructorOHReader2.readArray();
        instructorOHReader.close();
        instructorOHReader2.close();
        //*
        //*SYLLABUS
        //*
        String topics = dataManager.getSyllabusList().get("topics");
        String outcomes = dataManager.getSyllabusList().get("outcomes");
        String textbooks = dataManager.getSyllabusList().get("textbooks");
        String gradedC = dataManager.getSyllabusList().get("gradedComponents");
        JsonReader topicsReader = Json.createReader(new StringReader(topics));
        JsonReader outcomesReader = Json.createReader(new StringReader(outcomes));
        JsonReader textbooksReader = Json.createReader(new StringReader(textbooks));
        JsonReader gradedCReader = Json.createReader(new StringReader(gradedC));
        JsonArray topicsJsonArray = topicsReader.readArray();
        JsonArray outcomesJsonArray = outcomesReader.readArray();
        JsonArray textbooksJsonArray = textbooksReader.readArray();
        JsonArray gradedCJsonArray = gradedCReader.readArray();
        topicsReader.close();
        outcomesReader.close();
        textbooksReader.close();
        gradedCReader.close();

        //*
        //*MEETING TIMES PAGE
        //*
        JsonArray lecturesArray = saveLecturesTable(dataManager);
        JsonArray recitationsArray = saveRecitationsTable(dataManager);
        JsonArray labsArray = saveLabsTable(dataManager);

        //*
        //*OFFICE HOURS
        //*
        // NOW BUILD THE TA JSON OBJCTS TO SAVE
        JsonArrayBuilder gradTAsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder undergradTAsArrayBuilder = Json.createArrayBuilder();
        Iterator<TeachingAssistantPrototype> tasIterator = dataManager.teachingAssistantsIterator();
        while (tasIterator.hasNext()) {
            TeachingAssistantPrototype ta = tasIterator.next();
            JsonObject taJson = Json.createObjectBuilder()
                    .add(JSON_NAME, ta.getName())
                    .add(JSON_EMAIL, ta.getEmail())
                    .build();
            if (ta.getType().equals(TAType.Graduate.toString()))
                gradTAsArrayBuilder.add(taJson);
            else
                undergradTAsArrayBuilder.add(taJson);
        }
        JsonArray gradTAsArray = gradTAsArrayBuilder.build();
        JsonArray undergradTAsArray = undergradTAsArrayBuilder.build();

        // NOW BUILD THE OFFICE HOURS JSON OBJCTS TO SAVE
        JsonArrayBuilder officeHoursArrayBuilder = Json.createArrayBuilder();
        Iterator<TimeSlot> timeSlotsIterator = dataManager.officeHoursIterator();
        while (timeSlotsIterator.hasNext()) {
            TimeSlot timeSlot = timeSlotsIterator.next();
            for (int i = 0; i < DayOfWeek.values().length; i++) {
                DayOfWeek dow = DayOfWeek.values()[i];
                tasIterator = timeSlot.getTAsIterator(dow);
                while (tasIterator.hasNext()) {
                    TeachingAssistantPrototype ta = tasIterator.next();
                    JsonObject tsJson = Json.createObjectBuilder()
                            .add(JSON_DAY_OF_WEEK, dow.toString())
                            .add(JSON_START_TIME, timeSlot.getStartTime().replace(":", "_"))
                            .add(JSON_NAME, ta.getName()).build();
                    officeHoursArrayBuilder.add(tsJson);
                }
            }
        }
        JsonArray officeHoursArray = officeHoursArrayBuilder.build();

        //*
        //*SCHEDULE
        //*
        JsonArrayBuilder schHolidaysArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schLecturesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schRecitationsArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schReferencesArrayBuilder = Json.createArrayBuilder();
        JsonArrayBuilder schHwsArrayBuilder = Json.createArrayBuilder();
        Iterator<SchedulesPrototype> scheduleIterator = dataManager.schedulesIterator();
        while (scheduleIterator.hasNext()) {
            SchedulesPrototype schedule = scheduleIterator.next();
            if (schedule.getType().equals(ScheduleType.Holiday.toString())) {
                JsonObject scheduleJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, schedule.getMonth())
                        .add(JSON_DAY, schedule.getDay())
                        .add(JSON_TITLE, schedule.getTitle())
                        .add(JSON_LINK, schedule.getLink())
                        .build();
                schHolidaysArrayBuilder.add(scheduleJson);
            }
            else {
                JsonObject scheduleJson = Json.createObjectBuilder()
                        .add(JSON_MONTH, schedule.getMonth())
                        .add(JSON_DAY, schedule.getDay())
                        .add(JSON_TITLE, schedule.getTitle())
                        .add(JSON_TOPIC, schedule.getTopic())
                        .add(JSON_LINK, schedule.getLink())
                        .build();
                if (schedule.getType().equals(ScheduleType.Lecture.toString()))
                    schLecturesArrayBuilder.add(scheduleJson);
                else if (schedule.getType().equals(ScheduleType.Recitation.toString()))
                    schRecitationsArrayBuilder.add(scheduleJson);
                else if (schedule.getType().equals(ScheduleType.Reference.toString()))
                    schReferencesArrayBuilder.add(scheduleJson);
                else if (schedule.getType().equals(ScheduleType.HW.toString()))
                    schHwsArrayBuilder.add(scheduleJson);
            }
        }

        JsonArray schHolidaysArray = schHolidaysArrayBuilder.build();
        JsonArray schLecturesArray = schLecturesArrayBuilder.build();
        JsonArray schRecitationsArray = schRecitationsArrayBuilder.build();
        JsonArray schReferencesArray = schReferencesArrayBuilder.build();
        JsonArray schHwsArray = schHwsArrayBuilder.build();
        // THEN PUT IT ALL TOGETHER IN A JsonObject
        JsonObject pageDataJSO = Json.createObjectBuilder()
                .add(JSON_SUBJECT, dataManager.getBanner().getSubject())
                .add(JSON_NUMBER, "" + dataManager.getBanner().getNumber())
                .add(JSON_SEMESTER, "" + dataManager.getBanner().getSemester())
                .add(JSON_YEAR, "" + dataManager.getBanner().getYear())
                .add(JSON_TITLE, dataManager.getBanner().getTitle())
                .add(JSON_LOGOS, Json.createObjectBuilder()
                        .add(JSON_FAVICON, Json.createObjectBuilder()
                                .add(JSON_HREF, dataManager.getLogList().get("favicon").getHref())
                        )
                        .add(JSON_NAVBAR, Json.createObjectBuilder()
                                .add(JSON_HREF, dataManager.getLogList().get("navbar").getHref())
                                .add(JSON_SRC,  dataManager.getLogList().get("navbar").getSrc())
                        )
                        .add(JSON_BOTTOM_LEFT, Json.createObjectBuilder()
                                .add(JSON_HREF,  dataManager.getLogList().get("left_footer").getHref())
                                .add(JSON_SRC, dataManager.getLogList().get("left_footer").getSrc())
                        )
                        .add(JSON_BOTTOM_RIGHT, Json.createObjectBuilder()
                                .add(JSON_HREF, dataManager.getLogList().get("right_footer").getHref())
                                .add(JSON_SRC, dataManager.getLogList().get("right_footer").getSrc())
                        )
                )
                .add(JSON_INSTRUCTOR, Json.createObjectBuilder()
                        .add(JSON_NAME, dataManager.getInstructor().getName())
                        .add(JSON_LINK, dataManager.getInstructor().getLink())
                        .add(JSON_EMAIL, dataManager.getInstructor().getEmail())
                        .add(JSON_ROOM,  dataManager.getInstructor().getRoom())
                        .add(JSON_PHOTO, dataManager.getInstructor().getPhotoPath())
                        .add(JSON_HOURS, instructorOHJsonArray)
                )
                .add(JSON_PAGES, pagesArray)
                .build();
        JsonObject syllabusDataJSO = Json.createObjectBuilder()
                .add(JSON_DESCRIPTION, dataManager.getSyllabusList().get("description"))
                .add(JSON_TOPICS, topicsJsonArray)
                .add(JSON_PREREQUISITES, dataManager.getSyllabusList().get("prerequisites"))
                .add(JSON_OUTCOMES, outcomesJsonArray)
                .add(JSON_TEXTBOOKS, textbooksJsonArray)
                .add(JSON_GRADED_COMPONENTS, gradedCJsonArray)
                .add(JSON_GRADING_NOTE, dataManager.getSyllabusList().get("gradingNote"))
                .add(JSON_ACADEMIC_DISHONESTY, dataManager.getSyllabusList().get("academicDishonesty"))
                .add(JSON_SPECIAL_ASSISTANCE, dataManager.getSyllabusList().get("specialAssistance"))
                .build();
        JsonObject sectionsDataJSO = Json.createObjectBuilder()
                .add(JSON_LECTURES, lecturesArray)
                .add(JSON_LABS, labsArray)
                .add(JSON_RECITATIONS, recitationsArray)
                .build();
        JsonObject officeHoursDataJSO = Json.createObjectBuilder()
                .add(JSON_START_HOUR, "" + dataManager.getStartHour())
                .add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_INSTRUCTOR, instructorOHJsonArray)
                .add(JSON_GRAD_TAS, gradTAsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, officeHoursArray)
                .add(JSON_INSTRUCTOR, Json.createObjectBuilder()
                        .add(JSON_NAME, dataManager.getInstructor().getName())
                        .add(JSON_LINK, dataManager.getInstructor().getLink())
                        .add(JSON_EMAIL, dataManager.getInstructor().getEmail())
                        .add(JSON_ROOM,  dataManager.getInstructor().getRoom())
                        .add(JSON_PHOTO, dataManager.getInstructor().getPhotoPath())
                        .add(JSON_HOURS, instructorOHJsonArray2)
                )
                .build();
        JsonObject scheduleDataJSO = Json.createObjectBuilder()
                .add(JSON_STARTING_MONDAY_MONTH, "" + dataManager.getStartingMondayMonth())
                .add(JSON_STARTING_MONDAY_DAY, "" + dataManager.getStartingMondayDay())
                .add(JSON_ENDING_FRIDAY_MONTH, "" + dataManager.getEndingFridayMonth())
                .add(JSON_ENDING_FRIDAY_DAY, "" + dataManager.getEndingFridayDay())
                .add(JSON_HOLIDAYS, schHolidaysArray)
                .add(JSON_LECTURES, schLecturesArray)
                .add(JSON_REFERENCES, schReferencesArray)
                .add(JSON_RECITATIONS, schRecitationsArray)
                .add(JSON_HWS, schHwsArray)
                .build();
        exportJSonFile(pageDataJSO, "./app_data/exportData/js/PageData.json");
        exportJSonFile(syllabusDataJSO, "./app_data/exportData/js/SyllabusData.json");
        exportJSonFile(sectionsDataJSO, "./app_data/exportData/js/SectionsData.json");
        exportJSonFile(officeHoursDataJSO, "./app_data/exportData/js/OfficeHoursData.json");
        exportJSonFile(scheduleDataJSO, "./app_data/exportData/js/ScheduleData.json");
        copyFiles(dataManager);
    }
    
    private void isPageListEmpty(CourseSiteGeneratorData data) throws NoPageSelectedException {
        
    }
    
    private void copyFiles(CourseSiteGeneratorData data) throws IOException {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        AppGUIModule gui = app.getGUIModule();
        Label dirLabel = (Label)gui.getGUINode(CSG_SITE_EXPORT_DIR_PATH);
        String dir = dirLabel.getText();
        String styleSheet = data.getStyleSheet();
        int indexOfSecondSlash = dir.indexOf("\\", dir.indexOf("\\") + 2);
        String fileName = dir.substring(indexOfSecondSlash + 1, dir.indexOf("\\", indexOfSecondSlash + 2));
        String imagesBase = props.getProperty(APP_PATH_IMAGES);
        String exportBase = props.getProperty(APP_PATH_EXPORT);
        String styleSheetBase = props.getProperty(APP_PATH_STYLE_SHEET);
        String app_DataBase = props.getProperty(APP_PATH_APP_DATA);
        String cssBase = props.getProperty(APP_PATH_LOCAL_CSS); 
        String exportDataBase = props.getProperty(APP_PATH_EXPORT_DATA);
        String exportDataImagesBase = props.getProperty(APP_PATH_EXPORT_DATA_IMAGES);
        String exportDataCSSBase = props.getProperty(APP_PATH_EXPORT_DATA_CSS);
        //DELET ALL IMAGES FILES IN EXPORTDATA 
        FileUtils.deleteDirectory(new File(exportDataImagesBase));
        //COPY IMAGES TO EXPORTDATA FOLDER
        String faviconRelativePath = data.getLogList().get("favicon").getHref();
        String navbarRelativePath = data.getLogList().get("navbar").getSrc();
        String bottomLeftRelativePath = data.getLogList().get("left_footer").getSrc();
        String bottomRightRelativePath = data.getLogList().get("right_footer").getSrc();
        FileUtils.copyFile(new File(relativeToAbsolute(faviconRelativePath, imagesBase)), 
                new File(exportDataImagesBase + getFileName(faviconRelativePath)));
        FileUtils.copyFile(new File(relativeToAbsolute(navbarRelativePath, imagesBase)), 
                new File(exportDataImagesBase + getFileName(navbarRelativePath)));
        FileUtils.copyFile(new File(relativeToAbsolute(bottomLeftRelativePath, imagesBase)), 
                new File(exportDataImagesBase + getFileName(bottomLeftRelativePath)));
        FileUtils.copyFile(new File(relativeToAbsolute(bottomRightRelativePath, imagesBase)), 
                new File(exportDataImagesBase + getFileName(bottomRightRelativePath)));
        //DELET ALL CSS FILES IN EXPORTDATA 
        FileUtils.deleteDirectory(new File(exportDataCSSBase));
        //COPY HOMEPAGE CSS FILE TO EXPORTDATA CSS FOLDER
        FileUtils.copyFile(new File(relativeToAbsolute("/homepageStyleSheet/" + "course_homepage_layout.css", styleSheetBase)), 
                new File(relativeToAbsolute("/css/" + "course_homepage_layout.css", exportDataCSSBase)));
        //COPY SELECTED CSS FILE TO EXPORTDATA CSS FOLDER
        FileUtils.copyFile(new File(relativeToAbsolute("/styleSheet/" + styleSheet, styleSheetBase)), 
                new File(relativeToAbsolute("/css/" + styleSheet, exportDataCSSBase)));
        //EDIT HTML CSS LINK TO SELECTED CSS FILE
        String indexPath = exportDataBase + "index.html";
        String syllabusPath = exportDataBase + "syllabus.html";
        String hwsPath = exportDataBase + "hws.html";
        String schedulePath = exportDataBase + "schedule.html";
        Document indexDoc = Jsoup.parse(new File(indexPath), "utf-8");
        Document syllabusDoc = Jsoup.parse(new File(syllabusPath), "utf-8");
        Document hwsDoc = Jsoup.parse(new File(hwsPath), "utf-8");
        Document scheduleDoc = Jsoup.parse(new File(schedulePath), "utf-8");
        Elements indexLinks = indexDoc.select("link[href]");
        Elements syllabusLinks = syllabusDoc.select("link[href]");
        Elements hwsLinks = hwsDoc.select("link[href]");
        Elements scheduleLinks = scheduleDoc.select("link[href]");
        for (Element link : indexLinks) {
            if (link.attr("href").equals("./css/course_homepage_layout.css")) {}
            else
              link.attr("href", cssBase + styleSheet);
        }
        for (Element link : syllabusLinks) {
            if (link.attr("href").equals("./css/course_homepage_layout.css")) {}
            else
              link.attr("href", cssBase + styleSheet);
        }
        for (Element link : hwsLinks) {
            if (link.attr("href").equals("./css/course_homepage_layout.css")) {}
            else
              link.attr("href", cssBase + styleSheet);
        }
        for (Element link : scheduleLinks) {
            if (link.attr("href").equals("./css/course_homepage_layout.css")) {}
            else
              link.attr("href", cssBase + styleSheet);
        }
        FileUtils.writeStringToFile(new File(indexPath), indexDoc.outerHtml(), "UTF-8");
        FileUtils.writeStringToFile(new File(syllabusPath), syllabusDoc.outerHtml(), "UTF-8");
        FileUtils.writeStringToFile(new File(hwsPath), hwsDoc.outerHtml(), "UTF-8");
        FileUtils.writeStringToFile(new File(schedulePath), scheduleDoc.outerHtml(), "UTF-8");
        //COPY ALL EXPORTDATA FILES TO EXPORT FOLDER
        String source = relativeToAbsolute(exportDataBase, app_DataBase);
        File srcDir = new File(source);

        String destination = exportBase + fileName + "/";
        File destDir = new File(destination);

        FileUtils.copyDirectory(srcDir, destDir);
    }
    
    private String getFileName(String filePath) {
        int indexOfLastSlash = filePath.lastIndexOf("/");
        if (indexOfLastSlash < 0)
            indexOfLastSlash = filePath.lastIndexOf("\\");
        return filePath.substring(indexOfLastSlash + 1);
    }
    private void exportJSonFile(JsonObject dataManagerJSO, String filePath) throws IOException {
        // AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
        Map<String, Object> properties = new HashMap<>(1);
        properties.put(JsonGenerator.PRETTY_PRINTING, true);
        JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = writerFactory.createWriter(sw);
        jsonWriter.writeObject(dataManagerJSO);
        jsonWriter.close();

        // INIT THE WRITER
        OutputStream os = new FileOutputStream(filePath);
        JsonWriter jsonFileWriter = Json.createWriter(os);
        jsonFileWriter.writeObject(dataManagerJSO);
        String prettyPrinted = sw.toString();
        PrintWriter pw = new PrintWriter(filePath);
        pw.write(prettyPrinted);
        pw.close();
    }
}