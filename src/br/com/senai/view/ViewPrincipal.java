package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ViewPrincipal extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public ViewPrincipal() {
		setTitle("Tela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 568, 23);
		contentPane.add(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenu mnCategoria = new JMenu("Categoria");
		mnCadastro.add(mnCategoria);
		
		JMenuItem mnCadastroCategoria = new JMenuItem("Cadastro");
		mnCadastroCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroCategoria view = new ViewCadastroCategoria();
				view.setVisible(true);
			}
		});
		mnCategoria.add(mnCadastroCategoria);
		
		JMenuItem mnListagemCategoria = new JMenuItem("Listagem");
		mnListagemCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListagemCategoria view = new ViewListagemCategoria();
				view.setVisible(true);
				
			}
		});
		mnCategoria.add(mnListagemCategoria);
		
		JMenu mnRestaurante = new JMenu("Restaurante");
		mnCadastro.add(mnRestaurante);
		
		JMenuItem mnCadastroRestaurante = new JMenuItem("Cadastro");
		mnCadastroRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCadastroRestaurante view = new ViewCadastroRestaurante();
				view.setVisible(true);
			}
		});
		mnRestaurante.add(mnCadastroRestaurante);
		
		JMenuItem mnListagemRestaurante = new JMenuItem("Listagem");
		mnListagemRestaurante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListagemRestaurante view = new ViewListagemRestaurante();
				view.setVisible(true);
				
			}
		});
		mnRestaurante.add(mnListagemRestaurante);
		
		JMenu mnNewMenu_1 = new JMenu("Configurações");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mnHorario = new JMenuItem("Horário");
		mnHorario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewConfHorario view = new ViewConfHorario();
				view.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mnHorario);
		
		JMenu mnSair = new JMenu("Sair");
		menuBar.add(mnSair);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		mnSair.add(mntmSair);
		
		setLocationRelativeTo(null);
	}
}
