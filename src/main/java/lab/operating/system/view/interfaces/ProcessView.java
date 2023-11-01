package lab.operating.system.view.interfaces;

import java.util.List;
import lab.operating.system.model.Process;

/**
 *
 * @author C.Mateo
 */
public interface ProcessView {
    
    public int getNumberOfProcesses();
    public String getFilterOptionSelected();
    public void sendMessageStatus(String message);
    public void loadProcessesInView(List<Process> processList);
}
