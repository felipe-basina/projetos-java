package com.github.youmoo.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.youmoo.spring.domain.User;
import com.github.youmoo.spring.helper.JsonBuilder;
import com.github.youmoo.spring.service.UserService;

/**
 * @autor youmoo
 * @since 2014-06-14 下午5:07
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/list")
	public String list() {
		return "user/list";
	}

	/** Retorna um mapa com objetos **/
	@RequestMapping(value = "/getAll", produces = "application/json", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody Map<String, Object> userGetAllObjectReturn(
			@RequestParam(value = "param", defaultValue = "hi-angular") String param) {
		System.out.println("\n\tUser get all!!".concat("\n\tparam = "
				.concat(param)));

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<User> usuarios = null;
		boolean ok = true;
		
		try {
			usuarios = userService.listUser();
			map.put("users", usuarios);
		} catch (Exception ex) {
			System.out.println("\n\tErro: " + ex.getMessage());
			ok = false;
			map.put("msg", "Nothing was found :(");
		}
		
		map.put("ok", ok);

		return map;
	}

	/** Retorna uma String **/
	@RequestMapping(value = "/getAll-string", method = RequestMethod.GET)
	public @ResponseBody String userGetAllStringReturn(
			@RequestParam(value = "param", defaultValue = "hi-angular") String param) {
		System.out.println("\n\tUser get all!!".concat("\n\tparam = "
				.concat(param)));

		String retorno = "{\"msg\":\"Nothing was found :(\"}";

		try {
			retorno = new JsonBuilder().put("ok", true)
					.put("users", userService.list()).build();
		} catch (Exception ex) {
			System.out.println("\n\t ->>>> Erro: " + ex.getMessage());
		}

		return retorno;
	}
}