package rml.model.po;

/**
 * Tusertrole entity. @author MyEclipse Persistence Tools
 */

public class Tusertrole implements java.io.Serializable {

	// Fields

	private String cid;
	private Trole trole;
	private Tuser tuser;

	// Constructors

	/** default constructor */
	public Tusertrole() {
	}

	/** minimal constructor */
	public Tusertrole(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public Tusertrole(String cid, Trole trole, Tuser tuser) {
		this.cid = cid;
		this.trole = trole;
		this.tuser = tuser;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Trole getTrole() {
		return this.trole;
	}

	public void setTrole(Trole trole) {
		this.trole = trole;
	}

	public Tuser getTuser() {
		return this.tuser;
	}

	public void setTuser(Tuser tuser) {
		this.tuser = tuser;
	}

}