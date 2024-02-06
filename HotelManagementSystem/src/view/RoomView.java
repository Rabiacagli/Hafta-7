package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import dao.HotelDao;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RoomView extends Layout {
    private JPanel contanier;
    private JTextField fld_hotel_name;
    private JTextField fld_pension_type;
    private JTextField fld_stock;
    private JRadioButton btn_tv;
    private JRadioButton btn_minibar;
    private JRadioButton btn_konsol;
    private JRadioButton btn_kasa;
    private JRadioButton btn_projeksiyon;
    private JButton btn_save;
    private JTextField fld_adult_price;
    private JTextField fld_child_price;
    private JTextField fld_bed_capacity;
    private JLabel lbl_header;
    private JPanel pnl_left;
    private JLabel lbl_hotel_name;
    private JLabel lbl_pension_type;
    private JLabel lbl_room_type;
    private JLabel lbl_stock;
    private JLabel lbl_adult_price;
    private JLabel lbl_child_price;
    private JLabel lbl_bed_capacity;
    private JTextField fld_metrekare;
    private JLabel lbl_square;
    private JComboBox cmb_room_type;
    private JComboBox cmb_hotel_name;
    private JComboBox cmb_hotel_pension;
    private JTextField fld_otel_id;
    private Room room;
    private RoomManager roomManager;
    private Hotel hotel;
    private HotelManager hotelManager;
    private Pension pension;
    private PensionManager pensionManager;
    private Season season;
    private SeasonManager seasonManager;


    public RoomView(Room room) {
        this.room = room;
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.pension = pension;
        this.pensionManager = new PensionManager();
        this.season = season;
        this.seasonManager = new SeasonManager();
        this.room = room;
        this.roomManager = new RoomManager();
        this.add(contanier);
        this.guiInitiliaze(500, 500);




        List<String> otelIsimleri = hotelManager.getTumOtelIsimleri();
        cmb_hotel_name.setModel(new DefaultComboBoxModel<>(otelIsimleri.toArray(new String[0])));


        cmb_hotel_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String secilenOtelAdi = (String) cmb_hotel_name.getSelectedItem();
                int hotelId = hotelManager.getByHotelId(secilenOtelAdi);

                // Seçilen otel için ait olan pensionları cmb_hotel_pension'a ekle
                ArrayList<String> pensionlar = pensionManager.getOteleAitPensionlar(hotelId);
                cmb_hotel_pension.setModel(new DefaultComboBoxModel<>(pensionlar.toArray(new String[0])));
            }
        });

        if (this.room.getRoom_id() != 0){

            this.cmb_hotel_name.setSelectedItem(this.room.getHotel_name());
            this.cmb_hotel_pension.setSelectedItem(this.room.getPension_type());
            this.cmb_room_type.setSelectedItem(this.room.getRoom_type());
            this.fld_stock.setText(String.valueOf(this.room.getStock()));
            this.fld_adult_price.setText(String.valueOf(this.room.getAdult_price()));
            this.fld_child_price.setText(String.valueOf(this.room.getChild_price()));
            this.fld_bed_capacity.setText(String.valueOf(this.room.getBed_capacity()));
            this.fld_metrekare.setText(this.room.getMkare());
            this.btn_kasa.setSelected(this.room.isKasa());
            this.btn_konsol.setSelected(this.room.isKonsol());
            this.btn_tv.setSelected(this.room.isTv());
            this.btn_minibar.setSelected(this.room.isMinibar());
            this.btn_projeksiyon.setSelected(this.room.isProjeksiyon());
        }


        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_adult_price, this.fld_child_price, this.fld_bed_capacity, this.fld_metrekare})) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                int hotelId = hotelManager.getByHotelId(String.valueOf(cmb_hotel_name.getSelectedItem()));
                this.room.setHotel_id(hotelId);
                this.room.setHotel_name(String.valueOf(cmb_hotel_name.getSelectedItem()));
                this.room.setPension_type(String.valueOf(cmb_hotel_pension.getSelectedItem()));
                this.room.setRoom_type((String) cmb_room_type.getSelectedItem());
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setAdult_price(Double.parseDouble(fld_adult_price.getText()));
                this.room.setChild_price(Double.parseDouble(fld_child_price.getText()));
                this.room.setBed_capacity(Integer.parseInt(fld_bed_capacity.getText()));
                this.room.setMkare(fld_metrekare.getText());
                this.room.setTv(btn_tv.isSelected());
                this.room.setMinibar(btn_minibar.isSelected());
                this.room.setKonsol(btn_konsol.isSelected());
                this.room.setKasa(btn_kasa.isSelected());
                this.room.setProjeksiyon(btn_projeksiyon.isSelected());



                if (this.room.getRoom_id() != 0) {
                    result = this.roomManager.update(this.room);

                } else {
                    result = this.roomManager.save(this.room);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });   // Değerlendirme 13 - 14
    }
}
