
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.MeetingTimesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class AddLab_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    MeetingTimesPrototype lab;
    public AddLab_Transaction(CourseSiteGeneratorData data, MeetingTimesPrototype lab) {
        this.data = data;
        this.lab = lab;
    }
    @Override
    public void doTransaction() {
        data.addLab(lab);
    }

    @Override
    public void undoTransaction() {
        data.removeLab(lab);
    }
    
}
