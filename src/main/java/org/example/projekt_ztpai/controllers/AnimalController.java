package org.example.projekt_ztpai.controllers;

import jakarta.validation.Valid;
import org.example.projekt_ztpai.dto.AnimalRequest;
import org.example.projekt_ztpai.dto.AnimalResponse;
import org.example.projekt_ztpai.models.Animal;
import org.example.projekt_ztpai.services.AnimalService;
import org.example.projekt_ztpai.mapper.AnimalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("api/animals")

public class AnimalController {
    @Autowired
    private final AnimalService animalService;

    public AnimalController(AnimalService service) {
        this.animalService = service;
    }

    @GetMapping
    public List<AnimalResponse> getAnimals() {
        return animalService.getAllAnimals().stream()
                .map(AnimalMapper::mapAnimalToAnimalResponse).toList();
    }

    @GetMapping("/{id}")
    public AnimalResponse getAnimal(@PathVariable long id) {
        return AnimalMapper.mapAnimalToAnimalResponse(animalService.getAnimal(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Animal addAnimal(@Valid @RequestBody AnimalRequest request) {
        return animalService.createAnimal(
                AnimalMapper.mapAnimalRequestToAnimal(request)
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable long id) {
        animalService.deleteAnimal(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public AnimalResponse updateAnimal(@PathVariable long id, @Valid @RequestBody AnimalRequest request) {
        Animal updatedAnimal = animalService.updateAnimal(
                id,
                AnimalMapper.mapAnimalRequestToAnimal(request)
        );

        return AnimalMapper.mapAnimalToAnimalResponse(updatedAnimal);
    }
}

