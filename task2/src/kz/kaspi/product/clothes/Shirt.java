package kz.kaspi.product.clothes;

import java.util.Objects;

/**
 * Class for shirts
 * Have own fields with enums {@link NeckType}
 */
public class Shirt extends Clothes {

    private NeckType neck;

    public Shirt(double price, String title, Size size, Color color, NeckType neck) {
        super(price, title, size, color);
        this.neck = neck;
    }

    public NeckType getNeck() {
        return neck;
    }

    public void setNeck(NeckType neck) {
        this.neck = neck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Shirt))
            return false;

        if (!super.equals(o))
            return false;

        Shirt shirt = (Shirt) o;
        return neck == shirt.neck;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), neck);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + super.toString() + ", neck type: " + neck.value + "]";
    }

    public enum NeckType {

        ROUND("Round"),
        V_SHAPE("V shape"),
        POLO("Polo"),
        HENLEY("Henley");

        private final String value;

        NeckType(String value) {
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
