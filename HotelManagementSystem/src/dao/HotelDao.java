package dao;

import core.Db;
import entity.Hotel;
import entity.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao {
    private final Connection conn;

    public HotelDao() {
        this.conn = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM public.hotel ORDER BY hotel_id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setAddress(rs.getString("hotel_address"));
        obj.setMail(rs.getString("hotel_mail"));
        obj.setPhone(rs.getString("hotel_phone"));
        obj.setStar(rs.getString("hotel_star"));
        obj.setCarPark(rs.getBoolean("hotel_carpark"));
        obj.setWifi(rs.getBoolean("hotel_wifi"));
        obj.setPool(rs.getBoolean("hotel_pool"));
        obj.setFitness(rs.getBoolean("hotel_fitness"));
        obj.setConcierge(rs.getBoolean("hotel_concierge"));
        obj.setSpa(rs.getBoolean("hotel_spa"));
        obj.setRoomService(rs.getBoolean("hotel_roomservice"));

        return obj;

    }

    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "hotel_name," +
                "hotel_address," +
                "hotel_mail," +
                "hotel_phone," +
                "hotel_star," +
                "hotel_carpark," +
                "hotel_wifi," +
                "hotel_pool," +
                "hotel_fitness," +
                "hotel_concierge," +
                "hotel_spa," +
                "hotel_roomservice" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isCarPark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET " +
                "hotel_name = ? ," +
                "hotel_address = ? , " +
                "hotel_mail = ? , " +
                "hotel_phone = ? , " +
                "hotel_star= ? , " +
                "hotel_carpark = ? , " +
                "hotel_wifi = ? , " +
                "hotel_pool = ? , " +
                "hotel_fitness = ? , " +
                "hotel_concierge = ? , " +
                "hotel_spa = ? , " +
                "hotel_roomservice = ?  " +
                "WHERE hotel_id = ?";


        try {

            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getMail());
            pr.setString(4, hotel.getPhone());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isCarPark());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoomService());

            pr.setInt(13, hotel.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int hotel_id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, hotel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
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
    public String getByName(int id) {
        String hotelName = null;
        String query = "SELECT hotel_name FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                hotelName = rs.getString("hotel_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelName;
    }

    public int getByHotelId(String hotelName) {
        int hotelId = 0;
        String query = "SELECT hotel_id FROM public.hotel WHERE hotel_name = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, hotelName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                hotelId = rs.getInt("hotel_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelId;
    }

    public List<String> getTumOtelIsimleri() {
        List<String> otelIsimleri = new ArrayList<>();
        String query = "SELECT hotel_name FROM public.hotel";  // Uyarlanmış sorgu

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                otelIsimleri.add(rs.getString("hotel_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otelIsimleri;
    }


}
