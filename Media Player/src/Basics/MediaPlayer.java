package Basics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MediaPlayer extends JPanel implements ActionListener{
	JFrame window=new JFrame("Interface");
	JLabel info=new JLabel("Simple");
	JButton addButton=new JButton("Add songs");
	JButton playButton=new JButton("Play");
	JButton stopButton=new JButton("Stop");
	
	Font customFont=new Font("",Font.BOLD,20);
	JComboBox list=new JComboBox();
	JFileChooser browser=new JFileChooser();
	FileNameExtensionFilter filter=new FileNameExtensionFilter("WAV Sound","wav");
	
	int returnValue;
	String[] musics=new String[10];
	int index=0;
	File selectedFile;
	File sound;
	AudioInputStream als;
	Clip clip;
	
	
	MediaPlayer(){
		this.setBackground(Color.YELLOW);
		window.add(this);

		addButton.setFont(customFont);
		stopButton.setFont(customFont);
		playButton.setFont(customFont);
		
		
		addButton.setBackground(Color.black);
		addButton.setForeground(Color.cyan);
		
	playButton.setBackground(Color.BLACK);
	playButton.setForeground(Color.red);
	
	
	stopButton.setBackground(Color.black);
	stopButton.setForeground(Color.blue);
		
addButton.addActionListener(this);
playButton.addActionListener(this);
stopButton.addActionListener(this);
		
		window.add(addButton,BorderLayout.LINE_START);
		window.add(playButton,BorderLayout.CENTER);
		window.add(stopButton,BorderLayout.LINE_END);
		
		list.setBackground(Color.DARK_GRAY);
	//	list.setForeground(Color.GREEN);
		window.add(list,BorderLayout.PAGE_START);
		
		
		browser.setFileFilter(filter);
		
		
		info.setFont(new Font("",Font.ITALIC,20));
		window.setSize(400, 200);
		window.setLocation(100, 100);
		window.add(info,BorderLayout.PAGE_END);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== addButton){
			returnValue=browser.showOpenDialog(window);
		
			if(returnValue==browser.APPROVE_OPTION)
			{
				selectedFile=browser.getSelectedFile();
				musics[index]=selectedFile.toString();
				list.addItem("sONG - "+index);
				index++;
			
			
			}
		
		}
		else if(e.getSource()==playButton){
			try{
				if(list.getSelectedIndex()==0)
				{
					sound=new File(musics[list.getSelectedIndex()]);
					als=AudioSystem.getAudioInputStream(sound);		
					clip=AudioSystem.getClip();
					clip.open(als);
				
				}
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null,ex);
			}
		}
		
		
		else if(e.getSource()==stopButton){
			clip.stop();
		}
	}
	
}
