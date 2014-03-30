package rml.action;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;

//import com.alibaba.fastjson.JSON;
import rml.util.JacksonJsonUtil;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction {
   
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
