package spring.jpa.eclipselink.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "usuarioAutorizacao")
@Table(name = "SUE_USUARIO_AUTORIZADO")
public class UsuarioAutorizacao extends AbstractPersistent<Long> implements
		Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CD_USUARIO")
	private Long id;

	@Transient
	private UsuarioAutenticacao usuarioAutenticacao;

	@OneToMany(mappedBy = "usuarioAutorizacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UsuarioPerfilRecurso> matrizAutorizacoes;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "NO_EMAIL")
	private String email;

	@Column(name = "NO_USUARIO")
	private String nomeUsuario;

	@Column(name = "IN_STATUS_USUARIO")
	private String statusUsuario;

	@Column(name = "IN_TIPO_DOCUMENTO")
	private String tipoDocumento;

	@Column(name = "NU_DOCUMENTO_USUARIO")
	private String nuDocumento;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_NASCIMENTO")
	private Date dtNascimento;

	public UsuarioAutenticacao getUsuarioAutenticacao() {
		return usuarioAutenticacao;
	}

	public void setUsuarioAutenticacao(UsuarioAutenticacao usuarioAutenticacao) {
		this.usuarioAutenticacao = usuarioAutenticacao;
	}

	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}

	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}

	public String getUsuarioManutencao() {
		return usuarioManutencao;
	}

	public void setUsuarioManutencao(String usuarioManutencao) {
		this.usuarioManutencao = usuarioManutencao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(String statusUsuario) {
		this.statusUsuario = statusUsuario;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNuDocumento() {
		return nuDocumento;
	}

	public void setNuDocumento(String nuDocumento) {
		this.nuDocumento = nuDocumento;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public List<UsuarioPerfilRecurso> getMatrizAutorizacoes() {
		return matrizAutorizacoes;
	}

	public void setMatrizAutorizacoes(
			List<UsuarioPerfilRecurso> matrizAutorizacoes) {
		this.matrizAutorizacoes = matrizAutorizacoes;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsuarioAutorizacao [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", email=");
		builder.append(email);
		builder.append(", nomeUsuario=");
		builder.append(nomeUsuario);
		builder.append(", statusUsuario=");
		builder.append(statusUsuario);
		builder.append(", tipoDocumento=");
		builder.append(tipoDocumento);
		builder.append(", nuDocumento=");
		builder.append(nuDocumento);
		builder.append(", dtNascimento=");
		builder.append(dtNascimento);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public UsuarioAutorizacao clone() throws CloneNotSupportedException {
		return (UsuarioAutorizacao) super.clone();
	}

}
