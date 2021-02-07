package main.java;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import priorityItem;

public class Dispatcher {

	private JFrame frame;
	private JTextField txtNewprocess;
	private JTextField textPriorityNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dispatcher window = new Dispatcher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dispatcher() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1151, 673);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		DefaultListModel<priorityItem> listModelReady;
		DefaultListModel<priorityItem> listModelRun;
		DefaultListModel<priorityItem> listModelBlocked;
		DefaultListModel<priorityItem> listModelTerminated;
		
		listModelReady = new DefaultListModel<>();
		listModelRun = new DefaultListModel<>();
		listModelBlocked = new DefaultListModel<>();
		listModelTerminated = new DefaultListModel<>();
		
		JList<priorityItem> readyList = new JList<>(listModelReady);
		readyList.setBounds(25, 39, 261, 412);
		frame.getContentPane().add(readyList);
		
		JList<priorityItem> runList = new JList<>(listModelRun);
		runList.setBounds(328, 39, 261, 113);
		frame.getContentPane().add(runList);
		
		JList<priorityItem> blockList = new JList<>(listModelBlocked);
		blockList.setBounds(328, 226, 261, 225);
		frame.getContentPane().add(blockList);
		
		JList<priorityItem> terminateList = new JList<>(listModelTerminated);
		terminateList.setBounds(647, 39, 261, 412);
		frame.getContentPane().add(terminateList);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(975, 39, 138, 58);
		btnRun.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRun.setBackground(Color.GREEN);
		frame.getContentPane().add(btnRun);
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (readyList.getSelectedValue() == null) {
						readyList.setSelectedIndex(0);
						
						if (readyList.getModel().getSize() > 1) {
						    int j = 0;
							String temp1 = readyList.getSelectedValue().toString();
						    int x = Integer.parseInt(temp1.substring(temp1.length() - 1));
						    for (int i = 1; i < readyList.getModel().getSize(); i++) {
						    	readyList.setSelectedIndex(i);
							    String temp2 = readyList.getSelectedValue().toString();
							    int y = Integer.parseInt(temp2.substring(temp2.length() - 1));
							    if (y < x) {
							    	j=i;
							    }
						    }
						    readyList.setSelectedIndex(j);
						}
					}
					if (runList.getSelectedValue() == null) {
						runList.setSelectedIndex(0);
					}
					if (runList.getSelectedValue() == null) {
						
					    listModelRun.addElement(readyList.getSelectedValue());
					    listModelReady.removeElement(readyList.getSelectedValue());
					}
					else {
						readyList.clearSelection();
						runList.clearSelection();
					}
				}
				catch(Exception noRun) {
				}
			}
		});
		
		
		JButton btnBlock = new JButton("Block");
		btnBlock.setBounds(975, 123, 138, 58);
		btnBlock.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBlock.setBackground(Color.RED);
		frame.getContentPane().add(btnBlock);
		btnBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (runList.getSelectedValue() == null) {
						runList.setSelectedIndex(0);
					}
					listModelBlocked.addElement(runList.getSelectedValue());
					listModelRun.removeElement(runList.getSelectedValue());
				}
				catch(Exception noReady) {
				}
			}
		});
		
		
		JButton btnReady = new JButton("Ready");
		btnReady.setBounds(975, 211, 138, 58);
		btnReady.setForeground(Color.BLACK);
		btnReady.setBackground(Color.ORANGE);
		btnReady.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.getContentPane().add(btnReady);
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (blockList.getSelectedValue() == null) {
						blockList.setSelectedIndex(0);
					}
					listModelReady.addElement(blockList.getSelectedValue());
					listModelBlocked.removeElement(blockList.getSelectedValue());
				}
				catch(Exception noReady) {
				}
			}
		});
		
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setBounds(975, 349, 138, 58);
		frame.getContentPane().add(btnTerminate);
		btnTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, "Helloworld");
				try {
					if (runList.getSelectedValue() != null) { 
						listModelRun.removeElement(runList.getSelectedValue());
						
					}
					else if (readyList.getSelectedValue() != null) {
						listModelReady.removeElement(readyList.getSelectedValue());
					}
					else if (blockList.getSelectedValue() != null) {
					    listModelBlocked.removeElement(blockList.getSelectedValue());
					}
					else if (runList.getModel().getSize() >= 1){
						runList.setSelectedIndex(0);
						listModelRun.removeElement(runList.getSelectedValue());
                        readyList.setSelectedIndex(0);
		 				
						if (readyList.getModel().getSize() > 1) {
						    int j = 0;
							String temp1 = readyList.getSelectedValue().toString();
						    int x = Integer.parseInt(temp1.substring(temp1.length() - 1));
						    for (int i = 1; i < readyList.getModel().getSize(); i++) {
						    	readyList.setSelectedIndex(i);
							    String temp2 = readyList.getSelectedValue().toString();
							    int y = Integer.parseInt(temp2.substring(temp2.length() - 1));
							    if (y < x) {
							    	j=i;
							    }
						    }
						    readyList.setSelectedIndex(j);
						}
						listModelRun.addElement(readyList.getSelectedValue());
					    listModelReady.removeElement(readyList.getSelectedValue());
					}
					readyList.clearSelection();
					runList.clearSelection();
				}
				catch(Exception noReady) {
				}
			}
		});
		
		
		JButton btnTimeslice = new JButton("Timeslice");
		btnTimeslice.setBounds(975, 436, 138, 58);
		frame.getContentPane().add(btnTimeslice);
		btnTimeslice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (runList.getSelectedValue() == null) {
						runList.setSelectedIndex(0);
						
						if (runList.getSelectedValue() != null) {
							listModelReady.addElement(runList.getSelectedValue());
							listModelRun.removeElement(runList.getSelectedValue());
						}
					}
					
					if (readyList.getSelectedValue() == null) {
						readyList.setSelectedIndex(0);

						if (readyList.getModel().getSize() > 1) {
						    int j = 0;
							String temp1 = readyList.getSelectedValue().toString();
						    int x = Integer.parseInt(temp1.substring(temp1.length() - 1));
						    for (int i = 1; i < readyList.getModel().getSize() - 1; i++) {
						    	readyList.setSelectedIndex(i);
							    String temp2 = readyList.getSelectedValue().toString();
							    int y = Integer.parseInt(temp2.substring(temp2.length() - 1));
							    if (x > y) {
							    	j = i;
							    	x = y;
							    }
						    }
						readyList.setSelectedIndex(j);
						}
					}
					listModelRun.addElement(readyList.getSelectedValue());
					listModelReady.removeElement(readyList.getSelectedValue());	
				}
				catch(Exception noReady) {
				}
			}
		});
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(540, 548, 138, 58);
		frame.getContentPane().add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNewprocess.getText().length() != 0) {
						if (textPriorityNum.getText().length() != 0) {
						    priorityItem process = new priorityItem(txtNewprocess.getText(),Integer.parseInt(textPriorityNum.getText()));
						    listModelReady.addElement(process);
						    txtNewprocess.setText(null);
						    textPriorityNum.setText(null);
						} 
						else {
							priorityItem process = new priorityItem(txtNewprocess.getText());
							listModelReady.addElement(process);
						    txtNewprocess.setText(null);
						    textPriorityNum.setText(null);
						}
					}
					else {
						if (textPriorityNum.getText().length() != 0) {
						    priorityItem process = new priorityItem("generic",Integer.parseInt(textPriorityNum.getText()));
						    listModelReady.addElement(process);
						    txtNewprocess.setText(null);
						    textPriorityNum.setText(null);
						}
						else {
							priorityItem process = new priorityItem();
							listModelReady.addElement(process);
						    txtNewprocess.setText(null);
						    textPriorityNum.setText(null);
						}
					}
				}
				catch(Exception noEnteredProcess) {
				}
			}
		});
		
		
		txtNewprocess = new JTextField();
		txtNewprocess.setBounds(70, 548, 302, 58);
		frame.getContentPane().add(txtNewprocess);
		txtNewprocess.setColumns(10);
		
		textPriorityNum = new JTextField();
		textPriorityNum.setColumns(10);
		textPriorityNum.setBounds(387, 548, 138, 58);
		frame.getContentPane().add(textPriorityNum);
	}
}
