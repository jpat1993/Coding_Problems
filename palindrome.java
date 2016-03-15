import java.util.*;
public class palindrome{

     public static void main(String []args){
        boolean ans = palindrome("potrop");
        System.out.println(ans);
     }
     
     public static boolean palindrome(String word) {
         return word.equals(new StringBuilder(word).reverse().toString());
     }
}
