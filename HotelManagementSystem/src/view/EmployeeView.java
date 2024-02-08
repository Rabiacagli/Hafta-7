package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel lbl_city;
    private JLabel lbl_check_in;
    private JLabel lbl_check_out;
    private JLabel lbl_adultcount;
    private JLabel lbl_childcount;
    private JScrollPane scrl_rez;
    private JTable tbl_rez;
    private JButton editButton;
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

        this.lbl_welcome.setText("Welcome " + this.user.getUsername());

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


            tableSelectedRow(this.tbl_room);
            editButton.addActionListener(e -> {
                try {
                int selectRoomId = this.getTableSelectedRow(this.tbl_room, 0);


                RoomView roomView = new RoomView(roomManager.getById(selectRoomId));

                roomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadReservationTable(null);
                    }
                });
                } catch (Exception a) {
                    Helper.showMsg("Please choose room!");
                }
            });

        //Rezervasyon

        loadReservationTable(null);
        loadRezComponent();

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }); // Çıkış
        btn_search_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkinStr = fld_room_checkin.getText();
                String checkoutStr = fld_room_checkout.getText();

                // Tarihlerin doğru formatta olup olmadığını kontrol eder
                if (checkinStr.matches("\\d{4}-\\d{2}-\\d{2}") && checkoutStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    LocalDate checkin = LocalDate.parse(checkinStr);
                    LocalDate checkout = LocalDate.parse(checkoutStr);

                    // Check-in tarihi, check-out tarihinden önce olmalı veya aynı gün olmamalı
                    if (checkin.isBefore(checkout) && !checkinStr.equals(checkoutStr)) {
                        loadSearchTable(); // Geçerli tarihler, tabloyu yükler
                    } else {
                        Helper.showMsg("Please check check-in date and check-out date !");
                    }
                } else {
                    Helper.showMsg("Please enter a valid date format (yyyy-MM-dd)!");
                }
            }
        });


        btn_reset.addActionListener(new ActionListener() { //Oda Listesi Sıfırlama
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRoomTable(null);
            }
        });

    }   // Personel Ekranı

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Hotel's Name", "Address", "Mail", "Phone", "Star", "Parking", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Service"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }  // Otel Tablosu

    public void loadHotelComponent() {
        tableSelectedRow(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("New").addActionListener(e -> {

            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });
        });
        this.hotel_menu.add("Update").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                    loadPensionTable();
                    loadSeasonTable();
                    loadRoomTable(null);
                    loadReservationTable(null);

                }
            });
        });

        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("All hotel data will be deleted .\nAre you sure?")) {
                int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
                if (this.hotelManager.delete(selectHotelId)) {
                    // Odaları silme işlemi
                    List<Room> roomsToDelete = this.roomManager.getRoomsByHotelId(selectHotelId);
                    boolean allRoomsDeleted = true;
                    for (Room room : roomsToDelete) {
                        if (!this.roomManager.delete(room.getRoom_id())) {
                            allRoomsDeleted = false;
                            break;
                        }
                    }
                    // Rezervasyonları silme işlemi
                    List<Integer> reservationToDelete = this.reservationManager.getReservationIdsByRoomIds(roomsToDelete);
                    boolean allReservationsDeleted = true;
                    for (int reservationId : reservationToDelete) {
                        if (!this.reservationManager.delete(reservationId)) {
                            allReservationsDeleted = false;
                            break;
                        }
                    }

                    // Sezonları silme işlemi
                    List<Season> seasonsToDelete = this.seasonManager.getSeasonsByHotelId(selectHotelId);
                    boolean allSeasonsDeleted = true;
                    for (Season season : seasonsToDelete) {
                        if (!this.seasonManager.delete(season.getSeasonId())) {
                            allSeasonsDeleted = false;
                            break;
                        }
                    }
                    // Pansiyonları silme işlemi
                    List<Pension> pensionsToDelete = this.pensionManager.getPensionsByHotelId(selectHotelId);
                    boolean allPensionsDeleted = true;
                    for (Pension pension : pensionsToDelete) {
                        if (!this.pensionManager.delete(pension.getPensionId())) {
                            allPensionsDeleted = false;
                            break;
                        }
                    }
                    // Silme işlemlerinin sonucunda
                    if (allRoomsDeleted && allSeasonsDeleted && allPensionsDeleted) {
                        Helper.showMsg("Hotel and related data deleted.");
                        loadHotelTable();
                        loadRoomTable(null);
                        loadSeasonTable();
                        loadPensionTable();
                        loadReservationTable(null);
                        // Diğer tabloları güncelleme
                    } else {
                        Helper.showMsg("Hotel was deleted but some related data could not be deleted.");
                    }
                } else {
                    Helper.showMsg("Hotel could not be deleted.");
                }
            }
        });

        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

    } // Otel Tablo Menüsü

    public void loadSeasonTable() {

        Object[] col_season = {"ID","Hotel ID","Hotel's Name", "Start Date", "Finish Date", "Season Factor"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);

    }  // Sezon Tablosu

    public void loadSeasonComponent() {
        tableSelectedRow(this.tbl_season);

        this.season_menu = new JPopupMenu();
        this.season_menu.add("New").addActionListener(e -> {

            SeasonView seasonView = new SeasonView(new Season());
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();

                }
            });
        });
        this.season_menu.add("Update").addActionListener(e -> {
            int selectSeasonId = this.getTableSelectedRow(this.tbl_season, 0);
            SeasonView seasonView = new SeasonView(this.seasonManager.getById(selectSeasonId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();

                }
            });
        });

        this.season_menu.add("Delete").addActionListener(e -> {
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

    }   // Sezon Tablo Menüsü

    public void loadPensionTable() {

        Object[] col_pension = {"ID","Hotel id","Hotel's Name", "Pension Type", "Pension Factor"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        this.createTable(this.tmdl_pension, this.tbl_pension, col_pension, pensionList);

    }   // Pansiyon Tablosu

    public void loadPensionComponent() {
        tableSelectedRow(this.tbl_pension);

        this.pension_menu = new JPopupMenu();
        this.pension_menu.add("New").addActionListener(e -> {

            PensionView pensionView = new PensionView(new Pension());
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();

                }
            });
        });
        this.pension_menu.add("Update").addActionListener(e -> {
            int selectPensionId = this.getTableSelectedRow(this.tbl_pension, 0);
            PensionView pensionView = new PensionView(this.pensionManager.getById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                    loadRoomTable(null);
                    loadReservationTable(null);

                }
            });
        });

        this.pension_menu.add("Delete").addActionListener(e -> {
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

    }    // Pansiyon Tablo Menüsü

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID","City", "Hotel's Name", "Pension Type", "Room Type", "Stock", "Adult's Price", "Child's Price", "Number of bed", "Square Meters", "TV", "Minibar", "Console", "Safe-Box", "Projection", "Hotel id"};
        // Stok kontrollü tablo oluşturma
        if (roomList == null || roomList.isEmpty()) {
            roomList = new ArrayList<>();
            this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
        } else {
            ArrayList<Object[]> filteredRoomList = new ArrayList<>();
            for (Object[] room : roomList) {
                int stock = (int) room[5];
                if (stock > 0) {
                    filteredRoomList.add(room);
                }
            }
            this.createTable(this.tmdl_room, this.tbl_room, col_room, filteredRoomList);
        }
    }  // Stok kontrollü Oda Tablosu

    public void loadRoomComponent() {

        tableSelectedRow(this.tbl_room);

        this.rez_menu = new JPopupMenu();
        this.rez_menu.add("Create new reservation").addActionListener(e -> {
            if (!fld_room_checkin.getText().isEmpty() && !fld_room_checkout.getText().isEmpty() && !fld_room_adult_count.getText().isEmpty() && !fld_room_child_count.getText().isEmpty()) {

                int selectrezId = this.getTableSelectedRow(this.tbl_room, 0);
                String checkIn = fld_room_checkin.getText();
                String checkOut = fld_room_checkout.getText();
                int yetişkinSayisi = Integer.parseInt(fld_room_adult_count.getText());
                int cocukSayisi = Integer.parseInt(fld_room_child_count.getText());

                ReservationView reservationView = new ReservationView(new Reservation(0), this.roomManager.getById(selectrezId), checkIn, checkOut, yetişkinSayisi, cocukSayisi);
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable(null);
                        loadRoomTable(null);

                    }
                });
            } else {
                Helper.showMsg("Please fill in the dates and number of guests!");
            }
        });
        this.rez_menu.add("Delete room").addActionListener(e -> {
            if (Helper.confirm("All data related the room will be deleted .\nAre you sure?")) {
                int selectRoomId = this.getTableSelectedRow(this.tbl_room, 0);
                if (this.roomManager.delete(selectRoomId)) {
                    // Rezervasyonları silme işlemi
                    List<Reservation> reservationsToDelete = this.reservationManager.getReservationByRoomId(selectRoomId);
                    boolean allReservationsDeleted = true;
                    for (Reservation reservation : reservationsToDelete) {
                        if (!this.reservationManager.delete(reservation.getReservation_id())) {
                            allReservationsDeleted = false;
                            break;
                        }
                    }

                    // Silme işlemlerinin sonucunda
                    if (allReservationsDeleted) {
                        Helper.showMsg("Room and reservations deleted");
                        loadRoomTable(null);
                        loadReservationTable(null);
                        // Diğer tabloları güncelleme
                    } else {
                        Helper.showMsg("Room was deleted but related data could not be deleted.");
                    }
                } else {
                    Helper.showMsg("Room could not be deleted.");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(rez_menu);
    }  // Oda Tablo Menüsü

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
    }   // Oda Arama // Değerlendirme 15 - 16 - 17 Fiyat Hesaplama Helper sınıfında yapıldı

    public void loadReservationTable(ArrayList<Object[]> reservationList) {

        this.col_reservation = new Object[]{"ID", "Room ID", "Hotel's Name", "Pension Type","Address", "Check-in Date", "Check-out Date", "Total Balance", "Number of Guest", "Guest Name", "Guest ID", "Guest Mail", "Guest Phone Number"};
        if (reservationList == null) {

            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }
        this.createTable(this.tmdl_reservation, this.tbl_rez, col_reservation, reservationList);
    }  // Rezervasyon Tablosu Değerlendirme 20

    public void loadRezComponent() {
        tableSelectedRow(this.tbl_rez);

        this.rez_menu2 = new JPopupMenu();
        this.rez_menu2.add("Update reservation").addActionListener(e -> {
            int selectrezId = this.getTableSelectedRow(this.tbl_rez, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectrezId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);
                    loadRoomTable(null);

                }
            });
        });

        this.rez_menu2.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectrezId = this.getTableSelectedRow(this.tbl_rez, 0);
                int room_id = this.reservationManager.getById(selectrezId).getRoom_id();
                if (this.reservationManager.delete(selectrezId)) {
                    Helper.showMsg("done");
                    roomManager.updateStock(room_id, 1);
                    loadReservationTable(null);
                    loadRoomTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_rez.setComponentPopupMenu(rez_menu2);

    } // Rezervasyon Tablo Menüsü
}



