
package csg;

/**
 *
 * @author Jie Dai
 */
public enum CourseSiteGeneratorPropertyType {
     OH_OK_PROMPT,
    OH_CANCEL_PROMPT,

    // THESE ARE FOR TEXT PARTICULAR TO THE APP'S WORKSPACE CONTROLS
    CSG_FOOLPROOF_SETTINGS,
    
    // FOR THE EDIT DIALOG
    OH_TA_EDIT_DIALOG,
    OH_TA_DIALOG_GRID_PANE,
    OH_TA_DIALOG_HEADER_LABEL, 
    OH_TA_DIALOG_NAME_LABEL,
    OH_TA_DIALOG_NAME_TEXT_FIELD,
    OH_TA_DIALOG_EMAIL_LABEL,
    OH_TA_DIALOG_EMAIL_TEXT_FIELD,
    OH_TA_DIALOG_TYPE_LABEL,
    OH_TA_DIALOG_TYPE_BOX,
    OH_TA_DIALOG_GRAD_RADIO_BUTTON,
    OH_TA_DIALOG_UNDERGRAD_RADIO_BUTTON,
    OH_TA_DIALOG_OK_BOX,
    OH_TA_DIALOG_OK_BUTTON, 
    OH_TA_DIALOG_CANCEL_BUTTON, 
    
    // THESE ARE FOR ERROR MESSAGES PARTICULAR TO THE APP
    CSG_NO_TA_SELECTED_TITLE, CSG_NO_TA_SELECTED_CONTENT,
    CSG_INVALID_DATE_TITLE, CSG_INVALID_DATE_CONTENT,
    CSG_INVALID_FILE, 
    CSG_FAILED_TO_LOAD,
    CSG_NO_PAGE_SELECTED_TITLE, CSG_NO_PAGE_SELECTED_CONTENT,
    CSG_OK_PROMPT,
    CSG_CANCEL_PROMPT,
    CSG_TABPANE,
    CSG_LOAD_SUBJECT_PATH,
    CSG_LOAD_NUMBER_PATH,
    CSG_LOAD_SEMESTER_PATH,
    CSG_LOAD_YEAR_PATH,
    CSG_LOAD_STYLE_SHEET_FILES_PATH,
    
