package csg.transactions;

import jtps.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.TeachingAssistantPrototype;
import csg.data.TimeSlot;
import java.util.ArrayList;

/**
 *
 * @author McKillaGorilla
 */
public class RemoveTA_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    TeachingAssistantPrototype ta;
    ArrayList<TimeSlot> timeSlotList;
    
    public RemoveTA_Transaction(CourseSiteGeneratorData initData, TeachingAssistantPrototype initTA, ArrayList<TimeSlot> initTimeSlotList) {
        data = initData;
        ta = initTA;
        timeSlotList = initTimeSlotList;
    }

    @Override
    public void doTransaction() {
        data.removeTA(ta);        
    }

    @Override
    public void undoTransaction() {
        data.undoRemove(ta, timeSlotList);
    }
}
