package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;

import java.util.Calendar;


public class Study extends Shift {

    public boolean isExpired() throws ShiftDateException {
        if(date == null)
            throw new ShiftDateException("Study date can't be null.");
        return date.after(Calendar.getInstance());
    }
}
