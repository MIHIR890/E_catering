package com.example.annapurna;

public class MyTaskModel {

    String FOOD;

    String COACHNUMBER;


    String SEATNUMBER;



    String PASSENGER;


    String RATE;

    // default construcutor
    public MyTaskModel() {
    }

    //parameter constructor
    public MyTaskModel(String FOOD, String RATE, String PASSENGER, String COACHNUMBER, String SEATNUMBER) {
        this.FOOD = FOOD;
        this.PASSENGER=PASSENGER;
        this.RATE = RATE;
        this.COACHNUMBER = COACHNUMBER;
        this.SEATNUMBER = SEATNUMBER;
    }
    //GET STRING VALUE FOOD FROM DATABASE

    public String getFOOD() {
        return FOOD;
    }

    public void setFOOD(String FOOD) {
        this.FOOD = FOOD;
    }


    //GET STRING VALUE RATE FROM DATABASE



    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }


    //GET STRING VALUE PASSENGER FROM DATABASE

    public String getPASSENGER() {
        return PASSENGER;
    }

    public void setPASSENGER(String PASSENGER) {
        this.PASSENGER = PASSENGER;
    }


    public String getCOACHNUMBER() {
        return COACHNUMBER;
    }

    public void setCOACHNUMBER(String COACHNUMBER) {
        this.COACHNUMBER = COACHNUMBER;
    }

    public String getSEATNUMBER() {
        return SEATNUMBER;
    }

    public void setSEATNUMBER(String SEATNUMBER) {
        this.SEATNUMBER = SEATNUMBER;
    }

}
