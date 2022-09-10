package ru.gothmog.ws.auth.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gothmog.ws.auth.core.model.auth.Role;
import ru.gothmog.ws.auth.core.model.auth.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Boolean existsByRoleName(RoleName roleName);

    Optional<Role> findByRoleName(RoleName roleName);
}
