import java.util.*;

public class longestSubArr{

     public static void main(String []args){
        int arr[] = {1, 2, 3, 3, 2, 4, 6, 7};
        int ans = longestSubArr(arr);
        System.out.println(ans);
     }
     
     public static int longestSubArr(int[] arr) {
         int max =0;
         int curr = 1;
         
        for(int i = 1;i < arr.length; i++) {
            if(arr[i] > arr[i-1]) {
                curr++;
            } else {
                curr = 1;
            }
            
            if(max < curr) {
                max = curr;
            }
        }
        
        return max;
     }
}
