package WatchIt.Views;

import WatchIt.Application;
import WatchIt.Controllers.Account.Client.*;
import WatchIt.Controllers.Account.Client.Helps.GridController;
import WatchIt.Controllers.Account.Client.Pages.OtherPageController;
import WatchIt.Controllers.Account.Client.Pages.Settings.ChangePassword;
import WatchIt.Controllers.Account.Client.Pages.Settings.Renew;
import WatchIt.Controllers.Cast.CastCardClientController;
import WatchIt.Controllers.Cast.CastPageController;
import WatchIt.Controllers.Content.ContentCardControllerClient;
import WatchIt.Controllers.Account.Client.Helps.HLineController;
import WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers.*;
import WatchIt.Controllers.Account.Client.Pages.MainPageController;
import WatchIt.Controllers.Account.Client.Pages.ContentsPageController;
import WatchIt.Controllers.Content.EpisodeCardControllerAdmin;
import WatchIt.Controllers.Content.EpisodeCardControllerClient;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import src.AccountControl.*;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.DataBase.*;
import src.Engines.RecommendationEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class sortWatch implements Comparator<Content> {
    public int compare(Content o1, Content o2) {
        return (int)o1.Viewers-(int)o2.Viewers;
    }
}

class sortRate implements Comparator<Content> {
    public int compare(Content o1, Content o2) {
        return (int)o1.TotalRate()-(int)o2.TotalRate();
    }
}

class sortDate implements Comparator<Content> {
    public int compare(Content o1, Content o2) {
        return o1.datePublished.compareTo(o2.datePublished);
    }
}

public class ClientView {
    public static  FXMLLoader Client(Node node){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Client.fxml"));
        fxmlLoader.setController(new ClientController(node));
        return fxmlLoader;
    }
    public static FXMLLoader MainPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/MainPage.fxml"));
        List<DataObject>db = new ArrayList<>();
        List<Content>listContent = new ArrayList<>(DataBase.contentsData.getData().stream().sorted(new sortWatch()).toList());
        for (int i = listContent.size()-1; i >= Math.max(listContent.size()-10,0); i--) {
            db.add(listContent.get(i));
        }
        HLine(db,"Popular");

        List<DataObject>db1 = new ArrayList<>();
        List<Content>listContent1 = new ArrayList<>(DataBase.contentsData.getData().stream().sorted(new sortRate()).toList());
        for (int i = listContent1.size()-1; i >= Math.max(listContent1.size()-10,0); i--) {
            db1.add(listContent1.get(i));
        }
        HLine(db1,"Best");

        List<DataObject>db2 = new ArrayList<>();
        List<Content>listContent2 = new ArrayList<>(DataBase.contentsData.getData().stream().sorted(new sortDate()).toList());
        for (int i = listContent2.size()-1; i >= Math.max(listContent2.size()-10,0); i--) {
            db2.add(listContent1.get(i));
        }
        HLine(db2,"Recent");

        List<DataObject>db3 = new ArrayList<>();
        RecommendationEngine recommendationEngine  = new RecommendationEngine();
        db3.addAll(recommendationEngine.getContentBasedRecommendations());


