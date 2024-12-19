package WatchIt.Controllers.Account.Client.Helps;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.List;

public class GridController {
    public GridPane View;
    public AnchorPane Container;
    public List<Node> nodeList;
    public Text title;
    String Title;
    public GridController(List<Node> nodeList,String Title) {
        this.nodeList = nodeList;
        this.Title=Title;
    }

    public void SetToGrid(){
        View.getChildren().clear();
        for(var node : nodeList)
            HandleGrid.setToGrid(View,(int)Container.getWidth(),node);
    }

    public void initialize(){
        title.setText(Title);
        SetToGrid();
        Container.widthProperty().addListener((observable, oldValue, newValue) -> {
            SetToGrid();
        });
    }
}
