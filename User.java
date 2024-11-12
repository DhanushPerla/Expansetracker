import java.util.HashMap;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private static HashMap<String, String> userDatabase = new HashMap<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static boolean registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            System.out.println("User already exists.");
            return false;
        }
        userDatabase.put(username, password);
        System.out.println("User registered successfully!");
        return true;
    }

    public static boolean loginUser(String username, String password) {
        return userDatabase.getOrDefault(username, "").equals(password);
    }
}
