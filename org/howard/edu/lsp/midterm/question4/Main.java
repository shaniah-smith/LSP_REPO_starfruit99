package org.howard.edu.lsp.midterm.question4;

import java.util.*;

/**
 * Driver class to test Smart Campus Devices.
 * Provided by Professor for LSP Midterm – Fall 2025
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SMART CAMPUS DEVICE TEST ===");

        // Create some devices
        DoorLock lock = new DoorLock("LOCK-101", "Engineering Building", 80);
        Thermostat thermo = new Thermostat("THERM-202", "Library", 22.5);
        Camera cam = new Camera("CAM-303", "Quad Entrance", 60);

        // Store in list for later iteration
        List<Device> devices = new ArrayList<>();
        devices.add(lock);
        devices.add(thermo);
        devices.add(cam);

        // Demonstrate heartbeat simulation
        System.out.println("\n--- Heartbeat Test ---");
        for (Device d : devices) {
            System.out.println(d.heartbeat());
        }

        // Demonstrate invalid battery test
        System.out.println("\n--- Invalid Battery Test ---");
        try {
            lock.setBatteryPercent(150); // should throw exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // Demonstrate connectivity test
        System.out.println("\n--- Connectivity Test ---");
        for (Device d : devices) {
            if (d instanceof Networked) {
                ((Networked) d).connect();
                System.out.println(d.getId() + " connected.");
            }
        }

        // Demonstrate battery-powered device reporting
        System.out.println("\n--- Battery Report ---");
        for (Device d : devices) {
            if (d instanceof BatteryPowered) {
                BatteryPowered bp = (BatteryPowered) d;
                System.out.println(d.getId() + " battery: " + bp.getBatteryPercent() + "%");
            }
        }

        // Display full device status
        System.out.println("\n--- Final Device Status ---");
        for (Device d : devices) {
            System.out.println(d.getStatus());
        }

        System.out.println("\n=== END OF TEST ===");
    }
}
