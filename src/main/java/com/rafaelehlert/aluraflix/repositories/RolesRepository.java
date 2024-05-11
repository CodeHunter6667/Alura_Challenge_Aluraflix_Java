package com.rafaelehlert.aluraflix.repositories;

import com.rafaelehlert.aluraflix.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    @Query("SELECT obj FROM Roles obj WHERE obj.nomeRole = :roleName")
    Roles searchByNomeRole(String roleName);
}
