/*******************************************************************************
 * Copyright (C) 2015 Anton Gustafsson
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package com.github.antag99.retinazer.beam.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.github.antag99.retinazer.RetinazerTestCase;

public final class GWTOrdinalSetTest extends RetinazerTestCase {
    public void testBasic() {
        OrdinalSet<TestOrdinal> set = new OrdinalSet<>(TestOrdinal.TYPE);
        assertFalse(set.contains(TestOrdinal.TEST_0));
        assertFalse(set.contains(TestOrdinal.TEST_1));
        assertFalse(set.contains(TestOrdinal.TEST_2));
        assertFalse(set.contains(TestOrdinal.TEST_3));
        set.add(TestOrdinal.TEST_0);
        assertTrue(set.contains(TestOrdinal.TEST_0));
        assertFalse(set.contains(TestOrdinal.TEST_1));
        assertFalse(set.contains(TestOrdinal.TEST_2));
        assertFalse(set.contains(TestOrdinal.TEST_3));
        set.add(TestOrdinal.TEST_1);
        assertTrue(set.contains(TestOrdinal.TEST_0));
        assertTrue(set.contains(TestOrdinal.TEST_1));
        assertFalse(set.contains(TestOrdinal.TEST_2));
        assertFalse(set.contains(TestOrdinal.TEST_3));
        set.add(TestOrdinal.TEST_3);
        assertTrue(set.contains(TestOrdinal.TEST_0));
        assertTrue(set.contains(TestOrdinal.TEST_1));
        assertFalse(set.contains(TestOrdinal.TEST_2));
        assertTrue(set.contains(TestOrdinal.TEST_3));
        set.add(TestOrdinal.TEST_2);
        assertTrue(set.contains(TestOrdinal.TEST_0));
        assertTrue(set.contains(TestOrdinal.TEST_1));
        assertTrue(set.contains(TestOrdinal.TEST_2));
        assertTrue(set.contains(TestOrdinal.TEST_3));
        set.remove(TestOrdinal.TEST_0);
        set.remove(TestOrdinal.TEST_2);
        assertFalse(set.contains(TestOrdinal.TEST_0));
        assertTrue(set.contains(TestOrdinal.TEST_1));
        assertFalse(set.contains(TestOrdinal.TEST_2));
        assertTrue(set.contains(TestOrdinal.TEST_3));
        set.clear();
        assertFalse(set.contains(TestOrdinal.TEST_0));
        assertFalse(set.contains(TestOrdinal.TEST_1));
        assertFalse(set.contains(TestOrdinal.TEST_2));
        assertFalse(set.contains(TestOrdinal.TEST_3));
    }

    public void testIterator() {
        OrdinalSet<TestOrdinal> set = new OrdinalSet<>(TestOrdinal.TYPE);
        set.add(TestOrdinal.TEST_0);
        set.add(TestOrdinal.TEST_1);
        set.add(TestOrdinal.TEST_2);
        set.add(TestOrdinal.TEST_3);

        Iterator<TestOrdinal> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_0, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_3, iterator.next());
        assertFalse(iterator.hasNext());

        iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_0, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_1, iterator.next());
        iterator.remove();
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_2, iterator.next());
        iterator.remove();
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_3, iterator.next());
        assertFalse(iterator.hasNext());

        iterator = set.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_0, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(TestOrdinal.TEST_3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    public void testIteratorNoElement() {
        try {
            new OrdinalSet<>(TestOrdinal.TYPE).iterator().next();
        } catch (NoSuchElementException ex) {
            if (ex.getClass() == NoSuchElementException.class)
                return;
        }
        fail("NoSuchElementException expected");
    }

    public void testIteratorIllegalState0() {
        try {
            new OrdinalSet<>(TestOrdinal.TYPE).iterator().remove();
        } catch (IllegalStateException ex) {
            if (ex.getClass() == IllegalStateException.class)
                return;
        }
        fail("IllegalStateException expected");
    }

    public void testIteratorIllegalState1() {
        try {
            OrdinalSet<TestOrdinal> set = new OrdinalSet<>(TestOrdinal.TYPE);
            set.add(TestOrdinal.TEST_0);
            Iterator<TestOrdinal> iterator = set.iterator();
            iterator.next();
            iterator.remove();
            iterator.remove();
        } catch (IllegalStateException ex) {
            if (ex.getClass() == IllegalStateException.class)
                return;
        }
        fail("IllegalStateException expected");
    }

    public void testIteratorExpiration0() {
        try {
            OrdinalSet<TestOrdinal> set = new OrdinalSet<>(TestOrdinal.TYPE);
            Iterator<TestOrdinal> iterator = set.iterator();
            set.iterator();
            iterator.hasNext();
        } catch (IllegalStateException ex) {
            if (ex.getClass() == IllegalStateException.class)
                return;
        }
        fail("IllegalStateException expected");
    }

    public void testIteratorExpiration1() {
        try {
            OrdinalSet<TestOrdinal> set = new OrdinalSet<>(TestOrdinal.TYPE);
            set.iterator();
            Iterator<TestOrdinal> iterator = set.iterator();
            set.iterator();
            iterator.hasNext();
        } catch (IllegalStateException ex) {
            if (ex.getClass() == IllegalStateException.class)
                return;
        }
        fail("IllegalStateException expected");
    }
}