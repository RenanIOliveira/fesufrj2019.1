package model;

public class Veiculo {
	private int id;
	private String filial;
	private String chassi;
	private String RENAVAM;
	private String placa;
	private String marca;
	private String modelo;
	private Integer anoDeFabricacao;
	private Integer quilometragem;
	private String classe;
	private String cor;
	private String manutencaoEmDia;
	private String informacoesAdicionais;

	// === Aluguel ===
	private boolean alugado;
	private Cliente locatario;

	/**
	 * Construtor.
	 * @param marca 		a marca do veiculo
	 * @param modelo		o modelo do veiculo
	 * @param placa 		a placa do veiculo
	 * @param chassi 		o Numero de Identificacao (Chassi) do Veiculo
	 * @param quilometragem a quilometragem do veiculo
	 */
	public Veiculo(int id, String filial, String chassi, String RENAVAM, String placa, String marca,
				   String modelo, Integer ano, Integer quilometragem, String classe, String cor, String manutencaoEmDia,
				   String informacoesAdicionais){
		this.id = id;
		this.filial = filial;
		this.chassi = chassi;
		this.RENAVAM = RENAVAM;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoDeFabricacao = ano;
		this.quilometragem = quilometragem;
		this.classe = classe;
		this.cor = cor;
		this.manutencaoEmDia = manutencaoEmDia;
		this.informacoesAdicionais = informacoesAdicionais;
	}
	
	//====construtor teste====
	public Veiculo(int id, String marca, String modelo, String placa, String chassi, Integer quilometragem){
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.chassi = chassi;
		this.quilometragem = quilometragem;
	}

	/**
	 * Construtor
	 * Nao recebe parametro nenhum, apenas cria um Veiculo novo
	 */
	public Veiculo(){

	}

	/**
	 * @return O ID do veiculo
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return A filial atual do veiculo
	 */
	public String getFilial() {
		return this.filial;
	}

	/**
	 * @param filial A filial onde o veiculo esta atualmente
	 */
	public void setFilial(String filial) {
		this.filial = filial;
	}

	/**
	 * @return A marca do veiculo
	 */
	public String getMarca() {
		return this.marca;
	}

	/**
	 * @param marca A marca do veiculo
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * @return O modelo do veiculo
	 */
	public String getModelo() {
		return this.modelo;
	}

	/**
	 * @param modelo O modelo do veiculo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	/**
	 * @return A placa do veiculo
	 */
	public String getPlaca() {
		return this.placa;
	}

	/**
	 * @param placa A placa do veiculo
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	/**
	 * @return O chassi do veiculo
	 */
	public String getChassi() {
		return this.chassi;
	}

	/**
	 * @param chassi O numero do chassi do carro
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	/**
	 * @return O RENAVAM do veiculo
	 */
	public String getRENAVAM() {
		return RENAVAM;
	}

	/**
	 * @param RENAVAM O RENAVAM do veiculo
	 */
	public void setRENAVAM(String RENAVAM) {
		this.RENAVAM = RENAVAM;
	}

	/**
	 * @return A classe do veiculo
	 */
	public String getClasse() {
		return this.classe;
	}

	/**
	 * @param classe A classe do veiculo na locadora
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * @return O ano de fabricacao do veiculo
	 */
	public Integer getAnoDeFabricacao() {
		return this.anoDeFabricacao;
	}

	/**
	 * @param ano O ano em que o veiculo foi fabricado
	 */
	public void setAnoDeFabricacao(Integer ano) {
		this.anoDeFabricacao = ano;
	}

	/**
	 * @return A quilometragem do veiculo
	 */
	public Integer getQuilometragem() {
		return quilometragem;
	}

	/**
	 * @param quilometragem A quilometragem do veiculo atual
	 */
	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	/**
	 * @return A cor do veiculo
	 */
	public String getCor() {
		return cor;
	}

	/**
	 * @param cor A cor do veiculo
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}

	/**
	 * @return Se a manutencao do veiculo esta em dia, retorna String "SIM" ou "NÂO"
	 */
	public String getManutencaoEmDia() {
		return manutencaoEmDia;
	}

	/**
	 * @param manutencaoEmDia String que indica se a manutencao do veiculo esta em dia ou nao
	 */
	public void setManutencaoEmDia(String manutencaoEmDia) {
		this.manutencaoEmDia = manutencaoEmDia;
	}

	/**
	 * @return As informacoes adicionais do veiculo
	 */
	public String getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	/**
	 * @param informacoesAdicionais Informacoes adicionais que o veiculo pode possuir
	 */
	public void setInformacoesAdicionais(String informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

	public static boolean ValidarChassi(String chassi) {

		//https://respostas.guj.com.br/41944-validar-chassi-chassi-sempre-invalido

		if (chassi.length() != 17)
		{
			return false;
		}

//		Pattern zeroNoPrimeiroDigito = Pattern.compile ("^0");
//		Matcher matcherZero  = zeroNoPrimeiroDigito.matcher(chassi);
//
//		Pattern espacoNoChassi = Pattern.compile (" ");
//		Matcher matcherEspaco = espacoNoChassi.matcher(chassi);
//
//		Pattern repeticaoMaisDe6Vezes = Pattern.compile ("^.{4,}([0-9A-Z])\\1{5,}");
//		Matcher matcherRepetir = repeticaoMaisDe6Vezes.matcher(chassi);
//
//		Pattern caracteresiIoOqQ = Pattern.compile ("[iIoOqQ]");
//		Matcher matcherCaract = caracteresiIoOqQ.matcher(chassi);
//
//		Pattern ultimos6Numericos = Pattern.compile ("[0-9]{6}$");
//		Matcher matcherUltimos = ultimos6Numericos.matcher(chassi);
//
//		if (matcherZero.find() || matcherEspaco.find() || matcherRepetir.find() || matcherCaract.find() || matcherUltimos.find())
//		{
//			return false;
//		}

		return true;
	}

	/**
	 * @return Valor booleano indicando se o veiculo encontra-se alugado ou nao
	 */
	public boolean isAlugado() {
		return alugado;
	}

	/**
	 * @param alugado Boolean para setar o valor alugado do veiculo
	 */
	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

	/**
	 * @return O cliente que alugou o veiculo
	 */
	public Cliente getLocatario() {
		return locatario;
	}

	/**
	 * @return O nome do cliente que alugou o veiculo
	 */
	public String getLocatarioNome() {
		return locatario.getNome();
	}

	/**
	 * @return O CPF do cliente que alugou o veiculo
	 */
	public String getLocatarioCPF() {
		return locatario.getCPF();
	}
	/**
	 * @return O numero de registro do cliente que alugou o veiculo
	 */
	public String getLocatarioHabilitacao() {
		return locatario.getHabilitacao().getNumeroDeRegistro();
	}

	/**
	 * @param locatario O Cliente que alugou o veiculo
	 */
	public void setLocatarioNome(Cliente locatario) {
		this.locatario = locatario;
	}
}
