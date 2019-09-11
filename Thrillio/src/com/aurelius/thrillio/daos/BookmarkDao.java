package com.aurelius.thrillio.daos;

import java.util.ArrayList;
import java.util.List;

import com.aurelius.thrillio.DataStore;
import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.UserBookmark;
import com.aurelius.thrillio.entities.WebLink;

public class BookmarkDao {

	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);
	}

	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<Bookmark> allWebLinks = DataStore.getBookmarks().get(0);

		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink) bookmark);
		}
		return result;
	}

	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
		List<WebLink> result = new ArrayList<>();
		List<WebLink> allWebLinks = getAllWebLinks();
		for (WebLink webLink : allWebLinks) {
			if (webLink.getDownloadStatus().equals(downloadStatus)) {
				result.add(webLink);
			}
		}
		return result;
	}
}
