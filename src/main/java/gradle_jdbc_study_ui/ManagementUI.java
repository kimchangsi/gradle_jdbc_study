package gradle_jdbc_study_ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradle_jdbc_study_dao.DepartmentDao;
import gradle_jdbc_study_dao.EmployeeDao;
import gradle_jdbc_study_dao.TitleDao;
import gradle_jdbc_study_daoimpl.TitleDaoimpl;
import gradle_jdbc_study_dto.Department;
import gradle_jdbc_study_dto.Employee;
import gradle_jdbc_study_dto.Title;
import gradle_jdbc_study_ui_info.PanelDepartment;
import gradle_jdbc_study_ui_info.PanelEmloyee;
import gradle_jdbc_study_ui_info.PanelTitle;
import gradle_jdbc_study_ui_list.PanelDepartmentList;
import gradle_jdbc_study_ui_list.PanelEmplyeeList;
import gradle_jdbc_study_ui_list.PanelTitleList;

import java.awt.BorderLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class ManagementUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel panelList;
	private JPanel panelInfo;
	private JButton btnAdd;
	private JButton btnCancle;
	private TitleDao titDao;
	private DepartmentDao deptDao;
	private EmployeeDao empDao;
	private ErpManagementUI parent;
	private ArrayList<Title> titList;
	private ArrayList<Department> deptList;
	private ArrayList<Employee> empList;
	private int i;

	public ManagementUI(UItype type, int i, ErpManagementUI erp) throws SQLException {
		setMinimumSize(new Dimension(500, 800));
		setParentItem(erp, i);
		setList();
		setTitle("관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 438);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		panelInfo = new JPanel();
		panelInfo = PanelFactory.getPanelItem(type);
		setPanelInfo(i);
		contentPane.add(panelInfo);
		panelInfo.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		panel.setBounds(5, 127, 540, 55);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		panel.add(btnAdd);

		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		panel.add(btnCancle);

		contentPane.add(panel);

		panelList = new JPanel();

		setPanelList(i);
		contentPane.add(panelList);
		panelList.setLayout(new GridLayout(1, 0, 0, 0));

		setVisible(true);

		// 자기가 무슨 패널리스트인지확인하기위해 / 부모에 false로 셋팅
		if (panelList instanceof PanelTitleList) {
			parent.setTit(false);
		} else if (panelList instanceof PanelDepartmentList) {
			parent.setDept(false);
		} else if (panelList instanceof PanelEmplyeeList) {
			parent.setEmp(false);
		}

		// 프레임x클릭시 부모에 true로 설정
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (i == 0) {
					parent.setEmp(true);
				} else if (i == 1) {
					parent.setDept(true);
				} else if (i == 2) {
					parent.setTit(true);
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancle) {
			if (i == 0) {
				actionPerformedEmpBtnCancle(e);
			} else if (i == 1) {
				actionPerformedBtnDeptCancle(e);
			} else if (i == 2) {
				actionPerformedBtnTitCancle(e);
			}

		}
		if (e.getSource() == btnAdd) {
			if (btnAdd.getText().equals("추가")) {
				if (i == 0) {
					actionPerformedBtnEmpAdd(e);
				} else if (i == 1) {
					actionPerformedBtnDeptAdd(e);
				} else if (i == 2) {
					actionPerformedBtnTitAdd(e);
				}
			} else {
				if (i == 0) {
					actionPerformedBtnEmpUpdate(e);
				} else if (i == 1) {
					actionPerformedBtnDeptUpdate(e);
				} else if (i == 2) {
					actionPerformedBtnTitUpdate(e);
				}
			}

		}
	}

	private void actionPerformedBtnTitUpdate(ActionEvent e) { // 직책 수정
		Title newTit = ((PanelTitle) panelInfo).getTfUpdate();

		int res = -1;
		try {
			res = titDao.updateTitle(newTit);
			JOptionPane.showMessageDialog(null, String.format("%s 직책이 수정되었습니다", newTit.getTitname()));
			setList();
			setPanelInfo(i);
			reFreshList(i);
			btnAdd.setText("추가");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnDeptUpdate(ActionEvent e) { // 부서수정
		Department newDept = ((PanelDepartment) panelInfo).getTfUpdate();
		int res = -1;
		try {
			res = deptDao.updateDepartment(newDept);
			JOptionPane.showMessageDialog(null, String.format("%s 부서가 수정되었습니다", newDept.getDeptName()));
			setList();
			setPanelInfo(i);
			reFreshList(i);
			btnAdd.setText("추가");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnEmpUpdate(ActionEvent e) { // 사원 수정
		Employee newEmp = ((PanelEmloyee) panelInfo).getTfUpdate();
		int res = -1;
		try {
			res = empDao.updateEmployee(newEmp);
			JOptionPane.showMessageDialog(null, String.format("%s 사원이 수정되었습니다", newEmp.getEmpName()));
			setList();
			setPanelInfo(i);
			reFreshList(i);
			btnAdd.setText("추가");

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnTitCancle(ActionEvent e) { // 직책 취소(텍스트 초기화)
		((PanelTitle) panelInfo).setTf(titList);
		btnAdd.setText("추가");
	}

	private void actionPerformedBtnDeptCancle(ActionEvent e) {// 부서 취소(텍스트 초기화)
		((PanelDepartment) panelInfo).setTf(deptList);
		btnAdd.setText("추가");
	}

	private void actionPerformedEmpBtnCancle(ActionEvent e) {// 사원 취소(텍스트 초기화)
		((PanelEmloyee) panelInfo).setTf(empList);
		btnAdd.setText("추가");
	}

	private void actionPerformedBtnTitAdd(ActionEvent e) { // 직책 추가
		Title newTit = ((PanelTitle) panelInfo).getTf();
		int res = -1;
		try {
			res = titDao.insertTitle(newTit);
			JOptionPane.showMessageDialog(null, String.format("%s 직책이 추가되었습니다", newTit.getTitname()));
			
			setList();
			setPanelInfo(i);
			reFreshList(i);
			

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnDeptAdd(ActionEvent e) { // 부서 추가
		Department newDept = ((PanelDepartment) panelInfo).getTf();
		int res = -1;
		try {
			res = deptDao.insertDepartment(newDept);
			JOptionPane.showMessageDialog(null, String.format("%s 부서가 추가되었습니다", newDept.getDeptName()));
			setList();
			setPanelInfo(i);
			reFreshList(i);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void actionPerformedBtnEmpAdd(ActionEvent e) { // 사원 추가
		Employee newEmp = ((PanelEmloyee) panelInfo).getTf();
		if (newEmp.getEmpName().equals("")) {
			JOptionPane.showMessageDialog(null, "사원명을 입력하세요");
			try {
				setPanelInfo(i);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			int res = -1;
			try {
				res = empDao.insertEmployee(newEmp);
				JOptionPane.showMessageDialog(null, String.format("%s 사원이 추가되었습니다", newEmp.getEmpName()));
				setList();
				setPanelInfo(i);
				reFreshList(i);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	protected void actionPerformedBtnCancle(ActionEvent e) throws SQLException { // 취소버튼 클릭시
		setPanelInfo(i);
	}

	// list패널 set
	public void setPanelList(int i) throws SQLException {
		if (i == 2) {
			panelList = new PanelTitleList();
			reFreshList(i);
		} else if (i == 1) {
			panelList = new PanelDepartmentList();
			reFreshList(i);
		} else if (i == 0) {
			panelList = new PanelEmplyeeList();
			reFreshList(i);
		}
	}

	// info패널의 tf들을 기본값으로 설정
	public void setPanelInfo(int i) throws SQLException {
		if (i == 2) {
			((PanelTitle) panelInfo).setTf(titList);
		} else if (i == 1) {
			((PanelDepartment) panelInfo).setTf(deptList);

		} else if (i == 0) {
			((PanelEmloyee) panelInfo).setTf(empList);
			((PanelEmloyee) panelInfo).setCmbManager(deptList, titList);
		}
	}

	// 리스트에서 수정할 아이템 클릭시 info패널 값 셋팅
	public void setInfo(Title searchTit) {
		((PanelTitle) panelInfo).setTf(searchTit);
		((PanelTitle) panelInfo).setSearchNo(searchTit);
		btnAdd.setText("수정");
	}

	public void setInfo(Department searchDept) {
		((PanelDepartment) panelInfo).setTf(searchDept);
		((PanelDepartment) panelInfo).setSearchNo(searchDept);
		btnAdd.setText("수정");
	}

	public void setInfo(Employee searchEmp) {
		((PanelEmloyee) panelInfo).setTf(searchEmp);
		((PanelEmloyee) panelInfo).setSearchNo(searchEmp);
		btnAdd.setText("수정");
	}

	// daoimp set
	public void setParentItem(ErpManagementUI erp, int i) {
		this.i = i;
		parent = erp;
		titDao = parent.getTitDao();
		deptDao = parent.getDeptDao();
		empDao = parent.getEmpDao();

	}

	// list를 set함
	public void setList() throws SQLException {
		titList = (ArrayList<Title>) titDao.selectTitleByAll();
		deptList = (ArrayList<Department>) deptDao.selectDepartmentByAll();
		empList = (ArrayList<Employee>) empDao.selectEmployeeByAll();
	}

	// 리스트를 새로운 리스트로
	public void reFreshList(int i) {
		if (i == 2) {
			((PanelTitleList) panelList).setTitlist(titList);
			((PanelTitleList) panelList).setParent(this);
			((PanelTitleList) panelList).reloadData();
		} else if (i == 1) {
			((PanelDepartmentList) panelList).setDeptlist(deptList);
			((PanelDepartmentList) panelList).setParent(this);
			((PanelDepartmentList) panelList).reloadData();
		} else if (i == 0) {
			((PanelEmplyeeList) panelList).setEmplist(empList);
			((PanelEmplyeeList) panelList).setParent(this);
			((PanelEmplyeeList) panelList).reloadData();
		}
	}

	// 삭제
	public void actionPerformedBtnEmpDelete(Title searchTit) { // 직책 삭제
		int res = -1;
		try {
			res = titDao.deleteTitle(searchTit);
			JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			setList();
			setPanelInfo(i);
			reFreshList(i);
			btnAdd.setText("추가");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformedBtnEmpDelete(Department searchDept) { // 부서 삭제
		int res = -1;
		try {
			res = deptDao.deleteDepartment(searchDept);
			JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			setList();
			setPanelInfo(i);
			reFreshList(i);
			btnAdd.setText("추가");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actionPerformedBtnEmpDelete(Employee searchEmp) { // 부서 삭제
		int res = -1;
		try {
			res = empDao.deleteEmployee(searchEmp);
			JOptionPane.showMessageDialog(null, "삭제되었습니다.");
			setList();
			setPanelInfo(i);
			reFreshList(i);
			btnAdd.setText("추가");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
