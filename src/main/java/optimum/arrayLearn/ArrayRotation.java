package ArrayLearn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int rotationCount = scanner.nextInt();
        List<Integer> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            array.add(scanner.nextInt());
        }
        rotationCount %= size;
        for (int i = 0; i < rotationCount; i++) {
            Integer remove = array.remove(size - 1);
            array.add(0, remove);
        }
        for (int i = 0; i < size; i++) {
            System.out.print(array.get(i));
            System.out.print(" ");
        }
    }
}
