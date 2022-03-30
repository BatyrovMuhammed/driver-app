package peaksoft.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.driverapp.models.entities.BankAccount;
import peaksoft.driverapp.models.entities.Driver;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {
    boolean existsByAuthInfoEmail(String email);

    Optional<Driver> findDriverByAuthInfoEmail(String email);

    @Query("select d.bankAccount from Driver d where d.authInfo.email = ?1")
    Optional<BankAccount> findBankAccountByDriverEmail(String email);
}