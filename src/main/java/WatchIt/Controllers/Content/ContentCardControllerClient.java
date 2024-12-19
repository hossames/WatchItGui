package WatchIt.Controllers.Content;

import WatchIt.Models.FavoritesModel;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ContentControl.*;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

public class ContentCardControllerClient {
    public ImageView ContentImage;

    public Text ContentName;

    public Text Genre;

    public Text Year;

    public FontAwesomeIconView Heart;

    DataObject content;

    public ContentCardControllerClient(DataObject content) {
        this.content = content;
    }

    public void initialize() {
        ContentImage.setImage(content.getImage());
        ContentName.setText(content.getName(2));
        Year.setText(String.valueOf(content.getDate().getYear()+1900));
        if(content instanceof Content content1){
            StringBuilder genres = new StringBuilder();
            for (int i = 0 ; i<Math.min(2,content1.genres.size());i++)
                genres.append(content1.genres.get(i)).append(" ");
            Genre.setText(genres.toString());
        }
        FavoritesModel favoritesModel = new FavoritesModel(content.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),0);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)!=null){
            Heart.setIcon(FontAwesomeIcon.HEART);
        }
    }

    public void OpenInnerPage(MouseEvent event){
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.InnerPage(content)));
    }

    public void SetFavorite(MouseEvent mouseEvent) {
        FavoritesModel favoritesModel = new FavoritesModel(content.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),0);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)==null){
           DataBase.getInstance().Favorites.addData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART);
        }else{
            DataBase.getInstance().Favorites.removeData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART_ALT);

        }
    }
}
