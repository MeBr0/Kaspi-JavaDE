package kz.kaspi.product.clothes;

import java.util.Objects;

/**
 * Class for jeans
 * Have own fields with enums {@link Fit} and {@link Cloth}
 */
public class Jeans extends Clothes {

    private Fit fit;
    private Cloth cloth;

    public Jeans(double price, String title, Size size, Color color, Fit fit, Cloth cloth) {
        super(price, title, size, color);
        this.fit = fit;
        this.cloth = cloth;
    }

    public Fit getFit() {
        return fit;
    }

    public void setFit(Fit fit) {
        this.fit = fit;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Jeans))
            return false;

        if (!super.equals(o))
            return false;

        Jeans jeans = (Jeans) o;
        return fit == jeans.fit &&
                cloth == jeans.cloth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fit, cloth);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", fit: " + fit.value +
                ", cloth: " + cloth.value + "]";
    }

    public enum Fit {

        REGULAR("Regular"),
        RELAXED("Relaxed"),
        BAGGY("Baggy"),
        SLIM("Slim"),
        SKINNY("Skinny");

        private final String value;

        Fit(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public enum Cloth {

        DENIM("Denim"),
        SERGE("Serge"),
        STRENCH("Stretch"),
        JEAN("Jean"),
        CHAMBRAY("Chambray"),
        ECRU("Ecru");

        private final String value;

        Cloth(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
