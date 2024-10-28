package interfaceJogo;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;

/**
 * A classe TelaInicial representa a janela principal do jogo BeeFrutas, onde o jogador pode
 * configurar e iniciar uma nova partida, carregar, salvar ou visualizar o terreno.
 * Ela exibe campos para entrada de dados relacionados às frutas, árvores e outros elementos
 * do terreno.
 */
public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textVariaveisInicializacao[] = new JTextField[18];
	private JTextField textJogador0;
	private JTextField textJogador1;
	
	
	/**
     * Método principal que inicia a aplicação. 
     * Cria uma nova instância da TelaInicial e a exibe.
     * 
     * @param args argumentos da linha de comando (não usados).
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial telaInicialJogo = new TelaInicial();
					telaInicialJogo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String [] obterNomesJogadores() {
		String[] nomesJogadoresTemp = new String[2];
		nomesJogadoresTemp[0] = textJogador0.getText();
		nomesJogadoresTemp[1] = textJogador1.getText();
		return nomesJogadoresTemp;
	}
	
	private int[] obterInteirosInicializacao(JTextField[] textVariaveisInicializacao){
		int variaveisInicializacao[] = new int[18];
		for (int i = 0; i < 18; i++) {
			if (textVariaveisInicializacao[i].getText().isEmpty()) {
				variaveisInicializacao[i] = 0;}
			else {
				variaveisInicializacao[i] = Integer.parseInt(textVariaveisInicializacao[i].getText()) ;}
		}
		return variaveisInicializacao;
	}
	
	/**
     * Construtor da TelaInicial. Configura a interface gráfica e define os componentes da janela,
     * como os botões, campos de texto e suas funcionalidades.
     */
	public TelaInicial() {
		
		GerenciamentoDeArquivos arq = new GerenciamentoDeArquivos();
		
		setMinimumSize(new Dimension(450, 650));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(40, 40, 40, 40));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		//Painel do Título
		JPanel panelTitulo = new JPanel();
		panelTitulo.setMaximumSize(new Dimension(32767, 100));
		FlowLayout flowLayout = (FlowLayout) panelTitulo.getLayout();
		flowLayout.setVgap(20);
		contentPane.add(panelTitulo);
		
		JLabel lblNewLabel = new JLabel("BeeFrutas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelTitulo.add(lblNewLabel);
		
		//Painel dos botões
		JPanel panelBotoes = new JPanel();
		panelBotoes.setMaximumSize(new Dimension(450, 150));
		contentPane.add(panelBotoes);
		panelBotoes.setLayout(new GridLayout(2, 2, 5, 5));
		panelBotoes.setBounds(EXIT_ON_CLOSE, ABORT, 100, 300);
		
		JButton botaoPartida = new JButton("Iniciar Partida");
		panelBotoes.add(botaoPartida);
		botaoPartida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JanelaPartida main = new JanelaPartida(obterInteirosInicializacao(textVariaveisInicializacao), obterNomesJogadores());
					setVisible(false);
				}
				catch(RuntimeException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
		JButton botaoCarregarTerreno = new JButton("Carregar Terreno");
		panelBotoes.add(botaoCarregarTerreno);
		botaoCarregarTerreno.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // pergunta pro usuário o nome do arquivo para carregar ele
		        String nomeDoArquivo = JOptionPane.showInputDialog("Insira o nome do arquivo para carregar o terreno");
		        
		        if (nomeDoArquivo != null && !nomeDoArquivo.isEmpty()) {
		            try {
		            	arq.lerArquivo(nomeDoArquivo, textVariaveisInicializacao);
		            } catch (RuntimeException e1) {
		                JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);}} 
		        else {
		            JOptionPane.showMessageDialog(null, "Digite o nome do arquivo", "Erro", JOptionPane.ERROR_MESSAGE);}
		    }
		});
		
		JButton botaoVisualizarTerreno = new JButton("Visualizar Terreno");
		panelBotoes.add(botaoVisualizarTerreno);
		botaoVisualizarTerreno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JanelaPartida main = new JanelaPartida(obterInteirosInicializacao(textVariaveisInicializacao), obterNomesJogadores());
					setVisible(true);
				}
				catch(RuntimeException e1){
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
		
		JButton botaoSalvarTerreno = new JButton("Salvar Terreno");
		panelBotoes.add(botaoSalvarTerreno);
		botaoSalvarTerreno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e5) {
				String nomeDoArquivo = JOptionPane.showInputDialog("Insira o nome do arquivo");
				arq.escreverArquivo(nomeDoArquivo, obterInteirosInicializacao(textVariaveisInicializacao));
			}
		});
		
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		JPanel panelFrutasArvores = new JPanel();
		panel_2.add(panelFrutasArvores);
		panelFrutasArvores.setLayout(new GridLayout(8, 3, 4, 4));
		
		JLabel separador = new JLabel("");
		panelFrutasArvores.add(separador);
		
		JLabel labelFrutas = new JLabel("Frutas no chão");
		labelFrutas.setHorizontalAlignment(SwingConstants.CENTER);
		panelFrutasArvores.add(labelFrutas);
		
		JLabel labelArvores = new JLabel("Arvores");
		labelArvores.setHorizontalAlignment(SwingConstants.CENTER);
		panelFrutasArvores.add(labelArvores);
		
		
		JLabel labelLaranja = new JLabel("Laranja");
		panelFrutasArvores.add(labelLaranja);
		textVariaveisInicializacao[9] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[9]);
		textVariaveisInicializacao[9].setColumns(10);
		textVariaveisInicializacao[2] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[2]);
		textVariaveisInicializacao[2].setColumns(10);
		
		
		JLabel labelAbacate = new JLabel("Abacate");
		panelFrutasArvores.add(labelAbacate);
		textVariaveisInicializacao[10] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[10]);
		textVariaveisInicializacao[10].setColumns(10);
		textVariaveisInicializacao[3] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[3]);
		textVariaveisInicializacao[3].setColumns(10);
		
		
		JLabel labelCoco = new JLabel("Coco");
		panelFrutasArvores.add(labelCoco);
		textVariaveisInicializacao[11] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[11]);
		textVariaveisInicializacao[11].setColumns(10);
		textVariaveisInicializacao[4] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[4]);
		textVariaveisInicializacao[4].setColumns(10);
		
		
		JLabel labelAcerola = new JLabel("Acerola");
		panelFrutasArvores.add(labelAcerola);
		textVariaveisInicializacao[12] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[12]);
		textVariaveisInicializacao[12].setColumns(10);
		textVariaveisInicializacao[5] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[5]);
		textVariaveisInicializacao[5].setColumns(10);
		
		
		JLabel labelAmora = new JLabel("Amora");
		panelFrutasArvores.add(labelAmora);
		textVariaveisInicializacao[13] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[13]);
		textVariaveisInicializacao[13].setColumns(10);
		textVariaveisInicializacao[6] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[6]);
		textVariaveisInicializacao[6].setColumns(10);
		
		
		JLabel labelGoiaba = new JLabel("Goiaba");
		panelFrutasArvores.add(labelGoiaba);
		textVariaveisInicializacao[14] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[14]);
		textVariaveisInicializacao[14].setColumns(10);
		textVariaveisInicializacao[7] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[7]);
		textVariaveisInicializacao[7].setColumns(10);
		
		
		JLabel labelMaracuja = new JLabel("Maracujá");
		panelFrutasArvores.add(labelMaracuja);
		textVariaveisInicializacao[8] = new JTextField();
		panelFrutasArvores.add(textVariaveisInicializacao[8]);
		textVariaveisInicializacao[8].setColumns(10);
		
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(7, 2, 5, 4));
		
		JLabel labelDimensao = new JLabel("Dimensão do terreno");
		panel_4.add(labelDimensao);
		textVariaveisInicializacao[0] = new JTextField();
		panel_4.add(textVariaveisInicializacao[0]);
		textVariaveisInicializacao[0].setColumns(10);
		
		JLabel labelPedras = new JLabel("Pedras");
		panel_4.add(labelPedras);
		textVariaveisInicializacao[1] = new JTextField();
		panel_4.add(textVariaveisInicializacao[1]);
		textVariaveisInicializacao[1].setColumns(10);
		
		JLabel labelCapMochila = new JLabel("Capacidade da mochila");
		panel_4.add(labelCapMochila);
		textVariaveisInicializacao[15] = new JTextField();
		panel_4.add(textVariaveisInicializacao[15]);
		textVariaveisInicializacao[15].setColumns(10);
		
		JLabel labelFrutasBichadas = new JLabel("Frutas Bichadas (%)");
		panel_4.add(labelFrutasBichadas);
		textVariaveisInicializacao[16] = new JTextField();
		panel_4.add(textVariaveisInicializacao[16]);
		textVariaveisInicializacao[16].setColumns(10);
		
		
		JLabel labelMaracujasPartida = new JLabel("Maracujás na partida");
		panel_4.add(labelMaracujasPartida);
		textVariaveisInicializacao[17] = new JTextField();
		panel_4.add(textVariaveisInicializacao[17]);
		
		JLabel labelJogador1 = new JLabel("Nome do(a) Jogador(a) 0");
		panel_4.add(labelJogador1);
		
		textJogador0 = new JTextField();
		panel_4.add(textJogador0);
		textJogador0.setColumns(10);
		
		JLabel labelJogador2 = new JLabel("Nome do(a) Jogador(a) 1");
		panel_4.add(labelJogador2);
		
		textJogador1 = new JTextField();
		panel_4.add(textJogador1);
		textJogador1.setColumns(10);
		textVariaveisInicializacao[17].setColumns(10);
	}

}
