import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class HeapTests {

    public static double heapTests() {

        double testScore = 1. ;

        /**
         * Make a small Priority Queue
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Comparator<Integer>) (Integer a, Integer b) -> a - b);

            for (int i = 0; i < 5; i++) {
                pq.offer(i);
            }

            if (pq.size() == 5) {
                System.out.println( "Test 1 passed" );
                testScore ++;
            }
        }
        
        /**
         * Offer a few things in, poll a few things out
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Comparator<Integer>) (Integer a, Integer b) -> a - b);

            for (int i = 0; i < 5; i++) {
                pq.offer(i);
            }

            boolean testPassed = true;
            for (int i = 0; i < 5; i++) {
                if (pq.poll() != i) {
                    testPassed = false;
                }
            }

            if (testPassed) {
                System.out.println( "Test 2 passed" );
                testScore ++;
            }
        }

        /**
         * Adding and removing a bunch of items (simple)
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Comparator<Integer>) (Integer a, Integer b) -> a - b);

            for (int i = 0; i < 100; i++) {
                pq.offer(i);
            }

            boolean testPassed = true;
            for (int i = 0; i < 100; i++) {
                if (pq.poll() != i) {
                    testPassed = false;
                }
            }

            if (testPassed) {
                System.out.println( "Test 3 passed" );
                testScore ++;
            }
        }

        /**
         * Adding and removing a bunch of items (a bit more interesting)
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Integer a, Integer b) -> a - b);
            Random rand = new Random();
            ArrayList<Integer> nums = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                nums.add(rand.nextInt(300));
            }

            Collections.shuffle(nums);
            for (int x : nums) {
                pq.offer(x);
            }
            
            nums.sort((Integer a, Integer b) -> a - b);
            boolean testPassed = true;
            for (int i = 0; i < 100; i++) {
                int p = pq.poll();
                if ( p != nums.get(i)) {
                    testPassed = false;
                }
            }

            if (testPassed) {
                System.out.println( "Test 4 passed" );
                testScore ++;
            }
        }

        /**
         * Making sure the runtime is roughly nlog n
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Integer a, Integer b) -> a - b);
            Random rand = new Random();

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++)
                pq.offer(rand.nextInt(Integer.MAX_VALUE));
            for (int i = 0; i < 1000000; i++)
                pq.poll();
            long elapsedTimeMillis = System.currentTimeMillis() - startTime;

            if ( elapsedTimeMillis < 5000 ) {
                System.out.println( "Test 5 passed" );
                testScore ++;
            }
        }

        /**
         * Offer a few things in, poll a few things out using the max heap property
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Comparator<Integer>) (Integer a, Integer b) -> a - b, true);

            for (int i = 0; i < 5; i++) {
                pq.offer(i);
            }

            boolean testPassed = true;
            for (int i = 4; i > 0; i--) {
                if (pq.poll() != i) {
                    testPassed = false;
                }
            }

            if (testPassed) {
                System.out.println( "Test 6 passed" );
                testScore ++;
            }
        }

        /**
         * Adding and removing a bunch of items (a bit more interesting) using the max heap property
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Integer a, Integer b) -> a - b, true);
            Random rand = new Random();
            ArrayList<Integer> nums = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                nums.add(rand.nextInt(300));
            }

            Collections.shuffle(nums);
            for (int x : nums) {
                pq.offer(x);
            }
            
            nums.sort((Integer a, Integer b) -> a - b);
            boolean testPassed = true;
            for (int i = 99; i >= 0; i--) {
                int p = pq.poll();
                if ( p != nums.get(i)) {
                    testPassed = false;
                }
            }

            if (testPassed) {
                System.out.println( "Test 7 passed" );
                testScore ++;
            }
        }

        /**
         * Making sure the runtime is roughly nlog n using the max heap property
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Integer a, Integer b) -> a - b, true);
            Random rand = new Random();

            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++)
                pq.offer(rand.nextInt(Integer.MAX_VALUE));
            for (int i = 0; i < 1000000; i++)
                pq.poll();
            long elapsedTimeMillis = System.currentTimeMillis() - startTime;

            if ( elapsedTimeMillis < 5000 ) {
                System.out.println( "Test 8 passed" );
                testScore ++;
            }
        }

        /**
         * Making sure peek does not remove the element from the heap while poll does
         * ensure peek returns the same value as poll
         */
        {
            PriorityQueue<Integer> pq = new Heap<>((Integer a, Integer b) -> a - b, true);
            Random rand = new Random();
            ArrayList<Integer> nums = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                nums.add(rand.nextInt(300));
            }

            Collections.shuffle(nums);
            for (int x : nums) {
                pq.offer(x);
            }
            
            boolean testPassed = true;
            for (int i = 0; i < 100; i++) {
                int size = pq.size();
                int p1 = pq.peek();
                if ( size != pq.size() ) {
                    testPassed = false;
                }
                int p2 = pq.poll();
                if ( p1 != p2 ) {
                    testPassed = false;
                }
            }

            if (testPassed) {
                System.out.println( "Test 9 passed" );
                testScore ++;
            }
        }

        return testScore;
    }

    public static void main(String[] args) {
        System.out.println( heapTests() + "/10.0" );
    }
}