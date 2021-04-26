package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import lombok.Data;

import javax.persistence.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shiftType")
public abstract class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected Calendar date;
    protected Boolean reserved = false;
    protected Specialitation specialitation;

    public abstract boolean isExpired() throws ShiftDateException;

    public void reserve(User patient) throws ShiftDateException{
        if(!this.isExpired()){
            this.reserved = true;
            patient.getShitfs().add(this);
        }else{
            this.reserved = false;
        }
    };

    public long daysToExpire() throws ShiftDateException{
        if(this.date == null)
            throw new ShiftDateException("Shift date is not set.");

        Calendar today = Calendar.getInstance();

        if(this.date.after(today))
            return 0;

        return Math.abs(ChronoUnit.DAYS.between(today.toInstant(),this.date.toInstant()));
    }

    public boolean isReserved(){ return this.reserved; }

    //Factory method.
    public static Shift createMedicalShift(){
        return new MedicalShift();
    }
    public static Shift createStudy(){ return new Study(); }
}
