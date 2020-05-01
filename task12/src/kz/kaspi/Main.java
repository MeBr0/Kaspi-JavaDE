package kz.kaspi;

import kz.kaspi.person.Buyer;
import kz.kaspi.person.LazyBuyer;
import kz.kaspi.person.SmartBuyer;
import kz.kaspi.shop.Shop;
import kz.kaspi.shop.product.clothes.Clothes.Color;
import kz.kaspi.shop.product.clothes.Clothes.Size;
import kz.kaspi.shop.product.clothes.Jeans;
import kz.kaspi.shop.product.clothes.Jeans.Cloth;
import kz.kaspi.shop.product.clothes.Jeans.Fit;
import kz.kaspi.shop.product.clothes.Shirt;
import kz.kaspi.shop.product.clothes.Shirt.NeckType;
import kz.kaspi.shop.product.food.Kefir;
import kz.kaspi.shop.product.food.Milk;
import kz.kaspi.shop.product.food.Sausage.Builder;
import kz.kaspi.shop.product.food.Sausage.MeatType;
import kz.kaspi.shop.product.other.CleaningAgent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Shop shop = Shop.load();
        shop.updateDiscounts();
        shop.open(getBuyers());

        shop.close();
    }

    private static List<Buyer> getBuyers() {
        LocalDate now = LocalDate.now();

        List<Buyer> buyers = new ArrayList<>();

        Buyer buyer1 = new Buyer("1", "Azamat");
        buyers.add(buyer1);

        Buyer buyer2 = new LazyBuyer("2", "Tomiris");
        buyer2.add(Builder.getInstance().
                price(433).title("Sausage").issueDate(now.minusDays(38)).
                expireDays(41).meatType(MeatType.HALF_SMOKED).
                build());
        buyer2.add(new CleaningAgent(100, "007", now.minusYears(1)));
        buyer2.add(new Milk(38.99, "Milk yeah", now, 99, 3.2f));
        buyers.add(buyer2);

        Buyer buyer3 = new Buyer("3", "Carol");
        buyer3.add(Builder.getInstance().
                price(33).title("Built sausage").issueDate(now.minusDays(39)).
                expireDays(2).meatType(MeatType.COOKED).
                build());
        buyer3.add(new Jeans(321, "Jeanssss", now.minusDays(222), Size.XL, Color.GREEN, Fit.BAGGY,
                Cloth.ECRU));
        buyer3.add(new Jeans(555, "WEWEWE", now.minusDays(1), Size.XS, Color.MIXED, Fit.SKINNY,
                Cloth.CHAMBRAY));
        buyers.add(buyer3);

        Buyer buyer4 = new Buyer("4", "Regular guy");
        buyer4.add(new Jeans(23, "Holy white", now, Size.S, Color.WHITE, Fit.RELAXED, Cloth.STRENCH));
        buyer4.add(new Shirt(42, "$42 shirts", now, Size.S, Color.RED, NeckType.POLO));
        buyer4.add(new Jeans(223, "Jeans, yes it is!", now, Size.S, Color.BLACK, Fit.SLIM, Cloth.JEAN));
        buyers.add(buyer4);

        Buyer buyer5 = new LazyBuyer("5", "Carol");
        buyer5.add(new Shirt(23.2, "SHIRT of SHIRT", now.minusYears(1), Size.M, Color.YELLOW,
                NeckType.ROUND));
        buyer5.add(new Shirt(40, "$40 shirts", now, Size.L, Color.GREEN, NeckType.HENLEY));
        buyer5.add(new Kefir(991, "For sure kefir", now, 20, 20));
        buyers.add(buyer5);

        Buyer buyer6 = new SmartBuyer("6", "Smart Carol");
        buyer6.add(new Shirt(222, "SHIRT", now.minusMonths(11), Size.XS, Color.GREY,
                NeckType.V_SHAPE));
        buyers.add(buyer6);

        Buyer buyer7 = new SmartBuyer("7", "Smart Loser");
        buyers.add(buyer7);

        return buyers;
    }
}
