package view;

import business.HotelManager;
import business.PensionManager;
import core.Helper;
import entity.Pension;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class PensionView extends Layout {
    private JPanel contanier;
    private JComboBox cmb_pension_type;
    private JButton btn_save;
    private JLabel lbl_hotel_name;
    private JLabel lbl_pension_type;
    private JLabel lbl_top;
    private JTextField fld_hotelid;
    private JTextField fld_pension_factor;
    private JLabel lbl_pansion_factor;
    private JComboBox cmb_hotel_name;
    private Pension pension;
    private PensionManager pensionManager;
    private HotelManager hotelManager = new HotelManager();
    private int hotelId;


    //Sezon bilgilerini görüntülemek ve düzenlemek için bir PensionView örneği oluşturur.
    // Verilen Pension nesnesi üzerinden sezon bilgileri alınır ve GUI bileşenlerine yerleştirilir.
    // Ayrıca, mevcut otellerin isimlerini bir liste olarak alır ve bu isimleri bir combo kutusuna yerleştirir.
    // Kullanıcı, bu combo kutusundan otel seçerse, otelin id"sini alır ve ilgili sezonun otel id"sine atanmasını sağlar
    public PensionView(Pension pension) {

        this.pension = pension;
        this.pensionManager = new PensionManager();
        this.add(contanier);
        this.guiInitiliaze(300, 300);

        List<String> hotelsName = hotelManager.getAllHotelsName();
        cmb_hotel_name.setModel(new DefaultComboBoxModel<>(hotelsName.toArray(new String[0])));

        cmb_hotel_name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String selectedHotelsName = (String) cmb_hotel_name.getSelectedItem();
                hotelId = hotelManager.getByHotelId(selectedHotelsName);
            }
        });


        if (this.pension.getPensionId() != 0) {

            String hotelName = hotelManager.getByName(this.pension.getHotelId());
            this.cmb_hotel_name.setSelectedItem(hotelName);
            this.cmb_pension_type.setSelectedItem(this.pension.getPensionType());
            this.fld_pension_factor.setText(String.valueOf(this.pension.getPensionFactor()));

        }


        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_pension_factor})) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                this.pension.setHotelId(hotelId);
                this.pension.setPensionType((String) cmb_pension_type.getSelectedItem());
                this.pension.setPensionFactor(Double.parseDouble(fld_pension_factor.getText()));


                if (this.pension.getPensionId() != 0) {
                    result = this.pensionManager.update(this.pension);

                } else {
                    result = this.pensionManager.save(this.pension);
                }

                if (result) {
                    Helper.showMsg("done");
                    this.dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });

    }  // Değerlendirme 10
}
