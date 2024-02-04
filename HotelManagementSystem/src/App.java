import business.UserManager;
import core.Helper;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {

        Helper.setTheme();
        UserManager userManager = new UserManager();
        //LoginView loginView = new LoginView();
        //AdminView adminView = new AdminView(userManager.findByLogin("admin","1234"));
        EmployeeView employeeView = new EmployeeView(userManager.findByLogin("admin","1234"));

    }
}