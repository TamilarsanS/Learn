package StreamLearn;

import java.util.LinkedHashSet;

public class HashLearn {
    public static void main(String[] args) {
        printNonDuplicateCharacter("I i LIKE like To to CODE code IN in JAVA");
    }

    private static void printNonDuplicateCharacter(String s) {
        LinkedHashSet<Character> hashSet = new LinkedHashSet<>();
        for (char a: s.toCharArray()) {
            if(a==' ')
                continue;
            hashSet.add(a);
        }
        hashSet.forEach(System.out::print);
    }
}
