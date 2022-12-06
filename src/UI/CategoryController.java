package src.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController {
    @FXML
    private StackPane stack;

    @FXML
    private AnchorPane anchorRoot;

    @FXML
    private TextField categories;
    @FXML
    private Label errorLabel;

    /**
     * Loads the categoryNames scene after the button is clicked.
     * Loads the number of forms that will be filled for each category
     * @param event: JavaFX event triggered when the button is pressed
     */
    @FXML
    private void loadCategoryNames(ActionEvent event) throws IOException {
        int numberOfCategories = 0;
        try {
            numberOfCategories = Integer.parseInt(categories.getText());
        } catch (Exception e) {
            errorLabel.setText("Please Enter integer values");
            return;
        }

        if (numberOfCategories > 6) {
            errorLabel.setText("Category limit of 5 exceeded");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("categoryNames.fxml"));

        BorderPane items = new BorderPane();
        items.setId("borderPane");

        items.setPadding(new Insets(10, 20, (12 - numberOfCategories) * 15, 20));

        VBox container = new VBox(2);
        container.setId("forms");

        // Creates a form for each category selected
        for (int i = 0; i < numberOfCategories; i++) {
            TextField field = new TextField();
            field.setId(String.valueOf(i));
            field.setMaxWidth(200);
            container.getChildren().add(field);
        }

        // Adds all the components to the UI
        container.setAlignment(Pos.CENTER);
        items.setBottom(container);
        stack.getChildren().add(items);
        stack.getChildren().add(root);
        stack.getChildren().remove(anchorRoot);
    }
}
