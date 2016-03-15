
import java.util.Collection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class LowercaseTrieVocabulary implements Vocabulary {

    // Tests
    public static void main(String[] args) throws Exception {

        // Load the words from the resource file
        // InputStream in = getResourceAsStream("test.txt");
        // BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        BufferedReader fileReader = null;

        fileReader = new BufferedReader(new FileReader("test.txt"));

        ArrayList<String> words = new ArrayList<>(150000);
        // ArrayList<byte[]> wordsInt = new ArrayList<>(150000);
        do {
            String line = fileReader.readLine();
            if (line == null) {
                break;
            }
            if (line.matches("[a-z]+")) {
                words.add(line);
                // wordsInt.add(Alphabet.LOWERCASE.toInt(line));
            }
        } while (true);
        fileReader.close();

        // Create the three vocabularies
        // ListVocabulary listVocabulary = new ListVocabulary(words);
        // TreeVocabulary treeVocabulary = new TreeVocabulary(words);
        LowercaseTrieVocabulary trieVocabulary = new LowercaseTrieVocabulary(words);

        boolean test = trieVocabulary.printPrefix("ap");
        System.out.println(test);
        // trieVocabulary.printTrie(trieVocabulary,1);
        
    }


    
    private boolean isWord = false;
    // The number of possible children is the number of letters in the alphabet
    private LowercaseTrieVocabulary[] children = new LowercaseTrieVocabulary[26];
    // This is the number of actual children
    private int numChildren = 0;

    private char value = '9';
    
    public LowercaseTrieVocabulary() {
    }
    
    public LowercaseTrieVocabulary(Collection<String> words) {
        for (String w:words) {
            add(w);
        }
    }
    
    public boolean add(String s) {
        char first = s.charAt(0);
        // int index = LOWERCASE.getIndex(first);
        int index = first - 97;
        if (index < 0) {
            System.out.println("uf");
        }
        LowercaseTrieVocabulary child = children[index];
        if (child == null) {
            child = new LowercaseTrieVocabulary();
            children[index] = child;
            children[index].value = first;
            numChildren++;
        } else {
            child.value = first;
        }

        if (s.length() == 1) {
            if (child.isWord) {
                // The word is already in the trie
                return false;
            }
            child.isWord = true;
            return true;
        } else {
            // Recurse into sub-trie
            return child.add(s.substring(1));
        }
    }

    /**
     * Searches for a string in this trie
     * @param s
     * @return
     */
    public boolean contains(String s) {
        LowercaseTrieVocabulary n = getNode(s);
        return n != null && n.isWord;
    }
    
    /**
     * Searches for a string prefix in this trie
     * @param s
     * @return
     */
    public boolean isPrefix(String s) {
        LowercaseTrieVocabulary n = getNode(s);
        return n != null && n.numChildren > 0;
    }


    //print the prefixs
    public boolean printPrefix(String s) {
        LowercaseTrieVocabulary n = getNode(s);
        // for(LowercaseTrieVocabulary check: children) {
        //     if(check != null) {
        //         System.out.println(check.value);
        //     }
            
        // }


        Queue<LowercaseTrieVocabulary> q = new LinkedList<LowercaseTrieVocabulary>();
        q.add(n);
        while(!q.isEmpty()) {
            LowercaseTrieVocabulary pop = q.poll();
            for(LowercaseTrieVocabulary check: pop.children) {
                if(check != null) {

                    System.out.print(check.value + " ");
                    if (check.isWord) {
                        System.out.println();
                    }
                    q.add(check);
                }
                 

                
            }
            // System.out.println();

        }


        return n != null && n.numChildren > 0;
    }

    /**
     * Returns the node corresponding to the string
     * @param s
     * @return
     */
    public LowercaseTrieVocabulary getNode(String s) {
        LowercaseTrieVocabulary node = this;
        for (int i = 0; i < s.length(); i++) {
            // int index = LOWERCASE.getIndex(s.charAt(i));
            char first = s.charAt(i);
            int index = first - 97;

            LowercaseTrieVocabulary child = node.children[index];
            if (child == null) {
                // There is no such word
                return null;
            }
            node = child;
        }
        return node;
    }

    /**
     * Searches for a string represented as indices in this trie, 
     * @param s
     * @return
     */
    public boolean contains(byte[] indices, int offset, int len) {
        LowercaseTrieVocabulary n = getNode(indices, offset, len);
        return n != null && n.isWord;
    }
    
    public boolean contains(byte[] indices, int offset) {
        LowercaseTrieVocabulary n = getNode(indices, offset, indices.length-offset);
        return n != null && n.isWord;
    }
    
    /**
     * Searches for a string prefix represented as indices in this trie
     * @param s
     * @return
     */
    public boolean isPrefix(byte[] indices, int offset, int len) {
        LowercaseTrieVocabulary n = getNode(indices, offset, len);
        return n != null && n.numChildren > 0;
    }

    public boolean isPrefix(byte[] indices, int offset) {
        LowercaseTrieVocabulary n = getNode(indices, offset, indices.length-offset);
        return n != null && n.numChildren > 0;
    }

    /**
     * Returns the node corresponding to the string represented as indices
     * @param s
     * @return
     */
    public LowercaseTrieVocabulary getNode(byte[] indices, int offset, int len) {
        LowercaseTrieVocabulary node = this;
        for (int i = 0; i < len; i++) {
            int index = indices[offset+i];
            LowercaseTrieVocabulary child = node.children[index];
            if (child == null) {
                // There is no such word
                return null;
            }
            node=child;
        }
        return node;
    }

    @Override
    public String getName() {
        return getClass().getName();
    }
    
    public boolean isWord() {
        return isWord;
    }
    
    public boolean hasChildren() {
        return numChildren > 0;
    }


    public void printTrie(LowercaseTrieVocabulary node, int offset) {
        // System.out.println(node.value);
        Queue<LowercaseTrieVocabulary> q = new LinkedList<LowercaseTrieVocabulary>();
        q.add(node);
        while(!q.isEmpty()) {
            LowercaseTrieVocabulary pop = q.poll();
            for(LowercaseTrieVocabulary check: pop.children) {
                if(check != null) {
                    System.out.print(check.value + " ");
                    q.add(check);
                }
                
            }
            System.out.println();

        }
        
         // print(node, offset)
         // // here you can play with the order of the children
         // for(Node child : node.getChildren()) {
         //      printTrie(child, offset + 2)
         // } 
    }
    
}