import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private String text;
    private int time;
    private String sender;
    private boolean special;

    Message(String log, int initialTime){
        // 05/01/22, 14:56 - 
        // 07/10/21, 18:06 - Joana LEI: Quanto n faço ideia

        // String[] split=log.split("\\d\\d/\\d\\d/\\d\\d, \\d\\d:\\d\\d -\\s.+:\\s");

        try{
            String[] split=log.split(":");                                      //separa o texto dos restantes componentes

            String tText=split[2].replaceFirst(" ", "");                        //copia a mensagem ou a sua primeira parte

            for (int i = 3 ; i !=split.length;i++ ){ tText+=":"+split[i]; }     //copia as restantes partes da mensagem, se existirem
            text=tText;                                                         //passa o resultado para a variavel mensagem

            split=log.split(text)[0].split("[-]");                              //copia para split tudo o que não é a mensagem e separa o tempo do utilizador

            sender=split[1].replace(":", "").replaceFirst(" ","");              //remove caracteres extra ao utilizador
            sender=enhancedSenderName(sender);                                  //remove os espaços se o identificador for um numero
            String sTime=split[0];

            ZonedDateTime localDate = LocalDateTime.parse(sTime, DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm ")).atZone(ZoneId.of("Europe/Lisbon"));
            time = (int)(localDate.toInstant().toEpochMilli()/1000) - initialTime;
            special=false;
        } catch (Exception e) {
            special=true;
            text=log;
            sender="WHATSAPP";
            ZonedDateTime localDate = LocalDateTime.parse(log.split("-")[0], DateTimeFormatter.ofPattern("dd/MM/yy, HH:mm ")).atZone(ZoneId.of("Europe/Lisbon"));
            time = (int)(localDate.toInstant().toEpochMilli()/1000) - initialTime;
        }

    }


    private String enhancedSenderName(String sender){
        String[] length=sender.split("[^\\d\\+ ]");
        if (length.length==1){return sender.replace(" ", "");}
        return sender;
    }

    public long getTime(int initialTime){
        return initialTime + time;
    }

    public String getMessage(){
        return text;
    }

    public int getLentgh(){
        return text.length();
    }

    public int getWordCount(){
        return text.split(" ").length;
    }

    public boolean olderThan(int time, int initialTime){
        return this.time+initialTime<time;
    }

    public String getSender(){
        return sender;
    }

    public boolean isSpecial(){
        return special;
    }
}
