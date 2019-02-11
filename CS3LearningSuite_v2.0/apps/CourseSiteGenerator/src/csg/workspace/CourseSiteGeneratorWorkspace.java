package csg.workspace;

import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import djf.ui.AppNodesBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import properties_manager.PropertiesManager;
import csg.CourseSiteGeneratorApp;
import static csg.CourseSiteGeneratorPropertyType.*;
import csg.data.MeetingTimesPrototype;
import csg.data.MyImage;
import csg.data.TeachingAssistantPrototype;
import csg.workspace.controllers.CourseSiteGeneratorController;
import csg.workspace.dialogs.TADialog;
import csg.workspace.foolproof.CourseSiteGeneratorFoolproofDesign;
import static csg.workspace.style.CSGStyle.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Jie Dai
 */
public class CourseSiteGeneratorWorkspace extends AppWorkspaceComponent {

    private String oldValueOfTitile;
    private String oldValueOfName;
    private String oldValueOfRoom;
    private String oldValueOfEmail;
    private String oldValueOfHomePage;
    private String oldValueOfInsturctorOH;
    private String oldValueOfDes;
    private String oldValueOfPre;
    private String oldValueOfTop;
    private String oldValueOfOut;
    private String oldValueOfTex;
    private String oldValueOfGc;
    private String oldValueOfGn;
    private String oldValueOfAd;
    private String oldValueOfSa;
    private static final String JSON_SUBJECT = "subject";
    private static final String JSON_NUMBER = "number";
    private static final String JSON_SEMESTER = "semester";
    private static final String JSON_YEAR = "year";
    private static final String JSON_ITEM = "item";
    private static final String CSS_EXTENSION = "css";
    public CourseSiteGeneratorWorkspace(CourseSiteGeneratorApp app) {
        super(app);

        // LAYOUT THE APP
        initLayout();

        // INIT THE EVENT HANDLERS
        initControllers();

        // 
        initFoolproofDesign();

        // INIT DIALOGS
        initDialogs();
    }

    private void initDialogs() {
        TADialog taDialog = new TADialog((CourseSiteGeneratorApp) app);
        app.getGUIModule().addDialog(OH_TA_EDIT_DIALOG, taDialog);
    }

