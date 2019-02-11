package csg.workspace.style;

/**
 * This class lists all CSS style types for this application. These
 * are used by JavaFX to apply style properties to controls like
 * buttons, labels, and panes.

 * @author Jie Dai
 * @version 1.0
 */
public class CSGStyle {
    public static final String EMPTY_TEXT = "";
    public static final int BUTTON_TAG_WIDTH = 75;

    // THESE CONSTANTS ARE FOR TYING THE PRESENTATION STYLE OF
    // THIS M3Workspace'S COMPONENTS TO A STYLE SHEET THAT IT USES
    // NOTE THAT FOUR CLASS STYLES ALREADY EXIST:
    // top_toolbar, toolbar, toolbar_text_button, toolbar_icon_button
    
    public static final String CLASS_OH_TEXT_FIELD    = "oh_text_field";
    public static final String CLASS_OH_TEXT_FIELD_ERROR = "oh_text_field_error";
    
    // FOR THE DIALOG
    public static final String CLASS_OH_DIALOG_GRID_PANE = "oh_dialog_grid_pane";
    public static final String CLASS_OH_DIALOG_HEADER = "oh_dialog_header"; 
    public static final String CLASS_OH_DIALOG_PROMPT = "oh_dialog_prompt"; 
    public static final String CLASS_OH_DIALOG_TEXT_FIELD = "oh_dialog_text_field";
    public static final String CLASS_OH_DIALOG_RADIO_BUTTON = "oh_dialog_radio_button";
    public static final String CLASS_OH_DIALOG_BOX = "oh_dialog_box";
    public static final String CLASS_OH_DIALOG_BUTTON = "oh_dialog_button";
    
    public static final String CLASS_CSG_MAIN_PANE          = "csg_main_pane";
    public static final String CLASS_CSG_TAB_PANE          = "csg_tab_pane";
    public static final String CLASS_CSG_TABS          = "csg_tabs";
    public static final String CLASS_CSG_PANE          = "csg_pane";
    public static final String CLASS_CSG_BOX          = "csg_box";
    public static final String CLASS_CSG_HEADER_LABEL          = "csg_header_label";
    public static final String CLASS_CSG_TEXT_LABEL          = "csg_text_label";
    public static final String CLASS_CSG_COMBO_BOX      ="csg_combo_box";
    public static final String CLASS_CSG_TEXT_FIELD          = "csg_text_field";
    public static final String CLASS_CSG_TEXT_AREA          = "csg_text_area";
    public static final String CLASS_CSG_BUTTON          = "csg_button";
    public static final String CLASS_CSG_FUNCTION_BUTTON          = "csg_function_button";
    public static final String CLASS_CSG_TABLE_VIEW          = "csg_table_view";
    public static final String CLASS_CSG_COLUMN          = "csg_column";
    public static final String CLASS_CSG_SCROLL_PANE          = "csg_scroll_pane";
    public static final String CLASS_CSG_BANNER_GRID_PANE          = "csg_banner_grid_pane";
    public static final String CLASS_CSG_STYLE_GRID_PANE          = "csg_style_grid_pane";
    public static final String CLASS_CSG_INSTRUCTOR_GRID_PANE          = "csg_instructor_grid_pane";
    public static final String CLASS_CSG_INSTRUCTOR_TEXT_FIELD          = "csg_instructor_text_field";
    public static final String CLASS_CSG_SITE_EXPORT_DIR_PATH_TEXT          = "csg_site_export_dir_path_text";
    public static final String CLASS_CSG_SITE_CHECK_BOX_GRID_PANE         = "csg_site_check_box_grid_pane";
    public static final String CLASS_CSG_SITE_PAGES_CHECK_BOX          = "csg_site_pages_check_box";
    public static final String CLASS_CSG_IMPORT_IMAGE_BUTTON          = "csg_import_image_button";
    public static final String CLASS_CSG_SITE_IMAGE_VIEW          = "csg_site_image_view";
    public static final String CLASS_CSG_OFFICE_HOURS_RADIO_BUTTONS_GRID_PANE          = "csg_office_hours_radio_buttons_grid_pane";
    public static final String CLASS_CSG_OFFICE_HOURS_ADD_TA_GRID_PANE          = "csg_office_hours_add_ta_grid_pane";
    public static final String CLASS_CSG_OFFICE_HOURS_SELECT_TIME_GRID_PANE          = "csg_office_hours_select_time_grid_pane";
    public static final String CLASS_CSG_OH_TIME_COLUMN          = "csg_oh_time_column"; 
    public static final String CLASS_CSG_OFFICE_HOURS_RADIO_BUTTON          = "csg_office_hours_radio_button";
    public static final String CLASS_CSG_OFFICE_HOURS_ADD_TA_BUTTON_GRID_PANE         = "csg_office_hours_add_ta_button_grid_pane";
    public static final String CLASS_CSG_SCHEDULE_CALENDAR_GRID_PANE          = "csg_schedule_calendar_grid_pane" ;
    public static final String CLASS_CSG_SCHEDULE_ADD_ITEM_GRID_PANE          = "csg_schedule_add_item_grid_pane";
    public static final String CLASS_CSG_SCHEDULE_TEXT_LABEL          = "csg_schedule_text_label";
    public static final String CLASS_CSG_SCHEDULE_DATE_PICKER         = "csg_schedule_date_picker";
    public static final String CLASS_CSG_SCHEDULE_GRID_PANE          = "csg_schedule_grid_pane";
}