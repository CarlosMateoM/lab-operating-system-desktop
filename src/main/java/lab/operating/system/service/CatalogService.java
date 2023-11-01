package lab.operating.system.service;

import lab.operating.system.dao.CatalogDao;
import lab.operating.system.model.Catalog;

/**
 *
 * @author C.Mateo
 */
public class CatalogService {
    
    private final CatalogDao catalogDao;
    
    public CatalogService(CatalogDao catalogDao){
        this.catalogDao = catalogDao;
    }
    
    public void saveCatalog(Catalog catalog){
        catalogDao.saveCatalog(catalog);
        
    }
}
