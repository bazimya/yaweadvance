package com.example.yawe;
public class container {
    int size;
    int water;
    int acid;
    int temperature;

    public container(int size, int water, int acid, int temperature) {
        this.size = size;
        this.water = water;
        this.acid = acid;
        this.temperature = temperature;
    }

    public container() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
