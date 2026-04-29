package com.example.user_service.entity;

public enum Role {
    USER,
    ADMIN,
    SUPER_ADMIN
}
/* Enum defines a fixed set of constants
Role class is enum because in this project throughout role should be either role or admin
Prevents invalid values (like "superuser" or "random")
Type-safe compared to plain strings
*/