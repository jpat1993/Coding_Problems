import java.util.*;

public class allCombos{

     public static void main(String []args){

        List<String> test = allCombsRec("0Fe", 0);
        
        for(int i=0;i< test.size();i++){
            System.out.println(test.get(i));
        } 
     }
     
    public static List<String> allCombsRec(String s, int pos) {
    
    
        List<String> combs = new ArrayList<String>();
        
        // base case
        if(s==null || s.length()==0) {
            return combs;
        }

        // if the pos is the last one 
        if(pos >= s.length()) {
            combs.add("");
            return combs;
        }

        // Recurse and check through the combos of the suffixes (substring of the end)
        for(String srec: allCombsRec(s , pos+1)) {
           // System.out.println("TEST: " + srec + "  POS: " + pos);
          
            if(Character.isLowerCase(s.charAt(pos))) {
                
                // System.out.println("ADD to Comb" + Character.toUpperCase(s.charAt(pos))+srec + "!");
             
                combs.add(Character.toUpperCase(s.charAt(pos)) + srec);
            } else if(Character.isUpperCase(s.charAt(pos))) {
                
                // System.out.println("ADD to Comb" + Character.toLowerCase(s.charAt(pos))+srec + "!");
             
                combs.add(Character.toLowerCase(s.charAt(pos)) + srec);
            }
            
            System.out.println("ADD 2 to Comb" + s.charAt(pos)+srec  + "!");
            // combs.add(s.charAt(pos) + srec);
        }
        return combs;
    }
}
