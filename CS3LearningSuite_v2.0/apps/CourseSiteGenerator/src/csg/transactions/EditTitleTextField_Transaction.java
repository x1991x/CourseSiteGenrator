
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class EditTitleTextField_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    String oldVal;
    String newVal;
    
    public EditTitleTextField_Transaction(CourseSiteGeneratorData initData, String initOldVal, String initNewVal) {
        this.data = initData;
        this.oldVal = initOldVal;
        this.newVal = initNewVal;
    }

    @Override
    public void doTransaction() {
        data.editTitleTextField(newVal);
    }

    @Override
    public void undoTransaction() {
        data.editTitleTextField(oldVal);
    }
}
