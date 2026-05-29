package org.example.projekt_ztpai;

import org.example.projekt_ztpai.exception.NotFound;
import org.example.projekt_ztpai.models.Animal;
import org.example.projekt_ztpai.repository.AnimalRepository;
import org.example.projekt_ztpai.services.AnimalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnimalServiceTests {

    AnimalRepository repository = Mockito.mock(AnimalRepository.class);
    AnimalService animalService = new AnimalService(repository);

    // HAPPY PATHS

    //getAllAnimals()
    @Test
    public void should_return_all_Animals() {
        List<Animal> animals = new ArrayList<>();
        Animal animal = new Animal();
        animal.setName("Animal 1");
        animals.add(animal);
        Animal animal2 = new Animal();
        animal2.setName("Animal 2");
        animals.add(animal2);

        Mockito.when(repository.findAll()).thenReturn(animals);

        List<Animal> allAnimals = animalService.getAllAnimals();
        Assertions.assertEquals(animals, allAnimals);
    }

    @Test
    public void should_return_empty_list_when_no_Animals() {
        List<Animal> animals = new ArrayList<>();

        Mockito.when(repository.findAll()).thenReturn(animals);

        List<Animal> allAnimals = animalService.getAllAnimals();
        Assertions.assertEquals(animals, allAnimals);
    }

    //getAnimal()
    @Test
    public void should_return_animal_by_id() {
        Animal animal = new Animal();
        animal.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(animal));

        Animal service_animal = animalService.getAnimal(1L);
        Assertions.assertEquals(animal, service_animal);
    }

    //createAnimal()
    @Test
    public void should_create_animal() {
        Animal animal = new Animal();
        animal.setName("Animal 1");
        animal.setAge(1);
        animal.setId(1L);

        Mockito.when(repository.save(animal)).thenReturn(animal);

        Animal createdAnimal = animalService.createAnimal(animal);

        Assertions.assertEquals(animal, createdAnimal);
    }

    @Test
    public void should_create_animal_despite_no_id() {
        Animal animal = new Animal();
        animal.setName("Animal 1");
        animal.setAge(1);

        Mockito.when(repository.save(animal)).thenReturn(animal);

        Animal createdAnimal = animalService.createAnimal(animal);

        Assertions.assertEquals(animal, createdAnimal);
    }

    //deleteAnimal()
    @Test
    public void should_delete_animal() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Animal 1");
        animal.setAge(1);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(animal));

        animalService.deleteAnimal(1L);

        Mockito.verify(repository).delete(animal);

    }

    //updateAnimal()
    @Test
    public void should_update_animal() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setName("Animal 1");
        animal.setAge(1);

        Animal animal2 = new Animal();
        animal2.setName("Animal 2");
        animal2.setAge(2);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(animal));

        // return the same value that was given to function save()
        Mockito.when(repository.save(Mockito.any(Animal.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Animal updatedAnimal = animalService.updateAnimal(1L, animal2);

        Assertions.assertEquals("Animal 2", updatedAnimal.getName());
        Assertions.assertEquals(2, updatedAnimal.getAge());
    }


    // NEGATIVE PATHS

    @Test
    public void should_throw_notfound_when_get_animal_that_does_not_exist() {

        Mockito.when(repository.findById(8L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFound.class, () -> animalService.getAnimal(8L));
    }

    @Test
    public void should_throw_notfound_when_delete_animal_that_does_not_exist() {

        Mockito.when(repository.findById(8L)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFound.class, () -> animalService.deleteAnimal(8L));
    }

    @Test
    public void should_throw_notfound_when_update_animal_that_does_not_exist() {
        Animal animal = new Animal();
        animal.setName("Animal 1");
        animal.setAge(1);

        Mockito.when(repository.findById(8L)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFound.class, () -> animalService.updateAnimal(8L, animal));
    }
    
}
