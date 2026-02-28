package tdd.api;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  <br>
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  <br>
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 */
public interface CircularQueue {

    /**
     * Gets the capacity set for the queue.
     *
     * @return The allocated size of the queue.
     */
    int getAllocatedSize();

    /**
     * Gets the number of elements currently in the queue.
     *
     * @return The actual size of the queue.
     */
    int getActualSize();

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Adds the passed element at the rear of the queue.
     *
     * @param element The element to enqueue.
     */
    void enqueue(int element);

    /**
     * Gets the element at the front of the queue, which is the oldest element in the queue.
     *
     * @return The element at the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    int dequeue();

    /**
     * Retrieves, but does not remove, the element at the front of the queue, which is the oldest element in the queue.
     *
     * @return The element at the front of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    int peekFront();

    /**
     * Retrieves, but does not remove, the element at the rear of the queue, which is the newest element in the queue.
     *
     * @return The element at the rear of the queue.
     * @throws IllegalStateException if the queue is empty.
     */
    int peekRear();

}
