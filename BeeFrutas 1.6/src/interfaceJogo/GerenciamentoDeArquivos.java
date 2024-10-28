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
	public void escreverArquivo (String nomeDoArquivo, int[] variaveisInicializacao) {
		if ((new File(nomeDoArquivo + ".txt")).exists()) {
			JOptionPane.showMessageDialog(null, "o arquivo já existe, crie outro arquivo com outro nome", "ERRO", JOptionPane.WARNING_MESSAGE);}
		else {
		auxiliarEscreverArquivo(nomeDoArquivo + ".txt", "dimensao " + variaveisInicializacao[0]
				+ "\npedras " + variaveisInicializacao[1]
				+ "\nmaracuja " + variaveisInicializacao[17] + " " + variaveisInicializacao[8]
				+"\nlaranja " + variaveisInicializacao[2] + " " + variaveisInicializacao[9]
				+ "\nabacate " + variaveisInicializacao[3] + " " + variaveisInicializacao[10]
				+ "\ncoco " + variaveisInicializacao[4] + " " +  variaveisInicializacao[11]
				+ "\nacerola " + variaveisInicializacao[5] + " " +  variaveisInicializacao[12]
				+ "\namora " + variaveisInicializacao[6] + " " + variaveisInicializacao[13]
				+ "\ngoiaba " + variaveisInicializacao[7] + " " + variaveisInicializacao[14]
				+ "\nbichadas " + variaveisInicializacao[16]
				+ "\nmochila " + variaveisInicializacao[15] );}}
	
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
	public void lerArquivo(String nomeDoArquivo, JTextField[] textVariaveisInicializacao) {

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

			auxiliarLerArquivo(valores,textVariaveisInicializacao);
			
		} catch (IOException e) {
			e.printStackTrace();}
	}

	private void auxiliarLerArquivo(String[] valores, JTextField[] textVariaveisInicializacao) {
		textVariaveisInicializacao[0].setText(valores[0]);
		textVariaveisInicializacao[1].setText(valores[1]);
		textVariaveisInicializacao[17].setText(valores[2]);
		textVariaveisInicializacao[8].setText(valores[3]);
		textVariaveisInicializacao[2].setText(valores[4]);
		textVariaveisInicializacao[9].setText(valores[5]);
		textVariaveisInicializacao[3].setText(valores[6]);
		textVariaveisInicializacao[10].setText(valores[7]);
		textVariaveisInicializacao[4].setText(valores[8]);
		textVariaveisInicializacao[11].setText(valores[9]);
		textVariaveisInicializacao[5].setText(valores[10]);
		textVariaveisInicializacao[12].setText(valores[11]);
		textVariaveisInicializacao[6].setText(valores[12]);
		textVariaveisInicializacao[13].setText(valores[13]);
		textVariaveisInicializacao[7].setText(valores[14]);
		textVariaveisInicializacao[14].setText(valores[15]);
		textVariaveisInicializacao[16].setText(valores[16]);
		textVariaveisInicializacao[15].setText(valores[17]);
	}
}