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

    /**
     * Swaps the elements at the given indices in the heap.
     * 
     * @param idx1  index of the first element to swap
     * @param idx2  index of the second element to swap
     */
    private void swap(int idx1, int idx2){
        T temp = heap.get(idx1);
        heap.set(idx1, heap.get(idx2));
        heap.set(idx2, temp);
    }

    /**
     * Returns the index of the left child of the element at the given index.
     * 
     * @param idx the index of the parent element
     * @return the index of the left child
     */
    private int getLeftChildIdx(int idx){
        return 2*idx;
    }

    /**
     * Returns the index of the right child of the element at the given index.
     * 
     * @param idx the index of the parent element
     * @return the index of the right child
     */
    private int getRightChildIdx(int idx){
        return 2*idx + 1;
    }

    /**
     * Returns the index of the parent of the element at the given index.
     * 
     * @param idx the index of the child element
     * @return the index of the parent
     */
    private int getParentIdx(int idx){
        if(idx==1) return -1;
        return idx/2;
    }
}