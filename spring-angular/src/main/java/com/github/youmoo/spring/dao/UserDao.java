package com.github.youmoo.spring.dao;

import java.util.List;
import java.util.Map;

import com.github.youmoo.spring.domain.User;

/**
 * @autor youmoo
 * @since 2014-06-14 下午5:28
 */
public interface UserDao {
	public List<User> select(Map<String, Object> params);
}
