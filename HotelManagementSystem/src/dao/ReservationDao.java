package dao;

import core.Db;
import entity.*;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private final Connection conn;

    public ReservationDao() {
        this.conn = Db.getInstance();
    }

    //Tüm rezervasyonları veritabanından getirir ve bir ArrayList içinde döndürür.
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

    //Bir ResultSet içindeki satırları Reservation nesnelerine dönüştürmek için kullanılan metod.
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

    public boolean save(Reservation reservation) {  //Yeni bir rezervasyon kaydı oluşturur ve veritabanına ekler
        String query = "INSERT INTO public.reservation " +
                "(" +
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
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


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


            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Reservation reservation) {  //Varolan bir rezervasyon kaydını günceller.
        String query = "UPDATE public.reservation SET " +
                "room_id = ? ," +
                "check_in_date = ? , " +
                "check_out_date = ? , " +
                "total_price = ? , " +
                "guest_count = ? , " +
                "guest_name = ? , " +
                "guest_citizen_id = ? , " +
                "guest_mail = ? , " +
                "guest_phone = ? " +

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

    public boolean delete(int reservation_id) {   //Belirli bir rezervasyon kaydını veritabanından siler.
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

    public Reservation getById(int id) {  //Belirli bir rezervasyonun ID'sine göre rezervasyon bilgilerini getirir
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

    public ArrayList<Reservation> selectByQuery(String query) {  //Belirli bir sorguya göre rezervasyonları getirir.
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

    // Belirli bir otel ve tarih aralığı için sezon faktörünü hesaplar
    public double searchForSeasonFactor(int hotel_id, String checkinDate, String checkoutDate) {
        double seasonFactor = 0;
        String select = "SELECT DISTINCT season.season_factor\n" +
                "FROM room\n" +
                "JOIN season ON room.hotel_id = season.hotel_id\n" +
                "JOIN pension ON room.hotel_id = pension.hotel_id\n" +
                "JOIN hotel ON room.hotel_id = hotel.hotel_id\n";

        ArrayList<String> whereList = new ArrayList<>();
        if (hotel_id != 0) {
            whereList.add("room.hotel_id =" + hotel_id);
        }
        if (checkinDate != null && !checkinDate.isEmpty() && checkoutDate != null && !checkoutDate.isEmpty()) {
            whereList.add(("'" + checkinDate +"' >= season.start_date AND '"+ checkoutDate +"' <= season.finish_date"));
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()) {
            query += " WHERE " + whereStr;
        }

            try {
                Statement statement = this.conn.createStatement();
                ResultSet rs = statement.executeQuery(query);
                if (rs.next()) {
                    seasonFactor = rs.getDouble("season_factor");
                }
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return seasonFactor;
        }


   //Belirli bir otel ve sezon türü için sezon faktörünü hesaplar.
    public double searchForPensionFactor(int hotel_id,String pensionType) {
        double pensionFactor = 0;
        String select = "SELECT DISTINCT pension.pension_factor\n" +
                "FROM room\n" +
                "JOIN season ON room.hotel_id = season.hotel_id\n" +
                "JOIN pension ON room.hotel_id = pension.hotel_id\n" +
                "JOIN hotel ON room.hotel_id = hotel.hotel_id\n";

        ArrayList<String> whereList = new ArrayList<>();
        if (hotel_id != 0) {
            whereList.add("room.hotel_id = ?");
        }
        if (pensionType != null && !pensionType.isEmpty()) {
            whereList.add("pension.pension_type ILIKE ?");
        }


        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()) {
            query += " WHERE " + whereStr;
        }

        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            int parameterIndex = 1;
            if (hotel_id != 0) {
                pr.setInt(parameterIndex++, hotel_id);
            }
            if (pensionType != null && !pensionType.isEmpty()) {
                pr.setString(parameterIndex++, pensionType);
            }
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                pensionFactor = rs.getDouble("pension_factor");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensionFactor;
    }


    //Belirli odalara ait rezervasyon id'lerini getirir
    public List<Integer> getReservationIdsByRoomIds(List<Room> roomsToDelete) {
        List<Integer> reservationIds = new ArrayList<>();
        List<Integer> roomIds = new ArrayList<>();
        for (Room room : roomsToDelete) {
            roomIds.add(room.getRoom_id());
        }

        // Odalara ait rezervasyonları sorgula
        if (!roomIds.isEmpty()) { // Eğer odalar varsa sorguyu yap
            StringBuilder queryBuilder = new StringBuilder("SELECT reservation_id FROM reservation WHERE room_id IN (");
            for (int i = 0; i < roomIds.size(); i++) {
                queryBuilder.append("?");
                if (i < roomIds.size() - 1) {
                    queryBuilder.append(",");
                }
            }
            queryBuilder.append(")");

            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                statement = this.conn.prepareStatement(queryBuilder.toString());
                int parameterIndex = 1;
                for (Integer roomId : roomIds) {
                    statement.setInt(parameterIndex++, roomId);
                }

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    reservationIds.add(resultSet.getInt("reservation_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return reservationIds;
    }


    public List<Reservation> getReservationByRoomId(int roomId) {
        List<Reservation> reservations = new ArrayList<>();
        try {
            // Veritabanından otel id ye göre rezervasyonları sorgula
            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM reservation WHERE room_id = ?");
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            // Sorgu sonucunda dönen rezervasyonları listeye ekle
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservation_id(resultSet.getInt("reservation_id"));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda boş bir liste döndür
            return reservations;
        }
        // Rezervasyonları içeren listeyi döndür
        return reservations;

    }

}
