package ru.oldjew.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.oldjew.tacos.model.TacoOrder;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends CrudRepository<TacoOrder, UUID> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
