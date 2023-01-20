import java.util.*;

public class Generator {
    public static Queue<Request> generatePriorityRequests(int count) {
        Queue<Request> requests = new PriorityQueue<>();
        for (int i = 0; i < count; i++) {
            requests.offer(new Request(1));
        }
        return requests;
    }
    public static Queue<Request> generateNotPriorityRequests(int count) {
        Queue<Request> requests = new PriorityQueue<>();
        for (int i = 0; i < count; i++) {
            requests.offer(new Request());
        }
        return requests;
    }
}
