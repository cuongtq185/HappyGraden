package vn.com.unit.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mservice.allinone.models.RefundMoMoRequest;
import com.mservice.allinone.processor.allinone.RefundMoMo;
import com.mservice.shared.sharedmodels.Environment;
import com.mservice.shared.sharedmodels.Environment.EnvTarget;
import com.mservice.shared.sharedmodels.Environment.ProcessType;

@Controller
public class TestRefund {

	EnvTarget target = EnvTarget.DEV;

//	ProcessType process = ProcessType.PAY_REFUND;
	ProcessType process = ProcessType.PAY_GATE;
	Environment environment = Environment.selectEnv(target, process);
	
	@SuppressWarnings("unused")
	@RequestMapping("/refund")
	public void test() {
		
//		partnerCode=MOMOIZCR20200811&accessKey=2cX38RzzWw1Iuh5r&requestId=1597287204398&amount=9999&orderId=1597287204398&orderInfo=Mua cái áo màu hường 9999 đ&orderType=momo_wallet&transId=2323220355&message=Success&localMessage=Thành công&responseTime=2020-08-13 09:53:52&errorCode=0&payType=qr&extraData=
		RefundMoMo refundMoMo = new RefundMoMo(environment);
		String requestId = String.valueOf(System.currentTimeMillis());
//		String orderId = "1597287204398";
		String orderId = String.valueOf(System.currentTimeMillis());
		String amount = "9999";
		String transId = "2323220355";
		RefundMoMoRequest refundMoMoRequest = refundMoMo.createRefundRequest(requestId, orderId, amount, transId);
		
		try {
			Environment env = environment;
			RefundMoMo.process(env, requestId, orderId, amount, transId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		try {
			refundMoMo.execute(refundMoMoRequest);
		} catch (MoMoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
