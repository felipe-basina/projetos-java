package com.github.youmoo.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.youmoo.spring.command.AjaxCommand;
import com.github.youmoo.spring.command.Prepare;
import com.github.youmoo.spring.command.QueryParam;
import com.github.youmoo.spring.command.ValidationException;
import com.github.youmoo.spring.command.Validator;
import com.github.youmoo.spring.helper.JsonBuilder;
import com.github.youmoo.spring.helper.SpringUtils;

/**
 * 所有ajax请求，都由此类处理
 *
 * @autor youmoo
 * @since 2014-06-14 下午5:14
 */
@Controller
@RequestMapping("/ajax")
public class AjaxController {

	@ResponseBody
	@RequestMapping(value = "/execute.do")
	public String execute(HttpServletRequest request, String command) {
		if (command != null && !command.trim().equals("")) {
			AjaxCommand ajaxCommand = (AjaxCommand) SpringUtils.getBean("ajax_"
					+ command.toLowerCase());
			if (ajaxCommand != null) {
				if (ajaxCommand instanceof Validator) {
					try {
						((Validator) ajaxCommand).validate(request);
					} catch (ValidationException e) {
						return e.getErrorMsg();
					}
				}
				QueryParam queryParam = null;
				if (ajaxCommand instanceof Prepare) {
					queryParam = ((Prepare) ajaxCommand).prepare(request);
				}
				return ajaxCommand.execute(null, request, queryParam);
			}
		}
		return new JsonBuilder().put("ok", false).build();
	}
}
