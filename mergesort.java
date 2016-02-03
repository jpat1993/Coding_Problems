import java.util.* ;


public class mergesort{

     public static void main(String []args) {

        int[] one = {3, 1, 5};
        int[] two = {2, 6, 4};

        int[] out = doubleMerge(one, two);
        System.out.println(Arrays.toString(out));

    }


    public static int[] doubleMerge(int[] arr1, int[] arr2) {

        int[] out = new int[arr1.length + arr2.length];

        int count = 0;

        for(int i = 0; i < arr1. length; i++) {
            out[count] = arr1[i];
            count++;
        }
        for(int k = 0; k < arr2. length; k++) {
            out[count] = arr2[k];
            count++;
        }
         // System.out.println(Arrays.toString(out));


        // int count =0;
        // // int count2 =0;

        // int counter =0;
        // while(count < arr1.length) {
        //     if(arr1[count] <= arr2[count]) {  
        //         out[counter] = arr1[count];
        //         out[counter+1] = arr2[count];
        //         count++;
        //         counter+= 2;
        //     } else {
        //         out[counter] = arr2[count];
        //         out[counter+1] = arr1[count];
        //         count++;
        //         counter+= 2;
        //     }

        // }
        int[] output=  pq(out);
        return output;

    }


    public static int[] pq(int[] arr) {

        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int[] out = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            test.add(arr[i]);
        }

        int j = 0;
        while(!test.isEmpty()) {
            int one = test.poll();
            out[j] =one;
            j++;
        }

        return out;
    }

    public static int[] mergesort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
        return array;
    }



    public static void mergesort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle); // Sort left half
            mergesort(array, helper, middle+1, high); // Sort right half
            merge(array, helper, low, middle, high); // Merge them
        }
    }

    public static void merge(int[] array, int[] helper, int low, int middle, int high) {
     /* Copy both halves into a helper array */

     // System.out.println("low :" + low + " middle : " + middle + " high: " + high);
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

     /* Iterate through helper array. Compare the left and right
     * half, copying back the smaller element from the two halves
     * into the original array. */


        while (helperLeft <= middle && helperRight <= high) {

            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else { // If right element is smaller than left element
                array[current] = helper[helperRight];
                helperRight++;
            }

            current++;
        }
        /* Copy the rest of the left side of the array into the
        * target array */
        int remaining = middle - helperLeft;

        // System.out.println("array: " + Arrays.toString(array));

        // System.out.println("helper: " + Arrays.toString(helper));

        for (int i = 0; i <= remaining; i++) {
            array [current + i] = helper[helperLeft + i];
        }
    }



}