package com;

public class Phone {
    private String manufacturer = "Apple";
    private String model = "iPhone 6";
    private int ram = 4;
    private Integer storage_size = 64;
    private Double display_size =  5.4;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public Integer getStorage_size() {
        return storage_size;
    }

    public void setStorage_size(Integer storage_size) {
        this.storage_size = storage_size;
    }

    public Double getDisplay_size() {
        return display_size;
    }

    public void setDisplay_size(Double display_size) {
        this.display_size = display_size;
    }
}
