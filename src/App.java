import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {

        int initialTime;

        String sTime = "07/10/21, 12:25 - ";
        
        ZonedDateTime localDate = LocalDateTime.parse(sTime, DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm - ")).atZone(ZoneId.of("Europe/Lisbon"));
        initialTime= (int)(localDate.toInstant().toEpochMilli()/1000);



        System.out.print("Starting");Thread.sleep(100);System.out.print(".");Thread.sleep(200);System.out.print(".");Thread.sleep(400);System.out.println(".");

        

        String mensagem="05/01/22, 16:41 - +351 964 478 110: Vem matlab: :para.:-:::.::..::::: algum- exame?";
        // String mensagem="07/10/21, 12:44 - ‎+351 942 817 573 aderiu através da ligação de convite deste grupo";
        // String mensagem="07/10/21, 18:06 - Joana LEI: Quanto n faço ideia";

        Message mess= new Message(mensagem, initialTime);

        print("InitialTime = "+initialTime);
        print("MessageTime = "+mess.getTime(initialTime));
        print("Message = "+mensagem);
        print("Message = "+mess.getMessage());
        print("Length = "+mess.getLentgh());
        print("Wordcount = "+mess.getWordCount());
        print("Older than = "+mess.olderThan(1641900000,initialTime));
        print("Sender = "+mess.getSender());

    }

        public static void print(String str){
            System.out.println(str);
        }
}
