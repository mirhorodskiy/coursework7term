public class Request implements Comparable<Request> {
    int priority;

    public Request(int priority) {
        this.priority = priority;
    }

    public Request() {
        this.priority = 0;
    }

    @Override
    public String toString() {
        return "Request{" +
                "priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(Request o) {
        return 0;
    }
}
