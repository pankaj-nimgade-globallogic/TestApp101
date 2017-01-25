package system.utils.pack110;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankaj.nimgade on 17-01-2017.
 */

public class MyBlock110List {

    public MyBlock110List() {
    }

    public static List<MyBlock110> getList() throws Exception{
        List<MyBlock110> myBlock110s = new ArrayList<>();
        myBlock110s.add(PatientInfo.getPatientInfo());
        return myBlock110s;
    }
}
