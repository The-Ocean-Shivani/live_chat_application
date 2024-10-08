
package Chatting_Application;
//first run  server then run client window
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server  implements ActionListener {
	
	JTextField text;// bcoz we can use in constructer and in other function that's by globly declare 
	JPanel a1;
	static Box vertical=Box.createVerticalBox();//vertically message print ho
  static JFrame f=new JFrame();
   static  DataOutputStream dout;
    
	public Server() {
		f.setLayout(null);
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7, 94, 84));
		p1.setBounds(0, 0, 450, 70); // green panel upper side
		p1.setLayout(null);
		f.add(p1); // without this panel not work

		// Arrow icon image work
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
		Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel back = new JLabel(i3);// Arrow clearly see on panel
		back.setBounds(5, 20, 25, 25);// image set
		p1.add(back);
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);// what you want after clicked on arrow
			}

		});

		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);// what you want after clicked on arrow
			}

		});

		// next image profile pic
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/1.png"));
		Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		JLabel profile = new JLabel(i6);
		profile.setBounds(40, 20, 50, 50);// image set
		p1.add(profile);

		// next video icon
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
		Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		JLabel video = new JLabel(i9);
		video.setBounds(300, 20, 30, 30);// video set
		p1.add(video);

		// next telephone icon
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
		Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		JLabel telephone = new JLabel(i12);
		telephone.setBounds(350, 20, 35, 30);// phone set
		p1.add(telephone);

		// next more icon
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		JLabel more = new JLabel(i15);
		more.setBounds(405, 20, 10, 25);// more icon set
		p1.add(more);
		
		//Add user name use jlabel
		JLabel status=new JLabel("Active Now");
		status.setBounds(110,35,100,18);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
		p1.add(status);
		
		//Status show like activity 
		JLabel name=new JLabel("Shivam");
		name.setBounds(110,15,100,20);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
		p1.add(name);
		
		
		
		
		//next panel after header panel for chatting
		/**	JPanel*/ a1=new JPanel();
		a1.setBounds(5, 70,440, 570);
		f.add(a1);
		
		//next TEXTFIELDl for chatting in bottum sec
		/*JTextField*/ text=new JTextField();
		text.setBounds(5, 655,310,40);
		text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f.add(text);
		
		
		//SEND BUTTON
		JButton send=new JButton("Send");
		send.setBounds(320,655,123,40);
		send.setBackground(new Color(7,90,80));
		send.setForeground(Color.WHITE);//send font color
		send.addActionListener(this);//after clicked send button event occurse in method
		send.setFont(new Font("SAN_SERIF",Font.BOLD,18));//bold Send show
       f. add(send);
		
	    	

		f.setSize(450, 700); // frame length & width
		f.setLocation(200, 50); // set frame location opening left se 200 and from upper side 50
		f.setUndecorated(true);// header white palne delete
		f.getContentPane().setBackground(Color.WHITE); // by default white frame
		f.setVisible(true); // frame can see , default left side open frame
	}

	public static void main(String[] args) {
		new Server();
		
		
		try {
			ServerSocket skt=new ServerSocket(6001);//create server 
			while(true)
			{
				Socket s =skt.accept();//message accept
				DataInputStream din=new DataInputStream(s.getInputStream());//message receved
/*/	DataOutputStream/*/ dout=new DataOutputStream(s.getOutputStream());//meassage send
				
				while(true)//message erad continously
				{
					String msg=din.readUTF();
					JPanel panel=formatLabel(msg);
					
					JPanel left=new JPanel(new BorderLayout());//message display
					left.add(panel,BorderLayout.LINE_START);//
					vertical.add(left);
					f.validate();
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	try {
		String out = text.getText();// kuch bhi likhoge out me store hoga
//		System.out.println(out);
//		JLabel output = new JLabel(out);
		JPanel p2 = formatLabel(out);//new JPanel();
//		p2.add(output);
		a1.setLayout(new BorderLayout());// borderlayout work on top buttom left right and center
		JPanel right = new JPanel(new BorderLayout());// right side print ho
		right.add(p2, BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));

		a1.add(vertical, BorderLayout.PAGE_START);
		dout.writeUTF(out);//error through then all code  of actionformed  write in try and catch
        text.setText(" ");//after sending text ,text bar became empty
		
        f.repaint();
		f.invalidate();
		f.validate();
	}catch(Exception e1){
		e1.printStackTrace();
		
	}
	}
	
	public static JPanel formatLabel(String out)
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel output=new JLabel("<html><p style=\"width:150px\">"+out+"</p></html>");
		output.setFont(new Font("Tahoma",Font.PLAIN,16));
		output.setBackground(new Color(37,211,102));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,60));
		panel.add(output);
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
		
		JLabel time=new JLabel();
		time.setText(sdf.format(cal.getTime()));//time show on page
		panel.add(time);
		return panel;
	}
	
}
