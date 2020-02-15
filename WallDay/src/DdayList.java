import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
// ���̵��� ����Ʈ�� ���̰Բ� �����ϴ� Ŭ����
public class DdayList extends JFrame implements ActionListener {
	
	ImageIcon icon = new ImageIcon("menubar.png");
    
    JPanel menubarup = new JPanel() {
        public void paintComponent(Graphics g) {
            g.drawImage(icon.getImage(), 0, 0, null);
            Dimension d = getSize();
            g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
            setOpaque(false);
            super.paintComponent(g);
        }
    };
	
	JMenu m = new JMenu("����");
	JMenuItem insert = new JMenuItem("�߰�");
	JMenuItem update = new JMenuItem("����");
	JMenuItem delete = new JMenuItem("����");
	JMenuItem go = new JMenuItem("����");
	JMenuItem quit = new JMenuItem("����");
	JMenuBar mb = new JMenuBar();
	
	String[] name = {"����", "��", "��", "����", "����", "���ڻ�"};
	
	JButton dDayWindow = new JButton("����");
	
	DefaultTableModel dt = new DefaultTableModel(name, 0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	DBconnect ect = new DBconnect();
	
	public DdayList() {
			
		super("���");
		getContentPane().setLayout(null);
		jsp.setBounds(0, 166, 1018, 552);
		
		getContentPane().add(jsp);
		menubarup.setBounds(0, 0, 1018, 136);
		
		getContentPane().add(menubarup);
		
		m.add(insert);
		m.add(update);
		m.add(delete);
		m.add(go);
		m.add(quit);
		
		mb.setBounds(0, 137, 1018, 31);
		getContentPane().add(mb);
		
		mb.add(m);
		
		insert.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		quit.addActionListener(this);
		go.addActionListener(this);
		
		setSize(1024, 768);
		setVisible(true);
		setResizable(false);
			
		ect.dDaySelectAll(dt);
		
		
		if (dt.getRowCount() > 0) {
			jt.setRowSelectionInterval(0, 0);
		}
	}
	
	//�߰�, ����, ���� �� �̺�Ʈ
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insert) {
			new newDdayGUI(this, "�߰�");
		} else if (e.getSource() == update) {
			new newDdayGUI(this, "����");
		} else if (e.getSource() == delete) {
			int row = jt.getSelectedRow();
			Object obj = jt.getValueAt(row, 3);
			if (ect.dDayDelete(obj.toString()) > 0) {
				newDdayGUI.messageBox(this, "���̰� �����Ǿ����ϴ�.");
				ect.dDaySelectAll(dt);
				if (dt.getRowCount() > 0) {
					jt.setRowSelectionInterval(0, 0);
				}
			} else {
				newDdayGUI.messageBox(this, "���̰� �������� �ʾҽ��ϴ�.");
			}
		} else if (e.getSource() == quit) {
			System.exit(0);
		} else if (e.getSource() == go) {
			int row = jt.getSelectedRow();
			Object obj = jt.getValueAt(row, 3);
			ect.dDayGo(obj.toString(), row);
		}
	}


	public static void main(String[] args) {
		
		new DdayList();

	}

}
