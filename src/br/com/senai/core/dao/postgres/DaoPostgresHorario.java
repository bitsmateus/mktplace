package br.com.senai.core.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;

import br.com.senai.core.dao.DaoHorario;
import br.com.senai.core.dao.ManagerDb;
import br.com.senai.core.domain.Horario;

public class DaoPostgresHorario implements DaoHorario {
	
	

	private final String INSERT = "INSERT INTO horarios_atendimento (dia_semana, hora_abertura, hora_fechamento, id_restaurante) VALUES (?, ?, ?, ?)";
	private final String UPDATE = "UPDATE horarios_atendimento SET dia_semana = ?, hora_abertura = ?, hora_fechamento = ?, id_restaurante = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM horarios_atendimento WHERE id = ?";
	private final String COUNT_BY_REST = "SELECT Count(*) qtde "
			+ "FROM horarios_atendimento h"
			+ "WHERE h.id_restaurante = ?";
	
	private Connection conexao;
	
	public void DaoPostgresHorarios() {
		this.conexao = ManagerDb.getInstance().getConexao();
	}
	
	@Override
	public void inserir(Horario horario) {
		

	}

	@Override
	public void alterar(Horario horario) {
		

	}

	@Override
	public void excluirPor(int id) {
		

	}

	@Override
	public int contarPor(int idDoRestaurante) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conexao.prepareStatement(COUNT_BY_REST);
			ps.setInt(1, idDoRestaurante);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("qtde");
			}
			return 0;
			
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao contar os horarios. "
					+ "Motivo: " + e.getMessage());
		}finally {
			ManagerDb.getInstance().fechar(ps);
			ManagerDb.getInstance().fechar(rs);
		}
	}

}
