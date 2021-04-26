package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Calendar;

@Entity(name = "Study")
@DiscriminatorValue("study")
public class Study extends Shift {

    public boolean isExpired() throws ShiftDateException {
        if(date == null)
            throw new ShiftDateException("Study date can't be null.");
        return date.after(Calendar.getInstance());
    }

}
