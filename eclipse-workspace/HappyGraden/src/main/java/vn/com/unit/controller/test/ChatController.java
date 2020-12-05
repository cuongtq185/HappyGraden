//package vn.com.unit.controller.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.session.SessionInformation;
////import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.context.request.RequestAttributes;
////import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.servlet.ModelAndView;
//
//import vn.com.unit.entity.Bill;
//import vn.com.unit.service.BillService;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.Locale;
//import java.util.Scanner;
//
//@Controller
//public class ChatController {
//
////	@Autowired
////    private SessionRegistry sessionRegistry;
//	
//	@GetMapping("/test/chat")
//	public ModelAndView testChat(Model model) {
//
//		return new ModelAndView("chat");
//	}
//	
//	@GetMapping("/test/session")
//	public ModelAndView testSession(Model model) {
//		
//	    Scanner sc = new Scanner(System.in);
//	    sc.nextLong();
//	    sc.close();
////		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//		
////		String current_session_id = requestAttributes.getSessionId();
//		
////		SessionInformation sessionInformation = sessionRegistry.getSessionInformation(current_session_id);
//		
////		(HttpSession) application.getAttribute("vF5_lvmxDtPf2ERO09DjctoGxBbPm1MhBl2ib1BM.desktop-c3292p7");
//		
//		return new ModelAndView("chat");
//	}
//	
//	@Autowired
//	BillService billService;
//	@GetMapping("/test/bill")
//	public ModelAndView testBill(Model model) {
//		Bill b = billService.test(1L);
//		Bill c = billService.test(null);
//		return new ModelAndView("index");
//	}
//	
//}
