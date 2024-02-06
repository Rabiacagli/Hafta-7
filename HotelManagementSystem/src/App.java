import business.UserManager;
import core.Helper;
import entity.User;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {

        Helper.setTheme();
        UserManager userManager = new UserManager();
        LoginView loginView = new LoginView();
    }
}