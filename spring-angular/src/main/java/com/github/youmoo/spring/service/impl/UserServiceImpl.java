package com.github.youmoo.spring.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.youmoo.spring.converter.Converters;
import com.github.youmoo.spring.dao.UserDao;
import com.github.youmoo.spring.domain.User;
import com.github.youmoo.spring.service.UserService;
import com.github.youmoo.spring.vo.UserVo;

/**
 * @autor youmoo
 * @since 2014-06-14 下午5:34
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<UserVo> list() {
		List<User> users = userDao.select(Collections.EMPTY_MAP);

		return Converters.convert(users, UserVo.class);
	}
	
	@Override
	public List<User> listUser() {
		return userDao.select(Collections.EMPTY_MAP);
	}
}
