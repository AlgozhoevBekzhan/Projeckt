package SmsHandy;

import java.util.ArrayList;
import java.util.Iterator;

public class SmsHandy {
    private String rufnummer;
    private Provider provider;
    private ArrayList<Nachricht> gesendet = new ArrayList();
    private ArrayList<Nachricht> empfangen = new ArrayList();

    public SmsHandy(String rufnummer, Provider provider) {
        this.rufnummer = rufnummer;
        this.provider = provider;
    }

    public void empfangeSms() {
        Nachricht nachricht = this.provider.check(this.rufnummer);
        if (nachricht == null) {
            System.out.println("Новые Сообщений Отсутствует.");
        } else {
            System.out.println(nachricht.toString());
        }

        this.empfangen.add(nachricht);
    }

    public void empfangeSms3(Nachricht sms) {
        this.empfangen.add(sms);
    }

    public void sendeSms(String empfaenger, String inhalt) {
        Nachricht nachricht = new Nachricht(this.rufnummer, inhalt, empfaenger);
        this.gesendet.add(nachricht);
        this.provider.addNachricht(nachricht);
        this.provider.finde(empfaenger).empfangeSms3(nachricht);
    }

    public void sendeSms(SmsHandy empfaenger, String inhalt) {
        Nachricht sms = new Nachricht(this.rufnummer, inhalt, empfaenger.gibRufnummerZurueck());
        if (this.rufnummer != empfaenger.gibRufnummerZurueck()) {
            this.gesendet.add(sms);
            empfaenger.empfangeSms3(sms);
            System.out.println("Сообщение отправлено.");
        }

    }

    public void wechselProvider(Provider provider) {
        this.provider = provider;
        System.out.println("Вы успешно сменили оператора на  \n" + provider.gibProviderName());
    }

    public String gibRufnummerZurueck() {
        return this.rufnummer;
    }

    public void zeigeRufnummer() {
        System.out.println(this.rufnummer);
    }

    public void listeEmpfangeneSms() {
        if (this.empfangen.size() == 0) {
            System.out.println("******************************** ");
            System.out.println("Список принятых сообщений пуст.");
            System.out.println("********************************");
        } else {
            System.out.println("Список принятых сообщений :");
            Iterator var1 = this.empfangen.iterator();

            while(var1.hasNext()) {
                Nachricht nachricht = (Nachricht)var1.next();
                System.out.println(nachricht);
            }

            System.out.println();
        }

    }

    public void listeGesendeteSms() {
        if (this.gesendet.size() == 0) {
            System.out.println("******************************** ");
            System.out.println("Список отправленных сообщений пуст.");
            System.out.println("********************************");
        } else {
            System.out.println("Список отправленных сообщений ");
            Iterator var1 = this.gesendet.iterator();

            while(var1.hasNext()) {
                Nachricht nachricht = (Nachricht)var1.next();
                System.out.println(nachricht);
            }

            System.out.println();
        }

    }
}



