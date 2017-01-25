package system.utils.pack111;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankaj.nimgade on 17-01-2017.
 */

public class MyBlock111List {

    public MyBlock111List() {
    }

    public static List<MyBlock111> getList() throws Exception {
        List<MyBlock111> myBlock110s = new ArrayList<>();
        myBlock110s.add(PatientInfo.getPatientInfo());
        myBlock110s.add(PTMLogsWritter.getPTMLogs());
        return myBlock110s;
    }
}
