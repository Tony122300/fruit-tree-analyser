package sample;

//The fruit tree cluster object,the information of the white pixel block in a rectangular box, is used to draw a rectangle.
public class FruitCluster {
    private int pixelsNum = 0;//Single cluster size (pixel value)
    private int maxX,minX,maxY,minY=0;//The maximum and minimum boundary value of the pixel block, used to draw a rectangular border
    private int ID = 0;//ID
    public FruitCluster(int x,int y,int ID)
    {
        this.ID = ID;
        handleData(x,y);
    }

    public void handleData(int x,int y)
    {
        countBorder(x,y);
        addPixelsNum();
    }
    //Processing the boundary
    public void countBorder(int x,int y) {
        if (x < minX || minX == 0) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY || minY == 0) minY = y;
        if (y > maxY) maxY = y;
    }

    public void addPixelsNum()
    {
        this.pixelsNum++;
    }
    public int getPixelsNum()
    {
        return this.pixelsNum;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinY() {
        return minY;
    }

    public int getID() {
        return ID;
    }
}
