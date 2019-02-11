
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelectHome_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    public SelectHome_Transaction(CourseSiteGeneratorData initData) {
        this.data = initData;
    }

    @Override
    public void doTransaction() {
        data.selectHomeCheckBox();
    }

    @Override
    public void undoTransaction() {
        data.undoSelectHomeCheckBox();
    }
}
