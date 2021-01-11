package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public abstract class Shift {
    protected Calendar date;
    public abstract boolean isExpired() throws ShiftDateException;

    public static Shift createMedicalShift(){
        return new MedicalShift();
    }

    public static Shift createStudy(){
        return new Study();
    }
}
