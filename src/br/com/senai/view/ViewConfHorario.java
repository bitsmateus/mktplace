package br.com.senai.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Restaurante;

public class ViewConfHorario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtAbertura;
	private JTextField edtFechamento;
	private JTable tableHorario;
	private JComboBox<Restaurante> cbRestaurante;

	public ViewConfHorario() {
		setTitle("Gerenciar Horários - Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 666, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestaurante = new JLabel("Restaurante");
		lblRestaurante.setBounds(25, 36, 70, 16);
		contentPane.add(lblRestaurante);
		
		cbRestaurante = new JComboBox<Restaurante>();
		cbRestaurante.setBounds(100, 32, 538, 25);
		contentPane.add(cbRestaurante);
		
		JLabel lblDiaSemana = new JLabel("Dia da Semana");
		lblDiaSemana.setBounds(12, 73, 85, 16);
		contentPane.add(lblDiaSemana);
		
		JComboBox cbDiaSemana = new JComboBox();
		cbDiaSemana.setBounds(100, 69, 117, 25);
		contentPane.add(cbDiaSemana);
		
		JLabel lblAbertura = new JLabel("Abertura");
		lblAbertura.setBounds(221, 73, 56, 16);
		contentPane.add(lblAbertura);
		
		edtAbertura = new JTextField();
		edtAbertura.setBounds(272, 71, 96, 20);
		contentPane.add(edtAbertura);
		edtAbertura.setColumns(10);
		
		JLabel lblFechamento = new JLabel("Fechamento");
		lblFechamento.setBounds(368, 73, 70, 16);
		contentPane.add(lblFechamento);
		
		edtFechamento = new JTextField();
		edtFechamento.setColumns(10);
		edtFechamento.setBounds(439, 71, 96, 20);
		contentPane.add(edtFechamento);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBounds(542, 69, 96, 26);
		contentPane.add(btnAdicionar);
		
		JLabel lblHorarios = new JLabel("Horários");
		lblHorarios.setBounds(12, 123, 56, 16);
		contentPane.add(lblHorarios);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 151, 398, 145);
		contentPane.add(scrollPane);
		
		tableHorario = new JTable();
		scrollPane.setViewportView(tableHorario);
		
		JLabel lblAcoes = new JLabel("Ações");
		lblAcoes.setBounds(428, 151, 36, 16);
		contentPane.add(lblAcoes);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(439, 179, 199, 26);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(439, 225, 199, 26);
		contentPane.add(btnExcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(542, 337, 96, 26);
		contentPane.add(btnCancelar);
	
		setLocationRelativeTo(null);
	}
}
