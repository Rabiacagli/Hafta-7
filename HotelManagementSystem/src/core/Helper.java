package core;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class Helper {
    public static void setTheme() {    // kullanıcı tarafındaki göünecek tablo için tema oluşmasını sağlar

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

    public static void showMsg(String str) {   // Kullanıcıyı yönlendirmek  bir takım mesajlar veririz, diğer sınıf ve tablolarda da kullanırız


        String msg;
        String title;

        switch (str) {
            case "fill" -> {
                msg = "Please fill all blanks!";
                title = "Error";
            }
            case "done" -> {
                msg = "Successful!";
                title = "Result";
            }
            case "notFound" -> {
                msg = "Not found!";
                title = "Not found";
            }
            case "error" -> {
                msg = "Wrong action!";
                title = "Error";
            }
            default -> {
                msg = str;
                title = "Information";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(String str) {
        String msg;

        if (str.equals("sure")) {
            msg = "Are you sure, you want to delete?";
        } else {
            msg = str;
        }

        return JOptionPane.showConfirmDialog(null, msg, "Warning", JOptionPane.YES_NO_OPTION) == 0;
    }

    // view ekranında metin alanının boş olup olmadığını kontrol eder
    public static boolean isFieldEmpty(JTextField fld) {
        return fld.getText().trim().isEmpty();
    }

    // view ekranındaki metin alanlarını Arraylistte tutar ve hepsini kontrol eder
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }


    // pencerenin ekran üzerindeki konumunu belirler
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }


    // tüm değişkenlerle  fiyat hesaplaması yapılır
    public static double CalculatePrice(double seasonFactor, double pensionFactor, int days, int adultcount, int childcount, double adultPrice, double childPrice) {
        return (((adultcount * adultPrice) + (childcount * childPrice)) * seasonFactor * pensionFactor * days);
    }


    //misafirlerin giriş ve çıkış tarihleri arasındaki kalış süresini hesaplamak için kullanılır.
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
