//package vn.com.unit.controller.payment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.mservice.allinone.models.CaptureMoMoRequest;
//import com.mservice.allinone.models.CaptureMoMoResponse;
//import com.mservice.allinone.processor.allinone.CaptureMoMo;
//import com.mservice.shared.sharedmodels.Environment;
//import com.mservice.shared.sharedmodels.Environment.EnvTarget;
//import com.mservice.shared.sharedmodels.Environment.ProcessType;
//
//import vn.com.unit.service.LogService;
//import vn.com.unit.service.PaymentService;
//import vn.com.unit.utils.CommonUtils;
//
//@Controller
//public class MomoPayment {
//
//	@Autowired
//	PaymentService paymentService;
//
//	@Autowired
//	LogService logService;
//
//	private EnvTarget target = EnvTarget.DEV;
//
//	private ProcessType process = ProcessType.PAY_GATE;
//	private Environment environment = Environment.selectEnv(target, process);
//
//	@PostMapping("/cart/payment/momo")
//	public ModelAndView momoPayment(Model model, @RequestParam(value = "address") String address) {
//
//		try {
//
//			String convert_address = CommonUtils.convertEncode(address);
//
//			Long bill_id = paymentService.createBill(convert_address);
//
//			String total = paymentService.calculateBillTotal(bill_id).toString();
//
//			CaptureMoMo captureMoMo = new CaptureMoMo(environment);
//
//			String orderId = bill_id.toString() + "T" + String.valueOf(System.currentTimeMillis());
//			String requestId = bill_id.toString() + orderId;
//			// => requestId.replace(orderId, ""); => bill_id
//			String amount = total;
//			String orderInfo = "Total : " + total + " Address: " + convert_address;
//
//			String BASE_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
//			String returnUrl =  BASE_URL + "/bill/" + bill_id.toString();
//			String notifyUrl = "https://2d5b673c6c4f.ngrok.io/webhook/momo";
//
//			String extraData = "";
//
//			String log = "/payment/momo start payment | " + "orderId=" + orderId + "&requestId=" + requestId
//					+ "&amount=" + amount + "&orderInfo=" + orderInfo + "&returnUrl=" + returnUrl + "&notifyUrl="
//					+ notifyUrl + "&extraData=" + extraData;
//			String type = "momo payment";
//			String author = this.getClass().getName();
//			logService.saveLog(log, type, author);
//
//			CaptureMoMoRequest captureMoMoRequest = captureMoMo.createPaymentCreationRequest(orderId, requestId, amount,
//					orderInfo, returnUrl, notifyUrl, extraData);
//
//			CaptureMoMoResponse captureMoMoResponse = captureMoMo.execute(captureMoMoRequest);
//			String payUrl = captureMoMoResponse.getPayUrl();
//
//			return new ModelAndView("redirect:" + payUrl);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return new ModelAndView("redirect:/cart");
//	}
//}
