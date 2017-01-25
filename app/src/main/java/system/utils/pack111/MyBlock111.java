package system.utils.pack111;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import system.utils.PageConfiguration;
import system.utils.pack110.MyTable110;

/**
 * Created by pankaj.nimgade on 12-01-2017.
 */

public class MyBlock111 {

    private List<MyTable111> myTables;
    private Canvas canvas;
    private Bulletin bulletin;
    private String titleOfBlock;
    private Paint blockPaint;

    private MyBlock111() {
        blockPaint = new Paint();
        blockPaint.setFakeBoldText(true);
        blockPaint.setTextSize(PageConfiguration.FONT_SIZE_HIGH+2);
        myTables = new ArrayList<>();
    }

    public List<MyTable111> getMyTables() {
        return myTables;
    }

    public void addTables(MyTable111 myTable110){
        myTables.add(myTable110);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    private void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    private void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public String getTitleOfBlock() {
        return titleOfBlock;
    }

    private void setTitleOfBlock(String titleOfBlock) {
        this.titleOfBlock = titleOfBlock;
    }

    private void setBlockPaint(Paint blockPaint) {
        this.blockPaint = blockPaint;
    }

    public Paint getBlockPaint() {
        return blockPaint;
    }

    public static class MyBlock109Builder {
        private Canvas canvas;
        private Bulletin bulletin;
        private String titleOfBlock;
        private Paint blockPaint;

        public MyBlock109Builder() {
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

        public MyBlock109Builder setBlockPaint(Paint blockPaint) {
            this.blockPaint = blockPaint;
            return this;
        }

        public MyBlock111 build() {
            MyBlock111 myBlock109 = new MyBlock111();
            if (this.blockPaint != null) {
                myBlock109.setBlockPaint(this.blockPaint);
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


    public static enum Bulletin {
        SQUARE,
        CIRCLE,
        NO_BULLETIN,
    }

    @Override
    public String toString() {
        return titleOfBlock != null ? titleOfBlock:"";
    }
}
