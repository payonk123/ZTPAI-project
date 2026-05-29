package org.example.projekt_ztpai.mapper;

import org.example.projekt_ztpai.dto.AnimalRequest;
import org.example.projekt_ztpai.dto.AnimalResponse;
import org.example.projekt_ztpai.models.Animal;

public class AnimalMapper {

    public static Animal mapAnimalRequestToAnimal(AnimalRequest req) {
        Animal animal = new Animal();
        animal.setName(req.getName());
        animal.setAge(req.getAge());
        return animal;
    }
    public static AnimalResponse mapAnimalToAnimalResponse(Animal animal) {
        AnimalResponse res = new AnimalResponse();
        res.setName(animal.getName());
        res.setAge(animal.getAge());
        return res;
    }
}
