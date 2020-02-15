// 도움말 화면 출력하는 클래스

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class helpGUI extends JFrame {
	
	JScrollPane hscrollPane;
    ImageIcon hicon;
 
    public helpGUI() {
    	setTitle("HELP");
        hicon = new ImageIcon("helpbg.png");
        
        // 배경화면 이미지 불러오기
        JPanel background = new JPanel() {
        	public void paintComponent(Graphics g) {
        		g.drawImage(hicon.getImage(), 0, 0, null);
        		Dimension d = getSize();
        		g.drawImage(hicon.getImage(), 0, 0, d.width, d.height, null);
        		setOpaque(false);
        		super.paintComponent(g);
            }
        };
       
        hscrollPane = new JScrollPane(background);
        
        background.setLayout(null);
        setContentPane(hscrollPane);
        
        setSize(1024, 768);
        setVisible(true);
        setResizable(false);
        
    }

	public static void main(String[] args) {
		new helpGUI();
	}

}
