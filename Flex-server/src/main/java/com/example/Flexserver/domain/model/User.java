package com.example.Flexserver.domain.model;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String name;
    private String password;
    private String role;
}
