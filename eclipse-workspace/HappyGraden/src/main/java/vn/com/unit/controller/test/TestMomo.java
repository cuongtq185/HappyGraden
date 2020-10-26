package vn.com.unit.controller.test;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import com.mservice.allinone.models.CaptureMoMoRequest;

//import com.mservice.allinone.processor.allinone.PayATM;

//import com.mservice.allinone.PayGate;
import com.mservice.allinone.models.CaptureMoMoResponse;
import com.mservice.allinone.processor.allinone.CaptureMoMo;
import com.mservice.shared.exception.MoMoException;
//import com.mservice.shared.sharedmodels.Environment;
import com.mservice.shared.sharedmodels.Environment;
import com.mservice.shared.sharedmodels.Environment.EnvTarget;
import com.mservice.shared.sharedmodels.Environment.ProcessType;

public class TestMomo {

	// https://business.momo.vn/merchant/integrateInfo
	// https://test-payment.momo.vn/gw_payment/transactionProcessor
	
//	String partnerCode = "";
//	String accessKey = "";
//	String secretKey = "";
	
//	PartnerInfo devInfo = new PartnerInfo(partnerCode, accessKey, secretKey);
	
/*	
	String requestId = String.valueOf(System.currentTimeMillis());
    String orderId = String.valueOf(System.currentTimeMillis());
    
    String orderInfo = "Pay With MoMo";
    String returnURL = "https://google.com.vn";
    String notifyURL = "https://google.com.vn";
    String extraData = "";
    String bankCode = "SML";
    String customerNumber = "0963181714";

    EnvTarget target = EnvTarget.DEV;
    
    // PAY_GATE, APP_IN_APP, PAY_POS, PAY_QUERY_STATUS, PAY_REFUND, PAY_CONFIRM;
	ProcessType process = ProcessType.PAY_GATE;
    Environment environment = Environment.selectEnv(target, process);
    
//    PayGate
*/  
    
    public void test() throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException, MoMoException {
//    	String secretKey = environment.getPartnerInfo().getSecretKey();
//    	String data = "partnerCode=MOMO&accessKey=F8BBA842ECF85&requestId=MM1540456472575&amount=150000&orderId=MM1540456472575&orderInfo=SDK team.&returnUrl=https://momo.vn&notifyUrl=https://momo.vn&extraData=email=abc@gmail.com";
//    	String signature = Encoder.signHmacSHA256(data, secretKey);
    	
    	
//    	TransactionQuery.process(env, partnerRefId, secretKey, publicKey, momoTransId, version)
//    	PayGateRequest asdasd = new PayGateRequest(partnerCode, signature, signature, accessKey, amount, signature, data, signature, notifyUrl, returnUrl, requestType)
    	
//    	QRNotifyRequest qr = new QRNotifyRequest(status, signature, amount, error, partnerRefId, momoTransId, message, partnerCode, accessKey, partnerTransId, transType, responseTime, storeId)
    	
//    	CaptureMoMoRequest captureMoMoRequest = new CaptureMoMoRequest(partnerCode, signature, signature, accessKey, amount, signature, data, signature, notifyUrl, returnUrl, requestType);
    	
    	// https://developers.momo.vn/v1/#cong-thanh-toan-momo-phuong-thuc-thanh-toan
    	EnvTarget target = EnvTarget.DEV;
    	
        // PAY_GATE, APP_IN_APP, PAY_POS, PAY_QUERY_STATUS, PAY_REFUND, PAY_CONFIRM;
    	ProcessType process = ProcessType.PAY_GATE;
        Environment environment = Environment.selectEnv(target, process);
        
    	CaptureMoMo captureMoMo = new CaptureMoMo(environment);
    	
    	String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        String amount = "9999";
        String orderInfo = "Mua cái áo màu hường 9999 đ";
        /*
        http://localhost:8080/callback?
        partnerCode=MOMOIZCR20200811
        &accessKey=2cX38RzzWw1Iuh5r&requestId=1597138393414
        &amount=9999
        &orderId=1597138393414
        &orderInfo=Mua%20c%C3%A1i%20%C3%A1o%20m%C3%A0u%20h%C6%B0%E1%BB%9Dng%209999%20%C4%91
        &orderType=momo_wallet
        &transId=2323109317
        &message=Success
        &localMessage=Th%C3%A0nh%20c%C3%B4ng
        &responseTime=2020-08-11%2016:36:06
        &errorCode=0
        &payType=qr
        &extraData=
        &signature=c6cd39eaab6cf89ccb421528b0397c6e18f7ccc427c8e4cd3ca822c52554352a
         */
        String returnUrl = "http://localhost:8080/callback";
        String notifyUrl = "http://localhost:8080/webhook/check-signature";
//      Thông tin bổ sung theo định dạng: <key>=<value>;<key>=<value>.
//      Giá trị mặc định là trống ""
        String extraData = "";
        
    	CaptureMoMoRequest captureMoMoRequest = captureMoMo.createPaymentCreationRequest(orderId, requestId, amount, orderInfo, returnUrl, notifyUrl, extraData);
    	
    	CaptureMoMoResponse captureMoMoResponse = captureMoMo.execute(captureMoMoRequest);
    	captureMoMoResponse.getPayUrl();
//    	Đối với các hình thức quét mã QR, mở ứng dụng MoMo, nhúng website vào ứng dụng MoMo xin vui lòng liên hệ chúng tôi để biết thêm chi tiết.
    	captureMoMoResponse.getQrCodeUrl();
    }
    
}
