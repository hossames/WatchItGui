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
import java.util.Objects;

public class Episode extends DataObject implements Rateable{
  // Variables
  private static Long cnt = (long)1;
  private final Long Id ;
  private final String SeriesName;
  private final int episodeNumber;
  private final String episodeTitle;
  private final int duration;
  private final Date releaseDate;
  public long Rate_Sum=0,RateCounter=0;
  Image poster;
  // Constructors
  public Episode(String SeriesName,String episodeTitle, int episodeNumber, int duration, Date releaseDate) {
    this.episodeNumber = episodeNumber;
    this.episodeTitle = episodeTitle;
    this.duration = duration;
    this.releaseDate = releaseDate;
    this.SeriesName = SeriesName;
    try {
      poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/" + SeriesName + ".jpg")));
    }catch (Exception e){
      poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/film.jpg")));
    }
    Id = cnt++;
  }

  public Episode(Long Id,String SeriesName,String episodeTitle, int episodeNumber, int duration, Date releaseDate) {
    this.episodeNumber = episodeNumber;
    this.episodeTitle = episodeTitle;
    this.duration = duration;
    this.releaseDate = releaseDate;
    this.SeriesName = SeriesName;
    this.Id = Id;
    try {
      poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/" + SeriesName + ".jpg")));
    }catch (Exception e){
      poster = new Image(Objects.requireNonNull(Application.class.getResourceAsStream("/Images/film.jpg")));
    }
    cnt = Math.max(cnt,Id+1);
    InitRate();
  }

  // Getters & Setters
  public String getSeriesName() { return SeriesName; }
  public int getDuration(){
    return this.duration;
  }
  public Image getImage(){return poster;}

  public void InitRate(){
    for(WatchRecord Record: DataBase.watchRecordData.getDataByString(getName(2),0)){
      Rate_Sum += Record.Rating;
      RateCounter++;
    }
  }

  public void AddRate(float rate) {
    DataBase.watchRecordData.addData(new WatchRecord(DataBase.getInstance().CurrentUser.getId(0),rate, getName(2), new Date()));
    RateCounter++;
    Rate_Sum += (long)rate;
  }

  public void EditRate(float rate){
    WatchRecord WatchRecordTemp = DataBase.watchRecordData.removeData(getName(2)+" "+ DataBase.getInstance().CurrentUser.getId(0).toString(),2).getFirst();
    Rate_Sum -= WatchRecordTemp.Rating;
    WatchRecordTemp.Rating = (long) rate;
    Rate_Sum += (long)rate;
    DataBase.watchRecordData.addData(WatchRecordTemp);
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
  public String toString() {
    return Id.toString()+","+SeriesName+","+episodeTitle+","+
            Integer.valueOf(episodeNumber).toString()+","+Integer.valueOf(duration).toString()+
            System.lineSeparator()+ DateFormat.getInstance().format(releaseDate) +System.lineSeparator();
  }
  @Override
  public boolean equals(Object o) {
    if(o instanceof Episode ep){
        return ep.episodeTitle.equals(episodeTitle) && ep.episodeNumber == episodeNumber && ep.releaseDate.equals(releaseDate);
    }
    return false;
  }

  @Override
  public String getVideo(){
    return "src/main/resources/Videos/"+SeriesName+"/"+episodeNumber+".mp4";
  }

  @Override
  public Node getNode(){
    try{
      if(DataBase.getInstance().CurrentUser instanceof User) {
        return ClientView.EpisodeCard(this).load();
      }else {
        return AdminView.EpisodeCard(this).load();
      }
    }catch (Exception e){
      System.out.println("Failed rendering Episode Card");
      return null;
    }
  }
  /**
   * @param op send any Value
   * @return Long Id
   */
  @Override
  public Long getId(int op) {
    return Id;
  }

  /**
   * @param op
   * 0 -> Series Name       /
   * 1 -> Episode Title     /
   * 2 -> Series + " " + Episode Number
   * @return String
   */
  @Override
  public String getName(int op){
    if(op==0)
      return this.SeriesName;
    if(op==1)
      return episodeTitle;
    return SeriesName+" "+Integer.valueOf(episodeNumber).toString();
  }

  @Override
  public Date getDate(){
    return releaseDate;
  }
}
