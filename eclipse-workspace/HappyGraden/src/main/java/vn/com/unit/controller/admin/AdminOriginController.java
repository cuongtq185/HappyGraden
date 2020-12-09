package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import vn.com.unit.entity.Category;
import vn.com.unit.entity.Origin;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.CategoryService;
import vn.com.unit.service.OriginService;

public class AdminOriginController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OriginService originService;
	
	@GetMapping("/admin/origin/list")
	public ModelAndView originList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
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
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
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
	
	
	
	
	@GetMapping("/admin/category/edit/{category_id}")
	public ModelAndView categoryEdit(@PathVariable("category_id") long category_id, Model model,
			HttpServletRequest request) {
		Category category = categoryService.findCategoryById(category_id);
		model.addAttribute("category",category);
		return new ModelAndView("category-edit");
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
		if(check_origin != null) {
			return ResponseEntity.status(HttpStatus.OK).body("{\"msg\" : \"Origin category successfully.\" }");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body("{ \"msg\" : \"You can't create an origin right now. Try again later\" }");
		
	}
	@PutMapping("/admin/category/edit")
	@ResponseBody
	public ResponseEntity<String> editCategory(@RequestBody Category category, Model model) {
		
		
		if (categoryService.findCategoryByName(category.getCategoryName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Category already exists.\" }");}
		
		if (category.getCategoryName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Name cannot be empty.\" }");
		}
		categoryService.updateCategoryById(category);

		return ResponseEntity.ok("{ \"msg\" : \"update category successfully.\" }");
	}
	@DeleteMapping("/admin/category/delete/{category_id}")
	public ResponseEntity<Boolean> AdminDisableCategory(Model model, @PathVariable("category_id") Long category_id,
			HttpServletRequest request) {
		categoryService.deleteCategoryById(category_id);
		return  ResponseEntity.ok(null);
	}
	
}
