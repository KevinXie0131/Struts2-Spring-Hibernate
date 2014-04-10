package rml.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import rml.model.vo.SessionInfo;
import rml.util.ResourceUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * interceptor of session
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo == null) {
			ServletActionContext.getRequest().setAttribute("msg", "You have not logined yet, please logon.");
			return "noSession";
		}
		return actionInvocation.invoke();
	}

}