    CSG_SITE_PANE,
    CSG_SITE_BANNER_PANE,
    CSG_SITE_BANNER_HEADER_PANE,
    CSG_SITE_BANNER_HEADER_LABEL,
    CSG_SITE_BANNER_GRID_PANE,
    CSG_SITE_SUBJECT_LABEL,
    CSG_SITE_SUBJECT_COMBO_BOX,
    CSG_SITE_SUBJECT_COMBO_BOX_PROMPT_TEXT,
    CSG_SITE_SUBJECT_OPTIONS,
    CSG_SITE_NUMBER_LABEL,
    CSG_SITE_NUMBER_COMBO_BOX,
    CSG_SITE_NUMBER_COMBO_BOX_PROMPT_TEXT,
    CSG_SITE_NUMBER_OPTIONS,
    CSG_SITE_NUMBER_DEFAULT_OPTION,
    CSG_SITE_SEMESTER_LABEL,
    CSG_SITE_SEMESTER_COMBO_BOX,
    CSG_SITE_SEMESTER_COMBO_BOX_PROMPT_TEXT,
    CSG_SITE_SEMESTER_OPTIONS,
    CSG_SITE_SEMESTER_DEFAULT_OPTION,
    CSG_SITE_YEAR_LABEL,
    CSG_SITE_YEAR_COMBO_BOX,
    CSG_SITE_YEAR_COMBO_BOX_PROMPT_TEXT,
    CSG_SITE_YEAR_OPTIONS,
    CSG_SITE_TITLE_LABEL,
    CSG_SITE_TITLE_TEXT_FIELD,
    CSG_SITE_EXPORT_DIR_LABEL,
    CSG_SITE_EXPORT_DIR_PATH,
    CSG_SITE_PAGES_PANE,
    CSG_SITE_PAGES_HEADER_PANE,
    CSG_SITE_PAGES_HEADER_LABEL,
    CSG_SITE_CHECK_BOX_GRID_PANE,
    CSG_SITE_PAGES_HOME_CHECK_BOX,
    CSG_SITE_PAGES_SYLLABUS_CHECK_BOX,
    CSG_SITE_PAGES_SCHEDULE_CHECK_BOX,
    CSG_SITE_PAGES_HWS_CHECK_BOX,
    CSG_SITE_STYLE_PANE,
    CSG_SITE_STYLE_HEADER_PANE,
    CSG_SITE_STYLE_HEADER_LABEL,
    CSG_SITE_STYLE_FAVICON_BUTTON,
    CSG_SITE_STYLE_FAVICON_IMAGE_VIEW,
    CSG_SITE_STYLE_FAVICON_DEFAULTE_IMAGE_PATH,
    CSG_SITE_STYLE_NAVBAR_BUTTON,
    CSG_SITE_STYLE_NAVBAR_IMAGE_VIEW,
    CSG_SITE_STYLE_NAVBAR_DEFAULTE_IMAGE_PATH,
    CSG_SITE_STYLE_LEFTE_FOOTER_BUTTON,
    CSG_SITE_STYLE_LEFTE_FOOTER_IMAGE_VIEW,
    CSG_SITE_STYLE_LEFTE_FOOTER_DEFAULTE_IMAGE_PATH,
    CSG_SITE_STYLE_RIGHT_FOOTER_BUTTON,
    CSG_SITE_STYLE_RIGHT_FOOTER_IMAGE_VIEW,
    CSG_SITE_STYLE_RIGHT_FOOTER_DEFAULTE_IMAGE_PATH,
    CSG_SITE_STYLE_IMAGE_HREF,
    CSG_SITE_STYLE_SHEET_PANE,
    CSG_SITE_STYLE_SHEET_LABEL,
    CSG_SITE_STYLE_SHEET_COMBO_BOX,
    CSG_SITE_STYLE_SHEET_OPTIONS,
    CSG_SITE_STYLE_SHEET_DEFAULT_OPTION,
    CSG_SITE_STYLE_NOTE_PANE,
    CSG_SITE_NOTE_LABEL,
    CSG_SITE_INSTRUCTOR_PANE,
    CSG_SITE_INSTRUCTOR_HEADER_PANE,
    CSG_SITE_INSTRUCTOR_HEADER_LABEL,
    CSG_SITE_INSTRUCTOR_GRID_PANE,
    CSG_SITE_NAME_LABEL,
    CSG_SITE_NAME_TEXT_FIELD,
    CSG_SITE_ROOM_LABEL,
    CSG_SITE_ROOM_TEXT_FIELD,
    CSG_SITE_EMAIL_LABEL,
    CSG_SITE_EMAIL_TEXT_FIELD,
    CSG_SITE_HOME_PAGE_LABEL,
    CSG_SITE_HOME_PAGE_TEXT_FIELD,
    CSG_SITE_OFFICE_HOURS_LABEL_PANE,
    CSG_SITE_OFFICE_HOURS_BUTTON,
    CSG_SITE_OFFICE_HOURS_LABEL,
    CSG_SITE_OFFICE_HOURS_PANE,
    CSG_SITE_OFFICE_HOURS_TEXT_AREA,

