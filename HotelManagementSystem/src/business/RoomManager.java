package business;

import core.Helper;
import dao.RoomDao;
import entity.Hotel;
import entity.Room;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomManager {
    private final RoomDao roomDao;
    private HotelManager hotelManager = new HotelManager();
    Hotel hotel ;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj : rooms) {
            Object[] rowObject = new Object[size];

            hotel = hotelManager.getById(obj.getHotel_id());

            int i = 0;
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = obj.getPension_type();
            rowObject[i++] = obj.getRoom_type();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdult_price();
            rowObject[i++] = obj.getChild_price();
            rowObject[i++] = obj.getBed_capacity();
            rowObject[i++] = obj.getMkare();
            rowObject[i++] = obj.isTv();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isKonsol();
            rowObject[i++] = obj.isKasa();
            rowObject[i++] = obj.isProjeksiyon();
            rowObject[i++] = obj.getHotel_id();


            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public boolean save(Room room) {
        if (room.getRoom_id() != 0) {
            Helper.showMsg("error");
        }
        return this.roomDao.save(room);
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public boolean update(Room room) {
        if (this.getById(room.getRoom_id()) == null) {
            Helper.showMsg("notfound");
        }
        return this.roomDao.update(room);
    }

    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg("notfound");
            return false;
        }
        return this.roomDao.delete(id);
    }

    public ArrayList<Room> searchForTable(String hotelName, String city, String checkIn, String checkOut, String adult, String child) {
        int adultCount = Integer.parseInt(adult);
        int childCount = Integer.parseInt(child);
        String select = "SELECT DISTINCT room.*\n" +
                "FROM room\n" +
                "JOIN season ON room.hotel_id = season.hotel_id\n" +
                "JOIN pension ON room.hotel_id = pension.hotel_id\n" +
                "JOIN hotel ON room.hotel_id = hotel.hotel_id";

        ArrayList<String> whereList = new ArrayList<>();

        if (hotelName != null && !hotelName.isEmpty()) {
            whereList.add("hotel.hotel_name ILIKE '" + hotelName + "%'");
        }

        if (city != null && !city.isEmpty()) {
            whereList.add("hotel.hotel_address ILIKE '" + city + "%'");
        }

        if (checkIn != null && !checkIn.isEmpty() && checkOut != null && !checkOut.isEmpty()) {
            whereList.add(("'" + checkIn +"' >= season.start_date AND '"+ checkOut +"' <= season.finish_date"));
        }

        int totalCount = adultCount + childCount;
        if (totalCount != 0) {
            whereList.add("room.bed_capacity >=" + (totalCount));
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }
        return this.roomDao.selectByQuery(query);

    }

    public void updateStock(int roomId,int roomCount) {
        this.roomDao.updateStock(roomId,roomCount);
    }

    public List<Room> getRoomsByHotelId(int hotelId) {
        return this.roomDao.getRoomsByHotelId(hotelId);
    }

}

