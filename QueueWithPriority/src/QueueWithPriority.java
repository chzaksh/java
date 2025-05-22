import java.util.*;

//This class implements a priority queue with N levels of priority using an array of linked lists
public class QueueWithPriority<T> implements Iterable<T> {
    private final List<LinkedList<T>> queues;
    private final int maxPriority;
    private int size;

    // Constructor: initializes the queue with a maximum priority level
    public QueueWithPriority(int n) {
        maxPriority = Math.min(Math.max(n, 1), 10);
        queues = new ArrayList<>(maxPriority);
        for (int i = 0; i < maxPriority; i++) {
            queues.add(new LinkedList<>());
        }
        size = 0;
    }

    // Adds an element with the specified priority to the queue
    public void add(T element, int priority) {
        int p = priority < 1 || priority > maxPriority ? maxPriority : priority;
        queues.get(p - 1).add(element);
        size++;
    }

    // Removes and returns the element with the highest priority (FIFO within priority)
    public T poll() {
        for (int i = 0; i < maxPriority; i++) {
            LinkedList<T> queue = queues.get(i);
            if (!queue.isEmpty()) {
                size--;
                return queue.poll();
            }
        }
        return null;
    }

    // Checks if the specified element exists in the queue
    public boolean contains(T element) {
        for (LinkedList<T> queue : queues) {
            if (queue.contains(element)) return true;
        }
        return false;
    }

    // Removes the first occurrence of the specified element and returns true if removed
    public boolean remove(T element) {
        for (LinkedList<T> queue : queues) {
            if (queue.remove(element)) {
                size--;
                return true;
            }
        }
        return false;
    }

    // Returns the total number of elements in the queue
    public int size() {
        return size;
    }

    // Returns an iterator that goes through the elements from highest to lowest priority
    @Override
    public Iterator<T> iterator() {
        List<T> all = new ArrayList<>();
        for (int i = 0; i < maxPriority; i++) {
            all.addAll(queues.get(i));
        }
        return all.iterator();
    }
}

