package org.example.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderProcessor {
    public static final List<String> auditLog = new ArrayList<>();
    public static final Consumer<Order> LOG_TO_CONSOLE = order -> System.out.println("ZAMÓWIENIE: " + order.id() + ", IMIĘ KLIENTA: " + order.customer().name());
    public static final Consumer<Order> ADD_TO_AUDIT = order -> auditLog.add(order.id());
    public static final Consumer<Order> SEND_CONFIRMATION = order -> System.out.println("ADRES KLIENTA: " + order.customer().email() + ", ID ZAMÓWIENIA: " + order.id());

    public static Consumer<Order> buildPipeline(boolean withAudit, boolean withEmail) {
        Consumer<Order> result = LOG_TO_CONSOLE;
        if (withAudit) {
            result = result.andThen(ADD_TO_AUDIT);
        }
        if (withEmail) {
            result = result.andThen(SEND_CONFIRMATION);
        }
        return result;
    }

    private static int counter = 0;
    public static final Supplier<String> generateOrderId = () -> "ORD-" + ++counter;
    public static Order createOrder(Customer c, List<Product> items, Supplier<String> idGenerator) {
        return new Order(idGenerator.get(), c, items);
    }
}
