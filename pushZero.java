import java.util.*;

public class pushZero{

     public static void main(String []args){

         int arr[] = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9};
         int[] res = pushZero(arr);
         System.out.println(Arrays.toString(res));
     }
     
     public static int[] pushZero(int[] arr) {
         int count = 0;
         
         for(int i =0; i < arr.length; i++) {
             if(arr[i] != 0) {
                 arr[count] = arr[i];
                 count++;
             }
         }
         
         while(count < arr.length) {
             arr[count] = 0;
             count++;
         }
         return arr;
     }
}
