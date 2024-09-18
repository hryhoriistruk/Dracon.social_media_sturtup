package com.example.socialmedia;

import java.util.EnumSet;
import java.util.logging.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotificationSystem {
    private static final Logger LOGGER = Logger.getLogger(NotificationSystem.class.getName());
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public enum NotificationType {
        EMAIL, PUSH, IN_APP
    }

    public static void sendNotification(UserProfile user, String message, EnumSet<NotificationType> types) {
        if (user == null || message == null || message.isEmpty()) {
            LOGGER.warning("Invalid notification parameters");
            return;
        }

        if (!user.getSettings().getSetting(Settings.SettingKey.NOTIFICATIONS_ENABLED)) {
            LOGGER.info("Notifications are disabled for user: " + user.getUsername());
            return;
        }

        executorService.submit(() -> {
            for (NotificationType type : types) {
                try {
                    switch (type) {
                        case EMAIL:
                            sendEmailNotification(user, message);
                            break;
                        case PUSH:
                            sendPushNotification(user, message);
                            break;
                        case IN_APP:
                            sendInAppNotification(user, message);
                            break;
                    }
                } catch (Exception e) {
                    LOGGER.severe("Failed to send " + type + " notification to " + user.getUsername() + ": " + e.getMessage());
                }
            }
        });
    }

    private static void sendEmailNotification(UserProfile user, String message) {
        // TODO: Implement email notification logic
        LOGGER.info("Email notification sent to " + user.getUsername() + ": " + message);
    }

    private static void sendPushNotification(UserProfile user, String message) {
        // TODO: Implement push notification logic
        LOGGER.info("Push notification sent to " + user.getUsername() + ": " + message);
    }

    private static void sendInAppNotification(UserProfile user, String message) {
        // TODO: Implement in-app notification logic
        LOGGER.info("In-app notification sent to " + user.getUsername() + ": " + message);
    }

    public static void shutdown() {
        executorService.shutdown();
    }
}