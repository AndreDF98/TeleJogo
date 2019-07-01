package framework;

import java.awt.Color;

public class ConfigGrafica {

	private static ConfigGrafica unicaInstanciacao;
	
	int ALTURA;
	int LARGURA;
	
	private Color corObstaculo;
	private Color corJogador;
	private Color corBola;
	private Color corBorda;
	private Color corFundo;
	private Color corPlacar;
	
	private ConfigGrafica() {
		ALTURA = 800;
		LARGURA = 1200;
		
		corObstaculo = Color.WHITE;
		corJogador = Color.WHITE;
		corBola = Color.WHITE;
		corBorda = Color.BLACK;
		corFundo = Color.BLACK;
		corPlacar = Color.WHITE;
		
	}
	
	public static ConfigGrafica Config() {
		if(unicaInstanciacao == null) {
			unicaInstanciacao = new ConfigGrafica();
		}
		
		return unicaInstanciacao;
		
	}
	
	public void defineCor(Color velha, Color nova) {
		velha = nova;
	}
	
	public Color corObstaculo() {
		return corObstaculo;
	}
	
	public Color corJogador() {
		return corJogador;
	}
	
	public Color corBola() {
		return corBola;
	}
	
	public Color corBorda() {
		return corBorda;
	}
	
	public Color corFundo() {
		return corFundo;
	}
	
	public Color corPlacar() {
		return corPlacar;
	}
	
}
