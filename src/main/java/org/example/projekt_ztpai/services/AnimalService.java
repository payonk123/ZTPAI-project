package org.example.projekt_ztpai.services;

import org.example.projekt_ztpai.models.Animal;
import org.example.projekt_ztpai.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.example.projekt_ztpai.exception.NotFound;
import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository repository;

    public AnimalService(AnimalRepository animalRepository) {
        this.repository = animalRepository;
    }

    // GET all records from the database
    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    // GET a specific record - if doesn't exist throw 404
    public Animal getAnimal(long id) {
        return repository.findById(id).orElseThrow(NotFound::new);
    }

    // POST - create a new animal, assign id automatically
    public Animal createAnimal(Animal animal) {
        return repository.save(animal);
    }

    // DELETE animal by id
    public void deleteAnimal(long id) {
        Animal animal = getAnimal(id);
        repository.delete(animal);
    }

    // PUT - update an existing animal
    public Animal updateAnimal(long id, Animal updatedAnimal) {
        Animal existingAnimal = getAnimal(id);

        existingAnimal.setName(updatedAnimal.getName());
        existingAnimal.setAge(updatedAnimal.getAge());

        return repository.save(existingAnimal);
    }
}










