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
import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.util.BaseException;

public class FrmSale extends JDialog implements ActionListener {
	public info_house house=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnOk = new JButton("ȷ��");
	private JButton btnCancel = new JButton("ȡ��");
	private JLabel Name = new JLabel(" �ͻ�����:");
    private JLabel Commission = new JLabel("�н��:");
    
	private JTextField edtName = new JTextField(20);
	private JTextField edtCommission = new JTextField(20);
	public FrmSale(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(Name);
		workPane.add(edtName);
		workPane.add(Commission);
		workPane.add(edtCommission);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(250, 180);
		// ��Ļ������ʾ
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
				String name=this.edtName.getText();
				String Commission=this.edtCommission.getText();
				try {
					Manager_HomeUtil.salesManager.sold(house, name, Commission);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
					return;
				}
			
			}
			
		}
		
	}


