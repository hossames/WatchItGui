package WatchIt.Views;

import WatchIt.Application;
import WatchIt.Controllers.Account.Admin.AdminController;
import WatchIt.Controllers.Account.Admin.Helps.EmptyCardController;
import WatchIt.Controllers.Account.Admin.Helps.UserCardController;
import WatchIt.Controllers.Account.Admin.Page.*;
import WatchIt.Controllers.Account.Client.Pages.OtherPageController;
import WatchIt.Controllers.Cast.CastCardAdminController;
import WatchIt.Controllers.Content.ContentCardControllerAdmin;
import WatchIt.Controllers.Content.EpisodeCardControllerAdmin;
import javafx.fxml.FXMLLoader;
import src.AccountControl.Account;
import src.AccountControl.User;
import src.Cast.CastMember;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

public class AdminView{
    public static FXMLLoader AdminScene(FXMLLoader fxmlLoader1) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Admin.fxml"));
        fxmlLoader.setController(new AdminController(fxmlLoader1));
        return fxmlLoader;
    }
    public static FXMLLoader DashBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/DashBoard.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader MoviesPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/ContentShow.fxml"));
        fxmlLoader.setController(new ContentShowController(DataBase.getInstance().moviesData,AddMovie()));
        return fxmlLoader;
    }
    public static FXMLLoader CastPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/ContentShow.fxml"));
        fxmlLoader.setController(new ContentShowController(DataBase.getInstance().castMemberData,AddCastMember()));
        return fxmlLoader;
    }
    public static FXMLLoader SeriesPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/ContentShow.fxml"));
        fxmlLoader.setController(new ContentShowController(DataBase.getInstance().seriesData,AddSeries()));
        return fxmlLoader;
    }
    public static FXMLLoader EpisodeCard(DataObject object){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Content/EpisodeCardAdmin.fxml"));
        fxmlLoader.setController(new EpisodeCardControllerAdmin(object));
        return fxmlLoader;
    }
    public static FXMLLoader EpisodePage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/ContentShow.fxml"));
        fxmlLoader.setController(new ContentShowController(DataBase.getInstance().episodesData,addEpisode()));
        return fxmlLoader;
    }
    public static FXMLLoader UsersPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/OtherPages.fxml"));
        fxmlLoader.setController(new OtherPageController(DataBase.accountsData));
        return fxmlLoader;
    }
    public static FXMLLoader CastCard(CastMember castMember){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Cast/CastCardAdmin.fxml"));
        fxmlLoader.setController(new CastCardAdminController(castMember));
        return fxmlLoader;
    }
    public static FXMLLoader UserCard(Account user){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Helps/UserCard.fxml"));
        fxmlLoader.setController(new UserCardController(user));
        return fxmlLoader;
    }
    public static FXMLLoader EmptyCard(FXMLLoader fxmlLoader1) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Helps/EmptyCard.fxml"));
        fxmlLoader.setController(new EmptyCardController(fxmlLoader1));
        return fxmlLoader;
    }
    public static FXMLLoader ContentCard(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Content/ContentCardAdmin.fxml"));
        fxmlLoader.setController(new ContentCardControllerAdmin(content));
        return fxmlLoader;
    }
    public static FXMLLoader AddMovie(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddMovie.fxml"));
        fxmlLoader.setController(new CreateMovieController());
        return fxmlLoader;
    }
    public static FXMLLoader AddSeries(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddSeries.fxml"));
        fxmlLoader.setController(new CreateSeriesController());
        return fxmlLoader;
    }
    public static FXMLLoader AddCastMember(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/CreateCast.fxml"));
        fxmlLoader.setController(new CreateCastController());
        return fxmlLoader;
    }
    public static  FXMLLoader addEpisode(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/AddEpisode.fxml"));
        fxmlLoader.setController(new CreateEpisodeController());
        return fxmlLoader;
    }
}
