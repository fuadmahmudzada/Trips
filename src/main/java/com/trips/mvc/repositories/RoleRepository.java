package com.trips.mvc.repositories;

import com.trips.mvc.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllById(Iterable<Long> ids);

}
