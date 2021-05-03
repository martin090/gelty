package com.martinsanguin.gelty.domain;

import com.martinsanguin.gelty.domain.interfaces.INotification;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private List<Shift> shitfs = new ArrayList<Shift>();
    private List<INotification> notifications = new ArrayList<INotification>();

}
