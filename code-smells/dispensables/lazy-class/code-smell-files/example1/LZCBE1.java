class Cat {
    private String name;
    private String cry;

    public Cat(String name, String cry) {
        this.name = name;
        this.cry = cry;
    }

    public void purr() {
        System.out.println(this.cry);
    }

    public void getCatName() {
        System.out.println(this.name);
    }
}

class Tiger extends Cat {
    public Tiger(String name, String cry) {
        super(name, cry);
    }

    public void isDangerous() {
        System.out.println("Yes");
    }
}

class Cheetah extends Cat {
    public Cheetah(String name, String cry) {
        super(name, cry);
    }
}

public class LZCBE1 {
    public static void main(String[] args) {
        Cat cat = new Cat("Siamese", "Meow");
        Tiger tiger = new Tiger("Bengal", "Roar");
        Cheetah cheetah = new Cheetah("African", "Meow");
        cheetah.purr();
        cat.purr();
        tiger.purr();
    }
}
