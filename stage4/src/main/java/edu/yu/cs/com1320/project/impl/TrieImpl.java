package edu.yu.cs.com1320.project.impl;

import edu.yu.cs.com1320.project.Trie;

import java.util.*;

/**
 * FOR STAGE 3
 * @param <Value>
 */
public class TrieImpl<Value> implements Trie
{
    private int alphabetSize = 256; // extended ASCII
    private Node root; // root of trie

    private class Node<Value>
    {
        protected ArrayList<Value> val;
        protected Node[] links = new Node[256];
    }

    /**
     * add the given value at the given key
     * @param key
     * @param val
     */
    public void put(String key, Object val){
        if(key != null){
            key = key.toLowerCase();
            key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
            if(val != null)
                this.root = put(this.root, key, (Value) val, 0);
        }else{
            throw new IllegalArgumentException();
        }
    }
    private Node put(Node x, String key, Value val, int d)
    {
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();
        //create a new node
        if (x == null)
        {
            x = new Node();
        }
        //we've reached the last node in the key,
        //set the value for the key and return the node
        if (d == key.length())
        {
            if(x.val == null) x.val = new ArrayList<Value>();
            if(!(x.val.contains(val))) x.val.add(val);
            return x;
        }
        //proceed to the next node in the chain of nodes that
        //forms the desired key
        char c = key.charAt(d);
        x.links[c] = this.put(x.links[c], key, val, d + 1);
        return x;
    }

    private Node get(Node x, String key, int d)
    {
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();
        //link was null - return null, indicating a miss

        //we've reached the last node in the key,
        //return the node
        if (d == key.length())
        {
            return x;
        }
        if (x == null)
        {
            return null;
        }
        //proceed to the next node in the chain of nodes that
        //forms the desired key
        char c = key.charAt(d);
        return this.get(x.links[c], key, d + 1);
    }


    /**
     * get all exact matches for the given key, sorted in descending order.
     * Search is CASE INSENSITIVE.
     * @param key
     * @param comparator used to sort  values
     * @return a List of matching Values, in descending order
     */
    public List<Value> getAllSorted(String key, Comparator comparator){
        if(key == null || comparator == null) throw new IllegalArgumentException();
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();

        if(key == "") return new ArrayList<Value>();
        Node listing = get(this.root, key, 0);
        if(key == null){
            throw new IllegalArgumentException();
        }
        ArrayList<Value> values = new ArrayList<>();
        if(listing != null){
            values = listing.val;
            if(values != null) Collections.sort(values, comparator);
        }

        return values;
    }

    /**
     * get all matches which contain a String with the given prefix, sorted in descending order.
     * For example, if the key is "Too", you would return any value that contains "Tool", "Too", "Tooth", "Toodle", etc.
     * Search is CASE INSENSITIVE.
     * @param prefix
     * @param comparator used to sort values
     * @return a List of all matching Values containing the given prefix, in descending order
     */
    public List<Value> getAllWithPrefixSorted(String prefix, Comparator comparator){
        if(comparator == null  || prefix == null) throw new IllegalArgumentException();
        prefix = prefix.replaceAll("[^a-zA-Z0-9\\s]", "");
        prefix = prefix.toLowerCase();
        //if() return new ArrayList<Value>();
        Node listing = get(this.root, prefix, 0);
        if(prefix == "") return new ArrayList<Value>();
        if(listing == null) return new ArrayList<Value>();
        List<Value> hasPrefix = new ArrayList<Value>();
        if(listing.val != null){
            hasPrefix.addAll(listing.val);
        }
        hasPrefix.addAll(checkForChildren(listing, new ArrayList<Value>()));
        Set<Value> hasP = new HashSet<>();
        hasP.addAll(hasPrefix);
        hasPrefix.clear();
        hasPrefix.addAll(hasP);
        Collections.sort(hasPrefix, comparator);
        return hasPrefix;
    }

