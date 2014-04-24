package rml.model.po;

/**
 * Troletauth entity. @author MyEclipse Persistence Tools
 */

public class Troletauth implements java.io.Serializable {

	// Fields

	private String cid;
	private Tauth tauth;
	private Trole trole;

	// Constructors

	/** default constructor */
	public Troletauth() {
	}

	/** minimal constructor */
	public Troletauth(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Troletauth(String cid, Tauth tauth, Trole trole) {
		this.cid = cid;
		this.tauth = tauth;
		this.trole = trole;
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

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

}