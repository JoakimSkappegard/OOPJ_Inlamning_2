import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PersonTest {
    @Test
    void testPerson() {
        long persnummer = 0101011111;
        Person p = new Person(persnummer, "Anna", "Andersson", "2024-01-01");
        assert(p.getPersNummer() ==0101011111);
        assert (!(p.getPersNummer()==0101012222));

        assert(p.getFNamn().equals("Anna"));
        assert (!p.getFNamn().equals("Klas"));

        assert(p.getENamn().equals("Andersson"));
        assert(!p.getENamn().equals("Groda"));

        LocalDate testDate = LocalDate.of(2024, 01, 01);
        assert(testDate.equals(p.getStartArskort()));
        assert(!testDate.equals(p.getStartArskort().plusDays(1)));
    }
}