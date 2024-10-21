import java.time.LocalDate;
import java.util.ArrayList;

public class Person {


    private long persNummer;
    private String fNamn;
    private String eNamn;
    private LocalDate startArskort;
    private ArrayList<LocalDate> besok;


    public ArrayList<LocalDate> getBesok() {
        return besok;
    }

    public void setBesok(ArrayList<LocalDate> besok) {
        this.besok = besok;
    }


    public long getPersNummer() {
        return persNummer;
    }

    public void setPersNummer(long persNummer) {
        this.persNummer = persNummer;
    }

    public String getFNamn() {
        return fNamn;
    }

    public void setFNamn(String fNamn) {
        this.fNamn = fNamn;
    }

    public String getENamn() {
        return eNamn;
    }

    public void setENamn(String eName) {
        this.eNamn = eName;
    }

    public LocalDate getStartArskort() {
        return startArskort;
    }

    public void setStartArskort(LocalDate startArskort) {
        this.startArskort = startArskort;
    }

    public Person(Long persNummer, String fNamn, String eNamn, String startArskort) {
        this.persNummer = persNummer;
        this.fNamn = fNamn;
        this.eNamn = eNamn;
        this.startArskort = LocalDate.parse(startArskort);
    }

}


