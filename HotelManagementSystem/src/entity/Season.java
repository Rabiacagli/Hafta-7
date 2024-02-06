package entity;

import java.time.LocalDate;

public class Season {

    private int seasonId;
    private int hotelId;
    private LocalDate strt_date;
    private LocalDate fnsh_date;
    private double season_factor;
    public Season() {
    }

    public Season(int seasonId, int hotelId, LocalDate strt_date, LocalDate fnsh_date, double season_factor) {
        this.seasonId = seasonId;
        this.hotelId = hotelId;
        this.strt_date = strt_date;
        this.fnsh_date = fnsh_date;
        this.season_factor = season_factor;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStrt_date() {
        return strt_date;
    }

    public void setStrt_date(LocalDate strt_date) {
        this.strt_date = strt_date;
    }

    public LocalDate getFnsh_date() {
        return fnsh_date;
    }

    public void setFnsh_date(LocalDate fnsh_date) {
        this.fnsh_date = fnsh_date;
    }

    public double getSeason_factor() {
        return season_factor;
    }

    public void setSeason_factor(double season_factor) {
        this.season_factor = season_factor;
    }
}
