
import java.util.*;
import java.lang.Object;

class bloom {
    public static void main (String[] args) {
        int[] a = {2,3,4,5};
        int[] b = {7,5,8,4};

        double start = System.nanoTime();
        ArrayList<Integer> print1 = findDup(a,b);
        double finish = System.nanoTime();

        double total = (finish - start) / 1000;
        System.out.println("Hash Time: " + String.format( "%.2f", total));
        System.out.println("Hash sol: " + print1);

        double start2 = System.nanoTime();
        ArrayList<Integer> print2 = findDup2(a,b);
        double finish2 = System.nanoTime();

        double total2 = (finish - start) / 1000;
        System.out.println("Bloom Time: " + String.format( "%.2f", total2));
        System.out.println("Bloom sol: " + print2);
    }
    
    public static ArrayList<Integer> findDup (int[] a, int[] b) {
        HashSet<Integer> map = new HashSet<Integer>();
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i : a)
            map.add(i);
        for (int i : b) {
            if (map.contains(i)) {
                out.add(i);
            }
                // found duplicate!   
        }
        return out;
    }

    public static ArrayList<Integer> findDup2 (int[] a, int[] b) {
        BloomFilter<Integer> map = new BloomFilter<Integer>();
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (int i : a)
            map.put(i);
        for (int i : b) {
            if (map.mightContain(i)) {
                out.add(i);
            }
                // found duplicate!   
        }
        return out;
    }
}