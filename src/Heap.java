/**
 * Author: Muneeb Azfar Nafees
 * 
 * Purpose of class: A generic implementation of a heap data structure that implements
 * the PriorityQueue interface. 
 */
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> implements PriorityQueue<T>{
    private Comparator<T> comparator;
    private ArrayList<T> heap;

    /**
     * Constructor for the Heap class.
     * 
     * @param comparator the comparator to use for ordering the elements in the heap
     * @param maxHeap    true if the heap should be a max heap, false if it should be a min heap
     */
    public Heap(Comparator<T> comparator, boolean maxHeap){
        if(maxHeap){
            this.comparator = new Comparator<T>(){
                @Override
                public int compare(T o1, T o2) {
                    return ((Comparable<T>) o2).compareTo(o1);
                }
            };
        }
        else{
            this.comparator = comparator;
        }
    }

    /**
     * Constructor for the Heap class.
     * 
     * @param comparator the comparator to use for ordering the elements in the heap
     */
    public Heap(Comparator<T> comparator){
        this(comparator, false);
    }
}