package com.rafaelehlert.aluraflix.repositories;

import com.rafaelehlert.aluraflix.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByRolesName(String roleName);
}