    // THIS HELPER METHOD INITIALIZES ALL THE CONTROLS IN THE WORKSPACE
    private void initLayout() {
        AppGUIModule gui = app.getGUIModule();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder ohBuilder = app.getGUIModule().getNodesBuilder();
        TabPane tabPane = ohBuilder.buildTabPane(CSG_TABPANE, null, CLASS_CSG_TAB_PANE, ENABLED);
        Tab siteTab = new Tab("Site");
        Tab syllabusTab = new Tab("Syllabus");
        Tab mtsTab = new Tab("Meeting Times");
        Tab ohTab = new Tab("Office Hours");
        Tab scheduleTab = new Tab("Schedule");

        siteTab.getStyleClass().add(CLASS_CSG_TABS);
        syllabusTab.getStyleClass().add(CLASS_CSG_TABS);
        mtsTab.getStyleClass().add(CLASS_CSG_TABS);
        ohTab.getStyleClass().add(CLASS_CSG_TABS);
        scheduleTab.getStyleClass().add(CLASS_CSG_TABS);
        siteTab.setClosable(false);
        syllabusTab.setClosable(false);
        mtsTab.setClosable(false);
        ohTab.setClosable(false);
        scheduleTab.setClosable(false);
        tabPane.getTabs().add(siteTab);
        tabPane.getTabs().add(syllabusTab);
        tabPane.getTabs().add(mtsTab);
        tabPane.getTabs().add(ohTab);
        tabPane.getTabs().add(scheduleTab);
        Stage stage = gui.getWindow();
        tabPane.tabMaxWidthProperty().bind(stage.widthProperty().multiply(1.0 / 6.0));
        tabPane.tabMinWidthProperty().bind(stage.widthProperty().multiply(1.0 / 6.0));
        /**
        *******************************************
        *
        *
        *SITE PANE
        *
        *
        *******************************************
        */
        ScrollPane siteScrollPane = new ScrollPane();

        VBox sitePane = ohBuilder.buildVBox(CSG_SITE_PANE, null, CLASS_CSG_MAIN_PANE, ENABLED);
        siteScrollPane.setContent(sitePane);
        sitePane.prefWidthProperty().bind(stage.widthProperty());
        siteScrollPane.setFitToWidth(ENABLED);
        //
        //Banner Pane
        //
        VBox bannerPane = ohBuilder.buildVBox(CSG_SITE_BANNER_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
        HBox bannerHeaderPane = ohBuilder.buildHBox(CSG_SITE_BANNER_HEADER_PANE, bannerPane, CLASS_CSG_BOX, ENABLED);
        GridPane bannerGridPane = ohBuilder.buildGridPane(CSG_SITE_BANNER_GRID_PANE, bannerPane, CLASS_CSG_BANNER_GRID_PANE, ENABLED);

        ohBuilder.buildLabel(CSG_SITE_BANNER_HEADER_LABEL, bannerHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_SUBJECT_LABEL, bannerGridPane, 0, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_NUMBER_LABEL, bannerGridPane, 2, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_SEMESTER_LABEL, bannerGridPane, 0, 1, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_YEAR_LABEL, bannerGridPane, 2, 1, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_TITLE_LABEL, bannerGridPane, 0, 2, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_EXPORT_DIR_LABEL, bannerGridPane, 0, 3, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        Label exportDir = ohBuilder.buildLabel(CSG_SITE_EXPORT_DIR_PATH, bannerGridPane, 1, 3, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);

        ComboBox subjectBox = ohBuilder.buildComboBox(CSG_SITE_SUBJECT_COMBO_BOX, bannerGridPane, 1, 0, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED,
                "", "");
        ComboBox numberBox = ohBuilder.buildComboBox(CSG_SITE_NUMBER_COMBO_BOX, bannerGridPane, 3, 0, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED,
                "", "");
        ComboBox semesterBox = ohBuilder.buildComboBox(CSG_SITE_SEMESTER_COMBO_BOX, bannerGridPane, 1, 1, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED,
                CSG_SITE_SEMESTER_OPTIONS, CSG_SITE_SEMESTER_DEFAULT_OPTION);
        ComboBox yearBox = ohBuilder.buildComboBox(CSG_SITE_YEAR_COMBO_BOX, bannerGridPane, 3, 1, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED,
                "", "");
        ohBuilder.buildTextField(CSG_SITE_TITLE_TEXT_FIELD, bannerGridPane, 1, 2, 1, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
        //INITIAL SUBJECT, NUMBER, AND YEAR COMBOBOXES
        initialBannerPane(subjectBox, props, numberBox, semesterBox, yearBox, exportDir);

        //
        //Pages Pane
        //
        VBox pagesPane = ohBuilder.buildVBox(CSG_SITE_PAGES_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
        HBox pagesHeaderPane = ohBuilder.buildHBox(CSG_SITE_PAGES_HEADER_PANE, pagesPane, CLASS_CSG_BOX, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_PAGES_HEADER_LABEL, pagesHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        GridPane checkBoxPane = ohBuilder.buildGridPane(CSG_SITE_CHECK_BOX_GRID_PANE, pagesHeaderPane, CLASS_CSG_SITE_CHECK_BOX_GRID_PANE, ENABLED);
        ohBuilder.buildCheckBox(CSG_SITE_PAGES_HOME_CHECK_BOX, checkBoxPane, 0, 0, 1, 1, CLASS_CSG_SITE_PAGES_CHECK_BOX, ENABLED);
        ohBuilder.buildCheckBox(CSG_SITE_PAGES_SYLLABUS_CHECK_BOX, checkBoxPane, 1, 0, 1, 1, CLASS_CSG_SITE_PAGES_CHECK_BOX, ENABLED);
        ohBuilder.buildCheckBox(CSG_SITE_PAGES_SCHEDULE_CHECK_BOX, checkBoxPane, 2, 0, 1, 1, CLASS_CSG_SITE_PAGES_CHECK_BOX, ENABLED);
        ohBuilder.buildCheckBox(CSG_SITE_PAGES_HWS_CHECK_BOX, checkBoxPane, 3, 0, 1, 1, CLASS_CSG_SITE_PAGES_CHECK_BOX, ENABLED);

        //
        //Style Pane
        //
        VBox stylePane = ohBuilder.buildVBox(CSG_SITE_STYLE_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
        HBox styleHeaderPane = ohBuilder.buildHBox(CSG_SITE_STYLE_HEADER_PANE, stylePane, CLASS_CSG_BOX, ENABLED);
        GridPane styleGridPane = ohBuilder.buildGridPane(CSG_SITE_STYLE_PANE, stylePane, CLASS_CSG_STYLE_GRID_PANE, ENABLED);
        HBox styleSheetPane = ohBuilder.buildHBox(CSG_SITE_STYLE_SHEET_PANE, stylePane, CLASS_CSG_BOX, ENABLED);
        HBox notePane = ohBuilder.buildHBox(CSG_SITE_STYLE_NOTE_PANE, stylePane, CLASS_CSG_BOX, ENABLED);

        ohBuilder.buildLabel(CSG_SITE_STYLE_HEADER_LABEL, styleHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_STYLE_SHEET_LABEL, styleSheetPane, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_NOTE_LABEL, notePane, CLASS_CSG_TEXT_LABEL, ENABLED);

        ohBuilder.buildTextButton(CSG_SITE_STYLE_FAVICON_BUTTON, styleGridPane, 0, 1, 1, 1, CLASS_CSG_IMPORT_IMAGE_BUTTON, ENABLED);
        ohBuilder.buildTextButton(CSG_SITE_STYLE_NAVBAR_BUTTON, styleGridPane, 0, 2, 1, 1, CLASS_CSG_IMPORT_IMAGE_BUTTON, ENABLED);
        ohBuilder.buildTextButton(CSG_SITE_STYLE_LEFTE_FOOTER_BUTTON, styleGridPane, 0, 3, 1, 1, CLASS_CSG_IMPORT_IMAGE_BUTTON, ENABLED);
        ohBuilder.buildTextButton(CSG_SITE_STYLE_RIGHT_FOOTER_BUTTON, styleGridPane, 0, 4, 1, 1, CLASS_CSG_IMPORT_IMAGE_BUTTON, ENABLED);

        ImageView faviconImage = ohBuilder.buildImageView(CSG_SITE_STYLE_FAVICON_IMAGE_VIEW, styleGridPane, 1, 1, 1, 1, CLASS_CSG_SITE_IMAGE_VIEW, ENABLED);
        ImageView navbarImage = ohBuilder.buildImageView(CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW, styleGridPane, 1, 2, 1, 1, CLASS_CSG_SITE_IMAGE_VIEW, ENABLED);
        ImageView leftImage = ohBuilder.buildImageView(CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW, styleGridPane, 1, 3, 1, 1, CLASS_CSG_SITE_IMAGE_VIEW, ENABLED);
        ImageView rightImage = ohBuilder.buildImageView(CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW, styleGridPane, 1, 4, 1, 1, CLASS_CSG_SITE_IMAGE_VIEW, ENABLED);

        ComboBox styleSheetBox = ohBuilder.buildComboBox(CSG_SITE_STYLE_SHEET_COMBO_BOX, "",
                "", styleSheetPane, CLASS_CSG_COMBO_BOX, ENABLED);
        initialStyleSheetBox(props, styleSheetBox);
        faviconImage.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_FAVICON_DEFAULTE_IMAGE_PATH)));
        navbarImage.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_NAVBAR_DEFAULTE_IMAGE_PATH)));
        leftImage.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_LEFTE_FOOTER_DEFAULTE_IMAGE_PATH)));
        rightImage.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_RIGHT_FOOTER_DEFAULTE_IMAGE_PATH)));
        faviconImage.setFitWidth(60);
        faviconImage.setFitHeight(60);
        navbarImage.setFitWidth(200);
        navbarImage.setFitHeight(40);
        leftImage.setFitWidth(200);
        leftImage.setFitHeight(40);
        rightImage.setFitWidth(200);
        rightImage.setFitHeight(40);

        //
        //Instructor Pane
        //
        VBox instructorPane = ohBuilder.buildVBox(CSG_SITE_INSTRUCTOR_PANE, sitePane, CLASS_CSG_PANE, ENABLED);
        HBox instructorHeaderPane = ohBuilder.buildHBox(CSG_SITE_INSTRUCTOR_HEADER_PANE, instructorPane, CLASS_CSG_BOX, ENABLED);
        GridPane instructorGridPane = ohBuilder.buildGridPane(CSG_SITE_INSTRUCTOR_GRID_PANE, instructorPane, CLASS_CSG_INSTRUCTOR_GRID_PANE, ENABLED);

        ohBuilder.buildLabel(CSG_SITE_INSTRUCTOR_HEADER_LABEL, instructorHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_NAME_LABEL, instructorGridPane, 0, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_ROOM_LABEL, instructorGridPane, 2, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_EMAIL_LABEL, instructorGridPane, 0, 1, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_HOME_PAGE_LABEL, instructorGridPane, 2, 1, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);

        ohBuilder.buildTextField(CSG_SITE_NAME_TEXT_FIELD, instructorGridPane, 1, 0, 1, 1, CLASS_CSG_INSTRUCTOR_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(CSG_SITE_ROOM_TEXT_FIELD, instructorGridPane, 3, 0, 1, 1, CLASS_CSG_INSTRUCTOR_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(CSG_SITE_EMAIL_TEXT_FIELD, instructorGridPane, 1, 1, 1, 1, CLASS_CSG_INSTRUCTOR_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(CSG_SITE_HOME_PAGE_TEXT_FIELD, instructorGridPane, 3, 1, 1, 1, CLASS_CSG_INSTRUCTOR_TEXT_FIELD, ENABLED);

        HBox siteOHLabelPane = ohBuilder.buildHBox(CSG_SITE_OFFICE_HOURS_LABEL_PANE, instructorPane, CLASS_CSG_BOX, ENABLED);
        Button siteOHTextButton = ohBuilder.buildTextButton(CSG_SITE_OFFICE_HOURS_BUTTON, siteOHLabelPane, CLASS_CSG_BUTTON, ENABLED);
        ohBuilder.buildLabel(CSG_SITE_OFFICE_HOURS_LABEL, siteOHLabelPane, CLASS_CSG_TEXT_LABEL, ENABLED);
        siteOHTextButton.setText("-");
        HBox ohTextAreaPane = ohBuilder.buildHBox(CSG_SITE_OFFICE_HOURS_PANE, instructorPane, CLASS_CSG_BOX, ENABLED);
        TextArea ohTextArea = ohBuilder.buildTextArea(CSG_SITE_OFFICE_HOURS_TEXT_AREA, ohTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        ohTextArea.prefWidthProperty().bind(stage.widthProperty());        
        /**
        *******************************************
        *
        *
        *SYLLABUSS PANE
        *
        *
        *******************************************
        */
        ScrollPane syllabusScrollPane = new ScrollPane();
        syllabusScrollPane.setFitToWidth(ENABLED);
        VBox syllabusPane = ohBuilder.buildVBox(CSG_SYLLABUS_PANE, null, CLASS_CSG_MAIN_PANE, ENABLED);
        syllabusScrollPane.setContent(syllabusPane);
        syllabusPane.prefWidthProperty().bind(stage.widthProperty());

        VBox descriptionPane = ohBuilder.buildVBox(CSG_SYLLABUS_DESCRIPTION_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox topicsPane = ohBuilder.buildVBox(CSG_SYLLABUS_TOPICS_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox prerequisitesPane = ohBuilder.buildVBox(CSG_SYLLABUS_PREREQUISITES_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox outComesPane = ohBuilder.buildVBox(CSG_SYLLABUS_OUTCOMES_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox textbooksPane = ohBuilder.buildVBox(CSG_SYLLABUS_TEXTBOOKS_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox gcPane = ohBuilder.buildVBox(CSG_SYLLABUS_GRADED_COMPONENTS_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox gnPane = ohBuilder.buildVBox(CSG_SYLLABUS_GRADING_NOTE_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox adPane = ohBuilder.buildVBox(CSG_SYLLABUS_ACADEMIC_DISHONESTY_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);
        VBox saPane = ohBuilder.buildVBox(CSG_SYLLABUS_SPECIAL_ASSISTANCE_LABEL_PANE, syllabusPane, CLASS_CSG_PANE, ENABLED);

        HBox descriptionLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_DESCRIPTION_LABEL_PANE, descriptionPane, CLASS_CSG_BOX, ENABLED);
        HBox topicsLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_TOPICS_LABEL_PANE, topicsPane, CLASS_CSG_BOX, ENABLED);
        HBox prerequisitesLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_PREREQUISITES_LABEL_PANE, prerequisitesPane, CLASS_CSG_BOX, ENABLED);
        HBox outcomesLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_OUTCOMES_LABEL_PANE, outComesPane, CLASS_CSG_BOX, ENABLED);
        HBox textbooksLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_TEXTBOOKS_LABEL_PANE, textbooksPane, CLASS_CSG_BOX, ENABLED);
        HBox gcLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_GRADED_COMPONENTS_LABEL_PANE, gcPane, CLASS_CSG_BOX, ENABLED);
        HBox gnLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_GRADING_NOTE_LABEL_PANE, gnPane, CLASS_CSG_BOX, ENABLED);
        HBox adLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_ACADEMIC_DISHONESTY_LABEL_PANE, adPane, CLASS_CSG_BOX, ENABLED);
        HBox saLabelPane = ohBuilder.buildHBox(CSG_SYLLABUS_SPECIAL_ASSISTANCE_LABEL_PANE, saPane, CLASS_CSG_BOX, ENABLED);

        Button descriptionButton = ohBuilder.buildTextButton(CSG_SYLLABUS_DESCRIPTION_BUTTON, descriptionLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button topicsButton = ohBuilder.buildTextButton(CSG_SYLLABUS_TOPICS_BUTTON, topicsLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button prerequisitesButton = ohBuilder.buildTextButton(CSG_SYLLABUS_PREREQUISITES_BUTTON, prerequisitesLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button outcomesButton = ohBuilder.buildTextButton(CSG_SYLLABUS_OUTCOMES_BUTTON, outcomesLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button textbooksButton = ohBuilder.buildTextButton(CSG_SYLLABUS_TEXTBOOKS_BUTTON, textbooksLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button gcButton = ohBuilder.buildTextButton(CSG_SYLLABUS_GRADED_COMPONENTS_BUTTON, gcLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button gnButton = ohBuilder.buildTextButton(CSG_SYLLABUS_GRADING_NOTE_BUTTON, gnLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button adButton = ohBuilder.buildTextButton(CSG_SYLLABUS_ACADEMIC_DISHONESTY_BUTTON, adLabelPane, CLASS_CSG_BUTTON, ENABLED);
        Button saButton = ohBuilder.buildTextButton(CSG_SYLLABUS_SPECIAL_ASSISTANCE_BUTTON, saLabelPane, CLASS_CSG_BUTTON, ENABLED);

        descriptionButton.setText("-");
        topicsButton.setText("+");
        prerequisitesButton.setText("+");
        outcomesButton.setText("+");
        textbooksButton.setText("+");
        gcButton.setText("+");
        gnButton.setText("+");
        adButton.setText("+");
        saButton.setText("+");

        ohBuilder.buildLabel(CSG_SYLLABUS_DESCRIPTION_LABEL, descriptionLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_TOPICS_LABEL, topicsLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_PREREQUISITES_LABEL, prerequisitesLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_OUTCOMES_LABEL, outcomesLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_TEXTBOOKS_LABEL, textbooksLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_GRADED_COMPONENTS_LABEL, gcLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_GRADING_NOTE_LABEL, gnLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_ACADEMIC_DISHONESTY_LABEL, adLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SYLLABUS_SPECIAL_ASSISTANCE_LABEL, saLabelPane, CLASS_CSG_HEADER_LABEL, ENABLED);

        HBox descriptionTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_DESCRIPTION_TEXT_AREA_PANE, descriptionPane, CLASS_CSG_BOX, ENABLED);
        HBox topicsTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_TOPICS_TEXT_AREA_PANE, topicsPane, CLASS_CSG_BOX, ENABLED);
        HBox prerequisitesTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_PREREQUISITES_TEXT_AREA_PANE, prerequisitesPane, CLASS_CSG_BOX, ENABLED);
        HBox outcomesTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_OUTCOMES_TEXT_AREA_PANE, outComesPane, CLASS_CSG_BOX, ENABLED);
        HBox textbooksTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA_PANE, textbooksPane, CLASS_CSG_BOX, ENABLED);
        HBox gcTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA_PANE, gcPane, CLASS_CSG_BOX, ENABLED);
        HBox gnTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA_PANE, gnPane, CLASS_CSG_BOX, ENABLED);
        HBox adTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA_PANE, adPane, CLASS_CSG_BOX, ENABLED);
        HBox saTextAreaPane = ohBuilder.buildHBox(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA_PANE, saPane, CLASS_CSG_BOX, ENABLED);

        TextArea desTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_DESCRIPTION_TEXT_AREA, descriptionTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea topTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_TOPICS_TEXT_AREA, topicsTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea preTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_PREREQUISITES_TEXT_AREA, prerequisitesTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea outTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_OUTCOMES_TEXT_AREA, outcomesTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea texTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA, textbooksTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea gcTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA, gcTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea gnTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA, gnTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea adTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA, adTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        TextArea saTextArea = ohBuilder.buildTextArea(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA, saTextAreaPane, CLASS_CSG_TEXT_AREA, ENABLED);
        desTextArea.prefWidthProperty().bind(stage.widthProperty());
        topTextArea.prefWidthProperty().bind(stage.widthProperty());
        preTextArea.prefWidthProperty().bind(stage.widthProperty());
        outTextArea.prefWidthProperty().bind(stage.widthProperty());
        texTextArea.prefWidthProperty().bind(stage.widthProperty());
        gcTextArea.prefWidthProperty().bind(stage.widthProperty());
        gnTextArea.prefWidthProperty().bind(stage.widthProperty());
        adTextArea.prefWidthProperty().bind(stage.widthProperty());
        saTextArea.prefWidthProperty().bind(stage.widthProperty());
        desTextArea.setVisible(true);
        preTextArea.setVisible(false);
        topTextArea.setVisible(false);
        outTextArea.setVisible(false);
        texTextArea.setVisible(false);
        gcTextArea.setVisible(false);
        gnTextArea.setVisible(false);
        adTextArea.setVisible(false);
        saTextArea.setVisible(false);
        preTextArea.setManaged(false);
        topTextArea.setManaged(false);
        outTextArea.setManaged(false);
        texTextArea.setManaged(false);
        gcTextArea.setManaged(false);
        gnTextArea.setManaged(false);
        adTextArea.setManaged(false);
        saTextArea.setManaged(false);
        /**
        *******************************************
        *
        *
        *MEETING TIMES PANE
        *
        *
        * ******************************************
        */
        ScrollPane mtsScrollPane = new ScrollPane();
        mtsTab.setContent(mtsScrollPane);
        VBox mtsPane = ohBuilder.buildVBox(CSG_MEETING_TIMES_PANE, null, CLASS_CSG_MAIN_PANE, ENABLED);
        mtsScrollPane.setContent(mtsPane);
        mtsScrollPane.setFitToWidth(ENABLED);
        mtsPane.prefWidthProperty().bind(stage.widthProperty());
        //
        //Lectures Pane
        //
        VBox lecturesPane = ohBuilder.buildVBox(CSG_MEETING_TIMES_LECTURES_PANE, mtsPane, CLASS_CSG_PANE, ENABLED);
        HBox lecturesHeaderPane = ohBuilder.buildHBox(CSG_MEETING_TIMES_LECTURES_HEADER_PANE, lecturesPane, CLASS_CSG_BOX, ENABLED);
        Button lecAddBt = ohBuilder.buildTextButton(CSG_MEETING_TIMES_LECTURES_ADD_BUTTON, lecturesHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        Button lecRemoveBt = ohBuilder.buildTextButton(CSG_MEETING_TIMES_LECTURES_REMOVE_BUTTON, lecturesHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        ohBuilder.buildLabel(CSG_MEETING_TIMES_LECTURES_HEADER_LABEL, lecturesHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        TableView<MeetingTimesPrototype> lecturesTable = ohBuilder.buildTableView(CSG_MEETING_TIMES_LECTURES_TABLE_VIEW, lecturesPane, CLASS_CSG_TABLE_VIEW, ENABLED);
        TableColumn lecSectionColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LECTURES_SECTION_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
        TableColumn lecDaysColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LECTURES_DAYS_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
        TableColumn lecTimeColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LECTURES_TIME_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
        TableColumn lecRoomColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LECTURES_ROOM_TABLE_COLUMN, lecturesTable, CLASS_CSG_COLUMN);
        lecAddBt.setText("+");
        lecRemoveBt.setText("-");
        lecSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        lecDaysColumn.setCellValueFactory(new PropertyValueFactory<String, String>("days"));
        lecTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("time"));
        lecRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        lecSectionColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 4.0));
        lecDaysColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 4.0));
        lecTimeColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 4.0));
        lecRoomColumn.prefWidthProperty().bind(lecturesTable.widthProperty().multiply(1.0 / 4.0));
        lecturesTable.prefWidthProperty().bind(stage.widthProperty());
        //
        //Recitation Pane
        //
        VBox recitationPane = ohBuilder.buildVBox(CSG_MEETING_TIMES_RECITATIONS_PANE, mtsPane, CLASS_CSG_PANE, ENABLED);
        HBox recitationHeaderPane = ohBuilder.buildHBox(CSG_MEETING_TIMES_RECITATIONS_HEADER_PANE, recitationPane, CLASS_CSG_BOX, ENABLED);
        Button recAddBt = ohBuilder.buildTextButton(CSG_MEETING_TIMES_RECITATIONS_ADD_BUTTON, recitationHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        Button recRemoveBt = ohBuilder.buildTextButton(CSG_MEETING_TIMES_RECITATIONS_REMOVE_BUTTON, recitationHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        ohBuilder.buildLabel(CSG_MEETING_TIMES_RECITATIONS_HEADER_LABEL, recitationHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        TableView<MeetingTimesPrototype> recitationTable = ohBuilder.buildTableView(CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW, recitationPane, CLASS_CSG_TABLE_VIEW, ENABLED);
        TableColumn recSectionColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_RECITATIONS_SECTION_TABLE_COLUMN, recitationTable, CLASS_CSG_COLUMN);
        TableColumn recDaysTimeColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_RECITATIONS_DAYS_AND_TIME_TABLE_COLUMN, recitationTable, CLASS_CSG_COLUMN);
        TableColumn recRoomColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_RECITATIONS_ROOM_TABLE_COLUMN, recitationTable, CLASS_CSG_COLUMN);
        TableColumn recTA1Column = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_RECITATIONS_TA1_TABLE_COLUMN, recitationTable, CLASS_CSG_COLUMN);
        TableColumn recTA2Column = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_RECITATIONS_TA2_TABLE_COLUMN, recitationTable, CLASS_CSG_COLUMN);

        recAddBt.setText("+");
        recRemoveBt.setText("-");
        recSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        recDaysTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysAndTime"));
        recRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        recTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
        recTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
        recSectionColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0 / 6.0));
        recDaysTimeColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(2.0 / 6.0));
        recRoomColumn.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0 / 6.0));
        recTA1Column.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0 / 6.0));
        recTA2Column.prefWidthProperty().bind(recitationTable.widthProperty().multiply(1.0 / 6.0));
        //
        //Lab Pane
        //
        VBox labPane = ohBuilder.buildVBox(CSG_MEETING_TIMES_LABS_PANE, mtsPane, CLASS_CSG_PANE, ENABLED);
        HBox labHeaderPane = ohBuilder.buildHBox(CSG_MEETING_TIMES_LABS_HEADER_PANE, labPane, CLASS_CSG_BOX, ENABLED);
        Button labAddBt = ohBuilder.buildTextButton(CSG_MEETING_TIMES_LABS_ADD_BUTTON, labHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        Button labRemoveBt = ohBuilder.buildTextButton(CSG_MEETING_TIMES_LABS_REMOVE_BUTTON, labHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        ohBuilder.buildLabel(CSG_MEETING_TIMES_LABS_HEADER_LABEL, labHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        TableView<MeetingTimesPrototype> labTable = ohBuilder.buildTableView(CSG_MEETING_TIMES_LABS_TABLE_VIEW, labPane, CLASS_CSG_TABLE_VIEW, ENABLED);
        TableColumn labSectionColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LABS_SECTION_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
        TableColumn labDaysTimeColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LABS_DAYS_AND_TIME_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
        TableColumn labRoomColumn = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LABS_ROOM_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
        TableColumn labTA1Column = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LABS_TA1_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
        TableColumn labTA2Column = ohBuilder.buildTableColumn(CSG_MEETING_TIMES_LABS_TA2_TABLE_COLUMN, labTable, CLASS_CSG_COLUMN);
        labAddBt.setText("+");
        labRemoveBt.setText("-");
        labSectionColumn.setCellValueFactory(new PropertyValueFactory<String, String>("section"));
        labDaysTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("daysAndTime"));
        labRoomColumn.setCellValueFactory(new PropertyValueFactory<String, String>("room"));
        labTA1Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta1"));
        labTA2Column.setCellValueFactory(new PropertyValueFactory<String, String>("ta2"));
        labSectionColumn.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0 / 6.0));
        labDaysTimeColumn.prefWidthProperty().bind(labTable.widthProperty().multiply(2.0 / 6.0));
        labRoomColumn.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0 / 6.0));
        labTA1Column.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0 / 6.0));
        labTA2Column.prefWidthProperty().bind(labTable.widthProperty().multiply(1.0 / 6.0));
        /**
        *******************************************
        *
        *
        *OFFICE HOURS PANE
        *
        *
        ********************************************
        */
        VBox ohPane = ohBuilder.buildVBox(CSG_OFFICE_HOURS_PANE, null, CLASS_CSG_MAIN_PANE, ENABLED);
        ohPane.prefWidthProperty().bind(stage.widthProperty());

        //
        //Top Pane
        //
        VBox topPane = ohBuilder.buildVBox(CSG_OFFICE_HOURS_TOP_PANE, ohPane, CLASS_CSG_PANE, ENABLED);
        HBox tasHeaderPane = ohBuilder.buildHBox(CSG_OFFICE_HOURS_TAS_HEADER_PANE, topPane, CLASS_CSG_BOX, ENABLED);
        ohBuilder.buildLabel(CSG_OFFICE_HOURS_TAS_HEADER_LABEL, tasHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        Button removeTA = ohBuilder.buildTextButton(CSG_OFFICE_HOURS_REMOVE_TA_BUTTON, tasHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        removeTA.setText("-");  
        GridPane radioButtonPane = ohBuilder.buildGridPane(CSG_OFFICE_HOURS_RADIO_BUTTON_PANE, tasHeaderPane, CLASS_CSG_OFFICE_HOURS_RADIO_BUTTONS_GRID_PANE , ENABLED);
        ToggleGroup csgToggleGroup = new ToggleGroup();
        ohBuilder.buildRadioButton(CSG_OFFICE_HOURS_ALL_RADIO_BUTTON, radioButtonPane, 0, 0, 1, 1,CLASS_CSG_OFFICE_HOURS_RADIO_BUTTON, ENABLED, csgToggleGroup, ENABLED);
        ohBuilder.buildRadioButton(CSG_OFFICE_HOURS_GRADUATE_RADIO_BUTTON, radioButtonPane, 1, 0, 1, 1,CLASS_CSG_OFFICE_HOURS_RADIO_BUTTON, ENABLED, csgToggleGroup, ENABLED);
        ohBuilder.buildRadioButton(CSG_OFFICE_HOURS_UNDERGRADUATE_RADIO_BUTTON, radioButtonPane, 2, 0, 1, 1,CLASS_CSG_OFFICE_HOURS_RADIO_BUTTON, ENABLED, csgToggleGroup, ENABLED);
        
        TableView<TeachingAssistantPrototype> csgTaTable = ohBuilder.buildTableView(CSG_OFFICE_HOURS_TAS_TABLE_VIEW, topPane, CLASS_CSG_TABLE_VIEW, ENABLED);
        TableColumn csgOHNameColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_NAME_TABLE_COLUMN, csgTaTable, CLASS_CSG_COLUMN);
        TableColumn csgOHEmailColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_EMAIL_TABLE_COLUMN, csgTaTable, CLASS_CSG_COLUMN);
        TableColumn csgOHTimeSlotsColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_SLOTS_TABLE_COLUMN, csgTaTable, CLASS_CSG_COLUMN);
        TableColumn csgOHTypeColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_TYPE_TABLE_COLUMN, csgTaTable, CLASS_CSG_COLUMN);
        csgOHNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("name"));
        csgOHEmailColumn.setCellValueFactory(new PropertyValueFactory<String, String>("email"));
        csgOHTimeSlotsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("timeSlots"));
        csgOHTypeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        csgOHNameColumn.prefWidthProperty().bind(csgTaTable.widthProperty().multiply(1.0 / 4.0));
        csgOHEmailColumn.prefWidthProperty().bind(csgTaTable.widthProperty().multiply(1.0 / 4.0));
        csgOHTimeSlotsColumn.prefWidthProperty().bind(csgTaTable.widthProperty().multiply(1.0 / 4.0));
        csgOHTypeColumn.prefWidthProperty().bind(csgTaTable.widthProperty().multiply(1.0 / 4.0));
        csgTaTable.prefWidthProperty().bind(stage.widthProperty());

        GridPane addTaPane = ohBuilder.buildGridPane(CSG_OFFICE_HOURS_ADD_TA_PANE, topPane, CLASS_CSG_OFFICE_HOURS_ADD_TA_GRID_PANE, ENABLED);
        ohBuilder.buildTextField(CSG_OFFICE_HOURS_NAME_TEXT_FIELD, addTaPane, 0, 0, 1, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD, addTaPane, 1, 0, 1, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextButton(CSG_OFFICE_HOURS_ADD_TA_BUTTON, addTaPane, 2, 0, 1, 1, CLASS_CSG_FUNCTION_BUTTON, ENABLED);
        //
        //Bottom Pane
        //
        VBox bottomPane = ohBuilder.buildVBox(CSG_OFFICE_HOURS_BOTTOM_PANE, ohPane, CLASS_CSG_PANE, ENABLED);
        HBox ohHeaderPane = ohBuilder.buildHBox(CSG_OFFICE_HOURS_HEADER_PANE, bottomPane, CLASS_CSG_BOX, ENABLED);
        
        GridPane ohSelectTimePane = ohBuilder.buildGridPane(CSG_OFFICE_HOURS_TIME_PANE, ohHeaderPane, CLASS_CSG_OFFICE_HOURS_SELECT_TIME_GRID_PANE, ENABLED);
        ohBuilder.buildLabel(CSG_OFFICE_HOURS_HEADER_LABEL, ohSelectTimePane, 0, 0, 1, 1, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_OFFICE_HOURS_START_TIME_LABEL, ohSelectTimePane, 1, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_OFFICE_HOURS_END_TIME_LABEL, ohSelectTimePane, 3, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ComboBox startTimeBox = ohBuilder.buildComboBox(CSG_OFFICE_HOURS_START_TIME_COMBO_BOX, ohSelectTimePane, 2, 0, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED, EMPTY_TEXT, EMPTY_TEXT);
        ComboBox endTimeBox = ohBuilder.buildComboBox(CSG_OFFICE_HOURS_END_TIME_COMBO_BOX, ohSelectTimePane, 4, 0, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED, EMPTY_TEXT, EMPTY_TEXT);
        String[] startTimeComboBox
                = {"9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm",
                     "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm", "7:00pm", "7:30pm", "8:00pm", "8:30pm",
                    }; 
        String[] endTimeComboBox 
                = {"9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm","1:30pm", "2:00pm", "2:30pm",
                     "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm", "7:00pm", "7:30pm", "8:00pm", "8:30pm",
                    "9:00pm"};
        startTimeBox.setItems(FXCollections.observableArrayList(startTimeComboBox));
        endTimeBox.setItems(FXCollections.observableArrayList(endTimeComboBox));
        startTimeBox.getSelectionModel().selectFirst();
        endTimeBox.getSelectionModel().selectLast();
        TableView csgOHTable = ohBuilder.buildTableView(CSG_OFFICE_HOURS_TABLE_VIEW, bottomPane, CLASS_CSG_TABLE_VIEW, ENABLED);
        TableColumn csgStartTimeColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_START_TIME_TABLE_COLUMN, csgOHTable, CLASS_CSG_OH_TIME_COLUMN);
        TableColumn csgEndTimeColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_END_TIME_TABLE_COLUMN, csgOHTable, CLASS_CSG_OH_TIME_COLUMN);
        TableColumn csgMondayColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_MONDAY_TABLE_COLUMN, csgOHTable, CLASS_CSG_COLUMN);
        TableColumn csgTuesdayColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_TUESDAY_TABLE_COLUMN, csgOHTable, CLASS_CSG_COLUMN);
        TableColumn csgWednesdayColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_WEDNESDAY_TABLE_COLUMN, csgOHTable, CLASS_CSG_COLUMN);
        TableColumn csgThursdayColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_THURSDAY_TABLE_COLUMN, csgOHTable, CLASS_CSG_COLUMN);
        TableColumn csgFridayColumn = ohBuilder.buildTableColumn(CSG_OFFICE_HOURS_FRIDAY_TABLE_COLUMN, csgOHTable, CLASS_CSG_COLUMN);

        csgStartTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("startTime"));
        csgEndTimeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("endTime"));
        csgMondayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("monday"));
        csgTuesdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("tuesday"));
        csgWednesdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("wednesday"));
        csgThursdayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("thursday"));
        csgFridayColumn.setCellValueFactory(new PropertyValueFactory<String, String>("friday"));

        csgStartTimeColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgEndTimeColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgMondayColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgTuesdayColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgWednesdayColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgThursdayColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgFridayColumn.prefWidthProperty().bind(csgOHTable.widthProperty().multiply(1.0 / 7.0));
        csgOHTable.prefWidthProperty().bind(stage.widthProperty());
        /*
         *******************************************
         *
         *
         *SCHEDULE
         *
         *
         *******************************************
         */
        ScrollPane scheduleScrollPane = new ScrollPane();
        VBox schedulePane = ohBuilder.buildVBox(CSG_SHCEDULE_PANE, null, CLASS_CSG_MAIN_PANE, ENABLED);
        scheduleScrollPane.setContent(schedulePane);
        scheduleScrollPane.setFitToWidth(ENABLED);
        schedulePane.prefWidthProperty().bind(stage.widthProperty());

        //
        //Calendar Pane
        //
        VBox calendarPane = ohBuilder.buildVBox(CSG_SCHEDULE_CALENDAR_PANE, schedulePane, CLASS_CSG_PANE, ENABLED);
        HBox calendarHeaderPane = ohBuilder.buildHBox(CSG_SCHEDULE_CALENDAR_HEADER_PANE, calendarPane, CLASS_CSG_BOX, ENABLED);
        GridPane calendarTimeGridPane = ohBuilder.buildGridPane(CSG_SCHEDULE_CALENDAR_TIME_GRID_PANE, calendarPane, CLASS_CSG_SCHEDULE_CALENDAR_GRID_PANE, ENABLED);

        ohBuilder.buildLabel(CSG_SCHEDULE_CALENDAR_HEADER_LABEL, calendarHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_LABEL, calendarTimeGridPane, 1, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_LABEL, calendarTimeGridPane, 3, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildDatePicker(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER, calendarTimeGridPane, 2, 0, 1, 1, CLASS_CSG_SCHEDULE_DATE_PICKER, ENABLED);
        ohBuilder.buildDatePicker(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER, calendarTimeGridPane, 4, 0, 1, 1, CLASS_CSG_SCHEDULE_DATE_PICKER, ENABLED);

        //
        //Schedule Items Pane
        //
        VBox scheduleItemsPane = ohBuilder.buildVBox(CSG_SCHEDULE_SCHEDULE_ITEMS_PANE, schedulePane, CLASS_CSG_PANE, ENABLED);
        HBox scheduleItemsHeaderPane = ohBuilder.buildHBox(CSG_SCHEDULE_SCHEDULE_ITEMS_HEADER_PANE, scheduleItemsPane, CLASS_CSG_BOX, ENABLED);
        Button RemoveShceduleItemsBt = ohBuilder.buildTextButton(CSG_SCHEDULE_SCHEDULE_ITEMS_REMOVE_BUTTON, scheduleItemsHeaderPane, CLASS_CSG_BUTTON, ENABLED);
        RemoveShceduleItemsBt.setText("-");
        ohBuilder.buildLabel(CSG_SCHEDULE_SCHEDULE_ITEMS_HEADER_LABEL, scheduleItemsHeaderPane, CLASS_CSG_TEXT_LABEL, ENABLED);

        TableView scheduleItemsTable = ohBuilder.buildTableView(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW, scheduleItemsPane, CLASS_CSG_TABLE_VIEW, ENABLED);
        TableColumn csgSITypeColumn = ohBuilder.buildTableColumn(CSG_SCHEDULE_SCHEDULE_TYPE_TABLE_COLUMN, scheduleItemsTable, CLASS_CSG_COLUMN);
        TableColumn csgSIDateColumn = ohBuilder.buildTableColumn(CSG_SCHEDULE_SCHEDULE_DATE_TABLE_COLUMN, scheduleItemsTable, CLASS_CSG_COLUMN);
        TableColumn csgSITitleColumn = ohBuilder.buildTableColumn(CSG_SCHEDULE_SCHEDULE_TITLE_TABLE_COLUMN, scheduleItemsTable, CLASS_CSG_COLUMN);
        TableColumn csgSITopicColumn = ohBuilder.buildTableColumn(CSG_SCHEDULE_SCHEDULE_TOPIC_TABLE_COLUMN, scheduleItemsTable, CLASS_CSG_COLUMN);

        csgSITypeColumn.setCellValueFactory(new PropertyValueFactory<String, String>("type"));
        csgSIDateColumn.setCellValueFactory(new PropertyValueFactory<String, String>("date"));
        csgSITitleColumn.setCellValueFactory(new PropertyValueFactory<String, String>("title"));
        csgSITopicColumn.setCellValueFactory(new PropertyValueFactory<String, String>("topic"));

        csgSITypeColumn.prefWidthProperty().bind(scheduleItemsTable.widthProperty().multiply(1.0 / 5.0));
        csgSIDateColumn.prefWidthProperty().bind(scheduleItemsTable.widthProperty().multiply(1.0 / 5.0));
        csgSITitleColumn.prefWidthProperty().bind(scheduleItemsTable.widthProperty().multiply(1.0 / 5.0));
        csgSITopicColumn.prefWidthProperty().bind(scheduleItemsTable.widthProperty().multiply(2.0 / 5.0));
        scheduleItemsTable.prefWidthProperty().bind(stage.widthProperty().multiply(19.0 / 20.0));
        //
        //Add Schedule Item Pane
        //
        VBox addScheduleItemsPane = ohBuilder.buildVBox(CSG_SCHEDULE_ADD_SCHEDULE_ITEMS_PANE, schedulePane, CLASS_CSG_PANE, ENABLED);
        HBox addScheduleItemsHeaderPane = ohBuilder.buildHBox(CSG_SCHEDULE_ADD_SCHEDULE_HEADER_PANE, addScheduleItemsPane, CLASS_CSG_BOX, ENABLED);
        GridPane addScheduleGridPane = ohBuilder.buildGridPane(CSG_SCHEDULE_ADD_SCHEDULE_HEADER_GRID_PANE, addScheduleItemsPane, CLASS_CSG_SCHEDULE_ADD_ITEM_GRID_PANE, ENABLED);

        ohBuilder.buildLabel(CSG_SCHEDULE_ADD_SCHEDULE_HEADER_LABEL, addScheduleItemsHeaderPane, CLASS_CSG_HEADER_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_TYPE_LABEL, addScheduleGridPane, 0, 0, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_DATE_LABEL, addScheduleGridPane, 0, 1, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_TITLE_LABEL, addScheduleGridPane, 0, 2, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_TOPIC_LABEL, addScheduleGridPane, 0, 3, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);
        ohBuilder.buildLabel(CSG_SCHEDULE_LINK_LABEL, addScheduleGridPane, 0, 4, 1, 1, CLASS_CSG_TEXT_LABEL, ENABLED);

        ohBuilder.buildComboBox(CSG_SCHEDULE_TYPE_COMBO_BOX, addScheduleGridPane, 1, 0, 1, 1, CLASS_CSG_COMBO_BOX, ENABLED, CSG_SCHEDULE_TYPE_OPTIONS, CSG_SCHEDULE_TYPE_DEFAULT_OPTION);
        ohBuilder.buildDatePicker(CSG_SCHEDULE_DATE_PICKER, addScheduleGridPane, 1, 1, 1, 1, CLASS_CSG_SCHEDULE_DATE_PICKER, ENABLED);
        ohBuilder.buildTextField(CSG_SCHEDULE_TITLE_TEXT_FIELD, addScheduleGridPane, 1, 2, 1, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(CSG_SCHEDULE_TOPIC_TEXT_FIELD, addScheduleGridPane, 1, 3, 1, 1, CLASS_CSG_TEXT_FIELD, ENABLED);
        ohBuilder.buildTextField(CSG_SCHEDULE_LINK_TEXT_FIELD, addScheduleGridPane, 1, 4, 1, 1, CLASS_CSG_TEXT_FIELD, ENABLED);

        ohBuilder.buildTextButton(CSG_SCHEDULE_ITEMS_ADD_BUTTON, addScheduleGridPane, 0, 5, 1, 1, CLASS_CSG_FUNCTION_BUTTON, ENABLED);
        ohBuilder.buildTextButton(CSG_SCHEDULE_CLEAR_BUTTON, addScheduleGridPane, 1, 5, 1, 1, CLASS_CSG_FUNCTION_BUTTON, ENABLED);

        siteTab.setContent(siteScrollPane);
        syllabusTab.setContent(syllabusScrollPane);
        mtsTab.setContent(mtsScrollPane);
        ohTab.setContent(ohPane);
        scheduleTab.setContent(scheduleScrollPane);
        //Put into workSpace
        VBox.setVgrow(lecturesTable, Priority.ALWAYS);
        workspace = new BorderPane();
        ((BorderPane) workspace).setCenter(tabPane);

    }

    public void initControllers() {
        CourseSiteGeneratorController controller = new CourseSiteGeneratorController((CourseSiteGeneratorApp) app);
        AppGUIModule gui = app.getGUIModule();
        TabPane tabPane = ((TabPane) gui.getGUINode(CSG_TABPANE));
        tabPane.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });
        /*
         *******************************************
         *
         *
         *SITE
         *
         *
         *******************************************
         */
        Button siteOHTextButton = (Button) gui.getGUINode(CSG_SITE_OFFICE_HOURS_BUTTON);        
        ComboBox subjectBox = (ComboBox) gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        ComboBox numberBox = (ComboBox) gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        ComboBox semesterBox = (ComboBox) gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        ComboBox yearBox = (ComboBox) gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        ChangeListener<String> subjectListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obj, String oldVal, String newVal) {
                if (newVal != null)
                    controller.processSelectSubject(subjectBox, oldVal, newVal, this);
            }
        };

        ChangeListener<String> numberListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obj, String oldVal, String newVal) {
                if (newVal != null)
                    controller.processSelectNumber(numberBox, oldVal, newVal, this);
            }
        };

        ChangeListener<String> semesterListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obj, String oldVal, String newVal) {
                if (newVal != null)
                    controller.processSelectSemester(semesterBox, oldVal, newVal, this);
            }
        };

        ChangeListener<String> yearListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obj, String oldVal, String newVal) {
                if (newVal != null)
                    controller.processSelectYear(yearBox, oldVal, newVal, this);
            }
        };
        subjectBox.getSelectionModel().selectedItemProperty().addListener(subjectListener);
        numberBox.getSelectionModel().selectedItemProperty().addListener(numberListener);
        semesterBox.getSelectionModel().selectedItemProperty().addListener(semesterListener);
        yearBox.getSelectionModel().selectedItemProperty().addListener(yearListener);
        
        subjectBox.setOnAction(e -> {
            try {
                controller.processUpdateExportDir();
                controller.processAddItemIntoSubjectBox();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        numberBox.setOnAction(e -> {
            try {
                controller.processUpdateExportDir();
                controller.processAddItemIntoNumberBox();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        semesterBox.setOnAction(e -> {
            try {
                controller.processUpdateExportDir();
                controller.processAddItemIntoSemesterBox();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        yearBox.setOnAction(e -> {
            try {
                controller.processUpdateExportDir();
                controller.processAddItemIntoYearBox();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        CheckBox homeCheckBox = (CheckBox) gui.getGUINode(CSG_SITE_PAGES_HOME_CHECK_BOX);
        CheckBox syllabusCheckBox = (CheckBox) gui.getGUINode(CSG_SITE_PAGES_SYLLABUS_CHECK_BOX);
        CheckBox scheduleCheckBox = (CheckBox) gui.getGUINode(CSG_SITE_PAGES_SCHEDULE_CHECK_BOX);
        CheckBox pageCheckBox = (CheckBox) gui.getGUINode(CSG_SITE_PAGES_HWS_CHECK_BOX);
        homeCheckBox.setOnAction(e -> {
            if (homeCheckBox.isFocused()) {
                controller.processSetHomeCheckBox();
            }
        });
        syllabusCheckBox.setOnAction(e -> {
            if (syllabusCheckBox.isFocused()) {
                controller.processSetSyllabusCheckBox();
            }
        });
        scheduleCheckBox.setOnAction(e -> {
            if (scheduleCheckBox.isFocused()) {
                controller.processSetScheduleCheckBox();
            }
        });
        pageCheckBox.setOnAction(e -> {
            if (pageCheckBox.isFocused()) {
                controller.processSetHWsCheckBox();
            }
        });

        ComboBox styleBox = (ComboBox) gui.getGUINode(CSG_SITE_STYLE_SHEET_COMBO_BOX);
        ((Button) gui.getGUINode(CSG_SITE_STYLE_FAVICON_BUTTON)).setOnAction(e -> {
            controller.processImportFavicon();
        });
        ((Button) gui.getGUINode(CSG_SITE_STYLE_NAVBAR_BUTTON)).setOnAction(e -> {
            controller.processImportNavbar();
        });
        ((Button) gui.getGUINode(CSG_SITE_STYLE_LEFTE_FOOTER_BUTTON)).setOnAction(e -> {
            controller.processImportLefterFooter();
        });
        ((Button) gui.getGUINode(CSG_SITE_STYLE_RIGHT_FOOTER_BUTTON)).setOnAction(e -> {
            controller.processImportRightFooter();
        });

        ChangeListener<String> styleListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> obj, String oldVal, String newVal) {
                controller.processSelectStyle(styleBox, oldVal, newVal, this);
            }
        };
        styleBox.getSelectionModel().selectedItemProperty().addListener(styleListener);
        
        TextField siteTitleTextField = ((TextField) gui.getGUINode(CSG_SITE_TITLE_TEXT_FIELD));
        TextField nameTextField = ((TextField) gui.getGUINode(CSG_SITE_NAME_TEXT_FIELD));
        TextField roomTextField = ((TextField) gui.getGUINode(CSG_SITE_ROOM_TEXT_FIELD));
        TextField emailTextField = ((TextField) gui.getGUINode(CSG_SITE_EMAIL_TEXT_FIELD));
        TextField homePageTextField = ((TextField) gui.getGUINode(CSG_SITE_HOME_PAGE_TEXT_FIELD));
        TextArea instructorOHTextArea = (TextArea) gui.getGUINode(CSG_SITE_OFFICE_HOURS_TEXT_AREA);
        siteTitleTextField.focusedProperty().addListener(e -> {
            if (!siteTitleTextField.isFocused()) {
                String newValue = siteTitleTextField.getText();
                if(!oldValueOfTitile.equals(newValue))
                    controller.processEditSiteTitle(oldValueOfTitile, newValue);
            } else {
                oldValueOfTitile = siteTitleTextField.getText();
            }
        });
        nameTextField.focusedProperty().addListener(e -> {
            if (!nameTextField.isFocused()) {
                String newValue = nameTextField.getText();
                if(!oldValueOfName.equals(newValue))
                controller.processEditInstructorName(oldValueOfName, newValue);
            } else {
                oldValueOfName = nameTextField.getText();
            }
        });
        roomTextField.focusedProperty().addListener(e -> {
            if (!roomTextField.isFocused()) {
                String newValue = roomTextField.getText();
                if(!oldValueOfRoom.equals(newValue))
                controller.processEditInstructorRoom(oldValueOfRoom, newValue);
            } else {
                oldValueOfRoom = roomTextField.getText();
            }
        });
        emailTextField.focusedProperty().addListener(e -> {
            if (!emailTextField.isFocused()) {
                String newValue = emailTextField.getText();
                if(!oldValueOfEmail.equals(newValue))
                controller.processEditInstructorEmail(oldValueOfEmail, newValue);
            } else {
                oldValueOfEmail = emailTextField.getText();
            }
        });
        homePageTextField.focusedProperty().addListener(e -> {
            if (!homePageTextField.isFocused()) {
                String newValue = homePageTextField.getText();
                if(!oldValueOfHomePage.equals(newValue))
                controller.processEditInstructorHPage(oldValueOfHomePage, newValue);
            } else {
                oldValueOfHomePage = homePageTextField.getText();
            }
        });

        siteOHTextButton.setOnAction(e -> {
            if (siteOHTextButton.getText().equals("-")) {
                instructorOHTextArea.setVisible(false);
                instructorOHTextArea.setManaged(false);
                siteOHTextButton.setText("+");
            } else {
                instructorOHTextArea.setVisible(true);
                instructorOHTextArea.setManaged(true);
                siteOHTextButton.setText("-");
            }
        });
        
        instructorOHTextArea.focusedProperty().addListener(e -> {
            if (!instructorOHTextArea.isFocused()) {
                String newValue = instructorOHTextArea.getText();
                if (!oldValueOfInsturctorOH.equals(newValue))
                    controller.processEditInstructorOH(oldValueOfInsturctorOH, newValue);
            } else {
                oldValueOfInsturctorOH = instructorOHTextArea.getText();
            }
        });
        //PRESS ENTER KEY TO MAKE TRANSACTION
        siteTitleTextField.setOnKeyReleased(e -> {
            if (siteTitleTextField.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = siteTitleTextField.getText();
                if(!oldValueOfTitile.equals(newValue)) {
                    controller.processEditSiteTitle(oldValueOfTitile, newValue);
                    oldValueOfTitile = siteTitleTextField.getText();
                }
            }
        });
         
        nameTextField.setOnKeyReleased(e -> {
            if (nameTextField.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = nameTextField.getText();
                if(!oldValueOfName.equals(newValue)) {
                    controller.processEditInstructorName(oldValueOfName, newValue);
                    oldValueOfName =  newValue;
                }
            }
        });
        
        roomTextField.setOnKeyReleased(e -> {
            if (roomTextField.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = roomTextField.getText();
                if(!oldValueOfRoom.equals(newValue)) {
                    controller.processEditInstructorRoom(oldValueOfRoom, newValue);
                    oldValueOfRoom = newValue;
                }
            }
        });
        
        emailTextField.setOnKeyReleased(e -> {
            if (emailTextField.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = emailTextField.getText();
                if(!oldValueOfEmail.equals(newValue)) {
                    controller.processEditInstructorEmail(oldValueOfEmail, newValue);
                    oldValueOfEmail = newValue;
                }
            }
        });
        
        homePageTextField.setOnKeyReleased(e -> {
            if (homePageTextField.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = homePageTextField.getText();
                if(!oldValueOfHomePage.equals(newValue)) {
                    controller.processEditInstructorHPage(oldValueOfHomePage, newValue);
                    oldValueOfHomePage = newValue;
                }
            }
        });
        
        instructorOHTextArea.setOnKeyReleased(e -> {
            if (instructorOHTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = instructorOHTextArea.getText();
                if (!oldValueOfInsturctorOH.equals(newValue)) {
                    controller.processEditInstructorOH(oldValueOfInsturctorOH, newValue);
                    oldValueOfInsturctorOH = newValue;
                }
            }
        });
        /*
         *******************************************
         *
         *
         *SYLLABUS
         *
         *
         *******************************************
         */
        Button descriptionButton = (Button) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_BUTTON);
        Button topicsButton = (Button) gui.getGUINode(CSG_SYLLABUS_TOPICS_BUTTON);
        Button prerequisitesButton = (Button) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_BUTTON);
        Button outcomesButton = (Button) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_BUTTON);
        Button textbooksButton = (Button) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_BUTTON);
        Button gcButton = (Button) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_BUTTON);
        Button gnButton = (Button) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_BUTTON);
        Button adButton = (Button) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_BUTTON);
        Button saButton = (Button) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_BUTTON);
        TextArea desTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TEXT_AREA);
        TextArea preTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TEXT_AREA);
        TextArea topTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_TOPICS_TEXT_AREA);
        TextArea outTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TEXT_AREA);
        TextArea texTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA);
        TextArea gcTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA);
        TextArea gnTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA);
        TextArea adTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA);
        TextArea saTextArea = (TextArea) gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA);
        descriptionButton.setOnAction(e -> {
            if (descriptionButton.getText().equals("-")) {
                desTextArea.setVisible(false);
                desTextArea.setManaged(false);
                descriptionButton.setText("+");
            } else {
                desTextArea.setVisible(true);
                desTextArea.setManaged(true);
                descriptionButton.setText("-");
            }
        });
        topicsButton.setOnAction(e -> {
            if (topicsButton.getText().equals("-")) {
                topTextArea.setVisible(false);
                topTextArea.setManaged(false);
                topicsButton.setText("+");
            } else {
                topTextArea.setVisible(true);
                topTextArea.setManaged(true);
                topicsButton.setText("-");
            }
        });

        prerequisitesButton.setOnAction(e -> {
            if (prerequisitesButton.getText().equals("-")) {
                preTextArea.setVisible(false);
                preTextArea.setManaged(false);
                prerequisitesButton.setText("+");
            } else {
                preTextArea.setVisible(true);
                preTextArea.setManaged(true);
                prerequisitesButton.setText("-");
            }
        });
        outcomesButton.setOnAction(e -> {
            if (outcomesButton.getText().equals("-")) {
                outTextArea.setVisible(false);
                outTextArea.setManaged(false);
                outcomesButton.setText("+");
            } else {
                outTextArea.setVisible(true);
                outTextArea.setManaged(true);
                outcomesButton.setText("-");
            }
        });
        textbooksButton.setOnAction(e -> {
            if (textbooksButton.getText().equals("-")) {
                texTextArea.setVisible(false);
                texTextArea.setManaged(false);
                textbooksButton.setText("+");
            } else {
                texTextArea.setVisible(true);
                texTextArea.setManaged(true);
                textbooksButton.setText("-");
            }
        });
        gcButton.setOnAction(e -> {
            if (gcButton.getText().equals("-")) {
                gcTextArea.setVisible(false);
                gcTextArea.setManaged(false);
                gcButton.setText("+");
            } else {
                gcTextArea.setVisible(true);
                gcTextArea.setManaged(true);
                gcButton.setText("-");
            }
        });
        gnButton.setOnAction(e -> {
            if (gnButton.getText().equals("-")) {
                gnTextArea.setVisible(false);
                gnTextArea.setManaged(false);
                gnButton.setText("+");
            } else {
                gnTextArea.setVisible(true);
                gnTextArea.setManaged(true);
                gnButton.setText("-");
            }
        });
        adButton.setOnAction(e -> {
            if (adButton.getText().equals("-")) {
                adTextArea.setVisible(false);
                adTextArea.setManaged(false);
                adButton.setText("+");
            } else {
                adTextArea.setVisible(true);
                adTextArea.setManaged(true);
                adButton.setText("-");
            }
        });
        saButton.setOnAction(e -> {
            if (saButton.getText().equals("-")) {
                saTextArea.setVisible(false);
                saTextArea.setManaged(false);
                saButton.setText("+");
            } else {
                saTextArea.setVisible(true);
                saTextArea.setManaged(true);
                saButton.setText("-");
            }
        });
        desTextArea.focusedProperty().addListener(e -> {
            if (!desTextArea.isFocused()) {
                String newValue = desTextArea.getText();
                controller.processEditDescription(oldValueOfDes, newValue);
            } else {
                oldValueOfDes = desTextArea.getText();
            }
        });
        topTextArea.focusedProperty().addListener(e -> {
            if (!topTextArea.isFocused()) {
                String newValue = topTextArea.getText();
                controller.processEditTopics(oldValueOfTop, newValue);
            } else {
                oldValueOfTop = topTextArea.getText();
            }
        });
        preTextArea.focusedProperty().addListener(e -> {
            if (!preTextArea.isFocused()) {
                String newValue = preTextArea.getText();
                controller.processEditPrerequisites(oldValueOfPre, newValue);
            } else {
                oldValueOfPre = preTextArea.getText();
            }
        });

        outTextArea.focusedProperty().addListener(e -> {
            if (!outTextArea.isFocused()) {
                String newValue = outTextArea.getText();
                controller.processEditOutcomes(oldValueOfOut, newValue);
            } else {
                oldValueOfOut = outTextArea.getText();
            }
        });
        texTextArea.focusedProperty().addListener(e -> {
            if (!texTextArea.isFocused()) {
                String newValue = texTextArea.getText();
                controller.processEditTextbooks(oldValueOfTex, newValue);
            } else {
                oldValueOfTex = texTextArea.getText();
            }
        });
        gcTextArea.focusedProperty().addListener(e -> {
            if (!gcTextArea.isFocused()) {
                String newValue = gcTextArea.getText();
                controller.processEditGradedComponents(oldValueOfGc, newValue);
            } else {
                oldValueOfGc = gcTextArea.getText();
            }
        });
        gnTextArea.focusedProperty().addListener(e -> {
            if (!gnTextArea.isFocused()) {
                String newValue = gnTextArea.getText();
                controller.processEditGradingNote(oldValueOfGn, newValue);
            } else {
                oldValueOfGn = gnTextArea.getText();
            }
        });
        adTextArea.focusedProperty().addListener(e -> {
            if (!adTextArea.isFocused()) {
                String newValue = adTextArea.getText();
                controller.processEditAcademicDishonesty(oldValueOfAd, newValue);
            } else {
                oldValueOfAd = adTextArea.getText();
            }
        });
        saTextArea.focusedProperty().addListener(e -> {
            if (!saTextArea.isFocused()) {
                String newValue = saTextArea.getText();
                controller.processEditSpecialAssistance(oldValueOfSa, newValue);
            } else {
                oldValueOfSa = saTextArea.getText();
            }
        });
        //IF USER PRESS ENTER, MAKE A TRANSACTION
        desTextArea.setOnKeyReleased(e -> {
            if (desTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = desTextArea.getText();
                if(!oldValueOfDes.equals(newValue)) {
                    controller.processEditDescription(oldValueOfDes, newValue);
                    oldValueOfDes = newValue;
                }
            }
        });
        topTextArea.setOnKeyReleased(e -> {
            if (topTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = topTextArea.getText();
                if(!oldValueOfTop.equals(newValue)) {
                    controller.processEditTopics(oldValueOfTop, newValue);
                    oldValueOfTop = newValue;
                }
            }
        });
        preTextArea.setOnKeyReleased(e -> {
            if (preTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = preTextArea.getText();
                if(!oldValueOfPre.equals(newValue)) {
                    controller.processEditPrerequisites(oldValueOfPre, newValue);
                    oldValueOfPre = newValue;
                }
            }
        });

        outTextArea.setOnKeyReleased(e -> {
            if (outTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = outTextArea.getText();
                if(!oldValueOfOut.equals(newValue)) {
                    controller.processEditOutcomes(oldValueOfOut, newValue);
                    oldValueOfOut = newValue;
                }
            }
        });
        texTextArea.setOnKeyReleased(e -> {
            if (texTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = texTextArea.getText();
                if(!oldValueOfTex.equals(newValue)) {
                    controller.processEditTextbooks(oldValueOfTex, newValue);
                    oldValueOfTex = newValue;
                }
            }
        });
        gcTextArea.setOnKeyReleased(e -> {
            if (gcTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = gcTextArea.getText();
                if(!oldValueOfGc.equals(newValue)) {
                    controller.processEditGradedComponents(oldValueOfGc, newValue);
                    oldValueOfGc = newValue;;
                }
            }
        });
        gnTextArea.setOnKeyReleased(e -> {
            if (gnTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = gnTextArea.getText();
                if(!oldValueOfGn.equals(newValue)) {
                    controller.processEditGradingNote(oldValueOfGn, newValue);
                    oldValueOfGn = newValue;
                }
            }
        });
        adTextArea.setOnKeyReleased(e -> {
            if (adTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = adTextArea.getText();
                if(!oldValueOfAd.equals(newValue)) {
                    controller.processEditAcademicDishonesty(oldValueOfAd, newValue);
                    oldValueOfAd = newValue;
                }
            }
        });
        saTextArea.setOnKeyReleased(e -> {
            if (saTextArea.isFocused() && e.getCode() == KeyCode.ENTER) {
                String newValue = saTextArea.getText();
                if(!oldValueOfSa.equals(newValue)) {
                    controller.processEditSpecialAssistance(oldValueOfSa, newValue);
                    oldValueOfSa = newValue;
                }
            }
        });
        /*
         *******************************************
         *
         *
         *MEETING TIMES
         *
         *
         *******************************************
         */
        TableView lecturesTableView = (TableView) gui.getGUINode(CSG_MEETING_TIMES_LECTURES_TABLE_VIEW);
        TableView recitationsTableView = (TableView) gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW);
        TableView labsTableView = (TableView) gui.getGUINode(CSG_MEETING_TIMES_LABS_TABLE_VIEW);
        lecturesTableView.setEditable(true);
        recitationsTableView.setEditable(true);
        labsTableView.setEditable(true);

        for (int i = 0; i < lecturesTableView.getColumns().size(); i++) {
            TableColumn tableColumn = ((TableColumn) lecturesTableView.getColumns().get(i));
            tableColumn.setSortable(false);
            tableColumn.setCellFactory(TextFieldTableCell.<MeetingTimesPrototype>forTableColumn());
            tableColumn.setOnEditCommit(e -> {
                MeetingTimesPrototype lecture = ((MeetingTimesPrototype) ((CellEditEvent) e).getRowValue());
                CellEditEvent cell = (CellEditEvent) e;
                controller.processEditLecturesTableView(lecture, (String) (cell.getOldValue()), (String) (cell.getNewValue()), cell.getTablePosition().getColumn());
            });
        }
        for (int i = 0; i < recitationsTableView.getColumns().size(); i++) {
            TableColumn tableColumn = ((TableColumn) recitationsTableView.getColumns().get(i));
            tableColumn.setSortable(false);
            tableColumn.setCellFactory(TextFieldTableCell.<MeetingTimesPrototype>forTableColumn());
            tableColumn.setOnEditCommit(e -> {
                MeetingTimesPrototype recitation = ((MeetingTimesPrototype) ((CellEditEvent) e).getRowValue());
                CellEditEvent cell = (CellEditEvent) e;
                controller.processEditRecitationsTableView(recitation, (String) (cell.getOldValue()),
                        (String) (cell.getNewValue()), cell.getTablePosition().getColumn());
            });
        }
        for (int i = 0; i < labsTableView.getColumns().size(); i++) {
            TableColumn tableColumn = ((TableColumn) labsTableView.getColumns().get(i));
            tableColumn.setSortable(false);
            tableColumn.setCellFactory(TextFieldTableCell.<MeetingTimesPrototype>forTableColumn());
            tableColumn.setOnEditCommit(e -> {
                MeetingTimesPrototype lab = ((MeetingTimesPrototype) ((CellEditEvent) e).getRowValue());
                CellEditEvent cell = (CellEditEvent) e;
                controller.processEditLabsTableView(lab, (String) (cell.getOldValue()),
                        (String) (cell.getNewValue()), cell.getTablePosition().getColumn());
            });
        }
        Button addLecture = (Button) gui.getGUINode(CSG_MEETING_TIMES_LECTURES_ADD_BUTTON);
        Button addRecitation = (Button) gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_ADD_BUTTON);
        Button addLab = (Button) gui.getGUINode(CSG_MEETING_TIMES_LABS_ADD_BUTTON);
        Button removeLecture = (Button) gui.getGUINode(CSG_MEETING_TIMES_LECTURES_REMOVE_BUTTON);
        Button removeRecitation = (Button) gui.getGUINode(CSG_MEETING_TIMES_RECITATIONS_REMOVE_BUTTON);
        Button removeLab = (Button) gui.getGUINode(CSG_MEETING_TIMES_LABS_REMOVE_BUTTON);

        lecturesTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });

        recitationsTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });

        labsTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });

        addLecture.setOnAction(e -> {
            controller.processAddLecture();
        });

        addRecitation.setOnAction(e -> {
            controller.processAddRecitation();
        });

        addLab.setOnAction(e -> {
            controller.processAddLab();
        });

        removeLecture.setOnAction(e -> {
            controller.processRemoveLecture();
        });

        removeRecitation.setOnAction(e -> {
            controller.processRemoveRecitation();
        });

        removeLab.setOnAction(e -> {
            controller.processRemoveLab();
        });
        /*
         *******************************************
         *
         *
         *OFFICE HOURS
         *
         *
         *******************************************
         */
        TextField csgTANameTextField = ((TextField) gui.getGUINode(CSG_OFFICE_HOURS_NAME_TEXT_FIELD));
        TextField csgTAEmailTextField = ((TextField) gui.getGUINode(CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD));
        
        csgTANameTextField.textProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });

        csgTAEmailTextField.textProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });

        csgTANameTextField.setOnAction(e -> {
            controller.processAddTA();
        });

        csgTAEmailTextField.setOnAction(e -> {
            controller.processAddTA();
        });

        ((Button) gui.getGUINode(CSG_OFFICE_HOURS_ADD_TA_BUTTON)).setOnAction(e -> {
            controller.processAddTA();
        });

        ((Button) gui.getGUINode(CSG_OFFICE_HOURS_REMOVE_TA_BUTTON)).setOnAction(e -> {
            controller.processRemoveTA();
        });

        TableView csgOfficeHoursTableView = (TableView) gui.getGUINode(CSG_OFFICE_HOURS_TABLE_VIEW);
        csgOfficeHoursTableView.getSelectionModel().setCellSelectionEnabled(true);
        csgOfficeHoursTableView.setOnMouseClicked(e -> {
            controller.processToggleOfficeHours();
        });
        
        TableView csgTasTableView = (TableView) gui.getGUINode(CSG_OFFICE_HOURS_TAS_TABLE_VIEW);
        for (int i = 0; i < csgOfficeHoursTableView.getColumns().size(); i++) {
            ((TableColumn) csgOfficeHoursTableView.getColumns().get(i)).setSortable(false);
        }

        for (int i = 0; i < csgTasTableView.getColumns().size(); i++) {
            ((TableColumn) csgTasTableView.getColumns().get(i)).setSortable(false);
        }

        csgTasTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });

        csgTasTableView.setOnMouseClicked(e -> {
            app.getFoolproofModule().updateAll();
            if (e.getClickCount() == 2) {
                controller.processEditTA();
            }
            controller.processSelectTA();
        });

        RadioButton csgAllRadio = (RadioButton) gui.getGUINode(CSG_OFFICE_HOURS_ALL_RADIO_BUTTON);
        RadioButton csgGradRadio = (RadioButton) gui.getGUINode(CSG_OFFICE_HOURS_GRADUATE_RADIO_BUTTON);
        RadioButton csgUndergradRadio = (RadioButton) gui.getGUINode(CSG_OFFICE_HOURS_UNDERGRADUATE_RADIO_BUTTON);
        ComboBox startTimeBox = ((ComboBox) gui.getGUINode(CSG_OFFICE_HOURS_START_TIME_COMBO_BOX));
        ComboBox endTimeBox = ((ComboBox) gui.getGUINode(CSG_OFFICE_HOURS_END_TIME_COMBO_BOX));
        csgAllRadio.setOnAction(e -> {
            controller.processSelectAllTAs();
        });

        csgGradRadio.setOnAction(e -> {
            controller.processSelectGradTAs();
        });

        csgUndergradRadio.setOnAction(e -> {
            controller.processSelectUndergradTAs();
        });
        
        ChangeListener<String> startTimeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> String, String oldVal, String newVal) {
                if (newVal != null && oldVal != null && !newVal.equals(oldVal)) {
                    controller.processOHSelectStartTime(oldVal, newVal, (String)endTimeBox.getValue(), this);
                    controller.processFoolproofDesign();
                }
            }
            
        };
        
         ChangeListener<String> endTimeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> String, String oldVal, String newVal) {
                if (newVal != null && oldVal != null && !newVal.equals(oldVal)) {
                    controller.processOHSelectEndTime(oldVal, newVal, (String)startTimeBox.getValue(), this);
                    controller.processFoolproofDesign();
                }
            }
            
        };
        startTimeBox.getSelectionModel().selectedItemProperty().addListener(startTimeListener);
        endTimeBox.getSelectionModel().selectedItemProperty().addListener(endTimeListener);
        /*
        *******************************************
        *
        *
        *SCHEDULE
        *
        *
        *******************************************
        */
        //ITEMS IN THE TABLE VIEW WILL BE UNSELECTED, IF USER DOUBLE CLICKED THE PANES
        VBox schedulePane = (VBox)gui.getGUINode(CSG_SHCEDULE_PANE);
        schedulePane.setOnMouseClicked(e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                controller.processUnselectScheduleItem();
            }
        });
        
        DatePicker startDate = (DatePicker) gui.getGUINode(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER);
        DatePicker endDate = (DatePicker) gui.getGUINode(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER);

        //EDIT START AND END DATES
        startDate.setEditable(false);
        endDate.setEditable(false);
        //DISABLE INVALID STARTING AND ENDING DATES
        startDate.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.getDayOfWeek() != DayOfWeek.MONDAY);
            }
        });

        endDate.setDayCellFactory(dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(empty || item.getDayOfWeek() != DayOfWeek.FRIDAY);
            }
        });
        //ADD LISTENER EVENTS FOR SELECT TIME RANGE
        ChangeListener<LocalDate> startDateListener = new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> obj, LocalDate startDateOldVal, LocalDate startDateNewVal) {
                if (startDateNewVal != null) {
                    controller.processSelectStartDate(startDate, (LocalDate) startDateOldVal, (LocalDate) startDateNewVal,
                            endDate.getValue(), this);
                }
            }
        };

        ChangeListener<LocalDate> endDateListener = new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> obj, LocalDate endDateOldVal, LocalDate endDateNewVal) {
                if (endDateNewVal != null) {
                    controller.processSelectEndDate(endDate, (LocalDate) endDateOldVal, (LocalDate) endDateNewVal,
                            startDate.getValue(), this);
                }
            }
        };

        startDate.valueProperty().addListener(startDateListener);
        endDate.valueProperty().addListener(endDateListener);

        //FOOLPROOF DESIGN FOR INVALID TIME RANGE
        startDate.setOnAction(e -> {
            controller.processStartDateInvalidTimeRangeAlert();
        });

        endDate.setOnAction(e -> {
            controller.processEndDateInvalidTimeRangeAlert();
        });

        TableView scheduleTableView = (TableView) gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW);
        //SET SCHEDULES TABLE VIEW NOT SORTABLE
        for (int i = 0; i < scheduleTableView.getColumns().size(); i++) {
            TableColumn tableColumn = ((TableColumn) scheduleTableView.getColumns().get(i));
            tableColumn.setSortable(false);
        }
        //IF AN ITEM IS SELECTED, LOAD ITS DATA TO EDIT ITEM PANE
        scheduleTableView.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
            controller.processLoadSelectedItem();
            controller.processChangeAddButtonText();
        });
        //EDITE SCHEDULE ITEM PANE
        ComboBox typeBox = (ComboBox) gui.getGUINode(CSG_SCHEDULE_TYPE_COMBO_BOX);
        DatePicker date = (DatePicker) gui.getGUINode(CSG_SCHEDULE_DATE_PICKER);
        date.setEditable(false);
        date.setValue(LocalDate.now());
        //ONCE A TYPE IS CHOSE, PROCESS FOOLPROOD DESIGN
        typeBox.getSelectionModel().selectedItemProperty().addListener(e -> {
            controller.processFoolproofDesign();
        });
        //ADD ITEM AND REMOVE ITEM EVENT
        Button removeScheduleItem = (Button) gui.getGUINode(CSG_SCHEDULE_SCHEDULE_ITEMS_REMOVE_BUTTON);
        Button addScheduleItem = (Button) gui.getGUINode(CSG_SCHEDULE_ITEMS_ADD_BUTTON);
        Button clearItem = (Button) gui.getGUINode(CSG_SCHEDULE_CLEAR_BUTTON);

        addScheduleItem.setOnAction(e -> {
            controller.processAddScheduleItem();
        });

        removeScheduleItem.setOnAction(e -> {
            controller.processRemoveScheduleItem();
        });

        //CLEAR ITEM EVENT
        clearItem.setOnAction(e -> {
            controller.processClearEditItem();
        });
    }

    public void initFoolproofDesign() {
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(CSG_FOOLPROOF_SETTINGS,
                new CourseSiteGeneratorFoolproofDesign((CourseSiteGeneratorApp) app));
    }
    
    public void reset() {
        AppGUIModule gui  = app.getGUIModule();
        CourseSiteGeneratorController controller = new CourseSiteGeneratorController((CourseSiteGeneratorApp) app);
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        resetSiteTabGUI(gui, props);
        resetSyllabusTabGUI(gui);
        resetOfficeHoursTabGUI(gui);
        resetScheduleTabGUI(gui, controller);
    }
    
    private void resetSiteTabGUI(AppGUIModule gui, PropertiesManager props) {
        ComboBox subjectBox = (ComboBox)gui.getGUINode(CSG_SITE_SUBJECT_COMBO_BOX);
        ComboBox numberBox = (ComboBox)gui.getGUINode(CSG_SITE_NUMBER_COMBO_BOX);
        ComboBox semesterBox = (ComboBox)gui.getGUINode(CSG_SITE_SEMESTER_COMBO_BOX);
        ComboBox yearBox = (ComboBox)gui.getGUINode(CSG_SITE_YEAR_COMBO_BOX);
        TextField titleTF = (TextField)gui.getGUINode(CSG_SITE_TITLE_TEXT_FIELD);
        subjectBox.getSelectionModel().selectFirst();
        numberBox.getSelectionModel().selectFirst();
        semesterBox.getSelectionModel().selectFirst();
        yearBox.getSelectionModel().selectFirst();
        titleTF.setText("");
        CheckBox homeBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HOME_CHECK_BOX);
        CheckBox syllabusBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SYLLABUS_CHECK_BOX);
        CheckBox scheduleBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_SCHEDULE_CHECK_BOX);
        CheckBox hwsBox = (CheckBox)gui.getGUINode(CSG_SITE_PAGES_HWS_CHECK_BOX);
        homeBox.setSelected(false);
        syllabusBox.setSelected(false);
        scheduleBox.setSelected(false);
        hwsBox.setSelected(false);
        ImageView faviconImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_FAVICON_IMAGE_VIEW);
        ImageView navbarImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW);
        ImageView leftFooterImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW);
        ImageView rightFooterImageView = (ImageView)gui.getGUINode(CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW);
        ComboBox styleSheetBox = (ComboBox)gui.getGUINode(CSG_SITE_STYLE_SHEET_COMBO_BOX);
        faviconImageView.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_FAVICON_DEFAULTE_IMAGE_PATH)));
        navbarImageView.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_NAVBAR_DEFAULTE_IMAGE_PATH)));
        leftFooterImageView.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_LEFTE_FOOTER_DEFAULTE_IMAGE_PATH)));
        rightFooterImageView.setImage(new MyImage("file:" + props.getProperty(CSG_SITE_STYLE_RIGHT_FOOTER_DEFAULTE_IMAGE_PATH)));
        styleSheetBox.getSelectionModel().selectFirst();
        TextField nameTF = (TextField)gui.getGUINode(CSG_SITE_NAME_TEXT_FIELD);
        TextField roomTF = (TextField)gui.getGUINode(CSG_SITE_ROOM_TEXT_FIELD);
        TextField emailTF = (TextField)gui.getGUINode(CSG_SITE_EMAIL_TEXT_FIELD);
        TextField homePageTF = (TextField)gui.getGUINode(CSG_SITE_HOME_PAGE_TEXT_FIELD);
        TextArea ohTArea = (TextArea)gui.getGUINode(CSG_SITE_OFFICE_HOURS_TEXT_AREA);
        nameTF.setText("");
        roomTF.setText("");
        emailTF.setText("");
        homePageTF.setText("");
        ohTArea.setText("");
    }
    
    private void  resetSyllabusTabGUI(AppGUIModule gui) {
        TextArea descriptionTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_DESCRIPTION_TEXT_AREA);
        TextArea topicsTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_TOPICS_TEXT_AREA);
        TextArea prerequisitesTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_PREREQUISITES_TEXT_AREA);
        TextArea outcomesTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_OUTCOMES_TEXT_AREA);
        TextArea textbooksTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA);
        TextArea gradedComponentsTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA);
        TextArea gradingNoteTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA);
        TextArea academicDishonestyTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA);
        TextArea specialAssistanceTArea = (TextArea)gui.getGUINode(CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA);
        descriptionTArea.setText("");
        topicsTArea.setText("");
        prerequisitesTArea.setText("");
        outcomesTArea.setText("");
        textbooksTArea.setText("");
        gradedComponentsTArea.setText("");
        gradingNoteTArea.setText("");
        academicDishonestyTArea.setText("");        
        specialAssistanceTArea.setText("");
        descriptionTArea.setVisible(true);
        topicsTArea.setVisible(false);
        prerequisitesTArea.setVisible(false);
        outcomesTArea.setVisible(false);
        textbooksTArea.setVisible(false);
        gradedComponentsTArea.setVisible(false);
        gradingNoteTArea.setVisible(false);
        academicDishonestyTArea.setVisible(false);
        specialAssistanceTArea.setVisible(false);
        prerequisitesTArea.setManaged(false);
        topicsTArea.setManaged(false);
        outcomesTArea.setManaged(false);
        textbooksTArea.setManaged(false);
        gradedComponentsTArea.setManaged(false);
        gradingNoteTArea.setManaged(false);
        academicDishonestyTArea.setManaged(false);
        specialAssistanceTArea.setManaged(false);
    }
    
    private void resetOfficeHoursTabGUI(AppGUIModule gui) {
        RadioButton allRadioButton = (RadioButton)gui.getGUINode(CSG_OFFICE_HOURS_ALL_RADIO_BUTTON);
        TextField taNameTF = (TextField)gui.getGUINode(CSG_OFFICE_HOURS_NAME_TEXT_FIELD);
        TextField taEmailTF = (TextField)gui.getGUINode(CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD);
        ComboBox startTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_START_TIME_COMBO_BOX);
        ComboBox endTimeBox = (ComboBox)gui.getGUINode(CSG_OFFICE_HOURS_END_TIME_COMBO_BOX);
        allRadioButton.setSelected(true);
        taNameTF.setText("");
        taEmailTF.setText("");
        String[] startTimeComboBox
                = {"9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm",
                     "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm", "7:00pm", "7:30pm", "8:00pm", "8:30pm",
                    }; 
        String[] endTimeComboBox 
                = {"9:30am", "10:00am", "10:30am", "11:00am", "11:30am", "12:00pm", "12:30pm", "1:00pm","1:30pm", "2:00pm", "2:30pm",
                     "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm", "6:00pm", "6:30pm", "7:00pm", "7:30pm", "8:00pm", "8:30pm",
                    "9:00pm"};
        startTimeBox.setItems(FXCollections.observableArrayList(startTimeComboBox));
        endTimeBox.setItems(FXCollections.observableArrayList(endTimeComboBox));
        startTimeBox.getSelectionModel().selectFirst();
        endTimeBox.getSelectionModel().selectLast();
    }
    
    private void resetScheduleTabGUI(AppGUIModule gui, CourseSiteGeneratorController controller) {
        DatePicker startDate = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER);
        DatePicker endDate = (DatePicker)gui.getGUINode(CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER);
        startDate.setValue(null);
        endDate.setValue(null);
        controller.processClearEditItem();
    }
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }

    @Override
    public void showNewDialog() {
        // WE AREN'T USING THIS FOR THIS APPLICATION
    }

    //HELPER METHODS
    private void initialBannerPane(ComboBox subjectBox, PropertiesManager props, ComboBox numberBox, ComboBox semesterBox, ComboBox yearBox, Label exportDir) {
        ArrayList<String> subjectList = new ArrayList();
        ArrayList<String> numberList = new ArrayList();
        ArrayList<String> semesterList = new ArrayList();
        ArrayList<String> yearList = new ArrayList();
        //LOAD SUBJECT ITEMS
        try {
            String loadSubjectPath = props.getProperty(CSG_LOAD_SUBJECT_PATH);
            JsonObject subjectJsonObject = loadJSONFile(loadSubjectPath);
            JsonArray jsonSubjectArray = subjectJsonObject.getJsonArray(JSON_SUBJECT);
            for (int i = 0; i < jsonSubjectArray.size(); i++) {
                JsonObject jsonSubject = jsonSubjectArray.getJsonObject(i);
                String item = jsonSubject.getString(JSON_ITEM);
                subjectList.add(item);
            }
            subjectBox.setItems(FXCollections.observableArrayList(subjectList));
        } catch (IOException ex) {
            Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LOAD NUMBER ITEMS
        try {
            String loadNumberPath = props.getProperty(CSG_LOAD_NUMBER_PATH);
            JsonObject numberJsonObject = numberJsonObject = loadJSONFile(loadNumberPath);
            JsonArray jsonNumberArray = numberJsonObject.getJsonArray(JSON_NUMBER);
            for (int i = 0; i < jsonNumberArray.size(); i++) {
                JsonObject jsonNumber = jsonNumberArray.getJsonObject(i);
                String item = jsonNumber.getString(JSON_ITEM);
                numberList.add(item);
            }
            numberBox.setItems(FXCollections.observableArrayList(numberList));
        } catch (IOException ex) {
            Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LOAD SEMESTER ITEMS
        try {
            String loadSemesterPath = props.getProperty(CSG_LOAD_SEMESTER_PATH);
            JsonObject semesterJsonObject = loadJSONFile(loadSemesterPath);
            JsonArray jsonSemesterArray = semesterJsonObject.getJsonArray(JSON_SEMESTER);
            for (int i = 0; i < jsonSemesterArray.size(); i++) {
                JsonObject jsonSemester = jsonSemesterArray.getJsonObject(i);
                String item = jsonSemester.getString(JSON_ITEM);
                semesterList.add(item);
            }
            semesterBox.setItems(FXCollections.observableArrayList(semesterList));
        } catch (IOException ex) {
            Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
        }
        //LOAD YEAR ITEMS
        try {
            String loadYearPath = props.getProperty(CSG_LOAD_YEAR_PATH);
            JsonObject yearJsonObject = loadJSONFile(loadYearPath);
            JsonArray jsonYearArray = yearJsonObject.getJsonArray(JSON_YEAR);
            for (int i = 0; i < jsonYearArray.size(); i++) {
                JsonObject jsonYEAR = jsonYearArray.getJsonObject(i);
                String item = jsonYEAR.getString(JSON_ITEM);
                yearList.add(item);
            }
            yearBox.setItems(FXCollections.observableArrayList(yearList));
        } catch (IOException ex) {
            Logger.getLogger(CourseSiteGeneratorWorkspace.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        subjectBox.setEditable(true);
        numberBox.setEditable(true);
        semesterBox.setEditable(true);
        yearBox.setEditable(true);
        subjectBox.setPromptText(props.getProperty(CSG_SITE_SUBJECT_COMBO_BOX_PROMPT_TEXT));
        numberBox.setPromptText(props.getProperty(CSG_SITE_NUMBER_COMBO_BOX_PROMPT_TEXT));
        semesterBox.setPromptText(props.getProperty(CSG_SITE_SEMESTER_COMBO_BOX_PROMPT_TEXT));
        yearBox.setPromptText(props.getProperty(CSG_SITE_YEAR_COMBO_BOX_PROMPT_TEXT));
        subjectBox.getSelectionModel().selectFirst();
        numberBox.getSelectionModel().selectFirst();
        semesterBox.getSelectionModel().selectFirst();
        yearBox.getSelectionModel().selectFirst();
        String subject = (String) subjectBox.getValue();
        String number = (String) numberBox.getValue();
        String semester = (String) semesterBox.getValue();
        String year = (String) yearBox.getValue();
        exportDir.setText(".\\export\\" + subject + "_" + number + "_" + semester + "_" + year + "\\public_html");
    }

    private void initialStyleSheetBox(PropertiesManager props, ComboBox styleSheetBox) {
        ArrayList<String> styleSheetList = new ArrayList();
        String styleSheetFilesPath = props.getProperty(CSG_LOAD_STYLE_SHEET_FILES_PATH);
        File folder = new File(styleSheetFilesPath);
        for (File file : folder.listFiles()) {
            String fileName = file.getName();
            int i = fileName.lastIndexOf('.');
            String extension = fileName.substring(i + 1);
            if (extension.equals(CSS_EXTENSION)) {
                styleSheetList.add(fileName);
            }
        }
        styleSheetBox.setItems(FXCollections.observableArrayList(styleSheetList));
        styleSheetBox.getSelectionModel().selectFirst();
    }

    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }
}
