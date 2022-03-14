import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ListaZakupow {
    
    private HashMap<String,Integer> listaZakupow=new HashMap<>();
    private HashMap<String,Integer> lodowka=new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        ListaZakupow wybor=new ListaZakupow();

        System.out.println("Jeżeli chcesz tylko ustawić lodówke wpisz 0, jeżeli chcesz uzupełnić tylko liste wpisz 1,jeżeli chcesz wykonać obie rzeczy wpisz 2: ");
        int opcja=Integer.parseInt(reader.readLine());
        wybor.options(opcja);

        

    }
    
        public void options(Integer opcja) throws IOException{
        if(opcja==0)
            setLodowka(lodowka);
        if(opcja==1)
            setListaZakupow(listaZakupow);
        if(opcja==2) {
            setLodowka(lodowka);
            setListaZakupow(listaZakupow);
        }

    }


    public static void addProdukt(String produkt, Integer ilosc, HashMap<String,Integer> lista){
        lista.put(produkt,ilosc);
    }


    public static void setLodowka(HashMap<String,Integer> stanLodowki) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("Wpisz nazwe produktu, który masz w lodówce: ");
            String produkt= reader.readLine();
            System.out.println("Wpisz ile sztuk lub ile gramów produktu posiadasz w lodówce: ");
            Integer ilosc=Integer.parseInt(reader.readLine());
            if(ilosc==null)
                ilosc=0;
            addProdukt(produkt,ilosc,stanLodowki);
            System.out.println(produkt +" został dodany do twojej lodówki, aby zakończyć dodawania wpisz koniec, jeśli chcesz dalej dodawać wpisz kolejny");
            String czyJuz= reader.readLine();
            if(czyJuz.equals("koniec"))
                break;
        }
    }


    public static void setListaZakupow(HashMap<String,Integer> listaZakupow) throws IOException{
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("Wpisz nazwe produktu, który chcesz dodać do listy zakupów: ");
            String produkt= reader.readLine();

            System.out.println("Wpisz ile sztuk lub ile gramów produktu chcesz dodać do listy: ");
            Integer ilosc=Integer.parseInt(reader.readLine());
            if(ilosc==null)
                ilosc=0;
            addProdukt(produkt,ilosc,listaZakupow);
            System.out.println(produkt +" został dodany do listy zakupów, aby zakończyć dodawania wpisz koniec, jeśli chcesz coś jeszcze dodać wpisz dodaj");
            String czyJuz= reader.readLine();
            if(czyJuz.equals("koniec"))
                break;
        }
    }

}
