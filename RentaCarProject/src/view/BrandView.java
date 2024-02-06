package view;


import business.BrandManager;
import core.Helper;
import entity.Brand;

import javax.swing.*;

public class BrandView extends Layout{
    private JPanel contanier;
    private JLabel lbl_brand;
    private JLabel lbl_brand_name;
    private JTextField flg_brand_name;
    private JButton btn_brand_save;
    private Brand brand;
    private BrandManager brandManager;

    public BrandView(Brand brand) {
        this.brandManager = new BrandManager();
        this.add(contanier);
        this.guiInitiliaze(300,160);
        this.brand = brand;

        if (this.brand != null) {
            this.flg_brand_name.setText(this.brand.getName());
        }

        btn_brand_save.addActionListener(e -> {
            if ( Helper.isFieldEmpty(this.flg_brand_name)) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                if (this.brand == null){
                    result = this.brandManager.save(new Brand(this.flg_brand_name.getText()));
                } else {
                    this.brand.setName(this.flg_brand_name.getText());
                    result = this.brandManager.update(this.brand);
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
