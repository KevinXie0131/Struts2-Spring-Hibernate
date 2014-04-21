package rml.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Bsee Action
 * 
 */
@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
   
	protected HttpServletRequest request = null;
	
	protected HttpServletResponse response = null;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;	
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;	
	}
	/**
	 * respond by JSON
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object)  {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");		
		/*	String json = null;
			try {
				json = JacksonJsonUtil.beanToJson(object);
			} catch (Exception e) {
				e.printStackTrace();
			} 	
		*/
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//revise json, make icon display
	public void writeJson_icon(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		/*	String json = null;
			try {
				json = JacksonJsonUtil.beanToJson(object);
			} catch (Exception e) {
				e.printStackTrace();
			} 	
		*/
			String json_icon = json.replace("iconcls", "iconCls");	
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json_icon);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
