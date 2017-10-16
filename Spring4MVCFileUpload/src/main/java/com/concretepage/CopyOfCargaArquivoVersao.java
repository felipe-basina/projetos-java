package com.concretepage;

import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class CopyOfCargaArquivoVersao {

	private CommonsMultipartFile[] arquivos;

	private Date dtInicioVigencia;

	private Date dtFimVigencia;

	private String descricao;

	public CommonsMultipartFile[] getArquivos() {
		return arquivos;
	}

	public void setArquivos(CommonsMultipartFile[] arquivos) {
		this.arquivos = arquivos;
	}

	public Date getDtInicioVigencia() {
		return dtInicioVigencia;
	}

	public void setDtInicioVigencia(Date dtInicioVigencia) {
		this.dtInicioVigencia = dtInicioVigencia;
	}

	public Date getDtFimVigencia() {
		return dtFimVigencia;
	}

	public void setDtFimVigencia(Date dtFimVigencia) {
		this.dtFimVigencia = dtFimVigencia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CargaArquivoVersao [arquivos=");
		builder.append(Arrays.toString(arquivos));
		builder.append(", dtInicioVigencia=");
		builder.append(dtInicioVigencia);
		builder.append(", dtFimVigencia=");
		builder.append(dtFimVigencia);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append("]");
		return builder.toString();
	}

}