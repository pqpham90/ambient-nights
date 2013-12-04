package com.cs371mproject.ambientnights.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Menu {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<Theme> ITEMS = new ArrayList<Theme>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, Theme> ITEM_MAP = new HashMap<String, Theme>();

	static {
		// Add the themes
		addItem(new Theme("1", "Jungle"));
		addItem(new Theme("2", "Ocean"));
		addItem(new Theme("3", "Rain"));
		addItem(new Theme("4", "River"));
	}

	private static void addItem(Theme item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class Theme {
		public String id;
		public String content;

		public Theme(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
