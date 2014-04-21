package rml.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.service.RepairServiceI;

@Namespace("/")
@Action(value = "repairAction", 
		results = { 
			@Result(name = "success", location = "/", type = "redirect") 
		})
public class RepairAction extends BaseAction {

	private RepairServiceI repairService;

	public RepairServiceI getRepairService() {
		return repairService;
	}

	@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}

	/**
	 * repair database
	 */
	public String doNotNeedSession_repairAction() {
		repairService.repair();
		return "success";
	}

	/**
	 * delete and repair database
	 */
	public String doNotNeedSession_deleteAndRepair() {
		repairService.deleteAndRepair();
		return "success";
	}

}
