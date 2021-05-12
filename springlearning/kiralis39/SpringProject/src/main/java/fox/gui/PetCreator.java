package fox.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import fox.door.Hibernate;
import fox.entities.Owner;
import fox.entities.Pet;
import fox.data.SEX;
import fox.spring.SpringEngine;


public class PetCreator extends JDialog {
	private Pet pet;
	private Owner owner;
	
	public PetCreator(JFrame parent) {
		super(parent, "Новое животное:", true);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(300, 300));
		getContentPane().setLayout(new BorderLayout(3, 3));
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> petsList = new JList<>(listModel) {
			{
				{
					addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if (e.getClickCount() >=2) {
								String petTypeName = getSelectedValue();								
								if (petTypeName != null && !petTypeName.equals("")) {
									
									PetCreator.this.setVisible(false);
									pet = customizePet(petTypeName);									
									Hibernate.writePet(pet);
									
									PetCreator.this.dispose();
								}
							}
						}
					});
				}
			}
		};
		JScrollPane scroll = new JScrollPane(petsList);
		
		listModel.add(listModel.getSize(), "Cat");
		listModel.add(listModel.getSize(), "Dog");
		listModel.add(listModel.getSize(), "Fox");
		listModel.add(listModel.getSize(), "Seal");
		listModel.add(listModel.getSize(), "Beaver");
		
		add(scroll);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public Pet get() {return pet;}


	private JTextField petNameField, ownerNameField, addressField, phoneField, colorField;
	private JSpinner petAgeSpinner, ownerAgeField, hpSpinner;
	private JTextArea commentArea;
	private JComboBox<SEX> sexCBox;
	private JComboBox<Owner> ownerName;
	private long phone = 0L;
	
	private Dimension labelDim = new Dimension(60, 30);
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Pet customizePet(String petTypeName) {
		Pet pet = new Pet();
		
		new JDialog() {
			{
				setTitle("Pet sets");
				setModal(true);
				setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
				setMinimumSize(new Dimension(300, 100));
				setLayout(new GridLayout(0, 1, 3, 3));
				getRootPane().setBorder(new EmptyBorder(3, 6, 3, 6));
				
				JPanel petNamePane = new JPanel(new BorderLayout(3,3)) {
					{
						petNameField = new JTextField(pet.getName());
						
						add(new JLabel("Name:") {{setPreferredSize(labelDim);}}, BorderLayout.WEST);
						add(petNameField, BorderLayout.CENTER);
					}
				};
				JPanel petAgePane = new JPanel(new GridLayout(1, 2, 6, 6)) {
					{
						SpinnerNumberModel model = new SpinnerNumberModel(0f, null, null, .1f); 
						petAgeSpinner = new JSpinner(model);
						petAgeSpinner.setValue(pet.getAge());
						
						hpSpinner = new JSpinner();
						hpSpinner.setValue(pet.getHP());
						
						add(new JLabel("Age:") {{setPreferredSize(labelDim);}});
						add(petAgeSpinner);
						add(new JLabel("HP:") {{setPreferredSize(labelDim);}});
						add(hpSpinner);
					}
				};
				JPanel petSexPane = new JPanel(new BorderLayout(3,3)) {
					{
						sexCBox = new JComboBox<SEX>(SEX.values());
						sexCBox.setSelectedItem(pet.getSex());
						
						add(new JLabel("Sex:") {{setPreferredSize(labelDim);}}, BorderLayout.WEST);
						add(sexCBox, BorderLayout.CENTER);
					}
				};
				JPanel petColorPane = new JPanel(new BorderLayout(3,3)) {
					{
						colorField = new JTextField(pet.getColor());
						
						add(new JLabel("Color:") {{setPreferredSize(labelDim);}}, BorderLayout.WEST);
						add(colorField, BorderLayout.CENTER);
					}
				};

				JPanel ownerPane = new JPanel(new BorderLayout(3,3)) {
					{
						ownerName = new JComboBox(Hibernate.getOwnersList().toArray());
						JButton createOwnerBtn = new JButton("+") {
							{
								addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										owner = createNewOwner();
										if (owner != null && owner.addPet(pet)) {
											pet.setOwner(owner);
											ownerName.addItem(owner);
										}
									}
								});
							}
						};

						add(new JLabel("Owner:") {{setPreferredSize(labelDim);}}, BorderLayout.WEST);
						add(ownerName, BorderLayout.CENTER);
						add(createOwnerBtn, BorderLayout.EAST);
					}
				};

				JPanel buttonsPane = new JPanel(new BorderLayout(3,3)) {
					{
						JButton abortBtn = new JButton("Cancel") {
							{
								addActionListener(new ActionListener() {														
									@Override
									public void actionPerformed(ActionEvent arg0) {
										petNameField.setText(null);
										dispose();
									}
								});
							}
						};
						JButton okBtn = new JButton("Ok") {
							{
								addActionListener(new ActionListener() {														
									@Override
									public void actionPerformed(ActionEvent arg0) {
										pet.setName(petNameField.getText());
										try {
											float age = Float.parseFloat(petAgeSpinner.getValue().toString().replaceAll(",", "."));
											pet.setAge(age);
										} catch (Exception e) {e.printStackTrace(); pet.setAge(0);}
										try {
											int hp = (int) hpSpinner.getValue();
											pet.setHP(hp);
										} catch (Exception e) {e.printStackTrace(); pet.setHP(50);}
										pet.setSex((SEX) sexCBox.getSelectedItem());
										pet.setColor(colorField.getText());
										pet.setOwner(owner == null ? (Owner) ownerName.getSelectedItem() : owner);
//										pet.setHealed(false);
										
										dispose();
									}
								});
							}
						};
						
						add(abortBtn, BorderLayout.WEST);
						add(okBtn, BorderLayout.CENTER);
					}
				};
				
				add(petNamePane);
				add(petAgePane);
				add(petSexPane);
				add(petColorPane);
				add(ownerPane);
				add(buttonsPane);
				
				addWindowListener(new WindowAdapter() {
					@Override public void windowClosing(WindowEvent arg0) {
						dispose();
					}
				});
				
				pack();
				setLocationRelativeTo(null);
				setVisible(true);
			}
		};
		
		if (petNameField.getText().isBlank()) {return null;}
		
