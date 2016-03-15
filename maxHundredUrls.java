import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.io.*;




/**
 * 
 * 
 * @author Jay Patel
 *
 */

public class maxHundredUrls {

    private File file;
    private ConcurrentHashMap<String, Integer> map;

    public maxHundredUrls() {
        this.file = new File("test.csv");
        this.map = new ConcurrentHashMap<String, Integer>();
    }


    // Main Method or testing.
    public static void main(String []args) throws FileNotFoundException, IOException,Exception {


        //~~~~~~~~~~~~~~~~concurrency~~~~~~~~~~~~~~~~~//

        long startTime = System.nanoTime();

        maxHundredUrls s = new maxHundredUrls();
        ConcurrentHashMap<String, Integer> test = s.processAll(8, 10000);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000L;
        System.out.println("Concurrent Time to Put into Map:  " + duration + " ms" );

        // ~~~~~~~~~~~~~ConCurr End~~~~~~~~~~~~~~//


        // ~~~~~~~~~~ Regular Beign~~~~~~~~~~~~~`//
        long startTime2 = System.nanoTime();
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
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2) / 1000000L;
        System.out.println("Reg Time to Put into Map:  " + duration2 + " ms" );

        //~~~~~~~~~~~~~ REgular end~~~~~~~~~~~```//

        // // // ~~~~~~~~~~~~~~~~~~~~~ Sorted whole list then print first hundred
        // // ArrayList<String> sortedUrls = new ArrayList<String>();

        // // sortedUrls = sortUrls(output);

        // // for(int i =0 ; i < 100; i++) {
        // //     System.out.println("URL: " + sortedUrls.get(i) + " Count: " + output.get(sortedUrls.get(i)));
        // // }


        // // ~~~~~~~~~~~~~~~~~~~ PQ Min Heap Implementiation.
        // System.out.println("~~~~~~~~~~~~~~Regular~~~~~~~~~~~~~~~~~~~~~~");
        // PriorityQueue<String> maxHun = new PriorityQueue<String>();

        // maxHun = minHeap(output);
        // // String[] out = maxHun.toArray();

        // while(!maxHun.isEmpty()) {
        //     String url = maxHun.peek();
        //     System.out.println("URL: " + maxHun.poll() + " Count : " + output.get(url));
        // }

        // // ~~~~~~~~~~~~~~~~~~~Concurrent PQ Min Heap Implementiation.
        // System.out.println("~~~~~~~~~~~~~~Concurrent~~~~~~~~~~~~~~~~~~~~~~");
        // PriorityQueue<String> maxHun2 = new PriorityQueue<String>();

        // maxHun2 = minHeap(test);
        // // String[] out = maxHun.toArray();

        // while(!maxHun2.isEmpty()) {
        //     String url2 = maxHun2.peek();
        //     System.out.println("URL2: " + maxHun2.poll() + " Count : " + test.get(url2));
        // }
   

    }

    // Splits the computation into chunks of the given size,
    // creates appropriate tasks and runs them using a 
    // given number of threads.
    public ConcurrentHashMap<String, Integer> processAll(int noOfThreads, int chunkSize) throws Exception {

        // Get number of Lines 
        long startNum = System.nanoTime();

        int numLines = getLines(file);
        System.out.println("Num of Lines: " + numLines);

        long endNum = System.nanoTime();
        long durationNum = (endNum - startNum) / 1000000L;
        System.out.println("Time to get Lines:  " + durationNum + " ms" );





        // System.out.println(file.length());
        // int count = (int)((file.length() + chunkSize - 1) / chunkSize);
        int count = (int)((numLines + chunkSize - 1) / chunkSize);
        System.out.println("Count of Tasks : " + count);

        // int count = 5;
        java.util.List<Callable<String>> tasks = new ArrayList<Callable<String>>(count);




        for(int i = 0; i < count; i++) {
            tasks.add(processPartTask(i * chunkSize, Math.min(numLines, (i+1) * chunkSize)));
            // tasks.add(processPartTask(i * chunkSize, (i+1) * chunkSize - 1));

        }
            
        ExecutorService es = Executors.newFixedThreadPool(noOfThreads);

        java.util.List<Future<String>> results = es.invokeAll(tasks);
        es.shutdown();

        // // use the results for something
        // for(Future<String> result : results){
        //     System.out.println(result.get());
        // }
        // map.put("url-29", 1);
        // System.out.println(map.get("url-0"));
        return map;
            
    }


    // Creates a task that will process the given portion of the file,
    // when executed.
    public Callable<String> processPartTask(final int start, final int end) {
        return new Callable<String>() {
            public String call()
                throws Exception
            {
                return processPart(start, end);
            }
        };
    }

    // Processes the given portion of the file.
    // Called simultaneously from several threads.
    // Use your custom return type as needed, I used String just to give an example.
    public String processPart(int start, int end) throws Exception {
        // BufferedReader is = null;
        // is = new BufferedReader(new FileReader(file));


        // int i = 0;
        // for(i = 0; i < start; i++) {
        //     is.readLine();
        // }
        // is.skip(start);

        // do a computation using the input stream,
        // checking that we don't read more than (end-start) bytes

        ArrayList<String> list = listOfLines(file, start, end);

        // System.out.println("Computing the part from " + start + " to " + end);

        // while(i < end && is.readLine() != null) {
            // String line = is.readLine();
        for(String line : list) {
            // System.out.println(line + " " + map.containsKey(line) + "< " + start + ", " + end + " >");

            if(map.containsKey(line)) {
                int next = map.get(line) + 1;
                map.put(line, next);
            } else {
                map.put(line, 1);
            }
        }
            
        // }
        

        // Thread.sleep(5);
        // System.out.println("Finished the part from " + start + " to " + end);

        return "Some result";
    }


    public int getLines(File file) throws FileNotFoundException, IOException,Exception {
        BufferedReader is = null;
        is = new BufferedReader(new FileReader(file));
        int count =0;
        while(is.readLine() != null) {
            count++;
        }
        is.close();
        return count;
    }

    public ArrayList<String> listOfLines(File file, int start, int end) throws FileNotFoundException, IOException,Exception {
        BufferedReader is = null;
        is = new BufferedReader(new FileReader(file));

        ArrayList<String> lines = new ArrayList<String>();
        String readLine;
        int curr =1;
        while ((readLine = is.readLine()) != null && curr < end)   {
            if (curr++ <= start) {
                continue;
            }

            lines.add(readLine);
        }

        is.close();

        return lines;
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




    public static PriorityQueue<String> minHeap(Map<String,Integer> map) {

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