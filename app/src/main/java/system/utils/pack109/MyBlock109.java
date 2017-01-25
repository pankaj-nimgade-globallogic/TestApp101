package system.utils.pack109;

import android.graphics.Canvas;

import system.utils.MyTable;

/**
 * Created by pankaj.nimgade on 12-01-2017.
 */

public class MyBlock109 {

    private MyTable myTables;
    private Canvas canvas;
    private Bulletin bulletin;
    private String titleOfBlock;

    private MyBlock109() {
    }

    public MyTable getMyTables() {
        return myTables;
    }

    public void setMyTables(MyTable myTables) {
        this.myTables = myTables;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public String getTitleOfBlock() {
        return titleOfBlock;
    }

    public void setTitleOfBlock(String titleOfBlock) {
        this.titleOfBlock = titleOfBlock;
    }

    public static class MyBlock109Builder{
        private MyTable myTables;
        private Canvas canvas;
        private Bulletin bulletin;
        private String titleOfBlock;

        public MyBlock109Builder() {
        }

        public MyBlock109Builder setMyTables(MyTable myTables) {
            this.myTables = myTables;
            return this;
        }

        public MyBlock109Builder setCanvas(Canvas canvas) {
            this.canvas = canvas;
            return this;
        }

        public MyBlock109Builder setBulletin(Bulletin bulletin) {
            this.bulletin = bulletin;
            return this;
        }

        public MyBlock109Builder setTitleOfBlock(String titleOfBlock) {
            this.titleOfBlock = titleOfBlock;
            return this;
        }

        public MyBlock109 build(){
            MyBlock109 myBlock109 = new MyBlock109();
            if (this.myTables != null) {
                myBlock109.setMyTables(this.myTables);
            }
            if (this.canvas != null) {
                myBlock109.setCanvas(this.canvas);
            }
            if (this.bulletin != null) {
                myBlock109.setBulletin(this.bulletin);
            }
            if (this.titleOfBlock != null && !this.titleOfBlock.equals("")) {
                myBlock109.setTitleOfBlock(this.titleOfBlock);
            }
            return myBlock109;
        }


    }





    public static enum Bulletin  {
        SQUARE,
        CIRCLE,
        NO_BULLETIN,
    }
}
