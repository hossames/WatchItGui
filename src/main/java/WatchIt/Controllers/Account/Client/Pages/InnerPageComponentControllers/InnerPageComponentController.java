package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Views.ClientView;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import src.ContentControl.Content;
import src.DataBase.DataObject;

import java.io.IOException;

public class InnerPageComponentController {
    Content content;
    public InnerPageComponentController(DataObject content){
        this.content = (Content) content;
    }
    @FXML
    private VBox vbox;
    public void initialize(){
        try {
            vbox.getChildren().add(ClientView.HeadComponent(content).load());
            vbox.getChildren().add(ClientView.DescriptionComponent(content.story).load());
            vbox.getChildren().add(ClientView.MediaComponent(content.getVideo()).load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
