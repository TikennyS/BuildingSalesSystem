package cn.edu.zucc.manager_home.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.manager_home.Manager_HomeUtil;
import cn.edu.zucc.manager_home.comtrol.example.ExampleUserManager;
import cn.edu.zucc.manager_home.model.info_building;
import cn.edu.zucc.manager_home.model.info_house;
import cn.edu.zucc.manager_home.model.info_region;
import cn.edu.zucc.manager_home.ui.FrmLogin;
import cn.edu.zucc.manager_home.util.BaseException;




public class FrmMain extends JFrame implements ActionListener  {
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_region=new JMenu("区域管理");
    private JMenu menu_building=new JMenu("楼盘管理");
    private JMenu menu_house=new JMenu("房屋管理");
    private JMenu menu_customermanager=new JMenu("客户管理");
    private JMenu menu_salesmanager=new JMenu("交易管理");
    private JMenu menu_usermanager=new JMenu("用户管理");
    
    private JMenuItem  menuItem_AddRegion=new JMenuItem("新建区域");
    private JMenuItem  menuItem_DeleteRegion=new JMenuItem("删除区域");
    private JMenuItem  menuItem_updataRegion=new JMenuItem("修改区域信息");
    private JMenuItem  menuItem_Addbuilding=new JMenuItem("新建楼盘");
    private JMenuItem  menuItem_Deletebuilding=new JMenuItem("删除楼盘");
    private JMenuItem  menuItem_updatebuilding=new JMenuItem("修改楼盘信息");
    private JMenuItem  menuItem_Addhouse=new JMenuItem("添加房屋");
    private JMenuItem  menuItem_deletehouse=new JMenuItem("删除房屋");
    private JMenuItem  menuItem_housestatus=new JMenuItem("出售");    
    private JMenuItem  menuItem_addcustomer=new JMenuItem("添加客户");
    private JMenuItem  menuItem_allcustomer=new JMenuItem("所有客户信息");
    private JMenuItem  menuItem_allsales=new JMenuItem("所有交易记录");
    private JMenuItem  menuItem_modifypwd=new JMenuItem("修改登陆密码");
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	
	private Object tblregionTitle[]=info_region.tableTitles;
	private Object tblregionData[][];
	DefaultTableModel tabregionModel=new DefaultTableModel();
	private JTable dataTableregion=new JTable(tabregionModel);
	
	private Object tblbuildingTitle[]=info_building.tblbuildingTitle;
	private Object tblbuildingData[][];
	DefaultTableModel tabbuildingModel=new DefaultTableModel();
	private JTable dataTablebuilding=new JTable(tabbuildingModel);
	
	private Object tblhouseTitle[]=info_house.tblhouseTitle;
	private Object tblhouseData[][];
	DefaultTableModel tabhouseModel=new DefaultTableModel();
	private JTable dataTablehouse=new JTable(tabhouseModel);
	
