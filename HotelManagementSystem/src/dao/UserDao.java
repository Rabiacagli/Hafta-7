package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection conn;

    public UserDao() {
        this.conn = Db.getInstance();
    }



    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user ORDER BY user_id ASC";
        try {
            ResultSet rs = this.conn.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_pass = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User match (ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_pass"));
        obj.setRole(rs.getString("user_role"));

        return obj;

    }

    public boolean save(User user) {
        String query = "INSERT INTO public.user " +
                "(" +
                "user_name,"  +
                "user_pass,"  +
                "user_role"  +
                ")" +
                " VALUES (?, ?, ?)";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean update(User user) {
        String query = "UPDATE public.user SET " +
                "user_name = ? ," +
                "user_pass = ? , " +
                "user_role = ?  " +
                "WHERE user_id = ?";


        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());

            pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean delete(int user_id) {
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.conn.prepareStatement(query);
            pr.setInt(1, user_id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getById(int id) {
        User obj= null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
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

}