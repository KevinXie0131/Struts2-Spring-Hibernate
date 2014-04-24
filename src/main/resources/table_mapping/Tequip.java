package rml.model.po;

/**
 * Tequip entity. @author MyEclipse Persistence Tools
 */

public class Tequip implements java.io.Serializable {

	// Fields

	private String cid;
	private String cdesc;
	private String cmodel;
	private String cname;
	private Long cno;
	private String cproducer;

	// Constructors

	/** default constructor */
	public Tequip() {
	}

	/** minimal constructor */
	public Tequip(String cid, String cmodel) {
		this.cid = cid;
		this.cmodel = cmodel;
	}

	/** full constructor */
	public Tequip(String cid, String cdesc, String cmodel, String cname,
			Long cno, String cproducer) {
		this.cid = cid;
		this.cdesc = cdesc;
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

	public String getCdesc() {
		return this.cdesc;
	}

	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
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