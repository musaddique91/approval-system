package com.musa.approvalsys.exceptions

enum class AppSysErrorCodes(val code: Int, val message: String) {
    // user errors
    USER_NOT_EXIST(101, "user does not exist"),
    USER_ALREADY_EXIST(102, "user already exist"),

    // role errors
    ROLE_ALREADY_EXIST(201, "role already exist"),
    ROLE_IS_IN_USE(202, "role is assign to 1 or more users")
}
