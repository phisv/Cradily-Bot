import net.dv8tion.jda.client.entities.Group;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import MethodsDL.ASCIIDL;
import java.util.*;

public class Main extends ListenerAdapter {

    Map<User,Boolean> mocked = new HashMap<User,Boolean>();
    Map<User,Boolean> catban = new HashMap<User,Boolean>();
    
    public static void main(String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        builder.setToken(System.getenv("CradilyDiscordToken"));
        builder.addEventListener(new Main());
        Game game = Game.playing("with Damian");
        builder.setGame(game);
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
//         if (event.getAuthor().isBot() && message.equals("Mashing Potatoes"))
//             mash(event);
        if (event.getAuthor().isBot())
             return;
        if(!event.getAuthor().getId().equals("475785719403642882") && !message.startsWith("c!") && mocked.containsKey(event.getAuthor()) && mocked.get(event.getAuthor()) == true)
//             event.getChannel().sendMessage(message).queue();
               rancap(event,message);
        else if (message.equals("c!ping"))
            event.getChannel().sendMessage("pong").queue();
        else if (message.equals("c!help"))
            help(event);
        else if (message.equals("c!shutdown"))
            event.getChannel().sendMessage("no u").queue();
        else if (message.substring(0, 6).equals("c!echo"))
            echo(event);
        else if (message.equals("c!cradily"))
            event.getChannel().sendMessage("cradamian").queue();
        else if (message.equals("c!whiscash"))
            event.getChannel().sendMessage(":yum:").queue();
        else if (message.equals("c!dogmeat"))
            event.getChannel().sendMessage("Nooo pls don't aboose me I'm coot doggo :frowning:").queue();
        else if (message.equals("c!evilpenguin"))
            event.getChannel().sendMessage("Get your stinky feet off of me!").queue();
        else if (message.equals("c!whoami"))
            event.getChannel().sendMessage("I'm " + event.getMember().getUser().getId()).queue();
//         else if (message.equals("c!mashedpotatoes"))
//             event.getChannel().sendMessage("Mashing Potatoes").queue();
        else if (message.substring(0, 7).equals("c!8ball"))
            shake(event, message.substring(8));
        else if (message.substring(0, 8).equals("c!expand"))
            expand(event, message.substring(9));
        else if (message.substring(0, 7).equals("c!ascii"))
            event.getChannel().sendMessage("```\n" + ASCIIDL.ASCII(message.substring(8), false) + "\n```").queue();
        else if (message.startsWith("c!mock"))
            mock(event);
        else if (message.startsWith("c!unmock"))
            unmock(event);
        else if (message.equals("c!myroles"))
            myroles(event);
        else if (message.startsWith("c!rancap"))
            rancap(event,message.substring(9));
        else if (message.startsWith("c!servercat") || message.startsWith("c!sc"))
            viewcat(event);
        else if (message.startsWith("c!feedcat"))
            feedcat(event,message.substring(10));
        else if (message.startsWith("c!petcat"))
            petcat(event);
        else if (message.startsWith("c!number"))
            number(event,message.substring(9));
    }

    private void help(MessageReceivedEvent event) {
        event.getChannel().sendMessage("```\n" +
                                            "Command List\n" +
                                            "c!help - Display this list\n" +
                                            "c!ping - Ping the bot\n" +
                                            "c!echo - Echo the user's input\n" +
                                            "\t(Optional parameter -h deletes original command)\n" +
                                            "c!8ball - Magic 8Ball\n" +
                                            "c!ascii - Display the message as ASCII art\n" +
                                            "c!expand - E x p a n d s input\n" +
                                            "c!whoami - Returns the author\n" +
                                            "c!mock <users> - Enable mocking of users\n" +
                                            "c!unmock <users> - Disables mocking (Requires role Cradily Master)\n" +
                                            "c!myroles - Displays your roles\n" +
                                            "c!rancap - rAndOmLy cApiTaLizEs yOuR mEsSagE\n" +
                                            "c!servercat / c!sc - View servercat (coot)\n" +
                                            "c!feedcat <food>- Feeds servercat\n" +
                                            "c!petcat - Pets servercat\n" +
                                            "c!shutdown - Shutdown this bot\n" +
                                            "```").queue();

    }

    private void echo(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();

        if (message.substring(7, 9).equals("-h")) {
            event.getMessage().delete().queue();
            rancap(event,message.substring(10));
        } else
            event.getChannel().sendMessage(message.substring(7)).queue();
    }

//     private void mash(MessageReceivedEvent event)
//     {
//         int i = 0;
//         String temp = "";
//         while(i < 40) //supposed to be i > 0
//         {
//             i++;
//             if(i%6 == 1)
//                 temp = ".";
//             else if(i%6 == 2)
//                 temp = "..";
//             else if(i%6 == 3)
//                 temp = "...";
//             else if(i%6 == 4)
//                 temp = "..";
//             else if(i%6 == 5)
//                 temp = ".";
//             else if(i%6 == 0)
//                 temp = "";
//             event.getMessage().editMessage("Mashing Potatoes" + temp).queue();
//         }
//     }
    private void shake(MessageReceivedEvent event, String question)
    {
        int ran = (int) (Math.random()*48);
        String response = "";
        if(ran%8 == 0)
            response = "Yes, definitely.";
        else if(ran%8 == 1)
            response = "There is a slight possibility.";
        else if(ran%8 == 2)
            response = "Impossible!";
        else if(ran%8 == 3)
            response = "Reply hazy. Feed me and try again.";
        else if(ran%8 == 4)
            response = "I'd say true.";
        else if(ran%8 == 5)
            response = "Doubt it.";
        else if(ran%8 == 6)
            response = "Very likely.";
        else if(ran%8 == 7)
            response = "Your answers are here: 1-800-273-8255.";
        event.getChannel().sendMessage("To your question: " + question + "\n" +
                                       ":crystal_ball: The All-Seeing Hooski says: " + response).queue();
    }

