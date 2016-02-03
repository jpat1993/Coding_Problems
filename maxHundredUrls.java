import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;




/**
 * 
 * 
 * @author Jay Patel
 *
 */

public class maxHundredUrls {

    // Main Method or testing.
    public static void main(String []args) throws FileNotFoundException, IOException {

        BufferedReader fileReader = null;

        fileReader = new BufferedReader(new FileReader("test.csv"));


        HashMap<String, Integer> output = new HashMap<String,Integer>();
        String line = "";
        while ((line = fileReader.readLine()) != null) {
            
            if(output.containsKey(line)) {
                int next = output.get(line) + 1;
                output.put(line, next);
            } else {
                output.put(line, 1);
            }
        }


        // // ~~~~~~~~~~~~~~~~~~~~~ Sorted whole list then print first hundred
        // ArrayList<String> sortedUrls = new ArrayList<String>();

        // sortedUrls = sortUrls(output);

        // for(int i =0 ; i < 100; i++) {
        //     System.out.println("URL: " + sortedUrls.get(i) + " Count: " + output.get(sortedUrls.get(i)));
        // }


        // ~~~~~~~~~~~~~~~~~~~ PQ Min Heap Implementiation.
        PriorityQueue<String> maxHun = new PriorityQueue<String>();

        maxHun = minHeap(output);
        // String[] out = maxHun.toArray();

        while(!maxHun.isEmpty()) {
            String url = maxHun.peek();
            System.out.println("URL: " + maxHun.poll() + " Count : " + output.get(url));
        }
   

    }




        /**
     * Sorts the Vertices
     *
     * @param graph HashMap of the given urls
     * @return Arraylist of urls sorted by counts
     *
     */

    public static ArrayList<String> sortUrls(HashMap<String,Integer> map) {


        // Collections.sort =  O(n log n) algorithm that sorts the vertices by their degree
        ArrayList<String> sortedUrls = new ArrayList<String>(map.keySet());

        Collections.sort(sortedUrls, new Comparator<String>() {
            public int compare(String a, String b) {
                return map.get(b) - map.get(a);
            }
        });

        // Return list of vertices in order from Max -> Min Degree
        return sortedUrls;


    } 




    public static PriorityQueue<String> minHeap(HashMap<String,Integer> map) {

        // PQ
        PriorityQueue<String> test = new PriorityQueue<String> (100, new Comparator<String>() {
            public int compare(String a, String b) {
                return map.get(a) - map.get(b);
            }
        });

        int count = 0;
        for(String url: map.keySet()) {

            if (count > 100) {
                if(map.get(test.peek()) <= map.get(url)) {
                    test.poll();
                    test.add(url);
                }
            } else {
                test.add(url);
                count++;
            }
        }

        return test;

    } 


}