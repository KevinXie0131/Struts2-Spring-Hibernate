package rml.interceptor;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import rml.model.vo.SessionInfo;
import rml.util.RequestUtil;
import rml.util.ResourceUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * interceptor of auth
 * 
 */
public class AuthInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo.getLoginName().equals("admin")) {// admin has full authority
			return actionInvocation.invoke();
		}
		String requestPath = RequestUtil.getRequestPath(ServletActionContext.getRequest());
		String authUrls = sessionInfo.getAuthUrls();
		boolean b = false;
		for (String url : authUrls.split(",")) {
			if (requestPath.equals(url)) {
				b = true;
				break;
			}
		}
		if (b) {
			return actionInvocation.invoke();
		}
		ServletActionContext.getRequest().setAttribute("msg", "You have no  authority to [" + requestPath + "]");
		return "noAuth";
	}

}
