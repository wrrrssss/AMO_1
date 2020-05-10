package com.example.amo1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.amo1.model.User;
import com.example.amo1.service.UserService;
import com.example.amo1.util.Define;
import com.example.amo1.vo.MessageVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 *   websocket发送消息服务器端  
 * @author TianTian
 * @create 2019/01/06 16:33:37
 * ws://localhost:8082/server/12
 *
 */
@ServerEndpoint("/server/{username}")
@Component
public class WebSocketServer {

	private static UserService userService;

	//静态变量,用来记录当前在线连接数.应该把它设计成线程安全的
	private static int onlineCount = 0;
	//线程安全的set,用来存放每个客户端对应的websocket对象
	private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
	//与某个客户端的连接会话,需要通过它来给客户端发送数据
	private Session session;

	private String username = "";
	
	/**
	  *    连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) {
	    this.session = session;
		webSocketMap.put(username,this);
		this.username = username;
		addOnlineCount();       //在线人数+1
		System.out.println("有新连接" + username + ",当前在线人数" + getOnlineCount());
		MessageVO messageVO = new MessageVO();
		messageVO.setOnlineNum(getOnlineCount());
		messageVO.setType(Define.MESSAGE_TYPE_ONLINE);
		messageVO.setCode(Define.MESSAGE_CODE_ONLINE);
		//取在线用户
        messageVO.setUsers(createOnlineUsers(webSocketMap));
		//把对象转换成json格式字符串
		String message = JSON.toJSONString(messageVO);
		//群发消息
		sendInfo(message,null);
	}

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage( String message,Session session) {
        System.out.println(message);
        //把收到的json字符串转成json对象
        JSONObject obj = JSONObject.parseObject(message);
        //取出需要发送的用户和内容
        String sender = obj.getString("sender");
        String content = obj.getString("content");
		String receiver = obj.getString("receiver");
		String time = obj.getString("time");
		String type = obj.getString("type");
        MessageVO messageVO = new MessageVO();
        if(type !=null && type.equals("online")){ //获取在线人数
			messageVO.setOnlineNum(getOnlineCount());
			messageVO.setType(Define.MESSAGE_TYPE_ONLINE);
			messageVO.setCode(Define.MESSAGE_CODE_ONLINE);
			//取在线用户
			messageVO.setUsers(createOnlineUsers(webSocketMap));
		}else { //发送信息通知，聊天消息
			if(type!=null && type.equals("connecting")) messageVO.setType("connecting");
			else messageVO.setType(Define.MESSAGE_TYPE_NORMAL);
			messageVO.setCode(Define.MESSAGE_CODE_NORMAL);
			messageVO.setContent(content);
			messageVO.setTime(time);
			messageVO.setSender(sender);
		}
        //把对象转换成json格式字符串
        String sendMsg = JSON.toJSONString(messageVO);
        //发送消息给指定的用户
        this.sendInfo(sendMsg,receiver);
    }

	/**
	 * 连接关闭时调用的方法
	 */
	@OnClose
	public void onClose() {
		System.out.println("离开");
		if(webSocketMap.get(this.username)!=null){
			webSocketMap.remove(username);
			subOnlineCount(); //在线人数减1
			System.out.println("有连接关闭,当前在线人数为"+getOnlineCount());
            MessageVO messageVO = new MessageVO();
            messageVO.setOnlineNum(getOnlineCount());
            messageVO.setType(Define.MESSAGE_TYPE_OFFLINE);
            messageVO.setCode(Define.MESSAGE_CODE_ONLINE);
            messageVO.setSender(username);
            //取在线用户
            messageVO.setUsers(createOnlineUsers(webSocketMap));
            //把对象转换成json格式字符串
            String message = JSON.toJSONString(messageVO);
            //群发消息
            sendInfo(message,null);
		}
		
	}
	
	/**
	 * 发生错误时调用的方法
	 * @param sesson
	 * @param error
	 */
	@OnError
	public void onError(Session sesson, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}
	
	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message,@PathParam("username") String username) {
		System.out.println("推送消息到" + username +",推送内容:"+message);
		//for(WebSocketServer item: webSocketSet) {
		for(WebSocketServer item: webSocketMap.values()) {
			try {
				if(username == null || username.equals("")) { //如果没有指定username,则推送到全部
					item.session.getBasicRemote().sendText(message);
				}else if(item.username.equals(username)) { //如果指定username,则发送给此username
					item.session.getBasicRemote().sendText(message);
				}
			}catch(IOException e) {
				e.printStackTrace();
				continue;
			}
			
		}
	}		
		
	/**
	 * 取在线人数
	 * @return
	 */
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}
	
	/**
	 *  在线人数加1
	 */
	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}
	
	/**
	 * 在线人数减1
	 */
	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

	private Set<User> createOnlineUsers(ConcurrentHashMap<String,WebSocketServer> webSocketMap){
        Set<User> users = new HashSet<>();
	    Set<String> keys = webSocketMap.keySet();
        for(String key:keys){
        	//根据用户名查询用户信息
			User user = userService.selByUsername(key);
            users.add(user);
        }
        return users;
	}

	@Resource
	public void setUserService(UserService userService) {
		WebSocketServer.userService = userService;
	}
}
