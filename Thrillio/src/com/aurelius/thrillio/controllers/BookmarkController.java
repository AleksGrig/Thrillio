package com.aurelius.thrillio.controllers;

import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.User;
import com.aurelius.thrillio.managers.BookmarkManager;

public class BookmarkController {

	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
	}

	public void setKidFriendlyStatus(String kidFriendlyStatus, Bookmark bookmark) {
		BookmarkManager.getInstance().setKidFriendlyStatus(kidFriendlyStatus, bookmark);
	}
}