    CSG_SYLLABUS_PANE,
    CSG_SYLLABUS_DESCRIPTION_LABEL_PANE,
    CSG_SYLLABUS_DESCRIPTION_BUTTON,
    CSG_SYLLABUS_DESCRIPTION_LABEL,
    CSG_SYLLABUS_DESCRIPTION_TEXT_AREA_PANE,
    CSG_SYLLABUS_DESCRIPTION_TEXT_AREA,
    CSG_SYLLABUS_TOPICS_LABEL_PANE,
    CSG_SYLLABUS_TOPICS_BUTTON,
    CSG_SYLLABUS_TOPICS_LABEL,
    CSG_SYLLABUS_TOPICS_TEXT_AREA_PANE,
    CSG_SYLLABUS_TOPICS_TEXT_AREA,
    CSG_SYLLABUS_PREREQUISITES_LABEL_PANE,
    CSG_SYLLABUS_PREREQUISITES_BUTTON,
    CSG_SYLLABUS_PREREQUISITES_LABEL,
    CSG_SYLLABUS_PREREQUISITES_TEXT_AREA_PANE,
    CSG_SYLLABUS_PREREQUISITES_TEXT_AREA,
    CSG_SYLLABUS_OUTCOMES_LABEL_PANE,
    CSG_SYLLABUS_OUTCOMES_BUTTON,
    CSG_SYLLABUS_OUTCOMES_LABEL,
    CSG_SYLLABUS_OUTCOMES_TEXT_AREA_PANE,
    CSG_SYLLABUS_OUTCOMES_TEXT_AREA,
    CSG_SYLLABUS_TEXTBOOKS_LABEL_PANE,
    CSG_SYLLABUS_TEXTBOOKS_BUTTON,
    CSG_SYLLABUS_TEXTBOOKS_LABEL,
    CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA_PANE,
    CSG_SYLLABUS_TEXTBOOKS_TEXT_AREA,
    CSG_SYLLABUS_GRADED_COMPONENTS_LABEL_PANE,
    CSG_SYLLABUS_GRADED_COMPONENTS_BUTTON,
    CSG_SYLLABUS_GRADED_COMPONENTS_LABEL,
    CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA_PANE,
    CSG_SYLLABUS_GRADED_COMPONENTS_TEXT_AREA,
    CSG_SYLLABUS_GRADING_NOTE_LABEL_PANE,
    CSG_SYLLABUS_GRADING_NOTE_BUTTON,
    CSG_SYLLABUS_GRADING_NOTE_LABEL,
    CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA_PANE,
    CSG_SYLLABUS_GRADING_NOTE_TEXT_AREA,
    CSG_SYLLABUS_ACADEMIC_DISHONESTY_LABEL_PANE,
    CSG_SYLLABUS_ACADEMIC_DISHONESTY_BUTTON,
    CSG_SYLLABUS_ACADEMIC_DISHONESTY_LABEL,
    CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA_PANE,
    CSG_SYLLABUS_ACADEMIC_DISHONESTY_TEXT_AREA,
    CSG_SYLLABUS_SPECIAL_ASSISTANCE_LABEL_PANE,
    CSG_SYLLABUS_SPECIAL_ASSISTANCE_BUTTON,
    CSG_SYLLABUS_SPECIAL_ASSISTANCE_LABEL,
    CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA_PANE,
    CSG_SYLLABUS_SPECIAL_ASSISTANCE_TEXT_AREA,
    
