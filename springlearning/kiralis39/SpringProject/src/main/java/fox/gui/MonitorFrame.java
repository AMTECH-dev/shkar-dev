package fox.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

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
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fox.Pet;
import fox.annotations.Spring;
import fox.clinics.PetClinic;
import fox.door.SpringEngine;


@SuppressWarnings("serial")
public class MonitorFrame<T> extends JFrame {
	private JScrollPane scrollPane;
	private JPanel midPane;
	private MonitorFrame monitor; 
	private JTextPane outputArea;

	private static JProgressBar healProgress;
	private static JLabel healedPets, failedPets;
	
	private SimpleAttributeSet filler = new SimpleAttributeSet();
	SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	
	
	public MonitorFrame() {
		tuneUI();
		
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
        				setOpaque(false);
        			    
    			        outputArea = new JTextPane() {
        					{
        						setBackground(Color.BLACK);
        						setForeground(Color.GREEN);
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

        						JPanel downLabelTextPane = new JPanel(new BorderLayout(3,3)) {
        							@Override
        							public void paint(Graphics g) {
        								super.paint(g);
        								
        								Graphics2D g2D = (Graphics2D) g;
        								
        								g2D.setFont(new Font("Arial Narrow", Font.BOLD, 16));
        								g2D.setColor(Color.BLACK);
        								g2D.drawString("Heal progress:", 19, 21);
        								g2D.setColor(Color.GREEN);
        								g2D.drawString("Heal progress:", 20, 20);
        							}
        							
									{
										setOpaque(false);
										setPreferredSize(new Dimension(110, 30));

										add(new JLabel("\u2665") {
												{
													setForeground(Color.RED);
													System.out.println(Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()));
													setFont(new Font("cl-unicode", Font.PLAIN, 24));
												}
											}, BorderLayout.WEST);
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
		outputAreaStyler();
    }

	public void outputAreaStyler() {
		new Thread(new Runnable() {			
			@Override
			public void run() {
				long was = System.currentTimeMillis();
				
				while (System.currentTimeMillis() - was < 3_000) {Thread.yield();}
				
				// Создание стилей
			    Style normal = outputArea.addStyle("normal", null);
		        StyleConstants.setFontFamily(normal, "Times New Roman"); 
		        StyleConstants.setFontSize(normal, 16);
		        StyleConstants.setForeground(normal, Color.GREEN);
		        
		        // Наследуем свойстdо FontFamily
		        Style heading = outputArea.addStyle("heading", normal);
		        StyleConstants.setFontSize(heading, 24);
		        StyleConstants.setBold(heading, true);
		        StyleConstants.setForeground(heading, Color.ORANGE);
				
//		        JCheckBox check = new JCheckBox("JCheckBox");
//		        check.setFont(new Font(FONT_style, Font.ITALIC, 16));
//		        check.setOpaque(false);
//		        outputArea.insertComponent(check);

//		        JRadioButton radio = new JRadioButton("JRadioButton");
//		        radio.setFont(new Font(FONT_style, Font.ITALIC, 16));
//		        radio.setOpaque(false);
//		        radio.setSelected(true);
//		        outputArea.insertComponent(radio);

		        StyleConstants.setForeground(filler, Color.YELLOW);
		        StyleConstants.setBackground(filler, Color.BLACK);
			}
		}).start();
	}

	private void tuneUI() {
		try {UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (Exception e) {
			try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e2) {
				try {UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
				} catch (Exception e3) {
					try {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					} catch (Exception e4) {System.out.println("Couldn't get specified look and feel, for some reason.");}
				}
			}
		}
		

		UIManager.getLookAndFeelDefaults().put("TextPane[Enabled].backgroundPainter", new Painter<T>() {
			@Override
			public void paint(Graphics2D g, T object, int width, int height) {
				g.setColor(Color.GREEN);
				g.fillRect(0, 0, 3, 3);
			}
		});
		
//		UIManager.getLookAndFeelDefaults().put("TextPane.background", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane.disabled", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane.disabledText", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane.foreground", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane.opaque", false);
//		UIManager.getLookAndFeelDefaults().put("TextPane[Disabled].backgroundPainter", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane[Disabled].textForeground", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane[Selected].backgroundPainter", Color.MAGENTA);
//		UIManager.getLookAndFeelDefaults().put("TextPane[Selected].textForeground", Color.MAGENTA);
		
//		UIManager.getLookAndFeelDefaults().put("TextPane.background", Color.RED);
		 
//		TextArea.background	#d6d9df (214,217,223)
//		TextArea.disabled	#d6d9df (214,217,223)	 
//		TextArea.disabledText	#8e8f91 (142,143,145)	  	 
//		TextArea.font	Font SansSerif 12	Font SansSerif 12 
//		TextArea.foreground	#000000 (0,0,0)	 	 
//		TextArea[Disabled].backgroundPainter	Painter	Painter
//		TextArea[Disabled].textForeground	#8e8f91 (142,143,145)
//		TextArea[Enabled].backgroundPainter	Painter	Painter
//		TextArea[Focused+NotInScrollPane].borderPainter	Painter	Painter
//		TextArea[Selected].backgroundPainter	Painter	Painter
//		TextArea[Selected].textForeground
//		
//		Panel.background	#d6d9df (214,217,223)
//		Panel.disabled	#d6d9df (214,217,223)	 
//		Panel.disabledText	#000000 (0,0,0)	 
//		Panel.font	Font SansSerif 12	Font SansSerif 12 
//		Panel.foreground	#000000 (0,0,0)
//		
//		EditorPane.background	#d6d9df (214,217,223)
//		EditorPane.disabled	#d6d9df (214,217,223)	 
//		EditorPane.disabledText	#8e8f91 (142,143,145)	 	 	 
//		EditorPane.font	Font SansSerif 12	Font SansSerif 12 
//		EditorPane.foreground	#000000 (0,0,0)	 	 
//		EditorPane[Disabled].backgroundPainter	Painter	Painter
//		EditorPane[Disabled].textForeground	#8e8f91 (142,143,145)	 
//		EditorPane[Enabled].backgroundPainter	Painter	Painter
//		EditorPane[Selected].backgroundPainter	Painter	Painter
//		EditorPane[Selected].textForeground	#f
//		 	 
//		ScrollPane.background	#d6d9df (214,217,223)
//		ScrollPane.disabled	#d6d9df (214,217,223)	 
//		ScrollPane.disabledText	#000000 (0,0,0)	 
//		ScrollPane.font	Font SansSerif 12	Font SansSerif 12 
//		ScrollPane.foreground	#000000 (0,0,0)	 
//		ScrollPane[Enabled+Focused].borderPainter	Painter	Painter
//		ScrollPane[Enabled].borderPainter
//		
//		Viewport.background	#d6d9df (214,217,223)
//		Viewport.disabled	#d6d9df (214,217,223)	 
//		Viewport.disabledText	#000000 (0,0,0)	 
//		Viewport.font	Font SansSerif 12	Font SansSerif 12 
//		Viewport.foreground	#000000 (0,0,0)
//		
//		ProgressBar.Indeterminate	Indeterminate 
//		ProgressBar.background	#d6d9df (214,217,223)
//		ProgressBar.disabled	#d6d9df (214,217,223)	 
//		ProgressBar.disabledText	#8e8f91 (142,143,145)	 
//		ProgressBar.font	Font SansSerif 12	Font SansSerif 12 
//		ProgressBar.foreground	#000000 (0,0,0)	 
//		ProgressBar.horizontalSize	Dimension (150,19)	Dimension (150,19)
//		ProgressBar.tileWhenIndeterminate	true//		
//		ProgressBar[Disabled+Finished].foregroundPainter	Painter	Painter
//		ProgressBar[Disabled+Indeterminate].foregroundPainter	Painter	Painter
//		ProgressBar[Disabled+Indeterminate].progressPadding	3	 
//		ProgressBar[Disabled].backgroundPainter	Painter	Painter
//		ProgressBar[Disabled].foregroundPainter	Painter	Painter
//		ProgressBar[Disabled].textForeground	#8e8f91 (142,143,145)	
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

	public void appendOut(String line) {
		outputArea.setText(outputArea.getText() + line);
//		StyledDocument doc = (StyledDocument)outputArea.getDocument();
//		Style style = doc.addStyle("StyleName", null);
//		StyleConstants.setBackground(style, Color.blue);
//		try {
//			doc.insertString(doc.getLength(), line, null);
//			doc.insertString(doc.getLength(), line, style);
//		} catch (BadLocationException e) {e.printStackTrace();}
		
//		outputArea.getStyledDocument().setCharacterAttributes(0, outputArea.getStyledDocument().getLength(), filler, true);
		outputArea.setCaretPosition(outputArea.getStyledDocument().getLength());
		
//		StyleConstants.setBackground(attributeSet, Color.MAGENTA);
//		outputArea.setCharacterAttributes(attributeSet, true);
	}

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
}