package dao.custom;

import dao.SuperDAO;
import dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null ) ? daoFactory = new DAOFactory() : daoFactory ;
    }

    public enum DAOTypes{

        CUSTOMER,ITEM,ORDER,DETAILS,QUERY_DAO
    }

    public SuperDAO getDAO(DAOTypes types){

        switch (types){
            case CUSTOMER:
                return new CustomersDAOimpl();

            case ITEM:
                return new ItemsDAOimpl();

            case ORDER:
                return new PlaceOrdersDAOimpl();

            case DETAILS:
                return new OrderDetailDAOimpl();

            case QUERY_DAO:
                return new QueryDAOimpl();

            default:
                return null;

        }
    }

}
