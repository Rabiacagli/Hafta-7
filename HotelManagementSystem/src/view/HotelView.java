package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

public class HotelView extends Layout {
    private JPanel contanier;
    private JLabel lbl_save;
    private JPanel pnl_info;
    private JPanel pnl_info2;
    private JRadioButton btn_carpark;
    private JRadioButton btn_wifi;
    private JRadioButton btn_pool;
    private JRadioButton btn_fitness;
    private JRadioButton btn_concierge;
    private JRadioButton btn_spa;
    private JRadioButton btn_roomsrvc;
    private JButton btn_save;
    private JTextField fld_hotelname;
    private JTextField fld_hotelmail;
    private JTextField fld_hoteltel;
    private JTextField fld_hoteladres;
    private JComboBox cmb_hotel_star;
    private JLabel lbl_hotelname;
    private JLabel lbl_hotelmail;
    private JLabel lbl_hoteltel;
    private JLabel lbl_hoteladres;
    private JLabel lbl_star;

    private Hotel hotel;
    private HotelManager hotelManager;



    public HotelView(Hotel hotel) {
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(contanier);
        this.guiInitiliaze(500,500);


        if (this.hotel.getId() != 0){

            this.fld_hotelname.setText(this.hotel.getName());
            this.fld_hoteladres.setText(this.hotel.getAddress());
            this.fld_hotelmail.setText(this.hotel.getMail());
            this.fld_hoteltel.setText(this.hotel.getPhone());
            this.cmb_hotel_star.setSelectedItem(this.hotel.getStar());
            this.btn_carpark.setSelected(this.hotel.isCarPark());
            this.btn_concierge.setSelected(this.hotel.isConcierge());
            this.btn_spa.setSelected(this.hotel.isSpa());
            this.btn_wifi.setSelected(this.hotel.isWifi());
            this.btn_fitness.setSelected(this.hotel.isFitness());
            this.btn_pool.setSelected(this.hotel.isPool());
            this.btn_roomsrvc.setSelected(this.hotel.isRoomService());
        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotelname, this.fld_hoteladres,this.fld_hotelmail,this.fld_hoteltel})) {
                Helper.showMsg("fill");
            } else {
                boolean result ;

                this.hotel.setName(fld_hotelname.getText());
                this.hotel.setAddress(fld_hoteladres.getText());
                this.hotel.setPhone(fld_hoteltel.getText());
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
        }); // Değerlendirme 10
    }
}
