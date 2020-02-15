import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;

//디데이 정보를 받아와서 디데이를 계산하고 표출하는 클래스
public class dDayGUI extends JFrame{

	String ddays;
	
	public static int caldate(int myear, int mmonth, int mday) { 
		Calendar today = Calendar.getInstance(); 
		Calendar dday = Calendar.getInstance(); 
		dday.set(myear,mmonth-1,mday);
		long day = dday.getTimeInMillis()/86400000;
		long tday = today.getTimeInMillis()/86400000; 
		long count = tday - day;
		return (int) count+1; 
	}
	
	public void dDayGUI(int year,int month,int day,String title,String bgColor,String textColor) {
		
		if(caldate(year, month, day) >= 0) {
			ddays = "D + " + caldate(year, month, day) + "일";
		} else {
			ddays = "D " + caldate(year, month, day) + " 일";
		}
		
		
		setTitle(title);
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		switch(bgColor) {
		case "빨간색": case "red":
			panel.setBackground(Color.RED);
			break;
		case "주황색": case "orange":
			panel.setBackground(Color.ORANGE);
			break;
		case "노란색": case "yellow":
			panel.setBackground(Color.YELLOW);
			break;
		case "초록색": case "green":
			panel.setBackground(Color.GREEN);
			break;
		case "파란색": case "blue":
			panel.setBackground(Color.BLUE);
			break;
		case "핑크색": case "pink":
			panel.setBackground(Color.PINK);
			break;
		case "검은색": case "black":
			panel.setBackground(Color.BLACK);
			break;
		case "하얀색": case "white":
			panel.setBackground(Color.WHITE);
			break;
		default :
			panel.setBackground(Color.white);
		
		}
		getContentPane().setLayout(null);
		panel.setBounds(-12, -45, 550, 550);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel(year + "년 " + month + "월 " + day + "일 ");
		label.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(17, 159, 483, 120);
		
		JLabel dlabel = new JLabel(ddays);
		dlabel.setFont(new Font("함초롬돋움", Font.PLAIN, 40));
		dlabel.setHorizontalAlignment(JLabel.CENTER);
		dlabel.setBounds(82, 235, 342, 130);
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		lblNewLabel.setBounds(17, 163, 483, 45);
		
		switch(textColor) {
		case "빨간색": case "red":
			label.setForeground(Color.RED);
			dlabel.setForeground(Color.RED);
			lblNewLabel.setForeground(Color.RED);
			break;
		case "주황색": case "orange":
			label.setForeground(Color.ORANGE);
			dlabel.setForeground(Color.ORANGE);
			lblNewLabel.setForeground(Color.ORANGE);
			break;
		case "노란색": case "yellow":
			label.setForeground(Color.YELLOW);
			dlabel.setForeground(Color.YELLOW);
			lblNewLabel.setForeground(Color.YELLOW);
			break;
		case "초록색": case "green":
			label.setForeground(Color.GREEN);
			dlabel.setForeground(Color.GREEN);
			lblNewLabel.setForeground(Color.GREEN);
			break;
		case "파란색": case "blue":
			label.setForeground(Color.BLUE);
			dlabel.setForeground(Color.BLUE);
			lblNewLabel.setForeground(Color.BLUE);
			break;
		case "핑크색": case "pink":
			label.setForeground(Color.PINK);
			dlabel.setForeground(Color.PINK);
			lblNewLabel.setForeground(Color.PINK);
			break;
		case "검은색": case "black":
			label.setForeground(Color.BLACK);
			dlabel.setForeground(Color.BLACK);
			lblNewLabel.setForeground(Color.BLACK);
			break;
		case "하얀색": case "white":
			label.setForeground(Color.WHITE);
			dlabel.setForeground(Color.WHITE);
			lblNewLabel.setForeground(Color.WHITE);
			break;
		default :
			label.setForeground(Color.white);
			dlabel.setForeground(Color.white);
			lblNewLabel.setForeground(Color.white);
		}
		
		
		panel.add(label);
		panel.add(dlabel);
		panel.add(lblNewLabel);
		
		setSize(500, 500);
		setVisible(true);
		setResizable(false);
	}
}