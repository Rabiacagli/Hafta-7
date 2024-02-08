package business;

import core.Helper;
import dao.HotelDao;
import dao.SeasonDao;
import entity.Hotel;
import entity.Season;
import entity.Season;

import java.util.ArrayList;
import java.util.List;

public class SeasonManager {
    private final SeasonDao seasonDao;
    private HotelManager hotelManager = new HotelManager();
    private Hotel hotel;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    //  sezon verilerini bir Arraylist  şeklinde tutar
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season obj : seasons) {
            Object[] rowObject = new Object[size];

            this.hotel = hotelManager.getById(obj.getHotelId());

            int i = 0;   //Bu sütunların her birine, sezonla ilgili özellikler  atanır
            rowObject[i++] = obj.getSeasonId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = obj.getStrt_date();
            rowObject[i++] = obj.getFnsh_date();
            rowObject[i++] = obj.getSeason_factor();


            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }  // Dao'daki findall() metodu

    public boolean save(Season season) { // yeni sezon ekleme
        if (season.getSeasonId() != 0) {
            Helper.showMsg("error");
        }
        return this.seasonDao.save(season);
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    public boolean update(Season season) {     //sezon güncelleme
        if (this.getById(season.getSeasonId()) == null) {
            Helper.showMsg("notfound");
        }
        return this.seasonDao.update(season);
    }

    public boolean delete(int id) {          //sezon silme
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.seasonDao.delete(id);
    }

    //belirli bir otel id'sine sahip olan otelin sezon verilerini döndürmektir.
    public List<Season> getSeasonsByHotelId(int hotelId) {
        return this.seasonDao.getSeasonsByHotelId(hotelId);
    }

}

