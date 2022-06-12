public class QRObject {
    String type;
    String name;
    String ingredients;
    String temp;
    boolean isReady;

    public QRObject(String type, String name, String ingredients, String temp) {
        this.type = type;
        this.name = name;
        this.ingredients = ingredients;
        this.temp = temp;
        isReady = false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
