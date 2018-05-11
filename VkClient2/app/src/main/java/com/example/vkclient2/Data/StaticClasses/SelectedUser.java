package com.example.vkclient2.Data.StaticClasses;

public class SelectedUser {
    private static int userId;
    private static String userName;

    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int userId) {
        SelectedUser.userId = userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        SelectedUser.userName = userName;
    }
}
