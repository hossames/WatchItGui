package WatchIt.Controllers.Utils;

import src.ContentControl.Content;
import src.DataBase.DataObject;

import java.util.Comparator;

public class sortWatch implements Comparator<DataObject> {

    @Override
    public int compare(DataObject o1, DataObject o2) {
        Content con = (Content) o1;
        Content con2 = (Content) o2;
        return (int) con2.Viewers - (int) con.Viewers;
    }
}
