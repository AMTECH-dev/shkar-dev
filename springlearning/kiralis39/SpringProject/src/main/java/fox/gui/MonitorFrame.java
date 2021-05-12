package fox.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Painter;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fox.door.Hibernate;
import fox.entities.Doctor;
import fox.entities.Pet;
import fox.entities.PetClinic;
import fox.entities.clinicData.Photodir;
import fox.gui.swing.VerticalFlowLayout;
import fox.spring.SpringEngine;
import fox.tools.IOM;
import fox.tools.IOMs;


public class MonitorFrame extends JFrame implements ActionListener {
	private JScrollPane scrollPane;
	private JPanel downLabelTextPane, leftClinicsPane, outPane;
	private JTextPane outputArea;
	private JTabbedPane midPane;
	private JButton doctorBut, petBut;
	
	private static JProgressBar healProgress;
	private static JLabel clinicsLabel, doctorsLabel, healedPets, failedPets;

	private String progressLabel = "Heal progress:";

	private double leftPaneWidthPercent = 0.18D;
	
	private int defaultHeaderFontSize = 14;
	
	private Font progressLabelFont = new Font("Arial Narrow", Font.BOLD, defaultHeaderFontSize);
	private Font uniFont = new Font("cl-unicode", Font.PLAIN, 24);
	private Font linksFont = new Font("cl-unicode", Font.PLAIN, 14);
	private Font littleFont = new Font("cl-unicode", Font.PLAIN, 10);
	private Font headerFont = new Font("Arial", Font.BOLD, 20);
	
	private Style normal, red, green, cyan, orange;
	
	private Dimension minFrameDim = new Dimension(800, 500);
	
	private ImageIcon consoleIcon = new ImageIcon("./media/icons/console.png");
	private ImageIcon clinicIcon = new ImageIcon("./media/icons/clinic.png");
	