    /**
     * Delete the subtree rooted at the last character of the prefix.
     * Search is CASE INSENSITIVE.
     * @param prefix
     * @return a Set of all Values that were deleted.
     */
    private Set<Value> valuesDeleted;
    public Set<Value> deleteAllWithPrefix(String prefix){
        if(prefix == null) throw new IllegalArgumentException();
        prefix = prefix.replaceAll("[^a-zA-Z0-9\\s]", "");
        prefix = prefix.toLowerCase();
        valuesDeleted = new HashSet<Value>();
        if(prefix == "") return valuesDeleted;
        valuesDeleted.addAll(getAllWithPrefixSorted(prefix, (a1, a2) -> 0));//does nothing, don't care about order
        deleteAllWithPrefix(this.root, prefix, 0);
        return valuesDeleted;
    }
    private Node deleteAllWithPrefix(Node x, String key, int d)
    {
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();

        //we're at the node to del - set the val to null
        if (d == key.length())
        {
            x = null;
        }
        //continue down the trie to the target node
        else
        {
            char c = key.charAt(d);
            x.links[c] = this.deleteAllWithPrefix(x.links[c], key, d + 1);
        }
        //this node has a val – do nothing, return the node
        if (x == null)
        {
            return null;
        }
        return x;
    }

    /**
     * Delete all values from the node of the given key (do not remove the values from other nodes in the Trie)
     * @param key
     * @return a Set of all Values that were deleted.
     */
    public Set<Value> deleteAll(String key){
        if(key == null) throw new IllegalArgumentException();
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();
        Set<Value> deletingValues = new HashSet<>();
        if(key == "") return deletingValues;
        Node deletingNode = get(this.root, key, 0);

        if(deletingNode.val == null) return new HashSet<Value>();
        deletingValues.addAll(deletingNode.val);
        deleteAll(this.root, key, 0);
        return deletingValues;
    }
    private Node deleteAll(Node x, String key, int d)
    {
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();
        if (x == null)
        {
            return null;
        }
        //we're at the node to del - set the val to null
        if (d == key.length())
        {
            x.val = null;
        }
        //continue down the trie to the target node
        else
        {
            char c = key.charAt(d);
            x.links[c] = this.deleteAll(x.links[c], key, d + 1);
        }
        //this node has a val – do nothing, return the node
        if (x.val != null)
        {
            return x;
        }
        //remove subtrie rooted at x if it is completely empty
        for (int c = 0; c < 256; c++)
        {
            if (x.links[c] != null)
            {
                return x; //not empty
            }
        }
        //empty - set this link to null in the parent
        return null;
    }

    /**
     * Remove the given value from the node of the given key (do not remove the value from other nodes in the Trie)
     * @param key
     * @param val
     * @return the value which was deleted. If the key did not contain the given value, return null.
     */
    public Value delete(String key, Object val){
        if(key == null || val == null) throw new IllegalArgumentException();
        key = key.replaceAll("[^a-zA-Z0-9\\s]", "");
        key = key.toLowerCase();

        if(key == "") return null;
        Node deleting = get(this.root, key, 0);
        Object va = null;
        for(Object v: deleting.val){
            if(v.equals(val)){
                va = v;
            }
        }
        boolean deletedTheValue = deleting.val.remove(val);
        if(deleting.val.size() == 0){
            deleteAll(deleting, key, 0);
        }
        if(deletedTheValue) return (Value) va;
        else return null;
    }
    private List<Value> checkForChildren(Node parent, ArrayList<Value> children){
        for(int i = 0; i < 256; i++){
            if(parent.links[i] != null){
                if(parent.links[i].val != null) children.addAll(parent.links[i].val);
                checkForChildren(parent.links[i], children);
            }
        }
        return children;
    }
    private List<Value> deleteChildren(Node parent, ArrayList<Value> children){
        for(int i = 0; i < 256; i++){
            if(parent.links[i] != null){
                if(parent.links[i].val != null){
                    children.addAll(parent.links[i].val);
                    parent.links[i] = null;
                }
                //checkForChildren(parent.links[i], children);
            }
        }
        return children;
    }
}