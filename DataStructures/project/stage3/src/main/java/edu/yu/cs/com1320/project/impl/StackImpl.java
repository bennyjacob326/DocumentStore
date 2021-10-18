package edu.yu.cs.com1320.project.impl;

import edu.yu.cs.com1320.project.Stack;

import java.util.LinkedList;

/**
 * @param <T>
 */
public class StackImpl<T> implements Stack<T>
{
    LinkedList<T> list;
    public StackImpl(){
    this.list = new LinkedList<T>();

}
    /**
     * @param element object to add to the Stack
     */


    @Override
    public void push(T element) {
        list.add((T) element);
    }

    /**
     * removes and returns element at the top of the stack
     * @return element at the top of the stack, null if the stack is empty
     */
    @Override
    public T pop(){
        if(this.list.size() == 0){
            return null;
        }
        return this.list.removeLast();
    }

    /**
     *
     * @return the element at the top of the stack without removing it
     */
    @Override
    public T peek(){
        if(this.list.size() == 0){
            throw new IllegalArgumentException();
        }
        return this.list.getLast();
    }

    /**
     *
     * @return how many elements are currently in the stack
     */
    @Override
    public int size(){
        return this.list.size();
    }

}