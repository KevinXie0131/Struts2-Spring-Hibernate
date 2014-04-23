package rml.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Log;
import rml.model.vo.User;
import rml.service.LogServiceI;
import rml.service.UserServiceI;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "logAction", 
		results = { 
			@Result(name = "log", location = "/admin/log.jsp")
		})
public class LogAction extends BaseAction implements ModelDriven<Log> {

	private static final Logger logger = Logger.getLogger(LogAction.class);

	private Log log = new Log();
	
	@Override
	public Log getModel() {
		return log;
	}
	
	private LogServiceI logService;

	public LogServiceI getLogService() {
		return logService;
	}

	@Autowired
	public void setLogService(LogServiceI logService) {
		this.logService = logService;
	}

	public String log() {
		return "log";
	}
	
	public void datagrid() {
		super.writeJson(logService.datagrid(log));
	}
	
}
