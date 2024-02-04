package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReservationView extends Layout {
    Room room;
    RoomManager roomManager;
    Reservation reservation;
    ReservationManager reservationManager;
    Hotel hotel;
    HotelManager hotelManager;
    private JPanel contanier;
    private JPanel pnl_top;
    private JPanel pnl_mid;
    private JPanel pnl_bottom;
    private JTextField fld_otel_adı;
    private JTextField fld_city;
    private JTextField fld_yıldız;
    private JRadioButton btn_carpark;
    private JRadioButton btn_concierge;
    private JRadioButton btn_wifi;
    private JRadioButton btn_spa;
    private JRadioButton btn_pool;
    private JRadioButton btn_roomserv;
    private JRadioButton btn_fitness;
    private JLabel lbl_otel_name;
    private JLabel lbl_city;
    private JLabel lbl_star;
    private JTextField fld_room_type;
    private JTextField fld_pension_type;
    private JTextField fld_checkin;
    private JTextField fld_checkout;
    private JTextField fld_total;
    private JRadioButton btn_tv;
    private JTextField textField6;
    private JTextField textField7;
    private JRadioButton btn_minibar;
    private JRadioButton btn_oyunKonsolu;
    private JRadioButton btn_projeksiyon;
    private JRadioButton btn_kasa;
    private JLabel lbl_room_type;
    private JLabel lbl_pension_type;
    private JLabel lbl_checkin;
    private JLabel lbl_checkout;
    private JLabel lbl_price;
    private JLabel lbl_yatak;
    private JLabel lbl_alan;
    private JLabel lbl_info2;
    private JLabel lbl_info;
    private JLabel lbl_info3;
    private JTextField fld_name;
    private JTextField fld_guestid;
    private JTextField fld_guestcount;
    private JTextField fld_mail;
    private JTextField fld_tel;
    private JButton btn_save;
    private JLabel lbl_name;
    private JLabel lbl_guestid;
    private JLabel lbl_guestcount;
    private JLabel lbl_mail;
    private JLabel lbl_guesttel;
    private EmployeeView employeeView;
    public ReservationView(Room room) {

        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.room = room;
        this.reservationManager = new ReservationManager();
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(contanier);
        this.guiInitiliaze(1000, 800);

        if (this.room.getRoom_id() != 0) {

            this.fld_otel_adı.setText(this.room.getHotel_name());
            this.fld_city.setText(this.hotel.getAddress());
            this.fld_yıldız.setText(this.hotel.getStar());
            this.btn_carpark.setSelected(this.hotel.isCarPark());
            this.btn_concierge.setSelected(this.hotel.isConcierge());
            this.btn_spa.setSelected(this.hotel.isSpa());
            this.btn_wifi.setSelected(this.hotel.isWifi());
            this.btn_fitness.setSelected(this.hotel.isFitness());
            this.btn_pool.setSelected(this.hotel.isPool());
            this.btn_roomserv.setSelected(this.hotel.isRoomService());
        }
    }

       public ReservationView (Reservation reservation) {

        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.room = room;
        this.reservationManager = new ReservationManager();
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(contanier);
        this.guiInitiliaze(1000, 800);



            this.fld_otel_adı.setText(this.room.getHotel_name());
            this.fld_city.setText(this.hotel.getAddress());
            this.fld_yıldız.setText(this.hotel.getStar());
            this.btn_carpark.setSelected(this.hotel.isCarPark());
            this.btn_concierge.setSelected(this.hotel.isConcierge());
            this.btn_spa.setSelected(this.hotel.isSpa());
            this.btn_wifi.setSelected(this.hotel.isWifi());
            this.btn_fitness.setSelected(this.hotel.isFitness());
            this.btn_pool.setSelected(this.hotel.isPool());
            this.btn_roomserv.setSelected(this.hotel.isRoomService());


        /*this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotelname, this.fld_hoteladres, this.fld_hotelmail, this.fld_hoteltel})) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                this.hotel.setName(fld_hotelname.getText());
                this.hotel.setAddress(fld_hoteladres.getText());
                this.hotel.setPhone(fld_hoteladres.getText());
                this.hotel.setMail(fld_hotelmail.getText());
                this.hotel.setStar((String) cmb_hotel_star.getSelectedItem());
                this.hotel.setCarPark(btn_carpark.isSelected());
                this.hotel.setConcierge(btn_concierge.isSelected());
                this.hotel.setSpa(btn_spa.isSelected());
                this.hotel.setWifi(btn_wifi.isSelected());
                this.hotel.setFitness(btn_fitness.isSelected());
                this.hotel.setPool(btn_pool.isSelected());
                this.hotel.setRoomService(btn_roomsrvc.isSelected());


                if (this.hotel.getId() != 0) {
                    result = this.hotelManager.update(this.hotel);

                } else {
                    result = this.hotelManager.save(this.hotel);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });*/

    }

}
