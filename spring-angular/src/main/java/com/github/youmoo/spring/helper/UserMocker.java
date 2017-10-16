package com.github.youmoo.spring.helper;

import java.util.List;

import com.github.youmoo.spring.domain.User;

/**
 * 对象生成辅助类
 *
 * @autor youmoo
 * @since 2014-06-14 下午6:14
 */
public class UserMocker {
	public static final String[] NAMES = { "Edward", "Jack", "Ben", "Jane" };

	public static User mock(Integer id, String username, Integer age) {
		return new User(id, username, age);
	}

	public static List<User> mock() {
		List<User> users = ObjectFactory.newArrayList();
		for (int i = 0; i < NAMES.length; i++) {
			users.add(mock(i, NAMES[i], NAMES[i].hashCode() % 25));
		}
		return users;
	}
}
