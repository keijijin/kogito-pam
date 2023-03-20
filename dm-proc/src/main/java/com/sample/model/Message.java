package com.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    public final static int HELLO = 0;
    public final static int GOODBYE = 1;

    private int status;
    private String message;
}
