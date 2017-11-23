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

package me.oskarmendel.settings;

import java.util.Properties;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import me.oskarmendel.settings.hotkeys.Hotkey;

/**
 * HotkeySettings class that stores and controls settings handling
 * hotkeys.
 * 
 * @author Oskar Mendel
 * @version 0.00.00
 * @name HotkeySettings.java
 */
public class HotkeySettings extends Settings {

	private static final String PATH = "settings/hotkey.properties";
	
	private static final Hotkey DEFAULT_NEWPLAYLIST = new Hotkey(KeyCombination.keyCombination("Ctrl+N"));
	private static final Hotkey DEFAULT_IMPORTPLAYLIST = new Hotkey(KeyCombination.keyCombination("Ctrl+Shift+N"));
	
	private static final Hotkey DEFAULT_UNDO = new Hotkey(KeyCombination.keyCombination("Ctrl+Z"));
	private static final Hotkey DEFAULT_REDO = new Hotkey(KeyCombination.keyCombination("Ctrl+Y"));
	private static final Hotkey DEFAULT_CUT = new Hotkey(KeyCombination.keyCombination("Ctrl+X"));
	private static final Hotkey DEFAULT_COPY = new Hotkey(KeyCombination.keyCombination("Ctrl+C"));
	private static final Hotkey DEFAULT_PASTE = new Hotkey(KeyCombination.keyCombination("Ctrl+V"));
	private static final Hotkey DEFAULT_DELETE = new Hotkey(KeyCode.DELETE);
	private static final Hotkey DEFAULT_MARKALL = new Hotkey(KeyCombination.keyCombination("Ctrl+A"));
	private static final Hotkey DEFAULT_SEARCH = new Hotkey(KeyCombination.keyCombination("Ctrl+L"));
	private static final Hotkey DEFAULT_SETTINGS = new Hotkey(KeyCombination.keyCombination("Ctrl+P"));
	
	private static final Hotkey DEFAULT_NEXT = new Hotkey(new KeyCodeCombination(KeyCode.RIGHT, KeyCodeCombination.CONTROL_DOWN));
	private static final Hotkey DEFAULT_PREVIOUS = new Hotkey(new KeyCodeCombination(KeyCode.LEFT, KeyCodeCombination.CONTROL_DOWN));
	private static final Hotkey DEFAULT_FASTFORWARD = new Hotkey(new KeyCodeCombination(KeyCode.RIGHT, KeyCodeCombination.SHIFT_DOWN));
	private static final Hotkey DEFAULT_REWIND = new Hotkey(new KeyCodeCombination(KeyCode.LEFT, KeyCodeCombination.SHIFT_DOWN));
	private static final Hotkey DEFAULT_SHUFFLE = new Hotkey(KeyCombination.keyCombination("Ctrl+S"));
	private static final Hotkey DEFAULT_REPEAT = new Hotkey(KeyCombination.keyCombination("Ctrl+R"));
	private static final Hotkey DEFAULT_VOLUMEUP = new Hotkey(new KeyCodeCombination(KeyCode.UP, KeyCodeCombination.CONTROL_DOWN));
	private static final Hotkey DEFAULT_VOLUMEDOWN = new Hotkey(new KeyCodeCombination(KeyCode.DOWN, KeyCodeCombination.CONTROL_DOWN));
	
	private static final Hotkey DEFAULT_RAPIDTUNESHELP = new Hotkey(KeyCode.F1);
	
	private Hotkey newPlaylist;
	private Hotkey importPlaylist;
	
	private Hotkey undo;
	private Hotkey redo;
	private Hotkey cut;
	private Hotkey copy;
	private Hotkey paste;
	private Hotkey delete;
	private Hotkey markAll;
	private Hotkey search;
	private Hotkey settings;
	
	private Hotkey next;
	private Hotkey previous;
	private Hotkey fastForward;
	private Hotkey rewind;
	private Hotkey shuffle;
	private Hotkey repeat;
	private Hotkey volumeUp;
	private Hotkey volumeDown;
	
	private Hotkey rapidtunesHelp;
	
	/**
	 * Default constructor for the HotkeySettings that simply calls
	 * the parent constructor and leaves all members uninitialized.
	 */
	public HotkeySettings() {
		super();
	}
	
