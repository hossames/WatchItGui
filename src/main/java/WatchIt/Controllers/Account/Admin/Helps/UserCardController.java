package WatchIt.Controllers.Account.Admin.Helps;
import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.MainView;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.AccountControl.Account;
import src.AccountControl.Admin;
import src.AccountControl.User;
import src.DataBase.DataBase;

public class UserCardController {
    public Text Email;


    public Text FirstName;


    public Text LastName;


    public Text Password;


    public Text Rule;

    Account account;

    public UserCardController(Account account) {
        this.account = account;
    }

    public void initialize() {
        Email.setText(account.getEmail());
        FirstName.setText(account.getFirstName());
        LastName.setText(account.getLastName());
        Password.setText(account.getPassword());
        if(account instanceof Admin) {
            Rule.setText("Admin");
        }
        else{
            Rule.setText("User");
        }
    }
    public void DeleteAccount(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this user?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")){
            if(Rule.getText().equals("Admin")){
                DataBase.getInstance().adminsData.removeData((Admin)account);
            }
            else{
                DataBase.getInstance().usersData.removeData((User)account);
            }
            Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.UsersPage()));
        }
    }
    public void UserToAdmin() {
        if(account instanceof Admin){
            return;
        }
        DataBase.getInstance().usersData.removeData((User) account);
        Admin admin = new Admin(account);
        DataBase.getInstance().adminsData.addData(admin);
    }
    public void Promote(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to make this user Admin?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")){
            UserToAdmin();
        }
    }
}
