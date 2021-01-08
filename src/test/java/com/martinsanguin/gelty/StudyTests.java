package com.martinsanguin.gelty;

import com.martinsanguin.gelty.domain.MedicalShift;
import com.martinsanguin.gelty.domain.Study;
import com.martinsanguin.gelty.domain.exceptions.StudyDateException;
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



    }

    @Test
    public void studyExpired() throws Exception{
        assertTrue(studyExpired.isExpired(),"The study should be expired.");
        assertTrue(medicalShiftExpired.isExpired());
    }

    @Test
    public void studyNotExpiredInTheSameDay() throws Exception{
        assertFalse(studyNotExpiredSameDay.isExpired(),"The study should not be expired.");
    }

    @Test
    public void studyNotExpired() throws Exception{
        assertFalse(studyNotExpired.isExpired(),"The study should not be expired.");
    }

    @Test
    public void studyWithoutDateThrowsExceptionWhenEvaluateExpiration(){
        assertThrows(StudyDateException.class,() -> {studyWithoutDate.isExpired();});
    }

    @Test
    public void studyExpiredByMinutes() throws Exception{
        assertTrue(studyExpiredBy30minutes.isExpired(),"The test should be expired by diference of minutes.");
    }

}
