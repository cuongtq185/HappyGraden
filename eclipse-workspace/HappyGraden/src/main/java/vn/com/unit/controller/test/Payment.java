//package vn.com.unit.controller.test;
//
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.mservice.allinone.models.CaptureMoMoRequest;
//import com.mservice.allinone.models.CaptureMoMoResponse;
//import com.mservice.allinone.processor.allinone.CaptureMoMo;
//import com.mservice.shared.constants.Parameter;
//import com.mservice.shared.sharedmodels.Environment;
//import com.mservice.shared.sharedmodels.Environment.EnvTarget;
//import com.mservice.shared.sharedmodels.Environment.ProcessType;
//import com.mservice.shared.utils.Encoder;
//
//
//public class Payment {
//
//	EnvTarget target = EnvTarget.DEV;
//
//	ProcessType process = ProcessType.PAY_GATE;
//	Environment environment = Environment.selectEnv(target, process);
//
//	@RequestMapping("/momo")
//	public ModelAndView momoPayment(Model model) {
//
//		try {
//
//			CaptureMoMo captureMoMo = new CaptureMoMo(environment);
//
//			String orderId = String.valueOf(System.currentTimeMillis());
//			String requestId = String.valueOf(System.currentTimeMillis());
//			String amount = "9999";
//			String orderInfo = "Mua cái áo màu hường 9999 đ";
//
//			String returnUrl = "http://localhost:8080/callback";
//			String notifyUrl = "https://1dd10ec48399.ngrok.io/webhook/momo";
//
//			String extraData = "";
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
//		return new ModelAndView("index");
//	}
//
////	check-signature
//	@PostMapping(value = "/webhook/momo", produces = "application/json; charset=UTF-8")
//	public ResponseEntity<String> webhookMomo(@RequestParam Map<String, String> body) throws UnsupportedEncodingException {
//		body.get("partnerCode");
//		body.get("accessKey");
//		body.get("requestId");
//		body.get("amount");
//		body.get("orderId");
//		body.get("orderInfo");
//		body.get("orderType");
//		body.get("transId");
//		body.get("message");
//		body.get("localMessage");
//		body.get("payType");
//		body.get("responseTime");
//		body.get("errorCode");
//		body.get("extraData");
//		body.get("signature");
//
////		partnerCode=MOMOIZCR20200811&accessKey=2cX38RzzWw1Iuh5r&requestId=1597202201403&amount=9999&orderId=1597202201403&orderInfo=Mua cÃ¡i Ã¡o mÃ u hÆ°á»ng 9999 Ä&orderType=momo_wallet&transId=2323114460&message=Success&localMessage=ThÃ nh cÃ´ng&responseTime=2020-08-12 10:17:47&errorCode=0&payType=qr&extraData=
//		String rawData = Parameter.PARTNER_CODE + "=" + body.get("partnerCode")
//
//				+ "&" + Parameter.ACCESS_KEY + "=" + body.get("accessKey")
//
//				+ "&" + Parameter.REQUEST_ID + "=" + body.get("requestId")
//
//				+ "&" + Parameter.AMOUNT + "=" + body.get("amount")
//
//				+ "&" + Parameter.ORDER_ID + "=" + body.get("orderId")
//
//				+ "&" + Parameter.ORDER_INFO + "=" + new String(body.get("orderInfo").getBytes("ISO-8859-1"), "UTF-8")
//
//				+ "&" + Parameter.ORDER_TYPE + "=" + body.get("orderType")
//
//				+ "&" + Parameter.TRANS_ID + "=" + body.get("transId")
//
//				+ "&" + Parameter.MESSAGE + "=" + new String(body.get("message").getBytes("ISO-8859-1"), "UTF-8")
//
//				+ "&" + Parameter.LOCAL_MESSAGE + "=" + new String(body.get("localMessage").getBytes("ISO-8859-1"), "UTF-8")
//
//				+ "&" + "responseTime" + "=" + body.get("responseTime")
//
//				+ "&" + Parameter.ERROR_CODE + "=" + body.get("errorCode")
//
//				+ "&" + Parameter.PAY_TYPE + "=" + body.get("payType")
//
//				+ "&" + Parameter.EXTRA_DATA + "=" + body.get("extraData");
//
//		String signatureClient = null;
//		try {
//			signatureClient = Encoder.signHmacSHA256(rawData, environment.getPartnerInfo().getSecretKey());
//			if (signatureClient.equals(body.get("signature"))) {
//				// error code == 0 => success => bill success => transfer to shop
//				// error code != 0 => error
//				return ResponseEntity.ok(null);
//			}
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//	}
//
//	@RequestMapping("/paypal")
//	public ModelAndView payPalPayment(Model model) {
////		PayPalClient payPalClient = new PayPalClient();
////		PayPalHttpClient payPalHttpClient = payPalClient.client();
////		payPalHttpClient.
//
//		return null;
//	}
//}
