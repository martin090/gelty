package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.StudyDateException;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class MedicalShift {
    private Calendar date;
    public boolean isExpired() throws StudyDateException {
        if(date == null)
            throw new StudyDateException("Study date can't be null.");
        return date.after(Calendar.getInstance());
    }
}
