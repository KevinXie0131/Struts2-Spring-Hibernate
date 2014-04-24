package rml.model.po;

/**
 * Tdoc entity. @author MyEclipse Persistence Tools
 */

public class Tdoc implements java.io.Serializable {

	// Fields

	private String cid;
	private String cmanual;
	private String cmodel;
	private String cname;
	private Long cno;
	private String cproducer;

	// Constructors

	/** default constructor */
	public Tdoc() {
	}

	/** minimal constructor */
	public Tdoc(String cid, String cmodel) {
		this.cid = cid;
		this.cmodel = cmodel;
	}

	/** full constructor */
	public Tdoc(String cid, String cmanual, String cmodel, String cname,
			Long cno, String cproducer) {
		this.cid = cid;
		this.cmanual = cmanual;
		this.cmodel = cmodel;
		this.cname = cname;
		this.cno = cno;
		this.cproducer = cproducer;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCmanual() {
		return this.cmanual;
	}

	public void setCmanual(String cmanual) {
		this.cmanual = cmanual;
	}

	public String getCmodel() {
		return this.cmodel;
	}

	public void setCmodel(String cmodel) {
		this.cmodel = cmodel;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Long getCno() {
		return this.cno;
	}

	public void setCno(Long cno) {
		this.cno = cno;
	}

	public String getCproducer() {
		return this.cproducer;
	}

	public void setCproducer(String cproducer) {
		this.cproducer = cproducer;
	}

}