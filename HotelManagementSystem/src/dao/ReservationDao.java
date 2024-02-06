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
            whereList.add(("'" + checkinDate +"' >= season.baslangic AND '"+ checkoutDate +"' <= season.bitis"));
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

}
