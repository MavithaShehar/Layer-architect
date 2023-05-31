package bro;

import bro.custom.impl.CustomerBOimpl;
import bro.custom.impl.ItemsBOimpl;
import bro.custom.impl.PurchaseOrderBOimpl;

public class BOFactory {

        private static BOFactory boFactory;

        private BOFactory(){
        }

        public static BOFactory getBoFactory(){

            return (boFactory==null)? boFactory=new BOFactory() : boFactory;
        }

        public enum BOTypes{

            CUSTOMER,ITEM,PURCHASE_ORDER
        }

        public SuperBO getBO(BOTypes types){
            switch (types){
                case CUSTOMER:
                    return new CustomerBOimpl();
                case ITEM:
                    return new ItemsBOimpl();
                case PURCHASE_ORDER:
                    return new PurchaseOrderBOimpl();
                default:
                    return null;
            }
        }

    }
