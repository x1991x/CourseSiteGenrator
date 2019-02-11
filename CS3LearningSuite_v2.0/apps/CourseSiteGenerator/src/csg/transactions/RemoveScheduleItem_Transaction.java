
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import csg.data.SchedulesPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class RemoveScheduleItem_Transaction  implements jTPS_Transaction{
    CourseSiteGeneratorData data;
    SchedulesPrototype item;

    public RemoveScheduleItem_Transaction(CourseSiteGeneratorData data, SchedulesPrototype item) {
        this.data = data;
        this.item = item;
    }
    
    @Override
    public void doTransaction() {
        data.removeScheduleItem(item);
    }

    @Override
    public void undoTransaction() {
        data.addScheduleItem(item);
    }
    
}
