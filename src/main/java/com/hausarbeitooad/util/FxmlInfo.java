package com.hausarbeitooad.util;

import com.hausarbeitooad.RudisDampfkesselApp;
import com.hausarbeitooad.model.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Holds FXML information:<br>
 * 
 * The scenes are loaded lazily, that is, only the first time they are called.
 * After that, the loaded scene is looked and returned.
 * 
 * @author ksnortum
 * @version 2019-01-30
 * @source https://github.com/ksnortum/javafx-multi-scene-fxml/tree/pre-javafx-11
 */

/**
 * Diese Klasse dient als Verteiler für die Kontroller, sofern es Updates in der
 * Datenbank gibt, werden die Controller über diese Klasse darüber informiert
 *
 * @author 1st: Tim Cirksena, 2nd: Abdurrahman Azattemür
 * Source: Selber erstellt
 * */
public class FxmlInfo {
	
	//private static Logger logger = LogManager.getLogger();
	private BuyListner buyListner;
	private LoginListener loginListener;
	private GuthabenListner guthabenListner;
	private AcceptsID acceptsID;
	private CleaningListener cleaningListener;
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

	public GuthabenListner getGuthabenListner(){
		if(guthabenListner == null){
			scene = load();
		}
		return guthabenListner;
	}

	public CleaningListener getCleaningListener() {
		if(cleaningListener == null){
			scene = load();
		}
		return cleaningListener;
	}

	public LoginListener getLoginListener() {
		if (loginListener == null){
			scene = load();
		}
		return loginListener;
	}
	public BuyListner getBuyListner(){
		if(buyListner == null){
			scene = load();
		}
		return buyListner;
	}

	public AcceptsID getAcceptsID(){
		if(acceptsID == null){
			scene = load();
		}
		return acceptsID;
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
		RudisDampfkesselApp.updateScenes(this.getSceneName(), this);

		Stageable controller = loader.getController();
		if (controller != null) {
			controller.setStage(this.getStage());
		}

		LoginListener lgb = loader.getController();
		if (lgb != null){
			loginListener = lgb;
		}
		if(loader.getController() instanceof GuthabenListner){
			guthabenListner = loader.getController();
		}

		if(loader.getController() instanceof AcceptsID){
			acceptsID = loader.getController();
		}
		if(loader.getController() instanceof BuyListner){
			buyListner = loader.getController();
		}
		if (loader.getController() instanceof CleaningListener){
			cleaningListener = loader.getController();
		}

		return scene;
	}
}
