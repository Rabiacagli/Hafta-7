package dao;

import core.Db;
import entity.Pension;
import entity.Room;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PensionDao {
    private final Connection conn;

    public PensionDao() {
        this.conn = Db.getInstance();
    }

    public ArrayList<Pension> findAll() {   // Pansiyon bilgilerini veritabanından çeker ve bir liste olarak döndürür.
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

    //Bir ResultSet nesnesinden veritabanından alınan pansiyon bilgilerini kullanarak bir pansiyon nesnesi oluşturmak ve döndürmektir.
    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setPensionId(rs.getInt("pension_id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setPensionType(rs.getString("pension_type"));
        obj.setPensionFactor(rs.getDouble("pension_factor"));


        return obj;

    }

    public boolean save(Pension pension) {   // yeni bir pansiyon kaydeder
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

    public boolean update(Pension pension) {  // Var olan pansiyonu günceller
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

    public boolean delete(int pension_id) {  // pansiyonu siler
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

    public Pension getById(int id) { // pansiyon id2sine göre pansiyon tablosunu döndürür
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

    public ArrayList<String> getHotelsPensions(int hotelId) { // veritabanında belirli bir otel id2siyle ilişkili pansiyonları listeler
        ArrayList<String> pensions = new ArrayList<>();
        String query = "SELECT pension_type FROM public.pension WHERE hotel_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, hotelId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pensions.add(rs.getString("pension_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return pensions;
    }

    public List<Pension> getPensionsByHotelId(int hotelId) {
        List<Pension> pensionss = new ArrayList<>();
        try {
            // Veritabanından otel id ye göre pansiyonları sorgular
            PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM pension WHERE hotel_id = ?");
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            // Sorgu sonucunda dönen pansiyonları listeye ekler
            while (resultSet.next()) {
                Pension pension = new Pension();
                pension.setPensionId(resultSet.getInt("pension_id"));
                pensionss.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda boş bir liste döndür
            return pensionss;
        }
        // Odaları içeren listeyi döndür
        return pensionss;

    }

}

