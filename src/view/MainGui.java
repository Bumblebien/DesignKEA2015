package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainGui extends JFrame {
	private static final long serialVersionUID = -2406374773613041450L;
	
	Object[][] databaseResults;
	Object[] columns = { "Id-FakturaNr", "Dato", "Start/Take-off", "Start/Take-off - 70%", "Ophold/Parking", "Passagerer/Passengers", "Åbning/Opening", "Total DKK" };

	public MainGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(150, 100);
		setTitle("Airport charges");
		setSize(1600, 850);
		setLayout(new BorderLayout());
		setResizable(true);
		
		//Jtable
		DefaultTableModel dTableModel = modifiedDefaultTableModel(columns);
		JTable table = new JTable(dTableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		
		//TabbedPane
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("DONE", scrollPane);
		tabbedPane.addTab("TO-DO", null);
		
		//Add to frame
		add(tabbedPane, BorderLayout.CENTER);
		add(new LeftGui(), BorderLayout.WEST);
		
		setVisible(true);
		revalidate();
	}

	public DefaultTableModel modifiedDefaultTableModel(Object[] columns) {
		DefaultTableModel dTableModel = new DefaultTableModel(databaseResults,columns) {
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
				Class<?> returnValue;

				if ((column >= 0) && (column < getColumnCount())) {
					returnValue = getValueAt(0, column).getClass();
				} else {

					// Returns the class for the item in the column
					returnValue = Object.class;
				}
				return returnValue;
			}

			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		return dTableModel;
	}
}
