package WatchIt.Controllers.Utils;

import src.ContentControl.Content;
import src.DataBase.DataObject;

import java.util.Comparator;

public class sortRate implements Comparator<DataObject> {
    public int compare(DataObject o1, DataObject o2) {
        Content con = (Content) o1;
        Content con2 = (Content) o2;
        return (int) con2.TotalRate() - (int) con.TotalRate();
    }
}