    CSG_MEETING_TIMES_SCROLL_PANE,
    CSG_MEETING_TIMES_PANE,
    CSG_MEETING_TIMES_LECTURES_PANE,
    CSG_MEETING_TIMES_LECTURES_HEADER_PANE,
    CSG_MEETING_TIMES_LECTURES_ADD_BUTTON,
    CSG_MEETING_TIMES_LECTURES_REMOVE_BUTTON,
    CSG_MEETING_TIMES_LECTURES_HEADER_LABEL,
    CSG_MEETING_TIMES_LECTURES_TABLE_VIEW,
    CSG_MEETING_TIMES_LECTURES_SECTION_TABLE_COLUMN,
    CSG_MEETING_TIMES_LECTURES_DAYS_TABLE_COLUMN,
    CSG_MEETING_TIMES_LECTURES_TIME_TABLE_COLUMN,
    CSG_MEETING_TIMES_LECTURES_ROOM_TABLE_COLUMN,
    CSG_MEETING_TIMES_RECITATIONS_PANE,
    CSG_MEETING_TIMES_RECITATIONS_HEADER_PANE,
    CSG_MEETING_TIMES_RECITATIONS_ADD_BUTTON,
    CSG_MEETING_TIMES_RECITATIONS_REMOVE_BUTTON,
    CSG_MEETING_TIMES_RECITATIONS_HEADER_LABEL,
    CSG_MEETING_TIMES_RECITATIONS_TABLE_VIEW,
    CSG_MEETING_TIMES_RECITATIONS_SECTION_TABLE_COLUMN,
    CSG_MEETING_TIMES_RECITATIONS_DAYS_AND_TIME_TABLE_COLUMN,
    CSG_MEETING_TIMES_RECITATIONS_ROOM_TABLE_COLUMN,
    CSG_MEETING_TIMES_RECITATIONS_TA1_TABLE_COLUMN,
    CSG_MEETING_TIMES_RECITATIONS_TA2_TABLE_COLUMN,
    CSG_MEETING_TIMES_LABS_PANE,
    CSG_MEETING_TIMES_LABS_HEADER_PANE,
    CSG_MEETING_TIMES_LABS_ADD_BUTTON,
    CSG_MEETING_TIMES_LABS_REMOVE_BUTTON,
    CSG_MEETING_TIMES_LABS_HEADER_LABEL,
    CSG_MEETING_TIMES_LABS_TABLE_VIEW,
    CSG_MEETING_TIMES_LABS_SECTION_TABLE_COLUMN,
    CSG_MEETING_TIMES_LABS_DAYS_AND_TIME_TABLE_COLUMN,
    CSG_MEETING_TIMES_LABS_ROOM_TABLE_COLUMN,
    CSG_MEETING_TIMES_LABS_TA1_TABLE_COLUMN,
    CSG_MEETING_TIMES_LABS_TA2_TABLE_COLUMN,

    CSG_OFFICE_HOURS_PANE,
    CSG_OFFICE_HOURS_TOP_PANE,
    CSG_OFFICE_HOURS_TAS_HEADER_PANE,
    CSG_OFFICE_HOURS_REMOVE_TA_BUTTON,
    CSG_OFFICE_HOURS_RADIO_BUTTON_PANE,
    CSG_OFFICE_HOURS_TAS_HEADER_LABEL,
    CSG_OFFICE_HOURS_ALL_RADIO_BUTTON,
    CSG_OFFICE_HOURS_GRADUATE_RADIO_BUTTON,
    CSG_OFFICE_HOURS_UNDERGRADUATE_RADIO_BUTTON,
    CSG_OFFICE_HOURS_TAS_SCROLL_PANE,
    CSG_OFFICE_HOURS_TAS_TABLE_VIEW,
    CSG_OFFICE_HOURS_NAME_TABLE_COLUMN,
    CSG_OFFICE_HOURS_EMAIL_TABLE_COLUMN,
    CSG_OFFICE_HOURS_SLOTS_TABLE_COLUMN,
    CSG_OFFICE_HOURS_TYPE_TABLE_COLUMN,
    CSG_OFFICE_HOURS_ADD_TA_PANE,
    CSG_OFFICE_HOURS_NAME_TEXT_FIELD,
    CSG_OFFICE_HOURS_EMAIL_TEXT_FIELD,
    CSG_OFFICE_HOURS_ADD_TA_BUTTON,
    CSG_OFFICE_HOURS_BOTTOM_PANE,
    CSG_OFFICE_HOURS_HEADER_PANE,
    CSG_OFFICE_HOURS_HEADER_LABEL,
    CSG_OFFICE_HOURS_TIME_PANE,
    CSG_OFFICE_HOURS_START_TIME_LABEL,
    CSG_OFFICE_HOURS_START_TIME_COMBO_BOX,
    CSG_OFFICE_HOURS_START_TIME_OPTIONS,
    CSG_OFFICE_HOURS_START_TIME_DEFAULT_OPTION,
    CSG_OFFICE_HOURS_END_TIME_LABEL,
    CSG_OFFICE_HOURS_END_TIME_COMBO_BOX,
    CSG_OFFICE_HOURS_END_TIME_OPTIONS,
    CSG_OFFICE_HOURS_END_TIME_DEFAULT_OPTION,     
    
