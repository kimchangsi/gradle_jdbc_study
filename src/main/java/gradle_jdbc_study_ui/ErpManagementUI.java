package gradle_jdbc_study_ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_study_dao.DepartmentDao;
import gradle_jdbc_study_dao.EmployeeDao;
import gradle_jdbc_study_dao.TitleDao;
import gradle_jdbc_study_daoimpl.DepartmentDaoimpl;
import gradle_jdbc_study_daoimpl.EmployeeDaoimpl;
import gradle_jdbc_study_daoimpl.TitleDaoimpl;

@SuppressWarnings("serial")
public class ErpManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnEmpMgn;
	private JButton btnDeptMgn;
	private JButton btnTitMgn;
	private ManagementUI mgnFrame;
	
	private TitleDao titDao;
	private DepartmentDao deptDao;
	private EmployeeDao empDao;
	private boolean isEmp;
	private boolean isDept;
	private boolean isTit;
	
	

	public void setEmp(boolean isEmp) {
		this.isEmp = isEmp;
	}



	public void setDept(boolean isDept) {
		this.isDept = isDept;
	}



	public void setTit(boolean isTit) {
		this.isTit = isTit;
	}



	public EmployeeDao getEmpDao() {
		return empDao;
	}



	public DepartmentDao getDeptDao() {
		return deptDao;
	}



	public TitleDao getTitDao() {
		return titDao;
	}

	

	public ErpManagementUI() {
		setTitle("ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 10, 0));
		
		btnEmpMgn = new JButton("사원관리");
		btnEmpMgn.addActionListener(this);
		contentPane.add(btnEmpMgn);
		
		btnDeptMgn = new JButton("부서관리");
		btnDeptMgn.addActionListener(this);
		contentPane.add(btnDeptMgn);
		
		btnTitMgn = new JButton("직책관리");
		btnTitMgn.addActionListener(this);
		contentPane.add(btnTitMgn);
		
		
		titDao = new TitleDaoimpl();
		deptDao = new DepartmentDaoimpl();
		empDao = new EmployeeDaoimpl();
		
		isDept=true;
		isEmp=true;
		isTit=true;
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitMgn) {
			try {
				actionPerformedBtnTitMgn(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnDeptMgn) {
			try {
				actionPerformedBtnDeptMgn(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnEmpMgn) {
			try {
				actionPerformedBtnEmpMgn(e);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	protected void actionPerformedBtnEmpMgn(ActionEvent e) throws SQLException { 
		if(mgnFrame==null || isEmp) {
			mgnFrame = new ManagementUI(UItype.EMPLOYEE,0,this); 
		}
	}
	protected void actionPerformedBtnDeptMgn(ActionEvent e) throws SQLException { 
		if(mgnFrame==null || isDept) {
		mgnFrame = new ManagementUI(UItype.DEPARTMENT,1,this); 
		}
		
	}
	protected void actionPerformedBtnTitMgn(ActionEvent e) throws SQLException { 
		if(mgnFrame==null || isTit) {
			mgnFrame = new ManagementUI(UItype.TITLE,2,this); 
		}
		
	}
}
