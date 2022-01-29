package solux.wansuki.OurNeighbor_BE.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.ApartmentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApartmentService {
    private final ApartmentRepository apartmentRepository;

    @Transactional
    public void delete(Long id) {
        apartmentRepository.deleteById(id);
    }

    public List<Apartment> findAll() {
        return apartmentRepository.findAll();
    }
}
