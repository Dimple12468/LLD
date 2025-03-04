package socialnetwork;

import java.util.*;

public class SocialNetworkApp {
    static Map<String, User> users = new HashMap<>();
    static User currentUser = null;

    public static void main(String[] args) {
        System.out.println("Enter Input:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split("~");
            switch (input[0].toLowerCase()) {
                case "signup":
                    users.put(input[1], new User(input[1]));
                    break;
                case "login":
                    currentUser = users.get(input[1]);
                    if (currentUser != null) currentUser.showNewsFeed();
                    break;
                case "logout":
                    currentUser = null;
                    break;
                case "post":
                    if (currentUser != null) currentUser.post(input[1]);
                    break;
                case "follow":
                    if (currentUser != null && users.containsKey(input[1])) 
                        currentUser.follow(users.get(input[1]));
                    break;
                case "upvote":
                    if (currentUser != null) currentUser.upvote(Integer.parseInt(input[1]));
                    break;
                case "downvote":
                    if (currentUser != null) currentUser.downvote(Integer.parseInt(input[1]));
                    break;
                case "reply":
                    if (currentUser != null) currentUser.reply(Integer.parseInt(input[1]), input[2]);
                    break;
                case "shownewsfeed":
                    if (currentUser != null) currentUser.showNewsFeed();
                    break;
            }
        }
        scanner.close();
    }
}