import java.util.*;

public class CompareCollection {
    public static void main(String args[]) {
        int[] numberArray = new int[]{1, 2, 3, 2, 4};
        int[] numberFrequency = new int[numberArray.length];
        for (int j : numberArray) {
            numberFrequency[j - 1]++;
        }
        List<NumberFrequency> numberFrequencies = new ArrayList<>();
        for (int j = 0; j < numberFrequency.length; j++) {
            if (numberFrequency[j] > 0) {
                numberFrequencies.add(new NumberFrequency(j + 1, numberFrequency[j]));
            }
        }
        Collections.sort(numberFrequencies, new NumberFrequencyComparator());
        System.out.println(numberFrequencies);
    }

    static class NumberListComparator implements Comparator {

        @Override
        public int compare(Object o, Object t1) {
            return 0;
        }
    }

    static class NumberFrequencyComparator implements Comparator {
        public int compare(Object o, Object t1) {
            NumberFrequency n1 = (NumberFrequency) o;
            NumberFrequency n2 = (NumberFrequency) t1;
            if (n1.frequency > n2.frequency) {
                return 1;
            } else if (n1.frequency == n2.frequency) {
                if (n1.number > n2.number) {
                    return 1;
                } else if (n1.number < n2.number) {
                    return -1;
                } else {
                    return 0;
                }
            }
            return -1;
        }
    }

    static class NumberFrequency {
        int number;
        int frequency;

        public NumberFrequency(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }

        public int getFrequency() {
            return frequency;
        }

        public int getNumber() {
            return number;
        }

        @Override
        public String toString() {
            return this.number + ":" + this.frequency;
        }
    }
}
