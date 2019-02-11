package csg.transactions;

import csg.data.CourseSiteGeneratorData;
import java.time.LocalDate;
import jtps.jTPS_Transaction;
import csg.data.TeachingAssistantPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class EditTA_Transaction implements jTPS_Transaction {
    TeachingAssistantPrototype taToEdit;
    CourseSiteGeneratorData data;
    String oldName, newName;
    String oldEmail, newEmail;
    String oldType, newType;
    
    public EditTA_Transaction(TeachingAssistantPrototype initTAToEdit, CourseSiteGeneratorData initData,
            String name, String email, String type) {
        taToEdit = initTAToEdit;
        oldName = initTAToEdit.getName();
        oldEmail = initTAToEdit.getEmail();
        oldType = initTAToEdit.getType();
        data = initData;
        newName = name;
        newEmail = email;
        newType = type;
         
    }


    @Override
    public void doTransaction() {
        taToEdit.setName(newName);
        taToEdit.setEmail(newEmail);
        taToEdit.setType(newType);
        data.updateTAs();
    }

    @Override
    public void undoTransaction() {
        taToEdit.setName(oldName);
        taToEdit.setEmail(oldEmail);
        taToEdit.setType(oldType);
        data.updateTAs();
    }
}