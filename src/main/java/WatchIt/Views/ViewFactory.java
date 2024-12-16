package WatchIt.Views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewFactory {
    public boolean type;
    public int currentPage=0;
    public Stage PrimaryStage;
    public Scene CurrentScene;
    public ViewFactory() {
        PrimaryStage = new Stage();
        //PrimaryStage.initStyle(StageStyle.UNDECORATED);
        PrimaryStage.setFullScreen(true);
    }
    public void Show(FXMLLoader ShowingScene){
        try {
            CurrentScene = new Scene(ShowingScene.load(),1280,720);
            PrimaryStage.setScene(CurrentScene);
            PrimaryStage.setFullScreen(true);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error: while rendering view");
        }
        PrimaryStage.show();
    }
    public void setType(boolean type){
        this.type = type;
    }
}
