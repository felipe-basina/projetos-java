package spring.jpa.eclipselink.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name = "usuarioAutenticacao")
@Table(name = "SUE_USUARIO_AUTENTICADO", schema = "SUE_C")
public class UsuarioAutenticacao extends AbstractPersistent<Long> implements
		Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_SEQ_USUARIO_AUTENT")
	@SequenceGenerator(name = "ID_SEQ_USUARIO_AUTENT", sequenceName = "SUE_USUARIO_SQ01", allocationSize = 1)
	@Column(name = "CD_USUARIO")
	private Long id;

	@Transient
	private UsuarioAutorizacao usuarioAutorizacao;

	@Column(name = "CD_USUARIO_CRIACAO")
	private String usuarioCriacao;

	@Column(name = "CD_USUARIO_MANUT")
	private String usuarioManutencao;

	@Column(name = "CD_LOGIN_TEMP")
	private String loginTemporario;

	@Column(name = "CD_LOGIN")
	private String login;

	@Column(name = "TX_SENHA")
	private String senha;

	@Column(name = "TX_SENHA_TEMP")
	private String senhaTemporaria;

	@Column(name = "IN_STATUS_SENHA")
	private Character statusSenha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ULTIMA_ALTERACAO_SENHA")
	private Date dtUltimaAlteracaoSenha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_EXPIRACAO_SENHA")
	private Date dtExpiracaoSenha;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_EXPIRACAO_SENHA_TEMP")
	private Date dtExpiracaoSenhaTemporaria;

	@Column(name = "IN_STATUS_ULTIMO_ACESSO")
	private Character statusUltimoAcesso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_ULTIMO_ACESSO")
	private Date dtUltimoAcesso;

	@Column(name = "QT_TENTATIVAS_FALHA_ACESSO")
	private Long qtTentativasFalhaAcesso;

	@Column(name = "QT_TENTATIVAS_FALHA_RESET")
	private Long qtTentativasFalhaReset;

	@Column(name = "QT_TOTAL_ACESSOS")
	private Long qtTotalAcessos;

	@Column(name = "QT_PARCIAL_ACESSOS")
	private Long qtParcialAcessos;

	@Column(name = "QT_TOTAL_RESETS")
	private Long qtTotalResets;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_INICIO_ULTIMO_BLOQUEIO")
	private Date dtInicioUltimoBloqueio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_FIM_ULTIMO_BLOQUEIO")
	private Date dtFimUltimoBloqueio;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Character getStatusSenha() {
		return statusSenha;
	}

	public void setStatusSenha(Character statusSenha) {
		this.statusSenha = statusSenha;
	}

	public Date getDtUltimaAlteracaoSenha() {
		return dtUltimaAlteracaoSenha;
	}

	public void setDtUltimaAlteracaoSenha(Date dtUltimaAlteracaoSenha) {
		this.dtUltimaAlteracaoSenha = dtUltimaAlteracaoSenha;
	}

	public Date getDtExpiracaoSenha() {
		return dtExpiracaoSenha;
	}

	public void setDtExpiracaoSenha(Date dtExpiracaoSenha) {
		this.dtExpiracaoSenha = dtExpiracaoSenha;
	}

	public Character getStatusUltimoAcesso() {
		return statusUltimoAcesso;
	}

	public void setStatusUltimoAcesso(Character statusUltimoAcesso) {
		this.statusUltimoAcesso = statusUltimoAcesso;
	}

	public Date getDtUltimoAcesso() {
		return dtUltimoAcesso;
	}

	public void setDtUltimoAcesso(Date dtUltimoAcesso) {
		this.dtUltimoAcesso = dtUltimoAcesso;
	}

	public Long getQtTentativasFalhaAcesso() {
		return qtTentativasFalhaAcesso;
	}

	public void setQtTentativasFalhaAcesso(Long qtTentativasFalhaAcesso) {
		this.qtTentativasFalhaAcesso = qtTentativasFalhaAcesso;
	}

	public Long getQtTentativasFalhaReset() {
		return qtTentativasFalhaReset;
	}

	public void setQtTentativasFalhaReset(Long qtTentativasFalhaReset) {
		this.qtTentativasFalhaReset = qtTentativasFalhaReset;
	}

	public Long getQtTotalAcessos() {
		return qtTotalAcessos;
	}

	public void setQtTotalAcessos(Long qtTotalAcessos) {
		this.qtTotalAcessos = qtTotalAcessos;
	}

	public Long getQtParcialAcessos() {
		return qtParcialAcessos;
	}

	public void setQtParcialAcessos(Long qtParcialAcessos) {
		this.qtParcialAcessos = qtParcialAcessos;
	}

	public Long getQtTotalResets() {
		return qtTotalResets;
	}

	public void setQtTotalResets(Long qtTotalResets) {
		this.qtTotalResets = qtTotalResets;
	}

	public Date getDtInicioUltimoBloqueio() {
		return dtInicioUltimoBloqueio;
	}

	public void setDtInicioUltimoBloqueio(Date dtInicioUltimoBloqueio) {
		this.dtInicioUltimoBloqueio = dtInicioUltimoBloqueio;
	}

	public Date getDtFimUltimoBloqueio() {
		return dtFimUltimoBloqueio;
	}

	public void setDtFimUltimoBloqueio(Date dtFimUltimoBloqueio) {
		this.dtFimUltimoBloqueio = dtFimUltimoBloqueio;
	}

	public UsuarioAutorizacao getUsuarioAutorizacao() {
		return usuarioAutorizacao;
	}

	public void setUsuarioAutorizacao(UsuarioAutorizacao usuarioAutorizacao) {
		this.usuarioAutorizacao = usuarioAutorizacao;
	}

	public String getLoginTemporario() {
		return loginTemporario;
	}

	public void setLoginTemporario(String loginTemporario) {
		this.loginTemporario = loginTemporario;
	}

	public String getSenhaTemporaria() {
		return senhaTemporaria;
	}

	public void setSenhaTemporaria(String senhaTemporaria) {
		this.senhaTemporaria = senhaTemporaria;
	}

	public Date getDtExpiracaoSenhaTemporaria() {
		return dtExpiracaoSenhaTemporaria;
	}

	public void setDtExpiracaoSenhaTemporaria(Date dtExpiracaoSenhaTemp) {
		this.dtExpiracaoSenhaTemporaria = dtExpiracaoSenhaTemp;
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
		builder.append("UsuarioAutenticacao [id=");
		builder.append(id);
		builder.append(", usuarioCriacao=");
		builder.append(usuarioCriacao);
		builder.append(", usuarioManutencao=");
		builder.append(usuarioManutencao);
		builder.append(", login=");
		builder.append(login);
		builder.append(", loginTemporario=");
		builder.append(loginTemporario);
		builder.append(", senha=");
		builder.append(senha);
		builder.append(", senhaTemporaria=");
		builder.append(senhaTemporaria);
		builder.append(", statusSenha=");
		builder.append(statusSenha);
		builder.append(", dtUltimaAlteracaoSenha=");
		builder.append(dtUltimaAlteracaoSenha);
		builder.append(", dtExpiracaoSenha=");
		builder.append(dtExpiracaoSenha);
		builder.append(", statusUltimoAcesso=");
		builder.append(statusUltimoAcesso);
		builder.append(", dtUltimoAcesso=");
		builder.append(dtUltimoAcesso);
		builder.append(", qtTentativasFalhaAcesso=");
		builder.append(qtTentativasFalhaAcesso);
		builder.append(", qtTentativasFalhaReset=");
		builder.append(qtTentativasFalhaReset);
		builder.append(", qtTotalAcessos=");
		builder.append(qtTotalAcessos);
		builder.append(", qtParcialAcessos=");
		builder.append(qtParcialAcessos);
		builder.append(", qtTotalResets=");
		builder.append(qtTotalResets);
		builder.append(", dtInicioUltimoBloqueio=");
		builder.append(dtInicioUltimoBloqueio);
		builder.append(", dtFimUltimoBloqueio=");
		builder.append(dtFimUltimoBloqueio);
		builder.append(", dtExpiracaoSenhaTemp=");
		builder.append(dtExpiracaoSenhaTemporaria);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public UsuarioAutenticacao clone() throws CloneNotSupportedException {

		UsuarioAutenticacao usuarioAutenticacao = new UsuarioAutenticacao();

		usuarioAutenticacao.setCreationDate(this.getCreationDate());
		usuarioAutenticacao.setDtExpiracaoSenha(this.getDtExpiracaoSenha());
		usuarioAutenticacao.setDtExpiracaoSenhaTemporaria(this
				.getDtExpiracaoSenhaTemporaria());
		usuarioAutenticacao.setDtFimUltimoBloqueio(this
				.getDtFimUltimoBloqueio());
		usuarioAutenticacao.setDtInicioUltimoBloqueio(this
				.getDtInicioUltimoBloqueio());
		usuarioAutenticacao.setDtUltimaAlteracaoSenha(this
				.getDtUltimaAlteracaoSenha());
		usuarioAutenticacao.setDtUltimoAcesso(this.getDtUltimoAcesso());
		usuarioAutenticacao.setId(this.getId());
		usuarioAutenticacao.setLogin(this.getLogin());
		usuarioAutenticacao.setLoginTemporario(this.getLoginTemporario());
		usuarioAutenticacao.setQtParcialAcessos(this.getQtParcialAcessos());
		usuarioAutenticacao.setQtTentativasFalhaAcesso(this
				.getQtTentativasFalhaAcesso());
		usuarioAutenticacao.setQtTentativasFalhaReset(this
				.getQtTentativasFalhaReset());
		usuarioAutenticacao.setQtTotalAcessos(this.getQtTotalAcessos());
		usuarioAutenticacao.setQtTotalResets(this.getQtTotalResets());
		usuarioAutenticacao.setSenha(this.getSenha());
		usuarioAutenticacao.setSenhaTemporaria(this.getSenhaTemporaria());
		usuarioAutenticacao.setStatusSenha(this.getStatusSenha());
		usuarioAutenticacao.setStatusUltimoAcesso(this.getStatusUltimoAcesso());
		usuarioAutenticacao.setUpdateDate(this.getUpdateDate());
		usuarioAutenticacao.setUsuarioAutorizacao(this.getUsuarioAutorizacao());
		usuarioAutenticacao.setUsuarioCriacao(this.getUsuarioCriacao());
		usuarioAutenticacao.setUsuarioManutencao(this.getUsuarioManutencao());

		return usuarioAutenticacao;
	}
}
