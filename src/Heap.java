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
            this.comparator = comparator.reversed();
        }
        else{
            this.comparator = comparator;
        }
        this.heap = new ArrayList<>();
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