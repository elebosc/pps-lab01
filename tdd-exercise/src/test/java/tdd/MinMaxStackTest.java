package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.api.MinMaxStack;
import tdd.impl.MinMaxStackImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackTest {

    private MinMaxStack stack;

    private void insertElements(List<Integer> elements) {
        elements.forEach((elem) -> this.stack.push(elem));
    }

    @BeforeEach
    void initTest() {
        this.stack = new MinMaxStackImpl();
    }

    @Test
    public void testStackIsInitiallyEmpty() {
        assertTrue(this.stack.isEmpty());
    }

    @Test
    public void testPushIncreasesStackSize() {
        insertElements(List.of(1));
        assertEquals(1, this.stack.size());
    }

    @Test
    public void testPopDecreasesStackSize() {
        insertElements(List.of(1));
        this.stack.pop();
        assertEquals(0, this.stack.size());
    }

    @Test
    public void testPopReturnsMostRecentlyInsertedValue() {
        insertElements(List.of(1, 2));
        assertEquals(2, this.stack.pop());
    }

    @Test
    public void testPopRemovesTheElement() {
        insertElements(List.of(1));
        this.stack.pop();
        assertEquals(0, this.stack.size());
    }

    @Test
    public void testPopThrowsExceptionIfStackIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.stack.pop());
    }

    @Test
    public void testPeekReturnsMostRecentlyInsertedValue() {
        insertElements(List.of(1, 2));
        assertEquals(2, this.stack.peek());
    }

    @Test
    public void testPeekDoesNotRemoveTheElement() {
        insertElements(List.of(1));
        this.stack.peek();
        assertEquals(1, this.stack.size());
    }

    @Test
    public void testPeekThrowsExceptionIfStackIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.stack.peek());
    }

    @Test
    public void testMaxRetrievalWorks() {
        insertElements(List.of(1, 3, 2));
        assertEquals(3, this.stack.getMax());
    }

    @Test
    public void testMinRetrievalWorks() {
        insertElements(List.of(1, 3, 2));
        assertEquals(1, this.stack.getMin());
    }

    @Test
    public void testMaxIsUpdatedWhenRemoved() {
        insertElements(List.of(1, 3, 4, 2));
        this.stack.pop();
        this.stack.pop();
        assertEquals(3, this.stack.getMax());
    }

    @Test
    public void testMinIsUpdatedWhenRemoved() {
        insertElements(List.of(4, 2, 1, 3));
        this.stack.pop();
        this.stack.pop();
        assertEquals(2, this.stack.getMin());
    }

}
