package entity;

import java.time.LocalDate;

public class Reservation {
    private int reservation_id;
    private int room_id;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private double total_price;
    private int guestCount;
    private String guestName;
    private String guestId;
    private String guestMail;
    private String guestPhone;

    public Reservation(int reservation_id) {
    }public Reservation() {
    }

    public Reservation(int reservation_id, int room_id, LocalDate checkinDate, LocalDate checkoutDate, double total_price, int guestCount, String guestName, String guestId, String guestMail, String guestPhone) {
        this.reservation_id = reservation_id;
        this.room_id = room_id;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.total_price = total_price;
        this.guestCount = guestCount;
        this.guestName = guestName;
        this.guestId = guestId;
        this.guestMail = guestMail;
        this.guestPhone = guestPhone;
    }



    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(LocalDate checkinDate) {
        this.checkinDate = checkinDate;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }
}
