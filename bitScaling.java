import java.io.*;

class bitScaling {

    public static void main (String[] args) {
        // System.out.println("test1:" + 2);

        // int test = 2 >> 5;
        // System.out.println("test2:" + test);

        // bitScaling bs = new bitScaling(32000);
        // System.out.println("test2:" + bs.bitSet.length);

        // System.out.println("Check GEt:" + bs.get(5));
        // bs.set(5);
        // System.out.println("check BITSET ARRAY:" + bs.bitSet[0]);


        int[] arr = {1,3,4,2,2,2,2,2,2,5,4,1};

        

        checkDup(arr);
    }

    public static void checkDup(int[] array) {
        boolean[] bitmap = new boolean[32000];

        for(int i=0; i < array.length; i++) {
            int num = array[i];
            if(bitmap[num] == false) {
                bitmap[num] = true;
            } else {
                System.out.println(num);
            }
        }
    }
    




    // BIT SCALING 
    
    // int[] bitSet;

    // public bitScaling(int size) {
    //     bitSet = new int[size >> 5];
    // }

    // boolean get(int pos) {
    //     int word = (pos >> 5);
    //     System.out.println("word: " + word);
    //     int bit = (pos & 0x1f);
    //     System.out.println("bit: " + bit);
    //     return(bitSet[word] & (1 << bit)) != 0;
    // }

    // void set(int pos) {
    //     int word = (pos >> 5);
    //     System.out.println("word: " + word);
    //     int bit = (pos & 0x1f);
    //     System.out.println("bit: " + bit);
    //     bitSet[word] |= 1 << bit;
    // }

}