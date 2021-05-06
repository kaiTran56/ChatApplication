package com.tranquyet.server;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ServerGui {

	public static int port = 9999;
	private JFrame frameServer;
	private JTextField txtIP, txtPort;
	private static TextArea txtMessage;
	public static JLabel lblUserOnline;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {

			try {
				ServerGui window = new ServerGui();
				window.frameServer.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	public ServerGui() {
		initialize();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frameServer = new JFrame();
		frameServer.getContentPane().setBackground(Color.GRAY);
		frameServer.setForeground(UIManager.getColor("RadioButtonMenuItem.foreground"));
		frameServer.getContentPane().setFont(new Font("SimSun", Font.BOLD | Font.ITALIC, 14));
		frameServer.getContentPane()
				.setForeground(UIManager.getColor("RadioButtonMenuItem.acceleratorSelectionForeground"));
		frameServer.setTitle("ServerChat");
		frameServer.setResizable(false);
		frameServer.setBounds(200, 200, 392, 313);
		frameServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameServer.getContentPane().setLayout(null);
		frameServer.setBackground(Color.DARK_GRAY);

		JLabel lblIP = new JLabel("Ip Address:");
		lblIP.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblIP.setBounds(140, 77, 89, 16);
		frameServer.getContentPane().add(lblIP);

		txtIP = new JTextField();
		txtIP.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtIP.setEditable(false);
		txtIP.setBounds(239, 72, 146, 28);
		frameServer.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		try {
			txtIP.setText(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("Port:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(0, 77, 48, 16);
		frameServer.getContentPane().add(lblNewLabel);

		txtPort = new JTextField();
		txtPort.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPort.setEditable(false);
		txtPort.setColumns(10);
		txtPort.setBounds(36, 72, 48, 28);
		frameServer.getContentPane().add(txtPort);
		txtPort.setText("9999");

		JButton btnStart = new JButton("Start");
		btnStart.setForeground(Color.BLACK);
		btnStart.setBackground(Color.GREEN);
		btnStart.setFont(new Font("SansSerif", Font.BOLD, 14));

		btnStart.setBounds(296, 216, 76, 42);
		frameServer.getContentPane().add(btnStart);

		JLabel lblNhom = new JLabel("Server Management");
		lblNhom.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNhom.setBounds(104, 0, 182, 52);

		frameServer.getContentPane().add(lblNhom);

		txtMessage = new TextArea();
		txtMessage.setBackground(Color.BLACK);
		txtMessage.setForeground(Color.GREEN);
		txtMessage.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtMessage.setEditable(false);
		txtMessage.setBounds(0, 99, 385, 111);
		frameServer.getContentPane().add(txtMessage);

		JButton btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnStop.setBounds(213, 216, 76, 42);
		frameServer.getContentPane().add(btnStop);

		JLabel lbllabelUserOnline = new JLabel("Online User:");
		lbllabelUserOnline.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lbllabelUserOnline.setBounds(0, 211, 94, 52);
		frameServer.getContentPane().add(lbllabelUserOnline);

		lblUserOnline = new JLabel("0");
		lblUserOnline.setForeground(Color.BLUE);
		lblUserOnline.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		lblUserOnline.setBounds(99, 220, 21, 34);
		frameServer.getContentPane().add(lblUserOnline);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					ServerGui.updateMessage("Server Starting....!");

					new Server(9999).run();

				} catch (Exception e) {
					ServerGui.updateMessage("Something wrong when Server start. Please, check again!");
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * <Functions>
	 * 
	 */

	public static void updateMessage(String message) {
		System.out.println("Message: " + message);
		txtMessage.append(message + "\n");

	}
}
