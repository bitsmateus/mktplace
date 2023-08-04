package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.domain.Endereco;
import br.com.senai.core.domain.Restaurante;
import br.com.senai.core.service.CategoriaService;
import br.com.senai.core.service.RestauranteService;

public class ViewCadastroRestaurante extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private JTextField edtDesc;
	private JTextField edtLogradouro;
	private JTextField edtCidade;
	private JTextField edtBairro;
	private JTextField edtComplemento;
	private CategoriaService catService = new CategoriaService();
	private JComboBox<Categoria> cbCategorias;
	private RestauranteService service = new RestauranteService();
	private Restaurante restaurante;
	private Endereco endereco;
	
	public void carregarComboCategoria() {
		List<Categoria> categorias = catService.listarTodas();
		for (Categoria cat : categorias) {
			cbCategorias.addItem(cat);
		}
	}

	
	public ViewCadastroRestaurante() {
		setTitle("Gerenciar Restaurante - Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(69, 54, 33, 16);
		contentPane.add(lblNome);
		
		edtNome = new JTextField();
		edtNome.setBounds(109, 52, 227, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(350, 54, 55, 16);
		contentPane.add(lblCategoria);
		
		cbCategorias = new JComboBox<Categoria>();
		cbCategorias.setToolTipText("Selecione.");
		cbCategorias.setBounds(414, 50, 160, 25);
		contentPane.add(cbCategorias);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListagemRestaurante view = new ViewListagemRestaurante();
				view.setVisible(true);
				dispose();
			}
		});
		btnPesquisar.setBounds(476, 12, 98, 26);
		contentPane.add(btnPesquisar);
		
		JLabel lblDescricao = new JLabel("Descrição");
		lblDescricao.setBounds(44, 82, 58, 16);
		contentPane.add(lblDescricao);
		
		edtDesc = new JTextField();
		edtDesc.setColumns(10);
		edtDesc.setBounds(109, 84, 465, 97);
		contentPane.add(edtDesc);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(36, 189, 66, 16);
		contentPane.add(lblLogradouro);
		
		edtLogradouro = new JTextField();
		edtLogradouro.setColumns(10);
		edtLogradouro.setBounds(109, 187, 465, 20);
		contentPane.add(edtLogradouro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(63, 224, 39, 16);
		contentPane.add(lblCidade);
		
		edtCidade = new JTextField();
		edtCidade.setColumns(10);
		edtCidade.setBounds(108, 222, 212, 20);
		contentPane.add(edtCidade);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(331, 224, 39, 16);
		contentPane.add(lblBairro);
		
		edtBairro = new JTextField();
		edtBairro.setColumns(10);
		edtBairro.setBounds(378, 222, 196, 20);
		contentPane.add(edtBairro);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(23, 256, 79, 16);
		contentPane.add(lblComplemento);
		
		edtComplemento = new JTextField();
		edtComplemento.setColumns(10);
		edtComplemento.setBounds(109, 254, 465, 20);
		contentPane.add(edtComplemento);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente cancelar a operação?", "Apagar Campos!", JOptionPane.YES_NO_OPTION);
				if (opcao == 0) {
					edtNome.setText("");
					cbCategorias.setSelectedIndex(0);
					edtDesc.setText("");
					edtLogradouro.setText("");
					edtCidade.setText("");
					edtBairro.setText("");
					edtComplemento.setText("");
				}
				
			}
		});
		btnCancelar.setBounds(476, 288, 98, 26);
		contentPane.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String nome = edtNome.getText();
					Categoria categoria = (Categoria) cbCategorias.getSelectedItem();
					String descricao = edtDesc.getText();
					String logradouro = edtLogradouro.getText();
					String cidade = edtCidade.getText();
					String bairro = edtBairro.getText();
					String complemento = edtComplemento.getText();
					
					if (restaurante == null && endereco == null) {
						
						endereco = new Endereco(cidade, logradouro, bairro, complemento);
						restaurante = new Restaurante(nome, descricao, endereco, categoria);
						service.salvar(restaurante);
						JOptionPane.showMessageDialog(contentPane, "Restaurante Cadastrado com sucesso!!");
						endereco = null;
						restaurante = null;
						edtNome.setText("");
						cbCategorias.setSelectedIndex(0);
						edtDesc.setText("");
						edtLogradouro.setText("");
						edtCidade.setText("");
						edtBairro.setText("");
						edtComplemento.setText("");
						
					} else {
						
						restaurante.setNome(nome);
						restaurante.setCategoria(categoria);
						restaurante.setDescricao(descricao);
						restaurante.setEndereco(new Endereco(cidade, logradouro, bairro, complemento));
						service.salvar(restaurante);
						JOptionPane.showMessageDialog(contentPane, "Restaurante Alterado com sucesso!!");
						endereco = null;
						restaurante = null;
						
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					boolean isRestauranteOK = restaurante.getId() <= 0;
					if (isRestauranteOK) {
						restaurante = null;
					}
				}
				
			}
		});
		btnSalvar.setBounds(366, 288, 98, 26);
		contentPane.add(btnSalvar);
		
		setLocationRelativeTo(null);
		this.carregarComboCategoria();
		this.catService = new CategoriaService();
	}
	
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
		this.edtNome.setText(restaurante.getNome());
		this.cbCategorias.setSelectedItem(restaurante.getCategoria());
		this.edtDesc.setText(restaurante.getDescricao());
		this.edtLogradouro.setText(restaurante.getEndereco().getLogradouro());
		this.edtCidade.setText(restaurante.getEndereco().getCidade());
		this.edtBairro.setText(restaurante.getEndereco().getBairro());
		this.edtComplemento.setText(restaurante.getEndereco().getComplemento());
	}
}
