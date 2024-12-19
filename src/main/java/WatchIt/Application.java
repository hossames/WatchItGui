package WatchIt;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage)  {
        Model.getInstance().getViewFactory().Show(MainView.LoginScene());
        //issues

    }

    public static void main(String[] args) {
        launch();
        DataBase.getInstance().Save();
    }
}