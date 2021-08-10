package POJO;

public class Stock {
   private String stockID;
   private String stockName;
   private int stockPrice;
   private int stockCount;
   private int stockDiscount;

   public static enum UpdateType{StockID,StockName,StockPrice,StockCount,StockDiscount};

    

    public Stock(String stockID, String name, int price, int count, int discount) {
    this.stockID = stockID;
    this.stockName = name;
    this.stockPrice = price;
    this.stockCount = count;
    this.stockDiscount = discount;
    }
    public String getStockId() {
        return stockID;
    }
    public void setStockId(String stockID) {
        this.stockID = stockID;
    }
    public String getStockName() {
        return stockName;
    }
    public void setStockName(String name) {
        this.stockName = name;
    }
    public int getStockPrice() {
        return stockPrice;
    }
    public void setStockPrice(int price) {
        this.stockPrice = price;
    }
    public int getStockCount() {
        return stockCount;
    }
    public void setStockCount(int count) {
        this.stockCount = count;
    }
    public int getStockDiscount() {
        return stockDiscount;
    }
    public void setStockDiscount(int discount) {
        this.stockDiscount = discount;
    }

   @Override
   public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("| %-20s |",stockID));
    builder.append(String.format(" %-20s |",stockName));
    builder.append(String.format(" %-15d |",stockPrice));
    builder.append(String.format(" %-15d |",stockCount));
    builder.append(String.format(" %-13s |",stockDiscount+"%"));

    return builder.toString();
   }
   
}
