package lab.operating.system.view.model;

import lab.operating.system.model.Process;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author C.Mateo
 */
public class ProcessTableModel extends AbstractTableModel {

    private List<Process> processList;
    private final String COLUMN_NAMES[];

    public ProcessTableModel() {
        processList = new ArrayList<>();
        COLUMN_NAMES = new String [] {"PID", "NAME", "USER", "DESCRIPTION", "CPU", "MEMORY", "PRIORITY"};
    }
    
    public List<Process> getProcessList() {
        return processList;
    }
    
    public void setProccessList(List<Process> processList){
        this.processList = processList;
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
    
    @Override
    public int getRowCount() {
        return processList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Process process = processList.get(rowIndex);
        switch(columnIndex){
            case 0:return process.getpId();
            case 1:return process.getpName();
            case 2:return process.getUser();
            case 3:return process.getDescription();
            case 4:return process.getCpuUsage();
            case 5:return process.getPhysicalMemory();
            case 6:return process.getPriority();
        }
        return process;
    }

}
