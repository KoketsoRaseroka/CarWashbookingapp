package com.example.launch;

public class Booking {
    private int bookingId;
    private String carType;
    private String washServiceType;
    private String bookingDateTime;
    private String paymentMethod;

    public Booking(int bookingId, String carType, String washServiceType, String bookingDateTime, String paymentMethod) {
        this.bookingId = bookingId;
        this.carType = carType;
        this.washServiceType = washServiceType;
        this.bookingDateTime = bookingDateTime;
        this.paymentMethod = paymentMethod;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getCarType() {
        return carType;
    }

    public String getWashServiceType() {
        return washServiceType;
    }

    public String getBookingDateTime() {
        return bookingDateTime;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
