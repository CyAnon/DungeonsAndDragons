package com.cyanon.dandd.gui;

import javax.swing.*;

public class MainFrame {

	private static void createAndShowGUI()
	{
		JFrame jframe = new JFrame("HelloWorldSwing");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("DungeonsAndDragons");
		jframe.getContentPane().add(label);
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	public MainFrame()
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}