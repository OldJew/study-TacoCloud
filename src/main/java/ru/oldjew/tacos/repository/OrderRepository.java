package ru.oldjew.tacos.repository;

import ru.oldjew.tacos.model.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder tacoOrder);
}
