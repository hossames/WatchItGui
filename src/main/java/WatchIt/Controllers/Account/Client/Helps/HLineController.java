package WatchIt.Controllers.Account.Client.Helps;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.DataBase.DataObject;
import java.util.List;

public class HLineController {
    public ScrollPane Container;
    public Text Title;
    public GridPane View;
    public VBox MainContainer;
    String title ;
    List<DataObject> nodeList;
    double Width = 0,widthItem=1;

    public HLineController(List<DataObject>list,String title){
        this.title = title;
        nodeList = list;
    }

    public void initialize() {
        Title.setText(title);
        for(var item :nodeList){
            HandleGrid.setToGridH(View,item.getNode());
        }
        MainContainer.widthProperty().addListener((observable, oldValue, newValue) -> {
            Container.setMinWidth(newValue.doubleValue()-10);
            Container.setMaxWidth(newValue.doubleValue()-10);
            if((int)((AnchorPane)View.getChildren().getFirst()).getWidth()>0) {
                Width = nodeList.size() - newValue.intValue() / ((int) ((AnchorPane) View.getChildren().getFirst()).getWidth()+20);
                Width = 1 / Width;
                System.out.println(Width);
            }
        });

    }

    @FXML
    void Next(MouseEvent event) {
        if(Container.getHvalue()+Width<0.99)
            Container.setHvalue(Container.getHvalue()+Width);
        else
            Container.setHvalue(1);
        System.out.println(Container.getHvalue());
    }

    @FXML
    void Previous(MouseEvent event) {
        if(Container.getHvalue()>0)
            Container.setHvalue(Container.getHvalue()-Width);
    }

}
