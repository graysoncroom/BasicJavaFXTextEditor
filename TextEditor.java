import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.input.*;
import javafx.application.*;

public class TextEditer extends Application {

    private Label showKey;

    public static void main(String args[]) { launch(args); }

    // Override the start() method.
    public void start(Stage myStage) {

        // Give the stage a title.
        myStage.setTitle("The Sophomore Text Editer");

        // Use a FlowPane for the root node. In this case,
        // the horizontal gap is 10
        GridPane rootNode = new GridPane();
        rootNode.setHgap(10);
        rootNode.setVgap(10);
        rootNode.setStyle("-fx-background-color: grey");
        rootNode.setStyle("-fx-font-size: 60");
        // Center the controls in the scene.


        // Create a scene.
        Scene myScene = new Scene(rootNode, 900, 700);

        // Set the scene on the stage.
        myStage.setScene(myScene);

        // Create Labels.
        showKey = new Label("");

        // Handle a key-typed event on the scene.
        myScene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                showKey.setText(showKey.getText() + event.getCharacter());
            }
        });

        // Handle a key-pressed event on the scene.
        myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                switch (event.getCode()) {
                    case BACK_SPACE:
                        try {
                            showKey.setText(
                                    showKey.getText().substring(
                                            0, showKey.getText().length() - 2));
                        } catch(StringIndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                        break;
                	case TAB:
                        if(event.isControlDown()) {
                            try {
                                FileWriter fw = new FileWriter(new File("SophDoc.txt"));
                                fw.write(showKey.getText());
                                fw.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            }
        });

        // Add the labels to the scene graph.
        rootNode.getChildren().addAll(showKey);

        // SHow the stage and its scene.
        myStage.show();
    }
}
