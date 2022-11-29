package ru.oldjew.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.oldjew.tacos.model.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
