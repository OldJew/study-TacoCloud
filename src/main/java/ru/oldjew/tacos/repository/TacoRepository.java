package ru.oldjew.tacos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.oldjew.tacos.model.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
