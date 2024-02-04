package entity;
public class Pension {

        private int pensionId;
        private int hotelId;
        private String pensionType;

    public Pension() {
    }
    public Pension(int pensionId, int hotelId, String pensionType) {
        this.pensionId = pensionId;
        this.hotelId = hotelId;
        this.pensionType = pensionType;
    }

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getPensionType() {
        return pensionType;
    }

    public void setPensionType(String pensionType) {
        this.pensionType = pensionType;
    }
}

