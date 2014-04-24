package rml.model.po;

import java.util.HashSet;
import java.util.Set;

/**
 * Trole entity. @author MyEclipse Persistence Tools
 */

public class Trole implements java.io.Serializable {

	// Fields

	private String cid;
	private String cdesc;
	private String cname;
	private Set troletauths = new HashSet(0);
	private Set tusertroles = new HashSet(0);

	// Constructors

	/** default constructor */
	public Trole() {
	}

	/** minimal constructor */
	public Trole(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Trole(String cid, String cdesc, String cname, Set troletauths,
			Set tusertroles) {
		this.cid = cid;
		this.cdesc = cdesc;
		this.cname = cname;
		this.troletauths = troletauths;
		this.tusertroles = tusertroles;
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

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set getTroletauths() {
		return this.troletauths;
	}

	public void setTroletauths(Set troletauths) {
		this.troletauths = troletauths;
	}

	public Set getTusertroles() {
		return this.tusertroles;
	}

	public void setTusertroles(Set tusertroles) {
		this.tusertroles = tusertroles;
	}

}