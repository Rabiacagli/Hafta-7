package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {

    private final PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> seasons) {
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension obj : seasons) {
            Object[] rowObject = new Object[size];

            int i = 0;
            rowObject[i++] = obj.getPensionId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPensionType();

            pensionObjList.add(rowObject);
        }
        return pensionObjList;
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public boolean save(Pension pension) {
        if (pension.getPensionId() != 0) {
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public boolean update(Pension pension) {
        if (this.getById(pension.getPensionId()) == null) {
            Helper.showMsg("notfound");
        }
        return this.pensionDao.update(pension);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.pensionDao.delete(id);
    }

}

