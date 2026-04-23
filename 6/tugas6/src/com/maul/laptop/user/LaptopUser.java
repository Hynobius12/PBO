package com.maul.laptop.user;
import com.maul.laptop.interfaces.Laptop;

public class LaptopUser {
    private Laptop laptop;

    public LaptopUser(Laptop laptop) {
        this.laptop = laptop;
    }

    public void turnOnLaptop() {
        laptop.powerOn();
    }

    public void turnOffLaptop() {
        laptop.powerOff();
    }

    public void makeVolumeUp() {
        laptop.volumeUp();
    }

    public void makeVolumeDown() {
        laptop.volumeDown();
    }
}