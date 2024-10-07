import java.util.HashMap;
import java.util.Map;

public class UserLogin {
    private Map<String, String> users = new HashMap<>(); //to save users and their pass in pair
    private Map<String, Integer> failedAttempts = new HashMap<>(); //to record number of tries
    private static final int maxAttemepts = 3; //maximum of tries allowed to login

    public void createUser(String username, String password) {
        users.put(username, password);
    }

    public String login(String username, String password) {
        // Check if account is locked after 3 failed attempts
        if (failedAttempts.getOrDefault(username, 0) >= maxAttemepts) {
            return "Account locked due to too many failed login attempts.";
        }

        // Validate username and password
        if (!users.containsKey(username) || !users.get(username).equals(password)) {
            failedAttempts.put(username, failedAttempts.getOrDefault(username, 0) + 1);
            return "Invalid username or password.";
        }

        // Reset failed attempts on successful login
        failedAttempts.put(username, 0);
        return "Login successful! Redirecting to dashboard.";
    }
}
