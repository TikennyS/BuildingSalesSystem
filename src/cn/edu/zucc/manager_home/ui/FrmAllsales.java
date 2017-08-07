package cn.edu.zucc.manager_home.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.manager_home.comtrol.example.ExampleCustomerManager;
import cn.edu.zucc.manager_home.comtrol.example.ExampleSalesManager;
import cn.edu.zucc.manager_home.model.info_customer;
import cn.edu.zucc.manager_home.model.info_sales;
import cn.edu.zucc.manager_home.util.BaseException;

public class FrmAllsales extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	ExampleSalesManager sales=new ExampleSalesManager();
	private Object tblTitle[]={"房子地址","客户姓名","时间","佣金"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable dataTable=new JTable(tablmod);
	List<info_sales> allsales=null;
   

  private void reloadTable(){
	  
		
		try {
			allsales=(sales.loadsales());
			
			tblData =new Object[allsales.size()][4];
			for(int i=0;i<allsales.size();i++){
				tblData[i][0]=allsales.get(i).getAddress();
				tblData[i][1]=allsales.get(i).getName();
				tblData[i][2]=allsales.get(i).getTime();
				tblData[i][3]=allsales.get(i).getCommission();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.dataTable.validate();
			this.dataTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
   public FrmAllsales(Frame f, String s, boolean b){
	   toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	   this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
				
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
			
		}


@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}


}

