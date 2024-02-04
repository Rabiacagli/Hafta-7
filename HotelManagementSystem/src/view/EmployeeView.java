package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout {

    private JPanel contanier;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JTabbedPane pnl_main;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_hotel;
    private JButton btn_addhotel;
    private JPanel pnl_room;
    private JPanel pnl_rez;
    private JPanel pnl_bottom;
    private JScrollPane scl_right;
    private JTable tbl_season;
    private JScrollPane scl_left;
    private JTable tbl_pension;
    private JLabel lbl_sezonlar;
    private JLabel lbl_pansiyonlar;
    private JScrollPane scl_room;
    private JTable tbl_room;
    private JButton btn_add_room;
    private JButton btn_search_room;
    private JButton btn_reset;
    private JTextField fld_room_hotel_name;
    private JTextField fld_room_city;
    private JTextField fld_room_checkin;
    private JTextField fld_room_checkout;
    private JTextField fld_room_adult_count;
    private JTextField fld_room_child_count;
    private JLabel lbl_hotelname;
    private JLabel lbl_sehir;
    private JLabel lbl_giris;
    private JLabel lbl_cikis;
    private JLabel lbl_adultcount;
    private JLabel lbl_childcount;
    private JScrollPane scrl_rez;
    private JTable tbl_rez;
    private User user;
    private Room room;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_pension = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private JPopupMenu hotel_menu;
    private JPopupMenu season_menu;
    private JPopupMenu pension_menu;
    private JPopupMenu room_menu;
    private JPopupMenu rez_menu;
    private JPopupMenu rez_menu2;
    private Object[] col_season;
    private Object[] col_pension;
    private Object[] col_room;
    private Object[] col_reservation;

    public EmployeeView(User user) {
        this.seasonManager = new SeasonManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.add(contanier);
        this.guiInitiliaze(1500, 800);
        this.user = user;

        this.lbl_welcome.setText("Hoşgeldiniz " + this.user.getUsername());

        //Hotel
        loadHotelTable();
        loadHotelComponent();
        btn_addhotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelView hotelView = new HotelView(new Hotel());
                hotelView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadHotelTable();

                    }
                });

            }
        }); //Otel Ekleme butonu

        //Season
        loadSeasonTable();
        loadSeasonComponent();

        //Pension
        loadPensionTable();
        loadPensionComponent();

        //Room
        loadRoomTable(null);
        loadRoomComponent();
        btn_add_room.addActionListener(e -> {

            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);

                }
            });
        });


        //Rez

        loadReservationTable(null);
        loadRezComponent();


        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }); // Çıkış
        btn_search_room.addActionListener(new ActionListener() {  // oda arama
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSearchTable();

            }
        });
        btn_reset.addActionListener(new ActionListener() { //Oda Listesi Sıfırlama
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRoomTable(null);
            }
        });
    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Otel Adı", "Adres", "Mail", "Telefon", "Yıldız", "Otopark", "Wifi", "Havuz", "Fitness", "Concierge", "Spa", "Oda Servisi"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadHotelComponent() {
        tableSelectedRow(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Yeni").addActionListener(e -> {

            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });
        });
        this.hotel_menu.add("Güncelle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });
        });

        this.hotel_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
                if (this.hotelManager.delete(selectHotelId)) {
                    Helper.showMsg("done");
                    loadHotelTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    }

    public void loadSeasonTable() {

        Object[] col_season = {"ID", "Otel ID", "Başlangıç Tarihi", "Bitiş Tarihi"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);

    }

    public void loadSeasonComponent() {
        tableSelectedRow(this.tbl_season);

        this.season_menu = new JPopupMenu();
        this.season_menu.add("Yeni").addActionListener(e -> {

            SeasonView seasonView = new SeasonView(new Season());
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();

                }
            });
        });
        this.season_menu.add("Güncelle").addActionListener(e -> {
            int selectSeasonId = this.getTableSelectedRow(this.tbl_season, 0);
            SeasonView seasonView = new SeasonView(this.seasonManager.getById(selectSeasonId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();

                }
            });
        });

        this.season_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(this.tbl_season, 0);
                if (this.seasonManager.delete(selectSeasonId)) {
                    Helper.showMsg("done");
                    loadSeasonTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_season.setComponentPopupMenu(season_menu);

    }

    public void loadPensionTable() {

        Object[] col_pension = {"ID", "Otel ID", "Pansiyon Tipi"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);

    }

    public void loadPensionComponent() {
        tableSelectedRow(this.tbl_pension);

        this.pension_menu = new JPopupMenu();
        this.pension_menu.add("Yeni").addActionListener(e -> {

            PensionView pensionView = new PensionView(new Pension());
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();

                }
            });
        });
        this.pension_menu.add("Güncelle").addActionListener(e -> {
            int selectPensionId = this.getTableSelectedRow(this.tbl_pension, 0);
            PensionView pensionView = new PensionView(this.pensionManager.getById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();

                }
            });
        });

        this.pension_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectPensionId = this.getTableSelectedRow(this.tbl_pension, 0);
                if (this.pensionManager.delete(selectPensionId)) {
                    Helper.showMsg("done");
                    loadPensionTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_pension.setComponentPopupMenu(pension_menu);

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {

        this.col_room = new Object[]{"ID", "Otel Adı", "Pansiyon Tipi", "Oda Tipi", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı", "Yatak Sayısı", "Metrekare", "TV", "Minibar", "Konsol", "Kasa", "Projeksiyon", "Otel ID"};
        if (roomList == null) {

            roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }
    public void loadSearchTable() {
        ArrayList<Room> roomListBySearch = this.roomManager.searchForTable(
                fld_room_hotel_name.getText(),
                fld_room_city.getText(),
                fld_room_checkin.getText(),
                fld_room_checkout.getText(),
                fld_room_adult_count.getText(),
                fld_room_child_count.getText()
        );

        ArrayList<Object[]> roomRowListBySearch = this.roomManager.getForTable(this.col_room.length, roomListBySearch);
        loadRoomTable(roomRowListBySearch);
    }
    public void loadReservationTable(ArrayList<Object[]> reservationList) {

        this.col_reservation = new Object[]{"ID", "Oda ID", "Giriş Tarihi", "Çıkış Tarihi", "Toplam Tutar", "Misafir Sayısı", "Misafir Adı", "Misafir ID", "Misafir Mail", "Misafir Telefon No"};
        if (reservationList == null) {

            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }
        this.createTable(this.tmdl_reservation, this.tbl_rez, col_reservation, reservationList);
    }

    public void loadRoomComponent() {
        tableSelectedRow(this.tbl_room);

        this.rez_menu = new JPopupMenu();
        this.rez_menu.add("Yeni Rezervasyon Yap").addActionListener(e -> {
            int selectrezId = this.getTableSelectedRow(this.tbl_room, 0);
            ReservationView reservationView = new ReservationView(this.roomManager.getById(selectrezId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);

                }
            });
        });
        this.tbl_room.setComponentPopupMenu(rez_menu);
    }

    public void loadRezComponent() {
        tableSelectedRow(this.tbl_rez);

        this.rez_menu2 = new JPopupMenu();
        this.rez_menu2.add("Güncelle").addActionListener(e -> {
            int selectrezId = this.getTableSelectedRow(this.tbl_rez, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectrezId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                   loadReservationTable(null);

                }
            });
        });

        this.rez_menu2.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectrezId = this.getTableSelectedRow(this.tbl_rez, 0);
                if (this.reservationManager.delete(selectrezId)) {
                    Helper.showMsg("done");
                    loadReservationTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_rez.setComponentPopupMenu(rez_menu2);

    }
}