	private Border plateBorder = BorderFactory.createCompoundBorder(
			BorderFactory.createSoftBevelBorder(0, new Color(0.25f, 0.25f, 0.25f, 1.0f), new Color(0.65f, 0.65f, 0.65f, 1.0f)),
			new EmptyBorder(0, 0, 0, 0));
	
	
	// GUI:
	public MonitorFrame() {
		presetUI();
		
		setIconImage(clinicIcon.getImage());
		setTitle(IOM.get(IOMs.GLOBAL.PROGRAMM_NAME) + " v." + IOM.get(IOMs.GLOBAL.PROGRAMM_VERSE));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(minFrameDim);
        setPreferredSize(new Dimension(820, 540));
        
        JPanel basePane = new JPanel(new BorderLayout(3,0)) {
        	@Override
        	public void paint(Graphics g) {
        		render((Graphics2D) g);
        		super.paint(g);
        	}
        	
        	{
        		setBorder(new EmptyBorder(0, 0, 0, 0));
        		setBackground(Color.gray);

        		leftClinicsPane = new JPanel(new BorderLayout(3,3)) {
					{
//						setOpaque(false);
						setBackground(Color.GRAY);
						setBorder(new EmptyBorder(3, 3, 0, 3));
						
						JPanel leftInfopane = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 3, 3)) {
							{
								setOpaque(false);
								
								JPanel clinicsLabelpane = new JPanel(new BorderLayout(3,3)) {
		        					{
		        						setOpaque(false);
		        						setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 20));
		        						
		        						clinicsLabel = new JLabel("0") {
		                					{
		                						setForeground(Color.WHITE);
		                					}
		                				};
		                				
		                				add(new JLabel("Clinics:") {{setForeground(Color.WHITE);}}, BorderLayout.WEST);
		                				add(clinicsLabel, BorderLayout.CENTER);
		        					}
		        				};
		        				
		        				JPanel doctorsLabelpane = new JPanel(new BorderLayout(3,3)) {
		        					{
		        						setOpaque(false);
		        						setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 20));
		        						
		        						doctorsLabel = new JLabel("0") {
		                					{
		                						setForeground(Color.YELLOW);
		                					}
		                				};
		                				
		                				add(new JLabel("Doctors:") {{setForeground(Color.YELLOW);}}, BorderLayout.WEST);
		                				add(doctorsLabel, BorderLayout.CENTER);
		        					}
		        				};
								
								JPanel upHealedInfoPane = new JPanel(new BorderLayout(3,3)) {
		        					{
		        						setOpaque(false);
		        						setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 20));
		        						
		        						healedPets = new JLabel("0") {
		                					{
		                						setForeground(Color.GREEN);
		                					}
		                				};
		                				
		                				add(new JLabel("Pets healed:") {{setForeground(Color.GREEN);}}, BorderLayout.WEST);
		                				add(healedPets, BorderLayout.CENTER);
		        					}
		        				};
		        				
		        				JPanel upFailedInfoPane = new JPanel(new BorderLayout(3,3)) {
		        					{
		        						setOpaque(false);
		        						setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 20));

		                				failedPets = new JLabel("0") {
		                					{
		                						setForeground(Color.RED);
		                					}
		                				};
		                				
		                				add(new JLabel("Heal failed:") {{setForeground(Color.RED);}}, BorderLayout.WEST);
		                				add(failedPets, BorderLayout.CENTER);
		        					}
		        				};
		        				
		        				add(clinicsLabelpane);
		        				add(doctorsLabelpane);
		        				add(upHealedInfoPane);
		        				add(upFailedInfoPane);
							}
						};
						
						JPanel leftButtonsPane = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.TOP, 3, 3)) {
							{
								setOpaque(false);
								
								JButton clinicBut = new JButton("ADD CLINIC") {
									{
										setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 25));
										setFocusPainted(false);
										setBackground(Color.DARK_GRAY);
										setForeground(Color.WHITE);
										setActionCommand("addClinic");
										addActionListener(MonitorFrame.this);
									}
								};
								
								doctorBut = new JButton("ADD DOCTOR") {
									{
										setEnabled(false);
										setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 25));
										setFocusPainted(false);
										setBackground(Color.DARK_GRAY);
										setForeground(Color.WHITE);
										setActionCommand("addDoctor");
										addActionListener(MonitorFrame.this);
									}
								};
								
								petBut = new JButton("ADD PET") {
		        					{
		        						setEnabled(false);
		        						setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 25));
		        						setFocusPainted(false);
		        						setBackground(Color.DARK_GRAY);
		        						setForeground(Color.WHITE);
		        						setActionCommand("addPet");
		        						addActionListener(MonitorFrame.this);
		        					}
		        				};
								
								add(clinicBut);
								add(doctorBut);
								add(petBut);
							}
						};
						
						add(leftInfopane, BorderLayout.CENTER);
						add(leftButtonsPane, BorderLayout.SOUTH);
					}
				};

        		midPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT) {
					{
						setFont(progressLabelFont);
						setBorder(plateBorder);

        				outPane = new JPanel(new BorderLayout()) {
        					{
        						setBackground(Color.BLACK);
        						
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
        				
    			        
        				addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								updateLeftButtons();			
							}							
						});
        				
        				addTab("Console  ", consoleIcon, outPane, "System out console");
        			}
        		};
        		
        		JPanel downPane = new JPanel(new BorderLayout(3,3)) {
        			{
        				setBorder(new EmptyBorder(3, 3, 1, 3));
        				setBackground(Color.GRAY);
        				
        				JPanel healProgressPane = new JPanel(new BorderLayout(3,3)) {
        					{
        						setOpaque(false);
        						setBorder(new EmptyBorder(0, 6, 3, 2));
        						
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
//        								render(g2D);
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
													setFont(uniFont);
												}
											}, BorderLayout.WEST);
									}
								};

        						add(downLabelTextPane, BorderLayout.WEST);
        						add(healProgress, BorderLayout.CENTER);
        					}
        				};
        				
        				JButton exitBut = new JButton("EXIT") {
        					{
        						setFocusPainted(false);
        						setBackground(Color.DARK_GRAY);
        						setForeground(Color.WHITE);
        						setActionCommand("exit");
        						addActionListener(MonitorFrame.this);
        					}
        				};

        				add(healProgressPane, BorderLayout.CENTER);
        				add(exitBut, BorderLayout.EAST);
        			}
        		};
        		
				add(leftClinicsPane, BorderLayout.WEST);
        		add(midPane, BorderLayout.CENTER);
        		add(downPane, BorderLayout.SOUTH);
        	}
        };
        
        postsetUI();
        
        add(basePane);
        
        addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) {
				exitReq();
			}
		});
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        render((Graphics2D) outputArea.getGraphics());
        
        outPane.add(new JPanel(new GridLayout(outPane.getHeight() / 32, 1, 0, 0)) {
			{
				setOpaque(false);
				
				add(new JButton("C") {
					{
						setBorder(null);
						setFont(littleFont);
						setPreferredSize(new Dimension(32, 32));
						setFocusPainted(false);
						setBackground(Color.YELLOW);
						setForeground(Color.BLACK);
						setActionCommand("clear");
						addActionListener(MonitorFrame.this);
					}
				});
				
				
			}
		}, BorderLayout.WEST);
        
		launchMonitorConsoleThread();

		downLabelTextPane.setPreferredSize(new Dimension((int) (minFrameDim.getWidth() * leftPaneWidthPercent), 28));
		downLabelTextPane.revalidate();

		reloadGame();
		
		midPane.setBackgroundAt(0, Color.BLACK);
	}
	
	private void updateLeftButtons() {
		petBut.setEnabled(false);
		doctorBut.setEnabled(false);
		
		if (midPane.getSelectedComponent() instanceof ClinicPanel) {
			PetClinic tmp = ((ClinicPanel) midPane.getSelectedComponent()).getClinic();
			System.out.println(tmp.getName());
			int doctorsCount = tmp.getDoctors().size();
			petBut.setEnabled(doctorsCount > 0 && tmp.isOpen());
			doctorBut.setEnabled(midPane.getSelectedIndex() > 0 && tmp.isOpen());
			((ClinicPanel) midPane.getSelectedComponent()).showControlBtn(doctorsCount > 0 && tmp.isOpen());
			
			System.out.println("Clinic " + tmp + " has " + tmp.getListOfDoctors().length + " doctors.");
		}
	}
	
	private void reloadGame() {
		int tc = midPane.getTabCount();
		for (int i = tc - 1; i > 0; i--) {
			System.out.println("Delete tab bu index " + i + "/" + tc);
			midPane.removeTabAt(i);
		}
		
		List<PetClinic> clinics = Hibernate.getClinics();
		for(PetClinic pc : clinics) {
			System.out.println("Finded the clinic '" + pc.getName() + "' into DB.");
			addNewClinic(pc);
		}
	}

	private void presetUI() {
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.getLookAndFeelDefaults().put("TextPane[Enabled].backgroundPainter", new Painter<Object>() {
				@Override public void paint(Graphics2D g, Object object, int width, int height) {
					g.setColor(Color.GREEN);
					g.fillRect(0, 0, 1, 1);
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
		
		int round = 6, hAdd = 9;
		UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Selected].backgroundPainter", new Painter<Object>() {
			@Override public void paint(Graphics2D g, Object object, int width, int height) {
				g.setPaint(new GradientPaint(width / 6, 0, Color.CYAN.darker(), width, height, Color.PINK));
				g.fillRoundRect(0, 0, width - 2, height + hAdd, round, round);
				
				g.setColor(Color.DARK_GRAY);
				g.drawRoundRect(0, 0, width - 2, height + hAdd, round, round);
			}
        });
		UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[MouseOver+Selected].backgroundPainter", new Painter<Object>() {
			@Override public void paint(Graphics2D g, Object object, int width, int height) {
				g.setPaint(new GradientPaint(width / 4, 0, Color.CYAN, width * 0.9f, height, Color.PINK));
				g.fillRoundRect(0, 0, width - 2, height + hAdd, round, round);
				
				g.setColor(Color.DARK_GRAY);
				g.drawRoundRect(0, 0, width - 2, height + hAdd, round, round);
			}
        });
		
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled].backgroundPainter", new Painter<Object>() {
			@Override public void paint(Graphics2D g, Object object, int width, int height) {
				g.setPaint(new GradientPaint(width / 4, 0, Color.GRAY, width * 0.9f, height, Color.PINK.darker()));
				g.fillRoundRect(0, 0, width - 2, height + hAdd, round, round);
			}        	
        });
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Enabled+MouseOver].backgroundPainter", new Painter<Object>() {
			@Override public void paint(Graphics2D g, Object object, int width, int height) {
				g.setPaint(new GradientPaint(width / 4, 0, Color.GRAY.darker(), width * 0.9f, height, Color.PINK));
				g.fillRoundRect(0, 0, width - 2, height + hAdd, round, round);
			}        	
        });
        
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+MouseOver+Selected].backgroundPainter", Color.GREEN);
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+Pressed+Selected].backgroundPainter", Color.GREEN);
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Focused+Selected].backgroundPainter", Color.GREEN);
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab[Pressed+Selected].backgroundPainter", Color.GREEN);
	}

	private void postsetUI() {
		// consoleArea styles:
        normal = outputArea.addStyle("normal", null);
		StyleConstants.setFontFamily(normal, "Dialog");
		StyleConstants.setFontSize(normal, 12);
		StyleConstants.setForeground(normal, Color.WHITE);
		StyleConstants.setBold(normal, false);

		red = outputArea.addStyle("red", normal);
		StyleConstants.setFontSize(red, 14);
		StyleConstants.setForeground(red, Color.RED);
		StyleConstants.setBold(red, true);

		green = outputArea.addStyle("green", null);
		StyleConstants.setFontFamily(normal, "Arial Narrow");
		StyleConstants.setFontSize(green, 14);
		StyleConstants.setForeground(green, Color.GREEN);
		StyleConstants.setBold(green, true);

		cyan = outputArea.addStyle("cyan", null);
		StyleConstants.setFontSize(cyan, 12);
		StyleConstants.setForeground(cyan, Color.CYAN);
		StyleConstants.setBold(cyan, true);

		orange = outputArea.addStyle("orange", null);
		StyleConstants.setFontSize(orange, 12);
		StyleConstants.setForeground(orange, Color.ORANGE);
		StyleConstants.setBold(orange, false);
	}
	
	private void render(Graphics2D g2D) {
		if (g2D == null) {
			System.err.println("render(): Graphics income is NULL");
		}
		
		if (IOM.get(IOMs.GLOBAL.USE_RENDER).equals("true")) {
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
			g2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			
			g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	//		g2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
	//		g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			
	//		g2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
			
	//		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
	//		g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			
	//		g2D.setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT);
	// 		(VALUE_RESOLUTION_VARIANT_DPI_FIT, VALUE_RESOLUTION_VARIANT_SIZE_FIT, VALUE_RESOLUTION_VARIANT_BASE)
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
								try {Thread.sleep(60); /* animation */} catch (Exception e) {/* IGNORE ANIMATION */}
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

		try {
			if (line.contains("=^_^=")) {
				doc.insertString(doc.getLength(), line, green);
			} else if (line.contains("created new")) {
				doc.insertString(doc.getLength(), line, cyan);
			} else if (line.contains("pet income")) {
				doc.insertString(doc.getLength(), line, orange);
			} else if (line.contains(" restricted")) {
				doc.insertString(doc.getLength(), line, red);
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

	public void clearOut() {
		StyledDocument doc = (StyledDocument) outputArea.getDocument();
		try {doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {e.printStackTrace();}
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
	private void addNewClinic(PetClinic clinic) {
		if (clinic != null) {
			midPane.addTab(clinic.getName() + "  ", clinicIcon, new ClinicPanel(clinic), "Clinic '" + clinic.getName() + "'");
			midPane.setBackgroundAt(midPane.getTabCount() - 1, Color.MAGENTA);
			midPane.revalidate();
			updateInfo();
		}
	}

	private void updateInfo() {
		clinicsLabel.setText("" + (midPane.getTabCount() - 1));
		doctorsLabel.setText("NA");
	}

	private void addNewPet(Pet pet) {
		if (pet == null) {
			healProgress.setValue(100);
			System.out.println("We have a ghost? Its a revenge!!!");
			return;
		}
		
		if (pet.getOwner() == null) {
			healProgress.setValue(100);
			System.out.println("Pets comes without owners restricted!");
			return;
		}
		
		System.out.println("Clinic " + ((ClinicPanel) midPane.getSelectedComponent()).getClinic().getName() + " has new pet income:\n" + pet.toString());
		((ClinicPanel) midPane.getSelectedComponent()).getClinic().work(pet);
		updateInfo();
	}

	// EXIT:
	private void exitReq() {
		int req = JOptionPane.showConfirmDialog(MonitorFrame.this, "Завершить работу?", "Exit reqiest:", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (req == 0) {endWorkEndExit(0);}
	}
	
	public static void endWorkEndExit(int errCode) {
		healProgress.setIndeterminate(true);
		healProgress.setString("Closing the Clinics... wait please...");
		
		Hibernate.close();
		SpringEngine.close();
		
		System.out.println("Clinic system is shutting down with code #" + errCode);
		System.exit(errCode);
	}

	// LISTENERS:
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "exit": exitReq();
				break;
				
			case "clear": clearOut();
				break;
				
			case "addClinic": 
				if (midPane.getTabCount() > 3) {
					JOptionPane.showConfirmDialog(MonitorFrame.this, "<html><b>It is DEMO version!</b><hr>Maximum clinics allowed - <b>3", "DEMO", 
							JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, clinicIcon);
					return;
				}
				healProgress.setIndeterminate(true);
				PetClinic clCreated = new ClinicCreator(MonitorFrame.this).get();
				if (clCreated != null) {
					addNewClinic(Hibernate.writeClinic(clCreated));
					System.out.println("Was created new clinic '" + clCreated.getName() + "'.");
				}
				healProgress.setIndeterminate(false);
				break;
				
				
			case "addDoctor": 
				PetClinic clinic = ((ClinicPanel) midPane.getSelectedComponent()).getClinic();
				Doctor aNewDoc = new DoctorCreator(MonitorFrame.this).get();
				if (aNewDoc != null) {
					
					clinic.addDoctor(aNewDoc);
					aNewDoc.addClinic(clinic);
					Hibernate.writeDoctor(clinic, aNewDoc);
					
					updateLeftButtons();
				}				
				
				break;
				
				
			case "addPet": 
				healProgress.setIndeterminate(true);
				addNewPet(new PetCreator(MonitorFrame.this).get());
				healProgress.setIndeterminate(false);
				break;
				
			default: 
		}
	}
	
	
	// SUB-CLASSES:
	public class ClinicPanel extends JPanel implements ComponentListener {
		private JPanel upWebURLPane;
		
		private final PetClinic clinic;
		private JPanel photosPane;
		private JScrollPane photoScroll, commentScroll;
		private JButton doctorsControlBtn;
		
		private boolean isActive = false;
		
		
		public ClinicPanel(PetClinic clinic) {
			this.clinic = clinic;
			
			setLayout(new BorderLayout(3,3));
			setBackground(Color.GRAY);
			setBorder(plateBorder);
			setName("cp_" + clinic.getName());
			
			JPanel upClinicTitlePane = new JPanel(new BorderLayout(3,3)) {
				@Override
				public void paint(Graphics g) {
					Graphics2D g2D = (Graphics2D) g;
					render(g2D);
					super.paint(g2D);
					
					g2D.setFont(headerFont.deriveFont(defaultHeaderFontSize + getWidth() * 0.005f));
					g2D.setColor(Color.DARK_GRAY);
					g2D.drawString(clinic.getName(), 12 + (int) (getWidth() * 0.01f), 42);
					g2D.setColor(Color.RED.darker());
					g2D.drawString(clinic.getName(), 12 + (int) (getWidth() * 0.011f), 40);
				}
				
				{
					setOpaque(false);
					setBorder(BorderFactory.createCompoundBorder(
							BorderFactory.createTitledBorder(null, "[Информация о клинике #" + clinic.getID() + "]", 1, 2, progressLabelFont, Color.DARK_GRAY),
							new EmptyBorder(-6, 3, 0, 3)
						));
					
					JLabel clDataLabel = new JLabel() {
						{
							setText("<html><i color=white>Адрес: " + clinic.getFias() + "<hr><p align=right color=white>Тел.: " + clinic.getPhone());
						}
					};
					
					upWebURLPane = new JPanel(new BorderLayout()) {
						{
							setOpaque(false);
							
							add(new JButton()
								{
									{
										try {
											String address = clinic.getWebpage().getUrl().toString();
											if (address.contains("www.")) {address = address.substring(address.indexOf("www.") + 4);}
											if (address.contains("http://")) {address = address.split("http://")[1];}
											if (address.contains("https://")) {address = address.split("https://")[1];}
											setText("<html><u color=blue> " + address + " </u>");
										} catch (Exception e) {
											setText("<html><u color=blue> NA </u>");
										}
										
										setFont(linksFont);									
										setBorder(null);
										setBorderPainted(false);
										setFocusPainted(false);
										setContentAreaFilled(false);
										setRolloverEnabled(true);
										
										addActionListener(new ActionListener() {
											@Override public void actionPerformed(ActionEvent e) {
												try {Desktop.getDesktop().browse(clinic.getWebpage().getUrl().toURI());
												} catch (Exception e1) {/* IGNORE NULL URI */}
											}
										});
									}
								}, BorderLayout.SOUTH
							);
						}
					};
					
					add(clDataLabel, BorderLayout.EAST);
					add(upWebURLPane, BorderLayout.WEST);
				}
			};
			
			JPanel midPhotoCommentPane = new JPanel(new BorderLayout(3,3)) {
				{
					
					JSplitPane midSplitPhotoCommentPane = new JSplitPane(1, IOM.get(IOMs.GLOBAL.USE_RENDER).equals("true")) {
						{
							int dividerSize = 16;
							
							setOneTouchExpandable(true);
							setDividerSize(dividerSize);
							setDividerLocation(midPane.getWidth() / 2 - dividerSize / 2);							
							
							photosPane = new JPanel(new FlowLayout(0, 0, 0));
							fillPhotos(photosPane);
							photoScroll = new JScrollPane(photosPane) {
								{
									getVerticalScrollBar().setUnitIncrement(32);
								}
							};
							
							JTextArea commentArea = new JTextArea(clinic.getComment()) {
								{
									setLineWrap(true);
									setWrapStyleWord(true);
									setBackground(Color.DARK_GRAY);
									setForeground(Color.WHITE);
									setFont(linksFont);
								}
							};
							commentScroll = new JScrollPane(commentArea);
							
							setLeftComponent(photoScroll);
							setRightComponent(commentScroll);
						}

						private void fillPhotos(JPanel photosPane) {
							List<BufferedImage> photos = new ArrayList<>();
							Photodir dir = clinic.getPhotoDir();
							if (dir != null) {
								try {
									Set<Path> paths = new TreeSet<>();
									Files.walkFileTree(Paths.get(clinic.getPhotoDir().getPath()), new SimpleFileVisitor<Path>() {
						                @Override
						                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						                    paths.add(file);
						                    return super.visitFile(Paths.get(clinic.getPhotoDir().getPath()), attrs);
						                }
									});
									
									paths.forEach(e -> {
										try {photos.add(ImageIO.read(e.toFile()));
										} catch (IOException e1) {
											System.out.println("Can`t read the photo '" + e.toFile() + "'...");
										}
									});

									int maxWidth = 0;
									int fullHeight = 0;
									for (BufferedImage image : photos) {
										photosPane.add(new JPanel() {
											@Override public void paint(Graphics g) {
												g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
											}
											
											{
												setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
											}
										});
										
										if (image.getWidth() > maxWidth) {maxWidth = image.getWidth();}
										fullHeight += image.getHeight();
									}
									
									photosPane.setPreferredSize(new Dimension(maxWidth, fullHeight));
								} catch (IOException e) {
									System.out.println("Not find the photo dir '" + clinic.getPhotoDir().getPath() + "'...");
								}
							}
						}
					};

					add(midSplitPhotoCommentPane, BorderLayout.CENTER);
//					add(new JSeparator(0) {{setOpaque(false);}}, BorderLayout.SOUTH);
				}
			};
			
			JPanel downControlButtonsPane = new JPanel(new BorderLayout(3,3)) {
				{
					setOpaque(false);
					
					JButton startStopBtn = new JButton() {
						{
							setText("Clinic is " + (clinic.isOpen() ? "open" : "close"));
							setBackground(clinic.isOpen() ? Color.GREEN : Color.RED);
							setForeground(clinic.isOpen() ? Color.BLACK : Color.WHITE);
							setFocusPainted(false);
							
							addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									clinic.setOpen(!clinic.isOpen());
									setText("Clinic is " + (clinic.isOpen() ? "open" : "close"));
									setBackground(clinic.isOpen() ? Color.GREEN : Color.RED);
									setForeground(clinic.isOpen() ? Color.BLACK : Color.WHITE);
									
									updateLeftButtons();
								}
							});
						}
					};
					
					doctorsControlBtn = new JButton("Personal coltrol") {
						{
							setBackground(Color.yellow);
							setFocusPainted(false);
							addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									new ClinicControlDialog(clinic);
								}
							});
						}
					};
					
					JButton deleteClinicBtn = new JButton("Delete the clinic") {
						{
							setBackground(Color.RED);
							setForeground(Color.WHITE);
							setFocusPainted(false);
							addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									int req = JOptionPane.showConfirmDialog(
											MonitorFrame.this, "Вы собираетесь безвозвратно уничтожить клинику?", "WARNING!", 
											JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
									if (req == 0) {
										if (Hibernate.dropClinic(clinic)) {
											midPane.remove(ClinicPanel.this);
											midPane.setSelectedIndex(0);
											updateInfo();
										}
									}
								}
							});
						}
					};
					
					add(startStopBtn, BorderLayout.WEST);
					add(doctorsControlBtn, BorderLayout.CENTER);
					add(deleteClinicBtn, BorderLayout.EAST);
				}
			};
			
			addComponentListener(this);
			
			add(upClinicTitlePane, BorderLayout.NORTH);
			add(midPhotoCommentPane, BorderLayout.CENTER);
			add(downControlButtonsPane, BorderLayout.SOUTH);
		}

		
		public void showControlBtn(boolean show) {
			doctorsControlBtn.setEnabled(show);
		}

		public PetClinic getClinic() {return clinic;}

		public boolean isActive() {return isActive;}
		public void setActive(boolean isActive) {this.isActive = isActive;}


		@Override
		public void componentResized(ComponentEvent e) {
			upWebURLPane.setBorder(new EmptyBorder(32, 3 + (int) (getWidth() * 0.01f), 0, 0));
		}

		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}
		public void componentHidden(ComponentEvent e) {}
	}
}