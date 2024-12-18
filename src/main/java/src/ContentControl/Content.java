package src.ContentControl;
import WatchIt.Application;
import WatchIt.Views.AdminView;
import WatchIt.Views.ClientView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import src.AccountControl.User;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class Content extends DataObject implements Rateable {
    public Long contentID;
    public String contentTitle;
    public String story;
    public Date datePublished;
    public static long cnt = (long)1;
    public List<String> cast;
    public Image poster;
    public Node node;
    public List<String>genres;
    public String language;
    public String country;
    public int budget;
    public int revenue;
    public long RateCounter;
    public long Rate_Sum,Viewers;
    public Content(String contentTitle,String language, String country,String Story,int budget, int revenue,List<String> genres,List<String>CastMembers,Date date){
        this.contentID = (Long) cnt++;
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
            poster = new Image(Application.class.getResourceAsStream("/Images/" + contentTitle + ".jpg"));
        }catch (Exception e){
            poster = new Image(Application.class.getResourceAsStream("/Images/film.jpg"));
        }
        DataBase.getInstance().castMemberData.getDataByString(cast,2).stream().forEach(item->{
            item.joinContent(contentTitle);
        });

    }
    public Content(Long Id,String contentTitle,String language, String country,String Story,int budget, int revenue,List<String> genres,List<String>CastMembers,Date date){
        this.contentID = Id;
        cnt = Id+1;
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
            poster = new Image(Application.class.getResourceAsStream("/Images/" + contentTitle + ".jpg"));
        }catch (Exception e){
            poster = new Image(Application.class.getResourceAsStream("/Images/film.jpg"));
        }
        for(WatchRecord Record: DataBase.watchRecordData.getDataByString(contentTitle,0)) {
            if (Record.Rating != -1) {
                Rate_Sum += Record.Rating;
                RateCounter++;
            }
            Viewers++;
        }
    }

    public void AddRate(int UserID, float rate) {
        DataBase.watchRecordData.addData(new WatchRecord((long) UserID,rate, contentTitle, new Date()));
        if(rate!=-1) {
            Rate_Sum += rate;
            RateCounter++;
        }
    }

    public Image getImage(){
        return poster;
    }

    public void EditRate(float rate){
        WatchRecord WatchRecordTemp = DataBase.watchRecordData.removeData(contentTitle+" "+Long.valueOf((long)DataBase.getInstance().CurrentUser.getId(0)).toString(),1).get(0);
        if(WatchRecordTemp.Rating != -1){
            Rate_Sum -= WatchRecordTemp.Rating;
        }
        WatchRecordTemp.Rating = (Float) rate;
        Rate_Sum += rate;
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

