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
        this.heap.add(null); // index 0 is unused
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

    /**
     * Bubbles up the element at the given index in the heap to maintain the heap property.
     * 
     * @param idx the index of the element to bubble up
     */
    private void bubbleUp(int idx){
        int parentIdx = getParentIdx(idx);
        if(parentIdx!=-1){
            if(comparator.compare(heap.get(idx), heap.get(parentIdx))<0){
                this.swap(idx,parentIdx);
                bubbleUp(parentIdx);
            }
        }
        
    }
    
    /**
     * Bubbles down the element at the given index in the heap to maintain the heap property.
     * 
     * @param idx the index of the element to bubble down
     */
    private void bubbleDown(int idx){
        int leftChildIdx = getLeftChildIdx(idx);
        int rightChildIdx = getRightChildIdx(idx);
        int n = heap.size();

        // if the left child index is out of bounds, return
        if(leftChildIdx>=n) return;

        int swapIdx = leftChildIdx;

        // if the right child index is in bounds and the left child is greater than the right child
        // set the swap index to the right child index
        if(rightChildIdx<n){
            if(comparator.compare(heap.get(leftChildIdx), heap.get(rightChildIdx))>0){
                swapIdx = rightChildIdx;
            }
        }
        
        // If the element at the current index is greater than the element at the swap index swap the two elements
        // and call bubbleDown on the swap index
        if(comparator.compare(heap.get(idx), heap.get(swapIdx))>0){
            this.swap(idx, swapIdx);
            bubbleDown(swapIdx);
        }

    }

    /**
     * Returns the string representation of the heap.
     * 
     * @return the string representation of the heap
     */
    public String toString() {
        int depth = 0 ;
        return toString( 1 , depth );
    }

    /**
     * Helper method to convert the heap to a string representation.
     * 
     * @param idx   the index of the element to convert to a string
     * @param depth the depth of the element in the heap
     * @return the string representation of the heap
     */
    private String toString( int idx , int depth ) {
        if (idx >= heap.size() ) {
            return "";
        }
        String left = toString(getLeftChildIdx( idx ) , depth + 1 );
        String right = toString(getRightChildIdx( idx ) , depth + 1 );

        String myself = "\t".repeat(depth) + this.heap.get( idx ) + "\n";
        return right + myself + left;
    }

    /**
     * Returns the number of items in the heap.
     * 
     * @return the number of items in the heap
     */
    public int size() {
        return heap.size() - 1;
    }

    /**
     * Returns the item of greatest priority in the heap (the root).
     * 
     * @return the item of greatest priority in the heap
     */
    public T peek(){
        if(heap.size() == 1){
            return null;
        }
        return heap.get(1);
    }
}
