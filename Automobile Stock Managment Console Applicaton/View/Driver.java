package View;

import View.BaseInterface.BaseView;

public class Driver{
    public static void main(String[] args) {
        BaseView stockManagerView = new StockManagmentSystemView();
        stockManagerView.onStart();
    }
}