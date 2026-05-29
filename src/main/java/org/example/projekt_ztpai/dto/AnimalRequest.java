package org.example.projekt_ztpai.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class AnimalRequest {

    @NotNull(message = "Name required")
    @Size(min = 2, max = 35, message= "Name must be between 2 and 35 characters")
    private String name;

    @NotNull(message = "Age required")
    @Min(value=0, message = "Minimum value of the age is 0")
    @Max(value=100, message = "Maximum value of the age is 100")
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}



