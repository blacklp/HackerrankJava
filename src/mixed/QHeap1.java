package mixed;

import java.util.Scanner;

/**
 * Created by luciapasarin on 09/08/16.
 */
public class QHeap1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        Heap<Integer> heap = new Heap<>();
        int numQueries = Integer.parseInt(s.nextLine().trim());

        for (int i = 0; i < numQueries; i++) {
            String query = s.nextLine();
            char operation = query.charAt(0);
            int element = operation == '3' ? -1 : query.charAt(2) - '0';

            switch (operation) {
                case '1':
                    heap.add(element);
                    break;
                case '2':
                    heap.delete(element);
                    break;
                case '3':
                    heap.printMin();
                    break;
                default:
                    break;
            }
        }
        s.close();
    }

    private static class Heap<T extends Comparable<T>> {
        private int initialSize = 10;
        private T[] array = (T[])new Comparable[initialSize];
        private int index = 0;

        public void add(T element) {
            if (shouldResize()) {
                resize();
            }
            array[index] = element;
            pushUp(index);
            index++;
        }

        private boolean shouldResize() {
            return index == array.length;
        }

        public void delete(T element) {
            int elementIndex = searchIndex(element);
            T last = array[array.length - 1];
            array[elementIndex] = last;
            index--;
            pushDown(elementIndex);
        }

        public void printMin() {
            System.out.println(array[0]); // min heap
        }

        private void resize() {
            int newSize = array.length * 2;
            T[] copy = (T[])new Comparable[newSize];

            for (int i = 0; i < array.length; i++) {
                copy[i] = array[i];
            }
            array = copy;
        }

        private int searchIndex(T element) {
            int currentIndex = 0;

            while (currentIndex < array.length) {
                T currentElement = array[currentIndex];
                if (currentElement.equals(element)) {
                    return currentIndex;
                } else {
                    int childIndex1 = currentIndex * 2 + 1;
                    int childIndex2 = childIndex1 + 1;
                    T child1 = array[childIndex1];
                    T child2 = array[childIndex2];

                    if (child1.compareTo(currentElement) < 0 && child2.compareTo(currentElement) < 0) {
                        if (child1.compareTo(child2) > 0) {
                            currentIndex = childIndex1;
                        } else {
                            currentIndex = childIndex2;
                        }
                    } else if (child1.compareTo(currentElement) < 0) {
                        currentIndex = childIndex1;
                    } else if (child2.compareTo(currentElement) < 0) {
                        currentIndex = childIndex2;
                    } else {
                        return -1; // not found
                    }
                }
            }
            return -1; // not found
        }

        private void pushDown(int index) {
            T element = array[index];
            int childIndex1 = 2 * index + 1;
            int childIndex2 = childIndex1 + 1;

            T child1 = childIndex1 < array.length ? array[childIndex1] : null;
            T child2 = childIndex2 < array.length ? array[childIndex2] : null;
            int indexToFollow = -1;

//            if (element.compareTo(child1) > 0 && element.compareTo(child2) > 0) {
//                if (child1.compareTo(child2) > 0) {
//                    indexToFollow = childIndex1;
//                } else {
//                    indexToFollow = childIndex2;
//                }
//            } else if (element.compareTo(child1) > 0) {
//                indexToFollow = childIndex1;
//            } else if (element.compareTo(child2) > 0) {
//                indexToFollow = childIndex2;
//            } else {
//                // don't follow;
//            }
//            if (indexToFollow > -1) {
//                swap(index, indexToFollow);
//                pushDown(indexToFollow);
//            }
            if (followIndex(element, child1) && followIndex(element, child2)) {
                if (followIndex(child1, child2)) {
                    indexToFollow = childIndex1;
                } else {
                    indexToFollow = childIndex2;
                }
            } else if (followIndex(element, child1)) {
                indexToFollow = childIndex1;
            } else if (followIndex(element, child2)) {
                indexToFollow = childIndex2;
            } else {
                // don't follow;
            }
            if (indexToFollow > -1) {
                swap(index, indexToFollow);
                pushDown(indexToFollow);
            }
        }

        private boolean followIndex(T elem1, T elem2) {
            if (elem1 == null || elem2 == null) {
                return false;
            }
            return elem1.compareTo(elem2) > 0;
        }

        private void pushUp(int index) {
            if (index == 0) {
                return;
            }
            T element = array[index];

            int factor = index % 2 == 0 ? 2 : 1;
            int parentIndex = (index - factor) / 2;
//            int childIndex1 = 2 * index + 1; // -> childIndex - 1 = 2 * index -> (childIndex - 1) / 2 = index
//            int childIndex2 = childIndex1 + 1; // -> childIndex2 = 2 * index + 2 -> (childIndex - 2) / 2 = index

            T parent = array[parentIndex];

            if (parent.compareTo(element) > 0) {
                swap(index, parentIndex);
                pushUp(parentIndex);
            }
        }

        private void swap(int index1, int index2) {
            T aux = array[index1];
            array[index1] = array[index2];
            array[index2] = aux;
        }
    }
}
