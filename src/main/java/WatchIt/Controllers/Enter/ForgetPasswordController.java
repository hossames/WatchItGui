package WatchIt.Controllers.Enter;

import WatchIt.Models.Model;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.AccountControl.Account;
import src.AccountControl.Admin;
import src.DataBase.DataBase;

import java.io.IOException;

public class ForgetPasswordController {

    public BorderPane ForgetPasswordBorderPane;
    public PasswordField ReEnterPassword;
    public PasswordField newPassword;
    public ImageView Background;
    public VBox Container;
    String Email;
    public ForgetPasswordController(String Email) {
        this.Email = Email;
        Stage stage = Model.getInstance().getViewFactory().PrimaryStage;
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Background.setFitWidth(t1.doubleValue());
                Container.setMinWidth(t1.doubleValue());
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Background.setFitHeight(t1.doubleValue());
                Container.setMinHeight(t1.doubleValue());
            }
        });
    }
    //Variables
    double mousePressedX,mousePressedY;

    //init Fx
    public void initialize() {
        try {
            ForgetPasswordBorderPane.setTop(MainView.TitleBar().load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Enter transitions
    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)&&ReEnterPassword.isFocused())
            Submit();
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0))
            ReEnterPassword.requestFocus();
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
        else if(newPassword.getText().equals(ReEnterPassword.getText())){
            DataBase db = DataBase.getInstance();
            Account user = DataBase.accountsData.getDataByString(Email,4).getFirst();
            user.setPassword(newPassword.getText());
            if(user instanceof Admin){
                db.adminsData.getDataByString(Email,4).getFirst().setPassword(newPassword.getText());
            }else{
                db.usersData.getDataByString(Email,4).getFirst().setPassword(newPassword.getText());
            }
            Model.getInstance().getViewFactory().Show(MainView.LoginScene());
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Matching Failed");
            alert.setHeaderText(null);
            alert.setContentText("Passwords do not match");
            alert.showAndWait();
        }
    }
    public void Login(MouseEvent mouseEvent) {
        Model.getInstance().getViewFactory().Show(MainView.LoginScene());
    }

}
