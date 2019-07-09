package gradle_jdbc_study;

import java.awt.EventQueue;

import gradle_jdbc_study_ui.ErpManagementUI;

public class ErpMain {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpManagementUI frame = new ErpManagementUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
}
