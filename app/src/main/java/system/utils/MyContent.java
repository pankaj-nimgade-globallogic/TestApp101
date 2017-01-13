package system.utils;

import android.graphics.Canvas;

import java.util.Vector;

/**
 * Created by pankaj.nimgade on 12-01-2017.
 */

public  class MyContent {

    private Vector<MyTable> myTables;
    private Canvas canvas;

    public MyContent(Vector<MyTable> myTables, Canvas canvas) {
        this.myTables = myTables;
        this.canvas = canvas;
    }

}
