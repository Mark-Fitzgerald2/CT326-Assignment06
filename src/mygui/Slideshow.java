package mygui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class Slideshow extends JFrame {
	//declare a new container
	private Container container;
	//declare a comboBox to choose the slideshow
	private JComboBox slideshowComboBox;
	//create an array with the info for the combobox
	private String slideshows[] = { "Please select", "Animals", "Flowers", "Food"};
	//declare a label for the images
	private JLabel label;
	//declare a timer to cycle through the images
	private Timer timer;
	//create a variable to choose an image from an array
	private int i = 0;
	//declare a button panel for the colour buttons
	private JPanel buttonPanel;
	//declare a combo panel for the comboBox
	private JPanel comboPanel;
	//declare an array of buttons
	private JButton buttons[];
	//create a list which will store references to images
	private String[] list = new String[3];
	//create an empty array so if "Please select"
	//is chosen no images will be displayed
	private String[] emptyList = new String[3];
	//create an array with animal images
	private String[] animalList = {"animal1.jpg", "animal2.jpg", "animal3.jpg"};
	//create an array with flower images
	private String[] flowerList = {"flower1.jpg", "flower2.jpg", "flower3.jpg"};
	//create an array with food images
	private String[] foodList = {"food1.jpg", "food2.jpg", "food3.jpg"};
	//declare a list of colors
	private JList colorList;
	//create an array with color names
	private String colorNames[] = {"White", "Black", "Blue", "Cyan", "Dark Gray", 
			"Gray", "Green", "Light Gray", "Magenta", "Orange", "Pink", "Red", "Yellow" };
	//create an array with different colors
	private Color colors[] = {Color.white, Color.black, Color.blue, Color.cyan, 
			Color.darkGray, Color.gray, Color.green, Color.lightGray, Color.magenta, 
			Color.orange, Color.pink, Color.red, Color.yellow };
	//create a slideshow variable and background variables
	//these will be used to determine what background color
	//will be changed and when
	private int slideshow = 0, background1 = 0, background2 = 0, background3 = 0;
	
	//constructor
	public Slideshow() {
		//set the title
		super("Choose your slideshow");
		//get content pane
		container = getContentPane();
		//create the buttons array with size 3
		buttons = new JButton[3];
		//setup a panel
		buttonPanel = new JPanel();
		//setup the title of each button
		buttons[0] = new JButton("Animal Background Color");
		buttons[1] = new JButton("Flower Background Color");
		buttons[2] = new JButton("Food Background Color");
		//loop through buttons array
		for(int i =0; i<buttons.length; i++) {
			//set the dimensions of the buttons
			buttons[i].setPreferredSize(new Dimension(237,25));
			//add the buttons to the button panel
			buttonPanel.add(buttons[i]);
		}
		//create a new comboBox with slideshows array as input
		slideshowComboBox = new JComboBox(slideshows);
		//set the amount of rows to 4
		slideshowComboBox.setMaximumRowCount(4);
		//create an item listener for the comboBox
		slideshowComboBox.addItemListener(
				new ItemListener() {
					//if the comboBox was changed this will run
					public void itemStateChanged(ItemEvent event) {
						//confirm that the state has changed
						if(event.getStateChange() == ItemEvent.SELECTED) {
							//check if the first option was chosen
							//this is our "Please select" option
							if(slideshowComboBox.getSelectedIndex() == 0) {
								//make sure no images can be displayed
								//do this by setting list to an emptyList
								list = emptyList;
								//the current slideshow mode is 0
								slideshow = 0;
								//set the background to white 
								container.setBackground(colors[0]);
							}
							//check if second option was chosen
							//this is our "Animals" option
							else if(slideshowComboBox.getSelectedIndex() == 1) {
								//set list to animalList so that
								//animal images can be displayed
								list = animalList;
								//current slideshow mode is 1
								slideshow = 1;
								//set the background color to the 
								//value of background1
								container.setBackground(colors[background1]);
							} 
							//check if third option was chosen
							//this is our "Flowers" option
							else if(slideshowComboBox.getSelectedIndex() == 2) {
								//set list to flowerList so that
								//flower images can be displayed
								list = flowerList;
								//current slideshow mode is 2
								slideshow = 2;
								//set the background color to the 
								//value of background2
								container.setBackground(colors[background2]);
							} 
							//check if fourth option was chosen
							//this is our "Food" option
							else if(slideshowComboBox.getSelectedIndex() == 3) {
								//set list to foodList so that
								//food images can be displayed
								list = foodList;
								//current slideshow mode is 3
								slideshow = 3;
								//set the background color to the 
								//value of background2
								container.setBackground(colors[background3]);
							}
						}
					}
				} );
		//create a new buttonHandler
		//this is to deal with the event of a button
		//being pressed
		ButtonHandler handler = new ButtonHandler();
		//add to handler to each button
		buttons[0].addActionListener(handler);
		buttons[1].addActionListener(handler);
		buttons[2].addActionListener(handler);
		//create a new label
		label = new JLabel();
		//set the alignment equal to half of the button Jpanel
		label.setHorizontalAlignment(buttonPanel.getWidth()/2);
		//create a new Timer
		timer = new Timer(1000, new ActionListener() {
			 @Override
	         public void actionPerformed(ActionEvent e) {
				 //everytime a second passes
				 //set the image size for image i
				 SetImageSize(i);
				 //increment i to cycle through 
				 //the list of images
	             i++;
	             //if i has exceeded the length of 
	             //the list
	             if(i >= list.length ) {
	            	 //set it back to the start
	            	 //of the list
	            	 i = 0;
	             }
			 }
		});
		//add the images to the center of the container
		container.add(label, BorderLayout.CENTER);
		//start the timer
		timer.start();
		//create a new panel for comboBox
		comboPanel = new JPanel();
		//set the diemensions of the comboBox
		slideshowComboBox.setPreferredSize( new Dimension(150,25) );
		//add the combox to the panel
		comboPanel.add(slideshowComboBox);
		//add the panel to the container
		container.add(comboPanel, BorderLayout.NORTH);
		//add the button panel to the container
		container.add(buttonPanel, BorderLayout.SOUTH);
		//set the size of the window
		setSize(750, 550);
		//make it visible
		setVisible(true);
		//stop it from being resizable
		setResizable(false);
	}
	
	//class to handle button events
	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//create a JList which passes in the color names
			colorList = new JList(colorNames);
			//make 5 rows visible at a time
			colorList.setVisibleRowCount(5);
			//only allow a user to select one option
			colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//create a list selection listener for the JList
			colorList.addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent event2) {
							//check if the slideshow mode is 0
							if(slideshow==0) {
								//if it is we don't want to background to change
								//check if the animal button is pressed
								if(event.getActionCommand().equals("Animal Background Color")) {
									//if it is change background1 so when we
									//choose animals it will be set to 
									//the user specified color
									background1 = colorList.getSelectedIndex();
								} 
								//check if the flower button is pressed
								else if (event.getActionCommand().equals("Flower Background Color")) {
									//if it is change background2 so when we
									//choose flowers it will be set to 
									//the user specified color
									background2 = colorList.getSelectedIndex();
								} 
								//check if the food button is pressed
								else if(event.getActionCommand().equals("Food Background Color")) {
									//if it is change background3 so when we
									//choose food it will be set to 
									//the user specified color
									background3 = colorList.getSelectedIndex();
								}
							} 
							//check if slideshow mode is 1
							else if(slideshow==1) {
								//if it is we are in animals
								//therefore if they change the animal 
								//background it needs to change
								//the background instantly
								if(event.getActionCommand().equals("Animal Background Color")) {
									background1 = colorList.getSelectedIndex();
									//update the background color immediately
									container.setBackground(colors[background1]);
								} else if (event.getActionCommand().equals("Flower Background Color")) {
									//update the value of background2
									background2 = colorList.getSelectedIndex();
								} else if(event.getActionCommand().equals("Food Background Color")) {
									//update the value of background3
									background3 = colorList.getSelectedIndex();
								}
							} 
							//check if slideshow mode is 2
							else if(slideshow==2) {
								//if it is we are in flowers
								//therefore if they change the flower 
								//background it needs to change
								//the background instantly
								if(event.getActionCommand().equals("Animal Background Color")) {
									//update the value of background1
									background1 = colorList.getSelectedIndex();
								} else if (event.getActionCommand().equals("Flower Background Color")) {
									//update background2 and change the 
									//background instantly
									background2 = colorList.getSelectedIndex();
									container.setBackground(colors[background2]);
								} else if(event.getActionCommand().equals("Food Background Color")) {
									//update background3
									background3 = colorList.getSelectedIndex();
								}
							} 
							//check if slideshow mode is 3
							else if(slideshow==3) {
								//if it is we are in food
								//therefore if they change the food 
								//background it needs to change
								//the background instantly
								if(event.getActionCommand().equals("Animal Background Color")) {
									//update background1
									background1 = colorList.getSelectedIndex();
								} else if (event.getActionCommand().equals("Flower Background Color")) {
									//update background2
									background2 = colorList.getSelectedIndex();
								} else if(event.getActionCommand().equals("Food Background Color")) {
									//update background3 and change
									//background immediately
									background3 = colorList.getSelectedIndex();
									container.setBackground(colors[background3]);
								}
							}
						}
					});
			//check which button was pressed
			//display a corresponding JOptionPane
			//this contains the JScrollPane with the JList
			//of color options
			if(event.getActionCommand().equals("Animal Background Color")) {
				JOptionPane.showMessageDialog(null, new JScrollPane(colorList), 
						"Animal Background Color", JOptionPane.INFORMATION_MESSAGE);
			} else if (event.getActionCommand().equals("Food Background Color")) {
				JOptionPane.showMessageDialog(null, new JScrollPane(colorList), 
						"Food Background Color", JOptionPane.INFORMATION_MESSAGE);
			} else if(event.getActionCommand().equals("Flower Background Color")) {
				JOptionPane.showMessageDialog(null, new JScrollPane(colorList), 
						"Flower Background Color", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	//method for setting image size
	public void SetImageSize(int i){
		//pass in i the index of the array 
		//create a new image with the values from list
		Image newimg = (new ImageIcon(list[i])).getImage().getScaledInstance(
				400, 250, Image.SCALE_SMOOTH);
		//create a new ImageIcon of the new image
        ImageIcon newIcon = new ImageIcon(newimg);
        //set the new icon to a JLabel
        label.setIcon(newIcon);
    }

	//main
	public static void main(String args[]) {
		//create a new slideshow
		Slideshow slideshow = new Slideshow();
		//set the default close operation
		slideshow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
