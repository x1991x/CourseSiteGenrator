
package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SelectSyllabus_Transaction implements jTPS_Transaction {
    CourseSiteGeneratorData data;
    public SelectSyllabus_Transaction(CourseSiteGeneratorData initData) {
        this.data = initData;
    }

    @Override
    public void doTransaction() {
        data.selectSyllabusCheckBox();
    }

    @Override
    public void undoTransaction() {
        data.undoSelectSyllabusBox();
    }
}
