package com.example.mazbahre_shop.Model;

public class PlateModel {

    public PlateModel(){
        //////// Empty Constructor ////////
    }

    private int plate_img;

    public PlateModel(int plate_img){
        this.plate_img = plate_img;
    }

    public int getPlate_img() {
        return plate_img;
    }

    public void setPlate_img(int plate_img) {
        this.plate_img = plate_img;
    }
}
