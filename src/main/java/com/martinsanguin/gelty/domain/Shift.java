package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Getter
@Setter
public abstract class Shift {
    protected Calendar date;
    public abstract boolean isExpired() throws ShiftDateException;

    public long daysToExpire() throws ShiftDateException{
        if(this.date == null)
            throw new ShiftDateException("Shift date is not set.");

        Calendar today = Calendar.getInstance();

        if(this.date.after(today))
            return 0;

        return Math.abs(ChronoUnit.DAYS.between(today.toInstant(),this.date.toInstant()));
    }

    //Factory method.
    public static Shift createMedicalShift(){
        return new MedicalShift();
    }
    public static Shift createStudy(){ return new Study(); }
}
