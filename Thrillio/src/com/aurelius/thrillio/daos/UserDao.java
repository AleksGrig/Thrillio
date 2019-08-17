package com.aurelius.thrillio.daos;

import com.aurelius.thrillio.DataStore;
import com.aurelius.thrillio.entities.User;

public class UserDao {

	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
