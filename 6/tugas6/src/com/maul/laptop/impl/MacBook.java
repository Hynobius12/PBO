package com.maul.laptop.impl;

import com.maul.laptop.abstracts.LaptopAbstract;

public class MacBook extends LaptopAbstract {

    @Override
    public void powerOn() {
        System.out.println("MacBook menyala...");
    }

    @Override
    public void powerOff() {
        System.out.println("MacBook mati...");
    }

    @Override
    public void volumeUp() {
        volume += 5;
        System.out.println("Volume MacBook naik");
        statusVolume();
    }

    @Override
    public void volumeDown() {
        volume -= 5;
        System.out.println("Volume MacBook turun");
        statusVolume();
    }
}