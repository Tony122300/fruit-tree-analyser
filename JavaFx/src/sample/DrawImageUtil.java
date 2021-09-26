package sample;

import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

public class DrawImageUtil {
    static String rectanglesImageName = "rectangles.png";
    static String sortImageName = "sortImageName.png";
    //Black and white pixel
    static ImageView drawBlackWhite(Image img, String imgPath) {
        ImageView blackWhiteImgView = new ImageView(img);
        PixelReader pR = img.getPixelReader();
        WritableImage wR = new WritableImage((int) img.getWidth(), (int) img.getHeight());
        PixelWriter pW = wR.getPixelWriter();
//having the black and white color
        Color black = Color.BLACK;
        Color white = Color.WHITE;
        String imgName = "red";
        if (imgPath.indexOf("orange") != -1) {
            imgName = "orange";
        } else if (imgPath.indexOf("purple") != -1) {
            imgName = "purple";
        }
        //judging the color value according to the three parameters of hue, saturation and brightness
        for (int readY = 0; readY < img.getHeight(); readY++) {
            for (int readX = 0; readX < img.getWidth(); readX++) {
                //for red fruits
                if (imgName == "red") {
                    if (((pR.getColor(readX, readY).getHue() > 0 && pR.getColor(readX, readY).getHue() < 20) ||
                            (pR.getColor(readX, readY).getHue() > 330 && pR.getColor(readX, readY).getHue() < 360))
                            && (pR.getColor(readX, readY).getSaturation() > 0.45 && pR.getColor(readX, readY).getSaturation() < 2)
                            && (pR.getColor(readX, readY).getBrightness() > 0.525 && pR.getColor(readX, readY).getBrightness() < 1)) {
                        //if above is true it turns white
                        pW.setColor(readX, readY, white);
                        blackWhiteImgView.setImage(wR);

                    } else {
                        //gives anything that doesnt turn white black
                        pW.setColor(readX, readY, black);
                        blackWhiteImgView.setImage(wR);
                    }
                    //for orange fruits
                } else if (imgName == "orange") {
                    if (pR.getColor(readX, readY).getHue() > 0 && pR.getColor(readX, readY).getHue() < 55
                            && pR.getColor(readX, readY).getSaturation() > 0.45 && pR.getColor(readX, readY).getSaturation() < 2
                            && (pR.getColor(readX, readY).getBrightness() > 0.3 && pR.getColor(readX, readY).getBrightness() < 1)) {
                        //if above is true it turns white
                        pW.setColor(readX, readY, white);
                        blackWhiteImgView.setImage(wR);
                    } else {
                        //gives anything that doesnt turn white black
                        pW.setColor(readX, readY, black);
                        blackWhiteImgView.setImage(wR);
                    }
                    //for purple fruits
                } else if (imgName == "purple") {
                    if (((pR.getColor(readX, readY).getHue() > 0 && pR.getColor(readX, readY).getHue() < 20) ||
                            (pR.getColor(readX, readY).getHue() > 250 && pR.getColor(readX, readY).getHue() < 360))
                            && (pR.getColor(readX, readY).getSaturation() > 0.3 && pR.getColor(readX, readY).getSaturation() < 2)) {
                        //if above is true it turns white
                        pW.setColor(readX, readY, white);
                        blackWhiteImgView.setImage(wR);
                    } else {
                        //gives anything that doesnt turn white black
                        pW.setColor(readX, readY, black);
                        blackWhiteImgView.setImage(wR);
                    }
                }
            }
        }
        return blackWhiteImgView;
    }

    //Write pictures (draw rectangles, write text)
    static String drawImageRectangles(File file,FruitClusterList fruitClusterList) {
        try {
            BufferedImage image2 = ImageIO.read(new File(file.getPath()));
            Graphics g = image2.getGraphics();
            g.setColor(java.awt.Color.BLUE);//color blue

            if (fruitClusterList.getHead() != null) {
                FruitClusterNode temp = fruitClusterList.getHead();
                while (temp != null) {
                    int width = temp.getValue().getMaxX()- temp.getValue().getMinX();
                    int height = temp.getValue().getMaxY()- temp.getValue().getMinY();
                    g.drawRect(temp.getValue().getMinX(), temp.getValue().getMinY(), width, height);//Rectangular frame (origin x coordinate, origin y coordinate, rectangle length, rectangle width)
                    temp = temp.getNextNode();
                }
            } else {
                System.out.println("No fruitCluster in list");
            }
            //g.dispose();
            String outPath = "out/outImage/" + rectanglesImageName;
            FileOutputStream out = new FileOutputStream(outPath);//The output of picture
            ImageIO.write(image2, "png", out);
            return outPath;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    static String drawImageSortText(File file,FruitClusterList fruitClusterList) {
        try {
            BufferedImage image2 = ImageIO.read(new File(file.getPath()));
            Graphics g = image2.getGraphics();
            g.setColor(java.awt.Color.BLUE);//color blue

            if (fruitClusterList.getHead() != null) {
                FruitClusterNode temp = fruitClusterList.getHead();
               // prints out the numbers beside the rectangle
                int count = 0;
                while (temp != null) {
                    count++;
                    g.setFont(new Font("Serif", Font.PLAIN, 12));
                    g.drawString(""+ count, temp.getValue().getMinX(), temp.getValue().getMinY());
                    temp = temp.getNextNode();
                }
            } else {
                System.out.println("No fruitCluster in list");
            }
            String outPath = "out/outImage/" + sortImageName;
            FileOutputStream out = new FileOutputStream(outPath);//The output of picture
            ImageIO.write(image2, "png", out);
            return outPath;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    static String drawImagePixelsNumText(File file,FruitClusterList fruitClusterList) {
        try {
            BufferedImage image2 = ImageIO.read(new File(file.getPath()));
            Graphics g = image2.getGraphics();
            g.setColor(java.awt.Color.black);//color RED
//prints out the numbers beside the rectangle
            if (fruitClusterList.getHead() != null) {
                FruitClusterNode temp = fruitClusterList.getHead();
                while (temp != null) {
                    g.setFont(new Font("Serif", Font.PLAIN, 12));
                    g.drawString(""+temp.getValue().getPixelsNum(), temp.getValue().getMinX(), temp.getValue().getMaxY());
                    temp = temp.getNextNode();
                }
            } else {
                System.out.println("No fruitCluster in list");
            }
            //g.dispose();
            String outPath = "out/outImage/" + sortImageName;
            FileOutputStream out = new FileOutputStream(outPath);//The output of picture
            ImageIO.write(image2, "png", out);
            return outPath;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    static ImageView drawColorImage(Image img, String imgPath,int [][] fruitsClusers) {
        ImageView colorImgView = new ImageView(img);
        PixelReader pR = img.getPixelReader();
        WritableImage wR = new WritableImage((int) img.getWidth(), (int) img.getHeight());
        PixelWriter pW = wR.getPixelWriter();

        Color [] colors = {Color.BLUE,Color.RED,Color.GREEN,Color.PINK,Color.GOLD,Color.GRAY,Color.PURPLE,Color.BLUE};
        for (int readY = 0; readY < img.getHeight(); readY++) {
            for (int readX = 0; readX < img.getWidth(); readX++) {
                if (fruitsClusers[readX][readY] != 0) {//means its not black
                    int colorIndex = fruitsClusers[readX][readY] % colors.length;
                    pW.setColor(readX, readY, colors[colorIndex]);
                    colorImgView.setImage(wR);
                } else {
                    pW.setColor(readX, readY, Color.BLACK);
                    colorImgView.setImage(wR);
                }
            }
        }

        return colorImgView;
    }
}
