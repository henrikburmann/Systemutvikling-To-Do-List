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


    public void addCategory(Category category){
        if(!categories.contains(category)){
            categories.add(category);
        } else throw new IllegalArgumentException("Already in list");
    }

    public void removeCategory(Category category){
        if(categories.contains(category)){
            categories.remove(category);
        }
        else throw new IllegalArgumentException("Category not in list");
    }

    public String toString(){
        StringBuilder text = new StringBuilder();
        categories.forEach(e-> text.append(e.getName() + "\n"));
        return text.toString();
    }
}
