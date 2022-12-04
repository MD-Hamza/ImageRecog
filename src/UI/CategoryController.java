package src.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    @FXML
    private StackPane stack;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private TextField categories;

    @FXML
    private void loadSecond(ActionEvent event) throws IOException {
        int numberOfCategories = Integer.parseInt(categories.getText());
        Parent root = FXMLLoader.load(getClass().getResource("categoryNames.fxml"));
        stack.getChildren().remove(anchorRoot);
        stack.getChildren().add(root);

        BorderPane items = new BorderPane();
        items.setPadding(new Insets(10, 20, (12 - numberOfCategories) * 15, 20));

        VBox container = new VBox(2);

        for (int i = 0; i < numberOfCategories; i++) {
            TextField field = new TextField();
            field.setId(String.valueOf(i));
            field.setMaxWidth(200);
            container.getChildren().add(field);
        }
        container.setAlignment(Pos.CENTER);
        items.setBottom(container);
        stack.getChildren().add(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
