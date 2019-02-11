
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.SchedulesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class AddScheduleItem_Transaction implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    SchedulesPrototype item;

    public AddScheduleItem_Transaction(CourseSiteGeneratorData data, SchedulesPrototype item) {
        this.data = data;
        this.item = item;
    }
    
    @Override
    public void doTransaction() {
        data.addScheduleItem(item);
    }

    @Override
    public void undoTransaction() {
        data.removeScheduleItem(item);
    }
    
}
