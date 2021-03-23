package hotelView;

import javax.swing.*;

import Managers.CinemaManager;
import Managers.GuestManager;
import Managers.HotelManager;
import Managers.HteManager;
import Managers.LayoutManager;
import Managers.MaidManager;
import Managers.Notifyer;
import hotel.Hotel;
import hotelEvents.HotelEventManager;
import layoutHandler.JSONReader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen implements ActionListener {

	private  JButton start, exit;
	private  JFrame frame;
	private  JComboBox<String> layoutSelectorField, aantalHteText;
	
	public StartScreen() {

		initStartScreen();
	}

    public void initStartScreen() {

        frame = new JFrame("Hotelsimulatie 3000");
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Font font = new Font("Verdana", Font.BOLD,14);


        // BG IMAGE

        ImageIcon background=new ImageIcon("src/images/frontBG.png"); 

        JLabel back=new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,600,500);


        // BUTTONS

        start = new JButton("Start");
        
        exit = new JButton("Exit");

        start.setBounds(200,400, 100,50);
        
        start.addActionListener(this);
        
        exit.setBounds(300,400, 100, 50 );
        
        exit.addActionListener(this);

        panel.add(start);
        panel.add(exit);

        // LABELS

        JLabel aantalHte = new JLabel("Aantal HTE");
        aantalHte.setFont(font);

        JLabel layoutSelector = new JLabel("Layout");
        layoutSelector.setFont(font);

        aantalHte.setBounds(180,210,100,30);
        layoutSelector.setBounds(180,275,100,30);

        panel.add(aantalHte);
        panel.add(layoutSelector);


        aantalHteText = new JComboBox<String>();
        aantalHteText.addItem("200 milliseconden");
        aantalHteText.addItem("500 milliseconden");
        aantalHteText.addItem("750 milliseconden");
        aantalHteText.addItem("1 seconde");
        
        layoutSelectorField = new JComboBox<String>();

        layoutSelectorField.addItem("Layout 1");
        layoutSelectorField.addItem("Layout 2");
        layoutSelectorField.addItem("Layout 3");
        layoutSelectorField.addItem("Layout 4");

        layoutSelectorField.setBounds(275,275,150,30);
        aantalHteText.setBounds(275,210,150,30);

        panel.add(aantalHteText);
        panel.add(layoutSelectorField);

        panel.add(back);

        frame.add(panel);

        frame.setVisible(true);

    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand() == "Start") {
			System.out.println("Start Hotelsimulatie. \n");
			System.out.println(layoutSelectorField.getSelectedItem().toString() + " Selected.\n");
			System.out.println(aantalHteText.getSelectedItem().toString() + " HTE Selected.\n");
			
			frame.setVisible(false);
			
			
			JSONReader reader = new JSONReader();

			// Creating Managers
			HteManager hteManager = new HteManager();	
			HotelEventManager hem = new HotelEventManager();
			MaidManager maidManager = new MaidManager(reader);		
			HotelManager hotelManager = new HotelManager(reader, hteManager, hem);
			
			// Creating Hotel
			Hotel hotel = new Hotel(reader, hteManager, hotelManager);
			// Registering managers
			hteManager.registerManager(maidManager);

			LayoutManager layoutManager = new LayoutManager(reader, hotel);
			GuestManager guestManager= new GuestManager(reader);
			hteManager.registerManager(guestManager);
			CinemaManager cinemaManager= new CinemaManager(reader);
			hteManager.registerManager(cinemaManager);
					
			hem.register(new Notifyer(guestManager, maidManager, cinemaManager));

			hteManager.registerManager(layoutManager);
			
		}
		if (e.getActionCommand() == "Exit") {
			System.out.println("\n Exit Hotelsimulatie");
			
			frame.setVisible(false);
			System.exit(0);
			
			
		}
		
		
		
	}


}
