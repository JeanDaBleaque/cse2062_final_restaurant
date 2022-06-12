import java.util.ArrayList;

public class Order {
    private long id;
    private String foods;
    private String drinks;
    private String customerName;
    private String customerSurname;
    private String customerMail;
    private String customerCity;
    private String customerAddress;
    private boolean isPaid;
    private ArrayList<Food> foodList;
    private ArrayList<Drink> drinkList;

    public Order(long id, String foods, String drinks, String customerName, String customerSurname, String customerMail, String customerCity, String customerAddress, boolean isPaid) {
        this.id = id;
        this.foods = foods;
        this.drinks = drinks;
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerMail = customerMail;
        this.customerCity = customerCity;
        this.customerAddress = customerAddress;
        this.isPaid = isPaid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public String getDrinks() {
        return drinks;
    }

    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setList(ArrayList<Food> foodArray, ArrayList<Drink> drinkArray) {
        long foodId = 0;
        long drinkId = 0;
        foodList = new ArrayList<Food>();
        drinkList = new ArrayList<Drink>();
        for (String food : foods.split(";")) {
            foodId = Long.parseLong(food);
            for (Food foodItem : foodArray) {
                if (foodItem.getId() == foodId) {
                    foodList.add(foodItem);
                }
            }
        }
        for (String drink : drinks.split(";")) {
            drinkId = Long.parseLong(drink);
            for (Drink drinkItem : drinkArray) {
                if (drinkItem.getId() == drinkId) {
                    drinkList.add(drinkItem);
                }
            }
        }
    }

    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    public ArrayList<Drink> getDrinkList() {
        return drinkList;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
