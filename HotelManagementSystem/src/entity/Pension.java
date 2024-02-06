package entity;
public class Pension {

        private int pensionId;
        private int hotelId;
        private String pensionType;
        private double pension_factor;

    public Pension() {
    }
    public Pension(int pensionId, int hotelId, String pensionType,double pension_factor) {
        this.pensionId = pensionId;
        this.hotelId = hotelId;
        this.pensionType = pensionType;
        this.pension_factor = pension_factor;
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
    public double getPensionFactor() {
        return pension_factor;
    }
    public void setPensionFactor(double pension_factor) {
        this.pension_factor = pension_factor;
    }
}

