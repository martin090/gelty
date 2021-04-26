package com.martinsanguin.gelty.api.v1.model;

import lombok.Data;

import java.util.Calendar;

@Data
public class ShiftDTO {
    private Calendar date;
    private Boolean reserved;
}
