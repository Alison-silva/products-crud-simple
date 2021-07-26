package products.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import products.dao.ProductDAO;
import products.model.Product;

public class Main extends JPanel{
	private JLabel jlDesc, jlQtd, jlPrice, jlSearch;
	private JTextField jtDesc, jtQtd, jtPrice, jtSearch;
	private JButton jbSave, jbDelete, jbUpdate, jbSearch;
	private JTable table;
	private JPanel pnTable;
	private JScrollPane scrollTable;
	DecimalFormat pc = new DecimalFormat("#,###.00");
	
	private Product product = new Product();
	private ProductDAO dao = new ProductDAO();
	
	public Main() {
		initializeComponents();
		defineEvents();
		tableFields();
	}
	
	private void initializeComponents() {
		setLayout(null);
		jlDesc = new JLabel("Descrição: ");
		jtDesc = new JTextField();
		jlQtd = new JLabel("Quantidade Kq: ");
		jtQtd = new JTextField();
		jlPrice = new JLabel("Preço: ");
		jtPrice = new JTextField();
		jbSave = new JButton("Salvar");
		jbDelete = new JButton("Deletar");
		jbUpdate = new JButton("Atualizar");
		jlSearch = new JLabel("Escreva a descrição do produto: ");
		jtSearch = new JTextField();
		jbSearch = new JButton("Buscar");
		
		jlDesc.setBounds(20, 20, 100, 20);
		jtDesc.setBounds(120, 20, 160, 20);
		jlQtd.setBounds(20, 60, 100, 20);
		jtQtd.setBounds(120, 60, 100, 20);
		jlPrice.setBounds(20, 100, 100, 20);
		jtPrice.setBounds(120, 100, 80, 20);
		jbSave.setBounds(20, 160, 100, 20);
		jbDelete.setBounds(170, 160, 100, 20);
		jbUpdate.setBounds(320, 160, 100, 20);
		jlSearch.setBounds(20, 200, 190, 20);
		jtSearch.setBounds(210, 200, 150, 20);
		jbSearch.setBounds(380, 200, 100, 20);
		
		add(jlDesc);
		add(jtDesc);
		add(jlQtd);
		add(jtQtd);
		add(jlPrice);
		add(jtPrice);
		add(jbSave);
		add(jbDelete);
		add(jbUpdate);
		add(jlSearch);
		add(jtSearch);
		add(jbSearch);
		
		pnTable = new JPanel(new BorderLayout());
		scrollTable = new JScrollPane();
		pnTable.setBorder(new TitledBorder("Produtos"));
		DefaultTableModel tableModel = new DefaultTableModel(
				new String[] { "Id", "Descrição", "Quantidade", "Preço" }, 0) {
			public boolean isCellEditable(int row, int col) {
				if (col == 3) {
					return false;
				}
				return true;
			}
		};
		table = new JTable(tableModel);
		
		DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
		alignRight.setHorizontalAlignment(SwingConstants.CENTER);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(alignRight);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(alignRight);
		
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		pnTable.setBounds(20, 250, 460, 200);
		add(pnTable);
		scrollTable.setViewportView(table);
		pnTable.add(scrollTable);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int selec = table.getSelectedRow();
				jtDesc.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				jtQtd.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				jtPrice.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			}
		});
		
		
		
	}
	
	private void defineEvents() {
		
		jbSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				product.setDescription(jtDesc.getText().trim());
				product.setQtd(Integer.parseInt(jtQtd.getText().trim()));
				product.setPrice(Double.parseDouble(jtPrice.getText().trim()));
				
				dao.save(product);
				clearFields();
				tableFields();
				
			}
		});
	
		jbSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				tableSearchFields(jtSearch.getText().trim().toString());
				
			}
		});
		
		jbUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					
					product.setDescription(jtDesc.getText().trim());
					product.setQtd(Integer.parseInt(jtQtd.getText().trim()));
					product.setPrice(Double.parseDouble(jtPrice.getText().trim()));
					product.setId((Long) table.getValueAt(table.getSelectedRow(), 0));
					dao.update(product);
					
					clearFields();
					tableFields();
					
				}
				
			}
		});
		
		jbDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					
					product.setId((Long) table.getValueAt(table.getSelectedRow(), 0));
					dao.delete(product);
					
					clearFields();
					tableFields();
					
				}
				
				
			}
		});
		
	}
	
	private void tableFields() {
		try {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setNumRows(0);
			ProductDAO dao = new ProductDAO();

			for (Product p : dao.read())
				dtm.addRow(new Object[] { p.getId(), p.getDescription(), p.getQtd(), pc.format(p.getPrice()) });

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void tableSearchFields(String bname) {
		try {
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setNumRows(0);
			ProductDAO dao = new ProductDAO();
			
			for(Product p: dao.searchTable(bname))
				
				dtm.addRow(new Object[] { p.getId(), p.getDescription(), p.getQtd(), pc.format(p.getPrice()) });

			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	private void clearFields() {
		jtDesc.setText("");
		jtQtd.setText("");
		jtPrice.setText("");
	}

}

























