package com.example.socialmedia;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserProfile {
    private static final Logger LOGGER = Logger.getLogger(UserProfile.class.getName());

    private final String username;
    private String bio;
    private BufferedImage profilePicture;
    private final List<UserProfile> followers;
    private final List<UserProfile> following;
    private final Settings settings;

    public UserProfile(String username, String bio, BufferedImage profilePicture) {
        this.username = Objects.requireNonNull(username, "Username cannot be null");
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.settings = new Settings();
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public BufferedImage getProfilePicture() {
        return profilePicture;
    }

    public int getFollowerCount() {
        return followers.size();
    }

    public List<UserProfile> getFollowers() {
        return Collections.unmodifiableList(followers);
    }

    public List<UserProfile> getFollowing() {
        return Collections.unmodifiableList(following);
    }

    public Settings getSettings() {
        return settings;
    }

    public void follow(UserProfile user) {
        Objects.requireNonNull(user, "User to follow cannot be null");
        if (!following.contains(user) && !user.equals(this)) {
            following.add(user);
            user.followers.add(this);
            LOGGER.info(username + " started following " + user.getUsername());
            NotificationSystem.sendNotification(user, "You have a new follower: " + username);
        }
    }

    public void unfollow(UserProfile user) {
        Objects.requireNonNull(user, "User to unfollow cannot be null");
        if (following.remove(user)) {
            user.followers.remove(this);
            LOGGER.info(username + " unfollowed " + user.getUsername());
            NotificationSystem.sendNotification(user, "You have lost a follower: " + username);
        }
    }

    public void updateBio(String newBio) {
        this.bio = Objects.requireNonNull(newBio, "New bio cannot be null");
        LOGGER.info(username + " updated their bio");
    }

    public void updateProfilePicture(BufferedImage newProfilePicture) {
        this.profilePicture = Objects.requireNonNull(newProfilePicture, "New profile picture cannot be null");
        LOGGER.info(username + " updated their profile picture");
    }

    public void deleteAccount() {
        LOGGER.info("Deleting account for user: " + username);
        // Unfollow all users this account was following
        new ArrayList<>(following).forEach(this::unfollow);
        // Remove this account from all followers
        new ArrayList<>(followers).forEach(follower -> follower.unfollow(this));
        // Send notification to followers
        followers.forEach(follower ->
                NotificationSystem.sendNotification(follower, "User " + username + " has deleted their account")
        );
        // TODO: Implement actual account deletion logic
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "username='" + username + '\'' +
                ", followerCount=" + getFollowerCount() +
                ", followingCount=" + following.size() +
                '}';
    }
}