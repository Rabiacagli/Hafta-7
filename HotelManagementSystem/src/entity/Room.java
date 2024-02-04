package entity;

public class Room {

    private int room_id;
    private String hotel_name;
    private String pension_type;
    private String room_type;
    private int stock;
    private double adult_price;
    private double child_price;
    private int bed_capacity;
    private String mkare;
    private boolean tv;
    private boolean minibar;
    private boolean konsol;
    private boolean kasa;
    private boolean projeksiyon;
    private int hotel_id;

    public Room() {
    }

    public Room(int room_id, String hotel_name, String pension_type, String room_type, int stock, double adult_price, double child_price, int bed_capacity, String mkare, boolean tv, boolean minibar, boolean konsol, boolean kasa, boolean projeksiyon,int hotel_id) {
        this.room_id = room_id;
        this.hotel_name = hotel_name;
        this.pension_type = pension_type;
        this.room_type = room_type;
        this.stock = stock;
        this.adult_price = adult_price;
        this.child_price = child_price;
        this.bed_capacity = bed_capacity;
        this.mkare = mkare;
        this.tv = tv;
        this.minibar = minibar;
        this.konsol = konsol;
        this.kasa = kasa;
        this.projeksiyon = projeksiyon;
        this.hotel_id = hotel_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAdult_price() {
        return adult_price;
    }

    public void setAdult_price(double adult_price) {
        this.adult_price = adult_price;
    }

    public double getChild_price() {
        return child_price;
    }

    public void setChild_price(double child_price) {
        this.child_price = child_price;
    }

    public int getBed_capacity() {
        return bed_capacity;
    }

    public void setBed_capacity(int bed_capacity) {
        this.bed_capacity = bed_capacity;
    }

    public String getMkare() {
        return mkare;
    }

    public void setMkare(String mkare) {
        this.mkare = mkare;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isKonsol() {
        return konsol;
    }

    public void setKonsol(boolean konsol) {
        this.konsol = konsol;
    }

    public boolean isKasa() {
        return kasa;
    }

    public void setKasa(boolean kasa) {
        this.kasa = kasa;
    }

    public boolean isProjeksiyon() {
        return projeksiyon;
    }

    public void setProjeksiyon(boolean projeksiyon) {
        this.projeksiyon = projeksiyon;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
}


