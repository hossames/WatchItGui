package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class MainPageController {
    public Text Actors;
    public ImageView BackgroundImage;
    public Text ContentName;
    public Text ContentName12;
    public Text Genre;
    public Text Movies;
    public Text Series;
    public Button WatchNow;
    public Text Year;
    public ScrollPane Container;
    public VBox InnerContainer;
    public ImageView Background;
    private List<Node>nodeList;

    public void initialize(){
        for (var node : nodeList)
            InnerContainer.getChildren().add(node);
        Container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Background.setFitWidth(t1.doubleValue());
                InnerContainer.minWidth(t1.doubleValue());
                InnerContainer.maxWidth(t1.doubleValue());
                 for (var node : nodeList) {
                     VBox vBox = (VBox) node;
                     vBox.minWidth(t1.doubleValue());
                     vBox.setMaxWidth(t1.doubleValue());
                 }
            }
        });
    }

    public void Notifications(MouseEvent event) {

    }

    public void OpenActorsPage(MouseEvent event) {
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.CastPage()));
    }

    public void OpenMoviesPage(MouseEvent event) {
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.MoviesPage()));
    }

    public void OpenSeriesPage(MouseEvent event) {
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.SeriesPage()));
    }

    public void Search(MouseEvent event) {

    }

    public void User(MouseEvent event) {

    }

    public void WatchThatContent(MouseEvent event) {

    }

    public void setFavorite(MouseEvent event) {

    }

    public MainPageController(List<Node>nodeList){
        this.nodeList = nodeList;
    }
}
