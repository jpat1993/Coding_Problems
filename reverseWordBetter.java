import java.util.*;
public class reverseWordBetter{

     public static void main(String []args){
        String  ans = reverseWordBetter("I am cool");
        // String  ans = reverseWordBetter("I am, ,3 ,5 , cool");
        System.out.println(ans);
     }
     
     public static String reverseWordBetter(String sent) {
        String delims = "[ ]+";
        // String delims = "[ ,]+";
        String[] tokens = sent.split(delims);
        StringBuilder out = new StringBuilder();
        // String out = "";
        
        for (int i = tokens.length-1; i >= 0; i-- ) {
            out.append(tokens[i] + " ");
            //out += tokens[i] + " ";
        }
        //out.trim();
        return out.toString().trim();
        

     }
}



