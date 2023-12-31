package br.com.senai.view.componentes.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.core.domain.Restaurante;

public class RestauranteTableModel extends AbstractTableModel{
	
	
	private static final long serialVersionUID = 1L;
	private List<Restaurante> restaurantes;
	
	public RestauranteTableModel(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Restaurante getPor(int rowIndex) {
		return restaurantes.get(rowIndex);
	}
	
	public String getColumnName(int columnIndex) {
		if (columnIndex == 0) {
			return "Código";
		} else if (columnIndex == 1) {
			return "Nome";
		}
		throw new IllegalArgumentException("Índice inválido.");
		
	}
	
	@Override
	public int getRowCount() {
		return restaurantes.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Restaurante restauranteDaLinha = restaurantes.get(rowIndex);
		if (columnIndex == 0) {
			return restauranteDaLinha.getId();
		} else if (columnIndex == 1) {
			return restauranteDaLinha.getNome();
		}
		throw new IllegalArgumentException("Índice inválido.");
	}

	public void removerPor(int rowIndex) {
		this.restaurantes.remove(rowIndex);
	}
	
	public boolean isVazio() {
		return restaurantes.isEmpty();
	}
	
}




















