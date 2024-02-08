package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password) {

        return this.userDao.findByLogin(username, password);
    }


    // kullanıcı verilerini bir tablo şeklinde sunmak için Arraylist oluştururuz ve kullanıcı özelleriklerini dizilere ekleriz.
    public ArrayList<Object[]> getForTable(int size) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user : this.findAll()) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }
    public ArrayList<User> findAll() {   // Tüm kullanıcıları veritabanından bulur ve bir liste olarak döndürür.
        return this.userDao.findAll();
    }

    public boolean save(User user) {   // kullanıcı ekleme
        return this.userDao.save(user);
    }

    public User getById(int id) {        //belirli bir kullanıcı id'sine sahip olan kullanıcıyı döndürür.
        return this.userDao.getById(id);
    }

    public boolean update(User user) {   // kullanıcıyı günceller
        if (this.getById(user.getId()) == null) {
            Helper.showMsg("notfound");
        }
        return this.userDao.update(user);
    }

    public boolean delete(int id) {      // kullanıcı siler
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.userDao.delete(id);
    }


}