//		Pet pet = new Pet(petNameField.getText(), (float) hpSpinner.getValue(), (SEX) sexCBox.getSelectedItem(), colorField.getText(), petTypeName, (Owner) ownerName.getSelectedItem());
		
		return pet;
	}
		
	public Owner createNewOwner() {
		Owner newOwner = null;

		try {
			new JDialog() {
				{
					setModal(true);
					setTitle("Создание хозяина:");
					setMinimumSize(new Dimension(400, 500));
					
					JPanel basePane = new JPanel(new GridLayout(0,1,3,3)) {
						{
							
							JPanel namePane = new JPanel(new BorderLayout(3,3)) {
								{
									
									ownerNameField = new JTextField();
								
									add(new JLabel("Name:") {{}}, BorderLayout.WEST);
									add(ownerNameField, BorderLayout.CENTER);
								}
							};
							
							JPanel agePane = new JPanel(new BorderLayout(3,3)) {
								{
									
									ownerAgeField = new JSpinner();
								
									add(new JLabel("Age:") {{}}, BorderLayout.WEST);
									add(ownerAgeField, BorderLayout.CENTER);
								}
							};
							
							JPanel addressPane = new JPanel(new BorderLayout(3,3)) {
								{
									
									addressField = new JTextField();
								
									add(new JLabel("Address:") {{}}, BorderLayout.WEST);
									add(addressField, BorderLayout.CENTER);
								}
							};
							
							JPanel phonePane = new JPanel(new BorderLayout(3,3)) {
								{
									
									phoneField = new JTextField() {
										{
											addKeyListener(new KeyAdapter() {
												@Override
												public void keyReleased(KeyEvent arg0) {
													try {phone = Long.parseLong(getText());
													} catch (Exception e) {
														setBackground(Color.RED);
													}
												}
											});
										}
									};
								
									add(new JLabel("Phone:") {{}}, BorderLayout.WEST);
									add(phoneField, BorderLayout.CENTER);
								}
							};
							
							JPanel sexPane = new JPanel(new BorderLayout(3,3)) {
								{
									sexCBox = new JComboBox<SEX>(SEX.values());
									
									add(new JLabel("Sex:") {{}}, BorderLayout.WEST);
									add(sexCBox, BorderLayout.CENTER);
								}
							};
							
							JPanel commentPane = new JPanel(new BorderLayout(3,3)) {
								{
									commentArea = new JTextArea() {
										{
											setLineWrap(true);
											setWrapStyleWord(true);
										}
									};
									
									add(new JLabel("Comment:") {{}}, BorderLayout.WEST);
									add(commentArea, BorderLayout.CENTER);
								}
							};
							
							add(namePane);
							add(agePane);
							add(addressPane);
							add(phonePane);
							add(sexPane);
							add(commentPane);
						}
					};
					
					add(basePane);
					
					pack();
					setLocationRelativeTo(null);
					setVisible(true);
				}
			};
			
			newOwner = new Owner(ownerNameField.getText(), (int) ownerAgeField.getValue(), addressField.getText(), phone, (SEX) sexCBox.getSelectedItem(), commentArea.getText());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return newOwner;
	}
}