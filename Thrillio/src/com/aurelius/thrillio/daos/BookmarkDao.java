package com.aurelius.thrillio.daos;

import java.util.List;

import com.aurelius.thrillio.DataStore;
import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.UserBookmark;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}
}
