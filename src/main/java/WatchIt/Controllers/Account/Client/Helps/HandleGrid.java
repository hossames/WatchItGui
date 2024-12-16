package WatchIt.Controllers.Account.Client.Helps;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import src.DataBase.DataObject;
import java.util.List;

public class HandleGrid{
    public static void setToGridH(Node node,Node Item){
        GridPane grid = (GridPane) node;
        grid.setHgap(20);
        grid.add(Item,grid.getChildren().size()+1,1);
    }
    public static void setToGridV(Node node,Node Item){
        GridPane grid = (GridPane) node;
        grid.setVgap(10);
        grid.add(Item,1,grid.getChildren().size());
    }
    public static void setToGrid(Node node , int width,Node item){
        int widthItem=1;

        if(item instanceof AnchorPane pane){
            widthItem = (int)pane.getPrefWidth();
        }
        else if(item instanceof HBox hbox){
            widthItem = (int)hbox.getPrefWidth();
        }
        else if(item instanceof ScrollPane pane){
            widthItem = (int)pane.getPrefWidth();
        } else if(item instanceof VBox vbox) {
            widthItem = (int)vbox.getPrefWidth();
        }
        int cols = width/(widthItem+15);
        GridPane grid = (GridPane) node;
        grid.setHgap(15);
        grid.setVgap(10);
        if(cols>0)
            grid.add(item,grid.getChildren().size()%cols+1,grid.getChildren().size()/cols);
        else
            grid.add(item,1,grid.getChildren().size());
    }
    public static void setList(List<DataObject> list,Node node,int type,int width){
        GridPane grid = (GridPane) node;
        if(type == 1){
            for(var item : list){
                setToGridH(node,item.getNode());
            }
        }
        else if(type == 2){
            for(var item : list){
                setToGridV(node,item.getNode());
            }
        }else{
            for(var item : list){
                setToGrid(node,width,item.getNode());
            }
        }
    }
}
