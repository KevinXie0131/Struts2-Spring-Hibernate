package rml.model.po;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tauth entity. @author MyEclipse Persistence Tools
 */

public class Tauth implements java.io.Serializable {

	// Fields

	private String cid;
	private Tauth tauth;
	private String cdesc;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	private Set tauths = new HashSet(0);
	private Set troletauths = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tauth() {
	}

	/** minimal constructor */
	public Tauth(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tauth(String cid, Tauth tauth, String cdesc, String cname,
			BigDecimal cseq, String curl, Set tauths, Set troletauths) {
		this.cid = cid;
		this.tauth = tauth;
		this.cdesc = cdesc;
		this.cname = cname;
		this.cseq = cseq;
		this.curl = curl;
		this.tauths = tauths;
		this.troletauths = troletauths;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Tauth getTauth() {
		return this.tauth;
	}

	public void setTauth(Tauth tauth) {
		this.tauth = tauth;
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

	public BigDecimal getCseq() {
		return this.cseq;
	}

	public void setCseq(BigDecimal cseq) {
		this.cseq = cseq;
	}

	public String getCurl() {
		return this.curl;
	}

	public void setCurl(String curl) {
		this.curl = curl;
	}

	public Set getTauths() {
		return this.tauths;
	}

	public void setTauths(Set tauths) {
		this.tauths = tauths;
	}

	public Set getTroletauths() {
		return this.troletauths;
	}

	public void setTroletauths(Set troletauths) {
		this.troletauths = troletauths;
	}

}