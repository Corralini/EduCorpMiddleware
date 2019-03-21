package com.educorp.eduinteractive.ecommerce.dao.service;

import java.util.List;

public class Results<T> {

	private List<T> resultados = null;
	private int startIndex = 0;
	private int resultadosTotales = 0;
	
	public Results() {
		
	}
	
	public Results(List<T> resultados, int startIndex, int resultadosTotales) {
		setResultados(resultados);
		setStartIndex(startIndex);
		setResultadosTotales(resultadosTotales);		
	}

	public List<T> getResultados() {
		return resultados;
	}

	public void setResultados(List<T> resultados) {
		this.resultados = resultados;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getResultadosTotales() {
		return resultadosTotales;
	}

	public void setResultadosTotales(int resultadosTotales) {
		this.resultadosTotales = resultadosTotales;
	}
	
	
	
}
