package peaksoft.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.driverapp.models.entities.AuthInfo;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Beksultan
 */
@Repository
public interface AuthInfoRepository extends JpaRepository<AuthInfo, UUID> {
    Optional<AuthInfo> findByEmail(String email);
}
