package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Application;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;

public class CenterComponentController {
    public AnchorPane ControlBar;
    public MediaView MediaContiner;
    public FontAwesomeIconView Play,Sound;
    public Text time;
    public Slider TimeSlider;
    public Slider Volume;
    public MediaPlayer mediaPlayer;
    StackPane vol,tim;
    public CenterComponentController(String Path){
        try {
            Media media = new Media(new File(Path).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.pause();
        }catch (Exception e){
            Media media = new Media(new File("src/main/resources/Videos/Error.mp4").toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.pause();
        }
    }

    public void initialize() {
        MediaContiner.setMediaPlayer(mediaPlayer);
        mediaPlayer.setVolume(Volume.getValue());
        time.setText("00:00:00");
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            String h = String.valueOf((int)newValue.toHours());;
            String m= String.valueOf((int)newValue.toMinutes()%60);
            String s = String.valueOf((int)newValue.toSeconds()%60);
            if(h.length()==1){
                h = "0"+h;
            }
            if(m.length()==1){
                m = "0"+m;
            }
            if(s.length()==1){
                s = "0"+s;
            }
            time.setText(h+":"+m+":"+s);
            TimeSlider.setValue((newValue.toSeconds()/mediaPlayer.getTotalDuration().toSeconds())*100);
        });
        Volume.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(vol==null){
                vol = (StackPane) Volume.lookup(".track");
            }
            mediaPlayer.setVolume(newValue.doubleValue());
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %d%%, #969696 %d%%);",
                    newValue.intValue(), newValue.intValue());
            vol.setStyle(style);
        });
        TimeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(tim==null){
                tim = (StackPane) TimeSlider.lookup(".track");
            }
            String style = String.format("-fx-background-color: linear-gradient(to right, #2D819D %d%%, #969696 %d%%);",
                    newValue.intValue(), newValue.intValue());
            tim.setStyle(style);
        });
    }

    public void GetTime(MouseEvent event){
        mediaPlayer.pause();
    }

    public void SetTime(MouseEvent event) {
        mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(TimeSlider.getValue()/100));
        mediaPlayer.play();
    }

    public void Play(MouseEvent event) {
        System.out.println(FontAwesomeIcon.PLAY.toString());
        if(Play.getGlyphName().equals(FontAwesomeIcon.PLAY.toString())){
            mediaPlayer.play();
            Play.setIcon(FontAwesomeIcon.PAUSE);
        }else{
            mediaPlayer.pause();
            Play.setIcon(FontAwesomeIcon.PLAY);
        }
    }

    public void Mute(MouseEvent event) {
        if(mediaPlayer.isMute()){
            Sound.setIcon(FontAwesomeIcon.VOLUME_UP);
            mediaPlayer.setMute(false);
        }
        else
        {
            Sound.setIcon(FontAwesomeIcon.VOLUME_OFF);
            mediaPlayer.setMute(true);
        }
    }

    public FXMLLoader getMedia(){
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/Fxml/Account/Client/Pages/InnerPageComponents/CenterComponent.fxml"));
        fxmlLoader.setController(this);
        return fxmlLoader;
    }
}
