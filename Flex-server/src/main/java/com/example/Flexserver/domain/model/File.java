package com.example.Flexserver.domain.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private int id;
    private String file;
    private int userId;
}
