package com.aurelius.thrillio.daos;

import java.util.List;

import com.aurelius.thrillio.DataStore;
import com.aurelius.thrillio.entities.User;

public class UserDao {

	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
