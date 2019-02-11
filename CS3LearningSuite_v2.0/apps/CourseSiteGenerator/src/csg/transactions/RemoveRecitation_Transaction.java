
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class RemoveRecitation_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    MeetingTimesPrototype selectedItem;

    public RemoveRecitation_Transaction(CourseSiteGeneratorData data, MeetingTimesPrototype recitation) {
        this.data = data;
        this.selectedItem = recitation;
    }
    
    @Override
    public void doTransaction() {
        data.removeRecitation(selectedItem);
    }

    @Override
    public void undoTransaction() {
        data.addRecitation(selectedItem);
    }
}