    CSG_OFFICE_HOURS_OH_SCROLL_PANE,
    CSG_OFFICE_HOURS_TABLE_VIEW,
    CSG_OFFICE_HOURS_START_TIME_TABLE_COLUMN,
    CSG_OFFICE_HOURS_END_TIME_TABLE_COLUMN,
    CSG_OFFICE_HOURS_MONDAY_TABLE_COLUMN,
    CSG_OFFICE_HOURS_TUESDAY_TABLE_COLUMN,
    CSG_OFFICE_HOURS_WEDNESDAY_TABLE_COLUMN,
    CSG_OFFICE_HOURS_THURSDAY_TABLE_COLUMN,
    CSG_OFFICE_HOURS_FRIDAY_TABLE_COLUMN,
    CSG_OFFICE_HOURS_DAYS_OF_WEEK,
    CSG_OFFICE_HOURS_FOOLPROOF_SETTINGS,

    CSG_SHCEDULE_PANE,
    CSG_SCHEDULE_CALENDAR_PANE,
    CSG_SCHEDULE_CALENDAR_HEADER_PANE,
    CSG_SCHEDULE_CALENDAR_HEADER_LABEL,
    CSG_SCHEDULE_CALENDAR_TIME_GRID_PANE,
    CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_LABEL,
    CSG_SCHEDULE_CALENDAR_STARTING_MONDAY_DATE_PICKER,
    CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_LABEL,
    CSG_SCHEDULE_CALENDAR_ENDING_FRIDAY_DATE_PICKER,
    CSG_SCHEDULE_SCHEDULE_ITEMS_PANE,
    CSG_SCHEDULE_SCHEDULE_ITEMS_HEADER_PANE,
    CSG_SCHEDULE_SCHEDULE_ITEMS_REMOVE_BUTTON,
    CSG_SCHEDULE_SCHEDULE_ITEMS_HEADER_LABEL,
    CSG_SCHEDULE_SCHEDULE_ITEMS_TABLE_VIEW,
    CSG_SCHEDULE_SCHEDULE_TYPE_TABLE_COLUMN,
    CSG_SCHEDULE_SCHEDULE_DATE_TABLE_COLUMN,
    CSG_SCHEDULE_SCHEDULE_TITLE_TABLE_COLUMN,
    CSG_SCHEDULE_SCHEDULE_TOPIC_TABLE_COLUMN,
    CSG_SCHEDULE_ADD_SCHEDULE_ITEMS_PANE,
    CSG_SCHEDULE_ADD_SCHEDULE_HEADER_PANE,
    CSG_SCHEDULE_ADD_SCHEDULE_HEADER_LABEL,
    CSG_SCHEDULE_ADD_SCHEDULE_HEADER_GRID_PANE,
    CSG_SCHEDULE_TYPE_LABEL,
    CSG_SCHEDULE_TYPE_COMBO_BOX,
    CSG_SCHEDULE_TYPE_OPTIONS,
    CSG_SCHEDULE_TYPE_DEFAULT_OPTION,
    CSG_SCHEDULE_DATE_LABEL,
    CSG_SCHEDULE_DATE_PICKER,
    CSG_SCHEDULE_TITLE_LABEL,
    CSG_SCHEDULE_TITLE_TEXT_FIELD,
    CSG_SCHEDULE_TOPIC_LABEL,
    CSG_SCHEDULE_TOPIC_TEXT_FIELD,
    CSG_SCHEDULE_LINK_LABEL,
    CSG_SCHEDULE_LINK_TEXT_FIELD,
    CSG_SCHEDULE_ITEMS_ADD_BUTTON,
    CSG_SCHEDULE_ITEMS_UPDATE_BUTTON,
    CSG_SCHEDULE_CLEAR_BUTTON,
}