package gradle_jdbc_study_ui_list;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import gradle_jdbc_study_dto.Department;
import gradle_jdbc_study_ui.ManagementUI;

@SuppressWarnings("serial")
public class PanelDepartmentList extends JPanel implements ActionListener {
	private JTable table;
	private List<Department> deptlist;
	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	private ManagementUI parent;

	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}

	public PanelDepartmentList() {
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		popupMenu = new JPopupMenu();

		mntmPopUpdate = new JMenuItem("수정");
		mntmPopUpdate.addActionListener(this);
		popupMenu.add(mntmPopUpdate);

		mntmPopDelete = new JMenuItem("삭제");
		mntmPopDelete.addActionListener(this);
		popupMenu.add(mntmPopDelete);

		table.setComponentPopupMenu(popupMenu);
		scrollPane.setComponentPopupMenu(popupMenu);
		setVisible(true);

	}

	public void reloadData() {
		table.setModel(new DefaultTableModel(getRows(), getColumnNames()));
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableSetWidth(100, 100);
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[deptlist.size()][];
		for (int i = 0; i < deptlist.size(); i++) {
			rows[i] = deptlist.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] { "번호", "부서명", "위치" };
	}

	protected void tableCellAlignment(int align, int... idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

	protected void tableSetWidth(int... width) {
		TableColumnModel cModel = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmPopUpdate) {
			updateItem();
		}
		if (e.getSource() == mntmPopDelete) {
			deleteItem();
		}
	}

	public void updateItem() {
		int i = table.getSelectedRow();
		if (table.getModel().getRowCount() == 0) {

			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) {
			JOptionPane.showMessageDialog(null, "선택된 부서가 없습니다.");
			return;
		}

		Department searchDept = deptlist.get(i);
		parent.setInfo(searchDept);
	}

	public void setParent(ManagementUI parent) {
		this.parent = parent;

	}

	public void deleteItem() {
		int i = table.getSelectedRow();
		if (table.getModel().getRowCount() == 0) { // 직책정보가 존재하지 않을 경우

			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 부서가 없습니다.");
			return;
		}

		Department searchdept = deptlist.get(i);
		parent.actionPerformedBtnEmpDelete(searchdept);
	}
}
