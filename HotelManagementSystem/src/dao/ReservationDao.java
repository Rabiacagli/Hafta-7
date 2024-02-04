package dao;

import core.Db;
import entity.Hotel;
import entity.Reservation;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {

    private final Connection conn;

    public ReservationDao() {
        this.conn = Db.getInstance();
    }

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> resList = new ArrayList<>();
        String query = "SELECT * FROM public.reservation ORDER BY reservation_id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                resList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setReservation_id(rs.getInt("reservation_id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setCheckinDate(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheckoutDate(LocalDate.parse(rs.getString("check_out_date")));
        obj.setTotal_price(rs.getInt("total_price"));
        obj.setGuestCount(rs.getInt("guest_count"));
        obj.setGuestName(rs.getString("guest_name"));
        obj.setGuestId(rs.getString("guest_citizen_id"));
        obj.setGuestMail(rs.getString("guest_mail"));
        obj.setGuestPhone(rs.getString("guest_phone"));

        return obj;
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation " +
                "(" +
                "reservation_id," +
                "room_id," +
                "check_in_date," +
                "check_out_date," +
                "total_price," +
                "guest_count," +
                "guest_name," +
                "guest_citizen_id," +
                "guest_mail," +
                "guest_phone" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, reservation.getReservation_id());
            pr.setInt(2, reservation.getRoom_id());
            pr.setDate(3, Date.valueOf(reservation.getCheckinDate()));
            pr.setDate(4, Date.valueOf(reservation.getCheckoutDate()));
            pr.setDouble(5, reservation.getTotal_price());
            pr.setInt(6, reservation.getGuestCount());
            pr.setString(7, reservation.getGuestName());
            pr.setString(8, reservation.getGuestId());
            pr.setString(9, reservation.getGuestMail());
            pr.setString(10, reservation.getGuestPhone());


            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "room_id = ? ," +
                "check_in_date = ? , " +
                "check_out_date = ? , " +
                "stock = ? , " +
                "total_price = ? , " +
                "guest_count = ? , " +
                "guest_citizen_id = ? , " +
                "guest_mail = ? , " +
                "guest_phone = ? , " +

                "WHERE reservation_id = ?";


        try {

            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, reservation.getRoom_id());
            pr.setDate(2, Date.valueOf(reservation.getCheckinDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckoutDate()));
            pr.setDouble(4, reservation.getTotal_price());
            pr.setInt(5, reservation.getGuestCount());
            pr.setString(6, reservation.getGuestName());
            pr.setString(7, reservation.getGuestId());
            pr.setString(8, reservation.getGuestMail());
            pr.setString(9, reservation.getGuestPhone());

            pr.setInt(10, reservation.getReservation_id());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int reservation_id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, reservation_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> resList = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                resList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }

    public ArrayList<String> getHotelDetails(String hotelName) {
        ArrayList<String> hotelDetails = new ArrayList<>();
        String query = "SELECT hotel_id, hotel_name, hotel_address, hotel_star, hotel_carpark, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_roomservice FROM public.hotel WHERE hotel_name = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, hotelName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                hotelDetails.add("Hotel ID: " + rs.getInt("hotel_id"));
                hotelDetails.add("Hotel Name: " + rs.getString("hotel_name"));
                hotelDetails.add("Hotel Address: " + rs.getString("hotel_address"));
                hotelDetails.add("Hotel Star: " + rs.getInt("hotel_star"));
                hotelDetails.add("Hotel Carpark: " + rs.getBoolean("hotel_carpark"));
                hotelDetails.add("Hotel Wifi: " + rs.getBoolean("hotel_wifi"));
                hotelDetails.add("Hotel Pool: " + rs.getBoolean("hotel_pool"));
                hotelDetails.add("Hotel Fitness: " + rs.getBoolean("hotel_fitness"));
                hotelDetails.add("Hotel Concierge: " + rs.getBoolean("hotel_concierge"));
                hotelDetails.add("Hotel Spa: " + rs.getBoolean("hotel_spa"));
                hotelDetails.add("Hotel Room Service: " + rs.getBoolean("hotel_roomservice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelDetails;
    }
}
