package com.maul.laptop.impl;
import com.maul.laptop.abstracts.LaptopAbstract;

public class Toshiba extends LaptopAbstract {

    @Override
    public void powerOn() {
        System.out.println("Laptop Toshiba menyala...");
    }

    @Override
    public void powerOff() {
        System.out.println("Laptop Toshiba mati...");
    }

    @Override
    public void volumeUp() {
        volume += 10;
        System.out.println("Volume Toshiba naik");
        statusVolume();
    }

    @Override
    public void volumeDown() {
        volume -= 10;
        System.out.println("Volume Toshiba turun");
        statusVolume();
    }
}