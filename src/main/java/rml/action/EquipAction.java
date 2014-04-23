package rml.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Equip;
import rml.model.vo.Json;
import rml.service.EquipServiceI;
import rml.util.ExcelFileGenerator;
import rml.util.ExceptionUtil;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "equipAction", 
		results = { 
			@Result(name = "equip", location = "/admin/equip.jsp"),
			@Result(name = "equipAdd", location = "/admin/equipAdd.jsp"), 
			@Result(name = "equipEdit", location = "/admin/equipEdit.jsp")
		})
public class EquipAction extends BaseAction implements ModelDriven<Equip>{

	private static final Logger logger = Logger.getLogger(EquipAction.class);

	private Equip equip = new Equip();

	@Override
	public Equip getModel() {
		return equip;
	}
	
	private EquipServiceI equipService;

	public EquipServiceI getEquipService() {
		return equipService;
	}

	@Autowired
	public void setEquipService(EquipServiceI equipService) {
		this.equipService = equipService;
	}

	public String equip() {
		return "equip";
	}

	public String equipAdd() {
		return "equipAdd";
	}

	public String equipEdit() {
		return "equipEdit";
	}

	public void add() {
		Json j = new Json();
		try {
			equipService.save(equip);
			j.setSuccess(true);
			j.setMsg("Add successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Add unsuccessfully");
		}
		super.writeJson(j);
	}

	public void edit() {
		Json j = new Json();
		try {
			equipService.update(equip);
			j.setSuccess(true);
			j.setMsg("Edit successfully");
		} catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Edit unsuccessfully");
		}
		super.writeJson(j);
	}
	
	public void delete() {
		Json j = new Json();
		try {
			equipService.delete(equip.getIds());
			j.setSuccess(true);
			j.setMsg("Delete successfully");
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Delete unsuccessfully");
		}
		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(equipService.datagrid(equip));
	}
	
	public String exportToExcel(){	
		equipService.exportToExcelFile();
		return "equip";
	}

}
