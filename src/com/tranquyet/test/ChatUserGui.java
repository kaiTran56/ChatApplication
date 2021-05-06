package com.tranquyet.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class ChatUserGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Thread read;
	private String serverName;
	private int port;
	private String name;
	public BufferedReader input;
	public PrintWriter output;
	public Socket socket;

	private JTextField textInputMessage_1 = new JTextField();
	private JPanel panelSignIn = new JPanel();
	private JPanel panelLogin = new JPanel();
	private JPanel panelChatPublic = new JPanel();
	private JPanel panelChatPrivate = new JPanel();
	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textMessageInput = new JTextField();
	private JTextField textInputName = new JTextField();;

	private JTextField textUsername_sign;
	private JTextField textPassword_sign;
	private JTextField textRepassword_sign;
	private JTabbedPane tabbedPane;
	private GroupLayout gl_contentPane;
	private JTextPane textListUser;
	private JTextPane textMessageShow_1 = new JTextPane();
	private JTextPane textPrivateMessageShow;
	private String message;
	private ArrayList<String> ListUser;
	private int number;
	private String value;

	private JLabel lblUserName = new JLabel("World");

	private JList<String> listUser = new JList<>(model);

	public static DefaultListModel<String> model = new DefaultListModel<>();
	private JTextField textOptionName = new JTextField();;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatUserGui frame = new ChatUserGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatUserGui() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("ChatApplication");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap().addComponent(tabbedPane).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		displayLogin();
		displayChatPublic();
		displayPrivateChat();

	}
	/*
	 * 
	 * 
	 * @panelLogin:
	 * 
	 * 
	 * 
	 */

	public void displayLogin() {

		this.serverName = "127.0.0.1";
		this.port = 9999;
		this.name = "";

		tabbedPane.addTab("Login", null, panelLogin, null);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));

		textUsername = new JTextField();
		textUsername.setColumns(10);

		textPassword = new JTextField();
		textPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {

				try {
					tabbedPane.remove(panelLogin);
					name = textUsername.getText();
					port = 9999;
					serverName = "127.0.0.1";
					socket = new Socket(serverName, port);

					input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					output = new PrintWriter(socket.getOutputStream(), true);
					// create a sesion to login
					String session = textUsername.getText() + "$" + textPassword.getText();
					output.println(session);

					read = new Read();
					read.start();
					System.out.println("Number: " + number);

					tabbedPane.remove(panelLogin);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		JButton btnSignIn = new JButton("Sign in");

		btnSignIn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				tabbedPane.remove(panelLogin);

			}
		});

		GroupLayout gl_panelLogin = new GroupLayout(panelLogin);
		gl_panelLogin.setHorizontalGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING).addGroup(gl_panelLogin
				.createSequentialGroup().addGap(122)
				.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelLogin.createSequentialGroup()
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(textUsername,
										GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelLogin.createSequentialGroup()
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelLogin.createSequentialGroup().addComponent(btnLogin)
												.addGap(18).addComponent(btnSignIn))
										.addComponent(textPassword))))
				.addContainerGap(139, Short.MAX_VALUE)));
		gl_panelLogin.setVerticalGroup(gl_panelLogin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLogin.createSequentialGroup().addGap(112)
						.addGroup(gl_panelLogin.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelLogin.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(textPassword, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
						.addGap(18).addGroup(gl_panelLogin.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogin).addComponent(btnSignIn))
						.addContainerGap(126, Short.MAX_VALUE)));
		panelLogin.setLayout(gl_panelLogin);
	}

	/*
	 * @panelChatPublic:
	 * 
	 * 
	 */

	public void displayChatPublic() {

		tabbedPane.addTab("Public", null, panelChatPublic, null);

		textListUser = new JTextPane();

		textListUser.setContentType("text/html");
		textListUser.setEditable(false);

		textMessageInput.setColumns(10);
		textMessageInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		textInputName.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		textInputName.setForeground(Color.BLUE);
		textInputName.setText("Enter name:");
		textInputName.setColumns(10);

		JButton btnConnectChat = new JButton("Private Chat");

		btnConnectChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				displayPrivateChat();
			}
		});

		JButton btnSendMessage = new JButton("Send");

		btnSendMessage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();

			}

		});

		JButton btnDisconnect = new JButton("Out");

		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout gl_panelChatPublic = new GroupLayout(panelChatPublic);
		gl_panelChatPublic
				.setHorizontalGroup(gl_panelChatPublic.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelChatPublic.createSequentialGroup().addContainerGap()
								.addGroup(gl_panelChatPublic.createParallelGroup(Alignment.LEADING)
										.addComponent(textMessageInput, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(gl_panelChatPublic.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panelChatPublic.createSequentialGroup().addGap(4).addGroup(
												gl_panelChatPublic.createParallelGroup(Alignment.LEADING, false)
														.addComponent(btnConnectChat, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(textInputName).addComponent(textListUser,
																GroupLayout.PREFERRED_SIZE, 152,
																GroupLayout.PREFERRED_SIZE)))
										.addGroup(gl_panelChatPublic.createSequentialGroup()
												.addComponent(btnSendMessage, GroupLayout.PREFERRED_SIZE, 73,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnDisconnect, GroupLayout.PREFERRED_SIZE, 69,
														GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		gl_panelChatPublic.setVerticalGroup(gl_panelChatPublic.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelChatPublic.createSequentialGroup()
						.addGroup(gl_panelChatPublic.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelChatPublic.createSequentialGroup().addGap(12)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(textMessageInput, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
								.addGroup(gl_panelChatPublic.createSequentialGroup().addContainerGap()
										.addGroup(gl_panelChatPublic.createSequentialGroup()
												.addComponent(textListUser, GroupLayout.PREFERRED_SIZE, 168,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textInputName, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
														btnConnectChat, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panelChatPublic.createParallelGroup(Alignment.LEADING)
												.addComponent(btnSendMessage, GroupLayout.DEFAULT_SIZE, 73,
														Short.MAX_VALUE)
												.addComponent(btnDisconnect, GroupLayout.DEFAULT_SIZE, 73,
														Short.MAX_VALUE))))
						.addGap(35)));
		textMessageShow_1.setContentType("text/html");
		textMessageShow_1.setEditable(false);
		scrollPane.setViewportView(textMessageShow_1);
		panelChatPublic.setLayout(gl_panelChatPublic);

	}

	/*
	 * 
	 * @panenlChatPrivate:
	 * 
	 * 
	 */

	public void addFriend(String message) {
		model.addElement(message);
	}

	public void removeFriend() {
		model.clear();
	}

	public void displayPrivateChat() {

		tabbedPane.addTab("Private", null, panelChatPrivate, null);

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				tabbedPane.remove(panelChatPrivate);
			}
		});
		textPrivateMessageShow = new JTextPane();

		textInputMessage_1.setColumns(10);

		textInputMessage_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// form of message for private
					String user = textOptionName.getText();
					String messagePrivate = textInputMessage_1.getText();
					if (!user.trim().equals("")) {
						sendMessagePrivate(user, messagePrivate);
					} else {
						return;
					}
				}
			}
		});

		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// form of message for private
				String user = textOptionName.getText();
				String messagePrivate = textInputMessage_1.getText();
				if (!user.trim().equals("")) {
					sendMessagePrivate(user, messagePrivate);
				} else {
					return;
				}

			}
		});

		JButton btnIconSmile = new JButton("Smile");

		JButton btnIconLike = new JButton("Like");

		JScrollPane scrollPane = new JScrollPane();
		textPrivateMessageShow.setContentType("text/html");
		textPrivateMessageShow.setEditable(false);
		scrollPane.setViewportView(textPrivateMessageShow);
		listUser.setForeground(Color.BLUE);

		listUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				value = (String) listUser.getModel().getElementAt(listUser.locationToIndex(arg0.getPoint()));

				textOptionName.setText(null);
				textOptionName.setText(value);

			}
		});

		JLabel lblNewLabel = new JLabel("User: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));

		lblUserName.setForeground(Color.RED);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));

		textOptionName.setFont(new Font("Tahoma", Font.BOLD, 12));
		textOptionName.setBackground(Color.BLACK);
		textOptionName.setForeground(Color.RED);
		textOptionName.setEditable(false);
		textOptionName.setColumns(10);

		GroupLayout gl_panelChatPrivate = new GroupLayout(panelChatPrivate);
		gl_panelChatPrivate.setHorizontalGroup(gl_panelChatPrivate.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChatPrivate.createSequentialGroup().addGroup(gl_panelChatPrivate
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelChatPrivate.createSequentialGroup().addGap(10).addGroup(gl_panelChatPrivate
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelChatPrivate.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 393,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_panelChatPrivate.createParallelGroup(Alignment.TRAILING)
												.addGroup(Alignment.LEADING,
														gl_panelChatPrivate.createSequentialGroup()
																.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
																		45, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(lblUserName, GroupLayout.DEFAULT_SIZE, 86,
																		Short.MAX_VALUE))
												.addGroup(gl_panelChatPrivate.createParallelGroup(Alignment.LEADING)
														.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 114,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(listUser, GroupLayout.PREFERRED_SIZE, 119,
																GroupLayout.PREFERRED_SIZE)))
										.addGap(11))
								.addGroup(gl_panelChatPrivate.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textOptionName, GroupLayout.PREFERRED_SIZE, 52,
												GroupLayout.PREFERRED_SIZE)
										.addGap(10)
										.addComponent(textInputMessage_1, GroupLayout.DEFAULT_SIZE, 336,
												Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 65,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnIconLike)
										.addGap(21))))
						.addGroup(gl_panelChatPrivate.createSequentialGroup().addGap(90).addComponent(btnIconSmile,
								GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panelChatPrivate.setVerticalGroup(gl_panelChatPrivate.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelChatPrivate.createSequentialGroup().addGap(10).addGroup(gl_panelChatPrivate
						.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelChatPrivate.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelChatPrivate.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 29,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(listUser, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnQuit, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
						.addGap(7)
						.addGroup(gl_panelChatPrivate.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textOptionName, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
								.addGroup(gl_panelChatPrivate.createParallelGroup(Alignment.BASELINE)
										.addComponent(textInputMessage_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
										.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnIconLike, GroupLayout.PREFERRED_SIZE, 38,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnIconSmile, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addGap(51)));
		panelChatPrivate.setLayout(gl_panelChatPrivate);
	}

	public void displaySignIn() {
		/*
		 * 
		 * @panelSginIn:
		 * 
		 */

		tabbedPane.addTab("Sign in", null, panelSignIn, null);

		JLabel lblUsername_sign = new JLabel("Username: ");
		lblUsername_sign.setFont(new Font("Tahoma", Font.BOLD, 14));

		JLabel lblPassword_sign = new JLabel("Password: ");
		lblPassword_sign.setFont(new Font("Tahoma", Font.BOLD, 14));

		textUsername_sign = new JTextField();
		textUsername_sign.setColumns(10);

		textPassword_sign = new JTextField();
		textPassword_sign.setColumns(10);

		textRepassword_sign = new JTextField();
		textRepassword_sign.setColumns(10);

		JLabel lblRepassword_sign = new JLabel("Re-Password: ");
		lblRepassword_sign.setFont(new Font("Tahoma", Font.BOLD, 14));

		JButton btnSign = new JButton("Sign in");

		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				displayLogin();
			}
		});

		GroupLayout gl_panelSignIn = new GroupLayout(panelSignIn);
		gl_panelSignIn.setHorizontalGroup(gl_panelSignIn.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSignIn.createSequentialGroup().addGap(62)
						.addGroup(gl_panelSignIn.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblRepassword_sign, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblPassword_sign, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(lblUsername_sign, GroupLayout.PREFERRED_SIZE, 76, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelSignIn.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSignIn.createSequentialGroup()
										.addComponent(btnSign, GroupLayout.PREFERRED_SIZE, 86,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(gl_panelSignIn.createSequentialGroup()
										.addGroup(gl_panelSignIn.createParallelGroup(Alignment.TRAILING)
												.addComponent(textRepassword_sign, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
												.addComponent(textPassword_sign, Alignment.LEADING)
												.addComponent(textUsername_sign, Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
										.addGap(147)))));
		gl_panelSignIn.setVerticalGroup(gl_panelSignIn.createParallelGroup(Alignment.LEADING).addGroup(gl_panelSignIn
				.createSequentialGroup().addGap(74)
				.addGroup(gl_panelSignIn.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername_sign, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textUsername_sign, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
				.addGap(34)
				.addGroup(gl_panelSignIn.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword_sign, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(textPassword_sign, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panelSignIn.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblRepassword_sign, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(textRepassword_sign, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
				.addGap(26).addComponent(btnSign, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(75, Short.MAX_VALUE)));
		panelSignIn.setLayout(gl_panelSignIn);
		contentPane.setLayout(gl_contentPane);
	}

	public void sendMessage() {
		String message = textMessageInput.getText().trim();
		if (message.equals("")) {
			return;
		}

		output.println(message);
		textMessageInput.requestFocus();
		textMessageInput.setText(null);

	}

	public void sendMessagePrivate(String user, String message) {
		message = message.trim();
		if (message.equals("")) {
			return;
		}
		message = user + message;
		System.out.println("Test form for private message: " + message);
		output.println(message);
		textInputMessage_1.requestFocus();
		textInputMessage_1.setText(null);

	}

	private void appendToPane(JTextPane tp, String message) {
		HTMLDocument doc = (HTMLDocument) tp.getDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) tp.getEditorKit();
		try {
			editorKit.insertHTML(doc, doc.getLength(), message, 0, 0, null);
			tp.setCaretPosition(doc.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Read extends Thread {
		public void run() {

			// boolean check = false;
			while (!Thread.currentThread().isInterrupted()) {
				try {

					message = input.readLine();

					// check existed name!
					if (message.equals("Name is exist!")) {

						JOptionPane.showMessageDialog(contentPane, "The name is Existed!. Could not connect to Server");
						System.exit(0);

					}

					// check login from server
					else if (message.equals("fail to login")) {

						JOptionPane.showMessageDialog(contentPane, "Fail to Login, please run again!");
						System.exit(0);
					}

					if (message != null) {
						if (message.charAt(0) == '[') {
							message = message.substring(1, message.length() - 1);

							// count number on the system
							ListUser = new ArrayList<String>(Arrays.asList(message.split(", ")));
							number = ListUser.size();

							// reset the list of user who you want to connect!
							removeFriend();

							// reset the list User online on the system but can not click on theses
							textListUser.setText(null);

							// set name for user in the private chat
							lblUserName.setText(null);
							lblUserName.setText(name);

							// show the current user
							appendToPane(textListUser, "<b style = 'color: black;'>User: </b>"
									+ "<span style='color:green;'>" + name + "</span>" + "(you)");

							for (String user : ListUser) {

								// show number user on the public chat
								appendToPane(textListUser, "@" + user);

								// show the list user on the private chat
								String userTemp = user.replace("<u><span style='color: red'>", "@")
										.replace("</span></u>", ": ");
								addFriend(userTemp);

							}

						}
						// show number user on the system
						else if (message.contains("Number:")) {

							textInputName.setText(null);
							textInputName.setText(message);

						}
						// show private chat on the private chat GUI
						else if (message.contains("->")) {
							appendToPane(textPrivateMessageShow, "<h3>" + message + "</h3>");

						}
						// show pubic chat on the public chat GUI
						else {
							appendToPane(textMessageShow_1, "<h3>" + message + "</h3>");
						}
					}

				} catch (IOException ex) {
					System.exit(0);
				}
			}
		}
	}
}
