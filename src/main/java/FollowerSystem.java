import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;

public class FollowerSystem {
    private static final Logger LOGGER = Logger.getLogger(FollowerSystem.class.getName());

    public static boolean follow(UserProfile user, UserProfile toFollow) {
        if (user == null || toFollow == null) {
            LOGGER.warning("Invalid user profiles provided for follow operation");
            return false;
        }

        if (user.equals(toFollow)) {
            LOGGER.warning("User " + user.getUsername() + " attempted to follow themselves");
            return false;
        }

        if (user.getFollowing().contains(toFollow)) {
            LOGGER.info("User " + user.getUsername() + " is already following " + toFollow.getUsername());
            return false;
        }

        if (toFollow.getSettings().getSetting(Settings.SettingKey.PRIVATE_ACCOUNT)) {
            // For private accounts, we might want to create a follow request instead
            LOGGER.info("Created follow request for private account: " + toFollow.getUsername());
            createFollowRequest(user, toFollow);
            return true;
        }

        user.follow(toFollow);
        LOGGER.info("User " + user.getUsername() + " successfully followed " + toFollow.getUsername());
        return true;
    }

    public static boolean unfollow(UserProfile user, UserProfile toUnfollow) {
        if (user == null || toUnfollow == null) {
            LOGGER.warning("Invalid user profiles provided for unfollow operation");
            return false;
        }

        if (!user.getFollowing().contains(toUnfollow)) {
            LOGGER.info("User " + user.getUsername() + " is not following " + toUnfollow.getUsername());
            return false;
        }

        user.unfollow(toUnfollow);
        LOGGER.info("User " + user.getUsername() + " successfully unfollowed " + toUnfollow.getUsername());
        return true;
    }

    public static List<UserProfile> getFollowers(UserProfile user) {
        if (user == null) {
            LOGGER.warning("Invalid user profile provided for getting followers");
            return new ArrayList<>();
        }
        return new ArrayList<>(user.getFollowers());
    }

    public static List<UserProfile> getFollowing(UserProfile user) {
        if (user == null) {
            LOGGER.warning("Invalid user profile provided for getting following");
            return new ArrayList<>();
        }
        return new ArrayList<>(user.getFollowing());
    }

    private static void createFollowRequest(UserProfile user, UserProfile toFollow) {
        // TODO: Implement follow request logic for private accounts
        LOGGER.info("Follow request created: " + user.getUsername() + " -> " + toFollow.getUsername());
    }
}