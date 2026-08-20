import java.util.Scanner;

public class passwordchecker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("          PASSWORD STRENGTH CHECKER");
        System.out.println("==============================================");

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        int score = 0;

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        // Check password characters
        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            }
            else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            }
            else if (Character.isDigit(ch)) {
                hasNumber = true;
            }
            else {
                hasSpecial = true;
            }
        }

        // Length score
        if (password.length() >= 8) {
            score++;
        }

        if (password.length() >= 12) {
            score++;
        }

        // Character type scores
        if (hasUppercase) {
            score++;
        }

        if (hasLowercase) {
            score++;
        }

        if (hasNumber) {
            score++;
        }

        if (hasSpecial) {
            score++;
        }

        // Display analysis
        System.out.println("\n-------------- PASSWORD ANALYSIS --------------");

        System.out.println("Password Length     : " + password.length());
        System.out.println("Uppercase Letters  : " + (hasUppercase ? "Yes" : "No"));
        System.out.println("Lowercase Letters  : " + (hasLowercase ? "Yes" : "No"));
        System.out.println("Numbers            : " + (hasNumber ? "Yes" : "No"));
        System.out.println("Special Characters : " + (hasSpecial ? "Yes" : "No"));

        // Strength classification
        String strength;

        if (score <= 2) {
            strength = "WEAK";
        }
        else if (score <= 4) {
            strength = "MEDIUM";
        }
        else {
            strength = "STRONG";
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Password Strength   : " + strength);
        System.out.println("-----------------------------------------------");

        // Suggestions
        if (!hasUppercase || !hasLowercase || !hasNumber ||
                !hasSpecial || password.length() < 8) {

            System.out.println("\nSuggestions to improve your password:");

            if (password.length() < 8) {
                System.out.println("- Use at least 8 characters.");
            }

            if (!hasUppercase) {
                System.out.println("- Add at least one uppercase letter (A-Z).");
            }

            if (!hasLowercase) {
                System.out.println("- Add at least one lowercase letter (a-z).");
            }

            if (!hasNumber) {
                System.out.println("- Add at least one number (0-9).");
            }

            if (!hasSpecial) {
                System.out.println("- Add at least one special character (!, @, #, $, etc.).");
            }

        } else {
            System.out.println("\nExcellent! Your password meets all");
            System.out.println("the basic security requirements.");
        }

        System.out.println("\n==============================================");
        System.out.println("        Password Check Completed");
        System.out.println("==============================================");

        sc.close();
    }
}