import java.util.Scanner;

public class TestDrive {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for palindromes
        System.out.println("Enter words to check for palindromes (type 'exit' to stop):");
        
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break; // Exit the loop if user types 'exit'
            }

            if (PalindromeUtil.isPalindrome(input)) {
                System.out.println("PASS: \"" + input + "\" is a palindrome.");
            } else {
                System.out.println("FAIL: \"" + input + "\" is not a palindrome.");
            }
        }

        scanner.close();
    }
}
