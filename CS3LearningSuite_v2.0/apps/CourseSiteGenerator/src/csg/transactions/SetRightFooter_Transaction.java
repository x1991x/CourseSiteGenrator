
package csg.transactions;

import csg.data.MyImage;
import csg.data.CourseSiteGeneratorData;
import java.io.File;
import jtps.jTPS_Transaction;

/**
 *
 * @author Jie Dai
 */
public class SetRightFooter_Transaction implements jTPS_Transaction  {
    CourseSiteGeneratorData data;
    MyImage oldImage;
    MyImage newImage;
    public SetRightFooter_Transaction(CourseSiteGeneratorData initData, MyImage initOldImage, MyImage initNewImage) { 
        this.data = initData;
        this.oldImage = initOldImage;
        this.newImage = initNewImage;
    }

    @Override
    public void doTransaction() {
        data.setRightFooterImage(newImage);
    }

    @Override
    public void undoTransaction() {
        data.setRightFooterImage(oldImage);
    }
}
