package vn.com.unit.controller.test;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import vn.com.unit.service.UploadImgService;

@Controller
public class TestUploadImg {

	@GetMapping("/test/upload")
	public ModelAndView uploadFile(Model model) {

		return new ModelAndView("upload");
	}

//	@PostMapping("/test/upload")
//	public ModelAndView saveFile(Model model, @RequestBody Map<String, String> json, @RequestParam("file") MultipartFile file) {
//		
//		
//		return null;
//	}

//	, @RequestBody String json
	@PostMapping("/test/upload")
	public ModelAndView saveFile(Model model, @RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) throws IllegalStateException, IOException {
/*
//		String yourSystemPath = System.getProperty("jboss.home.url") + "/want/to/save/here";
//		File fileToSave = new File(yourSystemPath,"foo.bar");  
//		Writer out = new OutputStreamWriter(new FileOutputStream(fileToSave), "UTF-8");
//		
//		String filePath = request.getServletContext().getRealPath("/");
		
		String time = String.valueOf(System.currentTimeMillis());
		
		String multipart_file_name = time + "_" + multipartFile.getOriginalFilename();
		
//		String filePath = System.getProperty("jboss.server.data.dir") + multipart_file_name; // jboss-eap-7.3\\standalone\\data
//		
//		multipartFile.transferTo(new File(filePath));
		
		String file_path = request.getServletContext().getRealPath("/") + "/" + multipart_file_name;
		
		File temp_file = new File(file_path);
		
		multipartFile.transferTo(temp_file);
		
//		InputStream file_input_stream = multipartFile.getInputStream();

//		Cloudinary cloudinary = new Cloudinary();
		Map<String, String> config = new HashMap<String, String>();
		config.put("cloud_name", "sodepr");
		config.put("api_key", "434272393799268");
		config.put("api_secret", "0TRwfILbqMsDPXyT_r8QCdsxpIM");
		Cloudinary cloudinary = new Cloudinary(config);

//		Map params = ObjectUtils.asMap("public_id", "my_folder/my_sub_folder/my_dog", "overwrite", true,
//				"notification_url", "https://mysite/notify_endpoint", "resource_type", "video");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("folder", "p2po");
//		params.put("public_id", multipart_file_name);
		params.put("public_id", time);
		params.put("overwrite", "false");
//		params.put("notification_url", "https://mysite/notify_endpoint");
//		params.put("resource_type", "https://mysite/notify_endpoint");
		
//		params.put("transformation", new Transformation().width(1920).quality("auto").fetchFormat("auto").crop("scale"));
		
//		cloudinary.uploader().upload("sample_file.jpg",
//				  ObjectUtils.asMap(
//				    "use_filename", "true", 
//				    "unique_filename", "false"));
		
//		Map uploadResult = cloudinary.uploader().upload(new File("doc.mp4"), params);
		
//		Map uploadResult = cloudinary.uploader().upload(new File("doc.mp4"), ObjectUtils.emptyMap());
		
		Map uploadResult = cloudinary.uploader().upload(temp_file, params);

		String url = (String) uploadResult.get("url");
		
		url = url.replace("/image/upload/", "/image/upload/q_auto/w_200,h_200/");
		
		temp_file.delete();
*/

//		String url = UploadImgService.uploadCloudinary(multipartFile);
		String url = UploadImgService.uploadCloudinary(multipartFile, 150, 150);
		
		return new ModelAndView("redirect:" + url);
	}

}
