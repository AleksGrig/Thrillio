package com.aurelius.thrillio.entities;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aurelius.thrillio.constants.BookGenre;
import com.aurelius.thrillio.managers.BookmarkManager;

public class BookTest {

	@Test
	public void isKidFriendlyEligible() {
		// Test1: philosophy books return false
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		boolean isKidFriendlyEligible = book.isKidFreindlyEligible();
		Assert.assertFalse(isKidFriendlyEligible, "For Philosophy genre isKidFreindlyEligible() should return false");

		// Test2: self help books return false
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		isKidFriendlyEligible = book.isKidFreindlyEligible();
		Assert.assertFalse(isKidFriendlyEligible, "For Self help genre isKidFreindlyEligible() should return false");

		// Test3: other books return true
		book = BookmarkManager.getInstance().createBook(4000, "Walden", "", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.BIOGRAPHY, 4.3);
		isKidFriendlyEligible = book.isKidFreindlyEligible();
		Assert.assertTrue(isKidFriendlyEligible, "For other genre isKidFreindlyEligible() should return true");
	}
}
