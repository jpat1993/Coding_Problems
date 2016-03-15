import java.io.*;

class bitCount {
    public static void main (String[] args) {
        int in = 4;
        System.out.println(bitCount(10));
    }
    
    public static int bitCount (int value) {
        int count = 0;
        while (value > 0) {           // until all bits are zero
            // System.out.println(value & 1);
            if ((value & 1) == 1)     // check lower bit
                count++;
            value >>>= 1;              // shift bits, removing lower bit
            // System.out.println(value>>1);
        }
        return count;
    }


    // 1010
    // &  1
    // 0000

    // 0101
    // &  1
    // 0001

    // 0010
    // &  1
    // 0000

    // 0001
    // &  1
    // 0001

}