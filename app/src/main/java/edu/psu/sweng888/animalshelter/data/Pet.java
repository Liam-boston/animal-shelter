package edu.psu.sweng888.animalshelter.data;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private String type; // Dog, Puppy, Cat, Kitten, Bunny, Hamster
    private String breed; // domestic short-hair, golden retriever, american bully, etc.
    private String color;
    private int age;
    private int daysInShelter;
    private double adoptionFee;

    public Pet() {
        // required empty constructor
    }

    public Pet(String name, String type, String breed, String color, int age, int shelterTime, double fee) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.color = color;
        this.age = age;
        this.daysInShelter = shelterTime;
        this.adoptionFee = fee;
    }

    public String toString(){
        return this.name + " / " + this.type + " / " + this.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDaysInShelter() {
        return daysInShelter;
    }

    public void setDaysInShelter(int daysInShelter) {
        this.daysInShelter = daysInShelter;
    }

    public double getAdoptionFee() {
        return adoptionFee;
    }

    public void setAdoptionFee(double adoptionFee) {
        this.adoptionFee = adoptionFee;
    }

}
