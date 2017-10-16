package generic.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="action", schema="jpa")
public class Action implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2522030189790476492L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CD_ACAO")
	private Integer cdAction;
	
	@Column(name="DESC_ACAO")
	private String descAcao;
	
	@Column(name="FG_STATUS_REGISTRO")
	private String fgStatusRegistro;
	
	@OneToMany(mappedBy = "id.action", fetch = FetchType.LAZY)
	private List<ActionProfile> actionProfile;
	
	public Integer getCdAction() {
		return cdAction;
	}
	public void setCdAction(Integer cdAcao) {
		this.cdAction = cdAcao;
	}
	public String getDescAcao() {
		return descAcao;
	}
	public void setDescAcao(String descAcao) {
		this.descAcao = descAcao;
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
		builder.append("Action [id=");
		builder.append(cdAction);
		builder.append(", descAcao=");
		builder.append(descAcao);
		builder.append(", fgStatusRegistro=");
		builder.append(fgStatusRegistro);
		builder.append(", actionProfile=");
		builder.append("???");
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
