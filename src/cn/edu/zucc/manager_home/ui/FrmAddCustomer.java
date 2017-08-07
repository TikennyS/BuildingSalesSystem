package cn.edu.zucc.manager_home.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.manager_home.Manager_HomeUtil;
import cn.edu.zucc.manager_home.util.BaseException;
import cn.edu.zucc.manager_home.util.BusinessException;

public class FrmAddCustomer extends JDialog implements ActionListener{	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel name = new JLabel("名称：");
	private JLabel age = new JLabel("年龄：");
	private JLabel phone = new JLabel("电话：");
	private JLabel address = new JLabel("地址：");
	private JLabel email = new JLabel("邮箱：");
	
	private JTextField edtname = new JTextField(20);
	private JTextField edtage = new JTextField(20);
	private JTextField edtphone = new JTextField(20);
	private JTextField edtaddress = new JTextField(20);
	private JTextField edtemail = new JTextField(20);
	
	public FrmAddCustomer(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(name);
		workPane.add(edtname);
		workPane.add(age);
		workPane.add(edtage);
		workPane.add(phone);
		workPane.add(edtphone);
		workPane.add(address);
		workPane.add(edtaddress);
		workPane.add(email);
		workPane.add(edtemail);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(250, 350);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String name=this.edtname.getText();
			String age=this.edtage.getText();
			String phone=this.edtphone.getText();
			String address=this.edtaddress.getText();
			String email=this.edtemail.getText();
			
			try {
				Manager_HomeUtil.customerManager.addcustomer(name,age,phone,address,email);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.setVisible(false);
		
		}
		
	}
	}


