package view;

import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;

public class SeasonView extends Layout {
    private JPanel contanier;
    private JLabel lbl_heading;
    private JLabel lbl_start_date;
    private JFormattedTextField fld_strt_day;
    private JLabel lbl_finish_date;
    private JFormattedTextField fld_finish_date;
    private JTextField fld_season_hotel_id;
    private JLabel lbl_season_hotel_id;
    private JButton btn_save;
    private Season season;
    private SeasonManager seasonManager;


    public SeasonView(Season season) {
        this.season = season;
        this.seasonManager = new SeasonManager();
        this.add(contanier);
        this.guiInitiliaze(300, 275);


        if (this.season.getSeasonId() != 0) {

            this.fld_season_hotel_id.setText(String.valueOf(this.season.getHotelId()));
            this.fld_strt_day.setText(String.valueOf(this.season.getStrt_date()));
            this.fld_finish_date.setText(String.valueOf(this.season.getFnsh_date()));
        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_season_hotel_id, this.fld_strt_day, this.fld_finish_date})) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                this.season.setHotelId(Integer.parseInt(fld_season_hotel_id.getText()));
                this.season.setStrt_date(LocalDate.parse(fld_strt_day.getText()));
                this.season.setFnsh_date(LocalDate.parse(fld_finish_date.getText()));


                if (this.season.getSeasonId() != 0) {
                    result = this.seasonManager.update(this.season);

                } else {
                    result = this.seasonManager.save(this.season);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }
}


