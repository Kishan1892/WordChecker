package WordChecker;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.*;

public class WordChecker extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		// fxml can be used to set the page
		primaryStage.setTitle("Word Checker");
		primaryStage.setWidth(400);

		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Check Word is Present in Alphabets");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		root.add(scenetitle, 0, 0, 2, 1);

		// Add Label and TextField for Alphabets
		Label alphabets = new Label("Alphabets:");
		root.add(alphabets, 0, 1);

		TextField alphabetsText = new TextField();
		root.add(alphabetsText, 1, 1);

		// Add Label and TextField for Word
		Label word = new Label("Word:");
		root.add(word, 0, 2);

		TextField wordText = new TextField();
		root.add(wordText, 1, 2);

		// Check Button
		Button btn = new Button();
		btn.setText("Check");
		root.add(btn, 0, 3);

		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.show();
		
		// Alert Message for Result Display
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText("Result :");

		// Button Event
		btn.setOnAction(new EventHandler<ActionEvent>() {			
		// Image Object
		Image image = null;
		ImageView imageView = null;
			@Override
			public void handle(ActionEvent event) {
				if (alphabetsText.getText().isEmpty() || wordText.getText().isEmpty()) {
					alert.setContentText("Don't Play with my code");
					image = new Image(
							"https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Emojione_1F62D.svg/64px-Emojione_1F62D.svg.png");
					imageView = new ImageView(image);
					alert.setGraphic(imageView);
					alert.showAndWait();

				} else {
					boolean result = checkWordPresent(alphabetsText.getText(), wordText.getText());

					if (result) {
						alert.setContentText(wordText.getText() + " can be made from " + alphabetsText.getText());
						image = new Image(
								"https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Noto_Emoji_Pie_1f600.svg/64px-Noto_Emoji_Pie_1f600.svg.png");
						imageView = new ImageView(image);
						alert.setGraphic(imageView);
					} else {
						image = new Image(
								"https://upload.wikimedia.org/wikipedia/commons/thumb/5/53/Noto_Emoji_Pie_1f914.svg/64px-Noto_Emoji_Pie_1f914.svg.png");
						imageView = new ImageView(image);
						alert.setGraphic(imageView);
						alert.setContentText(wordText.getText() + " cannot be made from " + alphabetsText.getText());
					}
					alert.showAndWait();
				}
			}
		});

	}

	public static boolean checkWordPresent(String text1, String wordCheck) {

		char[] arrayText1 = text1.toCharArray();
		Map<Character, Integer> x = new LinkedHashMap<>();
		for (int i = 0; i < arrayText1.length; i++) {
			if (x.containsKey(arrayText1[i])) {
				Integer value = x.get(arrayText1[i]);
				x.replace(arrayText1[i], value + 1);
			} else {
				x.put(arrayText1[i], 1);
			}
		}

		char[] wordChk = wordCheck.toCharArray();
		Map<Character, Integer> y = new LinkedHashMap<>();
		for (int i = 0; i < wordChk.length; i++) {
			if (y.containsKey(wordChk[i])) {
				Integer value = y.get(wordChk[i]);
				y.replace(wordChk[i], value + 1);
			} else {
				y.put(wordChk[i], 1);
			}
		}

		for (Entry<Character, Integer> i : y.entrySet()) {
			Character key = i.getKey();
			if (x.get(key) != null && i.getValue() <= x.get(key) && x.containsKey(key)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}