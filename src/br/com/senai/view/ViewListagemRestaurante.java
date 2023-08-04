package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.domain.Restaurante;
import br.com.senai.core.service.CategoriaService;
import br.com.senai.core.service.RestauranteService;
import br.com.senai.view.componentes.table.RestauranteTableModel;

public class ViewListagemRestaurante extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JTable tableRestaurante;
	private RestauranteService service = new RestauranteService();
	private CategoriaService catService = new CategoriaService();
	private JComboBox<Categoria> cbCategoria;
	
	public void carregarComboCategoria() {
		List<Categoria> categorias = catService.listarTodas();
		for (Categoria cat : categorias) {
			cbCategoria.addItem(cat);
		}
	}

	public ViewListagemRestaurante() {
		this.service = new RestauranteService();
		RestauranteTableModel model = new RestauranteTableModel(new ArrayList<Restaurante>());
		this.tableRestaurante = new JTable(model);
		tableRestaurante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setTitle("Gerenciar Restaurante - Listagem");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtros");
		lblFiltro.setBounds(12, 23, 55, 16);
		contentPane.add(lblFiltro);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(63, 53, 39, 16);
		contentPane.add(lblNome);
		
		edtNome = new JTextField();
		edtNome.setBounds(114, 51, 134, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String filtroInformado = edtNome.getText();
					Categoria itemSelecionado = (Categoria) cbCategoria.getSelectedItem();
					List<Restaurante> restauranteEncontrado = service.listarPor(filtroInformado, itemSelecionado);
					boolean isRestauranteOK = restauranteEncontrado.isEmpty();
					if (isRestauranteOK) {
						JOptionPane.showMessageDialog(contentPane, "Não foi encontrado nenhum restaurante!!");
					} else {
						RestauranteTableModel model = new RestauranteTableModel(restauranteEncontrado);
						tableRestaurante.setModel(model);
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
				}
				
			}
		});
		btnListar.setBounds(483, 48, 98, 26);
		contentPane.add(btnListar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewCadastroRestaurante view = new ViewCadastroRestaurante();
				view.setVisible(true);
				dispose();
				
			}
		});
		btnNovo.setBounds(483, 10, 98, 26);
		contentPane.add(btnNovo);
		
		JLabel lblTable = new JLabel("Restaurantes Encontadas");
		lblTable.setBounds(12, 93, 154, 16);
		contentPane.add(lblTable);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(256, 53, 55, 16);
		contentPane.add(lblCategoria);
		
		cbCategoria = new JComboBox<Categoria>();
		cbCategoria.setBounds(317, 49, 154, 25);
		contentPane.add(cbCategoria);
		
		JScrollPane scrollPane = new JScrollPane(tableRestaurante);
		scrollPane.setBounds(12, 122, 569, 111);
		contentPane.add(scrollPane);
		
		JLabel lblAcoes = new JLabel("Ações");
		lblAcoes.setBounds(317, 245, 39, 16);
		contentPane.add(lblAcoes);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tableRestaurante.getSelectedRow();
				boolean isLinhaOK = linhaSelecionada >= 0;
				if (isLinhaOK) {
					RestauranteTableModel model = (RestauranteTableModel) tableRestaurante.getModel();
					Restaurante restauranteSelecionado = model.getPor(linhaSelecionada);
					ViewCadastroRestaurante view = new ViewCadastroRestaurante();
					view.setRestaurante(restauranteSelecionado);
					view.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para edição!");
				}
				
			}
		});
		btnEditar.setBounds(346, 268, 98, 26);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tableRestaurante.getSelectedRow();
				RestauranteTableModel model = (RestauranteTableModel) tableRestaurante.getModel();
				boolean isLinhaOK = linhaSelecionada >= 0 && !model.isVazio();
				if (isLinhaOK) {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente excluir?", "Remoção!", JOptionPane.YES_NO_OPTION);
					
					if (opcao == 0) {
						Restaurante restauranteSelecionado = model.getPor(linhaSelecionada);
						try {
							service.removerPor(restauranteSelecionado.getId());
							model.removerPor(linhaSelecionada);
							tableRestaurante.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Restaurante removido com sucesso!");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(contentPane, e2.getMessage());
						}
					} else {
						JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para remoção!");
					}
				}
				
			}
		});
		btnExcluir.setBounds(456, 268, 98, 26);
		contentPane.add(btnExcluir);
		
		setLocationRelativeTo(null);
		this.carregarComboCategoria();
	}
}
