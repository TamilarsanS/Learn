package zoho;

import java.util.Scanner;

public class StringPattern {
    public static void main(String[] args) {
        char[] input = new Scanner(System.in).next().toCharArray();
        StringBuilder output = new StringBuilder();
        int index, length = input.length;
        for(int i=0;i<length;i++) {
            for(int j=0;j<length;j++) {
                if(j==i || length-1-j==i)
                    output.append(input[j]);
                else
                    output.append(" ");
            }
            output.append("\n");
        }
        System.out.println(output.toString());
    }
}
