package lab.operating.system.view.interfaces;

import lab.operating.system.model.Catalog;

/**
 *
 * @author C.Mateo
 */
public interface CatalogView {
    public Catalog getCatalogData();
    public void sendMessageStatus(String message);
}
