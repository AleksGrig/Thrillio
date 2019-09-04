package com.aurelius.thrillio;

import java.util.List;

import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.User;
import com.aurelius.thrillio.managers.BookmarkManager;
import com.aurelius.thrillio.managers.UserManager;

public class Launch {

	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;

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
		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				System.out.println(bookmark);
			}
		}
	}

	private static void start() {
		for (User user : users) {
			View.browse(user, bookmarks);
		}
	}

	public static void main(String[] args) {
		loadData();
		start();
	}
}
