package fox.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import fox.entities.PetClinic;
import fox.entities.clinicData.Photodir;
import fox.entities.clinicData.Webpage;


public class ClinicCreator extends JDialog {
	private PetClinic clinic;
	
	private Font labelsFont = new Font("Arial Narrow", Font.BOLD, 18); 
	private JTextField nameField, fiasField, phoneField, wPageField, phDirField;
	private JTextArea commentArea;
	private JPanel midAreasPane;
	private Color errBckg = new Color(1.0f, 0.75f, 0.75f, 1.0f);
	
	private Webpage wPage;
	private Photodir phDir;
	
	
	public ClinicCreator(JFrame parent) {
		super(parent, "Открытие новой клиники:", true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(350, 250));
		getContentPane().setLayout(new BorderLayout(3, 3));
		getRootPane().setBorder(new EmptyBorder(3, 6, 3, 3));
		
		JPanel upPane = new JPanel(new BorderLayout()) {
			{				
				JPanel leftLabelsPane = new JPanel(new GridLayout(0, 1, 0, 0)) {
					{
						add(new JLabel("Name:") {{setFont(labelsFont);}});
						add(new JLabel("FIAS:") {{setFont(labelsFont);}});
						add(new JLabel("Phone:") {{setFont(labelsFont);}});
						add(new JLabel("Web url:") {{setFont(labelsFont);}});
						add(new JLabel("Photo dir:") {{setFont(labelsFont);}});
					}
				};
				
				midAreasPane = new JPanel(new GridLayout(0, 1, 0, 0)) {
					{
						nameField = new JTextField();
						fiasField = new JTextField();
						phoneField = new JTextField();
						wPageField = new JTextField();
						
						JPanel photoLinePane = new JPanel(new BorderLayout(0,0)) {
							{
								
								phDirField = new JTextField() {
									{
										setHorizontalAlignment(0);
										setBackground(Color.DARK_GRAY);
										setForeground(Color.WHITE);
										addKeyListener(new KeyAdapter() {
											@Override
											public void keyReleased(KeyEvent e) {
												if (!getText().isBlank()) {
													setBackground(Color.DARK_GRAY);
												}
											}
										});
									}
								};
								
								JButton choiseDir = new JButton("...") {
									{
										addActionListener(new ActionListener() {											
											@Override public void actionPerformed(ActionEvent arg0) {
												phDirField.setText(choisePhotoDirectory().toString());
											}

											private Path choisePhotoDirectory() {
												JFileChooser phDirChooser = new JFileChooser("./media/photo") {
													{
														setDialogTitle("Выберите папку с фото клиники:");
														setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg"));
														setFileSelectionMode(DIRECTORIES_ONLY);
														setMultiSelectionEnabled(false);
													}
												};
												
												int result = phDirChooser.showOpenDialog(ClinicCreator.this);
												if (result == JFileChooser.APPROVE_OPTION) {
													return phDirChooser.getSelectedFile().toPath();
												}
												
												return null;
											}
										});
									}
								};
								
								add(phDirField, BorderLayout.CENTER);
								add(choiseDir, BorderLayout.EAST);
							}
						};
						
						add(nameField);
						add(fiasField);
						add(phoneField);
						add(wPageField);
						add(photoLinePane);
					}
				};
			
				add(leftLabelsPane, BorderLayout.WEST);
				add(midAreasPane, BorderLayout.CENTER);
			}
		};
				
		JPanel commentPane = new JPanel(new BorderLayout()) {
			{
				commentArea = new JTextArea() {
					{
						setBorder(new EmptyBorder(6, 6, 0, 3));
						
						setLineWrap(true);
						setWrapStyleWord(true);
						setBackground(Color.DARK_GRAY);
						setForeground(Color.WHITE);
					}
				};
				
				add(commentArea);
			}
		};
		
		add(upPane, BorderLayout.NORTH);
		add(commentPane, BorderLayout.CENTER);
		add(new JButton("Done") {
			{
				addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
						if (nameField.getText().isBlank()) {
							nameField.setBackground(errBckg);
							return;
						}
						
						if (phoneField.getText().isBlank()) {
							phoneField.setBackground(errBckg);
							return;
						}
						
						long phone = 0;
						try {phone = Long.parseLong(phoneField.getText());
						} catch (Exception e2) {
							phoneField.setBackground(errBckg);
							return;
						}
						
						if (!wPageField.getText().isBlank() && wPage == null) {
							try {wPage = new Webpage(new URL("http://" + wPageField.getText()));
							} catch (MalformedURLException e1) {
								wPageField.setBackground(errBckg);
								e1.printStackTrace();
								return;
							}
						}
						
						if (!phDirField.getText().isBlank() && phDir == null) {
							try {phDir = new Photodir(phDirField.getText());
							} catch (Exception e2) {
								phDirField.setBackground(errBckg);
								e2.printStackTrace();
								return;
							}
						}
						
						
						clinic = new PetClinic(nameField.getText(), fiasField.getText(), phone, wPage, phDir, commentArea.getText());
						dispose();
					}
				});
			}
		}, BorderLayout.SOUTH);
		
		for (Component comp : midAreasPane.getComponents()) {
			if (comp instanceof JTextField) {
				JTextField tmp = (JTextField) comp;
				tmp.setHorizontalAlignment(0);
				tmp.setBackground(Color.DARK_GRAY);
				tmp.setForeground(Color.WHITE);
				tmp.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (!tmp.getText().isBlank()) {
							tmp.setBackground(Color.DARK_GRAY);
						}
					}
				});
			}
		}
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public PetClinic get() {return clinic;}
}