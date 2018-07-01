package zoho;

import java.util.Scanner;

public class NumberEncrypt {
    public static void main(String[] args) {
        String input = new Scanner(System.in).next();
        StringBuilder sb = new StringBuilder();
        int times = 0;
        for(char c:input.toCharArray()) {
            if(Character.isAlphabetic(c)) {
                for(int i=0;i<times-1;i++)
                    sb.append(sb.charAt(sb.length()-1));
                times = 0;
                sb.append(c);
            }
            else times = (times * 10) + (c-'0');
        }
        for(int i=0;i<times-1;i++)
            sb.append(sb.charAt(sb.length()-1));
        System.out.println(sb.toString());
    }
}