        List<Node>nodeList=new ArrayList<>();
        try {
            nodeList.add(HLine(db3,"Recommendations").load());
            nodeList.add(HLine(db,"Popular").load());
            nodeList.add(HLine(db1,"Best").load());
            nodeList.add(HLine(db2,"Recent").load());
        }catch (IOException e){
            e.printStackTrace();
        }
        fxmlLoader.setController(new MainPageController(nodeList));
        return fxmlLoader;
    }

    public static FXMLLoader MoviesPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/ContentsPage.fxml"));
        fxmlLoader.setController(new ContentsPageController(DataBase.getInstance().moviesData));
        return fxmlLoader;
    }
    public static FXMLLoader EpisodeCard(DataObject object){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Content/EpisodeCardClient.fxml"));
        fxmlLoader.setController(new EpisodeCardControllerClient(object));
        return fxmlLoader;
    }
    public static FXMLLoader SeriesPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/ContentsPage.fxml"));
        fxmlLoader.setController(new ContentsPageController(DataBase.getInstance().seriesData));
        return fxmlLoader;
    }

    public static FXMLLoader CastPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/OtherPages.fxml"));
        fxmlLoader.setController(new OtherPageController(DataBase.getInstance().castMemberData));
        return fxmlLoader;
    }

    public static FXMLLoader CastCard(CastMember castMember){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Cast/CastCardClient.fxml"));
        fxmlLoader.setController(new CastCardClientController(castMember));
        return fxmlLoader;
    }

    public static void AlertShow(String Message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(Message);
        alert.showAndWait();
    }
    public static FXMLLoader setNode(FXMLLoader loader){
        try {
            return Client(loader.load());
        }catch (IOException e){
            AlertShow("Error Loading Node in Client View");
            e.printStackTrace();
        }
        return null;
    }
    public static FXMLLoader ContentCard(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Content/ContentCardClient.fxml"));
        fxmlLoader.setController(new ContentCardControllerClient(content));
        return fxmlLoader;
    }
    public static  FXMLLoader Renew(User user, Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/Settings/Renew.fxml"));
        fxmlLoader.setController(new Renew(user,stage));
        return fxmlLoader;
    }
    public static  FXMLLoader ChangePassword(Account user, Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/Settings/ChangePassword.fxml"));
        fxmlLoader.setController(new ChangePassword(user,stage));
        return fxmlLoader;
    }
    public static FXMLLoader Profile(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Profile.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader HeadComponent(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/InnerPageComponents/HeadComponent.fxml"));
        fxmlLoader.setController(new HeadComponentController(content));
        return fxmlLoader;
    }
    public static FXMLLoader DescriptionComponent(String story){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/InnerPageComponents/DescriptionComponent.fxml"));
        fxmlLoader.setController(new DescriptionController(story));
        return fxmlLoader;
    }
    public static FXMLLoader InnerPage(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/InnerPageComponents/InnerPage.fxml"));
        fxmlLoader.setController(new InnerPageComponentController(content));
        return fxmlLoader;
    }
    public static FXMLLoader CastPage(CastMember castMember,List<Node> nodeList){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Cast/CastPage.fxml"));
        fxmlLoader.setController(new CastPageController(castMember,nodeList));
        return fxmlLoader;
    }
    public static FXMLLoader WatchLater(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/OtherPages.fxml"));
        List<DataObject> list= DataBase.contentsData.ConvertListDataObject();
        list.addAll(DataBase.getInstance().episodesData.ConvertListDataObject());
        DataObjectController<DataObject> watched = new DataObjectController<>('\0',list);
        fxmlLoader.setController(new OtherPageController(new DataObjectController<>('\0',watched.getDataByString(((User)DataBase.getInstance().CurrentUser).getWatchLater(), 2))));
        return fxmlLoader;
    }
    public static FXMLLoader History(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/OtherPages.fxml"));
        List<DataObject> list= DataBase.contentsData.ConvertListDataObject();
        list.addAll(DataBase.getInstance().episodesData.ConvertListDataObject());
        DataObjectController<DataObject> watched = new DataObjectController<>('\0',list);
        fxmlLoader.setController(new OtherPageController(new DataObjectController<>('\0',watched.getDataByString(((User)DataBase.getInstance().CurrentUser).getHistory(), 2))));
        return fxmlLoader;
    }
    public static FXMLLoader MediaComponent(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/InnerPageComponents/CenterComponent.fxml"));
        fxmlLoader.setController(new CenterComponentController(content));
        return fxmlLoader;
    }
    public static FXMLLoader GenreComponent(String genre){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Pages/InnerPageComponents/GenreComponent.fxml"));
        fxmlLoader.setController(new GenreComponentController(genre));
        return fxmlLoader;
    }
    public static FXMLLoader GridMake(List<Node> nodeList,String Title){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Helps/Grid.fxml"));
        fxmlLoader.setController(new GridController(nodeList,Title));
        return fxmlLoader;
    }
    public static FXMLLoader HLine(List<DataObject> content,String Title){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Client/Helps/HLine.fxml"));
        fxmlLoader.setController(new HLineController(content,Title));
        return fxmlLoader;
    }
}
