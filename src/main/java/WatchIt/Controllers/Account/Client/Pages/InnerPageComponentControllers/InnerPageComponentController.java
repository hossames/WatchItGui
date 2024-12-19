package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Views.ClientView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            vbox.getChildren().add(ClientView.MediaComponent(content).load());
            List<Node> list;
            list=DataObjectController.MakeNodeList(DataBase.getInstance().castMemberData.getDataByString(content.cast,2));
           vbox.getChildren().add(ClientView.GridMake(list,"Cast Members").load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
