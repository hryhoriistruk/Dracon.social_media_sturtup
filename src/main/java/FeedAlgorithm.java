import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FeedAlgorithm {
    public List<Post> generateFeed(int userId, int numPosts) {
        return getFollowingPosts(userId).stream()
                .peek(post -> post.setScore(calculatePostScore(post, userId)))
                .sorted(Comparator.comparingDouble(Post::getScore).reversed())
                .limit(numPosts)
                .collect(Collectors.toList());
    }

    private double calculatePostScore(Post post, int userId) {
        return (calculateEngagementScore(post) * 0.4) +
                (calculatePostTypeScore(post) * 0.2) +
                (calculatePostAgeScore(post) * 0.2) +
                (calculateUserRelationshipScore(post, userId) * 0.1) +
                (calculatePostContentScore(post) * 0.1);
    }

    private double calculateEngagementScore(Post post) {
        return (post.getLikes() * 0.5) + (post.getComments() * 0.3) + (post.getShares() * 0.2);
    }

    private double calculatePostTypeScore(Post post) {
        return switch (post.getPostType()) {
            case IMAGE -> 0.8;
            case VIDEO -> 0.6;
            case STORY -> 0.4;
            default -> 0.2;
        };
    }

    private double calculatePostAgeScore(Post post) {
        long hoursDiff = ChronoUnit.HOURS.between(post.getPostDate(), Instant.now());
        if (hoursDiff < 1) return 1.0;
        if (hoursDiff < 24) return 0.8;
        if (hoursDiff < 48) return 0.6;
        return 0.4;
    }

    private double calculateUserRelationshipScore(Post post, int userId) {
        if (isFollowing(userId, post.getPosterId())) return 0.8;
        if (isFriend(userId, post.getPosterId())) return 0.6;
        return 0.4;
    }

    private double calculatePostContentScore(Post post) {
        // TODO: Implement post content scoring based on keywords, hashtags, etc.
        return 0.5;
    }

    private List<Post> getFollowingPosts(int userId) {
        // TODO: Implement getting posts from following users
        // This is just a placeholder implementation
        return List.of(
                new Post(1, "Image post", PostType.IMAGE, 10, 5, 2, Instant.now()),
                new Post(2, "Video post", PostType.VIDEO, 20, 10, 5, Instant.now().minus(1, ChronoUnit.DAYS)),
                new Post(3, "Story post", PostType.STORY, 15, 7, 3, Instant.now().minus(2, ChronoUnit.HOURS)),
                new Post(4, "Image post", PostType.IMAGE, 12, 6, 2, Instant.now().minus(3, ChronoUnit.DAYS)),
                new Post(5, "Video post", PostType.VIDEO, 25, 12, 6, Instant.now().minus(30, ChronoUnit.MINUTES))
        );
    }

    private boolean isFollowing(int userId, int posterId) {
        // TODO: Implement checking if the user is following the poster
        return true;
    }

    private boolean isFriend(int userId, int posterId) {
        // TODO: Implement checking if the user is friends with the poster
        return true;
    }
}

class Post {
    private final int id;
    private final String postContent;
    private final PostType postType;
    private final int likes;
    private final int comments;
    private final int shares;
    private final Instant postDate;
    private final int posterId;
    private double score;

    public Post(int id, String postContent, PostType postType, int likes, int comments, int shares, Instant postDate) {
        this.id = id;
        this.postContent = postContent;
        this.postType = postType;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.postDate = postDate;
        this.posterId = 1; // Set the poster ID to 1 for demonstration purposes
    }

    // Getters
    public int getId() { return id; }
    public String getPostContent() { return postContent; }
    public PostType getPostType() { return postType; }
    public int getLikes() { return likes; }
    public int getComments() { return comments; }
    public int getShares() { return shares; }
    public Instant getPostDate() { return postDate; }
    public int getPosterId() { return posterId; }
    public double getScore() { return score; }

    public void setScore(double score) { this.score = score; }
}

enum PostType {
    IMAGE, VIDEO, STORY
}