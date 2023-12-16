package com.example.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class User
{
    private String id;
    @Setter
    private String name;
    @Setter
    private String email;
    @Setter
    private String phone;
}
