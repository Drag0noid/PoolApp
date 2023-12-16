package ru.pool.poolapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Integer id_paramedic;
    private Integer id_patient;
}
