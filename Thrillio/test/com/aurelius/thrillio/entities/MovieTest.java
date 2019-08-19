package com.aurelius.thrillio.entities;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aurelius.thrillio.constants.MovieGenre;
import com.aurelius.thrillio.managers.BookmarkManager;

public class MovieTest {

	@Test
	void testIsKidFreindlyEligible() {
		// Test1: horror movie -- return false
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		boolean isKidFrienlyEligible = movie.isKidFreindlyEligible();
		Assert.assertFalse(isKidFrienlyEligible, "For Horror genre isKidFreindlyEligible() should return false");

		// Test2: thriller movie -- return false
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);
		isKidFrienlyEligible = movie.isKidFreindlyEligible();
		Assert.assertFalse(isKidFrienlyEligible, "For Thrillers genre isKidFreindlyEligible() should return false");

		// Test3: other genres should return true
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", "", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.CLASSICS,
				8.5);
		isKidFrienlyEligible = movie.isKidFreindlyEligible();
		Assert.assertTrue(isKidFrienlyEligible, "For Classics genre isKidFreindlyEligible() should return true");
	}
}
