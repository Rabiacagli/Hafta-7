package core;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class Helper {
    public static void setTheme() {

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static void showMsg(String str) {

        optionPaneTR();
        String msg;
        String title;

        switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz!";
                title = "Hata";
            }
            case "done" -> {
                msg = "İşlem başarılı!";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt bulunamadı!";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı İşlem Yaptınız!";
                title = "Hata";
            }
            default -> {
                msg = str;
                title = "Bilgilendirme";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String msg;

        if (str.equals("sure")) {
            msg = "Silmek İstediğinize Emin misiniz?";
        } else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Uyarı", JOptionPane.YES_NO_OPTION) == 0;
    }

    public static boolean isFieldEmpty(JTextField fld) {
        return fld.getText().trim().isEmpty();
    }

    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
        UIManager.put("OptionPane.cancelButtonText", "İptal");
    }

    public static double CalculatePrice(double seasonFactor, double pensionFactor, int days, int adultcount, int childcount, double adultPrice, double childPrice) {
        return (((adultcount * adultPrice) + (childcount * childPrice)) * seasonFactor * pensionFactor * days);
    }

    public static int calculateDays(String checkin, String checkout) {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        int days = 0;
        try {
            Date date1 = myFormat.parse(checkin);
            Date date2 = myFormat.parse(checkout);
            long difference = date2.getTime() - date1.getTime();
            days = (int) ChronoUnit.DAYS.between(LocalDate.parse(checkin), LocalDate.parse(checkout));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


}
