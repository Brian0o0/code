package data_objects;

public class DaoFactory implements IDaoFactory{

    @Override
    public IProductDao productDao() {
        return new ProductDao();
    }
}
