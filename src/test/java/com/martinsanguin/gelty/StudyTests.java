package com.martinsanguin.gelty;

import com.martinsanguin.gelty.domain.InAppNotification;
import com.martinsanguin.gelty.domain.InAppNotifier;
import com.martinsanguin.gelty.domain.Shift;
import com.martinsanguin.gelty.domain.User;
import com.martinsanguin.gelty.domain.exceptions.ShiftDateException;
import com.martinsanguin.gelty.domain.interfaces.Notifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class StudyTests {

    private Shift studyExpired;
    private Shift studyNotExpiredSameDay;
    private Shift studyNotExpired;
    private Shift studyWithoutDate;
    private Shift studyExpiredBy30minutes;
    private Shift medicalShiftExpired;
    private Shift medicalShiftNotExpiredSameDay;
    private Shift medicalShiftNotExpired;
    private Shift medicalShiftWithoutDate;
    private Shift medicalShiftExpiredBy30minutes;

    @BeforeEach
    void setUp() {
        //Study outdated
        studyExpired = Shift.createStudy();
        Calendar currentDayPlusOne = Calendar.getInstance();
        currentDayPlusOne.add(Calendar.DATE,1);
        studyExpired.setDate(currentDayPlusOne);

        //Study not outdated (same day)
        studyNotExpiredSameDay = Shift.createStudy();
        studyNotExpiredSameDay.setDate(Calendar.getInstance());

        //Study not outdated (one day before)
        studyNotExpired = Shift.createStudy();
        Calendar currentDayMinusOne = Calendar.getInstance();
        currentDayMinusOne.add(Calendar.DATE,-2);
        studyNotExpired.setDate(currentDayMinusOne);

        //Study without date
        studyWithoutDate = Shift.createStudy();

        //Study expired by 30 minutes
        studyExpiredBy30minutes = Shift.createStudy();
        Calendar currentDayPlus30Minutes = Calendar.getInstance();
        currentDayPlus30Minutes.add(Calendar.MINUTE,30);
        studyExpiredBy30minutes.setDate(currentDayPlus30Minutes);

        medicalShiftExpired = Shift.createMedicalShift();
        medicalShiftExpired.setDate(currentDayPlusOne);

        medicalShiftNotExpiredSameDay = Shift.createMedicalShift();
        medicalShiftNotExpiredSameDay.setDate(Calendar.getInstance());

        medicalShiftNotExpired = Shift.createMedicalShift();
        medicalShiftNotExpired.setDate(currentDayMinusOne);

        medicalShiftWithoutDate = Shift.createMedicalShift();

        medicalShiftExpiredBy30minutes = Shift.createMedicalShift();
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

    @Test
    public void sendNotification2DaysBeforeShiftBecameOutdated(){
        User user = new User();
        user.getShitfs().add(studyNotExpired);

        Notifier notifier = new InAppNotifier();
        notifier.sendNotificationsForShifts(user, new InAppNotification(Calendar.getInstance(),"Notification description"));

        assertEquals(1,user.getNotifications().size());
    }

    @Test
    public void shiftIs2DaysBeforeExpire() throws Exception{
        assertEquals(2,studyNotExpired.daysToExpire());
    }

    @Test
    public void shiftIsExpiredAndDaysToExpireIsCero() throws Exception{
        assertEquals(0,studyExpired.daysToExpire());
    }

}
