package zoho;

import java.util.Scanner;

public class SubstringChcek {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] str1 = scanner.next().toCharArray();
        char[] str2 = scanner.next().toCharArray();
        int index1=0, index2=0;
        while (index1<str1.length) {
            if(str1[index1] == str2[index2]) {
                index1++;
                index2++;
            } else {
                if(index2>0) {
                    index1-=index2;
                    index2=0;
                }
                index1++;
            }
            if(index2==str2.length)
                break;
        }
        if(index2==str2.length)
            System.out.println(index1-index2);
        else
            System.out.println(-1);
    }

    public static void main(String[] args) {
        String input = new Scanner(System.in).nextLine();
        reverseSentence(input);
    }

    static void reverseSentence(String string) {
        int index=0;
        while(index < string.length() && string.charAt(index++) != ' ');
        if(index < string.length())
            reverseSentence(string.substring(index));
        else index++;
        System.out.print(string.substring(0,index-1)+" ");
    }
}
