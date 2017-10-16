package generic.dao.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="profile", schema="jpa")
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2522030189790476492L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDENTIFICADOR")
	private Integer cdProfile;
	
	@Column(name="NOME")
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO")
	private Date creationDate;

	@OneToMany(mappedBy = "id.profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ActionProfile> actionProfile;
	
	public Integer getCdProfile() {
		return cdProfile;
	}
	public void setCdProfile(Integer cdProfile) {
		this.cdProfile = cdProfile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<ActionProfile> getActionProfile() {
		return actionProfile;
	}
	public void setActionProfile(List<ActionProfile> actionProfile) {
		this.actionProfile = actionProfile;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [id=");
		builder.append(cdProfile);
		builder.append(", name=");
		builder.append(name);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", actionProfile=");
		builder.append("??");
		builder.append("]");
		return builder.toString();
	}
	
}
