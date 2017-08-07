package cn.edu.zucc.manager_home.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.manager_home.Manager_HomeUtil;
import cn.edu.zucc.manager_home.comtrol.example.ExampleCustomerManager;
import cn.edu.zucc.manager_home.model.info_customer;
import cn.edu.zucc.manager_home.util.BaseException;

public class FrmAllcustomer extends JDialog implements ActionListener{
	public static int fu2k;
	ExampleCustomerManager customer=new ExampleCustomerManager();
	private JPanel toolBar = new JPanel();
	private JLabel Name = new JLabel("关键词查询");
	private JTextField edtId = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Button btndelete = new Button("删除");
	
	private Object tblTitle[]={"姓名","年龄","电话","地址","邮箱"};
	private Object tblData[][];
    DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);	
	
	List<info_customer> allcustomer=null;
	List<info_customer> searchcustomer=null;
	private void reloadTable(){
		
		try {
			allcustomer=(customer.loadcustomer());
			
			tblData =new Object[allcustomer.size()][5];
			for(int i=0;i<allcustomer.size();i++){
				tblData[i][0]=allcustomer.get(i).getName();
				tblData[i][1]=allcustomer.get(i).getAge();
				tblData[i][2]=allcustomer.get(i).getPhone();
				tblData[i][3]=allcustomer.get(i).getAddress();
				tblData[i][4]=allcustomer.get(i).getEmail();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public FrmAllcustomer(Frame f, String s, boolean b) {
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

		toolBar.add(Name);
        toolBar.add(edtId);
        
		toolBar.add(btnSearch);
		toolBar.add(btndelete);
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		this.dataTable.addMouseListener(new MouseAdapter (){
                 
				@Override
				public void mouseClicked(MouseEvent e) {
					fu2k=FrmAllcustomer.this.dataTable.getSelectedRow();
					if(fu2k<0) {
						return;
				}
				}});

		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		this.btnSearch.addActionListener(this);
		this.btndelete.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		reloadTable();
		if(e.getSource()==this.btnSearch){
			String keywords=this.edtId.getText();
			try {
				searchcustomer=customer.search(keywords);
				tblData =new Object[searchcustomer.size()][5];
				for(int i=0;i<searchcustomer.size();i++){
					tblData[i][0]=searchcustomer.get(i).getName();
					tblData[i][1]=searchcustomer.get(i).getAge();
					tblData[i][2]=searchcustomer.get(i).getPhone();
					tblData[i][3]=searchcustomer.get(i).getAddress();
					tblData[i][4]=searchcustomer.get(i).getEmail();
				}
				tablmod.setDataVector(tblData,tblTitle);
				this.dataTable.validate();
				this.dataTable.repaint();
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		else if(e.getSource()==this.btndelete){
			System.out.println(fu2k);
			   if(fu2k<0) {
				  JOptionPane.showMessageDialog(null, "请选择要删除的用户", "错误",JOptionPane.ERROR_MESSAGE);
				  return;
				  }
			try {
				 Manager_HomeUtil.customerManager.deletecustomer(this.allcustomer.get(fu2k));
				 System.out.println(this.allcustomer.get(fu2k).getName());
				 reloadTable();			
			   } catch (BaseException e1) {
					 JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					 return;
					}
			}
			
		}
		
	}

