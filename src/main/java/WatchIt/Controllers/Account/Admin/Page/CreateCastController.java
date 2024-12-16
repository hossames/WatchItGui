package WatchIt.Controllers.Account.Admin.Page;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.MainView;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import src.Cast.CastMember;
import src.DataBase.DataBase;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class CreateCastController {

    public Button Add;

    public AnchorPane AddCast;

    public TextField FirstName;

    public TextField Gender;

    public TextField LastName;

    public TextField Nationality;

    public TextField SocialMediaLink;

    public TextField WorkType;

    public DatePicker dateOfBirth;


    public void Add() {
        CastMember castMember = new CastMember(FirstName.getText(),LastName.getText(),Gender.getText(),Nationality.getText(),SocialMediaLink.getText(),WorkType.getText(), new ArrayList<>(),Date.from(dateOfBirth.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        DataBase.getInstance().castMemberData.addData(castMember);
        MainView<CastMember>  view = new MainView<>();
    }

    public void GoToNext(KeyEvent keyEvent) {
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)) {
            if(FirstName.isFocused()){
                LastName.requestFocus();
            }
            else if(LastName.isFocused()){
                Gender.requestFocus();
            }else if (Gender.isFocused()){
                Nationality.requestFocus();
            }else if (Nationality.isFocused()){
                SocialMediaLink.requestFocus();
            }else if (SocialMediaLink.isFocused()){
                WorkType.requestFocus();
            }else{
                dateOfBirth.requestFocus();
            }
        }
    }

}
