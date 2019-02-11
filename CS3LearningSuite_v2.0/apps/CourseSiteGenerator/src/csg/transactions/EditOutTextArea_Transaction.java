
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class EditOutTextArea_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    String oldVal;
    String newVal;
    
    public EditOutTextArea_Transaction(CourseSiteGeneratorData initData, String initOldVal, String initNewVal) {
        this.data = initData;
        this.oldVal = initOldVal;
        this.newVal = initNewVal;
    }

    @Override
    public void doTransaction() {
        data.editOutTextArea(newVal);
    }

    @Override
    public void undoTransaction() {
        data.editOutTextArea(oldVal);
    }
}
