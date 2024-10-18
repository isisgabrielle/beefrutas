package interfaceJogo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// para criar e escrever 

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * A classe GerenciamentoDeArquvios oferece métodos para manipulação de arquivos de texto.
 * Ela permite a criação, escrita e leitura de arquivos que armazenam dados de terrenos
 * criados pelo jogador, como dimensões do terreno e quantidade de frutas.
 */
public class GerenciamentoDeArquivos{

	/**
     * Escreve os dados do terreno do jogo em um arquivo de texto.
     *
     * @param nomeDoArquivo   Nome do arquivo que será criado.
     * @param dimensao        Dimensão do terreno.
     * @param pedras          Número de pedras no terreno.
     * @param maracuja        Número de maracujás.
     * @param arvorelaranja   Número de árvores de laranja.
     * @param laranja         Número de laranjas.
     * @param arvoreabacate   Número de árvores de abacate.
     * @param abacate         Número de abacates.
     * @param arvorecoco      Número de árvores de coco.
     * @param coco            Número de cocos.
     * @param arvoreacerola   Número de árvores de acerola.
     * @param acerola         Número de acerolas.
     * @param arvoreamora     Número de árvores de amora.
     * @param amora           Número de amoras.
     * @param arvoregoiaba    Número de árvores de goiaba.
     * @param goiaba          Número de goiabas.
     */
	public void escreverArquivo (String nomeDoArquivo, int dimensao,
							     int pedras,int arvorelaranja, int arvoreabacate, int arvorecoco, int arvoreacerola, int arvoreamora, int arvoregoiaba,
							     int maracujasPartida, int laranja, int abacate, int coco, int acerola, int amora, int goiaba, 
							     int mochila, int bichadas, int maracuja) {
		if ((new File(nomeDoArquivo + ".txt")).exists()) {
			JOptionPane.showMessageDialog(null, "o arquivo já existe, crie outro arquivo com outro nome", "ERRO", JOptionPane.WARNING_MESSAGE);}
		else {
		auxiliarEscreverArquivo(nomeDoArquivo + ".txt", "dimensao " + dimensao
				+ "\npedras " + pedras
				+ "\nmaracuja " + maracuja + " " + maracujasPartida
				+"\nlaranja " + arvorelaranja + " " + laranja
				+ "\nabacate " + arvoreabacate + " " + abacate
				+ "\ncoco " + arvorecoco + " " +  coco
				+ "\nacerola " + arvoreacerola + " " +  acerola
				+ "\namora " + arvoreamora + " " + amora
				+ "\ngoiaba " + arvoregoiaba + " " + goiaba
				+ "\nbichadas " + bichadas
				+ "\nmochila " + mochila );}}
	
	private static void auxiliarEscreverArquivo(String caminho, String textoArq ){
	try(
		FileWriter criadorArquivos = new FileWriter(caminho,false);
		BufferedWriter buffer = new BufferedWriter(criadorArquivos);
		PrintWriter escritorArquivos = new PrintWriter(buffer); ){
		escritorArquivos.append(textoArq);} 
	
	catch(IOException e) {
		e.printStackTrace();}}
	
	/**
     * Carrega os valores dos campos de texto com os dados lidos no arquivo.
     *
     * @param valores         Vetor contendo os valores lidos do arquivo.
     * @param textDimensao    Campo de texto para a dimensão do terreno.
     * @param textPedras      Campo de texto para o número de pedras.
     * @param textMaracuja    Campo de texto para o número de maracujás.
     * @param textArvLaranja  Campo de texto para o número de árvores de laranja.
     * @param textLaranja     Campo de texto para o número de laranjas.
     * @param textArvAbacate  Campo de texto para o número de árvores de abacate.
     * @param textAbacate     Campo de texto para o número de abacates.
     * @param textArvCoco     Campo de texto para o número de árvores de coco.
     * @param textCoco        Campo de texto para o número de cocos.
     * @param textArvAcerola  Campo de texto para o número de árvores de acerola.
     * @param textAcerola     Campo de texto para o número de acerolas.
     * @param textArvAmora    Campo de texto para o número de árvores de amora.
     * @param textAmora       Campo de texto para o número de amoras.
     * @param textArvGoiaba   Campo de texto para o número de árvores de goiaba.
     * @param textGoiaba      Campo de texto para o número de goiabas.
     */
	public void lerArquivo(String nomeDoArquivo, JTextField textDimensao, JTextField textPedras,
		JTextField textMaracuja,JTextField textArvLaranja,JTextField textLaranja,JTextField textArvAbacate,
		JTextField textAbacate,JTextField textArvCoco,JTextField textCoco,JTextField textArvAcerola,JTextField textAcerola,JTextField textArvAmora,
		JTextField textAmora, JTextField textArvGoiaba,JTextField textGoiaba, JTextField textMochila, JTextField textBichadas, JTextField textMaracujasPartida) {

		File arquivo = new File(nomeDoArquivo + ".txt");
		if (!arquivo.exists()) {
			JOptionPane.showMessageDialog(null, "Arquivo não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
			return;}
		
		try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
			String linha;
			String[] valores = new String[18];
			int contador = 0;
			
			while ((linha = leitor.readLine()) != null && contador < 18) {
				String[] partes = linha.split(" ");
				if (partes.length == 2) {
					valores[contador] = partes[1];  
					contador++;} 
				else if (partes.length == 3) {
					valores[contador] = partes[1];  
					contador++;
           	        valores[contador] = partes[2];  
        	        contador++;}}

			auxiliarLerArquivo(valores,textDimensao,textPedras,textMaracuja,
					textArvLaranja, textLaranja,textArvAbacate, textAbacate, textArvCoco, textCoco,
					textArvAcerola,textAcerola,textArvAmora,textAmora,textArvGoiaba, textGoiaba, textMochila, textBichadas, textMaracujasPartida);
			
		} catch (IOException e) {
			e.printStackTrace();}
	}

	private void auxiliarLerArquivo(String[] valores, JTextField textDimensao, JTextField textPedras,
		JTextField textMaracuja,JTextField textArvLaranja,JTextField textLaranja,JTextField textArvAbacate,
		JTextField textAbacate,JTextField textArvCoco,JTextField textCoco,JTextField textArvAcerola,JTextField textAcerola,JTextField textArvAmora,
		JTextField textAmora, JTextField textArvGoiaba,JTextField textGoiaba, JTextField textMochila, JTextField textBichadas, JTextField textMaracujasPartida) {
		textDimensao.setText(valores[0]);
		textPedras.setText(valores[1]);
		textMaracujasPartida.setText(valores[2]);
		textMaracuja.setText(valores[3]);
		textArvLaranja.setText(valores[4]);
		textLaranja.setText(valores[5]);
		textArvAbacate.setText(valores[6]);
		textAbacate.setText(valores[7]);
		textArvCoco.setText(valores[8]);
		textCoco.setText(valores[9]);
		textArvAcerola.setText(valores[10]);
		textAcerola.setText(valores[11]);
		textArvAmora.setText(valores[12]);
		textAmora.setText(valores[13]);
		textArvGoiaba.setText(valores[14]);
		textGoiaba.setText(valores[15]);
		textBichadas.setText(valores[16]);
		textMochila.setText(valores[17]);
	}
}