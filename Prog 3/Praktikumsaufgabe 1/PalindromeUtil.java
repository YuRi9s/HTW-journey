public final class PalindromeUtil {

  private PalindromeUtil() {
    // private constructor to prevent the utility class
    // from getting instantiated
  }

  public static boolean isPalindrome(String word) {
    // null or empty words are considered to be non-palindromic words
    if (word == null || word.isEmpty()) {
      return false;
    }

    // ignore case; e.g., "Anna" -> "anna" and "Salut" -> "salut"
    String lowerCase = word.toLowerCase();

    // reversed; e.g., "anna" -> "anna" and "salut" -> "tulas"
    String reversed = new StringBuilder(lowerCase).reverse().toString();

    // compare the lower case version with its reversed version
    return lowerCase.equals(reversed);
  }

}
