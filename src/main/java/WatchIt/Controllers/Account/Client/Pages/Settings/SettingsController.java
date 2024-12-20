package WatchIt.Controllers.Account.Client.Pages.Settings;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.AccountControl.Account;
import src.AccountControl.Admin;
import src.AccountControl.User;
import src.DataBase.DataBase;
import javafx.scene.control.Button;

public class SettingsController {
    Account account;
    public SettingsController(Account account) {
        this.account = account;
    }
    @FXML
    private Text Email;

    @FXML
    private Text FirstName;

    @FXML
    private Text LastName;

    @FXML
    private Text UserName;

    @FXML
    private Button UpgradeButton;

    @FXML
    private VBox Container;

    public void initialize() {
        FirstName.setText(account.getName(1));
        LastName.setText(account.getName(2));
        UserName.setText(account.getName(0));
        Email.setText(account.getEmail());
        if(account instanceof Admin) {
            Container.getChildren().remove(UpgradeButton);
        }
    }

    @FXML
    void ChangePassword(MouseEvent event) {
        MainView.ChangePassword();
    }

    @FXML
    void DeleteAccount(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this account?");
        alert.showAndWait();
        if(alert.getResult().getText().equals("OK")) {
            if(account instanceof Admin admin) {
                DataBase.getInstance().adminsData.removeData(admin);
            } else if (account instanceof User user) {
                DataBase.getInstance().usersData.removeData(user);
            }
            Model.getInstance().getViewFactory().Show(MainView.LoginScene());
            DataBase.getInstance().CurrentUser = null;
        }
    }

    @FXML
    void EditPersonalData(MouseEvent event) {
        MainView.EditPersonalData();
    }

    @FXML
    void Upgrade(MouseEvent event) {
        MainView.Upgrade();
    }

}
