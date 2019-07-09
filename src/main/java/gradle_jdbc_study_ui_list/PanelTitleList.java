package gradle_jdbc_study_ui_list;

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

import gradle_jdbc_study_dto.Title;
import gradle_jdbc_study_ui.ManagementUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PanelTitleList extends JPanel implements ActionListener {
	private JTable table;
	private List<Title> titlist;
	private JPopupMenu popupMenu;
	private JMenuItem mntmPopUpdate;
	private JMenuItem mntmPopDelete;
	private ManagementUI parent;

	public void setTitlist(List<Title> titlist) {
		this.titlist = titlist;
	}

	public PanelTitleList() {

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

		tableCellAlignment(SwingConstants.CENTER, 0, 1);

		tableSetWidth(100, 100);
	}

	private Object[][] getRows() {
		Object[][] rows = new Object[titlist.size()][];
		for (int i = 0; i < titlist.size(); i++) {
			rows[i] = titlist.get(i).toArray();
		}
		return rows;
	}

	private String[] getColumnNames() {
		return new String[] { "번호", "직책명" };
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
		if (table.getModel().getRowCount() == 0) { // 직책정보가 존재하지 않을 경우

			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 직책이 없습니다.");
			return;
		}

		Title searchTit = titlist.get(i);
		parent.setInfo(searchTit);
	}

	public void deleteItem() {
		int i = table.getSelectedRow();
		if (table.getModel().getRowCount() == 0) { // 직책정보가 존재하지 않을 경우

			return;
		}
		if (i < 0 || i > table.getModel().getRowCount() - 1) { // 선택하지 않은 경우
			JOptionPane.showMessageDialog(null, "선택된 직책이 없습니다.");
			return;
		}

		Title searchTit = titlist.get(i);
		parent.actionPerformedBtnEmpDelete(searchTit);
	}

	public void setParent(ManagementUI parent) {
		this.parent = parent;

	}

}
