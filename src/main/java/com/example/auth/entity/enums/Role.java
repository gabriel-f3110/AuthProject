package com.example.auth.entity.enums;

public enum Role {

    DEFAULT(0),
    USER(1),
    ADMIN(2);

    int cod;

    Role(int cod) {
        this.cod = cod;
    }
}
