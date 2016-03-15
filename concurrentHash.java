 
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


 
public class concurrentHash {
 
    public final static int THREAD_POOL_SIZE = 5;
 
    // public static Map<String, Integer> crunchifyHashTableObject = null;
    // public static Map<String, Integer> crunchifySynchronizedMapObject = null;
    // public static Map<String, Integer> crunchifyConcurrentHashMapObject = null;
 
    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
 
        // Test with Hashtable Object
        // crunchifyHashTableObject = new Hashtable<String, Integer>();
        // crunchifyPerformTest(crunchifyHashTableObject);
 
        // // Test with synchronizedMap Object
        // crunchifySynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
        // crunchifyPerformTest(crunchifySynchronizedMapObject);   


        // ~~~~~~~~~~~~~~~~~~~ PQ Min Heap Implementiation~~~~~~~~~//
        long start1 = System.nanoTime();

        PriorityQueue<String> maxHun = new PriorityQueue<String>();

        //~~~~~~~~~~~Regular Read~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        HashMap<String, Integer> regularHash = new HashMap<String,Integer>();
        try {
            regRead(regularHash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        maxHun = minHeap(regularHash);
        // String[] out = maxHun.toArray();

        while(!maxHun.isEmpty()) {
            String url = maxHun.peek();
            System.out.println("URL: " + maxHun.poll() + " Count : " + regularHash.get(url));
        }



        long end1 = System.nanoTime();
        long duration1 = (end1 - start1) / 1000000L;

        //~~~~~~~~~~~~~~~~~~~~~REG END~~~~~~~~~~~~~~~~~~~~~//
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

        // ~~~~~~~~~~~~~~~~~~~ Concurrent PQ Min Heap Implementiation~~~~~~~~~//
        long start2 = System.nanoTime();

        PriorityQueue<String> maxHun2 = new PriorityQueue<String>();

        //~~~~~~~~~~~ConcurrentHashMap Object~~~~~~~~~~~~~~~~~~~~~~~~//
        Map<String, Integer> concurrentHash = new ConcurrentHashMap<String, Integer>();
        try {
            crunchifyPerformTest(concurrentHash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        maxHun2 = minHeap(concurrentHash);
        // String[] out = maxHun.toArray();

        while(!maxHun2.isEmpty()) {
            String url = maxHun2.peek();
            System.out.println("URL: " + maxHun2.poll() + " Count : " + concurrentHash.get(url));
        }



        long end2 = System.nanoTime();
        long duration2 = (end2 - start2) / 1000000L;

        //~~~~~~~~~~~~~~~~~~~~~Concurrent END~~~~~~~~~~~~~~~~~~~~~//


//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

        // ~~~~~~~~~~~~~~~~~~~ Stream PQ Min Heap Implementiation~~~~~~~~~//
        long start3 = System.nanoTime();

        PriorityQueue<String> maxHun3 = new PriorityQueue<String>();

        //~~~~~~~~~~~Stream Object~~~~~~~~~~~~~~~~~~~~~~~~//
        Map<String, Integer> con2 = new HashMap<String, Integer>();
        try {
            streamRead(con2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        maxHun3 = minHeap(con2);
        // String[] out = maxHun.toArray();

        while(!maxHun3.isEmpty()) {
            String url = maxHun3.peek();
            System.out.println("URL: " + maxHun3.poll() + " Count : " + con2.get(url));
        }



        long end3 = System.nanoTime();
        long duration3 = (end3 - start3) / 1000000L;

        //~~~~~~~~~~~~~~~~~~~~~Con2 END~~~~~~~~~~~~~~~~~~~~~//


        System.out.println("Regular TOTAL:  " + duration1 + " ms" );
        System.out.println("Concurrent TOTAL:  " + duration2 + " ms" );
        System.out.println("Stream  TOTAL:  " + duration3 + " ms" );


 
    }

    public static void regRead(HashMap<String, Integer> output)  throws InterruptedException, FileNotFoundException, IOException{
         // ~~~~~~~~~~~~~~~~Regular~~~~~~~~~~~~~~//

        long start2 = System.nanoTime();
        BufferedReader fileReader = null;
        fileReader = new BufferedReader(new FileReader("test.csv"));

        
        String line = "";
        while ((line = fileReader.readLine()) != null) {
            
            if(output.containsKey(line)) {
                int next = output.get(line) + 1;
                output.put(line, next);
            } else {
                output.put(line, 1);
            }
        }


        long end2 = System.nanoTime();
        long duration2 = (end2 - start2) / 1000000L;

        System.out.println("Regular Read:  " + duration2 + " ms" );

        // ~~~~~~~~~~~~~~~~~REgular END~~~~~~~~~~~~~~~~~~//
    }

    public static void streamRead(Map<String, Integer> output)  throws InterruptedException, FileNotFoundException, IOException{
         // ~~~~~~~~~~~~~~~~Con2~~~~~~~~~~~~~~//

        long start2 = System.nanoTime();
        // BufferedReader fileReader = null;
        // fileReader = new BufferedReader(new FileReader("test.csv"));

        Path file = Paths.get("test.csv");

        try {
            Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8);

            for(String line : (Iterable<String>) lines :: iterator) {
                if(output.containsKey(line)) {
                    int next = output.get(line) + 1;
                    output.put(line, next);
                } else {
                    output.put(line, 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        long end2 = System.nanoTime();
        long duration2 = (end2 - start2) / 1000000L;

        System.out.println("Stream Read:  " + duration2 + " ms" );

        // ~~~~~~~~~~~~~~~~~Con2 END~~~~~~~~~~~~~~~~~~//
    }
 
    public static void crunchifyPerformTest(final Map<String, Integer> crunchifyThreads) throws InterruptedException, FileNotFoundException, IOException {
 
        System.out.println("Test started for: " + crunchifyThreads.getClass());
        // long averageTime = 0;
        // for (int i = 0; i < 5; i++) {
 
        long startTime = System.nanoTime();
        ExecutorService crunchifyExServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        
        final BufferedReader fileReader = new BufferedReader(new FileReader("test.csv"));

        // HashMap<String, Integer> output = new HashMap<String,Integer>();
        
        


        for (int j = 0; j < THREAD_POOL_SIZE; j++) {
            crunchifyExServer.execute(new Runnable() {
                @SuppressWarnings("unused")
                @Override
                public void run() {
                    String line = "";
                    try {
                        while ((line = fileReader.readLine()) != null) {
                
                            if(crunchifyThreads.containsKey(line)) {
                                int next = crunchifyThreads.get(line) + 1;
                                crunchifyThreads.put(line, next);
                            } else {
                                crunchifyThreads.put(line, 1);
                            }
                        }
                    } catch(IOException e) {
                        e.printStackTrace();
                    }

                    // for (int i = 0; i < 500000; i++) {
                    //     Integer crunchifyRandomNumber = (int) Math.ceil(Math.random() * 550000);

                    //     // Retrieve value. We are not using it anywhere
                    //     Integer crunchifyValue = crunchifyThreads.get(String.valueOf(crunchifyRandomNumber));

                    //     // Put value
                    //     crunchifyThreads.put(String.valueOf(crunchifyRandomNumber), crunchifyRandomNumber);
                    // }
                }
            });
        }

        // Make sure executor stops
        crunchifyExServer.shutdown();

        // Blocks until all tasks have completed execution after a shutdown request
        crunchifyExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

        long entTime = System.nanoTime();
        long totalTime = (entTime - startTime) / 1000000L;
        // averageTime += totalTime;
        System.out.println("Concurrent Hash Read: " + totalTime + " ms");
        // }
        // System.out.println("For " + crunchifyThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
        // System.out.println(crunchifyThreads.get("url-43"));
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