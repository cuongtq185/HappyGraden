package vn.com.unit.socket;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import vn.com.unit.entity.Account;
import vn.com.unit.service.AccountService;
import vn.com.unit.service.ChatServerService;
import vn.com.unit.utils.CommonUtils;

//import org.springframework.security.core.session.SessionRegistryImpl;

//@Component
public class ChatServerSocket {

	private static SocketIOServer server;

	private static SocketIONamespace chat_namespace;

	private static SocketIONamespace voice_namespace;

//	@Autowired
//	@Qualifier("sessionRegistry")
//	@Resource(name = "sessionRegistry")
//    private SessionRegistryImpl sessionRegistry;

//	@Resource(name = "sessionRegistry")
//	private static SessionRegistryImpl sessionRegistry;

//	@Autowired
//	private static SessionRegistry sessionRegistry;

//	@Autowired
//    private static SessionRegistry sessionRegistry;

//	@Autowired
//	private static AccountService accountService;
	
	public static void initChatServerSocket() {

		if (server != null) {
			return;
		}

		Configuration config = new Configuration();

		/*
		 * setHostname If not set then bind address will be 0.0.0.0 or ::0
		 */
//		config.setHostname("localhost");

		/*
		 * setPort The port the socket.io server will listen to
		 */
//		config.setPort(9092);

		/*
		 * setJsonTypeFieldName defaults to "@class"
		 */

		/*
		 * setJsonSupport Allows to setup custom implementation of JSON
		 * serialization/deserialization.See JsonSupport
		 */

		/*
		 * setAuthorizationListener Authorization listener invoked on every handshake.
		 * Accepts all clients by default.
		 */

		/*
		 * setStoreFactory Client store and pubsub factory, used to store session data
		 * and implements distributed pubsub. Defaults to MemoryStoreFactory, but
		 * RedisStoreFactory and HazelcastStoreFactory also implemented.
		 */

		/*
		 * setPreferDirectBuffer (added in 1.5 version) Buffer allocation method used
		 * during packet encoding. Defaults to true.
		 */

		/*
		 * setBossThreads (added in 1.5 version) boss-threads amount for netty
		 */

		/*
		 * setWorkerThreads (added in 1.5 version) worker-threads amount for netty
		 */

		/*
		 * setHeartbeatInterval Heartbeat interval (in seconds), defaults to 25
		 */

		/*
		 * setHeartbeatTimeout Heartbeat timeout (in seconds), defaults to 60. Use 0 to
		 * disable it
		 */
//		config.setCloseTimeout(30);

		/*
		 * setCloseTimeout Channel close timeout (in seconds) due to inactivity,
		 * defaults to 60
		 */

		/*
		 * setContext Namespace, defaults to "/socket.io"
		 */

		/*
		 * setAllowCustomRequests Allow to service custom requests that differ from
		 * socket.io protocol, defaults to false.
		 */

		/*
		 * If true, add own handler which handle custom requests in order to avoid hang
		 * connections. setPollingDuration Polling interval for XHR transport (in
		 * seconds), defaults to 20
		 */

		/*
		 * setKeyStorePassword SSL key store password (for secure connections)
		 */

		/*
		 * setKeyStore SSL key store stream, maybe appointed to any source
		 */

		/*
		 * setMaxHttpContentLength Set maximum HTTP content length limit, defaults to
		 * 64KB.
		 */

		/*
		 * If the length of the aggregated content exceeds this value, a
		 * TooLongFrameException will be raised. setTransports Transports supported by
		 * server, defaults to [Transport.WEBSOCKET, Transport.FLASHSOCKET,
		 * Transport.XHRPOLLING]. Cannot be empty list
		 */

		/*
		 * SocketConfig setReuseAddress
		 */
//		java.net.BindException: Address already in use
//		SocketConfig socketConfig = new SocketConfig();
//		socketConfig.setReuseAddress(true);

//		config.setSocketConfig(socketConfig);

//		com.corundumstudio.socketio.AuthorizationListener
		AuthorizationListener authorizationListener = new AuthorizationListener() {

			@Override
			public boolean isAuthorized(HandshakeData data) {
				

				// TODO Auto-generated method stub
//				return false;
//				String s = data.getHttpHeaders().toString();
//				String a = data.getHttpHeaders().get("asasaas").toString();
//				for (Object o : data.getHttpHeaders()) {
//					System.out.print(o.toString());
//				}
//				data.getHttpHeaders();

				HttpHeaders headers = data.getHttpHeaders();

				String header = headers.get("Cookie");

				// LAX : lỏng lẽo
				// STRICT : chặt chẽ
				List<Cookie> cookies = ServerCookieDecoder.STRICT.decodeAll(header);

				for (Cookie cookie : cookies) {
					String name = cookie.name();
					if (name.equals("token")) {
						String token = cookie.value();
						String username = CommonUtils.getUsernameFromJWT(token);
						AccountService accountService = CommonUtils.ACCOUNT_SERVICE;
//						Account current_user = CommonUtils.ACCOUNT_SERVICE.findByUsername(username);
					}
					
					/*
					if (name.equals("JSESSIONID")) {
						String j_session_id = cookie.value();
						
						String[] temp = j_session_id.split("\\.");
						
						String session_id = temp[0];
						
//						SessionRegistry sessionRegistry = new SessionRegistryImpl();
//						SessionInformation sessionInformation = sessionRegistry.getSessionInformation(session_id);
						
//						SessionInformation sessionInformation = ChatServerService.getSessionInformationFromSessionId(session_id);
						
//						org.springframework.security.core.session.SessionInformation;
//						org.springframework.security.core.session.SessionRegistry;
//						org.springframework.security.core.userdetails.User;

//						sessionRegistry.

//						SessionRegistry sessionRegistry = new SessionRegistryImpl();
//						List<Object> principals =  sessionRegistry.getAllPrincipals();
//						SessionInformation sessionInformation = sessionRegistry.getSessionInformation(sessionId);
//
//						Principal principal = (Principal) sessionInformation.getPrincipal();
					}
					*/
				}

				
				String token = data.getSingleUrlParam("token");

				return true;
			}
		};

		config.setPort(9092);
		config.setAuthorizationListener(authorizationListener);

		SocketConfig socketConfig = new SocketConfig();
		socketConfig.setReuseAddress(true);

		config.setSocketConfig(socketConfig);

		server = new SocketIOServer(config);

		server.addEventListener("msg", byte[].class, new DataListener<byte[]>() {

	@Override
	public void onData(SocketIOClient client, byte[] data, AckRequest ackRequest) {
		client.sendEvent("msg", data);
	}

	});

	chat_namespace=server.addNamespace("/chat");chat_namespace.addConnectListener(onChatConnected());chat_namespace.addEventListener("chat",ChatMessage.class,onChatReceived());chat_namespace.addDisconnectListener(onChatDisconnected());

//		server.addEventListener("msg", byte[].class, new DataListener<byte[]>() {
//            @Override
//            public void onData(SocketIOClient client, byte[] data, AckRequest ackRequest) {
//                client.sendEvent("msg", data);
//            }
//        });

	voice_namespace=server.addNamespace("/voice");voice_namespace.addConnectListener(onVoiceConnected());voice_namespace.addEventListener("voice",byte[].class,onVoiceReceived());voice_namespace.addDisconnectListener(onVoiceDisconnected());

	server.start();}

