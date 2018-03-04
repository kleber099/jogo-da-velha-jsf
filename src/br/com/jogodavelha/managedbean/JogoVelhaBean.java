package br.com.jogodavelha.managedbean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.event.AjaxBehaviorEvent;

import br.com.jogodavelha.business.EmpateException;
import br.com.jogodavelha.business.Jogo;
import br.com.jogodavelha.business.VencedorException;
import br.com.jogodavelha.model.Jogador;

@ManagedBean
@ViewScoped
public class JogoVelhaBean {

	@ManagedProperty("#{jogo}")
	private Jogo jogo;

	private Jogador jogador1 = new Jogador("O", 1);
	private Jogador jogador2 = new Jogador("X", -1);
	private Jogador jogadorAtual = null;

	private String saida = "";

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public Jogador getJogadorAtual() {
		return jogadorAtual;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public String getSaida() {
		return saida;
	}

	public void iniciar() {
		this.jogadorAtual = jogo.iniciarJogo(this.jogador1, this.jogador2);

		this.saida = "É a vez de " + this.jogadorAtual.getNome() + " jogar";
	}

	public void jogar(AjaxBehaviorEvent event) {
		UICommand command = (UICommand) event.getSource();
		Map<String, Object> atributos = (Map<String, Object>) command.getAttributes();

		Integer linha = Integer.parseInt(atributos.get("linha").toString());
		Integer coluna = Integer.parseInt(atributos.get("coluna").toString());

		Jogador aux = this.jogadorAtual;

		try {
			this.jogadorAtual = this.jogo.jogar(linha, coluna);
			this.saida = "É a vez de " + this.jogadorAtual.getNome() + " jogar";
			
		} catch (VencedorException e) {
			this.jogadorAtual = null;
			this.saida = "O jogador " + aux.getNome() + " é o vencedor";

		} catch (EmpateException e) {
			
			this.saida = "Empate!!!";
		} finally {

			atributos.put("disabled", true);
			command.setValue(aux.getSimbolo());
		}
	}

}
