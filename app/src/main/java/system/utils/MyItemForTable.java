package system.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankaj.nimgade on 13-01-2017.
 */

public class MyItemForTable {

    private String name1;
    private String name2;

    public MyItemForTable(String name1, String name2) {
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public static List<MyItemForTable> getListOfItems(){
        List<MyItemForTable> myItemForTables = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            myItemForTables.add(new MyItemForTable("Something to be", "Something to be"));
        }
        return myItemForTables;
    }
}
