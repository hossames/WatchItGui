package src.ContentControl;

import src.DataBase.DataBase;
import src.DataBase.DataObject;

import java.text.DateFormat;
import java.util.Date;

public class WatchRecord extends DataObject {
    public Long Rating;
    public Date DateOfWatching;
    public String ContentTitle;
    public Long UserId;
    public WatchRecord(long UserId, float Rating, String Content,Date DateOfWatching) {
        this.Rating = (long) Rating;
        this.DateOfWatching = DateOfWatching;
        this.ContentTitle = Content;
        this.UserId = (Long) UserId;
    }
    public WatchRecord(float Rating, String Content) {
        this.Rating = (long) Rating;
        this.DateOfWatching = new Date();
        this.ContentTitle = Content;
        this.UserId = DataBase.getInstance().CurrentUser.getId(0);
    }
    //--------------------------------------DataBase Methods-----------------------------------------//
    @Override
    public String toString() {
        return UserId.toString()+","+Rating.toString()+","+ ContentTitle+System.lineSeparator()+
                DateFormat.getInstance().format(DateOfWatching)+System.lineSeparator();
    }
    @Override
    public Long getId(int op){
        return UserId;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof WatchRecord watchRecord){
            return UserId.equals(watchRecord.UserId)&&ContentTitle.equals(watchRecord.ContentTitle);
        }
        return false;
    }
    public String getName(int op){
        if(op==0)
            return ContentTitle;
        return ContentTitle+" "+UserId.toString();
    }
}
