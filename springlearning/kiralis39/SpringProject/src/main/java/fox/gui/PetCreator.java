package fox.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import fox.Pet;
import fox.Pet.SEX;
import fox.door.SpringEngine;
import fox.pets.Cat;
import fox.pets.Dog;
import fox.pets.Fox;


@SuppressWarnings("serial")
public class PetCreator extends JDialog {
	private Pet pet;
	
	
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
								String petName = getSelectedValue();								
								if (petName != null && !petName.equals("")) {
									// возможно, switch стоит заменить на рефлекшн по пакету pets для удобного расширения пакетов без изменения кода ниже.
									PetCreator.this.setVisible(false);
									switch (petName) {
										case "Cat": 
											pet = customizePet(SpringEngine.getContext().getBean(Cat.class));
											break;
											
										case "Dog":	
											pet = customizePet(SpringEngine.getContext().getBean(Dog.class));
											break;
											
										case "Fox":	
											pet = customizePet(SpringEngine.getContext().getBean(Fox.class));
											break;
											
										default: System.out.println("Has been chousen: " + petName);
									}
									
									PetCreator.this.dispose();
								}
							}
						}

						JTextField nameField;
						JSpinner ageSpinner, hpSpinner;
						JComboBox<SEX> sexCBox;
						JTextField colorField;
						Dimension labelDim = new Dimension(60, 30);
						private Pet customizePet(Pet pet) {
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
											nameField = new JTextField(pet.getName());
											
											add(new JLabel("Name:") {{setPreferredSize(labelDim);}}, BorderLayout.WEST);
											add(nameField, BorderLayout.CENTER);
										}
									};
									JPanel petAgePane = new JPanel(new GridLayout(1, 2, 6, 6)) {
										{
											SpinnerNumberModel model = new SpinnerNumberModel(0f, null, null, .1f); 
											ageSpinner = new JSpinner(model);
											ageSpinner.setValue(pet.getAge());
											
											hpSpinner = new JSpinner();
											hpSpinner.setValue(pet.getHP());
											
											add(new JLabel("Age:") {{setPreferredSize(labelDim);}});
											add(ageSpinner);
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
									JPanel buttonsPane = new JPanel(new BorderLayout(3,3)) {
										{
											JButton abortBtn = new JButton("Cancel") {
												{
													addActionListener(new ActionListener() {														
														@Override
														public void actionPerformed(ActionEvent arg0) {
															nameField.setText(null);
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
															pet.setName(nameField.getText());
															try {
																float age = Float.parseFloat(ageSpinner.getValue().toString().replaceAll(",", "."));
																pet.setAge(age);
															} catch (Exception e) {e.printStackTrace(); pet.setAge(0);}
															try {
																int hp = (int) hpSpinner.getValue();
																pet.setHP(hp);
															} catch (Exception e) {e.printStackTrace(); pet.setHP(50);}
															pet.setSex((SEX) sexCBox.getSelectedItem());
															pet.setColor(colorField.getText());
															pet.setHealed(false);
															
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
							
							if (nameField.getText().isBlank()) {return null;}
							return pet;
						}
					});
				}
			}
		};
		JScrollPane scroll = new JScrollPane(petsList);
		
		listModel.add(listModel.getSize(), Cat.class.getSimpleName());
		listModel.add(listModel.getSize(), Dog.class.getSimpleName());
		listModel.add(listModel.getSize(), Fox.class.getSimpleName());
		
		add(scroll);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public Pet get() {
		if (pet != null) {
			System.out.println("The clinic has new pet: " + pet.toString());
		}
		
		return pet;
	}
}