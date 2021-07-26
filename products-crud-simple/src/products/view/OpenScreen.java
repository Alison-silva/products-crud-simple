package products.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import products.connection.SingleConnection;

public class OpenScreen {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Crud Mercado");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Main());
		frame.setBounds(0, 0, 520, 500);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((tela.width - frame.getSize().width) / 2,
						  (tela.height - frame.getSize().height) / 2);
		frame.setVisible(true);
		
		
	}

}
