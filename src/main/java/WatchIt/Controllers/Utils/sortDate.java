package WatchIt.Controllers.Utils;

import src.ContentControl.Content;
import src.DataBase.DataObject;

import java.util.Comparator;

public class sortDate implements Comparator<DataObject> {
    public int compare(DataObject o1, DataObject o2) {
        return o2.getDate().compareTo(o1.getDate());
    }
}
