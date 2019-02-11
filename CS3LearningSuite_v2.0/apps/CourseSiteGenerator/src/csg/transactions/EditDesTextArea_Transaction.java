
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class EditDesTextArea_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    String oldVal;
    String newVal;
    
    public EditDesTextArea_Transaction(CourseSiteGeneratorData initData, String initOldVal, String initNewVal) {
        this.data = initData;
        this.oldVal = initOldVal;
        this.newVal = initNewVal;
    }

    @Override
    public void doTransaction() {
        data.editDesTextArea(newVal);
    }

    @Override
    public void undoTransaction() {
        data.editDesTextArea(oldVal);
    }
}
