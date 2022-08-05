package com.hausarbeitooad;

import com.hausarbeitooad.db.DatabaseConnection;
import com.hausarbeitooad.model.SceneName;
import com.hausarbeitooad.util.FxmlInfo;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Quelle: <a href="https://github.com/ksnortum/javafx-multi-scene-fxml/tree/pre-javafx-11"> https://github.com/ksnortum/javafx-multi-scene-fxml/tree/pre-javafx-11 </a>
 *
 * @author Knute Snortum, modified by Abdurrahman Azattemür, Maximilian Jaesch, Tim Cirksena
 * @version 2019-08-23
 */
public class RudisDampfkesselApp extends Application {

	
	private static final String MAIN_FXML = "main-view.fxml";
	private static final String COLLECTION_VIEW_FXML = "collection-view.fxml";
	private static final String GAME_DETAIL_VIEW_FXML = "game-detail-view.fxml";
	private static final String GUTHABEN_VERWALTEN_VIEW_FXML = "guthaben-verwalten-view.fxml";
	private static final String LOGIN_VIEW_FXML = "login-view.fxml";
	private static final String OPTION_VIEW_FXML = "option-view.fxml";
	private static final String GUTHABEN_AUFLADEN_VIEW_FXML = "guthaben-aufladen-view.fxml";
	private static final String REZENSION_SCHREIBEN_VIEW_FXML = "rezension-schreiben-view.fxml";
	private static final String SHOP_MENU_VIEW_FXML = "shop-menu-view.fxml";
	private static final String REZENSION_VIEW_FXML = "rezension-view.fxml";
	private static final String SHOP_ITEM_VIEW_FXML = "shop-item-view.fxml";

	/** Holds the information for various scenes to switch between */
	private static Map<SceneName, FxmlInfo> scenes = new HashMap<>();

	private DatabaseConnection conn;

	public static void main(String[] args) {
		launch(args);
	}



	@Override
	public void start(Stage stage){
		conn = DatabaseConnection.getInstance();
		boolean loadTestData = false;
		if (loadTestData){
			BeispielDatenLoader b = new BeispielDatenLoader(conn);
		}
		//zum testen der Tables
		//conn.selectQuery("Select * from Nutzer");
		//conn.selectQuery("Select * from Nutzer_Besitzt");
		//conn.selectQuery("Select * from Rezension");
		//conn.selectQuery("Select * from Spiel");

		//alle FXMLinfo initialisieren, damit wir zwischen den scenes wechseln können
		scenes.put(SceneName.OPTION_VIEW, new FxmlInfo(RudisDampfkesselApp.class.getResource(OPTION_VIEW_FXML), SceneName.OPTION_VIEW, stage));
		scenes.put(SceneName.COLLECTION_VIEW, new FxmlInfo(RudisDampfkesselApp.class.getResource(COLLECTION_VIEW_FXML), SceneName.COLLECTION_VIEW, stage));
		scenes.put(SceneName.LOGIN, new FxmlInfo(RudisDampfkesselApp.class.getResource(LOGIN_VIEW_FXML), SceneName.LOGIN, stage));
		scenes.put(SceneName.MAIN, new FxmlInfo(RudisDampfkesselApp.class.getResource(MAIN_FXML), SceneName.MAIN, stage));
		scenes.put(SceneName.GUTHABENVERWALTEN, new FxmlInfo(RudisDampfkesselApp.class.getResource(GUTHABEN_VERWALTEN_VIEW_FXML), SceneName.GUTHABENVERWALTEN, stage));
		scenes.put(SceneName.GUTHABENAUFLADEN, new FxmlInfo(RudisDampfkesselApp.class.getResource(GUTHABEN_AUFLADEN_VIEW_FXML), SceneName.GUTHABENAUFLADEN, stage));
		scenes.put(SceneName.GAME_DETAIL_VIEW, new FxmlInfo(RudisDampfkesselApp.class.getResource(GAME_DETAIL_VIEW_FXML), SceneName.GAME_DETAIL_VIEW, stage));
		scenes.put(SceneName.REVIEW_VIEW, new FxmlInfo(RudisDampfkesselApp.class.getResource(REZENSION_SCHREIBEN_VIEW_FXML), SceneName.REVIEW_VIEW, stage));
		scenes.put(SceneName.SHOP_MENU, new FxmlInfo(RudisDampfkesselApp.class.getResource(SHOP_MENU_VIEW_FXML), SceneName.SHOP_MENU, stage));
		scenes.put(SceneName.SHOP_ITEM, new FxmlInfo(RudisDampfkesselApp.class.getResource(SHOP_ITEM_VIEW_FXML), SceneName.SHOP_ITEM, stage));
		scenes.put(SceneName.REZENSION_VIEW, new FxmlInfo(RudisDampfkesselApp.class.getResource(REZENSION_VIEW_FXML), SceneName.REZENSION_VIEW, stage));

		// getScene() will load the FXML file the first time
		stage.setScene(scenes.get(SceneName.LOGIN).getScene());
		stage.setTitle("Rudi's Dampfkessel");
		stage.setOnHidden( windowEvent -> {
			conn.closeDB();
		});
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