    private int hasher(String str)
    {
        int temp = 0;
        for(char c: str.toCharArray())
        {
            if((int)c == 32)
                continue;
            if(c >= 'A' && c <= 'Z')
                c = (char)((int)c + 32);
            temp += (int)c*31;
            temp = (temp*temp)%139;
        }
        //optionally implement randomness
        return temp;
    }
    

    private void expand(MessageReceivedEvent event, String str)
    {
        String response = "";
        for(char c: str.toCharArray())
        {
            response = response + c + " ";
        }
        event.getChannel().sendMessage(response).queue();
    }
    private boolean hasRole(Member mbr, String role)
    {
        for(Role r: mbr.getRoles())
            if(r.getName().equals(role))
                return true;
        return false;
    }
    private void mock(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        for(User usr: msg.getMentionedUsers())
        {
            if(usr.getId().equals("475785719403642882"))
            {
                event.getChannel().sendMessage("I won't mock myself!").queue();
                continue;
            }
            if(usr.isBot())
            {
                event.getChannel().sendMessage("I don't mock inferior bots!").queue();
                continue;
            }
            else
            {
                mocked.put(usr,true);
                event.getChannel().sendMessage("Cradily shall now mock <@" + usr.getId() + ">").queue();
            }
        }
    }
    private void unmock(MessageReceivedEvent event)
    {
        if(hasRole(event.getMember(),"Cradily Master") == false)
        {
            event.getChannel().sendMessage("You need the role *Cradily Master* to do this action!").queue();
            return;
        }
        Message msg = event.getMessage();
        for(User usr: msg.getMentionedUsers())
        {
            mocked.put(usr,false);
            event.getChannel().sendMessage("Lucky <@" + usr.getId() + ">, you're now spared from Cradily's mockery").queue();
        }
    }
    private void myroles(MessageReceivedEvent event)
    {
        for(Role r: event.getMember().getRoles())
            event.getChannel().sendMessage("Roles: " + r.getName()).queue();
    }
    private void rancap(MessageReceivedEvent event, String msg)
    {
        String result = "";
        msg = msg.toLowerCase();
        for(char c: msg.toCharArray())
        {
            int ran = (int) (Math.random()*50+1);
            if(ran%2 == 0 && c >= 'a' && c <= 'z')
                result += Character.toUpperCase(c);
            else if(ran%2 == 1 && c >= 'A' && c <= 'Z')
                result += Character.toLowerCase(c);
            else
                result += c;
        }
//         if(!event.getMessage().getContentRaw().startsWith("c!"))
        result += " <:thnking:475792129583742986>";
        event.getChannel().sendMessage(result).queue();
    }
    private void viewcat(MessageReceivedEvent event)
    {
        event.getChannel().sendMessage(":servercat:").queue();
        event.getChannel().sendMessage("adding happiness bar later").queue();
        event.getChannel().sendMessage("adding hungry bar later").queue();
    }
    private void feedcat (MessageReceivedEvent event, String msg)
    {
        if(catban.containsKey(event.getAuthor()) && catban.get(event.getAuthor()) == true)
        {
            event.getChannel().sendMessage("You're are not allowed to feed servercat :rage:").queue();
            return;
        }
        int ran = (int) (Math.random()*50+1);
        if(ran%3 == 0)
            event.getChannel().sendMessage("You just fed servercat some " + msg + "! :servercat:").queue();
        else if(ran%3 == 1)
            event.getChannel().sendMessage(event.getMessage().getAuthor().getName() + ", you know that servercat only wants to eat freshly peeled bread >:(").queue();
        else
            event.getChannel().sendMessage(event.getMessage().getAuthor().getName() + ", you horrible monster! servercat is allergic to " + msg).queue();
    }
    private void petcat(MessageReceivedEvent event)
    {
        if(catban.containsKey(event.getAuthor()) && catban.get(event.getAuthor()) == true)
            event.getChannel().sendMessage("You're not allowed to be within 15ft of servercat :rage:").queue();
        else
            event.getChannel().sendMessage("Yay! servercat just got petted!").queue();
    }
    //add in images
    
    private void number(MessageReceivedEvent event, String str)
    {
        int num = Integer.valueOf(str);
        boolean nice = true;
        if(num % 2 != 0)
            nice = false;
        if(str.indexOf("0") < 0)
            nice = false;
        if(num % 5 == 0)
            nice = true;
        if(num / 100 == 10 && (num % 100) % 11 != 0)
            nice = true;
        if(nice)
            event.getChannel().sendMessage(str + " is a nice number! :smile: ").queue();
        else
            event.getChannel().sendMessage(str + " is nasty number :nauseated_face:").queue();
    }
}
