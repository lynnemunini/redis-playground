package com.grayseal.bookstore.repositories;

import com.grayseal.bookstore.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository interface for {@link Role} entities.
 *
 * <p>This interface extends {@link CrudRepository} to provide basic CRUD operations for {@link Role} entities.
 * It also includes a custom query method to find a {@link Role} by its name.</p>
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

    /**
     * Finds the first {@link Role} entity by its name.
     *
     * <p>This method retrieves a {@link Role} entity where the name matches the specified role name.
     * If multiple roles have the same name, only the first one is returned.</p>
     *
     * @param role the name of the role to search for
     * @return the first {@link Role} with the given name, or {@code null} if no role is found
     */
    Role findFirstByname(String role);
}
