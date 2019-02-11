
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class RemoveLecture_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    MeetingTimesPrototype selectedItem;

    public RemoveLecture_Transaction(CourseSiteGeneratorData data, MeetingTimesPrototype lecture) {
        this.data = data;
        this.selectedItem = lecture;
    }
    
    @Override
    public void doTransaction() {
        data.removeLecture(selectedItem);
    }

    @Override
    public void undoTransaction() {
        data.addLecture(selectedItem);
    }
}
