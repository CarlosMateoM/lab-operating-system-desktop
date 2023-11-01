package lab.operating.system.service;

import java.util.Comparator;
import lab.operating.system.model.Process;
import java.util.List;
import java.util.stream.Collectors;
import org.jutils.jprocesses.JProcesses;

/**
 *
 * @author C.Mateo
 */
public class ProcessService {

    private List<Process> processesList;

    public ProcessService() {
        processesList = null;
    }

    public void CaptureAllSystemProcesses() {
        this.processesList = JProcesses.getProcessList()
                .stream()
                .map(jprocess -> {
                    Process process = new Process();
                    process.loadFromProcessInfo(jprocess);
                    return process;
                }).collect(Collectors.toList());
    }

    public List<Process> getAllProcesses() {
        if (processesList != null) {
            return processesList;
        } else {
            throw new RuntimeException("Uncaptured processes");
        }
    }

    public List<Process> getNProcesses(int n) {
        return getAllProcesses()
                .stream()
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Process> getTopProcessesByCpuUsage() {
        return getAllProcesses()
                .stream()
                .sorted(Comparator.comparing(Process::getCpuUsage).reversed())
                .collect(Collectors.toList());
    }

    public List<Process> getTopProcessesByPhysicalMemory() {
        return getAllProcesses()
                .stream()
                .sorted(Comparator.comparing(Process::getPhysicalMemory).reversed())
                .collect(Collectors.toList());
    }

    public List<Process> getTopProcessesByCpuUsage(int n) {
        return getAllProcesses()
                .stream()
                .sorted(Comparator.comparing(Process::getCpuUsage).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Process> getTopProcessesByPhysicalMemory(int n) {
        return getAllProcesses()
                .stream()
                .sorted(Comparator.comparing(Process::getPhysicalMemory).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}
