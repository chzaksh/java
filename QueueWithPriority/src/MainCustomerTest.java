// This class tests the queue with CustomerRequest objects
class MainCustomerTest {
    public static void main(String[] args) {
    	QueueWithPriority<CustomerRequest> pq = new QueueWithPriority<>(3);

        CustomerRequest r1 = new CustomerRequest("Alice", "001", "Reset password");
        CustomerRequest r2 = new CustomerRequest("Bob", "002", "Upgrade plan");
        CustomerRequest r3 = new CustomerRequest("Charlie", "001", "Reset password");
        CustomerRequest r4 = new CustomerRequest("Dana", "003", "Cancel account");

        pq.add(r1, 2);
        pq.add(r2, 1);
        pq.add(r4, 5); // out of range, goes to priority 3

        System.out.println("--- Initial queue ---");
        for (CustomerRequest req : pq) {
            System.out.println(req);
        }

        System.out.println("Contains r3 (equal to r1): " + pq.contains(r3)); // true
        System.out.println("Remove r3: " + pq.remove(r3)); // true
        System.out.println("Poll: " + pq.poll()); // Bob (priority 1)
        System.out.println("Poll again: " + pq.poll()); // Dana (priority 3)
        System.out.println("Final size: " + pq.size()); // 0
    }
}
