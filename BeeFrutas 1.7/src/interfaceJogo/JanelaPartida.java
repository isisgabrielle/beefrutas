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
import floresta.Arvore;
import floresta.Floresta;
import floresta.Grama;
import floresta.Pedra;

public class JanelaPartida extends JFrame implements KeyListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jogador[] jogadores;
	private Mochila[] mochilas = new Mochila[2];
	private int[] variaveisInicializacao;
	private String[] nomeJogadores;
	private Floresta[][] ladrilho;
	private Fruta[] ladrilhoDinamico;	
	private int dimensao;
	private int probBichada;
	private int vez = 1;
	private int cicloArvoreJog0 = -1;
	private int cicloArvoreJog1 = -1;
	private int contagemRodada = 0;
	BotaoFruta botaoMaracuja = new BotaoFruta("Maracuja");
    BotaoFruta botaoLaranja = new BotaoFruta("Laranja");
    BotaoFruta botaoAbacate = new BotaoFruta("Abacate");
    BotaoFruta botaoCoco = new BotaoFruta("Coco");
    BotaoFruta botaoAcerola = new BotaoFruta("Acerola");
    BotaoFruta botaoAmora = new BotaoFruta("Amora");
    BotaoFruta botaoGoiaba = new BotaoFruta("Goiaba");
    private int pontosMovimentoAntidoto = 0;
    private JLabel labelforcaJogadores = new JLabel();
    private JLabel labelMochilaDoJogador = new JLabel();
	private JLabel labelVezJogador = new JLabel();
	private JLabel labelPontosMovimento = new JLabel();
	private JLabel labelPontosVitoria = new JLabel();
	private JLabel labelPontosVitoria1 = new JLabel();
	private JLabel rodada;
	private boolean dadosRolados = false;
	Dados dado1 = new Dados();
	Dados dado2 = new Dados();
	
	public JanelaPartida(int[] variaveisInicializacao, String[] nomeJogadores){
		
		this.variaveisInicializacao = variaveisInicializacao;
		this.nomeJogadores = nomeJogadores;
		
		
		this.setTitle("BeeFrutas");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(665, 825);
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
		if (nomeJogadores[0].isBlank() || nomeJogadores[1].isBlank()) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("O nome dos jogadores deve ser definido.");}
		if (variaveisInicializacao[15] < (variaveisInicializacao[8] + variaveisInicializacao[17])/2 +1) {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("A capacidade da mochila deve ser igual ou maior do que a metade do total de maracujas dividida por 2 mais 1");}
		
		else {
			iniciarPartida();
			painelSuperior();
			painelDados();
		}
	}
	
	private void iniciarPartida() {
		this.dimensao = this.variaveisInicializacao[0];
		int k = 0;
		this.ladrilho = new Floresta[dimensao][dimensao];
		this.ladrilhoDinamico = new Fruta[dimensao*dimensao];
		this.jogadores = new Jogador[2];
		
			
		JLayeredPane camadas = new JLayeredPane();			
			
		JPanel panelDinamico = new JPanel();
		panelDinamico.setLayout(null);
		panelDinamico.setVisible(true);
		panelDinamico.setOpaque(false);
		panelDinamico.setBounds(0, 100, 650, 650);
			
		JPanel panel = new JPanel(new GridLayout(dimensao, dimensao));
		panel.setBounds(0, 100, 650, 650);
		
		
		MatrizTerreno jogo1 = new MatrizTerreno(this.variaveisInicializacao);
		
		jogo1.inicializarElementos();
		//jogo1.mostrarTerreno();
			
		int tamanhoLadrilho = 650/dimensao;
		
		this.jogadores[0] = new Jogador(0, 0, tamanhoLadrilho, 0, nomeJogadores[0]);
		panelDinamico.add(jogadores[0]);
		this.jogadores[1] = new Jogador(dimensao-1, dimensao-1, tamanhoLadrilho, 1, nomeJogadores[1]);
		panelDinamico.add(jogadores[1]);
		this.probBichada = variaveisInicializacao[16];
		
		for(int i = 0; i < dimensao; i++) {
			for(int j = 0; j < dimensao; j++) {
				if (jogo1.elementosDinamicos[i][j] != "  " && jogo1.elementosDinamicos[i][j] != "j0" && jogo1.elementosDinamicos[i][j] != "j1") {
					switch (jogo1.elementosDinamicos[i][j]) {
					case "m ": this.ladrilhoDinamico[k] = new Maracuja(j, i, tamanhoLadrilho, probBichada); break;
					case "l ": this.ladrilhoDinamico[k] = new Laranja(j, i, tamanhoLadrilho, probBichada); break;
					case "a ": this.ladrilhoDinamico[k] = new Abacate(j, i, tamanhoLadrilho,probBichada); break;
					case "c ": this.ladrilhoDinamico[k] = new Coco(j, i, tamanhoLadrilho,probBichada); break;
					case "r ": this.ladrilhoDinamico[k] = new Acerola(j, i, tamanhoLadrilho, probBichada); break;
					case "o ": this.ladrilhoDinamico[k] = new Amora(j, i, tamanhoLadrilho, probBichada); break;
					case "g ": this.ladrilhoDinamico[k] = new Goiaba(j, i, tamanhoLadrilho, probBichada); break;
					}
					panelDinamico.add(this.ladrilhoDinamico[k]);	
					k++;
				}
					
				switch (jogo1.elementosEstaticos[i][j]) {
				case "g ": this.ladrilho[i][j] = new Grama(tamanhoLadrilho); break;
				case "P ": this.ladrilho[i][j] = new Pedra(tamanhoLadrilho); break;
				case "AL": this.ladrilho[i][j] = new Arvore("Laranja", tamanhoLadrilho, i, j); break;
				case "AA": this.ladrilho[i][j] = new Arvore("Abacate", tamanhoLadrilho, i, j); break;
				case "AC": this.ladrilho[i][j] = new Arvore("Coco", tamanhoLadrilho, i, j); break;
				case "AR": this.ladrilho[i][j] = new Arvore("Acerola", tamanhoLadrilho, i, j); break;
				case "AO": this.ladrilho[i][j] = new Arvore("Amora", tamanhoLadrilho, i, j); break;
				case "AG": this.ladrilho[i][j] = new Arvore("Goiaba", tamanhoLadrilho, i, j); break;
				}
				
				ladrilho[i][j].setBounds(tamanhoLadrilho, tamanhoLadrilho, tamanhoLadrilho, tamanhoLadrilho);
				ladrilho[i][j].addActionListener(this);
				panel.add(ladrilho[i][j]);
			}
		}
		
		camadas.add(panel, Integer.valueOf(1));
		camadas.add(panelDinamico, Integer.valueOf(2));

		getContentPane().add(camadas,BorderLayout.CENTER);
		this.revalidate();
			
	}

	private void painelDados() { 
	
    
	JPanel panelDados = new JPanel();
	Color verdeEscuro = new Color(0, 100, 0);
    panelDados.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 4));
    dado1.setPreferredSize(new Dimension(25, 25)); 
    dado2.setPreferredSize(new Dimension(25, 25));
    this.labelVezJogador.setText("Vez de: " + jogadores[vez].nome);
    this.labelVezJogador.setPreferredSize(new Dimension(150, 25));
    labelVezJogador.setForeground(verdeEscuro);
    labelPontosMovimento = new JLabel("	Pontos Movimento: " + pontosMovimento);
    labelPontosMovimento.setPreferredSize(new Dimension(125, 25));
    labelPontosMovimento.setForeground(verdeEscuro);
    
    panelDados.add(this.labelVezJogador);
    panelDados.add(this.labelPontosMovimento);
    panelDados.add(dado1);
    panelDados.add(dado2);
    getContentPane().add(panelDados, BorderLayout.SOUTH); 
   
     
    JButton rolarDados = new JButton("Rolar dados");
    rolarDados.addActionListener(new ActionListener() {
        @Override
        
        public void actionPerformed(ActionEvent e) {
        		
            if (pontosMovimento == 0 && !dadosRolados) {  
            	frutaCaindoArvore();
            	contagemRodada++;
            	rodada.setText("Rodada: " + contagemRodada);
                dado1.rolarDado(); 
                dado2.rolarDado();
                pontosMovimento = dado1.getValorFace() + dado2.getValorFace();
                dadosRolados = true;
                labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento);
                JanelaPartida.this.requestFocusInWindow();}
            
            else {
                JOptionPane.showMessageDialog(null, "Você precisa concluir seus movimentos para rolar o dado.", "ERRO", JOptionPane.WARNING_MESSAGE);
                JanelaPartida.this.requestFocusInWindow(); } 
        } 

    });

    rolarDados.setPreferredSize(new Dimension(110, 25)); 
    panelDados.add(rolarDados);
    this.setVisible(true);
    
    JButton concluirTurno = new JButton("Concluir Turno");
    concluirTurno.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	if (ladrilho[jogadores[vez].y][jogadores[vez].x] instanceof Pedra) {
        		JOptionPane.showMessageDialog(null, "Você deve pular a pedra antes de concluir o turno.", "Maracujá!", JOptionPane.INFORMATION_MESSAGE);
        	}
        	else {
        		frutaCaindoArvore();
        		pontosMovimento = 0; 
                labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento);
                vez = inverterVez(vez); 
                labelVezJogador.setText("Vez de: " + jogadores[vez].nome); 
                dadosRolados = false; 
                atualizarPanels();
                
                if (ladrilho[jogadores[vez].y][jogadores[vez].x] instanceof Arvore) {
                	if (vez == 0) {
                		cicloArvoreJog0++;}
                	if (vez == 1) {
                		cicloArvoreJog1++;}}
                else {
                	if (vez == 0) {
                		cicloArvoreJog0 = -1;}
                	if (vez == 1) {
                		cicloArvoreJog1 = -1;}
                }
        	
        	JanelaPartida.this.requestFocusInWindow(); 
        }
    }});

    concluirTurno.setPreferredSize(new Dimension(120, 25)); 
    panelDados.add(concluirTurno);
    this.setVisible(true);
    
	JPanel rodape = new JPanel();
    rodape.setBackground(Color.GRAY);  
    rodape.setPreferredSize(new Dimension(400, 30)); 
    rodape.setLayout(new BorderLayout()); 
    rodape.add(panelDados, BorderLayout.CENTER);
    getContentPane().add(rodape, BorderLayout.SOUTH);
    this.revalidate();	
	}
	
	JPanel painelSuperior = new JPanel();
	
	private void painelSuperior() {
		
		this.mochilas[0] = new Mochila(variaveisInicializacao[15], 0, ladrilhoDinamico);
		this.mochilas[1] = new Mochila(variaveisInicializacao[15], 1, ladrilhoDinamico);

		Color verdeEscuro = new Color(0, 100, 0);
	    painelSuperior.setPreferredSize(new Dimension(400, 100));
	    painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 6));
	    this.setLayout(new BorderLayout());
	    this.add(painelSuperior, BorderLayout.NORTH);
	   	
	    JPanel panelJogador = new JPanel();
	    panelJogador.setPreferredSize(new Dimension(650, 40));
	    panelJogador.setLayout(new FlowLayout(FlowLayout.CENTER, 6, 6));
	    
	    this.labelPontosVitoria.setText("(Pts. de vitória) "+ jogadores[0].nome +" " + mochilas[0].maracujasNaMochila.size() + " X "  + mochilas[1].maracujasNaMochila.size() +" "+ jogadores[1].nome);
	    this.labelPontosVitoria1 = new JLabel(" ");
	    
	    this.labelMochilaDoJogador.setText("Mochila de " + jogadores[vez].nome + ":");
	    this.labelforcaJogadores.setText("(Força) " + jogadores[0].nome +" " + jogadores[0].forca + " X "  + jogadores[0].forca +" "+ jogadores[1].nome);
	    
	    this.botaoMaracuja.setText(" " + mochilas[vez].maracujasNaMochila.size());
	    
	    
	    this.botaoLaranja.setText(" " + mochilas[vez].laranjasNaMochila.size());
	    this.botaoLaranja.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (mochilas[vez].laranjasNaMochila.size() > 0) {  
	            	if(mochilas[vez].laranjasNaMochila.get(mochilas[vez].laranjasNaMochila.size()-1).estaBichada) {
	            		jogadores[vez].jogadorEstaBichado = true;
	            	}
	            	comerLaranja(vez);
	            	mochilas[vez].removerFrutaMochila("Laranja");
	            	removerFrutaDinamica("Laranja");
	            	atualizarPanels();
	            }
	            JanelaPartida.this.requestFocusInWindow(); 
	        } 
	    });
	    
	    
	    this.botaoAbacate.setText(" " + mochilas[vez].abacatesNaMochila.size());
	    this.botaoAbacate.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (mochilas[vez].abacatesNaMochila.size() > 0) {  
	            	if(mochilas[vez].abacatesNaMochila.get(mochilas[vez].abacatesNaMochila.size()-1).estaBichada) {
	            		jogadores[vez].jogadorEstaBichado = true;
	        			pontosMovimento = 0;
	            		pontosMovimentoAntidoto = pontosMovimento;
	            		JOptionPane.showMessageDialog(null, jogadores[vez].nome + " não poderá se movimentar até rolar o dado novamente porque comeu uma fruta bichada. "
	            				+ "Caso o johador tenha uma laranja na mochila, ela pode ser comida para desfazer o efeito.", "Fruta Bichada", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	comerAbacate(vez);
	            	mochilas[vez].removerFrutaMochila("Abacate");
	            	removerFrutaDinamica("Abacate");
	            	atualizarPanels();
	            }
	            JanelaPartida.this.requestFocusInWindow(); 
	        } 
	    });
	    
	    
	    this.botaoCoco.setText(" " + mochilas[vez].cocosNaMochila.size());
	    this.botaoCoco.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (mochilas[vez].cocosNaMochila.size() > 0) {  
	            	comerCoco(vez);
	            	if(mochilas[vez].cocosNaMochila.get(mochilas[vez].cocosNaMochila.size()-1).estaBichada) {
	            		jogadores[vez].jogadorEstaBichado = true;
	            		pontosMovimentoAntidoto = pontosMovimento;
	        			pontosMovimento = 0;
	            		JOptionPane.showMessageDialog(null, jogadores[vez].nome + " não poderá se movimentar até rolar o dado novamente porque comeu uma fruta bichada. "
	            				+ "Caso o johador tenha uma laranja na mochila, ela pode ser comida para desfazer o efeito.", "Fruta Bichada", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	mochilas[vez].removerFrutaMochila("Coco");
	            	removerFrutaDinamica("Coco");
	            	atualizarPanels();
	            }
	            JanelaPartida.this.requestFocusInWindow(); 
	        } 
	    });
	    
	    
	    this.botaoAcerola.setText(" " + mochilas[vez].acerolasNaMochila.size());
	    this.botaoAcerola.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (mochilas[vez].acerolasNaMochila.size() > 0) {  
	            	if(mochilas[vez].acerolasNaMochila.get(mochilas[vez].acerolasNaMochila.size()-1).estaBichada) {
	            		jogadores[vez].jogadorEstaBichado = true;
	            		pontosMovimentoAntidoto = pontosMovimento;
	        			pontosMovimento = 0;
	            		JOptionPane.showMessageDialog(null, jogadores[vez].nome + " não poderá se movimentar até rolar o dado novamente porque comeu uma fruta bichada."
	            				+ "Caso o johador tenha uma laranja na mochila, ela pode ser comida para desfazer o efeito.", "Fruta Bichada", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	mochilas[vez].removerFrutaMochila("Acerola");
	            	removerFrutaDinamica("Acerola");
	            	atualizarPanels();
	            }
	            JanelaPartida.this.requestFocusInWindow(); 
	        } 
	    });
	    
	    
	    this.botaoAmora.setText(" " + mochilas[vez].amorasNaMochila.size());
	    this.botaoAmora.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (mochilas[vez].amorasNaMochila.size() > 0) { 
	            	if(mochilas[vez].amorasNaMochila.get(mochilas[vez].amorasNaMochila.size()-1).estaBichada) {
	            		jogadores[vez].jogadorEstaBichado = true;
	            		pontosMovimentoAntidoto = pontosMovimento;
	        			pontosMovimento = 0;
	            		JOptionPane.showMessageDialog(null, jogadores[vez].nome + " não poderá se movimentar até rolar o dado novamente porque comeu uma fruta bichada."
	            				+ "Caso o johador tenha uma laranja na mochila, ela pode ser comida para desfazer o efeito.", "Fruta Bichada", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	mochilas[vez].removerFrutaMochila("Amora");
	            	removerFrutaDinamica("Amora");
	            	atualizarPanels();
	            }
	            JanelaPartida.this.requestFocusInWindow(); 
	        } 
	    });
	    
	    this.botaoGoiaba.setText(" " + mochilas[vez].goiabasNaMochila.size());
	    this.botaoGoiaba.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (mochilas[vez].goiabasNaMochila.size() > 0) {  
	            	if(mochilas[vez].goiabasNaMochila.get(mochilas[vez].goiabasNaMochila.size()-1).estaBichada) {
	            		jogadores[vez].jogadorEstaBichado = true;
	            		pontosMovimentoAntidoto = pontosMovimento;
	        			pontosMovimento = 0;
	            		JOptionPane.showMessageDialog(null, jogadores[vez].nome + " não poderá se movimentar até rolar o dado novamente porque comeu uma fruta bichada."
	            				+ "Caso o johador tenha uma laranja na mochila, ela pode ser comida para desfazer o efeito.", "Fruta Bichada", JOptionPane.INFORMATION_MESSAGE);
	            	}
	            	mochilas[vez].removerFrutaMochila("Goiaba");
	            	removerFrutaDinamica("Goiaba");
	            	atualizarPanels();
	            }
	            JanelaPartida.this.requestFocusInWindow(); 
	        } 
	    });
	    	    
	    
	
	    rodada = new JLabel ("Rodada: " + contagemRodada);
	    
	    labelPontosVitoria.setForeground(verdeEscuro); 
	    labelPontosVitoria1.setForeground(verdeEscuro); 
	    rodada.setForeground(verdeEscuro);
	    
	    panelJogador.add(this.botaoMaracuja);
	    panelJogador.add(this.botaoLaranja);
	    panelJogador.add(this.botaoAbacate);
	    panelJogador.add(this.botaoCoco);
	    panelJogador.add(this.botaoAcerola);
	    panelJogador.add(this.botaoAmora);
	    panelJogador.add(this.botaoGoiaba);

	    painelSuperior.add(this.labelMochilaDoJogador);
	    painelSuperior.add(panelJogador);
	    painelSuperior.add(this.labelPontosVitoria);
	    painelSuperior.add(this.labelforcaJogadores);
	    this.setVisible(true);
	}

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	private int pontosMovimento = 0;
	
	private void acoesDaPartida() {
		frutaCaindoArvore();
		colocaFrutasNaMochila(); 
		moverFrutasMochila(); 
		calcularForca(0);
		calcularForca(1);
		atualizarPanels();
		conferirVitoria();
		}
	
	public void keyPressed(KeyEvent e) {
		this.requestFocusInWindow();
		if (!dadosRolados) {
		   JOptionPane.showMessageDialog(null, "Na sua vez você precisa rolar os dados antes de se mover.", "ERRO", JOptionPane.WARNING_MESSAGE);
	        return; }
		
		if (pontosMovimento == 0) {
		   pontosMovimento = dado1.getValorFace() + dado2.getValorFace();}
		
		if(jogadores[vez].jogadorEstaBichado) {
			pontosMovimento = 0;
		}
		
		boolean movimentacaoConcluida = false;
		if(pontosMovimento > 0) {
		  switch(e.getKeyCode()) {
		  case KeyEvent.VK_UP: movimentacaoConcluida = jogadores[vez].moverJogadorParaCima(ladrilho, pontosMovimento); 
		  if (combate()) {
			  movimentacaoConcluida = jogadores[vez].moverJogadorParaBaixo(ladrilho, pontosMovimento); 
		  }
		  acoesDaPartida(); break;
		  case KeyEvent.VK_DOWN: movimentacaoConcluida = jogadores[vez].moverJogadorParaBaixo(ladrilho, pontosMovimento); 
		  if (combate()) {
			  movimentacaoConcluida = jogadores[vez].moverJogadorParaCima(ladrilho, pontosMovimento); 
		  }
		  acoesDaPartida();  break;
		  case KeyEvent.VK_RIGHT: movimentacaoConcluida = jogadores[vez].moverJogadorParaDireita(ladrilho, pontosMovimento); 
		  if (combate()) {
			  movimentacaoConcluida = jogadores[vez].moverJogadorParaEsquerda(ladrilho, pontosMovimento); 
		  }
		  acoesDaPartida();  break;
		  case KeyEvent.VK_LEFT: movimentacaoConcluida = jogadores[vez].moverJogadorParaEsquerda(ladrilho, pontosMovimento);
		  if (combate()) {
			  movimentacaoConcluida = jogadores[vez].moverJogadorParaDireita(ladrilho, pontosMovimento); 
		  }
		  acoesDaPartida(); break;
		  }
		
		if (ladrilho[jogadores[vez].y][jogadores[vez].x] instanceof Pedra) {
			pontosMovimento--;
    		JOptionPane.showMessageDialog(null, "Pular a pedra custou 1 ponto de movimento.", "Maracujá!", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		if ((ladrilho[jogadores[vez].y][jogadores[vez].x] instanceof Arvore == false) == true) {
        	if (vez == 0) {
        	cicloArvoreJog0 = -1;}
        	if (vez == 1) {
        		cicloArvoreJog1 = -1;}
        }}
		
		if (movimentacaoConcluida) {
			pontosMovimento--;}
		
		labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento);
		
        if (pontosMovimento == 0) {
        	vez = inverterVez(vez);
        	labelVezJogador.setText("Vez de: " + jogadores[vez].nome);
        	dadosRolados = false; }
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
		jogadores[0].jogadorEstaBichado = false;
		jogadores[1].jogadorEstaBichado = false;
		jogadores[0].forcaDobrada = false;
		jogadores[1].forcaDobrada = false;
		return tempVez;
	}

	private void colocaFrutasNaMochila() {
		for (Fruta fruta : this.ladrilhoDinamico) {
			if (fruta != null) {
				if (vez == 0 & fruta.x == jogadores[0].x && fruta.y == jogadores[0].y && mochilas[0].tamanho > mochilas[0].capacidadeUtilizada) {
					fruta.estaNaMochila = 0;
					mochilas[0].adicionarFrutaMochila(fruta);
				}
				if (vez == 1 && fruta.x == jogadores[1].x && fruta.y == jogadores[1].y && mochilas[1].tamanho > mochilas[1].capacidadeUtilizada) {
					fruta.estaNaMochila = 1;
					mochilas[1].adicionarFrutaMochila(fruta);
				}
			}
		}
	}
	
	private void moverFrutasMochila() {
		for (Fruta fruta : this.ladrilhoDinamico) {
			if (fruta != null) {
				if (fruta.estaNaMochila == 0) {
					fruta.moverFrutaPara(jogadores[0].x, jogadores[0].y);
				}
				if (fruta.estaNaMochila == 1) {
					fruta.moverFrutaPara(jogadores[1].x, jogadores[1].y);
				}
			}
		}
	}
	
	private void comerCoco(int jogadorIndex) {
		pontosMovimento *= 2; 
		labelPontosMovimento.setText("Pontos Movimento: " + pontosMovimento); 
	}
	
	private void comerLaranja(int jogadorIndex) {
		if(jogadores[jogadorIndex].jogadorEstaBichado) {
			jogadores[jogadorIndex].jogadorEstaBichado = false;
			pontosMovimento = pontosMovimentoAntidoto;
		}
	}
	
	private void comerAbacate(int jogadorIndex) {
		if(jogadores[jogadorIndex].forcaDobrada == false) {
			jogadores[jogadorIndex].forcaDobrada = true;
		}
	}

	
	private void atualizarPanels() {
		this.labelVezJogador.setText("Vez de: " + jogadores[vez].nome);
		this.labelPontosMovimento.setText("	Pontos Movimento: " + pontosMovimento);
	    this.labelforcaJogadores.setText("(Força) " + jogadores[0].nome +" " + jogadores[0].forca + " X "  + jogadores[1].forca +" "+ jogadores[1].nome);
	    this.labelPontosVitoria.setText("(Pts. Vitoria) " + jogadores[0].nome +" " + mochilas[0].maracujasNaMochila.size() + " X "  + mochilas[1].maracujasNaMochila.size() +" "+ jogadores[1].nome);	    
	    this.labelMochilaDoJogador.setText("Mochila de " + jogadores[vez].nome + ":");
	    this.botaoMaracuja.setText(" " + mochilas[vez].maracujasNaMochila.size());
	    this.botaoLaranja.setText(" " + mochilas[vez].laranjasNaMochila.size());
	    this.botaoAbacate.setText(" " + mochilas[vez].abacatesNaMochila.size());
	    this.botaoCoco.setText(" " + mochilas[vez].cocosNaMochila.size());
	    this.botaoAcerola.setText(" " + mochilas[vez].acerolasNaMochila.size());
	    this.botaoAmora.setText(" " + mochilas[vez].amorasNaMochila.size());
	    this.botaoGoiaba.setText(" " + mochilas[vez].goiabasNaMochila.size());
	    painelSuperior.revalidate();
	}
	
	private void removerFrutaDinamica(String tipoFruta) {
		int i = 0;
		boolean removida = false;
		while (i < this.ladrilhoDinamico.length && removida == false) {
			if ((this.ladrilhoDinamico[i] == null) == false) {
				if (this.ladrilhoDinamico[i].tipoFruta == tipoFruta && this.ladrilhoDinamico[i].x == jogadores[vez].x && this.ladrilhoDinamico[i].y == jogadores[vez].y) {
					this.ladrilhoDinamico[i].removerFruta();
					removida = true;
				}
			}
			i++;
		}
	}
	
	private void frutaCaindoArvore() {
		if (cicloArvoreJog0 % 5 == 0 && mochilas[0].tamanho > mochilas[0].capacidadeUtilizada) {
			mochilas[0].adicionarFrutaMochila(ladrilho[jogadores[vez].y][jogadores[vez].x].derrubarFrutaArvore(0, this.probBichada));
		}
		if (cicloArvoreJog1 % 5 == 0 && mochilas[1].tamanho > mochilas[1].capacidadeUtilizada) {
			mochilas[1].adicionarFrutaMochila(ladrilho[jogadores[vez].y][jogadores[vez].x].derrubarFrutaArvore(1, this.probBichada));
		}
	}
	
	private void derrubarFrutaEmpurrao(int x, int y, int indexJogador) {
		int i = 0;
		boolean frutaFoiDerrubada = false;
		while (frutaFoiDerrubada == false && i < ladrilhoDinamico.length) {
			if ((ladrilhoDinamico[i] == null) == false) {
				if (ladrilhoDinamico[i].estaNaMochila == indexJogador) {
					ladrilhoDinamico[i].estaNaMochila = 3;	
					mochilas[indexJogador].removerFrutaMochila(ladrilhoDinamico[i].tipoFruta);
					if (confereVazio(x-1,y)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x-1, y); break;}
					else if (confereVazio(x+1,y)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x+1, y); break;}
					else if (confereVazio(x,y-1)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x, y-1); break;}
					else if(confereVazio(x,y+1)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x, y+1); break;}
					else if(confereVazio(x+1,y+1)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x+1, y+1); break;}
					else if(confereVazio(x-1,y-1)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x-1, y-1); break;}
					else if(confereVazio(x-1,y+1)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x-1, y+1); break;}
					else if(confereVazio(x+1,y-1)) {
						frutaFoiDerrubada = true;
						ladrilhoDinamico[i].moverFrutaPara(x+1, y-1); break;}
				}
			}
			i++;
		}
	}
	
	private boolean confereVazio(int x, int y) {
		boolean ladrilhoVazio = false;
		if (ladrilho[x][y] instanceof Grama) {
			ladrilhoVazio = true;
		}
		for (Fruta fruta : ladrilhoDinamico) {
			if ((fruta == null) == false) {
				if (fruta.x == x && fruta.y == y) {
					ladrilhoVazio = false;
				}
			}
		}
			
		return ladrilhoVazio;
	}
	
	private boolean combate() {
		boolean combateOcorreu = false;
		if(jogadores[0].x == jogadores[1].x && jogadores[0].y == jogadores[1].y) {
			combateOcorreu = true;
			if (jogadores[0].forca > jogadores[1].forca && vez == 0) {
				derrubarFrutaEmpurrao(jogadores[1].x, jogadores[1].y, 1);
			}
			if (jogadores[1].forca > jogadores[0].forca && vez == 1) {
				derrubarFrutaEmpurrao(jogadores[0].x, jogadores[0].y, 0);
			}
		}
		return combateOcorreu;
	}
	
	private void calcularForca(int jogadorIndex) {
		jogadores[jogadorIndex].forca = 
		mochilas[jogadorIndex].maracujasNaMochila.size()+
		mochilas[jogadorIndex].laranjasNaMochila.size()+
		mochilas[jogadorIndex].abacatesNaMochila.size()+
		mochilas[jogadorIndex].cocosNaMochila.size()+
		mochilas[jogadorIndex].acerolasNaMochila.size()+
		mochilas[jogadorIndex].amorasNaMochila.size()+
		mochilas[jogadorIndex].goiabasNaMochila.size();
		
		if (jogadores[jogadorIndex].forcaDobrada == true) {
			jogadores[jogadorIndex].forca = 
		   (mochilas[jogadorIndex].maracujasNaMochila.size()+
			mochilas[jogadorIndex].laranjasNaMochila.size()+
			mochilas[jogadorIndex].abacatesNaMochila.size()+
			mochilas[jogadorIndex].cocosNaMochila.size()+
			mochilas[jogadorIndex].acerolasNaMochila.size()+
			mochilas[jogadorIndex].amorasNaMochila.size()+
			mochilas[jogadorIndex].goiabasNaMochila.size())*2;
		}
		
	}

	private void conferirVitoria() {
		int totalMaracujas = variaveisInicializacao[8] + variaveisInicializacao[17];
		int maracujasNecessariosVitoria = (totalMaracujas/2)+1;
		System.out.println(maracujasNecessariosVitoria);
		if (mochilas[0].maracujasNaMochila.size() >= maracujasNecessariosVitoria) {
			JOptionPane.showMessageDialog(null, jogadores[0].nome + " venceu a partida!!! \nParabéns!!!");
			JanelaPartida.this.dispose();
		}
		if (mochilas[1].maracujasNaMochila.size() >= maracujasNecessariosVitoria) {
			JOptionPane.showMessageDialog(null, jogadores[1].nome + " venceu a partida!!! \nParabéns!!!");
			JanelaPartida.this.dispose();
		}
	}
}

