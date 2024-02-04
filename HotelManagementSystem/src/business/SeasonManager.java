package business;

import core.Helper;
import dao.HotelDao;
import dao.SeasonDao;
import entity.Season;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    private HotelManager hotelManager;

        public SeasonManager() {
            this.seasonDao = new SeasonDao();
        }

        public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
            ArrayList<Object[]> seasonObjList = new ArrayList<>();
            for (Season obj : seasons) {
                Object[] rowObject = new Object[size];

                int i = 0;
                rowObject[i++] = obj.getSeasonId();
                rowObject[i++] = obj.getHotelId();
                rowObject[i++] = obj.getStrt_date();
                rowObject[i++] = obj.getFnsh_date();


                seasonObjList.add(rowObject);
            }
            return seasonObjList;
        }

        public ArrayList<Season> findAll() {
            return this.seasonDao.findAll();
        }

        public boolean save(Season season) {
            if (season.getSeasonId() != 0) {
                Helper.showMsg("error");
            }
            return this.seasonDao.save(season);
        }

        public Season getById(int id) {
            return this.seasonDao.getById(id);
        }

        public boolean update(Season season) {
            if (this.getById(season.getSeasonId()) == null) {
                Helper.showMsg("notfound");
            }
            return this.seasonDao.update(season);
        }

        public boolean delete(int id) {
            if (this.getById(id) == null) {
                Helper.showMsg("notfound");
                return false;
            }
            return this.seasonDao.delete(id);
        }

    }

