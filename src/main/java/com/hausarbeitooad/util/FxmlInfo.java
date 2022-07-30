package com.hausarbeitooad.util;

import com.hausarbeitooad.SceneFxmlApp;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.model.Stageable;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Holds FXML information:<br>
 * <ul>
 *   <li>the resource name for the FXML file</li>
 *   <li>the {@link SceneName}</li>
 *   <li>the primary stage, if needed by the controller</li>
 *   <li>the scene for this FXML, iff it has been loaded and set</li>
 * </ul>
 * 
 * The scenes are loaded lazily, that is, only the first time they are called.
 * After that, the loaded scene is looked and returned.
 * 
 * @author
 * @version 2019-01-30
 */
public class FxmlInfo {
	
	//private static Logger logger = LogManager.getLogger();
	
	private URL resourceName;
	private SceneName sceneName;
	private Stage stage;
	private Scene scene;
	
	/**
	 * Construct an FxmlInfo object
	 * 
	 * @param resourceName the resource name for this FXML
	 * @param sceneName the {@link SceneName} for this FXML
	 * @param stage the primary stage that the scene will be set to
	 */
	public FxmlInfo(URL resourceName, SceneName sceneName, Stage stage) {
		this.resourceName = resourceName;
		this.sceneName = sceneName;
		this.stage = stage;
	}
	
	/** @return the resource name for this FXML file */
	public URL getResourceName() {
		return resourceName;
	}
	
	/** @return the {@link SceneName} for this FXML file */
	public SceneName getSceneName() {
		return sceneName;
	}
	
	/** @param scene the scene to set, loaded from this FxmlInfo */
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	
	/**
	 * Builds the scene iff {@code scene} is {@code null}.  Then it returns the scene to the caller.
	 * 
	 *  @return the scene 
	 */
	public Scene getScene() {
		if (scene == null) {
			scene = load();
		}
		return scene;
	}
	
	/** @return the primary stage for this Scene */
	public Stage getStage() {
		return stage;
	}

	/**
	 * lädt die FXML datei wenn diese noch ungeladen ist
	 * */
	private Scene load() {
		FXMLLoader loader = new FXMLLoader(this.getResourceName());
		Scene scene;
		try {
			scene = new Scene(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			Platform.exit();
			return null;
		}


		// Write back the updated FxmlInfo to the scenes Map in Main
		this.setScene(scene);
		SceneFxmlApp.updateScenes(this.getSceneName(), this);

		Stageable controller = loader.getController();
		if (controller != null) {
			controller.setStage(this.getStage());
		}
		//hier database verteilen
		//AcceptsDatabase controlr = loader.getController();

		return scene;
	}
}
