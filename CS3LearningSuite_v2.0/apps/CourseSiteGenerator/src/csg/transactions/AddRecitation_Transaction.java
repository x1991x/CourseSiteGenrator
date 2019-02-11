
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class AddRecitation_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    MeetingTimesPrototype recitation;
    public AddRecitation_Transaction(CourseSiteGeneratorData data, MeetingTimesPrototype recitation) {
        this.data = data;
        this.recitation = recitation;
    }
    @Override
    public void doTransaction() {
        data.addRecitation(recitation);
    }

    @Override
    public void undoTransaction() {
        data.removeRecitation(recitation);
    }
    
}
