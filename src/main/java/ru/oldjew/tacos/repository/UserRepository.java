package ru.oldjew.tacos.repository;

import org.springframework.data.repository.CrudRepository;
import ru.oldjew.tacos.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
