package br.com.senai.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.senai.core.domain.Categoria;
import br.com.senai.core.service.CategoriaService;

public class ViewCadastroCategoria extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edtNome;
	private Categoria categoria;
	private CategoriaService service = new CategoriaService();

	
	public ViewCadastroCategoria() {
		setTitle("Gerenciar Categoria - Cadastro");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 40, 35, 14);
		contentPane.add(lblNome);
		
		edtNome = new JTextField();
		edtNome.setBounds(44, 37, 380, 20);
		contentPane.add(edtNome);
		edtNome.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewListagemCategoria view = new ViewListagemCategoria();
				view.setVisible(true);
				dispose();
				
			}
		});
		btnPesquisar.setBounds(319, 3, 105, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int opcao = JOptionPane.showConfirmDialog(contentPane, "Deseja realmente cancelar a operação?");
				if (opcao == 0) {
					edtNome.setText("");
				}
				
			}
		});
		btnCancelar.setBounds(335, 68, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					String nome = edtNome.getText();
					
					if (categoria == null) {
						categoria = new Categoria(nome);
						service.salvar(categoria);
						JOptionPane.showMessageDialog(contentPane, "Categoria cadastrada com sucesso!");
						categoria = null;
						edtNome.setText("");
					} else {
						categoria.setNome(nome);
						service.salvar(categoria);
						JOptionPane.showMessageDialog(contentPane, "Categoria alterada com sucesso!!");
						categoria = null;
					}
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
					boolean isCategoriaOk = categoria.getId() <= 0;
					if (isCategoriaOk) {
						categoria = null;
					}
				}
				
			}
		});
		btnSalvar.setBounds(236, 68, 89, 23);
		contentPane.add(btnSalvar);
		setLocationRelativeTo(null);
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
		this.edtNome.setText(categoria.getNome());
	}
}
