package com.roombooking.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String building;
    private String roomNumber;
    private int capacity;
    private boolean whiteboard;

    // No-arg constructor required by JPA
    public Room() {}

    // Parameterized constructor
    public Room(Long id, String name, String building, String roomNumber, int capacity, boolean whiteboard) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.whiteboard = whiteboard;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public boolean isWhiteboard() { return whiteboard; }
    public void setWhiteboard(boolean whiteboard) { this.whiteboard = whiteboard; }

    @Override
    public String toString() {
        return name + " (" + building + " " + roomNumber + ")";
    }
}