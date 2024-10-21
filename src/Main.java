import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private boolean isTest=false;

    private ArrayList<Person> kunder;

    Main(boolean isTest){

        kunder = new ArrayList<>();

        if (isTest){
            txtInputt("src/test1.txt");
        }
        else{

            int kundnummer;
            String eftersoktKund;
            txtInputt("src/indata.txt");
            int registreraBesok;

            while (true) {

                registreraBesok=1;



                eftersoktKund = JOptionPane.showInputDialog(null, "Skriv in personnummer eller för och efternamn på kunden ni söker\nPersonnummer anges med siffror utan mellanrum ÅÅMMDDXXXX\nNamn anges med med ett blanksteg Förnamn Efternamn");

                if (eftersoktKund == null){
                    System.exit(0);
                }
                else if (eftersoktKund.equals("")) {
                    int val = JOptionPane.showConfirmDialog(null, "Inget värde matades in, vill ni avsluta programet?", "Avsluta?", JOptionPane.YES_NO_OPTION);
                    if (val == JOptionPane.YES_OPTION || val == -1) {
                        System.exit(0);
                    }
                }





                try {
                    kundnummer = sok(Long.parseLong(eftersoktKund));
                } catch (NumberFormatException e) {
                    String fNamn;
                    String eNamn;
                    try {
                        fNamn = eftersoktKund.substring(0, eftersoktKund.indexOf(" ")).trim().toLowerCase();            //metoden borde göra detta! annars behöver man tänka på det varje gång man matar in något
                        eNamn = eftersoktKund.substring(eftersoktKund.indexOf(" ")).trim().toLowerCase();

                    } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
                        JOptionPane.showMessageDialog(null, "Felaktigt angivet värde");
                        continue;
                    }
                    kundnummer = sok(fNamn, eNamn);
                }


                if (kundnummer == -1) {
                    JOptionPane.showMessageDialog(null, "Namnet ni sökte kunde ej hittas, Detta kan bero på att personummer/namn är fel angivna \neller för att personen ni söker inte är registrerad kund");
                } else {
                    if (giltligMedlem(kunder.get(kundnummer).getStartArskort())) {
                        registreraBesok = JOptionPane.showConfirmDialog(null, kunder.get(kundnummer).getFNamn() + " " + kunder.get(kundnummer).getENamn() + "med personnummer: " + kunder.get(kundnummer).getPersNummer() + "\nÄr en giltig medlem och betalade senast medlems avgift den: " + kunder.get(kundnummer).getStartArskort() + "\n\nVill ni logga ett besök till gymmet för denna kund?", "Medlem", JOptionPane.YES_NO_OPTION);
                        if (registreraBesok == -1) {
                            System.exit(0);
                        }
                    }
                    else{

                        JOptionPane.showMessageDialog(null, kunder.get(kundnummer).getFNamn() + " " + kunder.get(kundnummer).getENamn() + "med personnummer: " + kunder.get(kundnummer).getPersNummer() + "\nBetalade senast medlems avgift den: " + kunder.get(kundnummer).getStartArskort() + "\n\nDetta är en registrerad men ej en aktiv medlem.");
                    }
                }

                if(registreraBesok==0){
                    registreraBesok(kundnummer);
                }
            }
        }
    }







    public static void main(String[] args) {

        Main main = new Main(false);

    }






    public void txtInputt (String input) {

        String filevag = input;
        try(BufferedReader reader = new BufferedReader(new FileReader(filevag))) {

            String inmatningUnderBearbetning;

            long persNummer;
            String fNamn;
            String eNamn;
            String date;



            while((inmatningUnderBearbetning = reader.readLine()) != null) {

                persNummer = Long.parseLong(inmatningUnderBearbetning.substring(0, inmatningUnderBearbetning.indexOf(',')));
                System.out.println(persNummer);
                fNamn = inmatningUnderBearbetning.substring(inmatningUnderBearbetning.indexOf(' ')+1, inmatningUnderBearbetning.indexOf(' ',(inmatningUnderBearbetning.indexOf(' ')+1)));
                System.out.println(fNamn);
                eNamn = inmatningUnderBearbetning.substring(1+inmatningUnderBearbetning.indexOf(' ',(inmatningUnderBearbetning.indexOf(' ')+1)));
                System.out.println(eNamn);
                date = reader.readLine();
                System.out.println(date);

                Person kund = new Person(persNummer, fNamn, eNamn, date);

                kunder.add(kund);
            }


        } catch (FileNotFoundException e) {
            System.out.println("hittar ej fil");
            //************************************************** åtgärda
        } catch (IOException e) {
            System.out.println("io");
            //************************************************** åtgärda
        }
    }

    public int sok(long persnum){
        for (int i = 0; i<kunder.size() ; i++) {
            if(kunder.get(i).getPersNummer()==persnum){

                return i;

            }
        }
        return -1;
    }

    public int sok(String fNamn, String eNamn){
        for (int i = 0; i<kunder.size() ; i++) {
            if(kunder.get(i).getFNamn().equalsIgnoreCase(fNamn.trim()) && kunder.get(i).getENamn().equalsIgnoreCase(eNamn.trim())){

                return i;

            }
        }
        return -1;
    }

    public boolean giltligMedlem(LocalDate startMedlem){
        LocalDate idag = LocalDate.now();
        if(idag.isBefore(startMedlem.plusYears(1))){
            return true;
        }
        else return false;
    }

    public void registreraBesok(int kundNummer){
        try (PrintWriter ut = new PrintWriter(new BufferedWriter
                (new FileWriter("src/registreradeBesok_PT.txt", true)))) {
            ut.println(kunder.get(kundNummer).getFNamn() + " " + kunder.get(kundNummer).getENamn() + kunder.get(kundNummer).getPersNummer() + "\nBesökt den: " + LocalDate.now());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Person> getKunder() {
        return kunder;
    }

    public Main(){

    }
}
/*
börja med att läsa in till lista
klass för att läsa in till lista?
    metod för att läsa in från fil och skapa personobjekt
    *test av personobjekt
    lägga in personobjekt i lista
    *test för inläsning

Has next line


*********************************

1 program som serializar filen vi fått.
program som vi köra från som kollar objekt från vår skapade .ser fil.

person objekt vi skapar bör ha en lista med träningstillfällen



 */

//file missmatch kan kolla skillnader mellan filen och man kan ha en test fil som ska överensstämma med skriven fil.