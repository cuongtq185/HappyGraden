package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.entity.Origin;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.OriginService;

@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminOriginController {
		
	@Autowired
	private OriginService originService;
	
	@GetMapping("/admin/origin/list")
	public ModelAndView originList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  originService.countAllOrigin();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Origin> pageable = new PageRequest<Origin>(page, limit, totalitems, totalpages);
		List<Origin> origins = originService.findOriginPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(origins);
		return new ModelAndView("origin");
	}
	
	
	
	@GetMapping("/admin/origin/ajax-list")
	public ModelAndView originAjaxList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  originService.countAllOrigin();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Origin> pageable = new PageRequest<Origin>(page, limit, totalitems, totalpages);
		List<Origin> origins = originService.findOriginPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(origins);
		return new ModelAndView("origin");
	}
	
	
	
	@GetMapping("/admin/origin/add")
	public ModelAndView categoryAdd(Model model,
			HttpServletRequest request) {

		return new ModelAndView("origin-add");
	}
	
	
	
	
	@GetMapping("/admin/origin/edit/{origin_id}")
	public ModelAndView originEdit(@PathVariable("origin_id") long origin_id, Model model,
			HttpServletRequest request) {
		Origin origin = originService.findOriginById(origin_id);
		model.addAttribute("origin",origin);
		return new ModelAndView("origin-edit");
	}
	
	@PostMapping("/admin/origin/add")
	@ResponseBody
	public ResponseEntity<String> createOrigin(@RequestBody Origin origin, Model model) {
		if (originService.findOriginByName(origin.getOriginName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Origin already exists.\" }");
			}
		if (origin.getOriginName() == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Name cannot be empty.\" }");
		}
		Origin check_origin = originService.createOrigin(origin);
		/*
		 * if(check_origin != null) { return ResponseEntity.status(HttpStatus.OK).
		 * body("{\"msg\" : \"Origin category successfully.\" }"); }
		 */
		/*
		 * return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		 * .body("{ \"msg\" : \"You can't create an origin right now. Try again later\" }"
		 * );
		 */
		return ResponseEntity.ok("{ \"msg\" : \"Create origin successfully.\" }");
	}
	@PutMapping("/admin/origin/edit")
	@ResponseBody
	public ResponseEntity<String> editCategory(@RequestBody Origin origin, Model model) {
		
		
		if (originService.findOriginByName(origin.getOriginName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Origin already exists.\" }");}
		
		if (origin.getOriginName() == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Name cannot be empty.\" }");
		}
		originService.updateOriginById(origin);

		return ResponseEntity.ok("{ \"msg\" : \"update origin successfully.\" }");
	}
	
	@DeleteMapping("/admin/origin/delete/{origin_id}")
	public ResponseEntity<Boolean> deleteOrigin(Model model, @PathVariable("origin_id") Long origin_id,
			HttpServletRequest request) {
		originService.deleteOriginById(origin_id);
		return  ResponseEntity.ok(null);
	}
	
}
