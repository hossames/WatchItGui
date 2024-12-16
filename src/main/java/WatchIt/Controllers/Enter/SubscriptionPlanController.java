package WatchIt.Controllers.Enter;
import WatchIt.Models.Model;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.AccountControl.Account;
import src.AccountControl.User;
import src.DataBase.DataBase;
import src.Subscription.CreditCard;
import src.Subscription.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SubscriptionPlanController {
    public TextField CVV;

    public TextField CardNumber;

    public ToggleGroup CreditCard;

    public TextField ExpireMonth;

    public TextField ExpireYear;

    public BorderPane SubscriptionPlanPane;

    public TextField HolderName;

    public ToggleGroup SubscripeType;
    public ImageView Background;
    public VBox Container;
    double mousePressedX,mousePressedY;

    String FirstName,LastName,UserName,Email,Password,FavName;
    List<String>SelectedGenres = new ArrayList<>();
    public SubscriptionPlanController(String FirstName, String LastName, String UserName, String Email, String Password, String FavName, List<String>SelectedGenre) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
        this.FavName = FavName;
        this.SelectedGenres = SelectedGenre;
    }
    //init Fx
    public void initialize() {
        try {
            SubscriptionPlanPane.setTop(MainView.TitleBar().load());
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

    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)) {
            if(HolderName.isFocused()){
                CardNumber.requestFocus();
            }
            else if(CardNumber.isFocused()){
                ExpireYear.requestFocus();
            }else if (ExpireYear.isFocused()){
                ExpireMonth.requestFocus();
            }else if (ExpireMonth.isFocused()){
                CVV.requestFocus();
            }else {
                Submit();
            }
        }

    }

    public void Submit() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2000 + Integer.parseInt(ExpireYear.getText()));
        cal.set(Calendar.MONTH,Integer.parseInt(ExpireMonth.getText())-1);
        CreditCard creditCard = new CreditCard(CardNumber.getText(),HolderName.getText(),((RadioButton)CreditCard.getSelectedToggle()).getText(),CVV.getText(),cal.getTime());
        if(DataBase.getInstance().creditData.getDataByObject(creditCard)!=null){
            int type =0;
            String s = ((RadioButton)SubscripeType.getSelectedToggle()).getText();
            if(s.contains("3000"))
                type = 2;
            else if(s.contains("1750"))
                type = 1;
            Subscription subscription = new Subscription(Account.cnt,type);
            DataBase.getInstance().usersData.addData(new User(UserName,FirstName,LastName,Email,Password,subscription,SelectedGenres,new ArrayList<>(),new ArrayList<>(),FavName));
            Model.getInstance().getViewFactory().Show(MainView.LoginScene());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No such Credit Card");
            alert.showAndWait();
        }
    }

    public void Register(){
        Model.getInstance().getViewFactory().Show(MainView.RegisterScene());
    }
}
