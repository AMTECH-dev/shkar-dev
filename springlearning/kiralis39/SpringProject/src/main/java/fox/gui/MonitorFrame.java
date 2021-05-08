package fox.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.*;

import fox.Pet;
import fox.clinics.PetClinic;
import fox.door.DataBase;
import fox.door.SpringEngine;


public class MonitorFrame extends JFrame {
	private JScrollPane scrollPane;
	private JPanel midPane, downLabelTextPane, leftClinicsPane;
	private JTextPane outputArea;

	private static JProgressBar healProgress;
	private static JLabel healedPets, failedPets;

	private String progressLabel = "Heal progress:";

	private Font progressLabelFont = new Font("Arial Narrow", Font.BOLD, 12);
	
	// GUI:
	public MonitorFrame() {
		tuneUI();
		
		setTitle("Monitor frame:");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new Dimension(750, 500));
        
        JPanel basePane = new JPanel(new BorderLayout(3,3)) {
        	{
        		setBorder(new EmptyBorder(0, 0, 0, 0));
        		setBackground(Color.DARK_GRAY);
        		
        		JPanel upInfoPane = new JPanel(new GridLayout(1, 3, 3, 3)) {
        			{
        				setOpaque(false);
        				setBorder(new EmptyBorder(0, 3, 0, 3));
        				
        				JPanel upHealedInfoPane = new JPanel(new BorderLayout(3,3)) {
        					{
        						setOpaque(false);
        						
        						healedPets = new JLabel("0") {
                					{
                						setForeground(Color.YELLOW);
                					}
                				};
                				
                				add(new JLabel("Pets healed:") {{setForeground(Color.YELLOW);}}, BorderLayout.WEST);
                				add(healedPets, BorderLayout.CENTER);
        					}
        				};
        				
        				JPanel upFailedInfoPane = new JPanel(new BorderLayout(3,3)) {
        					{
        						setOpaque(false);

                				failedPets = new JLabel("0") {
                					{
                						setForeground(Color.RED);
                					}
                				};
                				
                				add(new JLabel("Pets failed:") {{setForeground(Color.RED);}}, BorderLayout.WEST);
                				add(failedPets, BorderLayout.CENTER);
        					}
        				};
        				
        				add(upHealedInfoPane);
        				add(upFailedInfoPane);
        			}
        		};

        		leftClinicsPane = new JPanel(new GridLayout(0,1,3,3)) {
					{

					}
				};

        		midPane = new JPanel(new BorderLayout(3,3)) {
        			{
        				setBackground(Color.black);
        				setBorder(new EmptyBorder(1,3,1,3));
        				setOpaque(false);
        			    
    			        outputArea = new JTextPane() {
        					{
        						setBackground(Color.BLACK);
        						setEditable(false);
        					}
        				};
        				
        				scrollPane = new JScrollPane(outputArea) {
        					{
        						getViewport().setBackground(Color.BLACK);
        						setBackground(Color.BLACK);
        						setBorder(null);
        					}
        				};

						add(scrollPane, BorderLayout.CENTER);
        			}
        		};
        		
        		JPanel downButPane = new JPanel(new BorderLayout(3,3)) {
        			{
        				setBorder(new EmptyBorder(3, 3, 1, 3));
        				setBackground(Color.GRAY);

						JButton clinicBut = new JButton("ADD CLINIC") {
							{
								setFocusPainted(false);
								setBackground(Color.DARK_GRAY);
								setForeground(Color.WHITE);

								addActionListener(new ActionListener() {
									@Override public void actionPerformed(ActionEvent arg0) {
										healProgress.setIndeterminate(true);
										addNewClinic();
										healProgress.setIndeterminate(false);
									}
								});
							}
						};

        				JButton petBut = new JButton("ADD PET") {
        					{
        						setFocusPainted(false);
        						setBackground(Color.DARK_GRAY);
        						setForeground(Color.WHITE);
        						
        						addActionListener(new ActionListener() {									
									@Override public void actionPerformed(ActionEvent arg0) {
										healProgress.setIndeterminate(true);
										addNewPet(new PetCreator(MonitorFrame.this).get());
										healProgress.setIndeterminate(false);
									}
								});
        					}
        				};
        				
        				JButton exitBut = new JButton("EXIT") {
        					{
        						setFocusPainted(false);
        						setBackground(Color.DARK_GRAY);
        						setForeground(Color.WHITE);
        						
        						addActionListener(new ActionListener() {									
									@Override public void actionPerformed(ActionEvent arg0) {
										exitReq();
									}

									private void exitReq() {
										int req = JOptionPane.showConfirmDialog(MonitorFrame.this, "Завершить работу?", "Exit reqiest:", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
										if (req == 0) {
											endWorkEndExit(0);
										}
									}
								});
        					}
        				};
        				
        				JPanel healProgressPane = new JPanel(new BorderLayout(3,3)) {
        					{
        						setOpaque(false);
        						setBorder(new EmptyBorder(0, 2, 3, 2));
        						
        						healProgress = new JProgressBar(0, 100) {
        							{
        								setStringPainted(true);
        							}
        						};

        						downLabelTextPane = new JPanel(new BorderLayout(3,3)) {
        							@Override
        							public void paint(Graphics g) {
        								super.paint(g);
        								
        								Graphics2D g2D = (Graphics2D) g;

        								g2D.setFont(progressLabelFont);
        								g2D.setColor(Color.BLACK);
        								g2D.drawString(progressLabel, 22, 21);
        								g2D.setColor(Color.GREEN);
        								g2D.drawString(progressLabel, 23, 20);
        							}
        							
									{
										setOpaque(false);

										add(new JLabel("\u2665") {
												{
													setForeground(Color.RED);
													setFont(new Font("cl-unicode", Font.PLAIN, 24));
												}
											}, BorderLayout.WEST);
									}
								};

        						add(downLabelTextPane, BorderLayout.WEST);
        						add(healProgress, BorderLayout.CENTER);
        					}
        				};

        				add(clinicBut, BorderLayout.WEST);
        				add(petBut, BorderLayout.CENTER);
        				add(exitBut, BorderLayout.EAST);
        				add(healProgressPane, BorderLayout.SOUTH);
        			}
        		};
        		
        		add(upInfoPane, BorderLayout.NORTH);
				add(leftClinicsPane, BorderLayout.WEST);
        		add(midPane, BorderLayout.CENTER);
        		add(downButPane, BorderLayout.SOUTH);
        	}
        };
        
        add(basePane);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

		launchMonitorConsoleThread();

		downLabelTextPane.setPreferredSize(new Dimension((int) downLabelTextPane.getGraphics().getFontMetrics(progressLabelFont).getStringBounds(progressLabel, downLabelTextPane.getGraphics()).getWidth() + 20, 30));
		downLabelTextPane.revalidate();

		updateData();
	}

