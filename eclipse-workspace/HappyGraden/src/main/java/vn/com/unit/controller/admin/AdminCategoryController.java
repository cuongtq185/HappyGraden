package vn.com.unit.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import vn.com.unit.entity.Category;
import vn.com.unit.pageable.PageRequest;
import vn.com.unit.service.CategoryService;

@Controller
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminCategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/admin/category/list")
	public ModelAndView accountList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  categoryService.countAllCategory();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Category> pageable = new PageRequest<Category>(page, limit, totalitems, totalpages);
		List<Category> categories = categoryService.findCategoryPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(categories);
		return new ModelAndView("category");
	}
	
	
	
	@GetMapping("/admin/category/ajax-list")
	public ModelAndView accountAjaxList(Model model,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int limit,
			HttpServletRequest request) {

		int totalitems =  categoryService.countAllCategory();
		int totalpages = (int) Math.ceil((double) totalitems / (double) limit);
		PageRequest<Category> pageable = new PageRequest<Category>(page, limit, totalitems, totalpages);
		List<Category> categories = categoryService.findCategoryPageable(pageable.getLimit(), pageable.getOffset());
		model.addAttribute("pageable", pageable);
		pageable.setData(categories);
		return new ModelAndView("category");
	}
	
	
	
	@GetMapping("/admin/category/add")
	public ModelAndView categoryAdd(Model model,
			HttpServletRequest request) {

		return new ModelAndView("category-add");
	}
		
	
	@GetMapping("/admin/category/edit/{category_id}")
	public ModelAndView categoryEdit(@PathVariable("category_id") long category_id, Model model,
			HttpServletRequest request) {
		Category category = categoryService.findCategoryById(category_id);
		model.addAttribute("category",category);
		return new ModelAndView("category-edit");
	}
	
	@PostMapping("/admin/category/add")
	@ResponseBody
	public ResponseEntity<String> createCategory(@RequestBody Category category, Model model) {
		if (categoryService.findCategoryByName(category.getCategoryName()) != null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Category already exists.\" }");
			}
		if (category.getCategoryName() == "") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Name cannot be empty.\" }");
		}
		 categoryService.createCategory(category);
		/*
		 * if(check_category != null) { return ResponseEntity.status(HttpStatus.OK).
		 * body("{\"msg\" : \"Create category successfully.\" }"); }
		 */
		 return ResponseEntity.ok("{ \"msg\" : \"Create category successfully.\" }");
		
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
	
	
//	@PostMapping("/admin/category/addnew")
//	@ResponseBody
//	public ResponseEntity<String> createNewCategory(@RequestBody categoryEntity category, Model model) {
//		if (categoryService.findCategoryByName(category.getName()) != null) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Category already exists\" }");
//			}
//		if (category.getName() == "") {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{ \"msg\" : \"Name cannot be empty\" }");
//		}
//		categoryEntity result = categoryService.createNewCategory(category);
//		if (result.getId() != null) {
//			return ResponseEntity.ok("{ \"id\" : " + result.getId() + ", \"msg\" : \"Create category successfully\" }");
//		}
//		
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body("{ \"msg\" : \"You can't create an category right now. Try again later\" }");
//		
//	}
	
}
