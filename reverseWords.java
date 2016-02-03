import java.util.*;

public class reverseWords{

     public static void main(String []args){
         
        String[] test = "I like cats".split(" ");
        String out = "";
        int count = test.length -1;
        while(count >= 0) {
            out+= test[count] + " ";
            count--;
        }
        System.out.println(out);
        
        
     }
     

}
