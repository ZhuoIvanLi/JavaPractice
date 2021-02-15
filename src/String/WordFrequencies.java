package String;

import java.util.HashMap;

/**
 * 16.2 Word Frequencies: find the frequency of occurrences of a word in a book.
 */
public class WordFrequencies {
    int countWord(String[] book, String word){
        word = word.trim().toLowerCase();
        int count = 0;

        for(String b : book){
            if(b.trim().toLowerCase().equals(word)){
                count++;
            }
        }

        return count;
    }

    HashMap<String, Integer> setupDictionary(String[] book){
        HashMap<String, Integer> hm = new HashMap<>();

        for(String b : book){
            if(b !=null && !b.trim().equals("")){
                b = b.trim().toLowerCase();
                if(hm.containsKey(b)){
                    hm.put(b, hm.get(b) + 1);
                }else{
                    hm.put(b, 1);
                }
            }
        }
        return hm;
    }

    int getFrequency(HashMap<String, Integer> m, String word) {
        if(m.isEmpty() || word == null) return -1;

        word = word.trim().toLowerCase();
        return m.getOrDefault(word, 0);
    }
}
