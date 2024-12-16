package WatchIt.Controllers.Account.Client.Pages.Settings;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import src.AccountControl.Account;
import src.DataBase.DataBase;

public class ChangePassword {

    public PasswordField OldPassword;
    public PasswordField newPassword;
    public Button SubmitButton;
    Account user;
    Stage stage;
    public ChangePassword(Account user, Stage stage) {
        this.user = user;
        this.stage = stage;
    }

    //Enter transitions
    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)&&newPassword.isFocused())
            Submit();
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0))
            newPassword.requestFocus();
    }

    //Login Check
    public void Submit(){
        if(!newPassword.getText().matches(DataBase.getInstance().PasswordRegex)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Error");
            alert.setHeaderText(null);
            alert.setContentText("Password should contains at least one Capital char,small char,number,special char and it's length more than 8");
            alert.showAndWait();
        }
        else if(OldPassword.getText().equals(user.getPassword())){
            user.setPassword(newPassword.getText());
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Matching Failed");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
        }
    }

}
