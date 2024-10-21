import org.junit.jupiter.api.Test;

class SokareTest {
    @Test
    void testSokare() {
        Main main = new Main(true);

        Person p = new Person(1101011111L, "Anna", "Andersson", "2024-01-01");
        Person p2 = new Person(2202022222L, "Beatrice", "Bertilsson", "2024-02-02");
        int soktPerson1 = main.sok(1101011111);
        Person x = main.getKunder().get(soktPerson1);
        int soktPerson2 = main.sok("beatrice", "BERTILSSON");
        Person y = main.getKunder().get(soktPerson2);
        assert (x.getPersNummer()==p.getPersNummer());
        assert (x.getFNamn().equals(p.getFNamn()));
        assert (x.getENamn().equals(p.getENamn()));
        assert (y.getPersNummer()==p2.getPersNummer());
        assert (y.getFNamn().equals(p2.getFNamn()));
        assert (y.getENamn().equals(p2.getENamn()));
    }
    /*
    @Test
    void testPtSok() {
        Person p = new Person(0101011111, "Anna", "Andersson", 2024-01-01);
        p.addTraning(2024-01-01);

    }
    */


}

