package pdh.controller;


import javax.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pdh.dao.entity.order.Customer;
import pdh.dao.entity.qxMIS.UserInfor2;
import pdh.dao.impl.Response;
import pdh.pageModel.Json;
import pdh.pageModel.SessionInfo;
import pdh.service.UserService;


@Controller
@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/HQLogin")
	public Json HQLogin(UserInfor2 user, HttpSession session) {
		Json j = new Json();
		Response response = userService.HQlogin(user);
		if (response.isSuccess()) {
			j.setSuccess(true);
			j.setMsg("登陆成功！");

			SessionInfo sessionInfo = new SessionInfo();
			BeanUtils.copyProperties((UserInfor2)response.getReturnValue(), sessionInfo);
			//sessionInfo.setIp(IpUtil.getIpAddr(request));
			//sessionInfo.setResourceList(userService.resourceList(u.getId()));
			
			session.setAttribute(ControllerConfig.HQ_SESSION_INFO, sessionInfo);

			j.setObj(sessionInfo);
		} else {
			j.setMsg(response.getMessage());
		}
		return j;
	}
	
	@ResponseBody
	@RequestMapping("/login/mobile")
	public Json login(Customer cust, HttpSession session) {
		Json j = new Json();
		Response response = userService.loginMobile(cust);
		if (response.isSuccess()) {
			j.setSuccess(true);
			j.setMsg("登陆成功！");

			SessionInfo sessionInfo = new SessionInfo();
			cust = (Customer)response.getReturnValue();
			
			sessionInfo.setUserId(cust.getId());
			sessionInfo.setUserName(cust.getCustNameVO());
			
			session.setAttribute(ControllerConfig.HQ_SESSION_INFO, sessionInfo);

			j.setObj(sessionInfo);
		} else {
			j.setMsg(response.getMessage());
		}
		return j;
	}

	
	@RequestMapping("/Logout/mobile")
	public String MobileLogout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return"redirect:/index.jsp";
	}
	
	@RequestMapping("/HQMain")
	public String HQMain() {
		
		return"/jsp/hq/Main.jsp";
	}
	
	@RequestMapping("/HQLogoff")
	public String HQLogoff(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		return "/indexh.jsp";
	}

}
