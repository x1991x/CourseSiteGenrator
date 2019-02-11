
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelectHWs_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    public SelectHWs_Transaction(CourseSiteGeneratorData initData) {
        this.data = initData;
    }

    @Override
    public void doTransaction() {
        data.selectHWsCheckBox();
    }

    @Override
    public void undoTransaction() {
        data.undoSelectHWsCheckBox();
    }
}
