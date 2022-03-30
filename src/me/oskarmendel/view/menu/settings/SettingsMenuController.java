/**
 * RapidTunes.
 * The music application to help you use all your music sources in one place.
 *
 * The MIT License (MIT)
 *
 * Copyright (C) 2016 The RapidTunes
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.oskarmendel.view.menu.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import me.oskarmendel.model.SettingsModel;

/**
 * Controller class for the Settings Menu.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name SettingsMenuController.java
 */
public class SettingsMenuController {
	
	@FXML private GeneralTabController settingsMenuGeneralController;
	@FXML private SongTabController settingsMenuSongController;
	@FXML private PlaylistTabController settingsMenuPlaylistController;
	@FXML private SourceTabController settingsMenuSourceController;
	@FXML private HotkeysTabController settingsMenuHotkeysController;
	@FXML private AccountTabController settingsMenuAccountController;
	
	@FXML private Button settingsMenuApplyButton;
	@FXML private Button settingsMenuCancelButton;
	
	private SettingsModel settingsModel;
	
	@FXML
	public void initialize() {
		
	}
	
	@FXML
	public void onApply(ActionEvent event) {
		this.settingsMenuGeneralController.apply();
		this.settingsMenuSongController.apply();
		this.settingsMenuPlaylistController.apply();
		this.settingsMenuSourceController.apply();
		this.settingsMenuHotkeysController.apply();
		this.settingsMenuAccountController.apply();
		
		Stage stage = (Stage) this.settingsMenuApplyButton.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void onCancel(ActionEvent event) {
		
	}
	
	/**
	 * Initializes the SettingsModel which contains the applications settings.
	 * 
	 * @param settingsModel - settingsModel object to share data with.
	 */
	public void initSettingsModel(SettingsModel settingsModel) {
		if (this.settingsModel != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		
		this.settingsModel = settingsModel;
		
		this.settingsMenuGeneralController.initSettingsModel(this.settingsModel);
		this.settingsMenuSongController.initSettingsModel(this.settingsModel);
		this.settingsMenuPlaylistController.initSettingsModel(this.settingsModel);
		this.settingsMenuSourceController.initSettingsModel(this.settingsModel);
		this.settingsMenuHotkeysController.initSettingsModel(this.settingsModel);
		this.settingsMenuAccountController.initSettingsModel(this.settingsModel);
	}
	
	public SettingsModel get() {
		return this.settingsModel;
	}
}
