package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import db.DBConnection;
import env.ENV;
import exception.DataAccessException;
import gui.components.JRoundedButton;
import gui.tabs.FishpackTab;

public class Main extends JFrame {

	private JPanel contentPane;
	private static JPanel viewport;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setTitle("Akvarieimporten");
					frame.setIconImage(ImageIO.read(new File("res/logo.png")));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public Main() throws IOException, InterruptedException {
		setMinimumSize(new Dimension(800, 500));
		setPreferredSize(new Dimension(1000, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		viewport = new JPanel();
		contentPane.add(viewport, BorderLayout.CENTER);
		viewport.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel menuPane = new JPanel();
		menuPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(menuPane, BorderLayout.CENTER);
		GridBagLayout gbl_menuPane = new GridBagLayout();
		gbl_menuPane.columnWidths = new int[] { 150, 0 };
		gbl_menuPane.rowHeights = new int[] { 4, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_menuPane.columnWeights = new double[] { 4.9E-324, Double.MIN_VALUE };
		gbl_menuPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		menuPane.setLayout(gbl_menuPane);

		JButton btnAquarium = new JRoundedButton("Akvarier");
		btnAquarium.setPreferredSize(new Dimension(120, 30));
		btnAquarium.setBorderPainted(false);
		btnAquarium.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnAquarium = new GridBagConstraints();
		gbc_btnAquarium.insets = new Insets(0, 0, 5, 0);
		gbc_btnAquarium.gridx = 0;
		gbc_btnAquarium.gridy = 1;
		menuPane.add(btnAquarium, gbc_btnAquarium);

		JButton btnFishpack = new JRoundedButton("Kuld");
		btnFishpack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFishpackPressed();
			}
		});
		btnFishpack.setPreferredSize(new Dimension(120, 30));
		btnFishpack.setBorderPainted(false);
		btnFishpack.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnFishpack = new GridBagConstraints();
		gbc_btnFishpack.insets = new Insets(0, 0, 5, 0);
		gbc_btnFishpack.gridx = 0;
		gbc_btnFishpack.gridy = 2;
		menuPane.add(btnFishpack, gbc_btnFishpack);

		JButton btnStatistics = new JRoundedButton("Statistik");
		btnStatistics.setPreferredSize(new Dimension(120, 30));
		btnStatistics.setBorderPainted(false);
		btnStatistics.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnStatistics = new GridBagConstraints();
		gbc_btnStatistics.insets = new Insets(0, 0, 5, 0);
		gbc_btnStatistics.gridx = 0;
		gbc_btnStatistics.gridy = 3;
		menuPane.add(btnStatistics, gbc_btnStatistics);

		JButton btnSpecies = new JRoundedButton("Arter");
		btnSpecies.setPreferredSize(new Dimension(120, 30));
		btnSpecies.setBorderPainted(false);
		btnSpecies.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnSpecies = new GridBagConstraints();
		gbc_btnSpecies.insets = new Insets(0, 0, 5, 0);
		gbc_btnSpecies.gridx = 0;
		gbc_btnSpecies.gridy = 4;
		menuPane.add(btnSpecies, gbc_btnSpecies);

		JButton btnLocation = new JRoundedButton("Lokation");
		btnLocation.setPreferredSize(new Dimension(120, 30));
		btnLocation.setBorderPainted(false);
		btnLocation.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnLocation = new GridBagConstraints();
		gbc_btnLocation.insets = new Insets(0, 0, 5, 0);
		gbc_btnLocation.gridx = 0;
		gbc_btnLocation.gridy = 5;
		menuPane.add(btnLocation, gbc_btnLocation);

		JButton btnCalender = new JRoundedButton("Kalender");
		btnCalender.setPreferredSize(new Dimension(120, 30));
		btnCalender.setBorderPainted(false);
		btnCalender.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnCalender = new GridBagConstraints();
		gbc_btnCalender.insets = new Insets(0, 0, 5, 0);
		gbc_btnCalender.gridx = 0;
		gbc_btnCalender.gridy = 6;
		menuPane.add(btnCalender, gbc_btnCalender);

		JButton btnFeedingPlan = new JRoundedButton("Fodreplan");
		btnFeedingPlan.setPreferredSize(new Dimension(120, 30));
		btnFeedingPlan.setBorderPainted(false);
		btnFeedingPlan.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_btnFeedingPlan = new GridBagConstraints();
		gbc_btnFeedingPlan.gridx = 0;
		gbc_btnFeedingPlan.gridy = 7;
		menuPane.add(btnFeedingPlan, gbc_btnFeedingPlan);

		init();
	}

	private void init() {
		try {
			DBConnection.startConnection(ENV.db_host, ENV.db_port, ENV.db_name, ENV.db_username, ENV.db_password);
		} catch (DataAccessException e) {
			// Make error message
			e.printStackTrace();
		}

		btnFishpackPressed();
	}

	private void btnFishpackPressed() {
		EventQueue.invokeLater(() -> setViewport(new FishpackTab()));
	}

	public static void setViewport(JPanel view) {
		viewport.removeAll();
		viewport.add(view, BorderLayout.CENTER);
		viewport.updateUI();
	}
}
