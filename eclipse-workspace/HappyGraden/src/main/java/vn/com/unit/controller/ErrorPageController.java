package vn.com.unit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorPageController {

	@GetMapping("/403")
	public ModelAndView forbidden() {
		return new ModelAndView("/403");
	}

	@GetMapping("/404")
	public ModelAndView notFound() {
		return new ModelAndView("/404");
	}

}
