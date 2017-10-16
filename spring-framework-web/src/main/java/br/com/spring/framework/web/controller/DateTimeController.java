package br.com.spring.framework.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DateTimeController {

	@RequestMapping("/datetime")
	public ModelAndView showDateTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("datetime", "Data/Hora atual [" + sdf.format(new Date()) + "]");

		return new ModelAndView("datetime", map);
	}
}
