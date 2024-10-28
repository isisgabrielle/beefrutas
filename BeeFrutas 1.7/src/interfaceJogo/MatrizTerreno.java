package interfaceJogo;

import java.util.Random;

/**
 * A classe MatrizTerreno cria o terreno do jogo BeeFrutas como uma matriz de Strings
 * que contém elementos estáticos (como árvores, pedras, grama) e elementos dinâmicos (como frutas).
 * A classe permite a inicialização dos elementos em posições aleatórias no terreno seguindo as quantidades
 * definidas pelo jogador,  além de mostrar o terreno no console através de Strings.
 */
public class MatrizTerreno {		
	
	Random random = new Random();
	
	/*
	 * 0 - dimensao
	 * 1 - pedras
	 * 2 - arvoreLaranja
	 * 3 - arvoreAbacate
	 * 4 - arvoreCoco
	 * 5 - arvoreAcerola
	 * 6 - arvoreAmore
	 * 7 - arvoreGoiaba
	 * 8 - maracuja
	 * 9 - laranja
	 * 10 - abacate
	 * 11 - coco
	 * 12 - acerola
	 * 13 - amora
	 * 14 - goiaba
	 * 15 - mochila
	 * 16 - bichadas
	 * 17 - maracujasPartida
	 */
	
	private int variaveisInicializacao[] = new int [18];
	public String elementosEstaticos[][];	
	public String elementosDinamicos[][];
	
	
	/**
     * Construtor da classe MatrizTerreno.
     * Inicializa os atributos do terreno de acordo com os parâmetros passados.
     *
     * @param dimensao A dimensão da matriz (n x n) que representa a floresta.
     * @param totalPedras O número total de pedras no terreno.
     * @param arvoreLaranja O número de árvores de laranja no terreno.
     * @param arvoreAbacate O número de árvores de abacate no terreno.
     * @param arvoreCoco O número de árvores de coco no terreno.
     * @param arvoreAcerola O número de árvores de acerola no terreno.
     * @param arvoreAmora O número de árvores de amora no terreno.
     * @param arvoreGoiaba O número de árvores de goiaba no terreno.
     * @param maracuja A quantidade de maracujás no terreno.
     * @param laranja A quantidade de laranjas no terreno.
     * @param abacate A quantidade de abacates no terreno.
     * @param coco A quantidade de cocos no terreno.
     * @param acerola A quantidade de acerolas no terreno.
     * @param amora A quantidade de amoras no terreno.
     * @param goiaba A quantidade de goiabas no terreno.
     */
	public MatrizTerreno(int [] variaveisInicializacao) {
		this.variaveisInicializacao = variaveisInicializacao;}
	
	
	//Insere um elemento no terreno em uma célula aleatória que tenha grama e esteja vazia.
	private void eventoAleatorio(String[][] matriz, String nome, int quantidade){
		for (int i = 0; i < quantidade; i++) {
			boolean temGrama = false;
			while (temGrama == false) {
				int randomX = random.nextInt(this.variaveisInicializacao[0]);
				int randomY = random.nextInt(this.variaveisInicializacao[0]);
				if (elementosEstaticos [randomX][randomY] == "g " && elementosDinamicos [randomX][randomY] == "  ") {
					matriz [randomX][randomY] = nome;
					temGrama = true; }
				else {
					temGrama = false; } } } }
	
	
	protected void inicializarElementos() {
		elementosEstaticos = new String[variaveisInicializacao[0]][variaveisInicializacao[0]];	
		for (int i = 0; i < elementosEstaticos.length; i++) {
			for (int j = 0; j < elementosEstaticos[i].length; j++) {
				elementosEstaticos[i][j] = "g "; } } 
		
		elementosDinamicos = new String[variaveisInicializacao[0]][variaveisInicializacao[0]];	
				
		for (int i = 0; i < elementosDinamicos.length; i++) {
			for (int j = 0; j < elementosDinamicos[i].length; j++) {
				elementosDinamicos[i][j] = "  "; } } 
		
		//coloca os jogadores nos seus lugares
		
		elementosDinamicos[0][0] = "j0";
		elementosDinamicos[variaveisInicializacao[0]-1][variaveisInicializacao[0]-1] = "j1";
		
		eventoAleatorio(elementosEstaticos, "P ", variaveisInicializacao[1]);
		eventoAleatorio(elementosEstaticos, "AL", variaveisInicializacao[2]);
		eventoAleatorio(elementosEstaticos, "AA", variaveisInicializacao[3]);
		eventoAleatorio(elementosEstaticos, "AC", variaveisInicializacao[4]);
		eventoAleatorio(elementosEstaticos, "AR", variaveisInicializacao[5]);
		eventoAleatorio(elementosEstaticos, "AO", variaveisInicializacao[6]);
		eventoAleatorio(elementosEstaticos, "AG", variaveisInicializacao[7]);
		
		eventoAleatorio(elementosDinamicos, "m ", variaveisInicializacao[8]);
		eventoAleatorio(elementosDinamicos, "l ", variaveisInicializacao[9]);
		eventoAleatorio(elementosDinamicos, "a ", variaveisInicializacao[10]);
		eventoAleatorio(elementosDinamicos, "c ", variaveisInicializacao[11]);
		eventoAleatorio(elementosDinamicos, "r ", variaveisInicializacao[12]);
		eventoAleatorio(elementosDinamicos, "o ", variaveisInicializacao[13]);
		eventoAleatorio(elementosDinamicos, "g ", variaveisInicializacao[14]);
	}
	
	/**
     * Exibe o terreno criado no console, mostrando tanto os elementos estáticos quanto os dinâmicos.
     */
	public void mostrarTerreno() {
		for (int i = 0; i < elementosEstaticos.length; i++) {
			System.out.print("\n\n");
			for (int j = 0; j < elementosEstaticos[i].length; j++) {
				System.out.print(elementosEstaticos[i][j] + elementosDinamicos[i][j] + (" "));} } }
	
}
