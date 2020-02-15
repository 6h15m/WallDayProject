import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
// 첫 실행 화면 클래스
public class firstGUI extends JFrame {
    JScrollPane scrollPane;
    ImageIcon icon;
 
    public firstGUI() {
    	setTitle("WallDay");
        icon = new ImageIcon("bgimage.jpg");
          
        JPanel background = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
       
        scrollPane = new JScrollPane(background);
        
        //시작 버튼
        JButton sbutton = new JButton(new ImageIcon("startbutton2.png"));
        sbutton.setBorderPainted(false);
        sbutton.setContentAreaFilled(false);
        sbutton.setBounds(463, 462, 116, 60);
        sbutton.setFocusPainted(false);
        sbutton.setRolloverIcon(new ImageIcon("startbutton.png"));
        sbutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new DdayList();
        	}
        });
        
        //종료 버튼
        JButton ebutton = new JButton(new ImageIcon("exitbutton2.png"));
        ebutton.setBorderPainted(false);
        ebutton.setContentAreaFilled(false);
        ebutton.setBounds(0, 523, 116, 60);
        ebutton.setFocusPainted(false);
        ebutton.setRolloverIcon(new ImageIcon("exitbutton.png"));
        ebutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == ebutton) {
        			System.exit(0);
        		}
        	}
        });
        
        //도움말 버튼
        JButton hbutton = new JButton(new ImageIcon("helpbutton.png"));
        hbutton.setBorderPainted(false);
        hbutton.setContentAreaFilled(false);
        hbutton.setBounds(866, 0, 146, 60);
        hbutton.setFocusPainted(false);
        hbutton.setRolloverIcon(new ImageIcon("helpbutton2.png"));
        hbutton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		new helpGUI();
        	}
        });
        
        background.setLayout(null);
        background.add(sbutton);
        background.add(ebutton);
        background.add(hbutton);
        setContentPane(scrollPane);
    }
 
    public static void main(String[] args) {
    	firstGUI frame = new firstGUI();
        frame.setSize(1024, 768);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
