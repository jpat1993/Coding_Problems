import java.util.*;

public class isScorePossible{

     public static void main(String []args){
        System.out.println("Hello World");
        int[] test = {3,7};
        System.out.println(isScorePossible(test, 10));
     }
     
     public static boolean isScorePossible(int[] points, int value){
        int status [] =new int [value+1];
        System.out.println(Arrays.toString(status));
        
        status [0]=1;
        for (int i=0;i<points.length;++i){
            
            for (int j=points[i]; j <= value;++j){
                
                status[j]+=status[j-points[i]];
                
                System.out.println(Arrays.toString(status));

            }
        }
        
        return status[value]>0;
     }
}
