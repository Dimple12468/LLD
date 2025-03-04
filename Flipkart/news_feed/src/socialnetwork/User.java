package socialnetwork;

import java.util.*;

public class User {
    private String name;
    private List<Post> posts;
    private Set<User> following;

    public User(String name) {
        this.name = name;
        this.posts = new ArrayList<>();
        this.following = new HashSet<>();
    }

    public void post(String content) {
        Post newPost = new Post(this, content);
        posts.add(newPost);
    }

    public void follow(User user) {
        following.add(user);
    }

    public void upvote(int postId) {
        Post post = Post.getPostById(postId);
        if (post != null) post.upvote();
    }

    public void downvote(int postId) {
        Post post = Post.getPostById(postId);
        if (post != null) post.downvote();
    }

    public void reply(int postId, String content) {
        Post post = Post.getPostById(postId);
        if (post != null) post.addComment(new Comment(this, content));
    }

    public void showNewsFeed() {
        List<Post> feed = new ArrayList<>();
        for (User user : following) {
            feed.addAll(user.posts);
        }
        feed.sort(Comparator.comparing(Post::getScore).reversed());
        for (Post post : feed) {
            if (post != null) {
                System.out.println(post);
            }
        }
    }

    public String getName() {
        return name;
    }
}