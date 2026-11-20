package org.example.orders;

import java.util.*;

public class OrderQueue {
    private PriorityQueue<Order> pending = new PriorityQueue<>(Comparator.comparingInt(Order::priority).reversed());
    private Deque<Order> completed = new ArrayDeque<>();
    private Map<String, Status> statuses = new HashMap<>();

    public void submit(Order o) {
        pending.add(o);
        statuses.put(o.id(), Status.NEW);
    }

    public Optional<Order> processNext() {
        Order o = pending.poll();
        if (o == null) {
            return Optional.empty();
        }
        statuses.put(o.id(), Status.COMPLETED);
        completed.addFirst(o);
        if ( completed.size() > 10) {
            completed.pollLast();
        }
        return Optional.of(o);
    }

    public Optional<Order> peekNext() {
        return Optional.ofNullable(pending.peek());
    }

    public Optional<Order> undoLast() {
        Order o = completed.pollFirst();
        if (o == null) {
            return Optional.empty();
        }
        pending.add(o);
        statuses.put(o.id(), Status.NEW);
        return Optional.of(o);
    }

    public List<Order> getHistory() {
        return new ArrayList<>(completed);
    }

    public Map<Status, List<String>> groupByStatus() {
        Map<Status, List<String>> result = new EnumMap<>(Status.class);
        for (Map.Entry<String, Status> e : statuses.entrySet()) {
            result.computeIfAbsent(e.getValue(), k -> new ArrayList<>()).add(e.getKey());
        }
        return result;
    }

    public Set<String> findCustomersWithPendingOrders() {
        Set<String> result = new HashSet<>();
        for (Order o : pending) {
            result.add(o.customer());
        }
        return result;
    }

    public List<Order> getPending() {
        return new ArrayList<>(pending);
    }
}
