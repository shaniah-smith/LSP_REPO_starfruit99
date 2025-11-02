package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Models a mathematical set of unique integers backed by an ArrayList.
 * No duplicates are allowed. All mutator operations modify this instance.
 * Equality is based on contents, not reference or order.
 */
public class IntegerSet  {
    private List<Integer> set = new ArrayList<>();

    /** Clears the internal representation of the set, removing all elements. */
    public void clear() {
        set.clear();
    }

    /** Returns the number of elements in the set. */
    public int length() {
        return set.size();
    }

    /** Returns true if sets are equal, false otherwise. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof IntegerSet)) return false;

        IntegerSet other = (IntegerSet) o;
        if (this.length() != other.length()) return false;

        return this.set.containsAll(other.set) && other.set.containsAll(this.set);
    }

    /** Returns true if the set contains the value. */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /** Returns the largest item in the set. Throws exception if empty. */
    public int largest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    /** Returns the smallest item in the set. Throws exception if empty. */
    public int smallest() {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    /** Adds item to set if not already present. */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /** Removes item if present. */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /** Union operation — this = this ∪ other */
    public void union(IntegerSet other) {
        for (Integer val : other.set) {
            if (!this.set.contains(val)) {
                this.set.add(val);
            }
        }
    }

    /** Intersection operation — this = this ∩ other */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /** Difference operation — this = this \ other */
    public void diff(IntegerSet other) {
      set.removeAll(other.set);
    }

    /** Complement — this = other \ this */
    public void complement(IntegerSet other) {
      List<Integer> temp = new ArrayList<>(other.set);
      temp.removeAll(this.set);
      this.set.clear();
      this.set.addAll(temp);
    }

    /** Returns true if empty. */
    public boolean isEmpty() {
      return set.isEmpty();
    }

    /** Returns formatted string like [1, 2, 3] */
    @Override
    public String toString() {
      return "[" + set.stream()
          .map(String::valueOf)
          .collect(Collectors.joining(", ")) + "]";
    }
  }
