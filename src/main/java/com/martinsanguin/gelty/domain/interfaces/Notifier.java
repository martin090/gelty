package com.martinsanguin.gelty.domain.interfaces;

import com.martinsanguin.gelty.domain.User;

public interface Notifier {
    void sendNotificationsForShifts(User user, INotification notification);
}
