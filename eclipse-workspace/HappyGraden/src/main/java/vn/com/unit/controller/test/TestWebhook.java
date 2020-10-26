package vn.com.unit.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mservice.allinone.models.CaptureMoMoRequest;
import com.mservice.allinone.models.CaptureMoMoResponse;
import com.mservice.allinone.processor.allinone.CaptureMoMo;
import com.mservice.shared.sharedmodels.Environment;
import com.mservice.shared.sharedmodels.Environment.EnvTarget;
import com.mservice.shared.sharedmodels.Environment.ProcessType;

@Controller
public class TestWebhook {

	EnvTarget target = EnvTarget.DEV;

	ProcessType process = ProcessType.PAY_GATE;
	Environment environment = Environment.selectEnv(target, process);

	@RequestMapping("/momo")
	public ModelAndView momoPayment(Model model) {

		try {

			CaptureMoMo captureMoMo = new CaptureMoMo(environment);

			String orderId = String.valueOf(System.currentTimeMillis());
			String requestId = String.valueOf(System.currentTimeMillis());
			String amount = "9999";
			String orderInfo = "Mua cái áo màu hường 9999 đ";

			String returnUrl = "http://localhost:8080/callback";
			String notifyUrl = "https://1dd10ec48399.ngrok.io/webhook/momo";

			String extraData = "";

			CaptureMoMoRequest captureMoMoRequest = captureMoMo.createPaymentCreationRequest(orderId, requestId, amount,
					orderInfo, returnUrl, notifyUrl, extraData);

			CaptureMoMoResponse captureMoMoResponse = captureMoMo.execute(captureMoMoRequest);
			String payUrl = captureMoMoResponse.getPayUrl();

			return new ModelAndView("redirect:" + payUrl);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("index");
	}
	
}
