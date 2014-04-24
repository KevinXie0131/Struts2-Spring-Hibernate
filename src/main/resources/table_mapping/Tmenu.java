package rml.model.po;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tmenu entity. @author MyEclipse Persistence Tools
 */

public class Tmenu implements java.io.Serializable {

	// Fields

	private String cid;
	private Tmenu tmenu;
	private String ciconcls;
	private String cname;
	private BigDecimal cseq;
	private String curl;
	private Set tmenus = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tmenu() {
	}

	/** minimal constructor */
	public Tmenu(String cid, String cname) {
		this.cid = cid;
		this.cname = cname;
	}

	/** full constructor */
	public Tmenu(String cid, Tmenu tmenu, String ciconcls, String cname,
			BigDecimal cseq, String curl, Set tmenus) {
		this.cid = cid;
		this.tmenu = tmenu;
		this.ciconcls = ciconcls;
		this.cname = cname;
		this.cseq = cseq;
		this.curl = curl;
		this.tmenus = tmenus;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Tmenu getTmenu() {
		return this.tmenu;
	}

	public void setTmenu(Tmenu tmenu) {
		this.tmenu = tmenu;
	}

	public String getCiconcls() {
		return this.ciconcls;
	}

	public void setCiconcls(String ciconcls) {
		this.ciconcls = ciconcls;
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

	public Set getTmenus() {
		return this.tmenus;
	}

	public void setTmenus(Set tmenus) {
		this.tmenus = tmenus;
	}

}