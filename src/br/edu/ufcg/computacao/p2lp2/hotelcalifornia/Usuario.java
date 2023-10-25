package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.Objects;

/**
 * Classe que cria um Usuário no sistema
 * 
 * @author Ana Laura Barros de Melo
 */

public class Usuario {
	private String nome;
	private String tipoUsuario;
	private long documento;
	private String id;

	public Usuario(String nome, String tipoUsuario, long documento) {
		if (nome == null || tipoUsuario == null) {
			throw new NullPointerException("PARÂMETRO INVÁLIDO!");
		}

		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.documento = documento;
		this.id = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipoUsuario;
	}

	public void setTipo(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNome() {
		return nome;
	}

	public long getDocumento() {
		return documento;
	}

	@Override

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	public String toString() {
		return "[" + id + "] " + nome + " (No. Doc. " + documento + ")";
	}
}