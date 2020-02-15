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

//�� ���̸� �߰��ϴ� Ŭ����
public class newDdayGUI extends JDialog implements ActionListener{
	
	JPanel pw = new JPanel(new GridLayout(6, 1));
	JPanel pc = new JPanel(new GridLayout(6, 1));
	JPanel ps = new JPanel();
	
	JLabel lable_year = new JLabel("��");
	JLabel lable_month = new JLabel("��");
	JLabel lable_day = new JLabel("��");
	JLabel lable_title = new JLabel("����(���� �Ұ�)");
	JLabel lable_bgColor = new JLabel("����");
	JLabel lable_textColor = new JLabel("���ڻ�");
	
	JTextField year = new JTextField();
	JTextField month = new JTextField();
	JTextField day = new JTextField();
	JTextField title = new JTextField();
	JTextField bgColor = new JTextField();
	JTextField textColor = new JTextField();
	
	
	
	JButton confirm;
	JButton reset = new JButton("���");
	
	DdayList dl;
	
	DBconnect ect = new DBconnect();
	
	public newDdayGUI(DdayList dDay, String index) {
		super(dDay, "�߰�");
		this.dl = dDay;
		if(index.equals("�߰�")) {
			confirm = new JButton(index);
		} else {
			confirm = new JButton("����");
			
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
	
	// �߰�, ����, ���� ��ɿ� ���� �κ�
	public void actionPerformed(ActionEvent e) {
		String btnLabel = e.getActionCommand();
	       if(btnLabel.equals("�߰�")){
	           if(ect.dDayInsert(this) > 0 ){
	               messageBox(this , title.getText() + "��(��) ��ϵǾ����ϴ�.");
	               dispose();
	               ect.dDaySelectAll(dl.dt);             
	               if(dl.dt.getRowCount() > 0) {
	            	  dl.jt.setRowSelectionInterval(0, 0);
	               }
	           } else {
	               messageBox(this,"��ϵ��� �ʾҽ��ϴ�.");
	           }
	           
	       }else if(btnLabel.equals("����")){
	            if(ect.dDayUpdate(this) > 0){
	                messageBox(this, "������ �Ϸ�Ǿ����ϴ�.");
	                dispose();
	                ect.dDaySelectAll(dl.dt);
	                if(dl.dt.getRowCount() > 0 ) {
	                	dl.jt.setRowSelectionInterval(0, 0);
	                }
	            }else{
	                messageBox(this, "�������� �ʾҽ��ϴ�.");
	            }
	            
	       }else if(btnLabel.equals("���")){
	           dispose();
	       }
	}
	
	public static void messageBox(Object obj , String message){
        JOptionPane.showMessageDialog((Component)obj , message);
    }
}

