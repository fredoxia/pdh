package pdh.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pdh.dao.entity.VO.CustomerOrderExcelVO;
import pdh.dao.entity.VO.FactoryOrderExcelVO;
import pdh.dao.entity.order.Customer;
import pdh.dao.entity.systemConfig.SystemConfig;
import pdh.dao.impl.Response;
import pdh.pageModel.DataGrid;
import pdh.pageModel.Json;
import pdh.pageModel.SessionInfo;
import pdh.service.CustAcctService;
import pdh.service.SystemConfigService;

@Controller
@RequestMapping("/systemConfigController")
public class SystemConfigController {
	
	@Autowired
	private SystemConfigService systemConfigService;
	
	@RequestMapping("/PreSystemConfig")
	public ModelAndView PreSystemConfig(){
		ModelAndView mav = new ModelAndView();
		
		Response response = new Response();
		try {
			response = systemConfigService.PreSystemConfigPage();
		} catch (Exception e){
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		mav.setViewName("/jsp/hq/hqAdmin/SystemConfig.jsp");
		mav.addObject("command", response.getReturnValue());
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/UpdateSystemConfig")
	public Json UpdateSystemConfig(SystemConfig systemConfig, HttpSession session){
		Response response = new Response();
		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
		try {
			response = systemConfigService.updateSystemConfig(systemConfig,loginUser.getUserName());
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/DeleteCurrentOrderData")
	public Json DeleteCurrentOrderData(){
		Response response = new Response();
		try {
			response = systemConfigService.deleteCurrentOrderData();
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
}
