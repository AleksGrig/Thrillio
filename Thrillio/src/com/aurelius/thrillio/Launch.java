package com.aurelius.thrillio;

import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.User;
import com.aurelius.thrillio.managers.BookmarkManager;
import com.aurelius.thrillio.managers.UserManager;

public class Launch {

	private static User[] users;
	private static Bookmark[][] bookmarks;

	private static void loadData() {
		System.out.println("Loading data...");
		DataStore.loadData();

		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();

		System.out.println("Printing data...");
		printUserData();
		printBookmarkData();
	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	private static void startBookmarking() {
		System.out.println("\nBookmarking...");
		for (User user : users) {
			View.bookmark(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		startBookmarking();
	}
}
