package br.com.jogodavelha.model;

public class Jogador {

	private String nome;
	private String simbolo;
	private Integer valor;

	public Jogador() {

	}

	public Jogador(String simbobo, Integer valor) {
		this.simbolo = simbobo;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
}
