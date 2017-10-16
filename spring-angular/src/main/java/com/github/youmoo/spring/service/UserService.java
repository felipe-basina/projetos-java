package com.github.youmoo.spring.service;

import java.util.List;

import com.github.youmoo.spring.domain.User;
import com.github.youmoo.spring.vo.UserVo;

/**
 * @autor youmoo
 * @since 2014-06-14 下午5:34
 */
public interface UserService {

	public List<UserVo> list();
	
	public List<User> listUser();

}
