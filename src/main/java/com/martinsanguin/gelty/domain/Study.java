package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class Study {
    private Calendar date;

    public boolean isExpired() throws ShiftDateException {
        if(date == null)
            throw new ShiftDateException("Study date can't be null.");
        return date.after(Calendar.getInstance());
    }
}
