
public class subArray{

     public static void main(String []args){
        
        int arr[] = {15, 2, 4, 8, 9, 5, 10, 23};
        int sum = 23;
        int test = subArr(arr,sum);

     }
     
     
     public static int subArr(int[] arr, int sum) {
         
         int currSum = arr[0];
         int start = 0;
         
         /* Add elements one by one to curr_sum and if the curr_sum exceeds the
       sum, then remove starting element */
       
         for(int i = 1; i< arr.length; i++) {
             
             // If curr_sum exceeds the sum, then remove the starting elements
             while(currSum > sum && start < i-1) {
                 currSum = currSum - arr[start];
                 start++;
             }
             
             // If curr_sum becomes equal to sum, then return true
             if(currSum == sum) {
                 System.out.println("Start: " + start + " end: " + (i-1));
                 return 1;
             }
             
             // Add this element to curr_sum
             if(i < arr.length) {
                 currSum = currSum + arr[i];
             }
             
         }
         
         return 0;
         
     }
}
