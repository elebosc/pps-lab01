package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.api.CircularQueue;
import tdd.impl.CircularQueueImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularQueueTest {

    private static final int QUEUE_SIZE = 3;

    private CircularQueue queue;

    private void enqueueElements(List<Integer> elements) {
        elements.forEach((elem) -> this.queue.enqueue(elem));
    }

    private List<Integer> dequeueAllElements() {
        final List<Integer> elements = new ArrayList<>();
        while (!this.queue.isEmpty()) {
            elements.add(this.queue.dequeue());
        }
        return elements;
    }

    @BeforeEach
    public void initTest() {
        this.queue = new CircularQueueImpl(QUEUE_SIZE);
    }

    @Test
    public void testQueueIsAllocatedWithCorrectSize() {
        assertEquals(QUEUE_SIZE, this.queue.getAllocatedSize());
    }

    @Test
    public void testQueueIsInitiallyEmpty() {
        assertTrue(this.queue.isEmpty());
    }

    @Test
    public void testEnqueueIsSuccessful() {
        final int element = 1;
        this.queue.enqueue(element);
        assertEquals(element, this.queue.peekFront());
    }

    @Test
    public void testActualSizeIsCorrectlyUpdated() {
        enqueueElements(List.of(1, 2, 3));
        final int expectedSize = 3;
        assertEquals(expectedSize, this.queue.getActualSize());
    }

    @Test
    public void testFrontIsCorrectlySet() {
        enqueueElements(List.of(1, 2, 3));
        final int expectedElement = 1;
        assertEquals(expectedElement, this.queue.peekFront());
    }

    @Test
    public void testRearIsCorrectlySet() {
        enqueueElements(List.of(1, 2, 3));
        final int expectedElement = 3;
        assertEquals(expectedElement, this.queue.peekRear());
    }

    @Test
    public void testPeekFrontDoesNotRemoveTheElement() {
        final int element = 1;
        final int expectedSize = 1;
        this.queue.enqueue(element);
        this.queue.peekFront();
        assertEquals(expectedSize, this.queue.getActualSize());
    }

    @Test
    public void testPeekRearDoesNotRemoveTheElement() {
        final int element = 1;
        final int expectedSize = 1;
        this.queue.enqueue(element);
        this.queue.peekRear();
        assertEquals(expectedSize, this.queue.getActualSize());
    }

    @Test
    public void testQueueContentAndOrderAreCorrect() {
        enqueueElements(List.of(1, 2, 3));
        final List<Integer> expectedElements = List.of(1, 2, 3);
        final List<Integer> actualElements = dequeueAllElements();
        assertEquals(expectedElements, actualElements);
    }

    @Test
    public void testDequeueRemovesTheElement() {
        final int element = 1;
        final int expectedSize = 0;
        this.queue.enqueue(element);
        this.queue.dequeue();
        assertEquals(expectedSize, this.queue.getActualSize());
    }

    @Test
    public void testQueueContentAndOrderAreCorrectAfterMultipleOverwrites() {
        enqueueElements(List.of(1, 2, 3, 4, 5));
        final List<Integer> expectedElements = List.of(3, 4, 5);
        final List<Integer> actualElements = dequeueAllElements();
        assertEquals(expectedElements, actualElements);
    }

    @Test
    public void testDequeueFailsIfQueueIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.queue.dequeue());
    }

}
