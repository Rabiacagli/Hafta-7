package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;


import javax.swing.*;

public class UserView extends Layout {
    private JPanel contanier;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JComboBox cmb_user_role;
    private JButton btn_save;
    private JLabel lbl_username;
    private JLabel lbl_pass;
    private JLabel lbl_role;
    private UserManager userManager;
    private User user;

    public UserView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(contanier);
        this.guiInitiliaze(300, 250);

        if (this.user.getId() != 0){

            this.fld_username.setText(this.user.getUsername());
            this.fld_pass.setText(this.user.getPassword());
        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username, this.fld_pass})) {
                Helper.showMsg("fill");
            } else {
                boolean result ;

                this.user.setUsername(fld_username.getText());
                this.user.setPassword(fld_pass.getText());
                this.user.setRole((String) cmb_user_role.getSelectedItem());

                if (this.user.getId() != 0) {
                    result = this.userManager.update(this.user);

                } else {
                    result = this.userManager.save(this.user);
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


