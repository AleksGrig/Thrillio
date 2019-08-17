package com.aurelius.thrillio.daos;

import com.aurelius.thrillio.DataStore;
import com.aurelius.thrillio.entities.Bookmark;

public class BookmarkDao {

	public Bookmark[][] getBookmarks() {
		return DataStore.getBookmarks();
	}
}
