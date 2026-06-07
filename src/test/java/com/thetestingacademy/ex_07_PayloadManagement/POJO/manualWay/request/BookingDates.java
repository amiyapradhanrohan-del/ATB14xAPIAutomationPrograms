package com.thetestingacademy.ex_07_PayloadManagement.POJO.manualWay.request;

public class BookingDates {

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    private String checkin;
    private String checkout;

}

//This is called POJO,"POJO is nothing but a simple class with instance variable and getter setter"