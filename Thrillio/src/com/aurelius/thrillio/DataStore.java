package com.aurelius.thrillio;

import java.util.ArrayList;
import java.util.List;

import com.aurelius.thrillio.constants.BookGenre;
import com.aurelius.thrillio.constants.Gender;
import com.aurelius.thrillio.constants.MovieGenre;
import com.aurelius.thrillio.constants.UserType;
import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.User;
import com.aurelius.thrillio.entities.UserBookmark;
import com.aurelius.thrillio.managers.BookmarkManager;
import com.aurelius.thrillio.managers.UserManager;
import com.aurelius.thrillio.util.IOUtil;

public class DataStore {

//	public static final int USER_BOOKMARK_LIMIT = 5;
//	public static final int BOOKMARK_COUNT_PER_TYPE = 5;
//	public static final int BOOKMARK_TYPES_COUNT = 3;
//	public static final int TOTAL_USER_COUNT = 5;

	private static List<User> users = new ArrayList<>();
	private static List<List<Bookmark>> bookmarks = new ArrayList<>();
	private static List<UserBookmark> userBookmarks = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadBooks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
					"", Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5].toUpperCase()),
					Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] cast = values[3].split(",");
			String[] directors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1],
					"", Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5].toUpperCase()),
					Double.parseDouble(values[6]));
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadWebLinks() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Web-Link.txt");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]),
					values[1], values[2], values[3]);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadUsers() {
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User.txt");
		for (String row : data) {
			String[] values = row.split("\t");
			Gender gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANSGENDER;
			}

			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2],
					values[3], values[4], gender, UserType.valueOf(values[6].toUpperCase()));
			users.add(user);
		}
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);
	}
}
