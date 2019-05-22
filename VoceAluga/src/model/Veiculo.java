package model;

public class Veiculo {
	private Long id;
	private String filial;
	private String marca;
	private String modelo;
	private String placa;
	private String chassi;
	private char classe;
	private int anoDeFabricacao;
	private int tempoEmAnos;
	private float quilometragem;
	private boolean precisaManutencao;
	/**
	 * Construtor.
	 * @param chassi 		o Numero de Identificacao (Chassi) do Veiculo
	 * @param marca 		a marca do veiculo
	 * @param placa 		a placa do veiculo
	 * @param quilometragem a quilometragem do veiculo
	 */
	public Veiculo(String marca, String modelo, String placa, String chassi, float quilometragem){
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.chassi = chassi;
		this.quilometragem = quilometragem;
		this.precisaManutencao = false;
	}
	
	//================
	//CONSTRUTOR TESTE
	public Veiculo(Long id, String marca, String modelo, String placa, String chassi, float quilometragem){
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.placa = placa;
		this.chassi = chassi;
		this.quilometragem = quilometragem;
		this.precisaManutencao = false;
	}
	//CONSTRUTOR TESTE
	//================
	
	public Veiculo(){
		this.precisaManutencao = false;
	}
	/**
	 * @return o ID do veiculo
	 */
	public Long getID() {
		return this.id;
	}
	/**
	 * @return a filial atual do veiculo
	 */
	public String getFilial() {
		return this.filial;
	}

	/**
	 *
	 * @param filial a filial em que o veiculo esta atualmente
	 */
	public void setFilial(String filial) {
		this.filial = filial;
	}
	/**
	 * @return a marca do veiculo
	 */
	public String getMarca() {
		return this.marca;
	}
	/**
	 *
	 * @param marca a marca do veiculo
	 */
	public void setMarca(String marca){
		this.marca = marca;
	}
	/**
	 * @return o modelo do veiculo
	 */
	public String getModelo() {
		return this.modelo;
	}
	/**
	 *
	 * @param modelo o modelo do veiculo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return a placa do veiculo
	 */
	public String getPlaca() {
		return this.placa;
	}
	/**
	 *
	 * @param placa a placa do veiculo
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return o chassi do veiculo
	 */
	public String getChassi() {
		return this.chassi;
	}
	/**
	 *
	 * @param chassi o chassi do veiculo
	 */
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	/**
	 * @return a classe do veiculo
	 */
	public char getClasse() {
		return this.classe;
	}

	/**
	 *
	 * @param classe a classe do veiculo
	 */
	public void setClasse(char classe) {
		this.classe = classe;
	}
	/**
	 * @param anoDeFabricacao seta o ano de fabricacao do veiculo
	 */
	public void setAnoDeFabricacao(int anoDeFabricacao) {
		this.anoDeFabricacao = anoDeFabricacao;
	}
	/**
	 * @return o ano de fabricacao do veiculo
	 */
	public int getAnoDeFabricacao() {
		return this.anoDeFabricacao;
	}
	/**
	 *
	 * @param tempoEmAnos o tempo em anos que o veiculo tem
	 */
	public void setTempoEmAnos(int tempoEmAnos) {
		this.tempoEmAnos = tempoEmAnos;
	}
	/**
	 * @return o tempo em anos do veiculo
	 */
	public int getTempoEmAnos() {
		return tempoEmAnos;
	}
	/**
	 * @return a quilometragem do veiculo
	 */
	public float getQuilometragem() {
		return quilometragem;
	}
	/**
	 * @return verdadeiro se o veiculo precisa de manutencao, falso caso contrario
	 */
	public boolean isPrecisaManutencao() {
		return precisaManutencao;
	}
	/**
	 * @param precisaManutencao booleano que indica se o veiculo precisa de manutencao ou nao
	 */
	public void setPrecisaManutencao(boolean precisaManutencao) {
		this.precisaManutencao = precisaManutencao;
	}
}
