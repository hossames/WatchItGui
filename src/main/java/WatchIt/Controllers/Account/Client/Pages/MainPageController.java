package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    public VBox Container;
    public ImageView Background;
    private List<Node>nodeList;

    public void initialize(){
        for (var node : nodeList)
            Container.getChildren().add(node);
        Container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Background.setFitWidth(t1.doubleValue());
                 for (var node : nodeList) {
                     VBox vBox = (VBox) node;
                     vBox.setMaxWidth(t1.doubleValue());
                 }
            }
        });
    }

    public void Notifications(MouseEvent event) {

    }

    public void OpenActorsPage(MouseEvent event) {

    }

    public void OpenMoviesPage(MouseEvent event) {
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.MoviesPage()));
    }

    public void OpenSeriesPage(MouseEvent event) {

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
