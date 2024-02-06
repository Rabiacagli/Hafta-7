package dao;

import core.Db;
import entity.Car;
import entity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDao {
    private final Connection conn;
    private final BrandDao brandDao;
    private final ModelDao modelDao;

    public CarDao() {
        this.conn = Db.getInstance();
        this.brandDao = new BrandDao();
        this.modelDao = new ModelDao();
    }


    public Car getById(int id) {
        Car obj = null;
        String query = "SELECT * FROM public.car WHERE car_id = ?";
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

    public ArrayList<Car> findAll() {
        return this.selectByQuery("SELECT * FROM public.car ORDER BY car_id ASC");
    }

    public ArrayList<Car> getByListModelId(int model_id) {
        return this.selectByQuery("SELECT * FROM public.car WHERE car_model_id = " + model_id);
    }
    public ArrayList<Car> selectByQuery(String query) {
        ArrayList<Car> carList = new ArrayList<>();
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                carList.add(this.match(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public boolean save(Car car) {
        String query = "INSERT INTO public.car " +
                "(" +
                "car_model_id," +
                "car_color,"  +
                "car_km,"  +
                "car_plate"  +
                ")" +
                " VALUES (?, ?, ?, ?)";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, car.getModel_id());
            pr.setString(2, car.getColor().toString());
            pr.setInt(3, car.getKm());
            pr.setString(4, car.getPlate());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(Car car) {
        String query = "UPDATE public.car SET " +
                "car_model_id = ? ," +
                "car_color = ? , " +
                "car_km = ? , " +
                "car_plate = ?  " +
                "WHERE car_id = ?";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, car.getModel_id());
            pr.setString(2, car.getColor().toString());
            pr.setInt(3, car.getKm());
            pr.setString(4, car.getPlate());
            pr.setInt(5, car.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int car_id) {
        String query = "DELETE FROM public.car WHERE car_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, car_id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Car match (ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setColor(Car.Color.valueOf(rs.getString("car_color")));
        car.setKm(rs.getInt("car_km"));
        car.setPlate(rs.getString("car_plate"));
        car.setModel_id(rs.getInt("car_model_id"));
        car.setModel(this.modelDao.getById(car.getModel_id()));

        return car;
    }
}



