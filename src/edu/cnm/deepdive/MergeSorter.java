package edu.cnm.deepdive;

// Override is inheriting and modifying method but not changing sig.
// Overloading is different signatures but same name.

public class MergeSorter {

  public void sort(int[] data) {
    // Sorting data including but not less than 0. Invokes the second method.
    sort(data, 0, data.length);
  }

  public void sort(int[] data, int from, int to) {
    if (to - from <= 1) {
      return;
    }
    int midpoint = (from + to) / 2;
    sort(data, from, midpoint);
    // starts at midpoint and goes up to "to" but not including.
    sort(data, midpoint, to);
    // Merge, create array just as big.
    merge(data, from, to, midpoint);
  }

  private void merge(int[] data, int from, int to, int midpoint) {
    int[] merged = new int[to - from];
    // Pointer to top card and something pointing to where to put next card.
    int leftIndex = from;
    int rightIndex = midpoint;
    int mergedIndex = 0;
    while (leftIndex < midpoint && rightIndex < to) {
      // compares left value with right and checks lower.
      int leftValue = data[leftIndex];
      int rightValue = data[rightIndex];
      // takes first value in leftIndex and puts in new array. then moves pile to get next top card.
      if (leftValue <= rightValue) {
        // Looks at next card in leftIndex.
        merged[mergedIndex] = leftValue;
        leftIndex++;
      } else {
        // If left one lower, will put in merge, otherwise we'd get from right side.
        merged[mergedIndex] = rightValue;
        rightIndex++;
      }
      // Doesnt go out of range if Left and Right stay within range.
      mergedIndex++;
    }
    // If leftindex is less than midpoint, means theres still cards in left index.
    if (leftIndex < midpoint) {
      // arraycopy takes leftover space from leftindex and puts in merged if less than midpoint.
      // Remains go in to merged pile.
      System.arraycopy(data, leftIndex, merged, mergedIndex, midpoint - leftIndex);
    } else {
      // Leftover items in rightindex will copy to merged pile.
      System.arraycopy(data, rightIndex, merged, mergedIndex, to - rightIndex);
    }
    // Copies all elements from existing array and moving to existing array.
    System.arraycopy(merged, 0, data, from, merged.length);
  }

}
