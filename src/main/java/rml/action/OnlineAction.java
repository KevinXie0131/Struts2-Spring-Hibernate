package rml.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Online;
import rml.service.OnlineServiceI;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "onlineAction")
public class OnlineAction extends BaseAction implements ModelDriven<Online> {

	private Online online = new Online();

	public Online getModel() {
		return online;
	}

	private OnlineServiceI onlineService;

	public OnlineServiceI getOnlineService() {
		return onlineService;
	}

	@Autowired
	public void setOnlineService(OnlineServiceI onlineService) {
		this.onlineService = onlineService;
	}

	public void doNotNeedSession_onlineDatagrid() {
		super.writeJson(onlineService.datagrid(online));
	}

}
