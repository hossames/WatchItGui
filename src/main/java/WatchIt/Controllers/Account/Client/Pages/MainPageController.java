package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Controllers.Account.Client.Searchable;
import WatchIt.Models.FavoritesModel;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

import javax.lang.model.element.Name;
import java.util.List;

public class MainPageController extends Searchable {
    public Text ContentName;
    public Text Genre;
    public Text Series;
    public Button WatchNow;
    public Text Year;
    public ScrollPane Container;
    public VBox InnerContainer;
    public ImageView Background;
    private List<Node>nodeList;
    private DataObject mostPopular;
    public FontAwesomeIconView Heart;
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
        if(mostPopular != null) {
            Background.setImage(mostPopular.getImage());
            ContentName.setText(mostPopular.getName(2));
            Year.setText(String.valueOf(mostPopular.getDate().getYear() + 1900));
            if (mostPopular instanceof Content content1) {
                StringBuilder genres = new StringBuilder();
                for (int i = 0 ; i<Math.min(2,content1.genres.size());i++)
                    genres.append(content1.genres.get(i)).append(" ");
                Genre.setText(genres.toString());
            }
            FavoritesModel favoritesModel = new FavoritesModel(mostPopular.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),0);
            if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)!=null) {
                Heart.setIcon(FontAwesomeIcon.HEART);
            }
        }
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
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.GenreFilter("Any")));
    }

    public void User(MouseEvent event) {
        Model.getInstance().getViewFactory().Show(ClientView.setNode(MainView.SettingsPage()));
    }

    public void WatchThatContent(MouseEvent event) {
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.InnerPage(mostPopular)));
    }

    public void setFavorite(MouseEvent event) {
        FavoritesModel favoritesModel = new FavoritesModel(mostPopular.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),0);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)==null){
            DataBase.getInstance().Favorites.addData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART);
        }else{
            DataBase.getInstance().Favorites.removeData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART_ALT);
        }
    }

    public MainPageController(List<Node>nodeList, DataObject mostPopular){
        this.nodeList = nodeList;
        dataObjectController = DataBase.contentsData;
        this.mostPopular = mostPopular;
    }
}
