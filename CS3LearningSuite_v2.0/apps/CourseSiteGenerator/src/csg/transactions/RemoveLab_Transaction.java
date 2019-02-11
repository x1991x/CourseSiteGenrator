
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class RemoveLab_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    MeetingTimesPrototype selectedItem;

    public RemoveLab_Transaction(CourseSiteGeneratorData data, MeetingTimesPrototype lab) {
        this.data = data;
        this.selectedItem = lab;
    }
    
    @Override
    public void doTransaction() {
        data.removeLab(selectedItem);
    }

    @Override
    public void undoTransaction() {
        data.addLab(selectedItem);
    }
}
