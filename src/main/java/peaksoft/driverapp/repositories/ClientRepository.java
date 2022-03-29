package peaksoft.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.driverapp.models.entities.Client;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    @Query("select case when count(c) > 0 then true else false end" +
            " from Client c where c.authInfo.email = ?1")
    boolean existsByEmail(String email);

    @Query("select c from Client c where c.authInfo.email = ?1")
    Optional<Client> findByEmail(String email);

    Optional<Client> findByPhoneNumber(String phoneNumber);
}