	/**
	 * Constructor that initializes all members using the specified Properties
	 * object.
	 * 
	 * @param properties - Properties object to retrieve data from.
	 */
	public HotkeySettings(Properties properties) {
		super();
		
		this.newPlaylist = new Hotkey(properties.getProperty("newPlaylist"));
		this.importPlaylist = new Hotkey(properties.getProperty("importPlaylist"));
		
		this.undo = new Hotkey(properties.getProperty("undo"));
		this.redo = new Hotkey(properties.getProperty("redo"));
		this.cut = new Hotkey(properties.getProperty("cut"));
		this.copy = new Hotkey(properties.getProperty("copy"));
		this.paste = new Hotkey(properties.getProperty("paste"));
		this.delete = new Hotkey(properties.getProperty("delete"));
		this.markAll = new Hotkey(properties.getProperty("markAll"));
		this.search = new Hotkey(properties.getProperty("search"));
		this.settings = new Hotkey(properties.getProperty("settings"));
		
		this.next = new Hotkey(properties.getProperty("next"));
		this.previous = new Hotkey(properties.getProperty("previous"));
		this.fastForward = new Hotkey(properties.getProperty("fastForward"));
		this.rewind = new Hotkey(properties.getProperty("rewind"));
		this.shuffle = new Hotkey(properties.getProperty("shuffle"));
		this.repeat = new Hotkey(properties.getProperty("repeat"));
		this.volumeUp = new Hotkey(properties.getProperty("volumeUp"));
		this.volumeDown = new Hotkey(properties.getProperty("volumeDown"));
		
		this.rapidtunesHelp = new Hotkey(properties.getProperty("rapidtunesHelp"));
	}
	
	/**
	 * @return the newPlaylist
	 */
	public Hotkey getNewPlaylist() {
		return newPlaylist;
	}

	/**
	 * @param newPlaylist the newPlaylist to set
	 */
	public void setNewPlaylist(Hotkey newPlaylist) {
		this.newPlaylist = newPlaylist;
	}

	/**
	 * @return the importPlaylist
	 */
	public Hotkey getImportPlaylist() {
		return importPlaylist;
	}

	/**
	 * @param importPlaylist the importPlaylist to set
	 */
	public void setImportPlaylist(Hotkey importPlaylist) {
		this.importPlaylist = importPlaylist;
	}

	/**
	 * @return the undo
	 */
	public Hotkey getUndo() {
		return undo;
	}

	/**
	 * @param undo the undo to set
	 */
	public void setUndo(Hotkey undo) {
		this.undo = undo;
	}

	/**
	 * @return the redo
	 */
	public Hotkey getRedo() {
		return redo;
	}

	/**
	 * @param redo the redo to set
	 */
	public void setRedo(Hotkey redo) {
		this.redo = redo;
	}

	/**
	 * @return the cut
	 */
	public Hotkey getCut() {
		return cut;
	}

	/**
	 * @param cut the cut to set
	 */
	public void setCut(Hotkey cut) {
		this.cut = cut;
	}

	/**
	 * @return the copy
	 */
	public Hotkey getCopy() {
		return copy;
	}

	/**
	 * @param copy the copy to set
	 */
	public void setCopy(Hotkey copy) {
		this.copy = copy;
	}

	/**
	 * @return the paste
	 */
	public Hotkey getPaste() {
		return paste;
	}

	/**
	 * @param paste the paste to set
	 */
	public void setPaste(Hotkey paste) {
		this.paste = paste;
	}

	/**
	 * @return the delete
	 */
	public Hotkey getDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(Hotkey delete) {
		this.delete = delete;
	}

	/**
	 * @return the markAll
	 */
	public Hotkey getMarkAll() {
		return markAll;
	}

	/**
	 * @param markAll the markAll to set
	 */
	public void setMarkAll(Hotkey markAll) {
		this.markAll = markAll;
	}

	/**
	 * @return the search
	 */
	public Hotkey getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(Hotkey search) {
		this.search = search;
	}

	/**
	 * @return the settings
	 */
	public Hotkey getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(Hotkey settings) {
		this.settings = settings;
	}

