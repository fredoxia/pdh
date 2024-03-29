package pdh.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pdh.dao.entity.VO.CustInforExcelVO;
import pdh.dao.entity.VO.CustomerOrderExcelVO;
import pdh.dao.entity.VO.FactoryOrderExcelVO;
import pdh.dao.entity.order.Customer;
import pdh.dao.impl.Response;
import pdh.pageModel.DataGrid;
import pdh.pageModel.Json;
import pdh.pageModel.SessionInfo;
import pdh.service.CustAcctService;

@Controller
@RequestMapping("/custAcctController")
public class CustAcctController {
	
	@Autowired
	private CustAcctService custAcctService;
	
	@RequestMapping("/CustAcctMgmt")
	public String CustAcctMgmt() {
		
		return "/jsp/hq/hqAdmin/CustAcctMgmt.jsp";
	}
	
	@ResponseBody
	@RequestMapping("/SearchCustFromJinsuan")
	public DataGrid SearchCustFromJinsuan(String custName){
		DataGrid dataGrid = null ; //custAcctService.searchCustFromJinsuan(custType,status, custName, sort, order);

		return dataGrid;
	}
	
	@ResponseBody
	@RequestMapping("/GetCustAccts")
	public DataGrid GetCustAccts(Integer custType, Integer status, String custName, String sort, String order){
		DataGrid dataGrid = custAcctService.getCustAccts(custType,status, custName, sort, order);

		return dataGrid;
	}
	
	@RequestMapping("/PreAddUpdateCustAcct")
	public ModelAndView PreAddUpdateCustAcct(Customer cust){
		Response response = new Response();
		
		try {
			    response = custAcctService.prepareAddEditCustAcct(cust);
			} catch (Exception e){
				e.printStackTrace();
				response.setFail(e.getMessage());
			}

		ModelAndView mav = new ModelAndView();
		mav.addAllObjects((Map<String, ?>)response.getReturnValue());
		mav.setViewName("/jsp/hq/hqAdmin/CustAcctAddUpdate.jsp");
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/AddUpdateCustAcct")
	public Json AddUpdateCustAcct(Customer cust, HttpSession session){
		Response response = new Response();
//		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
//		try {
//			response = custAcctService.addUpdateAcct(cust,loginUser.getUserName());
//		} catch (Exception e){
//			e.printStackTrace();
//			response.setFail("系统错误 : " + e.getMessage());
//		}
		
		Json json = new Json(response);
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/InactiveCustAcct")
	public Json InactiveCustAcct(Integer id, HttpSession session){
		Response response = new Response();
		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
		try {
			response = custAcctService.inactiveCustAcct(id,loginUser.getUserName());
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/ActiveCustAcct")
	public Json ActiveCustAcct(Integer id, HttpSession session){
		Response response = new Response();
		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
		try {
			response = custAcctService.activeCustAcct(id,loginUser.getUserName());
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/CheckCustOrder")
	public Json CheckCustOrder(Integer id, HttpSession session){
		Response response = new Response();
		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
		try {
			response = custAcctService.checkCustOrder(id);
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
	@RequestMapping("/OpenCustOrderJSP")
	public ModelAndView GetCustOrderJSP(Integer id, Integer toCustId, HttpSession session){
		ModelAndView maView = new ModelAndView();
		
		Response response = new Response();
		try {
			response = custAcctService.getCustOrderJSP(id);
		} catch (Exception e){
			response.setFail("系统错误 : " + e.getMessage());
		}
		//maView.addObject("command", response.getReturnValue());
		maView.addAllObjects((Map<String, ?>)response.getReturnValue());
		maView.setViewName("/jsp/hq/hqAdmin/CustOrder.jsp");
		
		return maView;
	}
	
	@ResponseBody
	@RequestMapping("/GetCustOrderData")
	public DataGrid GetCustOrderData(Integer id, HttpSession session){
		DataGrid dataGrid = new DataGrid();
		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
		
		dataGrid = custAcctService.getCustOrder(id);


		return dataGrid;
	}
	
	@RequestMapping("/HQDownloadCustOrder")
	public ModelAndView HQDownloadCustOrder(Integer id){
		
		Response response = new Response();
		try {
			response = custAcctService.getCustOrderProducts(id);
		} catch (Exception e){
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView(new CustomerOrderExcelVO(), (Map<String, ?>)response.getReturnValue()); 
		
		return mav;
	}
	
	@RequestMapping("/HQDownloadCust")
	public ModelAndView HQDownloadCust(Integer custType, Integer status){
		
		Response response = new Response();
		try {
			response = custAcctService.downloadCust(custType, status);
		} catch (Exception e){
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		ModelAndView mav = new ModelAndView(new CustInforExcelVO(), (Map<String, ?>)response.getReturnValue()); 
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/AddCust")
	public Json AddCust(String clientIds, HttpSession session){
		Response response = new Response();
		SessionInfo loginUser = (SessionInfo)session.getAttribute(ControllerConfig.HQ_SESSION_INFO);
		try {
			response = custAcctService.addCust(clientIds,loginUser.getUserName());
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
	
	@ResponseBody
	@RequestMapping("/SearchJinSuanClients")
	public DataGrid SearchJinSuanClients(String custName){
		DataGrid dataGrid = new DataGrid();

		dataGrid = custAcctService.searchJinSuanClients(custName);

		return dataGrid;
	}
	
	@ResponseBody
	@RequestMapping("/CopyCustOrder")
	public Json CopyCustOrder(Integer fromCustId, Integer toCustId){
		Response response = new Response();
		try {
			response = custAcctService.copyCustOrder(fromCustId, toCustId);
		} catch (Exception e){
			e.printStackTrace();
			response.setFail("系统错误 : " + e.getMessage());
		}
		
		Json json = new Json(response);
		
		return json;
	}
	
}
