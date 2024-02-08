package dao;

import core.Db;
import entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {
    private final Connection conn;

    public RoomDao() {
        this.conn = Db.getInstance();
    }

    //Tüm odaları veritabanından getirir ve bir ArrayList içinde döndürür
    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room ORDER BY room_id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    // Bir ResultSet içindeki satırları Room nesnelerine dönüştürmek için kullanılan yardımcı bir metoddur.
    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setHotel_name(rs.getString("hotel_name"));
        obj.setPension_type(rs.getString("pension_type"));
        obj.setRoom_type(rs.getString("room_type"));
        obj.setStock(rs.getInt("stock"));
        obj.setAdult_price(rs.getDouble("adult_price"));
        obj.setChild_price(rs.getDouble("child_price"));
        obj.setBed_capacity(rs.getInt("bed_capacity"));
        obj.setMkare(rs.getString("square_meter"));
        obj.setTv(rs.getBoolean("tv"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setKonsol(rs.getBoolean("konsol"));
        obj.setKasa(rs.getBoolean("kasa"));
        obj.setProjeksiyon(rs.getBoolean("projeksiyon"));
        obj.setHotel_id(rs.getInt("hotel_id"));


        return obj;

    }

    //Yeni bir oda kaydı oluşturur ve veritabanına ekler.
    public boolean save(Room room) {
        String query = "INSERT INTO public.room " +
                "(" +
                "pension_type," +
                "hotel_name," +
                "room_type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square_meter," +
                "tv," +
                "minibar," +
                "konsol," +
                "kasa," +
                "projeksiyon," +
                "hotel_id" +
                ")" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, room.getPension_type());
            pr.setString(2,room.getHotel_name());
            pr.setString(3, room.getRoom_type());
            pr.setInt(4, room.getStock());
            pr.setDouble(5, room.getAdult_price());
            pr.setDouble(6, room.getChild_price());
            pr.setInt(7, room.getBed_capacity());
            pr.setString(8, room.getMkare());
            pr.setBoolean(9, room.isTv());
            pr.setBoolean(10, room.isMinibar());
            pr.setBoolean(11, room.isKonsol());
            pr.setBoolean(12, room.isKasa());
            pr.setBoolean(13, room.isProjeksiyon());
            pr.setInt(14, room.getHotel_id());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    //Varolan bir oda kaydını günceller
    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "pension_type = ? , " +
                "hotel_name = ? , " +
                "room_type = ? , " +
                "stock = ? , " +
                "adult_price = ? , " +
                "child_price = ? , " +
                "bed_capacity = ? , " +
                "square_meter = ? , " +
                "tv = ? , " +
                "minibar = ? , " +
                "konsol = ? , " +
                "kasa = ? , " +
                "projeksiyon = ?,  " +
                "hotel_id = ?  " +
                "WHERE room_id = ?";


        try {

            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, room.getPension_type());
            pr.setString(2, room.getHotel_name());
            pr.setString(3, room.getRoom_type());
            pr.setInt(4, room.getStock());
            pr.setDouble(5, room.getAdult_price());
            pr.setDouble(6, room.getChild_price());
            pr.setInt(7, room.getBed_capacity());
            pr.setString(8, room.getMkare());
            pr.setBoolean(9, room.isTv());
            pr.setBoolean(10, room.isMinibar());
            pr.setBoolean(11, room.isKonsol());
            pr.setBoolean(12, room.isKasa());
            pr.setBoolean(13, room.isProjeksiyon());
            pr.setInt(14, room.getHotel_id());
            pr.setInt(15, room.getRoom_id());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int room_id) {  //Belirli bir oda kaydını veritabanından siler.
        String query = "DELETE FROM public.room WHERE room_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, room_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Room getById(int id) {  //Belirli bir oda ID'sine göre oda bilgilerini getirir.
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
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

    public ArrayList<Room> selectByQuery(String query) {  // Belirli bir sorguya göre odaları getirir.
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public void updateStock(int roomId, int roomCount) {
        String updateQuery = "UPDATE public.room SET stock = stock + ? WHERE room_id = ?";

        try {
            // Stok azaltma işlemi yapar
            PreparedStatement updateStatement = this.conn.prepareStatement(updateQuery);
            updateStatement.setInt(1, roomCount);
            updateStatement.setInt(2, roomId);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        List<Room> rooms = new ArrayList<>();
        try {
            // Veritabanından otel id ye göre odaları sorgular
            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM room WHERE hotel_id = ?");
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            // Sorgu sonucunda dönen odaları listeye ekler
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoom_id(resultSet.getInt("room_id"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda boş bir liste döndürür
            return rooms;
        }
        // Odaları içeren listeyi döndürür
        return rooms;

    }
}

