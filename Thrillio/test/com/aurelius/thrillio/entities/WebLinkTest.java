package com.aurelius.thrillio.entities;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aurelius.thrillio.managers.BookmarkManager;

class WebLinkTest {

	@Test
	void testIsKidFreindlyEligible() {
		// Test1: porn in url -- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");
		boolean isKidFreindlyEligible = weblink.isKidFreindlyEligible();
		assertFalse(isKidFreindlyEligible, "For porn in url isKidFreindlyEligible() must return false");

		// Test2: porn in title -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFreindlyEligible = weblink.isKidFreindlyEligible();
		assertFalse(isKidFreindlyEligible, "For porn in title isKidFreindlyEligible() must return false");

		// Test3: adult in host -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.adult.com");

		isKidFreindlyEligible = weblink.isKidFreindlyEligible();
		assertFalse(isKidFreindlyEligible, "For adult in host isKidFreindlyEligible() must return false");

		// Test4: adult in url, but not in host -- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFreindlyEligible = weblink.isKidFreindlyEligible();
		assertTrue(isKidFreindlyEligible, "For adult in url isKidFreindlyEligible() must return true");

		// Test5: adult in title only -- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFreindlyEligible = weblink.isKidFreindlyEligible();
		assertTrue(isKidFreindlyEligible, "For adult in title only isKidFreindlyEligible() must return true");
	}

}
