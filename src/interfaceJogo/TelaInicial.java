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
	private JTextField textLaranja;
	private JTextField textArvAbacate;
	private JTextField textCoco;
	private JTextField textAcerola;
	private JTextField textAmora;
	private JTextField textGoiaba;
	private JTextField textMaracuja;
	private JTextField textArvLaranja;
	private JTextField textAbacate;
	private JTextField textArvCoco;
	private JTextField textArvAcerola;
	private JTextField textArvAmora;
	private JTextField textArvGoiaba;
	private JTextField textDimensao;
	private JTextField textMochila;
	private JTextField textBichadas;
	private JTextField textMaracujasPartida;
	private JTextField textPedras;

	
	
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
	
	private int obterInt(JTextField campoDeTexto){
		int intCampoDeTexto;
		if (campoDeTexto.getText().isEmpty()) {
			intCampoDeTexto = 0;}
		else {
			intCampoDeTexto = Integer.parseInt(campoDeTexto.getText()) ;}
		return intCampoDeTexto;
	}
	
	/**
     * Construtor da TelaInicial. Configura a interface gráfica e define os componentes da janela,
     * como os botões, campos de texto e suas funcionalidades.
     */
	public TelaInicial() {
		
		GerenciamentoDeArquivos arq = new GerenciamentoDeArquivos();
		 
		setMinimumSize(new Dimension(450, 600));
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
					JanelaPartida main = new JanelaPartida();
					main.iniciarPartida(obterInt(textDimensao),
				
										obterInt(textPedras), 
										obterInt(textArvLaranja),
										obterInt(textArvAbacate),
										obterInt(textArvCoco),
										obterInt(textArvAcerola),
										obterInt(textArvAmora),
										obterInt(textArvGoiaba),
										obterInt(textMaracuja),
										obterInt(textLaranja),
										obterInt(textAbacate),
										obterInt(textCoco),
										obterInt(textAcerola),
										obterInt(textAmora),
										obterInt(textGoiaba),
										obterInt(textMochila),
										obterInt(textBichadas),
										obterInt(textMaracujasPartida));
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
		            	arq.lerArquivo(nomeDoArquivo, textDimensao,textPedras,textMaracuja,
			        		    textArvLaranja, textLaranja,textArvAbacate, textAbacate, textArvCoco, textCoco,
			        		    textArvAcerola,textAcerola,textArvAmora,textAmora,textArvGoiaba, textGoiaba, textMochila, textBichadas, textMaracujasPartida);
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
					JanelaPartida main = new JanelaPartida();
					main.iniciarPartida(obterInt(textDimensao),
										obterInt(textPedras), 
										obterInt(textArvLaranja),
										obterInt(textArvAbacate),
										obterInt(textArvCoco),
										obterInt(textArvAcerola),
										obterInt(textArvAmora),
										obterInt(textArvGoiaba),
										obterInt(textMaracuja),
										obterInt(textLaranja),
										obterInt(textAbacate),
										obterInt(textCoco),
										obterInt(textAcerola),
										obterInt(textAmora),
										obterInt(textGoiaba),
										obterInt(textMochila),
										obterInt(textBichadas),
										obterInt(textMaracujasPartida));
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
				arq.escreverArquivo(nomeDoArquivo, obterInt(textDimensao), obterInt(textPedras), 
									obterInt(textArvLaranja), obterInt(textArvAbacate), obterInt(textArvCoco), obterInt(textArvAcerola), obterInt(textArvAmora), obterInt(textArvGoiaba),
									obterInt(textMaracuja), obterInt(textLaranja), obterInt(textAbacate), obterInt(textCoco), obterInt(textAcerola), obterInt(textAmora), obterInt(textGoiaba), obterInt(textMochila), obterInt(textBichadas), obterInt(textMaracujasPartida));
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
		
		textLaranja = new JTextField();
		panelFrutasArvores.add(textLaranja);
		textLaranja.setColumns(10);
		
		textArvLaranja = new JTextField();
		panelFrutasArvores.add(textArvLaranja);
		textArvLaranja.setColumns(10);
		
		JLabel labelAbacate = new JLabel("Abacate");
		panelFrutasArvores.add(labelAbacate);
		
		textAbacate = new JTextField();
		panelFrutasArvores.add(textAbacate);
		textAbacate.setColumns(10);
		
		textArvAbacate = new JTextField();
		panelFrutasArvores.add(textArvAbacate);
		textArvAbacate.setColumns(10);
		
		JLabel labelCoco = new JLabel("Coco");
		panelFrutasArvores.add(labelCoco);
		
		textCoco = new JTextField();
		panelFrutasArvores.add(textCoco);
		textCoco.setColumns(10);
		
		textArvCoco = new JTextField();
		panelFrutasArvores.add(textArvCoco);
		textArvCoco.setColumns(10);
		
		JLabel labelAcerola = new JLabel("Acerola");
		panelFrutasArvores.add(labelAcerola);
		
		textAcerola = new JTextField();
		panelFrutasArvores.add(textAcerola);
		textAcerola.setColumns(10);
		
		textArvAcerola = new JTextField();
		panelFrutasArvores.add(textArvAcerola);
		textArvAcerola.setColumns(10);
		
		JLabel labelAmora = new JLabel("Amora");
		panelFrutasArvores.add(labelAmora);
		
		textAmora = new JTextField();
		panelFrutasArvores.add(textAmora);
		textAmora.setColumns(10);
		
		textArvAmora = new JTextField();
		panelFrutasArvores.add(textArvAmora);
		textArvAmora.setColumns(10);
		
		JLabel labelGoiaba = new JLabel("Goiaba");
		panelFrutasArvores.add(labelGoiaba);
		
		textGoiaba = new JTextField();
		panelFrutasArvores.add(textGoiaba);
		textGoiaba.setColumns(10);
		
		textArvGoiaba = new JTextField();
		panelFrutasArvores.add(textArvGoiaba);
		textArvGoiaba.setColumns(10);
		
		JLabel labelMaracuja = new JLabel("Maracujá");
		panelFrutasArvores.add(labelMaracuja);
		
		textMaracuja = new JTextField();
		panelFrutasArvores.add(textMaracuja);
		textMaracuja.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(5, 2, 5, 4));
		
		JLabel labelDimensao = new JLabel("Dimensão do terreno");
		panel_4.add(labelDimensao);
		
		textDimensao = new JTextField();
		panel_4.add(textDimensao);
		textDimensao.setColumns(10);
		
		JLabel labelPedras = new JLabel("Pedras");
		panel_4.add(labelPedras);
		
		textPedras = new JTextField();
		panel_4.add(textPedras);
		textPedras.setColumns(10);
		
		JLabel labelCapMochila = new JLabel("Capacidade da mochila");
		panel_4.add(labelCapMochila);
		
		textMochila = new JTextField();
		panel_4.add(textMochila);
		textMochila.setColumns(10);
		
		JLabel labelFrutasBichadas = new JLabel("Frutas Bichadas (%)");
		panel_4.add(labelFrutasBichadas);
		
		textBichadas = new JTextField();
		panel_4.add(textBichadas);
		textBichadas.setColumns(10);
		
		JLabel labelMaracujasPartida = new JLabel("Maracujás na partida");
		panel_4.add(labelMaracujasPartida);
		
		textMaracujasPartida = new JTextField();
		panel_4.add(textMaracujasPartida);
		textMaracujasPartida.setColumns(10);
		
	}

}
