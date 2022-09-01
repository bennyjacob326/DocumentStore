package edu.yu.cs.com1320.project.impl;


import edu.yu.cs.com1320.project.HashTable;

import java.util.Hashtable;

public class HashTableImpl<Key, Value> implements HashTable {



    class Entry<Key, Value>{
        Key key;
        Value value;
        Entry next;
        Entry(Key k, Value v){
            if(k == null){
                throw new IllegalArgumentException();
            }
            key = k;
            value = v;
        }
    }
    private Entry<?,?>[] array = new Entry[5];
    private int amountFilled = 0;

    public Value get(Object k) {
        int index = hashC((Key) k);
        Entry e = this.array[index];
        if(e != null){
            return findV(e, (Key) k);
        }
        return null;
    }


    public Value put(Object k, Object v) {


        checkToDouble();
        if(k == null){
            throw new IllegalArgumentException();
        }
        int index = hashC((Key) k);
        Entry front = this.array[index];
        if(v == null){
            return deleteDoc(index, (Key) k);
            //return deleteDoc(front, (Key) k);
        }
        if(front != null){
            return addTo(front,(Key) k, (Value) v);
        }else{
            amountFilled++;//may need to comment out
            Entry<Key,Value> putEntry= new Entry<Key,Value>((Key) k, (Value) v);
            this.array[index] = putEntry;
            return null;
        }
    }

    private Value addTo(Entry ent, Key k, Value v){
        Value recurse = null;
        if(ent.key.equals(k)){
            Value prev = (Value) ent.value;
            ent.value = v;//just change the value of v
            return prev;
        }else if(ent.next == null){
            amountFilled++;
            ent.next = new Entry<Key, Value>(k, v);
            return null;
        }else if(!(ent.key.equals(k))){
            recurse = (Value) addTo(ent.next, k, v);
        }
        return recurse;
    }

    private Value deleteDoc(int index, Key k){
        if(this.array[index] == null){
            return null;
        }
        Value prev = (Value) this.array[index].value;
        if(this.array[index].key.equals(k)){
            amountFilled--;
            this.array[index] = this.array[index].next;
            return prev;
        }
        HashTableImpl.Entry  ent = this.array[index].next;
        if(ent == null) return null;
        HashTableImpl.Entry  ent2 = this.array[index];
        while(!(ent.key.equals(k))){
            ent2 = ent;
            ent = ent.next;
            if(ent == null){
                return null;
            }
        }
        amountFilled--;
        prev = (Value) ent.value;
        ent2.next = ent.next;

        //this.array[index] = this.array[index].next;
        return prev;
    }
    private Value findV(Entry e, Key k){
        if(e.key.equals(k)) return (Value) e.value;
        Entry theNextEntry = e;
        while(theNextEntry != null && !(theNextEntry.key.equals(k))){
            theNextEntry = theNextEntry.next;
        }
        if(theNextEntry != null) {
            if (theNextEntry.key.equals(k)) {//this should automatically be true
                return (Value) theNextEntry.value;
            }
        }
        return null;
    }
    private boolean checkToDouble(){
        if(array.length*(.75) > amountFilled){
            return false;
        }else{
            Entry<?,?>[] oldArray = array;
            array = new Entry[array.length*2];
            for(Entry v: oldArray){
                while(v != null){
                    this.put(v.key, v.value);
                    v = v.next;
                }
            }
            return true;
        }
    }
    private int hashC(Key k){
        int ret = k.hashCode() % 5;
        if(ret < 0){
            ret = ret * (-1);
        }
        return ret;
    }
}