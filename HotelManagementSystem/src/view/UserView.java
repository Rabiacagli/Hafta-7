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

    //Kullanıcı hesabı bilgilerini görüntülemek ve düzenlemek için bir UserView örneği oluşturur.
    // Verilen User nesnesi üzerinden kullanıcı hesabı bilgileri alınır ve GUI bileşenlerine yerleştirilir.
    public UserView(User user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(contanier);
        this.guiInitiliaze(300, 250);

        if (this.user.getId() != 0){

            this.fld_username.setText(this.user.getUsername());
            this.fld_pass.setText(this.user.getPassword());
        }

        //Kaydet butonuna basıldığında, GUI'deki alanlardan kullanıcı hesabı bilgileri alınır ve bu bilgiler User nesnesine atanır.
        // Daha sonra bu bilgilerin doğruluğu kontrol edilir ve gerekirse kullanıcıya bir mesaj gösterilir.
        // Son olarak, değişiklikler veritabanında güncellenir veya yeni bir kullanıcı hesabı oluşturulur.
        // Sonuç duruma göre kullanıcıya bir mesaj gösterilir ve pencere kapatılır.
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


