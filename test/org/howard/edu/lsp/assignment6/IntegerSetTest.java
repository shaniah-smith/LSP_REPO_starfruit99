package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Test suite for the IntegerSet class.
 * <p>
 * Verifies proper functionality of all IntegerSet operations including:
 * add, remove, clear, contains, equals, largest, smallest, union,
 * intersection, difference, complement, length, and toString.
 * <p>
 * Each test isolates one behavior, ensures correctness on
 * normal and edge cases, and confirms proper mutation behavior.
 */
public class IntegerSetTest {

    /**
     * Tests clearing a set and verifying its length becomes zero.
     */
    @Test
    void testClearAndLength() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        s.add(2);
        assertEquals(2, s.length());
        s.clear();
        assertEquals(0, s.length());
    }

    /**
     * Tests adding values and preventing duplicates.
     */
    @Test
    void testAdd() {
        IntegerSet s = new IntegerSet();
        s.add(1);
        s.add(1);
        s.add(2);
        assertEquals(2, s.length());
        assertTrue(s.contains(1));
        assertTrue(s.contains(2));
    }

    /**
     * Tests largest() and smallest() on an empty set should throw exceptions.
     */
    @Test
    void testLargestAndSmallestThrowOnEmpty() {
        IntegerSet s = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> s.largest());
        assertThrows(IllegalStateException.class, () -> s.smallest());
    }

    /**
     * Tests largest() and smallest() returning correct values.
     */
    @Test
    void testLargestAndSmallest() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        s.add(2);
        s.add(9);
        assertEquals(9, s.largest());
        assertEquals(2, s.smallest());
    }

    /**
     * Tests contains() and remove() logic.
     */
    @Test
    void testContainsAndRemove() {
        IntegerSet s = new IntegerSet();
        s.add(3);
        s.add(4);
        assertTrue(s.contains(3));
        s.remove(3);
        assertFalse(s.contains(3));
        assertTrue(s.contains(4));
    }

    /**
     * Tests set equality ignoring order.
     */
    @Test
    void testEqualsOrderIndependent() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1);
        a.add(2);
        b.add(2);
        b.add(1);
        assertTrue(a.equals(b));
    }

    /**
     * Tests intersection leaving only shared elements.
     */
    @Test
    void testIntersect() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1);
        a.add(2);
        b.add(2);
        b.add(3);
        a.intersect(b);   // a becomes {2}
        assertTrue(a.contains(2));
        assertEquals(1, a.length());
    }

    /**
     * Tests difference (this \ other).
     */
    @Test
    void testDiff() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);
        b.add(2);
        a.diff(b);   // a becomes {1,3}
        assertTrue(a.contains(1));
        assertTrue(a.contains(3));
        assertFalse(a.contains(2));
        assertEquals(2, a.length());
    }

    /**
     * Tests complement (other \ this).
     */
    @Test
    void testComplementOtherMinusThis() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(2);            // a = {2}
        b.add(1); b.add(2); b.add(3);  // b = {1,2,3}
        a.complement(b);     // a becomes {1,3}
        assertTrue(a.contains(1));
        assertTrue(a.contains(3));
        assertFalse(a.contains(2));
        assertEquals(2, a.length());
    }

    /**
     * Tests union combining all elements without duplicates.
     */
    @Test
    void testUnionMutatesThis() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1);
        a.add(2);
        b.add(2);
        b.add(3);
        a.union(b);   // a becomes {1,2,3}
        assertEquals(3, a.length());
        assertTrue(a.contains(1));
        assertTrue(a.contains(2));
        assertTrue(a.contains(3));
    }

    /**
     * Tests toString formatting as [x, y, z].
     */
    @Test
    void testToStringFormat() {
        IntegerSet s = new IntegerSet();
        assertEquals("[]", s.toString());
        s.add(10);
        s.add(20);
        assertEquals("[10, 20]", s.toString());
    }
}
