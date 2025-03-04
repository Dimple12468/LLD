package socialnetwork;

public class Comment {
    private User user;
    private String content;

    public Comment(User user, String content) {
        this.user = user;
        this.content = content;
    }

    @Override
    public String toString() {
        return user.getName() + ": " + content;
    }
}