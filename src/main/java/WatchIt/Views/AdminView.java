package WatchIt.Views;

import WatchIt.Application;
import WatchIt.Controllers.Account.Admin.AdminController;
import WatchIt.Controllers.Account.Admin.Helps.EmptyCardController;
import WatchIt.Controllers.Account.Admin.Helps.UserCardController;
import WatchIt.Controllers.Account.Admin.Page.CreateCastController;
import WatchIt.Controllers.Content.ContentCardControllerAdmin;
import WatchIt.Controllers.Account.Admin.Page.CreateMovieController;
import javafx.fxml.FXMLLoader;
import src.AccountControl.Account;
import src.DataBase.DataObject;

public class AdminView{
    public static FXMLLoader AdminScene(FXMLLoader fxmlLoader1) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Admin.fxml"));
        fxmlLoader.setController(new AdminController(fxmlLoader1));
        return fxmlLoader;
    }
    public static FXMLLoader Profile() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Profile.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader AddCast() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddCast.fxml"));
        fxmlLoader.setController(new CreateCastController());
        return fxmlLoader;
    }
    public static FXMLLoader AddMovie() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddMovie.fxml"));
        fxmlLoader.setController(new CreateMovieController());
        return fxmlLoader;
    }
    public static FXMLLoader AccountCard(Account account) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Helps/UserCard.fxml"));
        fxmlLoader.setController(new UserCardController(account));
        return fxmlLoader;
    }
    public static FXMLLoader AddSeries() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddSeries.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader AddEpisode() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddEpisode.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader DashBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/DashBoard.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader EmptyCard() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Helps/EmptyCard.fxml"));
        fxmlLoader.setController(new EmptyCardController());
        return fxmlLoader;
    }
    public static FXMLLoader ContentCard(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Content/ContentCard.fxml"));
        fxmlLoader.setController(new ContentCardControllerAdmin(content));
        return fxmlLoader;
    }
}
