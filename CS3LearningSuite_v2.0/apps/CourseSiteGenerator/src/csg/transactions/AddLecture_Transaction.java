
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class AddLecture_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    MeetingTimesPrototype lecture;
    public AddLecture_Transaction(CourseSiteGeneratorData data, MeetingTimesPrototype lecture) {
        this.data = data;
        this.lecture = lecture;
    }
    @Override
    public void doTransaction() {
        data.addLecture(lecture);
    }

    @Override
    public void undoTransaction() {
        data.removeLecture(lecture);
    }
    
}
