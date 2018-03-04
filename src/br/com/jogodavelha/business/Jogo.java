package br.com.jogodavelha.business;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.jogodavelha.model.Jogador;
import br.com.jogodavelha.model.Tabuleiro;

@ManagedBean
@ViewScoped
public class Jogo {

	private Jogador jogador1;
	private Jogador jogador2;
	private Jogador jogadorAtual;
	private Tabuleiro tabuleiro;

	public Jogo() {
		this.jogador1 = null;
		this.jogador2 = null;
		this.jogadorAtual = null;

		this.tabuleiro = new Tabuleiro();
	}

	public Jogador iniciarJogo(Jogador jogador1, Jogador jogador2) {
		this.jogador1 = jogador1;
		this.jogador2 = jogador2;

		Random r = new Random();
		int indice = r.nextInt(2);

		this.jogadorAtual = indice == 0 ? this.jogador1 : this.jogador2;

		return this.jogadorAtual;
	}

	public Jogador jogar(Integer linha, Integer coluna) throws VencedorException, EmpateException {

		tabuleiro.marcar(linha, coluna, this.jogadorAtual.getValor());

		Integer retorno = tabuleiro.checarTabuleiro();

		if (retorno == 0) {
			boolean isEmpate = tabuleiro.isEmpate();

			if (isEmpate == true) {
				throw new EmpateException();
			}

			this.trocarJogador();

		} else {
			throw new VencedorException();
		}

		return this.jogadorAtual;
	}

	private void trocarJogador() {
		this.jogadorAtual = this.jogadorAtual == jogador1 ? jogador2 : jogador1;
	}
}
