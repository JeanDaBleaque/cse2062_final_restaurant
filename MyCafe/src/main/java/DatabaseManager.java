import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String username = "root";
    private static final String password = "669445";
    private static final String url = "jdbc:mysql://localhost:3306/restaurant";
    private Connection connection = null;
    private static DatabaseManager instance;
    private boolean isConnected = false;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection.isValid(5)) {
                isConnected = true;
                System.out.println("Connected to database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance = this;
    }

    public static DatabaseManager getInstance() {
        if (instance != null) {
            return instance;
        }
        return null;
    }

    public ArrayList<Food> getFoods() {
        ArrayList<Food> foods = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM foods");
            while (resultSet.next()) {
                foods.add(new Food(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getFloat("price"),
                        resultSet.getString("ingredients"), resultSet.getString("type"), resultSet.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public ArrayList<Drink> getDrinks() {
        ArrayList<Drink> drinks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM drinks");
            while (resultSet.next()) {
                drinks.add(new Drink(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getFloat("price"),
                        resultSet.getString("ingredients"), resultSet.getString("type")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drinks;
    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                employees.add(new Employee(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("surname"),
                        resultSet.getString("mail"), resultSet.getString("phone"),
                        resultSet.getString("password"), resultSet.getString("position"), resultSet.getBoolean("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees (id, name, surname, mail, phone, password, position, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setString(4, employee.getMail());
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getPassword());
            preparedStatement.setString(7, employee.getPosition());
            preparedStatement.setBoolean(8, employee.isStatus());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateEmployee(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employees SET name = ?, surname = ?, mail = ?, phone = ?, password = ?, position = ?, status = ? WHERE id = ?");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getMail());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setString(5, employee.getPassword());
            preparedStatement.setString(6, employee.getPosition());
            preparedStatement.setBoolean(7, employee.isStatus());
            preparedStatement.setLong(8, employee.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFood(Food food) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO foods (id, name, price, ingredients, type, description) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setLong(1, food.getId());
            statement.setString(2, food.getName());
            statement.setFloat(3, food.getPrice());
            statement.setString(4, food.getIngredients());
            statement.setString(5, food.getType());
            statement.setString(6, food.getDescription());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDrink(Drink drink) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO drinks (id, name, price, ingredients, type) VALUES (?, ?, ?, ?, ?)");
            statement.setLong(1, drink.getId());
            statement.setString(2, drink.getName());
            statement.setFloat(3, drink.getPrice());
            statement.setString(4, drink.getIngredients());
            statement.setString(5, drink.getType());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFood(Food food) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM foods WHERE id = ?");
            statement.setLong(1, food.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDrink(Drink drink) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM drinks WHERE id = ?");
            statement.setLong(1, drink.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFood(Food food) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE foods SET name = ?, price = ?, ingredients = ?, type = ?, description = ? WHERE id = ?");
            statement.setString(1, food.getName());
            statement.setFloat(2, food.getPrice());
            statement.setString(3, food.getIngredients());
            statement.setString(4, food.getType());
            statement.setString(5, food.getDescription());
            statement.setLong(6, food.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDrink(Drink drink) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE drinks SET name = ?, price = ?, ingredients = ?, type = ? WHERE id = ?");
            statement.setString(1, drink.getName());
            statement.setFloat(2, drink.getPrice());
            statement.setString(3, drink.getIngredients());
            statement.setString(4, drink.getType());
            statement.setLong(5, drink.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong("id"), resultSet.getString("foods"),
                        resultSet.getString("drinks"), resultSet.getString("customerName"),
                        resultSet.getString("customerSurname"), resultSet.getString("customerMail"),
                        resultSet.getString("customerCity"), resultSet.getString("customerAddress"), resultSet.getBoolean("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Food searchFood(long id) {
        Food food = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM foods WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                food = new Food(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getFloat("price"), resultSet.getString("ingredients"),
                        resultSet.getString("type"), resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return food;
    }

    public Drink searchDrink(long id) {
        Drink drink = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM drinks WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                drink = new Drink(resultSet.getLong("id"), resultSet.getString("name"),
                        resultSet.getFloat("price"), resultSet.getString("ingredients"),
                        resultSet.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drink;
    }

    public boolean clearOrder(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateOrderStatus(long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE orders SET status = ? WHERE id = ?");
            statement.setBoolean(1, false);
            statement.setLong(2, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
