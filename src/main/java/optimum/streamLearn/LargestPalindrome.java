package StreamLearn;

import java.time.LocalTime;
import java.util.stream.IntStream;

public class LargestPalindrome {

    public static void main(String[] args) {
        String input = "abcbaerteghyayhg";
//        System.out.println(LocalTime.now());
        String largestPalindrome = findLargestPalindrome(input);
        System.out.println("Largest Palindrome: " + largestPalindrome);
//        System.out.println(LocalTime.now());
    }

    public static String findLargestPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        return IntStream.range(0, input.length())
                .mapToObj(i -> expandAroundCenter(input, i))
                .reduce("", (s1, s2) -> s1.length() > s2.length() ? s1 : s2);
    }

    private static String expandAroundCenter(String s, int i) {
        int n = s.length();
        int start = i, end = 1;
        int low, hi;

        // Find longest palindromic substring of even size
        low = i - 1;
        hi = i;

        while (low >= 0 && hi < n && s.charAt(low) == s.charAt(hi)) {

            if (hi - low + 1 > end) {
                start = low;
                end = hi - low + 1;
            }
            low--;
            hi++;
        }

        // Find longest palindromic substring of odd size
        low = i - 1;
        hi = i + 1;

        // Expand substring while it is palindrome and in bounds
        while (low >= 0 && hi < n && s.charAt(low) == s.charAt(hi)) {

            // Update maximum length and starting index
            if (hi - low + 1 > end) {
                start = low;
                end = hi - low + 1;
            }
            low--;
            hi++;
        }
        return s.substring(start, start + end);
    }
}
