package com.tranquyet.dao;

import java.util.List;

public interface UserDao<T> {

	List<T> listUser();

	T get(int id);

	void insert(T t);

	void delete(int id);

	void edit(T t);

	boolean login(T t);

}
