package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;

@Entity(name = "MedicalShift")
@DiscriminatorValue("medicalShift")
public class MedicalShift extends Shift {


    public boolean isExpired() throws ShiftDateException {
        if(date == null)
            throw new ShiftDateException("Medical shift date can't be null.");
        return date.after(Calendar.getInstance());
    }

}
