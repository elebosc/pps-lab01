package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.api.MinMaxStack;
import tdd.impl.MinMaxStackImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackTest {

    private MinMaxStack stack;

    private void pushElements(List<Integer> elements) {
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
        pushElements(List.of(1));
        final int expectedSize = 1;
        assertEquals(expectedSize, this.stack.size());
    }

    @Test
    public void testPopDecreasesStackSize() {
        pushElements(List.of(1));
        this.stack.pop();
        final int expectedSize = 0;
        assertEquals(expectedSize, this.stack.size());
    }

    @Test
    public void testPopReturnsMostRecentlyInsertedValue() {
        pushElements(List.of(1, 2));
        final int expectedElement = 2;
        assertEquals(expectedElement, this.stack.pop());
    }

    @Test
    public void testPopRemovesTheElement() {
        pushElements(List.of(1));
        this.stack.pop();
        final int expectedSize = 0;
        assertEquals(expectedSize, this.stack.size());
    }

    @Test
    public void testPopFailsIfStackIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.stack.pop());
    }

    @Test
    public void testPeekReturnsMostRecentlyInsertedValue() {
        pushElements(List.of(1, 2));
        final int expectedElement = 2;
        assertEquals(expectedElement, this.stack.peek());
    }

    @Test
    public void testPeekDoesNotRemoveTheElement() {
        pushElements(List.of(1));
        this.stack.peek();
        final int expectedSize = 1;
        assertEquals(expectedSize, this.stack.size());
    }

    @Test
    public void testPeekFailsIfStackIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.stack.peek());
    }

    @Test
    public void testMaxRetrievalIsCorrect() {
        pushElements(List.of(1, 3, 2));
        final int expectedElement = 3;
        assertEquals(expectedElement, this.stack.getMax());
    }

    @Test
    public void testMinRetrievalIsCorrect() {
        pushElements(List.of(1, 3, 2));
        final int expectedElement = 1;
        assertEquals(expectedElement, this.stack.getMin());
    }

    @Test
    public void testMaxIsUpdatedWhenRemoved() {
        pushElements(List.of(1, 3, 4, 2));
        this.stack.pop();
        this.stack.pop();
        final int expectedElement = 3;
        assertEquals(expectedElement, this.stack.getMax());
    }

    @Test
    public void testMinIsUpdatedWhenRemoved() {
        pushElements(List.of(4, 2, 1, 3));
        this.stack.pop();
        this.stack.pop();
        final int expectedElement = 2;
        assertEquals(expectedElement, this.stack.getMin());
    }

    @Test
    public void testMinRetrievalFailsIfStackIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.stack.getMin());
    }

    @Test
    public void testMaxRetrievalFailsIfStackIsEmpty() {
        assertThrows(IllegalStateException.class, () -> this.stack.getMax());
    }

}
