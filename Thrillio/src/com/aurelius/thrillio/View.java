package com.aurelius.thrillio;

import java.util.List;

import com.aurelius.thrillio.constants.KidFriendlyStatus;
import com.aurelius.thrillio.constants.UserType;
import com.aurelius.thrillio.controllers.BookmarkController;
import com.aurelius.thrillio.entities.Bookmark;
import com.aurelius.thrillio.entities.User;
import com.aurelius.thrillio.partner.Shareable;

public class View {

	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items...");
		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
					boolean isBookmarked = getBookmarkDecision(bookmark);
					if (isBookmarked) {
						BookmarkController.getInstance().saveUserBookmark(user, bookmark);
						System.out.println("New item bookmarked --> " + bookmark);
					}

				if ((user.getUserType().equals(UserType.EDITOR))
						|| (user.getUserType().equals(UserType.CHIEF_EDITOR))) {
					// Mark as kid-friendly
					if (bookmark.isKidFreindlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}

						// Sharing kid-friendly bookmark
						if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
								&& bookmark instanceof Shareable) {
							boolean isShared = getShareDecision();
							if (isShared) {
								BookmarkController.getInstance().share(user, bookmark);
							}
						}
					}
				}
			}
		}
	}

	private static boolean getShareDecision() {
		return Math.random() < 0.25 ? true : false;
	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
		double random = Math.random();
		return random < 0.4 ? KidFriendlyStatus.APPROVED
				: (random >= 0.4 && random < 0.6) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.25 ? true : false;
	}
}
