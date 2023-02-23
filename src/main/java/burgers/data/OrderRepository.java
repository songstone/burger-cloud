package burgers.data;

import burgers.domain.Order;

public interface OrderRepository {
    Order save(Order order);
}
