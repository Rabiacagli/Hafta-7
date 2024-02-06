package business;

import core.Helper;
import dao.BookDao;
import entity.Book;


import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;

    public BookManager() {
        this.bookDao = new BookDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Book> books) {
        ArrayList<Object[]> bookObjList = new ArrayList<>();
        for (Book obj : books) {
            Object[] rowObject = new Object[size];

            int i = 0;
            rowObject[i++] = obj.getId();


            bookObjList.add(rowObject);
        }
        return bookObjList;
    }

    public ArrayList<Book> findAll() {
        return this.bookDao.findAll();
    }

    public boolean save(Book book) {
        if (book.getId() != 0) {
            Helper.showMsg("error");
        }
        return this.bookDao.save(book);
    }

  /*  public Book getById(int id) {
        return this.bookDao.getById(id);
    }

    public boolean update(Book book) {
        if (this.getById(book.getId()) == null) {
            Helper.showMsg("notfound");
        }
        return this.bookDao.update(book);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.bookDao.delete(id);
    }

    /* public ArrayList<Book> getByListBrandId(int brandId) {
        return this.bookDao.getByListBrandId(brandId);
    }

    public ArrayList<Book> searchForTable(int brandId, Book.Fuel fuel, Book.Gear gear, Book.Type type) {
        String select = "SELECT * FROM public.book";
        ArrayList<String> whereList = new ArrayList<>();

        if (brandId != 0) {
            whereList.add("model_brand_id =" + brandId);
        }

        if (fuel != null) {
            whereList.add("model_fuel ='" + fuel.toString() + "'");
        }
        if (gear != null) {
            whereList.add("model_gear ='" + gear.toString() + "'");
        }
        if (type != null) {
            whereList.add("model_type ='" + type.toString() + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.bookDao.selectByQuery(query);
    } */
}