	private static ConnectListener onChatConnected() {
		return client -> {
			HandshakeData handshakeData = client.getHandshakeData();
			String sessionId = client.getSessionId().toString();
			String handshakeDataUrl = handshakeData.getUrl();

			client.sendEvent("chat", "Server: { \"session_id\" : \"" + sessionId + "\", \"handshakeDataUrl\" : \""
					+ handshakeDataUrl + "\" }");
			chat_namespace.getBroadcastOperations().sendEvent("chat",
					"Server: (broadcast) " + sessionId + " connect successfully");

//            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
		};
	}

//	ack : response for client after client send success (?)
	private static DataListener<ChatMessage> onChatReceived() {

		return (client, data, ackSender) -> {
//            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
			chat_namespace.getBroadcastOperations().sendEvent("chat", data);
		};
	}

	private static DisconnectListener onChatDisconnected() {
		return client -> {
//            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
		};
	}

	private static ConnectListener onVoiceConnected() {
		return client -> {
			HandshakeData handshakeData = client.getHandshakeData();
			String sessionId = client.getSessionId().toString();
			String handshakeDataUrl = handshakeData.getUrl();

			client.sendEvent("voice", "Server: { \"session_id\" : \"" + sessionId + "\", \"handshakeDataUrl\" : \""
					+ handshakeDataUrl + "\" }");
			voice_namespace.getBroadcastOperations().sendEvent("voice",
					"Server: (broadcast) " + sessionId + " connect successfully");

//            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
		};
	}

//	ack : response for client after client send success (?)
	private static DataListener<byte[]> onVoiceReceived() {

		return (client, data, ackSender) -> {
//            log.debug("Client[{}] - Received chat message '{}'", client.getSessionId().toString(), data);
			voice_namespace.getBroadcastOperations().sendEvent("voice", data);
		};
	}

	private static DisconnectListener onVoiceDisconnected() {
		return client -> {
//            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
		};
	}

	public static void start() {
		initChatServerSocket();
	}

	public static void stop() {
		server.stop();
	}

}
