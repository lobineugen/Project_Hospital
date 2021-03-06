package com.sumdu.hospital.service;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.sumdu.hospital.database.DAO;
import javafx.scene.control.Control;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.sumdu.hospital.constants.Constants.MILLISECOND_FORMAT;
import static com.sumdu.hospital.constants.Constants.REQUIRED_FIELD;

@Service
public class Helper {
    private static final Logger LOGGER = Logger.getLogger(Helper.class);
    private DAO dao;
    private ShowDialog showDialog;

    @Autowired
    public Helper(DAO dao, ShowDialog showDialog) {
        this.dao = dao;
        this.showDialog = showDialog;
    }

    public int getUniqueID() {
        StringBuilder result = new StringBuilder("1");
        result.append(String.format("%06d", dao.getID()));
        result.append(MILLISECOND_FORMAT.format(new Date()));
        int id = Integer.valueOf(result.toString());
        LOGGER.debug("Unique ID = " + id);
        return id;
    }

    public void addRequiredValidator(Control currentControl) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator();
        requiredFieldValidator.setMessage(REQUIRED_FIELD);
        if (currentControl instanceof JFXTextField) {
            ((JFXTextField) currentControl).getValidators().add(requiredFieldValidator);
        } else if (currentControl instanceof JFXDatePicker) {
            ((JFXDatePicker) currentControl).getValidators().add(requiredFieldValidator);
        } else if (currentControl instanceof JFXTextArea) {
            ((JFXTextArea) currentControl).getValidators().add(requiredFieldValidator);
        }
    }


}
