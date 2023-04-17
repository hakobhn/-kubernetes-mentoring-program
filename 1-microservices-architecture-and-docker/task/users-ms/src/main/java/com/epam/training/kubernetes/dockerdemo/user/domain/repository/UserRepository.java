package com.epam.training.kubernetes.dockerdemo.user.domain.repository;


import com.epam.training.kubernetes.dockerdemo.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findOneByUsername(String username);

}
