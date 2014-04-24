package rml.model.po;

import java.util.Date;

/**
 * Tlog entity. @author MyEclipse Persistence Tools
 */

public class Tlog implements java.io.Serializable {

	// Fields

	private String cid;
	private Date cdatetime;
	private String cip;
	private String cmsg;
	private String cname;

	// Constructors

	/** default constructor */
	public Tlog() {
	}

	/** minimal constructor */
	public Tlog(String cid, String cip, String cname) {
		this.cid = cid;
		this.cip = cip;
		this.cname = cname;
	}

	/** full constructor */
	public Tlog(String cid, Date cdatetime, String cip, String cmsg,
			String cname) {
		this.cid = cid;
		this.cdatetime = cdatetime;
		this.cip = cip;
		this.cmsg = cmsg;
		this.cname = cname;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Date getCdatetime() {
		return this.cdatetime;
	}

	public void setCdatetime(Date cdatetime) {
		this.cdatetime = cdatetime;
	}

	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getCmsg() {
		return this.cmsg;
	}

	public void setCmsg(String cmsg) {
		this.cmsg = cmsg;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}