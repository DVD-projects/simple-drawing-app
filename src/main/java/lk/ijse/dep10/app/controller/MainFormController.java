package lk.ijse.dep10.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainFormController {

    @FXML
    private ColorPicker clrFill;

    @FXML
    private ColorPicker clrStroke;

    @FXML
    private Canvas cnv;

    @FXML
    private ImageView imgEraser;

    @FXML
    private ImageView imgOval;

    @FXML
    private ImageView imgPencil;

    @FXML
    private ImageView imgRectangle;

    @FXML
    private ImageView imgRoundRectangle;

    @FXML
    private ImageView imgText;

    @FXML
    private Label lblEraser;

    @FXML
    private Label lblOval;

    @FXML
    private Label lblPencil;

    @FXML
    private Label lblRectangle;

    @FXML
    private Label lblRoundRectangle;

    @FXML
    private Label lblText;

    @FXML
    private AnchorPane root;

    @FXML
    private VBox vBox;

    private String tool;
    private String clrTool="Default";

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double width;
    private double height;
    private boolean keyPress;

    public void initialize() {
        cnv.widthProperty().bind(root.widthProperty());
        cnv.heightProperty().bind(root.heightProperty());
        cnv.getGraphicsContext2D().setStroke(clrStroke.getValue());
        cnv.getGraphicsContext2D().setFill(clrFill.getValue());
    }

    @FXML
    void cnvOnMousePressed(MouseEvent event) {
        x1 = event.getX();
        y1 = event.getY();
        if (tool.equals("Pencil")) {
            GraphicsContext gc = cnv.getGraphicsContext2D();
            gc.beginPath();
            gc.lineTo(x1,y1);
        }

    }

    @FXML
    void cnvOnMouseReleased(MouseEvent event) {
        x2 = event.getX();
        y2 = event.getY();
        width = x2-x1;
        height = y2-y1;
        GraphicsContext gc = cnv.getGraphicsContext2D();
        if (width<0){
            width = -width;
            x1=x2;
        }
        if (height<0){
            height=-height;
            y1 = y2;
        }
        if (tool!=null) {
            if (clrTool.equals("Stroke") || clrTool.equals("Default")) {
                if (tool.equals("Rectangle")) gc.strokeRect(x1,y1,width,height);
                if (tool.equals("Round Rectangle")) gc.strokeRoundRect(x1,y1,width,height,20,20);
                if (tool.equals("Oval")) gc.strokeOval(x1,y1,width,height);
            }
            if (clrTool.equals("Fill")) {
                if (tool.equals("Rectangle")) gc.fillRect(x1,y1,width,height);
                if (tool.equals("Round Rectangle")) gc.fillRoundRect(x1,y1,width,height,20,20);
                if (tool.equals("Oval")) gc.fillOval(x1,y1,width,height);
            }
        }
    }

    public void lblRectangleOnMouseClicked(MouseEvent mouseEvent) {
        tool = "Rectangle";
    }

    public void lblRoundRectangleOnMouseClicked(MouseEvent mouseEvent) {
        tool = "Round Rectangle";
    }

    public void lblOvalOnMouseClicked(MouseEvent mouseEvent) {
        tool = "Oval";
    }

    public void lblPencilOnMouseClicked(MouseEvent mouseEvent) {
        tool = "Pencil";
    }

    public void lblEraserOnMouseClicked(MouseEvent mouseEvent) {
        tool = "Eraser";
        System.out.println("erase");
    }


    public void clrStrokeOnAction(ActionEvent actionEvent) {
        clrTool = "Stroke";
        GraphicsContext gc = cnv.getGraphicsContext2D();
        gc.setStroke(clrStroke.getValue());
    }

    public void clrFillOnAction(ActionEvent actionEvent) {
        clrTool = "Fill";
        GraphicsContext gc = cnv.getGraphicsContext2D();
        gc.setFill(clrFill.getValue());
    }

    public void cnvOnMouseDragged(MouseEvent mouseEvent) {
        if (tool.equals("Eraser")) {
            x1 = mouseEvent.getX();
            y1 = mouseEvent.getY();
            GraphicsContext gc = cnv.getGraphicsContext2D();
            gc.clearRect(x1,y1,15,15);
        }
        if (tool.equals("Pencil")){
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            GraphicsContext gc = cnv.getGraphicsContext2D();
            gc.lineTo(x2,y2);
            gc.stroke();
        }
    }

    public void cnvOnMouseClicked(MouseEvent mouseEvent) {
        keyPress = true;
    }

    public void cnvOnKeyPressed(KeyEvent keyEvent) {
        if (keyPress){
            GraphicsContext gc = cnv.getGraphicsContext2D();
        }
    }

    public void vBoxOnMousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
    }

    public void vBoxOnMouseDragged(MouseEvent mouseEvent) {
        x2 = mouseEvent.getSceneX();
        y2 = mouseEvent.getSceneY();
        if (0<x2 || x2<cnv.getWidth()) {
            vBox.setLayoutX(x2-x1);
            vBox.setLayoutY(y2-y1);
        }
        if (0<y2 || y2<cnv.getHeight()) {
            vBox.setLayoutX(x2-x1);
            vBox.setLayoutY(y2-y1);
        }
    }
}
