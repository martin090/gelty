package com.martinsanguin.gelty;

import com.martinsanguin.gelty.domain.MedicalShift;
import com.martinsanguin.gelty.domain.Study;
import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class StudyTests {

    private Study studyExpired;
    private Study studyNotExpiredSameDay;
    private Study studyNotExpired;
    private Study studyWithoutDate;
    private Study studyExpiredBy30minutes;
    private MedicalShift medicalShiftExpired;
    private MedicalShift medicalShiftNotExpiredSameDay;
    private MedicalShift medicalShiftNotExpired;
    private MedicalShift medicalShiftWithoutDate;
    private MedicalShift medicalShiftExpiredBy30minutes;

    @BeforeEach
    void setUp() {
        //Study outdated
        studyExpired = new Study();
        Calendar currentDayPlusOne = Calendar.getInstance();
        currentDayPlusOne.add(Calendar.DATE,1);
        studyExpired.setDate(currentDayPlusOne);

        //Study not outdated (same day)
        studyNotExpiredSameDay = new Study();
        studyNotExpiredSameDay.setDate(Calendar.getInstance());

        //Study not outdated (one day before)
        studyNotExpired = new Study();
        Calendar currentDayMinusOne = Calendar.getInstance();
        currentDayMinusOne.add(Calendar.DATE,-1);
        studyNotExpired.setDate(currentDayMinusOne);

        //Study without date
        studyWithoutDate = new Study();

        //Study expired by 30 minutes
        studyExpiredBy30minutes = new Study();
        Calendar currentDayPlus30Minutes = Calendar.getInstance();
        currentDayPlus30Minutes.add(Calendar.MINUTE,30);
        studyExpiredBy30minutes.setDate(currentDayPlus30Minutes);

        medicalShiftExpired = new MedicalShift();
        medicalShiftExpired.setDate(currentDayPlusOne);

        medicalShiftNotExpiredSameDay = new MedicalShift();
        medicalShiftNotExpiredSameDay.setDate(Calendar.getInstance());

        medicalShiftNotExpired = new MedicalShift();
        medicalShiftNotExpired.setDate(currentDayMinusOne);

        medicalShiftWithoutDate = new MedicalShift();

        medicalShiftExpiredBy30minutes = new MedicalShift();
        medicalShiftExpiredBy30minutes.setDate(currentDayPlus30Minutes);

    }

    @Test
    public void shiftExpired() throws Exception{
        assertTrue(studyExpired.isExpired(),"The study should be expired.");
        assertTrue(medicalShiftExpired.isExpired(),"The medical shift should be expired.");
    }

    @Test
    public void shiftNotExpiredInTheSameDay() throws Exception{
        assertFalse(studyNotExpiredSameDay.isExpired(),"The study should not be expired.");
        assertFalse(medicalShiftNotExpiredSameDay.isExpired(),"The medical shift should not be expired.");
    }

    @Test
    public void shiftNotExpired() throws Exception{
        assertFalse(studyNotExpired.isExpired(),"The study should not be expired.");
        assertFalse(medicalShiftNotExpired.isExpired(),"The medical shift should not be expired.");
    }

    @Test
    public void shiftWithoutDateThrowsExceptionWhenEvaluateExpiration(){
        assertThrows(ShiftDateException.class,() -> {studyWithoutDate.isExpired();});
        assertThrows(ShiftDateException.class,() -> {medicalShiftWithoutDate.isExpired();});
    }

    @Test
    public void shiftExpiredByMinutes() throws Exception{
        assertTrue(studyExpiredBy30minutes.isExpired(),"The study should be expired by diference of minutes.");
        assertTrue(medicalShiftExpiredBy30minutes.isExpired(),"The medical shift should be expired by diference of minutes.");
    }

}