	private info_region curRegion=null;
	private info_building curbuilding=null;
	private info_house curhouse=null;
	List<info_region> allRegion=null;
	List<info_building> allbuilding=null;
	List<info_house> allhouse=null;
	List<info_building> regionbuildings=null;
	List<info_house> buildinghouse=null;
public void reloadregionTable(){//这是测试数据，需要用实际数替换
		
		try {
			allRegion=Manager_HomeUtil.regionManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblregionData =  new Object[allRegion.size()][info_region.tableTitles.length];
		for(int i=0;i<allRegion.size();i++){
			for(int j=0;j<info_region.tableTitles.length;j++)
				tblregionData[i][j]=allRegion.get(i).getCell(j, allRegion,i);
		}
		tabregionModel.setDataVector(tblregionData,tblregionTitle);
		this.dataTableregion.validate();
		this.dataTableregion.repaint();
	}
private void reloadbuildingTabel(int k) throws BaseException
{	if(k<0) return;
    curRegion=allRegion.get(k);
    allbuilding=Manager_HomeUtil.buildingManager.loadbuilding1(curRegion);
	try {
		regionbuildings=Manager_HomeUtil.buildingManager.loadbuilding1(curRegion);
	} catch (BaseException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		return;
	}
	tblbuildingData =new Object[regionbuildings.size()][info_building.tblbuildingTitle.length];
	for(int i=0;i<regionbuildings.size();i++){
		for(int j=0;j<info_building.tblbuildingTitle.length;j++)
			tblbuildingData[i][j]=regionbuildings.get(i).getCell(j, regionbuildings,i);
	}
	
	tabbuildingModel.setDataVector(tblbuildingData,tblbuildingTitle);
	this.dataTablebuilding.validate();
	this.dataTablebuilding.repaint();
	}

private void reloadhouseTabel(int k) throws BaseException
{	if(k<0) return;
    System.out.println(k+"aaaa");
    curbuilding=allbuilding.get(k);
    allhouse=Manager_HomeUtil.houseManager.loadhouse(curbuilding);
	try {
		buildinghouse=Manager_HomeUtil.houseManager.loadhouse(curbuilding);
	} catch (BaseException e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
		return;
	}
	tblhouseData =new Object[buildinghouse.size()][info_house.tblhouseTitle.length];
	for(int i=0;i<buildinghouse.size();i++){
		for(int j=0;j<info_house.tblhouseTitle.length;j++)
			tblhouseData[i][j]=buildinghouse.get(i).getCell(j, buildinghouse,i);
	}
	tabhouseModel.setDataVector(tblhouseData, tblhouseTitle);
	this.dataTablehouse.validate();
    this.dataTablehouse.repaint();
	}
public FrmMain(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("房屋中介管理系统");
		dlgLogin=new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
		this.menu_region.add(this.menuItem_AddRegion); this.menuItem_AddRegion.addActionListener(this);
	    this.menu_region.add(this.menuItem_DeleteRegion); this.menuItem_DeleteRegion.addActionListener(this);
	    this.menu_region.add(this.menuItem_updataRegion); this.menuItem_updataRegion.addActionListener(this);
	    this.menu_building.add(this.menuItem_Addbuilding); this.menuItem_Addbuilding.addActionListener(this);
		this.menu_building.add(this.menuItem_Deletebuilding); this.menuItem_Deletebuilding.addActionListener(this);
		this.menu_building.add(this.menuItem_updatebuilding); this.menuItem_updatebuilding.addActionListener(this);
	    this.menu_house.add(this.menuItem_Addhouse); this.menuItem_Addhouse.addActionListener(this);
	    this.menu_house.add(this.menuItem_deletehouse); this.menuItem_deletehouse.addActionListener(this);
		this.menu_house.add(this.menuItem_housestatus); this.menuItem_housestatus.addActionListener(this);
		this.menu_customermanager.add(this.menuItem_addcustomer); this.menuItem_addcustomer.addActionListener(this);
		this.menu_customermanager.add(this.menuItem_allcustomer); this.menuItem_allcustomer.addActionListener(this);
		this.menu_usermanager.add(this.menuItem_modifypwd); this.menuItem_modifypwd.addActionListener(this);
		this.menu_salesmanager.add(this.menuItem_allsales);this.menuItem_allsales.addActionListener(this);
		
	    menubar.add(menu_region);
	    menubar.add(menu_building);
	    menubar.add(menu_house);
	    menubar.add(menu_customermanager);
	    menubar.add(menu_salesmanager);
	    menubar.add(menu_usermanager);
	   
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableregion), BorderLayout.LINE_START);
	    this.dataTableregion.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.dataTableregion.getSelectedRow();
				if(i<0) {
					return;
				}
				try {
					FrmMain.this.reloadbuildingTabel(i);
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
			}
	    	
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTablebuilding), BorderLayout.CENTER);
	    this.dataTablebuilding.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.dataTablebuilding.getSelectedRow();
				System.out.println(i);
				if(i<0) {
					return;
				}
				try {
					FrmMain.this.reloadhouseTabel(i);
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });
	    this.getContentPane().add(new JScrollPane(this.dataTablehouse), BorderLayout.AFTER_LAST_LINE);
	    
	    this.reloadregionTable();
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!用户"+ExampleUserManager.loginname);//修改成   您好！+登陆用户名
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.menuItem_AddRegion){
			FrmAddRegion dlg=new FrmAddRegion(this,"新建区域",true);
			dlg.setVisible(true);
			reloadregionTable();
		}
		else if(e.getSource()==this.menuItem_DeleteRegion){
			if(this.curRegion==null) {
				JOptionPane.showMessageDialog(null, "请选择区域", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				Manager_HomeUtil.regionManager.deleteRegion(this.curRegion);
				int i=FrmMain.this.dataTableregion.getSelectedRow();
				reloadbuildingTabel(i);
				reloadregionTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_updataRegion){
			FrmModifyRegion dlg=new FrmModifyRegion(this,"修改区域信息",true,curRegion);
			dlg.region=curRegion;
			dlg.setVisible(true);
			int i=FrmMain.this.dataTableregion.getSelectedRow();
			if(i<0)
				JOptionPane.showMessageDialog(null, "请选择区域", "错误",JOptionPane.ERROR_MESSAGE);
			try {
				reloadbuildingTabel(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			reloadregionTable();
	}
		else if(e.getSource()==this.menuItem_Addbuilding){
			FrmAddbuilding dlg=new FrmAddbuilding(this,"新建楼盘",true);
			dlg.region=curRegion;
			dlg.setVisible(true);
			int i=FrmMain.this.dataTableregion.getSelectedRow();
			if(i<0)
				JOptionPane.showMessageDialog(null, "请选择区域", "错误",JOptionPane.ERROR_MESSAGE);
			try {
				reloadbuildingTabel(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==this.menuItem_Deletebuilding){
			int i=FrmMain.this.dataTablebuilding.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择要删除的楼盘", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
			    Manager_HomeUtil.buildingManager.deletebuilding(this.regionbuildings.get(i));	 
				int j=FrmMain.this.dataTableregion.getSelectedRow();
				reloadregionTable();
				reloadbuildingTabel(j);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==this.menuItem_updatebuilding){
			FrmModifyBuilding dlg=new FrmModifyBuilding(this,"修改楼盘信息",true,curbuilding);
			dlg.building=curbuilding;
			dlg.setVisible(true);
			int i=FrmMain.this.dataTablebuilding.getSelectedRow();
			
			try {
				reloadbuildingTabel(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			reloadregionTable();
	}
		else if(e.getSource()==this.menuItem_Addhouse){
			FrmAddhouse dlg=new FrmAddhouse(this,"添加房屋",true);
			dlg.building=curbuilding;
			dlg.setVisible(true);
			int i=FrmMain.this.dataTablebuilding.getSelectedRow();
			if(i<0)
				JOptionPane.showMessageDialog(null, "请选择楼盘", "错误",JOptionPane.ERROR_MESSAGE);
			try {
				reloadhouseTabel(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	    else if(e.getSource()==this.menuItem_deletehouse){
		   int i=FrmMain.this.dataTablehouse.getSelectedRow();
		   if(i<0) {
			  JOptionPane.showMessageDialog(null, "请选择要删除的房屋", "错误",JOptionPane.ERROR_MESSAGE);
			  return;
		}
		   try {
		      Manager_HomeUtil.houseManager.deletehouse(this.buildinghouse.get(i));	 
			  int j=FrmMain.this.dataTableregion.getSelectedRow();
			  reloadhouseTabel(i);
		  } catch (BaseException e1) {
			  JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			  return;
		}
		   }
		else if(e.getSource()==this.menuItem_housestatus){
			FrmSale dlg=new FrmSale(this,"出售",true);
			int i=FrmMain.this.dataTablehouse.getSelectedRow();
			if(i<0)
			JOptionPane.showMessageDialog(null, "请选择房子", "错误",JOptionPane.ERROR_MESSAGE);
			curhouse=allhouse.get(i);
			dlg.house=curhouse;
			dlg.setVisible(true);		
			try {
				reloadhouseTabel(i);
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
			}
		}
		else if(e.getSource()==this.menuItem_addcustomer){
			    FrmAddCustomer dlg=new FrmAddCustomer(this,"添加客户",true);
				dlg.setVisible(true);
				reloadregionTable();
	
		}
		else if(e.getSource()==this.menuItem_allcustomer){
		    FrmAllcustomer dlg=new FrmAllcustomer(this,"所有客户信息",true);
			dlg.setVisible(true);

	}
		else if(e.getSource()==this.menuItem_modifypwd){
			FrmModifyPwd dlg=new FrmModifyPwd(this,"修改登陆密码",true);
			dlg.setVisible(true);

	}
		else if(e.getSource()==this.menuItem_allsales){
			FrmAllsales dlg=new FrmAllsales(this,"所有交易记录",true);
			dlg.setVisible(true);

	}
	}
	}
	

