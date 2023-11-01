package lab.operating.system.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.jutils.jprocesses.model.ProcessInfo;

/**
 *
 * @author C.Mateo
 */
public class Process {

    private int pId;
    private String user;
    private String pName;
    private String priority;
    private String description;
    private int virtualMemory;
    private int physicalMemory;
    private int cpuUsage;
    private String fullCommand;
    private LocalTime pTime;
    private LocalTime startTime;

    public Process() {
        pId = -1;
        virtualMemory = -1;
        physicalMemory = -1;
        cpuUsage = 0;
        user = "";
        pName = "";
        priority = "0";
        description = "";
        fullCommand = "";
        pTime = LocalTime.of(0, 0, 0);
        startTime = LocalTime.of(0, 0, 0);
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority() {
        if (getUser() != null) {
            priority = getUser().equals("SYSTEM") ? "1" : "0";
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getpTime() {
        return pTime;
    }

    public void setpTime(LocalTime pTime) {
        this.pTime = pTime;
    }

    public int getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(int virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public int getPhysicalMemory() {
        return physicalMemory;
    }

    public void setPhysicalMemory(int physicalMemory) {
        this.physicalMemory = physicalMemory;
    }

    public int getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        if (cpuUsage != null) {
            this.cpuUsage = Integer.parseInt(cpuUsage);
        } 
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getFullCommand() {
        return fullCommand;
    }

    public void setFullCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void loadFromProcessInfo(ProcessInfo processInfo) {
        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            this.setpId(Integer.parseInt(processInfo.getPid()));
            this.setUser(processInfo.getUser());
            this.setpName(processInfo.getName());
            this.setPriority();
            this.setDescription(processInfo.getCommand());//no hay descripción del proceso, tocó usar el comando
            //this.setVirtualMemory(Integer.parseInt(processInfo.getVirtualMemory()));
            this.setPhysicalMemory(Integer.parseInt(processInfo.getPhysicalMemory()));
            this.setCpuUsage(processInfo.getCpuUsage());
            this.setFullCommand(processInfo.getCommand());
            //this.setpTime(LocalTime.parse(processInfo.getTime(), formatter));
            //this.setStartTime(LocalTime.parse(processInfo.getStartTime(), formatter));

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
