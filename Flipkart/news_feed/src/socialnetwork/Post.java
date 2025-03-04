package socialnetwork;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Post {
    private static int counter = 0;
    private static Map<Integer, Post> postRegistry = new HashMap<>();

    private int id;
    private User user;
    private String content;
    private int upvotes, downvotes;
    private List<Comment> comments;
    private LocalDateTime timestamp;


    public Post(User user, String content) {
        this.id = ++counter;
        this.user = user;
        this.content = content;
        this.upvotes = 0;
        this.downvotes = 0;
        this.comments = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
        postRegistry.put(id, this);
    }

    public void upvote() {
        upvotes++;
    }

    public void downvote() {
        downvotes++;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public int getScore() {
        return upvotes - downvotes;
    }

    public static Post getPostById(int id) {
        return postRegistry.get(id);
    }

    @Override
    public String toString() {
        String formattedTimestamp = (timestamp != null) 
            ? timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a"))
            : "Unknown Time"; 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return String.format("id: %03d\n(%d upvotes, %d downvotes)\n%s\n%s\n%s", 
            id, upvotes, downvotes, user.getName(), content, timestamp.format(formatter));
    }
}