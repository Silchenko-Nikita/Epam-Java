package edu.epam.fop.json.warehouse;

import edu.epam.fop.json.warehouse.item.Item;
import java.io.InputStream;
import java.util.Collection;

public interface WarehouseReader {
  WarehouseReaderImpl warehouseReaderImpl = new WarehouseReaderImpl();

  Collection<Item> readItems(InputStream data);

  static WarehouseReader getInstance() {
    return warehouseReaderImpl;
  }
}
