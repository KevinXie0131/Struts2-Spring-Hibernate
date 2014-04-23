package rml.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import rml.model.vo.Doc;
import rml.model.vo.Json;
import rml.service.DocServiceI;
import rml.util.ExceptionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "docAction", 
		results = { 
			@Result(name = "doc", location = "/admin/doc.jsp"),
			@Result(name = "docAdd", location = "/admin/docAdd.jsp"), 
			@Result(name = "docEdit", location = "/admin/docEdit.jsp"),
			@Result(name = "docUpload", location = "/admin/docUpload.jsp")
		})
public class DocAction extends BaseAction implements ModelDriven<Doc>{

	private static final Logger logger = Logger.getLogger(DocAction.class);

	private Doc doc = new Doc();

	@Override
	public Doc getModel() {
		return doc;
	}
	
	private DocServiceI docService;

	public DocServiceI getDocService() {
		return docService;
	}

	@Autowired
	public void setDocService(DocServiceI docService) {
		this.docService = docService;
	}
	
	private File uploadFile;  
	private String uploadFileFileName; 
	private String uploadFileContentType;  

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String getUploadFileContentType() {
		return uploadFileContentType;
	}

	public void setUploadFileContentType(String uploadFileContentType) {
		this.uploadFileContentType = uploadFileContentType;
	}

	public String doc() {
		return "doc";
	}

	public String docAdd() {
		return "docAdd";
	}

	public String docEdit() {
		return "docEdit";
	}
	
	public String docUpload() {
		return "docUpload";
	}

	public void add() {
		Json j = new Json();
		try {
			docService.save(doc);
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
			docService.update(doc);
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
			docService.delete(doc.getIds());
			j.setSuccess(true);
			j.setMsg("Delete successfully");
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Delete unsuccessfully");
		}
		super.writeJson(j);
	}

	public void datagrid() {
		super.writeJson(docService.datagrid(doc));
	}
	
	public void upload() {
		Json j = new Json();
		try {			
			String uploadFileFileNameWithoutSpace = uploadFileFileName.replaceAll(" ", "");
			String realpath = ServletActionContext.getServletContext().getRealPath("/upload");		    
			File targetFile = new File(realpath+File.separator, uploadFileFileNameWithoutSpace);
			if (targetFile.exists()) {
				targetFile.delete();
			}
			FileUtils.copyFile(uploadFile, targetFile);
			
			docService.upload(doc,uploadFileFileNameWithoutSpace);
			
			j.setSuccess(true);
			j.setMsg("Upload manual successfully");
		}catch (Exception e) {
			logger.error(ExceptionUtil.getExceptionMessage(e));
			j.setMsg("Upload manual unsuccessfully");
		}
		super.writeJson(j);
	}
}
