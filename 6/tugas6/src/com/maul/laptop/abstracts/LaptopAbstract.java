package com.maul.laptop.abstracts;


import com.maul.laptop.interfaces.Laptop;
public abstract class LaptopAbstract implements Laptop {
    protected int volume = 50;

    public void statusVolume() {
        System.out.println("Volume sekarang: " + volume);
    }
}