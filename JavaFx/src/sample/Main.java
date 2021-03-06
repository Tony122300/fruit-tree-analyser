package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class Main extends Application {
    StackPane stackPane;
    VBox vBox;
    Scene scene1,scene2;
    ImageView blackWhiteImgView,recImgView,sortImageView,pixelImageView = null;
    int minOutlierValue = 15;//The minimum
    private FruitClusterList fruitClusterList = new FruitClusterList();
    Stage primaryStage;
    String outRecPath = "";
    int [][] fruitsClusters;
    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;
        primaryStage.setTitle("fruit tree analyser system");
        stackPane = new StackPane();
        //setting size of scene1
        scene1 =  new Scene(stackPane, 300, 250);
        vBox = new VBox();
        //spacing for the vbox
        vBox.setSpacing(5);
        //setting size of scene2
        scene2 =  new Scene(vBox, 900, 800);

        Button buttonLoad = new Button("Select Image File");
        buttonLoad.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Images", "*.*");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file == null) return;
                showImage(file,primaryStage);
            }
        });
        stackPane.getChildren().add(buttonLoad);

        primaryStage.setScene(scene1);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public void clear() {
        vBox.getChildren().clear();
        primaryStage.setScene(scene1);
        blackWhiteImgView = null;
        recImgView = null;
        sortImageView = null;
        pixelImageView = null;
        outRecPath = null;
        fruitClusterList = new FruitClusterList();
    }
    public void showImage(File file,Stage primaryStage) {
        primaryStage.setScene(scene2);
        Button backButton = new Button("go back");
        backButton.setOnMouseClicked(e -> {
            clear();
        });
        String str = "file:" + file.getPath();
        System.out.println(str);
// hbox lays outs the buttons with a spacing of 5
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        Image image = new Image(str);
        ImageView imageView = new ImageView(image);
        hBox.getChildren().add(imageView);
// hbox lays outs the buttons with a spacing of 200
        HBox hBox2 = new HBox();
        hBox2.setSpacing(200);
// hbox lays outs the buttons with a spacing of 5
        HBox hBox3 = new HBox();
        hBox3.setSpacing(5);
// button for the cluster number and also showing the random colour for the fruits
        Button clusterNum = new Button("Show-ClusterNum");
        clusterNum.setOnMouseClicked(e -> {
            System.out.println("Click clusterNum");
            if (pixelImageView != null) return;
            String outPixelImgPath = DrawImageUtil.drawImagePixelsNumText(new File(outRecPath),fruitClusterList);
            Image pixelImage = new Image("file:"+outPixelImgPath);
            pixelImageView = new ImageView(pixelImage);
            hBox3.getChildren().add(pixelImageView);


            ImageView colorImageView = DrawImageUtil.drawColorImage(image,str,fruitsClusters);
            hBox3.getChildren().add(colorImageView);
        });
//runs the sortcluster and shows the image
        Button sortCluster = new Button("Sort-Cluster");
        sortCluster.setOnMouseClicked(e -> {
            System.out.println("Click sortCluster");
            if (sortImageView != null) return;
            fruitClusterList.sortFruitClusterByPixelsNum();
            printFruitCluster();
            String outSortImgPath = DrawImageUtil.drawImageSortText(new File(outRecPath),fruitClusterList);
            if (outSortImgPath == null) return;
            Image sortImage = new Image("file:"+outSortImgPath);
            sortImageView = new ImageView(sortImage);
            vBox.getChildren().add(clusterNum);
            hBox3.getChildren().add(sortImageView);
        });

        Button rectangles = new Button("Blue-Rectangles");
        rectangles.setOnMouseClicked(e -> {
            System.out.println("Click rectangles");
            if (recImgView != null) return;
            fruitsClusters = UnionUtil.countNumberOfClusters(blackWhiteImgView,fruitClusterList);
            fruitClusterList.removeFruitCluster(minOutlierValue);
            printFruitCluster();
            outRecPath = DrawImageUtil.drawImageRectangles(file,fruitClusterList);
            if (outRecPath == null) return;
            System.out.println("outPath: " + outRecPath);
            Image recImage = new Image("file:"+outRecPath);
            recImgView = new ImageView(recImage);
            hBox.getChildren().add(recImgView);
            hBox2.getChildren().add(sortCluster);
        });

        Button blackAndWhite = new Button("Black-and-white");
        blackAndWhite.setOnMouseClicked(e -> {
            System.out.println("Click blackAndWhite");
            if (blackWhiteImgView != null) return;
            blackWhiteImgView = DrawImageUtil.drawBlackWhite(image,str);
            hBox.getChildren().add(blackWhiteImgView);
            hBox2.getChildren().add(rectangles);
        });
        hBox2.getChildren().add(blackAndWhite);


        vBox.getChildren().addAll(backButton,hBox,hBox2,hBox3);
    }

    private void printFruitCluster() {
        if (fruitClusterList.getHead() != null) {
            FruitClusterNode temp = fruitClusterList.getHead();
            while (temp != null) {
                System.out.println("Clusters ID:" + temp.getValue().getID() + " pixels: " + temp.getValue().getPixelsNum());
                temp = temp.getNextNode();
            }
        } else {
            System.out.println("No fruitCluster in list");
        }
    }


}
