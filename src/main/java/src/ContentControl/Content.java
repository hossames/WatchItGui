package src.ContentControl;
import WatchIt.Application;
import WatchIt.Views.AdminView;
import WatchIt.Views.ClientView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import src.AccountControl.User;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Content extends DataObject implements Rateable {
    public final Long contentID;
    public final String contentTitle;
    public final String story;
    public final Date datePublished;
    public static long cnt = 1;
    public final List<String> cast;
    public Image poster;
    public Node node;
    public final List<String>genres;
    public final String language;
    public final String country;
    public final int budget;
    public final int revenue;
    private long RateCounter;
    private long Rate_Sum;
    public long Viewers;
    public Content(String contentTitle,String language, String country,String Story,int budget, int revenue,List<String> genres,List<String>CastMembers,Date date){
        this.contentID =  cnt++;
        this.contentTitle = contentTitle;
        datePublished = date;
        this.cast = CastMembers;
        this.genres = genres;
        this.language = language;
        this.country = country;
        this.budget = budget;
        this.revenue = revenue;
        this.story = Story;
        Rate_Sum = 0;
        try {
            poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/" + contentTitle + ".jpg")));
        }catch (Exception e){
            poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/film.jpg")));
        }
        for(var castMember : DataBase.getInstance().castMemberData.getDataByString(cast,2)) {
            castMember.joinContent(contentTitle);
        }
    }
    public Content(Long Id,String contentTitle,String language, String country,String Story,int budget, int revenue,List<String> genres,List<String>CastMembers,Date date){
        this.contentID = Id;
        cnt = Math.max(cnt,Id+1);
        this.contentTitle = contentTitle;
        datePublished = date;
        this.story = Story;
        this.cast = CastMembers;
        this.genres = genres;
        this.language = language;
        this.country = country;
        this.budget = budget;
        this.revenue = revenue;
        Rate_Sum = 0;
        try {
            poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/" + contentTitle + ".jpg")));
        }catch (Exception e){
            poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/film.jpg")));
        }
        for(WatchRecord Record: DataBase.watchRecordData.getDataByString(contentTitle,0)) {
            if (Record.Rating != -1) {
                Rate_Sum += Record.Rating;
                RateCounter++;
            }
            Viewers++;
        }
    }

    public void AddRate(float rate) {
        DataBase.watchRecordData.addData(new WatchRecord(DataBase.getInstance().CurrentUser.getId(0),rate, contentTitle, new Date()));
        if(rate!=-1) {
            Rate_Sum += (long)rate;
            RateCounter++;
        }
    }

    public Image getImage(){
        return poster;
    }

    public void EditRate(float rate){
        WatchRecord WatchRecordTemp = DataBase.watchRecordData.removeData(contentTitle+" "+ DataBase.getInstance().CurrentUser.getId(0).toString(),2).getFirst();
        if(WatchRecordTemp.Rating != -1){
            Rate_Sum -= WatchRecordTemp.Rating;
        }
        WatchRecordTemp.Rating = (long) rate;
        Rate_Sum += (long)rate;
        DataBase.watchRecordData.addData(WatchRecordTemp);
        System.out.println(WatchRecordTemp);
    }
    public long TotalRate(){
        try {
            return (Rate_Sum/RateCounter);
        }catch (ArithmeticException e){
            return 0;
        }
    }
    //--------------------------------------DataBase Methods-----------------------------------------//
    @Override
    public Long getId(int op){
        return contentID;
    }
    @Override
    public Node getNode() {
        try {
            if(DataBase.getInstance().CurrentUser instanceof User)
                node = ClientView.ContentCard(this).load();
            else
                node = AdminView.ContentCard(this).load();
        } catch (Exception e) {
            ClientView.AlertShow("Error Loading ContentCard");

        }
        return node;
    }
    @Override
    public String getName(int op){
        return contentTitle;
    }
    @Override
    public String getVideo(){
        return "src/main/resources/Videos/"+contentTitle+".mp4";
    }
    @Override
    public Date getDate(){
        return datePublished;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(contentID.toString());
        sb.append(",");
        sb.append(contentTitle);
        sb.append(",");
        sb.append(language);
        sb.append(",");
        sb.append(country);
        sb.append(",");
        sb.append(story);
        sb.append(",");
        sb.append(Integer.valueOf(budget).toString());
        sb.append(",");
        sb.append(Integer.valueOf(revenue).toString());
        sb.append(",");
        sb.append(Integer.valueOf(genres.size()).toString());
        sb.append(",");
        for (String s : genres)
        {
            sb.append(s);
            sb.append(",");
        }
        sb.append(Integer.valueOf(cast.size()).toString());
        sb.append(",");
        for (String s : cast){
            sb.append(s);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(System.lineSeparator());
        sb.append(DateFormat.getInstance().format(datePublished));
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}

