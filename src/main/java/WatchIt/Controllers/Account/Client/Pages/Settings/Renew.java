package WatchIt.Controllers.Account.Client.Pages.Settings;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.AccountControl.User;
import src.DataBase.DataBase;
import src.Subscription.CreditCard;
import src.Subscription.Subscription;

import java.util.Calendar;

public class Renew {
    public TextField CVV;

    public TextField CardNumber;

    public ToggleGroup CreditCard;

    public TextField ExpireMonth;

    public TextField ExpireYear;

    public BorderPane SubscriptionPlanPane;

    public TextField HolderName;

    public ToggleGroup SubscripeType;

    User user;

    Stage stage;
    public Renew(User user, Stage stage) {
        this.user = user;
        this.stage = stage;
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
            Subscription subscription = new Subscription(user.getId(0),type );
            user.Renew(subscription);
            System.out.println("OK");
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No such Credit Card");
            alert.showAndWait();
        }
    }
}
