package WatchIt.Controllers.Account.Client.Pages.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import src.AccountControl.Account;

public class EditPersonalData {

    Account account;
    Stage stage;
    public EditPersonalData(Account account, Stage stage) {
        this.account = account;
        this.stage = stage;
    }

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    //Enter transitions
    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)&&LastName.isFocused())
            Submit();
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0))
            LastName.requestFocus();
    }

    public void Submit(){
        account.setFirstName(FirstName.getText());
        account.setLastName(LastName.getText());
    }

}
