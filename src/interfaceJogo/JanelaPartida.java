package interfaceJogo;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import floresta.Arvore;
import floresta.Floresta;
import floresta.Grama;
import floresta.Pedra;
import fruta.Abacate;
import fruta.Acerola;
import fruta.Amora;
import fruta.Coco;
import fruta.Fruta;
import fruta.Goiaba;
import fruta.Laranja;
import fruta.Maracuja;
import jogadores.JogadorDois;
import jogadores.JogadorUm;


public class JanelaPartida {
	 	private JFrame janela;
	    private JogadorUm jogadorUm; 
	    private Floresta[][] ladrilho;
	    private Object[][] ladrilhoDinamico;
	    private JPanel panelDinamico;
	    private MatrizTerreno jogo1;
	
	
	public JanelaPartida(){
		janela = new JFrame();
		janela.setTitle("BeeFrutas");
		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(665, 685);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		janela.setResizable(false);
		 
	    panelDinamico = new JPanel();  
	    panelDinamico.setLayout(null);
	    panelDinamico.setVisible(true);
	    panelDinamico.setOpaque(false);
	    panelDinamico.setSize(650, 650);
	    
        janela.add(panelDinamico);

	    panelDinamico.setFocusable(true);
	    panelDinamico.requestFocusInWindow();
	    
	    janela.addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent e) {
	                int keyCode = e.getKeyCode();
	                int novoX = jogadorUm.getX();
	                int novoY = jogadorUm.getY();

	                switch (keyCode) {
	                    case KeyEvent.VK_UP:    moverJogador(novoX, novoY - 1); break;
	                    case KeyEvent.VK_DOWN:  moverJogador(novoX, novoY + 1); break;
	                    case KeyEvent.VK_LEFT:  moverJogador(novoX - 1, novoY); break;
	                    case KeyEvent.VK_RIGHT: moverJogador(novoX + 1, novoY); break;
	                }
	                moverJogador(novoX, novoY);
	            }
	        });
	}
	// função para mover o jogador

	public void moverJogador(int novoX, int novoY) {
	    System.out.println("Tentando mover para: " + novoX + ", " + novoY);
	    
	    if (novoX >= 0 && novoX < jogo1.dimensao && novoY >= 0 && novoY < jogo1.dimensao) {
	        if (jogo1.elementosEstaticos[novoX][novoY].equals("g ")) {
	            System.out.println("Movendo jogador de: " + jogadorUm.getX() + ", " + jogadorUm.getY() + " para " + novoX + ", " + novoY);
	            
	            jogo1.elementosDinamicos[jogadorUm.getX()][jogadorUm.getY()] = "  "; 
	            jogadorUm.setX(novoX);
	            jogadorUm.setY(novoY);
	            jogo1.elementosDinamicos[novoX][novoY] = "J "; 
	            
	            jogadorUm.setBounds(novoX * jogadorUm.getTamanhoIcone(), novoY * jogadorUm.getTamanhoIcone(),
	                    jogadorUm.getTamanhoIcone(), jogadorUm.getTamanhoIcone());

	            panelDinamico.revalidate();
	            panelDinamico.repaint();
	        }
	    }
	}

    
	
	public void iniciarPartida(int dimensao, int pedras, int arvoreLaranja, int arvoreAbacate, int arvoreCoco, int arvoreAcerola, int arvoreAmora, int arvoreGoiaba,
							   int maracuja, int laranja, int abacate, int coco, int acerola, int amora, int goiaba, int mochila, int bichadas, int maracujasPartida) {
		  MatrizTerreno jogo1 = new MatrizTerreno(dimensao, pedras, arvoreLaranja, arvoreAbacate, arvoreCoco, arvoreAcerola, arvoreAmora, arvoreGoiaba,
	                maracuja, laranja, abacate, coco, acerola, amora, goiaba);
		  
		int totalLadrilhos = dimensao*dimensao;
		int totalElementos = pedras + arvoreLaranja + arvoreAbacate + arvoreCoco + arvoreAcerola + arvoreAmora + arvoreGoiaba + 
							 maracuja + laranja + abacate + coco + acerola + amora + goiaba;
		
		if (dimensao < 3 || dimensao > 25) {
			janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("A dimensão da floresta deve ser de no mínimo 3 ladrilhos e no máximo 25.");}
		if (maracuja < 1) {
			janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("A floresta deve conter pelo menos 1 maracujá.");}
		if (totalElementos > totalLadrilhos) {
			janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
			throw new RuntimeException("O número total de elementos não pode ultrapassar a quantidade de ladrilhos da floresta.");}
		
		else {
			Floresta[][] ladrilho = new Floresta[dimensao][dimensao];
			Object[][] ladrilhoDinamico = new Object[dimensao][dimensao];
			
		    JLayeredPane camadas = new JLayeredPane();		
			
			
			JPanel panelDinamico = new JPanel();
			panelDinamico.setLayout(null);
			panelDinamico.setVisible(true);
			panelDinamico.setOpaque(false);
			panelDinamico.setSize(650, 650);
			
			
			JPanel panel = new JPanel(new GridLayout(dimensao, dimensao));
			panel.setSize(650, 650);
			
			
			jogo1.inicializarElementos();
			jogo1.mostrarTerreno();
			
			int tamanhoLadrilho = 650/dimensao;
			JogadorUm jogadorUm = null; 
			
			for(int i = 0; i < dimensao; i++) {
				for(int j = 0; j < dimensao; j++) {
					
					
					if (!jogo1.elementosDinamicos[i][j].equals("  ")) {
						switch (jogo1.elementosDinamicos[i][j]) {
						case "m ": ladrilhoDinamico[i][j] = new Maracuja(j, i, tamanhoLadrilho); break;
						case "l ": ladrilhoDinamico[i][j] = new Laranja(j, i, tamanhoLadrilho); break;
						case "a ": ladrilhoDinamico[i][j] = new Abacate(j, i, tamanhoLadrilho); break;
						case "c ": ladrilhoDinamico[i][j] = new Coco(j, i, tamanhoLadrilho); break;
						case "r ": ladrilhoDinamico[i][j] = new Acerola(j, i, tamanhoLadrilho); break;
						case "o ": ladrilhoDinamico[i][j] = new Amora(j, i, tamanhoLadrilho); break;
						case "g ": ladrilhoDinamico[i][j] = new Goiaba(j, i, tamanhoLadrilho); break;
						 case "ju ": 
	                            jogadorUm = new JogadorUm(j, i, tamanhoLadrilho); // Inicializa o jogador
	                            ladrilhoDinamico[i][j] = jogadorUm; System.out.println("Jogador está em: " + jogadorUm.getX() + ", " + jogadorUm.getY());
	                            break; 
						
						case "jd ": ladrilhoDinamico[i][j] = new JogadorDois(j, i, tamanhoLadrilho); break;

						}
						panelDinamico.add((Component)ladrilhoDinamico[i][j]);	
					}
					
					switch (jogo1.elementosEstaticos[i][j]) {
					case "g ": ladrilho[i][j] = new Grama(tamanhoLadrilho); break;
					case "P ": ladrilho[i][j] = new Pedra(tamanhoLadrilho); break;
					case "AL": ladrilho[i][j] = new Arvore("Laranja", tamanhoLadrilho); break;
					case "AA": ladrilho[i][j] = new Arvore("Abacate", tamanhoLadrilho); break;
					case "AC": ladrilho[i][j] = new Arvore("Coco", tamanhoLadrilho); break;
					case "AR": ladrilho[i][j] = new Arvore("Acerola", tamanhoLadrilho); break;
					case "AO": ladrilho[i][j] = new Arvore("Amora", tamanhoLadrilho); break;
					case "AG": ladrilho[i][j] = new Arvore("Goiaba", tamanhoLadrilho); break;
					}
					ladrilho[i][j].setBounds(j*tamanhoLadrilho, i*tamanhoLadrilho, tamanhoLadrilho, tamanhoLadrilho);
					panel.add(ladrilho[i][j]);
				}
			}
			
			camadas.add(panel, Integer.valueOf(1));
			camadas.add(panelDinamico, Integer.valueOf(2));
			
			janela.add(camadas);
			 janela.revalidate();
		        janela.repaint();
			
           
           
        }
    }

   
        
}


