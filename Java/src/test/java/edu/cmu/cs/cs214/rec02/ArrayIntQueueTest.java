package edu.cmu.cs.cs214.rec02;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ArrayIntQueueTest {

    @Test
    public void testEmptyQueueBehavior() {
        ArrayIntQueue queue = new ArrayIntQueue();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
        assertNull(queue.dequeue());
    }

    @Test
    public void testFifoOrderingAndSize() {
        ArrayIntQueue queue = new ArrayIntQueue();

        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        assertEquals(Integer.valueOf(1), queue.peek());

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
        assertEquals(Integer.valueOf(3), queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testResizeWithoutWrapAround() {
        ArrayIntQueue queue = new ArrayIntQueue();

        for (int i = 0; i <= 10; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i <= 10; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testResizeWithWrapAroundPreservesOrder() {
        ArrayIntQueue queue = new ArrayIntQueue();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        for (int i = 0; i < 4; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        for (int i = 10; i < 14; i++) {
            queue.enqueue(i);
        }

        queue.enqueue(14);

        for (int i = 4; i <= 14; i++) {
            assertEquals(Integer.valueOf(i), queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    public void testClearResetsHeadAndState() {
        ArrayIntQueue queue = new ArrayIntQueue();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(Integer.valueOf(1), queue.dequeue());

        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.peek());
        assertNull(queue.dequeue());

        queue.enqueue(42);
        assertEquals(Integer.valueOf(42), queue.peek());
        assertEquals(Integer.valueOf(42), queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
