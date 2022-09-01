package edu.yu.cs.com1320.project.impl;

import edu.yu.cs.com1320.project.MinHeap;

import java.util.NoSuchElementException;

public class MinHeapImpl<E extends Comparable<E>> extends MinHeap {
//    public static void main(String[] args) {
//        MinHeapImpl<Integer> intHeap = new MinHeapImpl<>();
//        intHeap.insert(77);
//        intHeap.reHeapify(77);
//        intHeap.insert(2);
//        intHeap.reHeapify(2);
//        intHeap.insert(3);
//        intHeap.reHeapify(3);
//        intHeap.insert(4);
//        intHeap.reHeapify(4);
//        intHeap.insert(4);
//        intHeap.reHeapify(4);
//        intHeap.insert(6);
//        intHeap.reHeapify(6);
//        System.out.println(intHeap.getArrayIndex(77));
//        System.out.println(intHeap.getArrayIndex(2));
//        System.out.println(intHeap.getArrayIndex(4));
//    }
    public MinHeapImpl(){
        this.elements = new Comparable[20];
    }

    @Override
    public void reHeapify(Comparable element) {
        //compare the values,using isGreater, see if it's greater than the parents or the children. if it's not, keep it in the same place.
        for(int i = 0; i< elements.length; i++) {
            if(elements[i] != null) {
                upHeap(i);
                downHeap(i);
            }
        }
    }

    @Override
    protected int getArrayIndex(Comparable element) {
        for(int i = 0; i < this.elements.length;i++){
            if(this.elements[i] != null) {
                if (elements[i].equals(element)) {
                    return i;
                }
            }
        }
        System.out.println("amount: " + count);
        throw new NoSuchElementException();
    }

    @Override
    protected void doubleArraySize() {//make sure that I'm not supposed to do anything more complicated than this.
        Comparable[] oldElements = elements;
        elements = new Comparable[oldElements.length*2];
        for(int i = 0; i < oldElements.length; i++){
            elements[i] = oldElements[i];
        }
    }
}
