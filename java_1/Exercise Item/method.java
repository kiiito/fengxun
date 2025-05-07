public class method {
    public static void main(String[] agrs) {
        person p = new person();
        p.name = "jack";
        p.age = 10;
        Tools t = new Tools();
        person p2 = t.copyPerson(p);
        System.out.println(p2.name);
    }
}
    class person {
        String name;
        int age;
    }
    class Tools {
        public person copyPerson(person p) {
            person p2 = new person();
            p2.name = p.name;
            p2.age = p.age;
            return p2;
        }
    }

