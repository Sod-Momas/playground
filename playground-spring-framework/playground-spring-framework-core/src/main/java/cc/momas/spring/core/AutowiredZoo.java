package cc.momas.spring.core;

import java.util.List;

/**
 * @author Sod-Momas
 * @since 2022/7/23
 */
public class AutowiredZoo {
    private Cat cat;
    private List<AnimalFactory.Animal> animals;

    public AutowiredZoo(Cat cat) {
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public List<AnimalFactory.Animal> getAnimals() {
        return animals;
    }


    public void setAnimals(List<AnimalFactory.Animal> animals) {
        this.animals = animals;
    }
}
