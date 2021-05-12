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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fox.data.SEX;
import fox.entities.Doctor;


public class DoctorCreator extends JDialog {
	private Doctor doc;
	
	private Font labelsFont = new Font("Arial Narrow", Font.BOLD, 18);
	
	private JTextField nameField, phoneField, addressField;
	private JSpinner ageField;
	private JComboBox<String> sexField;
	private JPanel midAreasPane;
	
	private Color errBckg = new Color(1.0f, 0.75f, 0.75f, 1.0f);
	
	
	public DoctorCreator(JFrame parent) {
		super(parent, "Регистрация нового доктора:", true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(350, 250));
		getContentPane().setLayout(new BorderLayout(3, 3));
		getRootPane().setBorder(new EmptyBorder(3, 6, 3, 3));
		
		JPanel upPane = new JPanel(new BorderLayout()) {
			{
				JPanel leftLabelsPane = new JPanel(new GridLayout(0, 1, 0, 0)) {
					{
						add(new JLabel("Name:") {{setFont(labelsFont);}});
						add(new JLabel("Age:") {{setFont(labelsFont);}});
						add(new JLabel("Sex:") {{setFont(labelsFont);}});
						add(new JLabel("Phone:") {{setFont(labelsFont);}});
						add(new JLabel("Address:") {{setFont(labelsFont);}});
					}
				};
				
				midAreasPane = new JPanel(new GridLayout(0, 1, 0, 0)) {
					{
						nameField = new JTextField();
						ageField = new JSpinner();
						phoneField = new JTextField();
						DefaultComboBoxModel<String> cbm = new DefaultComboBoxModel<String>() {
							{
								addElement(SEX.MALE.name());
								addElement(SEX.FEMA.name());
							}
						};
						sexField = new JComboBox<String>(cbm);
						addressField = new JTextField();
						
						add(nameField);
						add(ageField);
						add(sexField);
						add(phoneField);
						add(addressField);
					}
				};
			
				add(leftLabelsPane, BorderLayout.WEST);
				add(midAreasPane, BorderLayout.CENTER);
			}
		};
		
		add(upPane);
		add(new JButton("Done") {
			{
				addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (nameField.getText().isBlank()) {
							nameField.setBackground(errBckg);
							return;
						}
						
						if ((int) ageField.getValue() < 18) {
							ageField.setValue(18);
							ageField.setBackground(Color.RED);
							return;
						}
						
						long phone = 0;
						if (phoneField.getText().isBlank()) {
							phoneField.setBackground(errBckg);
							return;
						} else {						
							try {phone = Long.parseLong(phoneField.getText());
							} catch (Exception e2) {
								phoneField.setBackground(errBckg);
								return;
							}
						}
						
						if (addressField.getText().isBlank()) {
							addressField.setBackground(errBckg);
							return;							
						}
						
						
						doc = new Doctor(nameField.getText(), (int) ageField.getValue(), addressField.getText(), phone, SEX.valueOf(sexField.getSelectedItem().toString()));
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
	
	public Doctor get() {return doc;}
}