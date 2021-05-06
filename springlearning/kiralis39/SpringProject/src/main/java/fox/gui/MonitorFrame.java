package fox.gui;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import fox.door.SpringEngine;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fox.Pet;
import fox.annotations.Spring;
import fox.clinics.PetClinic;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;


@SuppressWarnings("serial")
public class MonitorFrame extends JFrame {
	private JScrollPane scrollPane;
	private JPanel midPane;
	private MonitorFrame monitor; 
	private JTextArea outputArea;

	private static JProgressBar healProgress;
	private static JLabel healedPets, failedPets;
	
	
	public MonitorFrame() {
		monitor = this;
		
        setTitle("Monitor frame:");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        setPreferredSize(new Dimension(550, 500));

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
        		
        		midPane = new JPanel(new BorderLayout(3,3)) {
        			{
        				setBackground(Color.black);
        				setBorder(new EmptyBorder(1,3,1,3));

        				outputArea = new JTextArea() {
        					{
        						setWrapStyleWord(true);
        						setLineWrap(true);
        						setBackground(Color.BLACK);
        						setForeground(Color.GREEN);
        						setEditable(false);
        					}
        				};
        				
        				scrollPane = new JScrollPane(outputArea) {
        					{
        						getViewport().setBackground(Color.BLACK);
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
        						
        						healProgress = new JProgressBar(0, 100);

        						JPanel downLabelTextPane = new JPanel(new BorderLayout(3,3)) {
									{
										setOpaque(false);

										add(new JLabel("\uD83E") {
												{
													setForeground(Color.RED);
													System.out.println(Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()));
													setFont(new Font("cl-unicode", Font.PLAIN, 24));
												}
											}, BorderLayout.WEST);
										add(new JLabel("Heal progress:") {{setForeground(Color.GREEN);}}, BorderLayout.CENTER);
									}
								};

        						add(downLabelTextPane, BorderLayout.WEST);
        						add(healProgress, BorderLayout.CENTER);
        					}
        				};
        				
        				add(petBut, BorderLayout.CENTER);
        				add(exitBut, BorderLayout.EAST);
        				add(healProgressPane, BorderLayout.SOUTH);
        			}
        		};
        		
        		add(upInfoPane, BorderLayout.NORTH);
        		add(midPane, BorderLayout.CENTER);
        		add(downButPane, BorderLayout.SOUTH);
        	}
        };
        
        add(basePane);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

		monitor.revalidate();
		launchMonitorConsoleThread();
    }

	@Spring
    private void addNewPet(Pet pet) {
    	if (pet == null) {
    		healProgress.setValue(100);
			System.out.println("We have a ghost? Its a revenge!!!");
			return;
		}

		SpringEngine.getContext().getBean(PetClinic.class).work(pet);
	}

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

    private void endWorkEndExit(int errCode) {
    	System.out.println("Clinic system is shutting down with code #" + errCode);
		SpringEngine.closeContext();
		System.exit(errCode);
	}

	public void appendOut(String line) {outputArea.append(line);}

	public static void setHealProgressValue(int value) {
		healProgress.setValue(value);
	}

	public static void addHealedPetsCollection() {
		healedPets.setText(String.valueOf(Integer.parseInt(healedPets.getText()) + 1));
	}
	
	public static void addFailedPetsCollection() {
		failedPets.setText(String.valueOf(Integer.parseInt(failedPets.getText()) + 1));
	}
}