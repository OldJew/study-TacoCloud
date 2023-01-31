package ru.oldjew.tacos.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import ru.oldjew.tacos.model.TacoOrder;
import ru.oldjew.tacos.model.User;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {

    List<TacoOrder> findByDeliveryZip(String deliveryZip);
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
