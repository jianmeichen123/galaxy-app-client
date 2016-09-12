package com.galaxyinternet.pushMsg.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.galaxyinternet.common.controller.BaseControllerImpl;
import com.galaxyinternet.exception.PlatformException;
import com.galaxyinternet.framework.core.model.Page;
import com.galaxyinternet.framework.core.model.PageRequest;
import com.galaxyinternet.framework.core.model.ResponseData;
import com.galaxyinternet.framework.core.model.Result;
import com.galaxyinternet.framework.core.model.Result.Status;
import com.galaxyinternet.framework.core.service.BaseService;
import com.galaxyinternet.model.pushmsg.PushMsg;
import com.galaxyinternet.service.PushMsgService;
import com.tencent.xinge.XGPush;

/**
 * 自定义消息推送
 */
@Controller
@RequestMapping("/galaxy/pushMsg")
public class PushMsgController extends BaseControllerImpl<PushMsg, PushMsg> {
	final Logger logger = LoggerFactory.getLogger(PushMsgController.class);
	@Autowired
	private PushMsgService msgService;
	

	
	@Override
	protected BaseService<PushMsg> getBaseService() {
		return this.msgService;
	}
	

	/**
	 * 默认页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request,HttpServletResponse response) {
		
		return"system/pushMsg/pushmsg_list";
	}

	/**
	 * 查询自定义推送列表
	    *
	    *  
	    * @param @param msg
	    * @param @return
	    * @return ResponseData<User>
	    *
	 */
	@ResponseBody
	@RequestMapping(value = "/queryMsgList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PushMsg> queryMsgList(@RequestBody PushMsg msg) {
		
		ResponseData<PushMsg> responseBody = new ResponseData<PushMsg>();
		try {
			PageRequest pageRequest = new PageRequest(msg.getPageNum(),
					msg.getPageSize());
			Page<PushMsg> pageList = msgService.queryPageList(msg, pageRequest);
			responseBody.setPageList(pageList);
			responseBody.setResult(new Result(Status.OK,"success"));
		} catch (PlatformException e) {
			responseBody.setResult(new Result(Status.ERROR,"queryMsgList failed"));
			if (logger.isErrorEnabled()) {
				logger.error("queryMsgList ", e);
			}
		}
		
		return responseBody;
	}
	
	@ResponseBody
	@RequestMapping(value = "/insertPushMsg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseData<PushMsg> insertPushMsg(@RequestBody PushMsg msg) {
		
		ResponseData<PushMsg> responseBody = new ResponseData<PushMsg>();
		try {
			//推送消息
			XGPush xgPush = XGPush.getInstance();
			xgPush.pushAccountList(msg.getUserIdList(), null, msg.getContent());
			//消息入库
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");  
			Long time = System.currentTimeMillis();
			String d = format.format(time);  
			Date date=format.parse(d); 
			msg.setNoticeTime(date);
			msgService.insert(msg);
			responseBody.setResult(new Result(Status.OK,"success"));
		} catch (Exception e) {
			responseBody.setResult(new Result(Status.ERROR,"insertPushMsg failed"));
			if (logger.isErrorEnabled()) {
				logger.error("insertPushMsg failed", e);
			}
		}
		
		return responseBody;
	}

	
}