	private void updateData() {
		List<PetClinic> clinics = DataBase.getClinics();
		for(PetClinic pc : clinics) {
			System.out.println("Finded the clinic '" + pc.getName() + "' into DB.");
			leftClinicsPane.add(new JLabel(pc.getName()));
		}
	}

	private void tuneUI() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().put("TextPane[Enabled].backgroundPainter", new Painter<Object>() {

				@Override
				public void paint(Graphics2D g, Object object, int width, int height) {
					g.setColor(Color.GREEN);
					g.fillRect(0, 0, 3, 3);
				}
			});
		} catch (Exception e) {
			try {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (Exception e2) {
				try {UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
				} catch (Exception e3) {
					try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch (Exception e4) {System.out.println("Couldn't get specified look and feel, for some reason.");}
				}
			}
		}
	}

	// OUT THREAD:
	private void launchMonitorConsoleThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try (
						PipedOutputStream pOut = new PipedOutputStream();
						PipedInputStream pIn = new PipedInputStream(pOut);
						BufferedReader reader = new BufferedReader(new InputStreamReader(pIn))
				) {
					System.setOut(new PrintStream(pOut));

					String nextLine;
					while(MonitorFrame.this.isVisible()) {

						if (reader.ready() && (nextLine = reader.readLine()) != null) {
							appendOut(nextLine);

							while (reader.ready() && (nextLine = reader.readLine()) != null) {
								appendOut("\r\n");
								appendOut(nextLine);
								Thread.sleep(120); // animation
							}
							appendOut("\r\n\r\n");
						}
					}

					reader.close();
				} catch (Exception e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}

			}
		}) {{setDaemon(true);}}.start();
	}

	public void appendOut(String line) {
		StyledDocument doc = (StyledDocument) outputArea.getDocument();

		Style normal = outputArea.addStyle("normal", null);
		StyleConstants.setFontFamily(normal, "Dialog");
		StyleConstants.setFontSize(normal, 12);
		StyleConstants.setForeground(normal, Color.WHITE);
		StyleConstants.setBold(normal, false);

		Style red = outputArea.addStyle("red", normal);
		StyleConstants.setFontSize(red, 14);
		StyleConstants.setForeground(red, Color.RED);
		StyleConstants.setBold(red, true);

		Style green = outputArea.addStyle("green", null);
		StyleConstants.setFontFamily(normal, "Arial Narrow");
		StyleConstants.setFontSize(green, 14);
		StyleConstants.setForeground(green, Color.GREEN);
		StyleConstants.setBold(green, true);

		Style cyan = outputArea.addStyle("cyan", null);
		StyleConstants.setFontSize(cyan, 12);
		StyleConstants.setForeground(cyan, Color.CYAN);
		StyleConstants.setBold(cyan, true);

		Style orange = outputArea.addStyle("orange", null);
		StyleConstants.setFontSize(orange, 12);
		StyleConstants.setForeground(orange, Color.ORANGE);
		StyleConstants.setBold(orange, false);

		try {
			if (line.contains("=^_^=")) {
				doc.insertString(doc.getLength(), line, green);
			} else if (line.contains("created new")) {
				doc.insertString(doc.getLength(), line, cyan);
			} else if (line.contains("pet income")) {
				doc.insertString(doc.getLength(), line, orange);
			} else {
				doc.insertString(doc.getLength(), line, normal);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		outputArea.setCaretPosition(outputArea.getStyledDocument().getLength());


//		SimpleAttributeSet filler = new SimpleAttributeSet();
//		StyleConstants.setForeground(filler, Color.YELLOW);
//		StyleConstants.setBackground(filler, Color.BLACK);
//		outputArea.getStyledDocument().setCharacterAttributes(0, outputArea.getStyledDocument().getLength(), filler, true);

//		StyleConstants.setBackground(attributeSet, Color.MAGENTA);
//		outputArea.setCharacterAttributes(attributeSet, true);


//		JCheckBox check = new JCheckBox("JCheckBox");
//		check.setFont(new Font(FONT_style, Font.ITALIC, 16));
//		check.setOpaque(false);
//		outputArea.insertComponent(check);

//		JRadioButton radio = new JRadioButton("JRadioButton");
//		radio.setFont(new Font(FONT_style, Font.ITALIC, 16));
//		radio.setOpaque(false);
//		radio.setSelected(true);
//		outputArea.insertComponent(radio);
	}

	// DOWN PROGRESS BAR:
	public static void setHealProgressValue(int value, String label) {
		healProgress.setValue(value);
		setProgressbarText("Healed " + label + " (" + healProgress.getValue() + "%)");
	}
	
	public static void setProgressbarText(String label) {
		healProgress.setString(label);
	}

	public static void addHealedPetsCollection() {
		healedPets.setText(String.valueOf(Integer.parseInt(healedPets.getText()) + 1));
	}
	
	public static void addFailedPetsCollection() {
		failedPets.setText(String.valueOf(Integer.parseInt(failedPets.getText()) + 1));
	}

	// FRAME LOGIC:
	private void addNewClinic() {
		String name = JOptionPane.showInputDialog(MonitorFrame.this, "Название клиники:");
		PetClinic clinic = DataBase.addClinic(new PetClinic(name, null, 2990345L, null, null, "my comment"));

		if (clinic != null) {
			System.out.println("Was created new clinic '" + clinic.getName() + "'.");
		}
	}

	private void addNewPet(Pet pet) {
		if (pet == null) {
			healProgress.setValue(100);
			System.out.println("We have a ghost? Its a revenge!!!");
			return;
		}
		System.out.println("New pet income:\n" + pet.toString());
		SpringEngine.getContext().getBean(PetClinic.class).work(pet);
	}

	// EXIT:
	private void endWorkEndExit(int errCode) {
//		DataBase.clearDB();
		healProgress.setIndeterminate(true);
		healProgress.setString("Closing the Clinics... wait please...");
		SpringEngine.getContext().close();
		System.out.println("Clinic system is shutting down with code #" + errCode);
		System.exit(errCode);
	}
}