package br.ufrn.imd.socket;

import org.alicebot.ab.*;

import java.io.File;

public class ChatBot {
    private static final boolean TRACE_MODE = false;
    static String botName = "super";

   public static String chatBotResponse(String question){

       String resourcesPath = getResourcesPath();

       try {

           MagicBooleans.trace_mode = TRACE_MODE;
           Bot bot = new Bot("super", resourcesPath);
           Chat chatSession = new Chat(bot);
           bot.brain.nodeStats();

           if ((question == null) || (question.length() < 1))
               return "BOT: Digite alguma coisa!!";
           else {

               String response = chatSession.multisentenceRespond(question);

               while (response.contains("&lt;"))
                   response = response.replace("&lt;", "<");
               while (response.contains("&gt;"))
                   response = response.replace("&gt;", ">");

               return "BOT:" + response;
           }
       }catch (Exception e) {
           return "BOT: Ocorreu um problema no meu sistema, desculpe :c";
       }
   }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        String resourcesPath = path + File.separator + "src" + File.separator + "resources";
        return resourcesPath;
    }

}