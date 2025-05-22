import java.util.*;
// This class represents a customer service request with name, ID and message
class CustomerRequest {
    private final String name;
    private final String id;
    private final String request;

    // Constructor: initializes a customer request
    public CustomerRequest(String name, String id, String request) {
        this.name = name;
        this.id = id;
        this.request = request;
    }

    // Returns a string representation of the request
    public String toString() {
        return "[" + name + ", ID: " + id + ", Request: " + request + "]";
    }

    // Checks equality based on ID and request message
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerRequest)) return false;
        CustomerRequest other = (CustomerRequest) o;
        return id.equals(other.id) && request.equals(other.request);
    }

    // Generates a hash code based on ID and request message
    @Override
    public int hashCode() {
        return Objects.hash(id, request);
    }
}
