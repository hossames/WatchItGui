package WatchIt.Controllers.Enter;

import WatchIt.Models.Model;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.AccountControl.Account;
import src.DataBase.DataBase;

import java.io.IOException;
import java.util.List;

public class CheckFavoriteNameController{

    public TextField FAVNAME;
    public BorderPane CheckFavoriteNameBorderPane;
    public String Email;
    public ImageView Background;
    public VBox Container;
    public CheckFavoriteNameController(String Email){
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
            CheckFavoriteNameBorderPane.setTop(MainView.TitleBar().load());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Enter transitions
    public void GoToNext(KeyEvent keyEvent){
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0))
            Submit();
    }

    //Submit Check
    public void Submit(){
        List<Account>list = DataBase.accountsData.getDataByString(Email,4);
        if(list.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Email Error");
            alert.setHeaderText(null);
            alert.setContentText("Email not found");
            alert.showAndWait();
        } else if (list.getFirst().getFavoriteName().equals(FAVNAME.getText())) {
            Model.getInstance().getViewFactory().Show(MainView.ForgetScene(Email));
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Favorite Name Error");
            alert.setHeaderText(null);
            alert.setContentText("Favorite Name not matched");
            alert.showAndWait();
        }
    }

    public void Login(MouseEvent mouseEvent) {
        Model.getInstance().getViewFactory().Show(MainView.LoginScene());
    }
}
