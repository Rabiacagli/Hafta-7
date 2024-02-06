import business.UserManager;
import core.Helper;
import view.AdminView;

public class App {
    public static void main(String[] args) {

        // DAO database access object
        // Business
        // View
        // entity
        // core

        //app üzerinde ki mainden program çalıştırılır.

        Helper.setTheme();
        UserManager userManager = new UserManager();
        AdminView adminView = new AdminView(userManager.findByLogin("admin","1234"));


    }
}
