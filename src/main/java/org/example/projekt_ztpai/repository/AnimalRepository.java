package org.example.projekt_ztpai.repository;

import org.example.projekt_ztpai.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}


