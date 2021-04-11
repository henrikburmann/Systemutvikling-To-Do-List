package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Category;

import java.util.ArrayList;

public class CategoryRegister {


    private ArrayList<Category> categories;

    public CategoryRegister(){
        this.categories = new ArrayList<>();
    }

    /**
     *
     * @return arraylist of categories
     */
    public ArrayList<Category> getCategories(){
        return categories;
    }


    public boolean addCategory(Category category){
        for (Category c: categories){
            if (c.equals(category)){
                return false;
            }
        }
        categories.add(category);
        return true;
    }

    public void removeCategory(Category category){
        categories.remove(category);
    }

    public String toString(){
        StringBuilder text = new StringBuilder();
        categories.forEach(e-> text.append(e.getName() + "\n"));
        return text.toString();
    }
}
