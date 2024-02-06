package dao;

import core.Db;
import entity.Pension;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PensionDao {
    private final Connection conn;

    public PensionDao() {
        this.conn = Db.getInstance();
    }

    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pension ORDER BY pension_id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setPensionId(rs.getInt("pension_id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPensionType(rs.getString("pension_type"));
        obj.setPensionFactor(rs.getDouble("pension_factor"));


        return obj;

    }

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension " +
                "(" +
                "hotel_id," +
                "pension_type," +
                "pension_factor" +
                ")" +
                " VALUES (?, ?, ?)";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());
            pr.setString(2, pension.getPensionType());
            pr.setDouble(3, pension.getPensionFactor());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Pension pension) {
        String query = "UPDATE public.pension SET " +
                "hotel_id = ? ," +
                "pension_type = ? , " +
                "pension_factor = ? " +
                "WHERE pension_id = ?";


        try {

            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());
            pr.setString(2, pension.getPensionType());
            pr.setDouble(3, pension.getPensionFactor());

            pr.setInt(4, pension.getPensionId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int pension_id) {
        String query = "DELETE FROM public.pension WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, pension_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
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

    public ArrayList<String> getOteleAitPensionlar(int hotelId) {
        ArrayList<String> pensionlar = new ArrayList<>();
        String query = "SELECT pension_type FROM public.pension WHERE hotel_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pensionlar.add(rs.getString("pension_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return pensionlar;
    }

}

