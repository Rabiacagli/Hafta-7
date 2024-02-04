package view;

import business.PensionManager;
import core.Helper;
import entity.Pension;

import javax.swing.*;

public class PensionView extends Layout {
    private JPanel contanier;
    private JComboBox cmb_pension_type;
    private JButton btn_save;
    private JLabel lbl_hotelid;
    private JLabel lbl_pension_type;
    private JLabel lbl_top;
    private JTextField fld_hotelid;
    private Pension pension;
    private PensionManager pensionManager;

    public PensionView(Pension pension) {

        this.pension = pension;
        this.pensionManager = new PensionManager();
        this.add(contanier);
        this.guiInitiliaze(300, 250);


        if (this.pension.getPensionId() != 0) {

            this.fld_hotelid.setText(String.valueOf(this.pension.getHotelId()));
            this.cmb_pension_type.setSelectedItem(this.pension.getPensionType());

        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotelid})) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                this.pension.setHotelId(Integer.parseInt(fld_hotelid.getText()));
                this.pension.setPensionType((String) cmb_pension_type.getSelectedItem());


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

    }
}
