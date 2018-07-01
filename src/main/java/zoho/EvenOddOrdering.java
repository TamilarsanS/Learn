package zoho;

import java.util.ArrayList;
import java.util.List;

public class EvenOddOrdering {
    public static void main(String[] args) {
        int[] input = new int[]{13,2,4,15,12,10,5};
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        for(int i=0;i<input.length;i++) {
            if(i%2!=0)
                evenList.add(input[i]);
            else
                oddList.add(input[i]);
        }
        oddList.sort((e1,e2)->e2-e1);
        evenList.sort(Integer::compareTo);
        for (int i=0;i<input.length;i++) {
            if(i%2==0)
                System.out.print(oddList.get(i/2) + " ");
            else
                System.out.print(evenList.get(i/2) + " ");
        }
    }
}
