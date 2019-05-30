package br.ufc.great.es.tsd.hadoop.q2;

public class HoraDiaMes {
	
	private Integer hora;
	private Integer dia;
	private Integer mes;
	
		
	public HoraDiaMes(Integer hora, Integer dia, Integer mes) {
		super();
		this.hora = hora;
		this.dia = dia;
		this.mes = mes;
	}
	
	public Integer getHora() {
		return hora;
	}
	public void setHora(Integer hora) {
		this.hora = hora;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer Mes) {
		this.mes = mes;
	}
	
}
