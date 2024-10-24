package interfaceJogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
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
import elementosDinamicos.Mochila;
import floresta.Arvore;
import floresta.Floresta;
import floresta.Grama;
import floresta.Pedra;

public class JanelaPartida extends JFrame implements KeyListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Jogador[] jogadores;
	private int[] variaveisInicializacao;
	private Floresta[][] ladrilho;
	private Fruta[] ladrilhoDinamico;	
	private int vez;
	private JLabel labelVezJogador;
	private JLabel labelPontosMovimento;
	private JLabel labelPontosVitoria;
	private boolean dadosRolados = false;
	Dados dado1 = new Dados();
	Dados dado2 = new Dados();
	
	public JanelaPartida(int[] variaveisInicializacao){
		
		this.variaveisInicializacao = variaveisInicializacao;
		
		this.setTitle("BeeFrutas");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(665, 730);
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
			vez = 1;
			iniciarPartida();			
		}
	}
	
	private void iniciarPartida() {
		int dimensao = this.variaveisInicializacao[0];
		int k = 0;
		this.ladrilho = new Floresta[dimensao][dimensao];
		this.ladrilhoDinamico = new Fruta[dimensao*dimensao];
		this.jogadores = new Jogador[2];
		
			
		JLayeredPane camadas = new JLayeredPane();			
			
		JPanel panelDinamico = new JPanel();
		panelDinamico.setLayout(null);
		panelDinamico.setVisible(true);
		panelDinamico.setOpaque(false);
		panelDinamico.setSize(650, 650);
			
		JPanel panel = new JPanel(new GridLayout(dimensao, dimensao));
		panel.setSize(650, 650);
		
		JPanel painelSuperior = new JPanel();
		Color verdeEscuro = new Color(0, 100, 0);
	    painelSuperior.setPreferredSize(new Dimension(400, 28));
	    painelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 6));
	    this.setLayout(new BorderLayout());
	    this.add(painelSuperior, BorderLayout.NORTH);
	   	    
	    labelPontosVitoria = new JLabel("Pontos vitoria: ");
	    labelPontosVitoria.setForeground(verdeEscuro); 
	
	    this.setVisible(true);
	    
	    
	    
	   
	    
		JPanel panelDados = new JPanel();
	    panelDados.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
	    dado1.setPreferredSize(new Dimension(25, 25)); 
	    dado2.setPreferredSize(new Dimension(25, 25));
	    labelVezJogador = new JLabel("Vez de: " + vez);
	    labelPontosMovimento = new JLabel("	Pontos Movimento: " + pontosMovimento);
	    labelVezJogador.setPreferredSize(new Dimension(60, 25));
	    labelPontosMovimento.setPreferredSize(new Dimension(125, 25));
	    labelVezJogador.setForeground(verdeEscuro);
	    labelPontosMovimento.setForeground(verdeEscuro);
	    
	    this.add(panelDados, BorderLayout.SOUTH); 
	    panelDados.add(labelVezJogador);
	    panelDados.add(labelPontosMovimento);
	    panelDados.add(dado1);
	    panelDados.add(dado2);
	   
	    JButton rollButton = new JButton("Rolar dados");
	    rollButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

	            if (pontosMovimento == 0 && !dadosRolados) {  
	                dado1.rolarDado(); 
	                dado2.rolarDado();
	                pontosMovimento = dado1.getValorFace() + dado2.getValorFace();
	                dadosRolados = true;
	                labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento);
	                verificaCoco(vez); 
	                JanelaPartida.this.requestFocusInWindow();}
	            else {
	                JOptionPane.showMessageDialog(null, "Você precisa concluir seus movimentos para rolar o dado.", "ERRO", JOptionPane.WARNING_MESSAGE);
	                JanelaPartida.this.requestFocusInWindow(); }
	        }
	    });

	    rollButton.setPreferredSize(new Dimension(110, 25)); 
	    panelDados.add(rollButton);
	    this.setVisible(true);
	    
	    JButton concluirTurno = new JButton("Concluir Turno");
	    concluirTurno.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

	            pontosMovimento = 0; 
	            labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento);
	            vez = inverterVez(vez); 
	            labelVezJogador.setText("Vez de: " + vez); 
	            dadosRolados = false; 
	            JanelaPartida.this.requestFocusInWindow(); 
	        }
	    });

	    concluirTurno.setPreferredSize(new Dimension(120, 25)); 
	    panelDados.add(concluirTurno);
	    this.setVisible(true);
	    
		JPanel rodape = new JPanel();
	    rodape.setBackground(Color.GRAY);  
	    rodape.setPreferredSize(new Dimension(400, 30)); 
        rodape.setLayout(new BorderLayout()); 
        rodape.add(panelDados, BorderLayout.CENTER);
			
		MatrizTerreno jogo1 = new MatrizTerreno(this.variaveisInicializacao);
		jogo1.inicializarElementos();
		//jogo1.mostrarTerreno();
			
		int tamanhoLadrilho = 650/dimensao;
		
		int capacidadeMochila = this.variaveisInicializacao[15];
		
		Mochila mochilaJogador0 = new Mochila(capacidadeMochila,"Jogador0", 18);
	    Mochila mochilaJogador1 = new Mochila(capacidadeMochila,"Jogador1", 18);
	    
	    painelSuperior.add(labelPontosVitoria);
	    painelSuperior.add(mochilaJogador0); 
	    painelSuperior.add(mochilaJogador1); 

		this.jogadores[0] = new Jogador(0, 0, tamanhoLadrilho, 0, "Ciclano", capacidadeMochila);
		panelDinamico.add(jogadores[0]);
		this.jogadores[1] = new Jogador(dimensao-1, dimensao-1, tamanhoLadrilho, 1, "Fulano", capacidadeMochila);
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
				ladrilho[i][j].addActionListener(this);
				panel.add(ladrilho[i][j]);
			}
		}
		
		camadas.add(panel, Integer.valueOf(1));
		camadas.add(panelDinamico, Integer.valueOf(2));

		this.add(camadas,BorderLayout.CENTER);
		this.add(rodape, BorderLayout.SOUTH);
		this.revalidate();
			
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private int pontosMovimento = 0;
	
	public void keyPressed(KeyEvent e) {
		this.requestFocusInWindow();
		if (!dadosRolados) {
		   JOptionPane.showMessageDialog(null, "Na sua vez você precisa rolar os dados antes de se mover.", "ERRO", JOptionPane.WARNING_MESSAGE);
	        return; }
		
		if (pontosMovimento == 0) {
		   pontosMovimento = dado1.getValorFace() + dado2.getValorFace();}
		
		if(pontosMovimento > 0) {
		  switch(e.getKeyCode()) {
		  case KeyEvent.VK_UP: jogadores[vez].moverJogador(0, -1, variaveisInicializacao[0]); colocaFrutasNaMochila(); moverFrutasMochila();  
		  verificaCoco(vez); verificaMaracuja(vez); break;
		  case KeyEvent.VK_DOWN: jogadores[vez].moverJogador(0, 1, variaveisInicializacao[0]); colocaFrutasNaMochila();moverFrutasMochila();
          verificaCoco(vez);verificaMaracuja(vez);  break;
		  case KeyEvent.VK_RIGHT: jogadores[vez].moverJogador(1, 0, variaveisInicializacao[0]); colocaFrutasNaMochila();moverFrutasMochila();	   
		  verificaCoco(vez);verificaMaracuja(vez);  break;
		  case KeyEvent.VK_LEFT: jogadores[vez].moverJogador(-1, 0, variaveisInicializacao[0]); colocaFrutasNaMochila();moverFrutasMochila(); 
          verificaCoco(vez);verificaMaracuja(vez); break;
		  
		  }
		
		pontosMovimento--;
		labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento);
		
        if (pontosMovimento == 0) {
        	vez = inverterVez(vez);
        	labelVezJogador.setText("Vez de: " + vez);
        	dadosRolados = false; }
		}
		
		  
	}

	@Override
	public void keyReleased(KeyEvent e) {
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Vc pressionou o botão");
		this.requestFocus();
	}
	
	private int inverterVez(int vez) {
		int tempVez = 0;
		if (vez == 0) {
			tempVez = 1;
		}
		if (vez == 1) {
			tempVez = 0;
		}
		return tempVez;
	}

	private void colocaFrutasNaMochila() {
	    for (Fruta fruta : this.ladrilhoDinamico) {
	        if (fruta != null) {
	            
	            if (fruta.x == jogadores[0].x && fruta.y == jogadores[0].y) {
	                boolean adicionada = jogadores[0].getMochila().adicionarFruta(fruta);
	                if (adicionada) {
	                    fruta.setVisible(false);  
	                    fruta.estaNaMochila = 0;
	                }
	            }
	            
	            if (fruta.x == jogadores[1].x && fruta.y == jogadores[1].y) {
	                boolean adicionada = jogadores[1].getMochila().adicionarFruta(fruta);
	                if (adicionada) {
	                    fruta.setVisible(false);  
	                    fruta.estaNaMochila = 1;
	                }
	            }
	        }
	    }
	}

	private void moverFrutasMochila() {
	    
	    for (Fruta fruta : this.ladrilhoDinamico) {
	        if (fruta != null) {
	            
	            if (fruta.estaNaMochila == 0) {
	                fruta.setVisible(false);  
	                fruta.moverFrutaPara(jogadores[0].x, jogadores[0].y);  
	            }
	            
	            else if (fruta.estaNaMochila == 1) {
	                fruta.setVisible(false);  
	                fruta.moverFrutaPara(jogadores[1].x, jogadores[1].y);  
	            }
	        }
	    }
	}
	private void verificaCoco(int jogadorIndex) {
	    for (Fruta fruta : this.ladrilhoDinamico) {
	        if (fruta instanceof Coco && !fruta.isFoiColetada()) {
	            if (fruta.x == jogadores[jogadorIndex].x && fruta.y == jogadores[jogadorIndex].y) {
	                pontosMovimento *= 2; 
	                labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento); 
	                JOptionPane.showMessageDialog(null, "Você encontrou um Coco, pontos de movimento duplicados.", "Coco!", JOptionPane.INFORMATION_MESSAGE);
	                fruta.setFoiColetada(true);
	            }
	        }
	    }
	}
	private int pontosVitoria = 0;
	private void verificaMaracuja(int jogadorIndex) {
	    for (Fruta fruta : this.ladrilhoDinamico) {
	        if (fruta instanceof Maracuja && !fruta.isFoiColetada()) {
	            if (fruta.x == jogadores[jogadorIndex].x && fruta.y == jogadores[jogadorIndex].y) {
	                pontosVitoria ++; 
	                labelPontosVitoria.setText("Pontos Vitoria: " + pontosVitoria); 
	                JOptionPane.showMessageDialog(null, "Você encontrou um Maracujá, +1 ponto vitoria.", "Maracuja!", JOptionPane.INFORMATION_MESSAGE);
	                fruta.setFoiColetada(true);
	            }
	        }
	    }
	}

}

