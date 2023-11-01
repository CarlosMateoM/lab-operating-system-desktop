package lab.operating.system.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import lab.operating.system.model.Catalog;
import lab.operating.system.service.CatalogService;
import lab.operating.system.view.interfaces.CatalogView;

/**
 *
 * @author C.Mateo
 */
public class CatalogController implements ActionListener {

    private final CatalogService catalogService;
    private final Map<String, CatalogView> viewRegistry;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
        this.viewRegistry = new HashMap<>();
    }

    public void suscribeView(String viewId, CatalogView catalogView) {
        this.viewRegistry.put(viewId, catalogView);
    }

    public void saveCatalog(CatalogView catalogView) {
        try {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    catalogView.sendMessageStatus(" Saving catalog, please wait...");
                    Catalog catalog = catalogView.getCatalogData();
                    catalogService.saveCatalog(catalog);
                    catalogView.sendMessageStatus(" Catalog successfully saved");
                }
            };
            
            thread.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] parts = e.getActionCommand().split("_");

        String viewId = parts[0];
        String command = parts[1];

        CatalogView view = viewRegistry.get(viewId);

        switch (command) {
            case "saveCapturedProcesses":
                saveCatalog(view);
                break;
        }
    }
}
