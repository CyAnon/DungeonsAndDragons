package com.cyanon.dandd.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame {

	JPanel mainPanel = new JPanel();
	
	public MainFrame()
	{
		super("Dungeons And Dragons Server");
		setBounds(100, 100, 640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane();
		con.add(mainPanel);
		setVisible(true);
	}
}
