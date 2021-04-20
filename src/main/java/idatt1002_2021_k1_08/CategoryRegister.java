package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Category;

import java.util.ArrayList;

public class CategoryRegister {


    private ArrayList<Category> categories;

    /**
     * Constructor that only creates a new instance of the categories ArrayList
     */
    public CategoryRegister(){
        this.categories = new ArrayList<>();
    }

    /**
     *  toString for each String inside categories
     * @return the toString built with StringBuilder
     */
    public String toString(){
        StringBuilder text = new StringBuilder();
        categories.forEach(e-> text.append(e.getName() + "\n"));
        return text.toString();
    }
}
