package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Log;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.LogService;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")


public class AdminController {
	@Autowired
	private LogService logService;
	@RequestMapping("/admin")
	public ModelAndView home(Model model) {

		return new ModelAndView("admin");
	}
	
	@GetMapping("/admin/log/list")
	public ModelAndView ShopList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
			HttpServletRequest request) {

		int totalitems = logService.countAllLog();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);

		PageRequest pageable = new PageRequest(page, limit, totalitems, totalpages);

		List<Log> logs = logService.findAllLog(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("logs", logs);
		model.addAttribute("pageable", pageable);

		return new ModelAndView("admin/log/log-table");
	}

}
