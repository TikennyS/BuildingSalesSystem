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
import javax.swing.JTextField;

import cn.edu.zucc.manager_home.Manager_HomeUtil;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.util.BaseException;

public class FrmAddhouse extends JDialog implements ActionListener{
	public info_building building=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labeladdress= new JLabel("房屋地址:");
	private JLabel labelfloor = new JLabel("楼层:");
	private JLabel labeltotal = new JLabel("总楼层:");
	private JLabel labeltype = new JLabel("房屋类型:");
	private JLabel labelorientation = new JLabel("朝向:");
	private JLabel labelarea = new JLabel("面积:");
	private JLabel labelprice = new JLabel("报价:");
	
	private JTextField edtaddress = new JTextField(20);
	private JTextField edtfloor= new JTextField(20);
	private JTextField edttotal = new JTextField(20);
	private JTextField edttype = new JTextField(20);
	private JTextField edtorientation = new JTextField(20);
	private JTextField edtarea = new JTextField(20);
	private JTextField edtprice= new JTextField(20);
	
	public FrmAddhouse(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labeladdress);
		workPane.add(edtaddress);
		workPane.add(labelfloor);
		workPane.add(edtfloor);
		workPane.add(labeltotal);
		workPane.add(edttotal);
		workPane.add(labeltype);
		workPane.add(edttype);
		workPane.add(labelorientation);
		workPane.add(edtorientation);
		workPane.add(labelarea);
		workPane.add(edtarea);
		workPane.add(labelprice);
		workPane.add(edtprice);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(250, 500);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
   }
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String address=this.edtaddress.getText();
			String floor=this.edtfloor.getText();
			String total=this.edttotal.getText();
			String type=this.edttype.getText();
			String orientation=this.edtorientation.getText();
			String area=this.edtarea.getText();
			String price=this.edtprice.getText();
			
			try {
				Manager_HomeUtil.houseManager.addhouse(building,address,floor,total,type,orientation,area,price);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
}
