package cali.eventkalender.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/** Singleton - för uppgiften temporär lösning då tanken av appen enbart är att visa och inte ha addmetoder osv. Ska egentligen konsumera
 * en webbservice där data från databasen skickas.*/
public class NationLab implements Parcelable{
    private static NationLab sNationLab;

    private List<Nation> mNations;

    public static NationLab get(Context context) {
        if (sNationLab == null) {
            sNationLab = new NationLab(context);
        }
        return sNationLab;
    }

   /** Ska egentligen komma från vår externa databas men mock-data skapas här nu istället*/
    private NationLab(Context context) {

        mNations = new ArrayList<>();
        Nation malmo_nation = new Nation("Malmö Nation", "Malmö Nation karaktäriseras av de cirka 90 tjänstemän och 500 funktionärer som alla varje termin är med och skapar den speciella stämningen och sammanhållning som vi är mycket stolta över på Malmö Nation. Våra tre ledord är: Tradition, Gemenskap & Glädje. Vi har 3000 medlemmar i dagsläget vilket gör oss till Lunds näst största nation. Men vi är absolut störst när det kommer till att arrangera fester. Vi har haft uppträdanden från artister såsom: Avicii, Laidback Luke, Kygo, Nause, Joachim Garraud, Sean Kingston och många fler!", "Östra Vallgatan 51, 223 61 Lund", 55.704456, 13.201184 ); mNations.add(malmo_nation);
        Nation lunds_nation = new Nation("Lunds Nation", "Varmt välkommen till den bästa tiden i ditt liv! I nationen kan du finna en tillflyktsort nnr tentaångesten gör sig påmind, CSN har tagit slut eller om du har lite hemlängtan. Här träffar du en härlig blandning av folk från alla olika fakulteter som känner precis som du och vill göra något roligt vid sidan av studierna. Den tid som ligger framför dig kanske verkar oändligt länge, men utan att bli högtravande vill vi säga ta vara på den här tiden och gör det mesta av den.", "Agardhsgatan 1, 223 51 Lund",55.703559, 13.199829 ); mNations.add(lunds_nation);
        Nation goteborgs_nation = new Nation("Göteborgs Nation", "Göteborgs nation kan erbjuda dig en stark gemenskap och en underbar studietid som gör dina år i Lund till de bästa någonsin! Utöver våra löpande verksamheter arrangerar nationen årliga evenemang såsom Gustav II Adolf bal som är Nordens största studentbal, Julfest, Första maj-firande, Räkfest och Novischfester. Göteborgs nation karakteriseras av att vara en nation med fart och glädje i alla sina verksamheter. Vi är den lilla nationen med det stora hjärtat!", "Östra Vallgatan 47, 223 61 Lund", 55.704069, 13.201113); mNations.add(goteborgs_nation);
        Nation hallands_nation= new Nation("Hallands Nation", "Hallands Nation är nationen för alla! Här blandas studenter från alla fakulteter i en härlig röra. Vårt hus är alltid fullt av liv och det är lätt att komma in i gemenskapen och lära sig vårt motto; De é kärlek. Hallands Nation har runt 70 förmän som arbetar för att skapa en trevlig atmosfär och för att du som medlem skall få ut så mycket som möjligt av din studietid i Lund. Onsdagspuben är ett perfekt tillfälle att glömma bort skolans stress med dina kursare och utmanas istället av ett klurigt pubquiz eller att bara dyka upp på fredagspuben som ett härligt avslut på veckan.", "Thomanders väg 1, 224 65 Lund", 55.705405,13.211135 ); mNations.add(hallands_nation);
        Nation blekingska_nation = new Nation("Blekingska Nation", "Glädje, Gemenskap, Musik är våra ledord. Blekingska är nationen där vi i mer än tjugo år erbjudit en scen där många i dag stora band spelat. Vad sägs om Silvana Imam, Alphaville, Broder Daniel och The Cardigans? Vår legendariska klubb Indigo har erbjudit det bästa inom indie i över två decennier men i några år har vi även drivit electroklubben Heartbeats som spelar fantastisk electronica snarare än mainstream house.", "Måsvägen 11, 227 33 Lund", 55.708546, 13.169827);mNations.add(blekingska_nation);
        Nation kristianstad_nation = new Nation("Kristianstad Nation", "Kristianstads Nation är din länk mellan fritid och framtid! Vi är en relativt liten nation men med ett garanterat stort hjärta. Vi har ca 1800 inskrivna studenter, och det finns alltid plats för en till. Det är lätt att snabbt komma in i vårt gäng och att få underbara minnen för livet genom att jobba på någon av våra aktiviteter. Här kan du bland annat dansa i dimman på Klubb Huset, äta gott till studentpris på både lunch, pub och nattcafé samt se Lunds bästa nationsspääx.", "Tornavägen 7, 223 63 Lund", 55.709028, 13.207512);mNations.add(kristianstad_nation);
        Nation ostgota_nation = new Nation("Östgöta Nation", "Östgöta Nation har funnits ända sedan 1668 och är din port in i studentlivet. Nationen är en mötesplats för alla Lunds studenter och genomsyras av glädje, engagemang och gemenskap! Vi har ett brett utbud i vår verksamhet och erbjuder allt ifrån matlagning och pub- och klubbverksamhet av hög kvalitet till foto, teknik, idrott och kultur.", "Adelgatan 4, 223 50 Lund", 55.704519, 13.198149);mNations.add(ostgota_nation);
        Nation helsingkrona_nation = new Nation("Helsingkrona Nation", "Helsingkrona Nation karaktäriseras av ett brett utbud av utskott och aktiviteter med en öppen och välkomnande atmosfär med goda möjligheter att utveckla sina egna idéer!Som inskriven i Helsingkrona Nation är du välkommen att engagera dig i våra utskott! Sexmästeriet, Helsingkronaspexet, Idrott, NATU, IT, Redaktionen, Internationella sekretariatet, Foto, Gillestugeriet och Novischeriet.", "Tornavägen 3, 223 63 Lund", 55.709540, 13.208144);mNations.add(helsingkrona_nation);
        Nation sydskanska_nation = new Nation("Sydskånska Nation", "Nationen där musiken spelar roll! Med en etablerad musiktradition förgyller SSK ditt Lundaliv med det bästa från musikvärlden! Varje helg smäller vi upp dörrarna till klubbar där du kan dansa dig svettig till grymma DJ:s, liveband och feta MC:s. De fyra klubbar som regelbundet huserar på nationen är hiphop- och urbanklubben Svartklubben som kör varannan vecka och Electrified där källaren fylls med vibrerande elektroniska toner.", "Tornavägen 5, 223 63 Lund", 55.709431, 13.207031);mNations.add(sydskanska_nation);
        Nation wermlands_nation= new Nation("Wermlands Nation", "Wermlands erbjuder en plats där du kan känna dig som hemma och snabbt bli en del av gemenskapen. Som Sveriges första KRAV-certifierade studentförening kan vi erbjuda vällagad ekologisk mat och ett brett ölsortiment till studentvänliga priser varje onsdag och lördag. Vi har då våra pubar American pub, Greenway Pub och Pub Aperitivo öppna.", "Stora Tvärgatan 13, 223 53 Lund", 55.699450, 13.193715);mNations.add(wermlands_nation);
        Nation kalmar_nation = new Nation("Kalmar Nation", "Kalmar nation är nationen med Lunds högsta mysighetsfaktor. Beläget mitt i stadens bankande studenthjärta blir vår lummiga trädgård under novischperioden din perfekta port in i studielivet. Att Kalmar inte hör till de största nationerna gör oss till ett tight gäng där du lätt hittar vänner för livet!Varje tisdag och fredag håller våra populära pubar öppet. Pub Ölkällaren ger besökaren ett härligt barhäng för den som tycker lill-lördag är för långt borta.", "Biskopsgatan 12, 223 62 Lund", 55.706484, 13.198410);mNations.add(kalmar_nation);
        Nation vastgota_nation = new Nation("Västgöta Nation", "Varmt välkommen till det sprudlande studentlivet i Lund! Livet i Lund är så mycket mer än bara studier så därför är det viktigt att välja en nation där man trivs. Vi på Västgöta Nation är kända för den härliga gemenskapen som kan liknas vid en enda stor familj. Om du vill vara en del av denna familj har du kommit alldeles rätt!", "Tornavägen 17-19, 223 63 Lund", 55.705762, 13.210100);mNations.add(vastgota_nation);
        Event casa = new Event("Casanova","När solen skiner på oss allihopa är det den där tiden på året igen. Vi kickar igång våra ben, och svettas till timmen blir sen. Och bara bönar ber om den där melodin som är så ren. Vi alla älskar CASANOVA och CASANOVA älskar er, välkomna äro ni ner för att svalka av er till musiken av hög kvalité tillhörandes så klart Trap och Rave genrén!", "Klubb" ,malmo_nation);
        Event gbg = new Event("Kajplats","Snart är veckan med skolarbete och tentaplugg över, och man kan snart vara ute och njuta av solen! Så häng i Botaniska, grilla i Stadsparken eller bada i Lomma och kom sedan till Kajplats och dansa in sommaren med oss! Som vanligt kommer vi att välkomna er med fett chill musik, skönt folk, härligt krispiga drinkar och kall bira - i sann fredagsanda! Inte helt otippat står båda dansgolven öppna och våra kära DJ-förmän styr musiken. Det kan inte bli annat än dunder!", "Klubb", goteborgs_nation);
        Event skybar = new Event("Skybar","Bra Evenemang","Bar", goteborgs_nation);
        Event vgs = new Event("Freda' Kök och Bar","Kvällens huvudrätter är:\n" +
                "- Ankbröst med gräddkokt spetskål, krämig salviasås, krispigt stekta svartrötter och rostad brysselkål\n" +
                "- Fröpanerad kummel med rostad palsternacka, beurre blanc, och en grön ärtpesto toppat med rödbetschips\n" +
                "- Marinerad och friterad tofu med puré på rostade morötter, smörslungad kålrabbi och en uppfriskande limesås\n" +
                "\n" +
                "Till dessert serveras en kladdig brownietårta med havssalt och grädde\n","Restaurang", vastgota_nation);

        for(int i = 0; i<5; i++){
            Event event = new Event("test" + i,"Bra Evenemang" + i,"SpamEvent", malmo_nation);
        }

        for(int i = 0; i<5; i++){
            Event event = new Event("test" + (i+6),"Roligt Evenemang" + i,"SpamEvent", goteborgs_nation);
        }

    }

    public List<Nation> getNations() {
        return mNations;
    }

    public Nation getNation(UUID id) {
        for (Nation nation : mNations) {
            if (nation.getId().equals(id)) {
                return nation;
            }
        }
        return null;
    }

    public List<Event> findAllEvents(){
        List<Event> events = new ArrayList<>();
        for(Nation n : mNations){
            for(int i = 0; i< n.getEvents().size(); i++){

                // För tillfället bortkommenterad då alla evenemang försvinner då det är satta till dagens datum och tid.
            //    Date c = Calendar.getInstance().getTime();
           //     if(c.getTime() <= n.getEvents().get(i).getDate().getTime())
                events.add(n.getEvents().get(i));
            }
        }
        Collections.sort(events);
        return events;
    }

    public NationLab(Parcel in) {

        if (in.readByte() == 0x01) {
            mNations = new ArrayList<Nation>();
            in.readList(mNations, Nation.class.getClassLoader());
        } else {
            mNations = null;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (mNations == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(mNations);
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public NationLab createFromParcel(Parcel in) {
            return new NationLab(in);
        }

        public NationLab[] newArray(int size) {
            return new NationLab[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

}
