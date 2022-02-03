package solux.wansuki.OurNeighbor_BE.domain.Apartment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    Optional<Apartment> findByApartName(String apartName);
}
