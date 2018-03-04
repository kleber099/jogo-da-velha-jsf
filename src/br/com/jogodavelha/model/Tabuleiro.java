package br.com.jogodavelha.model;

public class Tabuleiro {
	private Integer casas[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

	public void marcar(Integer linha, Integer coluna, Integer valor) {
		this.casas[linha][coluna] = valor;
	}

	public Integer checarTabuleiro() {
		Integer total = checarLinhas();

		if (total != 0) {
			return total;
		}

		total = checarColunas();

		if (total != 0) {
			return total;
		}

		total = checarDiagonais();

		return total;
	}

	public boolean isEmpate() {
		for (int linha = 0; linha < 3; linha++) {
			for (int coluna = 0; coluna < 3; coluna++) {
				if (casas[linha][coluna] == 0) {
					return false;
				}
			}
		}

		return true;

	}

	private Integer checarLinhas() {
		for (int linha = 0; linha < 3; linha++) {
			Integer total = casas[linha][0] + casas[linha][1] + casas[linha][2];

			if (total == -3 || total == 3) {
				return total;
			}
		}

		return 0;
	}

	private int checarColunas() {
		for (int coluna = 0; coluna < 3; coluna++) {
			Integer total = casas[0][coluna] + casas[1][coluna] + casas[2][coluna];

			if (total == -3 || total == 3) {
				return total;
			}

		}

		return 0;
	}

	private int checarDiagonais() {
		Integer total = casas[0][0] + casas[1][1] + casas[2][2];
		if (total == -3 || total == 3) {
			return total;
		}

		total = casas[0][2] + casas[1][1] + casas[2][0];
		if (total == -3 || total == 3) {
			return total;
		}

		return 0;
	}
}
