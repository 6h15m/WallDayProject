import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//새 디데이를 추가하는 클래스
public class newDdayGUI extends JDialog implements ActionListener{
	
	JPanel pw = new JPanel(new GridLayout(6, 1));
	JPanel pc = new JPanel(new GridLayout(6, 1));
	JPanel ps = new JPanel();
	
	JLabel lable_year = new JLabel("년");
	JLabel lable_month = new JLabel("월");
	JLabel lable_day = new JLabel("일");
	JLabel lable_title = new JLabel("제목(변경 불가)");
	JLabel lable_bgColor = new JLabel("배경색");
	JLabel lable_textColor = new JLabel("글자색");
	
	JTextField year = new JTextField();
	JTextField month = new JTextField();
	JTextField day = new JTextField();
	JTextField title = new JTextField();
	JTextField bgColor = new JTextField();
	JTextField textColor = new JTextField();
	
	
	
	JButton confirm;
	JButton reset = new JButton("취소");
	
	DdayList dl;
	
	DBconnect ect = new DBconnect();
	
	public newDdayGUI(DdayList dDay, String index) {
		super(dDay, "추가");
		this.dl = dDay;
		if(index.equals("추가")) {
			confirm = new JButton(index);
		} else {
			confirm = new JButton("수정");
			
			int row = dDay.jt.getSelectedRow();
			
			year.setText(dDay.jt.getValueAt(row, 0).toString());
			month.setText(dDay.jt.getValueAt(row, 1).toString());
			day.setText(dDay.jt.getValueAt(row, 2).toString());
			title.setText(dDay.jt.getValueAt(row, 3).toString());
			bgColor.setText(dDay.jt.getValueAt(row, 4).toString());
			textColor.setText(dDay.jt.getValueAt(row, 5).toString());
		}
		
		pw.add(lable_year);
		pw.add(lable_month);
		pw.add(lable_day);
		pw.add(lable_title);
		pw.add(lable_bgColor);
		pw.add(lable_textColor);
		
		
		pc.add(year);
		pc.add(month);
		pc.add(day);
		pc.add(title);
		pc.add(bgColor);
		pc.add(textColor);
		
		ps.add(confirm);
		ps.add(reset);
		
		add(pw, "West");
		add(pc, "Center");
		add(ps, "South");
		
		setSize(500, 400);
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		confirm.addActionListener(this);
		reset.addActionListener(this);
		
	}
	
	// 추가, 수정, 삭제 기능에 대한 부분
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
	       if(btnLabel.equals("추가")){
	           if(ect.dDayInsert(this) > 0 ){
	               messageBox(this , title.getText() + "이(가) 등록되었습니다.");
	               dispose();
	               ect.dDaySelectAll(dl.dt);             
	               if(dl.dt.getRowCount() > 0) {
	            	  dl.jt.setRowSelectionInterval(0, 0);
	               }
	           } else {
	               messageBox(this,"등록되지 않았습니다.");
	           }
	           
	       }else if(btnLabel.equals("수정")){
	            if(ect.dDayUpdate(this) > 0){
	                messageBox(this, "수정이 완료되었습니다.");
	                dispose();
	                ect.dDaySelectAll(dl.dt);
	                if(dl.dt.getRowCount() > 0 ) {
	                	dl.jt.setRowSelectionInterval(0, 0);
	                }
	            }else{
	                messageBox(this, "수정되지 않았습니다.");
	            }
	            
	       }else if(btnLabel.equals("취소")){
	           dispose();
	       }
	}
	
	public static void messageBox(Object obj , String message){
        JOptionPane.showMessageDialog((Component)obj , message);
    }
}

