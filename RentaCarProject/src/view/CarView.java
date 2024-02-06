package view;

import business.CarManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.Car;
import entity.Model;

import javax.swing.*;

public class CarView extends Layout {
    private JPanel contanier;

    private Car car;
    private ModelManager modelManager;
    private CarManager carManager;
    private JComboBox<ComboItem> cmb_s_car_model;
    private JComboBox<Car.Color> cmb_s_car_color;
    private JButton btn_save;
    private JTextField fld_km;
    private JTextField fld_plate;
    private JLabel lbl_km;
    private JLabel lbl_color;
    private JLabel lbl_model;
    private JLabel lbl_plate;

    public CarView(Car car) {
        this.car = car;
        this.carManager = new CarManager();
        this.modelManager = new ModelManager();
        this.add(contanier);
        this.guiInitiliaze(300, 300);

        this.cmb_s_car_color.setModel(new DefaultComboBoxModel<>(Car.Color.values()));
        for (Model model : this.modelManager.findAll()) {
            this.cmb_s_car_model.addItem(model.getComboItem());
        }


        if (this.car.getId() != 0) {
            ComboItem selectedItem = car.getModel().getComboItem();
            this.cmb_s_car_model.getModel().setSelectedItem(selectedItem);
            this.cmb_s_car_color.getModel().setSelectedItem(car.getColor());
            this.fld_plate.setText(car.getPlate());
            this.fld_km.setText(Integer.toString(car.getKm()));
        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{fld_km, fld_plate})) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                ComboItem selectedModel = (ComboItem) this.cmb_s_car_model.getSelectedItem();
                this.car.setModel_id(selectedModel.getKey());
                this.car.setColor((Car.Color) cmb_s_car_color.getSelectedItem());
                this.car.setPlate(this.fld_plate.getText());
                this.car.setKm(Integer.parseInt(this.fld_km.getText()));
                if (this.car.getId() != 0) {
                    result = this.carManager.update(this.car);
                    dispose();
                } else {
                    result = this.carManager.save(this.car);
                    dispose();
                }
                if (result) {
                    Helper.showMsg("done");
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}





