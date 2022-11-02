package com.example.Flexserver.domain.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private int id;
    private String message;
    private int userId;
}
