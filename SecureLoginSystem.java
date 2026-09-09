import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecureLoginSystem {

    
    static Map<String, User> users = new HashMap<>();

    
    static final int MAX_ATTEMPTS = 3;
    static final long LOCK_TIME = 30 * 1000; // 30 seconds

    
    static class User {
        String username;
        byte[] passwordHash;
        byte[] salt;

        int failedAttempts = 0;
        long lockUntil = 0;

        User(String username, byte[] passwordHash, byte[] salt) {
            this.username = username;
            this.passwordHash = passwordHash;
            this.salt = salt;
        }
    }

    
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    
    public static byte[] hashPassword(String password, byte[] salt) {
        try {
            PBEKeySpec spec = new PBEKeySpec(
                    password.toCharArray(),
                    salt,
                    65536,
                    256
            );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            return factory.generateSecret(spec).getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Password hashing error!");
        }
    }

    
    public static boolean passwordMatches(byte[] enteredHash, byte[] storedHash) {

        if (enteredHash.length != storedHash.length) {
            return false;
        }

        int result = 0;

        for (int i = 0; i < enteredHash.length; i++) {
            result |= enteredHash[i] ^ storedHash[i];
        }

        return result == 0;
    }

    
    public static void register(String username, String password) {

        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }

        byte[] salt = generateSalt();
        byte[] hash = hashPassword(password, salt);

        User newUser = new User(username, hash, salt);
        users.put(username, newUser);

        System.out.println("Registration successful!");
        System.out.println("Password is stored securely using hashing.");
    }

    
    public static void login(String username, String password) {

        if (!users.containsKey(username)) {
            System.out.println("Invalid username or password.");
            return;
        }

        User user = users.get(username);

        
        if (System.currentTimeMillis() < user.lockUntil) {

            long remaining =
                    (user.lockUntil - System.currentTimeMillis()) / 1000;

            System.out.println(
                    "Account is temporarily locked. Try again in "
                    + (remaining + 1) + " seconds."
            );

            return;
        }

        
        byte[] enteredHash = hashPassword(password, user.salt);

        
        if (passwordMatches(enteredHash, user.passwordHash)) {

            user.failedAttempts = 0;

            System.out.println("Login successful!");
            System.out.println("Welcome, " + username + "!");

        } else {

            user.failedAttempts++;

            System.out.println("Invalid username or password.");

            int remainingAttempts =
                    MAX_ATTEMPTS - user.failedAttempts;

            if (user.failedAttempts >= MAX_ATTEMPTS) {

                user.lockUntil =
                        System.currentTimeMillis() + LOCK_TIME;

                user.failedAttempts = 0;

                System.out.println(
                        "Too many failed attempts!"
                );

                System.out.println(
                        "Account locked for 30 seconds."
                );

            } else {

                System.out.println(
                        "Attempts remaining: "
                        + remainingAttempts
                );
            }
        }
    }

    
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     SECURE LOGIN SYSTEM");
        System.out.println("=================================");

        // Register user
        System.out.println("\n--- Registration ---");

        register("prerit", "java123");

        // Successful login
        System.out.println("\n--- Login 1 ---");
        login("prerit", "java123");

        // Failed login
        System.out.println("\n--- Login 2 ---");
        login("prerit", "wrong123");

        // Failed login
        System.out.println("\n--- Login 3 ---");
        login("prerit", "wrong456");

        // Third failed attempt -> account lock
        System.out.println("\n--- Login 4 ---");
        login("prerit", "wrong789");

        // Attempt login while locked
        System.out.println("\n--- Login 5 ---");
        login("prerit", "java123");
    }
}