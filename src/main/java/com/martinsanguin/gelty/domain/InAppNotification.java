package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.interfaces.INotification;

import java.util.Calendar;

public class InAppNotification implements INotification {

    private String message;
    private Calendar datetime;

    public InAppNotification(Calendar datetime, String notification_description) {
        this.message = notification_description;
        this.datetime = datetime;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Calendar getDateTime() {
        return datetime;
    }
}
