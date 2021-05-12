package fox.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fox.door.Hibernate;
import fox.entities.Doctor;
import fox.entities.PetClinic;
import fox.gui.swing.VerticalFlowLayout;


public class ClinicControlDialog extends JDialog {
	private static JPanel doctorsPane;
	private static JScrollPane docsScroll;
	private static PetClinic clinic;
	
	public ClinicControlDialog(PetClinic clinic) {
		this.clinic = clinic;

		setTitle("Контроль клиники:");
		setModal(false);
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(450, 600));
		
		JPanel basePane = new JPanel(new BorderLayout(3, 3)) {
			{
				setBackground(Color.DARK_GRAY);

				doctorsPane = new JPanel(new VerticalFlowLayout(0,3,3)) {
					{
						setBackground(Color.DARK_GRAY);

						
					}
				};
				
				Doctor[] doctors = clinic.getListOfDoctors();
				for (Doctor doctor : doctors) {
					doctorsPane.add(new DoctorLine(doctor));
				}
				
				docsScroll = new JScrollPane(doctorsPane);
				
				add(docsScroll, BorderLayout.CENTER);
			}
		};
		
		add(basePane);
		
		addComponentListener(new ComponentAdapter() {
			@Override public void componentResized(ComponentEvent arg0) {updateList();}
		});
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		updateList();
	}
		
	private static void updateList() {
		for (Component c : doctorsPane.getComponents()) {
			if (c instanceof DoctorLine) {
				((DoctorLine) c).setPreferredSize(new Dimension(docsScroll.getWidth() - 28, ((DoctorLine) c).getHeight()));
			}
		}
	}

	public static class DoctorLine extends JPanel {
		
		public DoctorLine(Doctor doctor) {
			setLayout(new BorderLayout(3,3));
			setBorder(new EmptyBorder(1, 3, 1, 3));
			
			String header = "<html><b><h4 color=black>" + doctor.getName() + "<hr>";
			add(new JLabel(header) {{setHorizontalAlignment(JLabel.LEFT);}}, BorderLayout.NORTH);
			
			String body = "<html><b>Age: </b>" + doctor.getAge() + "; <b>Sex: </b>" + doctor.getSex() + 
					"; <b>Phone: </b>" + doctor.getPhone() + ";<br><b>Address: </b>" + doctor.getAddress();
			add(new JLabel(body), BorderLayout.CENTER);
			
			add(new JPanel(new BorderLayout()) {
				{
					add(new JPanel(new GridLayout(0,1,6,6)) {
						{
							add(new JButton("C") {{setBackground(Color.YELLOW); setToolTipText("Change this doctor");}});
							add(new JButton("D") {
								{
									setBackground(Color.RED);
									setToolTipText("Delete this doctor");

									addActionListener(new ActionListener() {
										@Override
										public void actionPerformed(ActionEvent e) {
											int req = JOptionPane.showConfirmDialog(
													DoctorLine.this, "Удалить врача?", "Delete reqiest:",
													JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
											if (req == 0) {
												Hibernate.deleteDoctor(clinic, doctor);
												doctorsPane.remove(DoctorLine.this);
												updateList();
											}
										}
									});
								}
							});
						}
					});
					add(new JSeparator(1), BorderLayout.WEST);
				}
			}, BorderLayout.EAST);
			
			add(new JSeparator(0), BorderLayout.SOUTH);
		}
	}
}