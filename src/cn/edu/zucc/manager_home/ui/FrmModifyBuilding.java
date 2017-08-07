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

public class FrmModifyBuilding  extends JDialog implements ActionListener{
	public info_building building=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("确定");
	private JButton btnCancel = new JButton("取消");
	private JLabel labelregion = new JLabel("区域名称:");
	private JLabel labelName = new JLabel("楼盘名称:");
	private JLabel labelbuildtype= new JLabel("建筑类型:");
	private JLabel labeldata = new JLabel("建筑年代:");
	private JLabel labelstreet = new JLabel("所在街道:");
	private JLabel labeldeveloper = new JLabel("开发商:");
	private JLabel labelratio = new JLabel("容积率:");
	private JLabel labeldensity = new JLabel("绿化率:");
	private JLabel labelpark = new JLabel("停车位:");
	
	private JTextField edtregion = new JTextField(20);
	private JTextField edtbuildtype = new JTextField(20);
	private JTextField edtdata = new JTextField(20);
	private JTextField edtstreet = new JTextField(20);
	private JTextField edtdeveloper = new JTextField(20);
	private JTextField edtratio = new JTextField(20);
	private JTextField edtdensity= new JTextField(20);
	private JTextField edtpark = new JTextField(20);
	private JTextField edtName = new JTextField(20);
	
	public FrmModifyBuilding(JFrame f, String s, boolean b, info_building building1) {
		super(f, s, b);
		edtName.setText(building1.getName());
		edtbuildtype.setText(building1.getBuildType());
		edtdata.setText(building1.getData());
		edtstreet.setText(building1.getStreet());
		edtdeveloper.setText(building1.getDeveloper());
		edtratio.setText(building1.getRatio());
		edtdensity.setText(building1.getDensity());
		edtpark.setText(building1.getPark());
		
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelregion);
		workPane.add(edtregion);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelbuildtype);
		workPane.add(edtbuildtype);
		workPane.add(labeldata);
		workPane.add(edtdata);
		workPane.add(labelstreet);
		workPane.add(edtstreet);
		workPane.add(labeldeveloper);
		workPane.add(edtdeveloper);
		workPane.add(labelratio);
		workPane.add(edtratio);
		workPane.add(labeldensity);
		workPane.add(edtdensity);
		workPane.add(labelpark);
		workPane.add(edtpark);
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String region=this.edtregion.getText();
			String name=this.edtName.getText();
			String buildtype=this.edtbuildtype.getText();
			String data=this.edtdata.getText();
			String street=this.edtstreet.getText();
			String developer=this.edtdeveloper.getText();
			String ratio=this.edtratio.getText();
			String density=this.edtdensity.getText();
			String park=this.edtpark.getText();
			try {
				Manager_HomeUtil.buildingManager.modify(building,region,name,buildtype,data,street,developer,ratio,density,park);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
	}
		
	}

}
