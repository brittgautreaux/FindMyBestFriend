package com.example.britt_000.dogfinder.model;

/**
 * Created by Britt on 12/14/2017.
 */

public class Pet {
    private int id;
    private String name;
    private String type;
    private String breed;
    private String color;
    private int age;
    private String description;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getBreed(){
        return breed;
    }

    public void setBreed(String breed){
        this.breed = breed;
    }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
