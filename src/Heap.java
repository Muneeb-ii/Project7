/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: A generic implementation of a heap data structure that implements
 * the PriorityQueue interface. 
 */

public class Heap<T> implements PriorityQueue<T>{
    private Comparator<T> comparator;
    private ArrayList<T> heap;
}