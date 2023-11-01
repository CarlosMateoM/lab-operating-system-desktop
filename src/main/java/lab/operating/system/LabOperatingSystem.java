package lab.operating.system;

import com.formdev.flatlaf.FlatLightLaf;
import lab.operating.system.controller.CatalogController;
import lab.operating.system.controller.ProcessController;
import lab.operating.system.dao.impl.CatalogJdbcDao;
import lab.operating.system.service.CatalogService;
import lab.operating.system.service.ProcessService;
import lab.operating.system.utils.DatabaseUtil;
import lab.operating.system.view.MainFrame;

/**
 *
 * @author C.Mateo
 */
public class LabOperatingSystem {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        MainFrame mainFrame = new MainFrame();
        
        CatalogJdbcDao catalogDao = new CatalogJdbcDao(DatabaseUtil.getDataSource());

        ProcessService processService = new ProcessService();
        CatalogService catalogService = new CatalogService(catalogDao);
        
        ProcessController processController = new ProcessController(processService);
        CatalogController catalogController = new CatalogController(catalogService);

        processController.suscribeView("view1", mainFrame.getProcessPanel());
        catalogController.suscribeView("view1", mainFrame.getProcessPanel());
        
        mainFrame.getProcessPanel().addActionListener(processController);
        mainFrame.getProcessPanel().addActionListener(catalogController);
        
        java.awt.EventQueue.invokeLater(() -> {
            mainFrame.setVisible(true);
        });

    }
}
