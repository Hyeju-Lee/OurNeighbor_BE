package solux.wansuki.OurNeighbor_BE.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import solux.wansuki.OurNeighbor_BE.domain.Apartment.Apartment;
import solux.wansuki.OurNeighbor_BE.service.ApartmentService;

import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
public class ApartmentController {
    private final ApartmentService apartmentService;

    @DeleteMapping("/apartments/{id}")
    public void delete(@PathVariable Long id) {
        apartmentService.delete(id);
    }

    @GetMapping("/apartments")
    public List<Apartment> findAll() {
        return apartmentService.findAll();
    }

}
