package com.hausarbeitooad;

import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.util.FxmlInfo;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Sets all scene info into a Map and displays the main scene.
 * <p>
 * Note: This class should be launched with these VM flags:
 * <p>
 * {@code --module-path /path/to/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml}
 * 
 * @author Knute Snortum
 * @version 2019-08-23
 */
public class SceneFxmlApp extends Application {
	
	private static final String MAIN_FXML = "main-view.fxml";
	private static final String SCENE_ONE_FXML = "scene-one.fxml";
	private static final String SCENE_TWO_FXML = "scene-two.fxml";
	private static final String SCENE_THREE_FXML = "scene-three.fxml";

	private static final String COLLECTION_VIEW_FXML = "collection-view.fxml";
	private static final String GUTHABEN_FXML = "guthaben-verwalten.fxml";
	private static final String LOGIN_FXML = "login.fxml";

	private static final String REVIEW_VIEW_FXML = "review-view.fxml";

	/** Holds the information for various scenes to switch between */
	private static Map<SceneName, FxmlInfo> scenes = new HashMap<>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		scenes.put(SceneName.LOGIN, new FxmlInfo(SceneFxmlApp.class.getResource(LOGIN_FXML), SceneName.LOGIN, stage));
		scenes.put(SceneName.MAIN, new FxmlInfo(SceneFxmlApp.class.getResource(MAIN_FXML), SceneName.MAIN, stage));
		scenes.put(SceneName.SCENE1, new FxmlInfo(SceneFxmlApp.class.getResource(SCENE_ONE_FXML), SceneName.SCENE1, stage));
		scenes.put(SceneName.SCENE2, new FxmlInfo(SceneFxmlApp.class.getResource(SCENE_TWO_FXML), SceneName.SCENE2, stage));
		scenes.put(SceneName.SCENE3, new FxmlInfo(SceneFxmlApp.class.getResource(SCENE_THREE_FXML), SceneName.SCENE3, stage));
		scenes.put(SceneName.GUTHABENVERWALTEN, new FxmlInfo(SceneFxmlApp.class.getResource(GUTHABEN_FXML), SceneName.GUTHABENVERWALTEN, stage));
		scenes.put(SceneName.COLLECTION_VIEW, new FxmlInfo(SceneFxmlApp.class.getResource(COLLECTION_VIEW_FXML), SceneName.COLLECTION_VIEW, stage));
		scenes.put(SceneName.REVIEW_VIEW, new FxmlInfo(SceneFxmlApp.class.getResource(REVIEW_VIEW_FXML), SceneName.REVIEW_VIEW, stage));

		// getScene() will load the FXML file the first time
		stage.setScene(scenes.get(SceneName.LOGIN).getScene());
		stage.setTitle("Rudi's Dampfkessel");
		stage.show();
	}

	/** @return a Map of the {@link FxmlInfo} by {@link SceneName} */
	public static Map<SceneName, FxmlInfo> getScenes() {
		return scenes;
	}
	
	/**
	 * Update the scene Map with new FxmlInfo
	 * 
	 * @param name the {@link SceneName} that is the key to update
	 * @param info the {@link FxmlInfo} that is the data to update
	 */
	public static void updateScenes(SceneName name, FxmlInfo info) {
		scenes.put(name, info);
	}

}
