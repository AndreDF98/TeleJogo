package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import jogoParedao.Paredao;
import jogosFut.CriaRedeFut;
import jogosFut.FutLocal;
import jogosFut.FutTreino;
import jogosTenis.CriaRedeTenis;
import jogosTenis.TenisLocal;
import jogosTenis.TenisTreino;

public class Controlador {

	Visao visao;
	Modelo modelo;
	
	int escolhaTreino = 0;
	int escolhaVelocidade = 0;
	int escolhaTamanho = 0;
	int escolhaOnline = 0;
	
	public Controlador() {
		
		modelo = new Modelo();
		visao = new Visao(modelo);
		
		modelo.treino.addActionListener(mudaTreino);
		modelo.velocidade.addActionListener(mudaVelocidade);
		modelo.tamanho.addActionListener(mudaTamanho);
		modelo.online.addActionListener(mudaOnline);
		
		modelo.trocaJogo.addActionListener(mudaJogo);
		modelo.jogar.addActionListener(comecaJogo);
		
	}
	
	ActionListener mudaTreino = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaTreino = (escolhaTreino + 1) % 2;
			visao.mudaTreino(escolhaTreino);
		}
	};
	
	ActionListener mudaVelocidade = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaVelocidade = (escolhaVelocidade + 1) % 4;
			visao.mudaVelocidade(escolhaVelocidade);
		}
	};
	
	ActionListener mudaTamanho = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaTamanho = (escolhaTamanho + 1) % 3;
			visao.mudaTamanho(escolhaTamanho);
		}
	};
	
	ActionListener mudaOnline = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			escolhaOnline = (escolhaOnline + 1) % 2;
			visao.mudaOnline(escolhaOnline);
		}
	};
	
	ActionListener mudaJogo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(modelo.paredao.isSelected()) modelo.futebol.setSelected(true);
			else if(modelo.tenis.isSelected()) modelo.paredao.setSelected(true);
			else modelo.tenis.setSelected(true);
		}
	};
	
	ActionListener comecaJogo = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(modelo.paredao.isSelected()) new Paredao(escolhaTamanho, escolhaVelocidade);
			else if(modelo.tenis.isSelected()){
				if(escolhaOnline == 1) new CriaRedeTenis(escolhaTamanho, escolhaVelocidade);
				else {
					if(escolhaTreino == 0) new TenisLocal(escolhaTamanho, escolhaVelocidade);
					else new TenisTreino(escolhaTamanho, escolhaVelocidade);
				}
			}
			else {
				if(escolhaOnline == 1) new CriaRedeFut(escolhaTamanho, escolhaVelocidade);
				else {
					if(escolhaTreino == 0) new FutLocal(escolhaTamanho, escolhaVelocidade);
					else new FutTreino(escolhaTamanho, escolhaVelocidade);
				}
			}
		}
	};
	
}
