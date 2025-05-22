// This class tests the queue with String elements
class MainStringTest {
    public static void main(String[] args) {
        QueueWithPriority<String> pq = new QueueWithPriority<>(5);

        System.out.println("--- Inserting values with various priorities ---");
        pq.add("A", 3);
        pq.add("B", 1);
        pq.add("C", 1);
        pq.add("D", 2);
        pq.add("E", 10); // will be inserted with priority 5
        pq.add("F", -1);  // invalid priority, will also go to priority 5

        System.out.println("--- Iterator output (should be sorted by priority) ---");
        for (String s : pq) {
            System.out.println(s);
        }

        System.out.println("Poll: " + pq.poll()); // Should be B
        System.out.println("Contains 'B': " + pq.contains("B")); // false
        System.out.println("Remove 'C': " + pq.remove("C")); // true
        System.out.println("Remove 'Z': " + pq.remove("Z")); // false (not in queue)
        System.out.println("Contains 'D': " + pq.contains("D")); // true
        System.out.println("Size: " + pq.size()); // Should be 3

        System.out.println("--- Poll all until empty ---");
        while (pq.size() > 0) {
            System.out.println("Polling: " + pq.poll());
        }
        System.out.println("Final size: " + pq.size()); // 0
        System.out.println("Poll on empty: " + pq.poll()); // null
    }
}