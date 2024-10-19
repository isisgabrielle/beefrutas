package interfaceJogo;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import elementosDinamicos.Abacate;
import elementosDinamicos.Acerola;
import elementosDinamicos.Amora;
import elementosDinamicos.Coco;
import elementosDinamicos.Fruta;
import elementosDinamicos.Goiaba;
import elementosDinamicos.Jogador;
import elementosDinamicos.Laranja;
import elementosDinamicos.Maracuja;
import floresta.Arvore;
import floresta.Floresta;
import floresta.Grama;
import floresta.Pedra;

public class JanelaPartida extends JFrame implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Jogador[] jogadores;
	private int[] variaveisInicializacao;
	private Floresta[][] ladrilho;
	private Fruta[] ladrilhoDinamico;	
	
	public JanelaPartida(int[] variaveisInicializacao){
		
		this.variaveisInicializacao = variaveisInicializacao;
		
		this.setTitle("BeeFrutas");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(665, 685);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.addKeyListener(this);
		this.setFocusable(true);
				
		int dimensao = variaveisInicializacao[0];
		int totalLadrilhos = dimensao*dimensao;
		int totalElementos = 0;
		for (int i = 1; i <= 14; i++) {
			totalElementos = totalElementos + variaveisInicializacao[i];}
		if (dimensao < 3 || dimensao > 25) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("A dimensão da floresta deve ser de no mínimo 3 ladrilhos e no máximo 25.");}
		if (variaveisInicializacao[8] < 1) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("A floresta deve conter pelo menos 1 maracujá.");}
		if (totalElementos > totalLadrilhos-2) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("O número total de elementos não pode ultrapassar a quantidade de ladrilhos da floresta.");}
		
		
		else {
			iniciarPartida();			
		}
	}
	
	private void iniciarPartida() {
		int dimensao = this.variaveisInicializacao[0];
		this.ladrilho = new Floresta[dimensao][dimensao];
		this.ladrilhoDinamico = new Fruta[dimensao*dimensao];
		this.jogadores = new Jogador[2];
		int k = 0;
			
		JLayeredPane camadas = new JLayeredPane();		
			
			
		JPanel panelDinamico = new JPanel();
		panelDinamico.setLayout(null);
		panelDinamico.setVisible(true);
		panelDinamico.setOpaque(false);
		panelDinamico.setSize(650, 650);
			
			
		JPanel panel = new JPanel(new GridLayout(dimensao, dimensao));
		panel.setSize(650, 650);
			
		MatrizTerreno jogo1 = new MatrizTerreno(this.variaveisInicializacao);
		jogo1.inicializarElementos();
		jogo1.mostrarTerreno();
			
		int tamanhoLadrilho = 650/dimensao;
		
	
		this.jogadores[0] = new Jogador(0, 0, tamanhoLadrilho, 0);
		panelDinamico.add(jogadores[0]);
		this.jogadores[1] = new Jogador(dimensao-1, dimensao-1, tamanhoLadrilho, 1);
		panelDinamico.add(jogadores[1]);
		
		
		for(int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				if (jogo1.elementosDinamicos[i][j] != "  " && jogo1.elementosDinamicos[i][j] != "j0" && jogo1.elementosDinamicos[i][j] != "j1") {
					switch (jogo1.elementosDinamicos[i][j]) {
					case "m ": this.ladrilhoDinamico[k] = new Maracuja(j, i, tamanhoLadrilho); break;
					case "l ": this.ladrilhoDinamico[k] = new Laranja(j, i, tamanhoLadrilho); break;
					case "a ": this.ladrilhoDinamico[k] = new Abacate(j, i, tamanhoLadrilho); break;
					case "c ": this.ladrilhoDinamico[k] = new Coco(j, i, tamanhoLadrilho); break;
					case "r ": this.ladrilhoDinamico[k] = new Acerola(j, i, tamanhoLadrilho); break;
					case "o ": this.ladrilhoDinamico[k] = new Amora(j, i, tamanhoLadrilho); break;
					case "g ": this.ladrilhoDinamico[k] = new Goiaba(j, i, tamanhoLadrilho); break;
					}
					panelDinamico.add(this.ladrilhoDinamico[k]);	
					k++;
				}
					
				switch (jogo1.elementosEstaticos[i][j]) {
				case "g ": this.ladrilho[i][j] = new Grama(tamanhoLadrilho); break;
				case "P ": this.ladrilho[i][j] = new Pedra(tamanhoLadrilho); break;
				case "AL": this.ladrilho[i][j] = new Arvore("Laranja", tamanhoLadrilho); break;
				case "AA": this.ladrilho[i][j] = new Arvore("Abacate", tamanhoLadrilho); break;
				case "AC": this.ladrilho[i][j] = new Arvore("Coco", tamanhoLadrilho); break;
				case "AR": this.ladrilho[i][j] = new Arvore("Acerola", tamanhoLadrilho); break;
				case "AO": this.ladrilho[i][j] = new Arvore("Amora", tamanhoLadrilho); break;
				case "AG": this.ladrilho[i][j] = new Arvore("Goiaba", tamanhoLadrilho); break;
				}
				
				ladrilho[i][j].setBounds(tamanhoLadrilho, tamanhoLadrilho, tamanhoLadrilho, tamanhoLadrilho);
				panel.add(ladrilho[i][j]);
			}
		}
		camadas.add(panel, Integer.valueOf(1));
		camadas.add(panelDinamico, Integer.valueOf(2));

		this.add(camadas);	
		this.revalidate();

			
	}

	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP: jogadores[0].moverJogador(0, -1);; break;
		case KeyEvent.VK_DOWN: jogadores[0].moverJogador(0, 1);; break;
		case KeyEvent.VK_RIGHT: jogadores[0].moverJogador(1, 0);; break;
		case KeyEvent.VK_LEFT: jogadores[0].moverJogador(-1, 0);; break;

		}
		
		if(e.getKeyCode() == KeyEvent.VK_1) {
			System.out.println("Hello World");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
				
	}

}