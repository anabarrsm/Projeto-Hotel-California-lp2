package br.edu.ufcg.computacao.p2lp2.hotelcalifornia;

import java.util.Objects;

/**
 * Classe que cria um Usuário no sistema
 * @author Ana Laura Barros de Melo
 */

public class Usuario {
	private String idAutenticacao;
	private String nome;
	private String tipoUsuario;
	private String documento;
	
	public Usuario(String idAutenticacao, String nome, String tipoUsuario, String documento) {
		
		if(idAutenticacao == null) {
			throw new NullPointerException("USUÁRIO INVÁLIDO - CAMPO ID NULO");
		}
		
		if(nome == null) {
			throw new NullPointerException("USUÁRIO INVÁLIDO - CAMPO NOME NULO");
		}
		
		if(tipoUsuario == null) {
			throw new NullPointerException("USUÁRIO INVÁLIDO - CAMPO TIPO VAZIO");
		}
		
		
		// pq tipo long n tem como passar null? dps conserto isso
		if(documento == null) {
			throw new NullPointerException("USUÁRIO INVÁLIDO - CAMPO DOCUMENTO VAZIO");
		}
		
		this.idAutenticacao = idAutenticacao;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.documento = documento;
	}
	
	public String getId() {
		return idAutenticacao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getTipo() {
		return tipoUsuario;
	}
	
	public void setTipo(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	@Override
	
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Usuario usuario)) return false;
		return Objects.equals(idAutenticacao, usuario.idAutenticacao);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(idAutenticacao);
    }
	
	public String toString() {
		return "[" + idAutenticacao + "] " + nome + " (No. Doc. " + documento + ")";
	}
}