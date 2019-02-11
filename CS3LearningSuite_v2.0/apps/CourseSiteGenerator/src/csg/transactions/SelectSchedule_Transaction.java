
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelectSchedule_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    public SelectSchedule_Transaction(CourseSiteGeneratorData initData) {
        this.data = initData;
    }

    @Override
    public void doTransaction() {
        data.selectScheduleCheckBox();
    }

    @Override
    public void undoTransaction() {
        data.undoSelectScheduleCheckBox();
    }
}
