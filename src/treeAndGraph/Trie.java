package treeAndGraph;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树的简单实现
 */
public class Trie {

    public class TrieNode {
        //子节点
        Map<Character, TrieNode> childdren;
        boolean wordEnd;

        public TrieNode() {
            childdren = new HashMap<>();
            wordEnd = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.wordEnd = false;
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = new Character(word.charAt(i));
            if (!node.childdren.containsKey(c)) {
                node.childdren.put(c, new TrieNode());
            }
            node = node.childdren.get(c);
        }
        node.wordEnd = true;
    }

    public boolean startsWith(String prefix) {
        return contains(prefix) != 0;
    }

    public boolean search(String word) {
        return contains(word) == 1;
    }

    /**
     * @param s the string to search
     * @return 1 if string s is one of the leaf of trie, or -1 if s is prefix of one
     *         leaf but trie does not contain s, else 0.
     */
    private int contains(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!node.childdren.containsKey(c)) {
                return 0;
            }
            node = node.childdren.get(c);
        }
        return node.wordEnd == true ? 1 : -1;
    }


}
