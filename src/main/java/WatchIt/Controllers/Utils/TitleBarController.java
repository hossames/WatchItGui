package WatchIt.Controllers.Utils;
import WatchIt.Models.Model;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TitleBarController {
    public HBox Container;
    double mousePressedX, mousePressedY;
    public void Close(MouseEvent e){
        Model.getInstance().getViewFactory().PrimaryStage.close();
    }
    public void initialize(){
        Container.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mousePressedX = mouseEvent.getX();
                mousePressedY = mouseEvent.getY();
            }
        });
        Container.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double crrX = mouseEvent.getScreenX();
                double crrY = mouseEvent.getScreenY();
                Model.getInstance().getViewFactory().PrimaryStage.setX(crrX - mousePressedX);
                Model.getInstance().getViewFactory().PrimaryStage.setY(crrY - mousePressedY);
            }
        });
    }
}