	/**
	 * @return the next
	 */
	public Hotkey getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Hotkey next) {
		this.next = next;
	}

	/**
	 * @return the previous
	 */
	public Hotkey getPrevious() {
		return previous;
	}

	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(Hotkey previous) {
		this.previous = previous;
	}

	/**
	 * @return the fastForward
	 */
	public Hotkey getFastForward() {
		return fastForward;
	}

	/**
	 * @param fastForward the fastForward to set
	 */
	public void setFastForward(Hotkey fastForward) {
		this.fastForward = fastForward;
	}

	/**
	 * @return the rewind
	 */
	public Hotkey getRewind() {
		return rewind;
	}

	/**
	 * @param rewind the rewind to set
	 */
	public void setRewind(Hotkey rewind) {
		this.rewind = rewind;
	}

	/**
	 * @return the shuffle
	 */
	public Hotkey getShuffle() {
		return shuffle;
	}

	/**
	 * @param shuffle the shuffle to set
	 */
	public void setShuffle(Hotkey shuffle) {
		this.shuffle = shuffle;
	}

	/**
	 * @return the repeat
	 */
	public Hotkey getRepeat() {
		return repeat;
	}

	/**
	 * @param repeat the repeat to set
	 */
	public void setRepeat(Hotkey repeat) {
		this.repeat = repeat;
	}

	/**
	 * @return the volumeUp
	 */
	public Hotkey getVolumeUp() {
		return volumeUp;
	}

	/**
	 * @param volumeUp the volumeUp to set
	 */
	public void setVolumeUp(Hotkey volumeUp) {
		this.volumeUp = volumeUp;
	}

	/**
	 * @return the volumeDown
	 */
	public Hotkey getVolumeDown() {
		return volumeDown;
	}

	/**
	 * @param volumeDown the volumeDown to set
	 */
	public void setVolumeDown(Hotkey volumeDown) {
		this.volumeDown = volumeDown;
	}

	/**
	 * @return the rapidtunesHelp
	 */
	public Hotkey getRapidtunesHelp() {
		return rapidtunesHelp;
	}

	/**
	 * @param rapidtunesHelp the rapidtunesHelp to set
	 */
	public void setRapidtunesHelp(Hotkey rapidtunesHelp) {
		this.rapidtunesHelp = rapidtunesHelp;
	}

	/**
	 * Returns the path for the settings file.
	 * This path is used when saving / loading setting files.
	 * 
	 * @return - Path of the Settings file.
	 */
	@Override
	public String getPath() {
		return HotkeySettings.PATH;
	}

	/**
	 * Returns a Settings object converted into a Properties object.
	 * 
	 * @return - Properties object with all the Settings defined by the target Settings object.
	 */
	@Override
	public Properties toProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("newPlaylist", hotkeyToString(this.newPlaylist));
		properties.setProperty("importPlaylist", hotkeyToString(this.importPlaylist));
		
		properties.setProperty("undo", hotkeyToString(this.undo));
		properties.setProperty("redo", hotkeyToString(this.redo));
		properties.setProperty("cut", hotkeyToString(this.cut));
		properties.setProperty("copy", hotkeyToString(this.copy));
		properties.setProperty("paste", hotkeyToString(this.paste));
		properties.setProperty("delete", hotkeyToString(this.delete));
		properties.setProperty("markAll", hotkeyToString(this.markAll));
		properties.setProperty("search", hotkeyToString(this.search));
		properties.setProperty("settings", hotkeyToString(this.settings));
		
		properties.setProperty("next", hotkeyToString(this.next));
		properties.setProperty("previous", hotkeyToString(this.previous));
		properties.setProperty("fastForward", hotkeyToString(this.fastForward));
		properties.setProperty("rewind", hotkeyToString(this.rewind));
		properties.setProperty("shuffle", hotkeyToString(this.shuffle));
		properties.setProperty("repeat", hotkeyToString(this.repeat));
		properties.setProperty("volumeUp", hotkeyToString(this.volumeUp));
		properties.setProperty("volumeDown", hotkeyToString(this.volumeDown));
		
		properties.setProperty("rapidtunesHelp", hotkeyToString(this.rapidtunesHelp));
		
		return properties;
	}
	
	/**
	 * Returns the default Properties object defined by the target Settings object.
	 * 
	 * @return - Properties object with all the default properties.
	 */
	@Override
	public Properties getDefaultProperties() {
		Properties properties = new Properties();
		
		properties.setProperty("newPlaylist", hotkeyToString(DEFAULT_NEWPLAYLIST));
		properties.setProperty("importPlaylist", hotkeyToString(DEFAULT_IMPORTPLAYLIST));
		
		properties.setProperty("undo", hotkeyToString(DEFAULT_UNDO));
		properties.setProperty("redo", hotkeyToString(DEFAULT_REDO));
		properties.setProperty("cut", hotkeyToString(DEFAULT_CUT));
		properties.setProperty("copy", hotkeyToString(DEFAULT_COPY));
		properties.setProperty("paste", hotkeyToString(DEFAULT_PASTE));
		properties.setProperty("delete", hotkeyToString(DEFAULT_DELETE));
		properties.setProperty("markAll", hotkeyToString(DEFAULT_MARKALL));
		properties.setProperty("search", hotkeyToString(DEFAULT_SEARCH));
		properties.setProperty("settings", hotkeyToString(DEFAULT_SETTINGS));
		
		properties.setProperty("next", hotkeyToString(DEFAULT_NEXT));
		properties.setProperty("previous", hotkeyToString(DEFAULT_PREVIOUS));
		properties.setProperty("fastForward", hotkeyToString(DEFAULT_FASTFORWARD));
		properties.setProperty("rewind", hotkeyToString(DEFAULT_REWIND));
		properties.setProperty("shuffle", hotkeyToString(DEFAULT_SHUFFLE));
		properties.setProperty("repeat", hotkeyToString(DEFAULT_REPEAT));
		properties.setProperty("volumeUp", hotkeyToString(DEFAULT_VOLUMEUP));
		properties.setProperty("volumeDown", hotkeyToString(DEFAULT_VOLUMEDOWN));
		
		properties.setProperty("rapidtunesHelp", hotkeyToString(DEFAULT_RAPIDTUNESHELP));
		
		return properties;
	}
	
	/**
	 * Converts a Hotkey to String format depending on if it is a single key or a combination of 
	 * multiple keys.
	 * 
	 * @param hotkey - Hotkey to convert to String format.
	 * 
	 * @return - String with key or keys for the specified Hotkey.
	 */
	private String hotkeyToString(Hotkey hotkey) {
		return hotkey.isSingleKey() ? String.valueOf(hotkey.getSingleKeybind().getKey()) : String.valueOf(hotkey.getCombinationKeybind().getKeyCombination());
	}
}
