package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class MedicalShift {
    private Calendar date;
    public boolean isExpired() throws ShiftDateException {
        if(date == null)
            throw new ShiftDateException("Medical shift date can't be null.");
        return date.after(Calendar.getInstance());
    }
}
