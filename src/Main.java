
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int time = 120;
        int queueCapacity = 10;
        int requestsCount = 0;
        int lostRequestsCount = 0;
        int processedRequestsCount = 0;
        int summaryQueue = 0;
        int priorityRequestsCount = 0;
        int notPriorityRequestsCount = 0;
        Queue<Request> requestQueue = new PriorityQueue<>(queueCapacity);
        for (int i = 0; i < time; i++) {
            Queue<Request> priorityRequests = Generator.generatePriorityRequests((int) (Math.random() * 4));
            Queue<Request> notPriorityRequests = Generator.generateNotPriorityRequests((int) (Math.random() * (9 - 5) + 5));
            requestsCount = requestsCount + priorityRequests.size() + notPriorityRequests.size();
            priorityRequestsCount += priorityRequests.size();
            notPriorityRequestsCount += notPriorityRequests.size();

            System.out.println("=======================================");
            System.out.println("Step: " + (i + 1));
            System.out.println("All requests count: " + requestsCount);
            System.out.println("priority requests count: " + priorityRequests.size());
            System.out.println("not priority requests count: " + notPriorityRequests.size());


            int processingCapacity = (int) (Math.random() * (11 - 6) + 6);
            System.out.println("Processing capacity: " + processingCapacity);
            for (int j = 0; j < processingCapacity; j++) {
                if (!priorityRequests.isEmpty()) {
                    if(priorityRequests.poll() != null)
                        processedRequestsCount++;
                } else if (!requestQueue.isEmpty()) {
                    if(requestQueue.poll() != null)
                        processedRequestsCount++;
                } else {
                    if(notPriorityRequests.poll() != null)
                        processedRequestsCount++;
                }
            }

            if (!priorityRequests.isEmpty()) {
                while (!priorityRequests.isEmpty()) {
                    if (requestQueue.size() == queueCapacity) {
                        lostRequestsCount++;
                        requestQueue.poll();
                    }
                    requestQueue.offer(priorityRequests.poll());
                }
            }
            if (!notPriorityRequests.isEmpty()) {
                while (!notPriorityRequests.isEmpty()) {
                    if (requestQueue.size() == queueCapacity) {
                        lostRequestsCount++;
                        System.out.println("LOST");
                        notPriorityRequests.poll();
                    } else
                        requestQueue.offer(notPriorityRequests.poll());
                }
            }
            summaryQueue += requestQueue.size();
            System.out.println("Queue size: " + requestQueue.size());
            System.out.println("All requests count: " + requestsCount);
            System.out.println("Processed requests count: " + processedRequestsCount);
            System.out.println("Lost requests count: " + lostRequestsCount);
        }

        System.out.println("=============================");
        System.out.println("SUMMARY: ");
        System.out.println("All requests count: " + requestsCount);
        System.out.println("Processed requests count: " + processedRequestsCount);
        System.out.println("Queue size: " + requestQueue.size());
        System.out.println("Priority requests count: " + priorityRequestsCount);
        System.out.println("Not priority requests count: " + notPriorityRequestsCount);
        System.out.println("Lost requests count: " + lostRequestsCount);
        System.out.println("Processing probability: " + (double)processedRequestsCount/requestsCount);
        System.out.println("Average queue size: " + (double)summaryQueue / time);

        // 1 CASE
        // 13 requests
        // 10 processed
        // queue -> 3

        // 2 case
        // 10 request
        // 12 processed -> 3 form queue + 9 from case 2
        // queue -> 1

        // 3 CASE
        // 11 requests
        // 8 processed -> 1 form queue + 7 from CASE 3
        // queue -> 4

    }
}