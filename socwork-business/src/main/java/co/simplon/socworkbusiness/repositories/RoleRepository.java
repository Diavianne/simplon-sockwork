package co.simplon.socworkbusiness.repositories;

import java.util.Set;
import co.simplon.socworkbusiness.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);

    Set<Role> findByIsDefaultTrue();

}
