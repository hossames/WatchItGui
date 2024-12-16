package WatchIt.Controllers.Enter;

import WatchIt.Models.Model;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.AccountControl.User;
import src.DataBase.DataBase;
import java.io.IOException;
public class RegisterController {
    // Fx Entities

    
    public TextField Email;

    
    public TextField FavName;

    
    public TextField FirstName;

    
    public TextField LastName;

    
    public Hyperlink Login;

    
    public PasswordField Password;

    
    public PasswordField RePassword;

    
    public Button RegisterButton;

    
    public BorderPane RegisterPage;

    public VBox Container;
    public ImageView Background;
    public TextField UserName;

    //Variables
    double mousePressedX,mousePressedY;

    //init Fx
    public void initialize() {
        try {
            RegisterPage.setTop(MainView.TitleBar().load());
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
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Enter transitions
    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)) {
            if(FirstName.isFocused()){
                LastName.requestFocus();
            }
            else if(LastName.isFocused()){
                UserName.requestFocus();
            }else if (UserName.isFocused()){
                Email.requestFocus();
            }else if (Email.isFocused()){
                Password.requestFocus();
            }else if (Password.isFocused()){
                RePassword.requestFocus();
            }else if (RePassword.isFocused()){
                FavName.requestFocus();
            }else{
                Register();
            }
        }

    }

    //Login Check
    public void Register(){
        if(!DataBase.accountsData.getDataByString(Email.getText(),4).isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Error");
            alert.setHeaderText(null);
            alert.setContentText("Email is already registered");
            alert.showAndWait();
        }else if(DataBase.accountsData.getDataByString(FavName.getText(),4).isEmpty()&&!Email.getText().matches(DataBase.getInstance().EmailRegex)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Error");
            alert.setHeaderText(null);
            alert.setContentText("Email should be like this example@domain.xyz");
            alert.showAndWait();
        }else if(!Password.getText().matches(DataBase.getInstance().PasswordRegex)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Error");
            alert.setHeaderText(null);
            alert.setContentText("Password should be contains of Capital char, Small Char, special Char , number and its length at least 8");
            alert.showAndWait();
        }else if(!Password.getText().equals(RePassword.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Password Matching Failed");
            alert.setHeaderText(null);
            alert.setContentText("Passwords doesn't match");
            alert.showAndWait();
        }else{
            Model.getInstance().getViewFactory().Show(MainView.GenreChoose
                    (FirstName.getText(),LastName.getText(),UserName.getText()
                            ,Email.getText(),Password.getText(),FavName.getText()));
        }
    }

    public void Login(MouseEvent mouseEvent) {
        Model.getInstance().getViewFactory().Show(MainView.LoginScene());
    }
}

