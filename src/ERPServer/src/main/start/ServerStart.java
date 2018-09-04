package main.start;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Data.hibernateHelper.HibernateHelper;
import main.RMI.RemoteHelper;

public class ServerStart{

	
	public static void main(String[] args) {
		HibernateHelper.initHibernateHelper();
		RemoteHelper.initServer();
		
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setTitle("ERPServer");
		frame.setSize(400, 300);
		JLabel label = new JLabel("服务器已开启");
		panel.add(label);
		frame.getContentPane().add(panel);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
