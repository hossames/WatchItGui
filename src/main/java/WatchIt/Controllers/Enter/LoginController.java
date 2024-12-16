package WatchIt.Controllers.Enter;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.AccountControl.Admin;
import src.AccountControl.User;
import src.DataBase.DataBase;
import java.awt.*;
import java.io.IOException;
public class LoginController {
    // Fx Entities
    public BorderPane LoginPage;
    public TextField Email;
    public PasswordField Password;
    public ImageView Background;
    public VBox Container;
    //Variables
    double mousePressedX,mousePressedY;

    //init Fx
    public void initialize() {
        try{
            LoginPage.setTop(MainView.TitleBar().load());
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

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //Enter transitions
    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)&&Password.isFocused())
            Login();
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0))
            Password.requestFocus();
    }

    //Login Check
    public void Login(){
        if(DataBase.getInstance().Login(Email.getText(),Password.getText())) {
            System.out.println("Login Successful");
            if(DataBase.getInstance().CurrentUser instanceof Admin)
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.DashBoard()));
            else
            {
                try {
                    if(DataBase.getInstance().CurrentUser instanceof User user && user.getSubscriptionPlan().isExpired()) {
                        Stage stage = new Stage();
                        try {
                            Scene scene = new Scene(ClientView.Renew(user, stage).load());
                            stage.setScene(scene);
                            stage.show();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }else
                        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.MainPage()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Email or Password is incorrect");
            alert.showAndWait();
        }
    }

    //Register Page
    public void Register(){
        Model.getInstance().getViewFactory().Show(MainView.RegisterScene());
    }

    //Forget PasswordPage
    public void Forget(){
        Model.getInstance().getViewFactory().Show(MainView.FavoriteNameScene(Email.getText()));
    }
}

