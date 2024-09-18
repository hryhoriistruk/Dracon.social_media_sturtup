package com.example.socialmedia;

import socialmedia.UserProfile;
import socialmedia.Settings;

public class AccountManagement {

    public static void updateSettings(UserProfile user, Settings newSettings) {
        user.setSettings(newSettings);
        System.out.println("Settings updated for " + user.getUsername());
    }

    public static void changePassword(UserProfile user, String newPassword) {
        // Update user password
        System.out.println("Password changed for " + user.getUsername());
        user.setPassword(newPassword);
    }

    public static void deleteAccount(UserProfile user) {
        user.deleteAccount();
        System.out.println("Account deleted for " + user.getUsername());
    }

    public static void logOutUser(UserProfile user) {
        System.out.println(user.getUsername() + " has been logged out");
    }
}