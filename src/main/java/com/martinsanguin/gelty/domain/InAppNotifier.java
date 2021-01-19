package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.interfaces.INotification;
import com.martinsanguin.gelty.domain.interfaces.Notifier;

public class InAppNotifier implements Notifier {
    @Override
    public void sendNotificationsForShifts(User user, INotification notification) {
        user.getNotifications().add(notification);
    }
}
