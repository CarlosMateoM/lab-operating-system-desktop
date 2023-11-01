package lab.operating.system.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author C.Mateo
 */
public class Catalog {
    
    private int id;
    private String name;
    private LocalTime time;
    private LocalDate date;
    private List<Process> processesList;

    public Catalog() {
    }

    public Catalog(int id, String name, LocalTime time, LocalDate date, List<Process> processesList) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.date = date;
        this.processesList = processesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Process> getProcessesList() {
        return processesList;
    }

    public void setProcessesList(List<Process> processesList) {
        this.processesList = processesList;
    }
    
}
