package br.com.spring.jdbc;

public class Contato {

	private int id;

	private String email;

	private String nome;

	private String sobrenome;

	private String telefone;

	private Contato() {
		super();
	}

	private Contato(int id, String email, String nome, String sobrenome,
			String telefone) {
		super();
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contato [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", sobrenome=");
		builder.append(sobrenome);
		builder.append(", telefone=");
		builder.append(telefone);
		builder.append("]");
		return builder.toString();
	}

	public static class ContatoBuilder {
		private int id;
		private String email;
		private String nome;
		private String sobrenome;
		private String telefone;
	
		public ContatoBuilder addId(int id) {
			this.id = id;
			return this;
		}

		public ContatoBuilder addEmail(String email) {
			this.email = email;
			return this;
		}

		public ContatoBuilder addNome(String nome) {
			this.nome = nome;
			return this;
		}

		public ContatoBuilder addSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
			return this;
		}

		public ContatoBuilder addTelefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public Contato createContato() {
			return new Contato(id, email, nome, sobrenome, telefone);
		}
	}
	
}
