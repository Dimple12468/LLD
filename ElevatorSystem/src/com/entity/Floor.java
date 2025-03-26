package com.entity;

import com.util.Direction;

public class Floor {

    int floorNo;
    ExternalButton externalButton;

    public Floor(int no){
        this.floorNo = no;
        externalButton = new ExternalButton();
    }

    public void pressButton(Direction dircetion){
        externalButton.request(floorNo, dircetion);
    }

}
