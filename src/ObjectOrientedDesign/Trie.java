package ObjectOrientedDesign;

/**
 * 208. Implement Trie (Prefix Tree): Trie (we pronounce "try") or prefix tree is a tree data structure used to retrieve
 * a key in a strings dataset. There are various applications of this very efficient data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() initializes the trie object.
 * void insert(String word) inserts the string word to the trie.
 * boolean search(String word) returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Note: Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 *
 */
public class Trie {
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;

        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            if (temp.children[c - 'a'] == null) {
                temp.children[c - 'a'] = new TrieNode();
            }
            temp = temp.children[c - 'a'];
        }

        temp.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) return false;

        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            if (temp.children[c - 'a'] == null) {
                return false;
            }

            temp = temp.children[c - 'a'];
        }

        return temp.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return false;

        TrieNode temp = root;
        for (char c: prefix.toCharArray()) {
            if (temp.children[c - 'a'] == null) return false;

            temp = temp.children[c - 'a'];
        }

        return true;
    }

    class TrieNode {
        public boolean isEnd;
        public TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }
}
