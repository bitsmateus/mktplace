package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
import br.com.senai.core.service.CategoriaService;
import br.com.senai.view.componentes.table.CategoriaTableModel;

public class ViewListagemCategoria extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtFiltro;
	private JTable tableCategorias;
	private CategoriaService service = new CategoriaService(); 

	
	public ViewListagemCategoria() {
		CategoriaTableModel model = new CategoriaTableModel(new ArrayList<Categoria>());
		this.tableCategorias = new JTable(model);
		tableCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setTitle("Gerenciar Categoria - Listagem");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFiltro = new JLabel("Filtros");
		lblFiltro.setBounds(12, 23, 55, 16);
		contentPane.add(lblFiltro);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(69, 53, 55, 16);
		contentPane.add(lblNome);
		
		edtFiltro = new JTextField();
		edtFiltro.setBounds(111, 51, 360, 20);
		contentPane.add(edtFiltro);
		edtFiltro.setColumns(10);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String filtroInformado = edtFiltro.getText();
					List<Categoria> categoriaEncontrado = service.listarPor(filtroInformado);
					boolean isCategoriaOk = categoriaEncontrado.isEmpty();
					if (isCategoriaOk) {
						JOptionPane.showMessageDialog(contentPane, "Nenhum resultado encontrado.");
					} else {
						CategoriaTableModel model = new CategoriaTableModel(categoriaEncontrado);
						tableCategorias.setModel(model);
						configurarTabela();
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
				
				ViewCadastroCategoria view = new ViewCadastroCategoria();
				view.setVisible(true);
				dispose();
				
			}
		});
		btnNovo.setBounds(483, 10, 98, 26);
		contentPane.add(btnNovo);
		
		JLabel lblTable = new JLabel("Categorias Encontadas");
		lblTable.setBounds(12, 93, 139, 16);
		contentPane.add(lblTable);
		
		JScrollPane scrollPane = new JScrollPane(tableCategorias);
		scrollPane.setBounds(12, 117, 569, 169);
		contentPane.add(scrollPane);
		
		JLabel lblAcoes = new JLabel("Ações");
		lblAcoes.setBounds(336, 299, 42, 16);
		contentPane.add(lblAcoes);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tableCategorias.getSelectedRow();
				CategoriaTableModel model = (CategoriaTableModel) tableCategorias.getModel();
				boolean isLinhaOK = linhaSelecionada >= 0 && !model.isVazio();
				if (isLinhaOK) {
					int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente excluir essa opção?", "Remoção!", JOptionPane.YES_NO_OPTION);
					
					if (opcao == 0) {
						Categoria categoriaSelecionada = model.getPor(linhaSelecionada);
						try {
							service.removerPor(categoriaSelecionada.getId());
							model.removerPor(linhaSelecionada);
							tableCategorias.updateUI();
							JOptionPane.showMessageDialog(contentPane, "Categoria removida com sucesso!!");
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(contentPane, e2.getMessage());
						}
					}
				} else {
					JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para excluir!");
				}
				
			}
			
			
			
		});
		btnExcluir.setBounds(469, 328, 98, 26);
		contentPane.add(btnExcluir);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linhaSelecionada = tableCategorias.getSelectedRow();
				boolean isLinhaOK = linhaSelecionada >= 0;
				if (isLinhaOK) {
					CategoriaTableModel model = (CategoriaTableModel) tableCategorias.getModel();
					Categoria categoriaSelecionada = model.getPor(linhaSelecionada);
					ViewCadastroCategoria view = new ViewCadastroCategoria();
					view.setCategoria(categoriaSelecionada);
					view.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para edição");
				}
				
			}
		});
		btnEditar.setBounds(362, 328, 98, 26);
		contentPane.add(btnEditar);
		
		
		
		setLocationRelativeTo(null);
	}
	
	private void configurarColuna(int indice, int largura) {
		this.tableCategorias.getColumnModel().getColumn(indice).setResizable(true);
		this.tableCategorias.getColumnModel().getColumn(indice).setPreferredWidth(largura);
	}
	
	private void configurarTabela() {
		final int COLUNA_ID = 0;
		final int COLUNA_NOME = 1;
		this.tableCategorias.getTableHeader().setReorderingAllowed(false);
		this.tableCategorias.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}
