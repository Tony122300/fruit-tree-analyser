package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;

public class UnionUtil {
    static int[][] countNumberOfClusters(ImageView blackWhiteImgView, FruitClusterList fruitClusterList) {
        Image img = blackWhiteImgView.getImage();
        PixelReader pR = img.getPixelReader();
        int pointValue = 0;
        int [][] fatherNode = new int[(int)img.getWidth()][(int)img.getHeight()];
        int [][] fruitsClusters = new int[(int)img.getWidth()][(int)img.getHeight()];
        for (int readY = 0; readY < img.getHeight(); readY++) {
            for (int readX = 0; readX < img.getWidth(); readX++) {
                int hashCode = pR.getColor(readX, readY).hashCode();
                //The hash value of white pixels is -1, the hash value of black is 255, and the white pixels of non-empty value indicate that they have been checked and do not need to be processed again
                if (hashCode == -1 && fatherNode[readX][readY] == 0) {
                    fatherNode[readX][readY] = ++pointValue;
                    fruitsClusters[readX][readY] = pointValue;
                    if (fruitClusterList.getLength() < pointValue) {
                        FruitCluster fruitCluster = new FruitCluster(readX,readY,fatherNode[readX][readY]);
                        FruitClusterNode fruitClusterNode = new FruitClusterNode(fruitCluster);
                        fruitClusterList.addFruitCluster(fruitClusterNode);
                    } else {
                        fruitClusterList.getTail().getValue().handleData(readX,readY);
                    }
                    NeighbourPointUnion(readX,readY,pR,fatherNode,fruitClusterList,fruitsClusters);
                }
            }
        }
        return fruitsClusters;
    }
    //
    static void NeighbourPointUnion(int x, int y, PixelReader pR, int[][] fatherNode, FruitClusterList fruitClusterList,int [][] fruitsClusters) {
        //left
        if (x-1 >= 0) {
            int hashCode = pR.getColor(x-1, y).hashCode();
            if (hashCode == -1 && fatherNode[x-1][y] == 0) {
                fatherNode[x-1][y] = fatherNode[x][y];
                fruitsClusters[x-1][y] = fatherNode[x][y];
                if (fruitClusterList.getLength() < fatherNode[x][y]) {
                    FruitCluster fruitCluster = new FruitCluster(x-1,y,fatherNode[x][y]);
                    FruitClusterNode fruitClusterNode = new FruitClusterNode(fruitCluster);
                    fruitClusterList.addFruitCluster(fruitClusterNode);
                } else {
                    fruitClusterList.getTail().getValue().handleData(x-1,y);
                }
                NeighbourPointUnion(x-1,y,pR,fatherNode,fruitClusterList,fruitsClusters);
            }
        }
        //right
        if (x+1 < fatherNode.length) {
            int hashCode = pR.getColor(x+1, y).hashCode();
            if (hashCode == -1 && fatherNode[x+1][y] == 0) {
                fatherNode[x+1][y] = fatherNode[x][y];
                fruitsClusters[x+1][y] = fatherNode[x][y];
                if (fruitClusterList.getLength() < fatherNode[x][y]) {
                    FruitCluster fruitCluster = new FruitCluster(x+1,y,fatherNode[x][y]);
                    FruitClusterNode fruitClusterNode = new FruitClusterNode(fruitCluster);
                    fruitClusterList.addFruitCluster(fruitClusterNode);
                } else {
                    fruitClusterList.getTail().getValue().handleData(x+1,y);
                }
                NeighbourPointUnion(x+1,y,pR,fatherNode,fruitClusterList,fruitsClusters);
            }
        }
        //up
        if (y+1 < fatherNode[0].length) {
            int hashCode = pR.getColor(x, y+1).hashCode();
            if (hashCode == -1 && fatherNode[x][y+1] == 0) {
                fatherNode[x][y+1] = fatherNode[x][y];
                fruitsClusters[x][y+1] = fatherNode[x][y];
                if (fruitClusterList.getLength() < fatherNode[x][y]) {
                    FruitCluster fruitCluster = new FruitCluster(x,y+1,fatherNode[x][y]);
                    FruitClusterNode fruitClusterNode = new FruitClusterNode(fruitCluster);
                    fruitClusterList.addFruitCluster(fruitClusterNode);
                } else {
                    fruitClusterList.getTail().getValue().handleData(x,y+1);
                }
                NeighbourPointUnion(x,y+1,pR,fatherNode,fruitClusterList,fruitsClusters);
            }
        }
        //down
        if (y-1 >= 0) {
            int hashCode = pR.getColor(x, y-1).hashCode();
            if (hashCode == -1 && fatherNode[x][y-1] == 0) {
                fatherNode[x][y-1] = fatherNode[x][y];
                fruitsClusters[x][y-1] = fatherNode[x][y];
                if (fruitClusterList.getLength() < fatherNode[x][y]) {
                    FruitCluster fruitCluster = new FruitCluster(x,y-1,fatherNode[x][y]);
                    FruitClusterNode fruitClusterNode = new FruitClusterNode(fruitCluster);
                    fruitClusterList.addFruitCluster(fruitClusterNode);
                } else {
                    fruitClusterList.getTail().getValue().handleData(x,y-1);
                }
                NeighbourPointUnion(x,y-1,pR,fatherNode,fruitClusterList,fruitsClusters);
            }
        }
    }
}
