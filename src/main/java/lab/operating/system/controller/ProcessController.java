package lab.operating.system.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import lab.operating.system.service.ProcessService;
import lab.operating.system.view.interfaces.ProcessView;

/**
 *
 * @author C.Mateo
 */
public class ProcessController implements ActionListener {

    private final ProcessService processService;
    private final Map<String, ProcessView> viewRegistry;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
        this.viewRegistry = new HashMap<>();
    }

    public void suscribeView(String viewId, ProcessView personView) {
        this.viewRegistry.put(viewId, personView);
    }

    public void captureAllSystemProcesses(ProcessView processView) {
        processView.sendMessageStatus("Capturing system processes, please wait...");
        Thread thread = new Thread() {
            @Override
            public void run() {
                processService.CaptureAllSystemProcesses();
                processView.loadProcessesInView(processService.getAllProcesses());
            }
        };
        thread.start();

    }

    public void loadFilteredProcessAndUpdateView(ProcessView processView) {
        try {
            int n = processView.getNumberOfProcesses();
            String filterOptionSelected = processView.getFilterOptionSelected();
            if (n == -1 && filterOptionSelected.equals("NONE")) {
                processView.loadProcessesInView(processService.getAllProcesses());
            } else if (n == -1 && filterOptionSelected.equals("CPU")) {
                processView.loadProcessesInView(processService.getTopProcessesByCpuUsage());
                processView.sendMessageStatus("Processes filtered by CPU usage");
            } else if (n == -1 && filterOptionSelected.equals("MEMORY")) {
                processView.loadProcessesInView(processService.getTopProcessesByPhysicalMemory());
                processView.sendMessageStatus("Processes filtered by MEMORY usage");
            } else if (n > 1 && filterOptionSelected.equals("NONE")) {
                processView.loadProcessesInView(processService.getNProcesses(n));
            } else if (n > 1 && filterOptionSelected.equals("CPU")) {
                processView.loadProcessesInView(processService.getTopProcessesByCpuUsage(n));
                processView.sendMessageStatus("Processes filtered by CPU usage");
            } else if (n > 1 && filterOptionSelected.equals("MEMORY")) {
                processView.loadProcessesInView(processService.getTopProcessesByPhysicalMemory(n));
                processView.sendMessageStatus("Processes filtered by MEMORY usage");
            } else {
                processView.sendMessageStatus("¡Number is less than 1!");
            }
        } catch (NumberFormatException e) {
            processView.sendMessageStatus("Invalid input");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] parts = e.getActionCommand().split("_");

        String viewId = parts[0];
        String command = parts[1];

        ProcessView view = viewRegistry.get(viewId);

        switch (command) {
            case "captureProcesses":
                captureAllSystemProcesses(view);
                break;
            case "filter":
                loadFilteredProcessAndUpdateView(view);
                break;

        }
    }
}
