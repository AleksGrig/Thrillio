package com.aurelius.thrillio.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import com.aurelius.thrillio.daos.BookmarkDao;
import com.aurelius.thrillio.entities.Book;
import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.Movie;
import com.aurelius.thrillio.entities.User;
import com.aurelius.thrillio.entities.UserBookmark;
import com.aurelius.thrillio.entities.WebLink;
import com.aurelius.thrillio.util.HttpConnect;
import com.aurelius.thrillio.util.IOUtil;

public class BookmarkManager {

	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast,
			String[] directors, String genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setProfileUrl(profileUrl);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);
		return movie;
	}

	public Book createBook(long id, String title, String profileUrl, int publicationYear, String publisher,
			String[] authors, String genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setProfileUrl(profileUrl);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);
		return book;
	}

	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);
		return weblink;
	}

	public List<List<Bookmark>> getBookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		if (bookmark instanceof WebLink) {
			try {
				String url = ((WebLink) bookmark).getUrl();
				if (!url.endsWith(".pdf")) {
					String webPage = HttpConnect.download(url);
					if (webPage != null) {
						IOUtil.write(webPage, bookmark.getId());
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		dao.saveUserBookmark(userBookmark);
	}

	public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);
		System.out.println(
				"Kid-friendly status: " + kidFriendlyStatus + ", marked by " + user.getEmail() + ", " + bookmark);
	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		System.out.println("Data to be shared: ");
		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());
		} else if (bookmark instanceof WebLink) {
			System.out.println(((WebLink) bookmark).getItemData());
		}
	}
}
