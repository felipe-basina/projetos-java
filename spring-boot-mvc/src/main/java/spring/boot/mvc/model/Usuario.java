package spring.boot.mvc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Usuario {

    @NotNull()
    @Size(min = 5, message = "{Size.min.msg}")
    private String nome;

    @NotNull()
    @Size(min = 4, message = "{Size.min.msg}")
    private String ultimoNome;

    @NotEmpty(message = "{NotEmpty.msg}")
    private String telefone;

    public Usuario() {

    }

    public String getNome() {
	if (nome != null) {
	    return nome.toUpperCase();
	}
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getUltimoNome() {
	if (ultimoNome != null) {
	    return ultimoNome.toUpperCase();
	}
	return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
	this.ultimoNome = ultimoNome;
    }

    public String getTelefone() {
	return telefone;
    }

    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result
		+ ((telefone == null) ? 0 : telefone.hashCode());
	result = prime * result
		+ ((ultimoNome == null) ? 0 : ultimoNome.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Usuario other = (Usuario) obj;
	if (nome == null) {
	    if (other.nome != null)
		return false;
	} else if (!nome.equals(other.nome))
	    return false;
	if (telefone == null) {
	    if (other.telefone != null)
		return false;
	} else if (!telefone.equals(other.telefone))
	    return false;
	if (ultimoNome == null) {
	    if (other.ultimoNome != null)
		return false;
	} else if (!ultimoNome.equals(other.ultimoNome))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Usuario [nome=");
	builder.append(nome);
	builder.append(", ultimoNome=");
	builder.append(ultimoNome);
	builder.append(", telefone=");
	builder.append(telefone);
	builder.append("]");
	return builder.toString();
    }

}
