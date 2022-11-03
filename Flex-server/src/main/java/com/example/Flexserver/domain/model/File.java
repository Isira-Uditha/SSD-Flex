package com.example.Flexserver.domain.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private int id;
    private String filePath;
    private int userId;
}
