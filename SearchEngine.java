import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

    ArrayList<String> stringCheese = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Hi there!!");
        } 
        else if (url.getPath().equals("/add")) {
            String[] args = url.getQuery().split("=");
            stringCheese.add(args[1]);
            return String.format("Added " + args[1] + "!");
        } 
        else if (url.getPath().contains("/search")) {
            String[] args = url.getQuery().split("=");
            for(int i = 0; i < stringCheese.size(); i ++){
                if(stringCheese.get(i).contains(args[1])){
                        return stringCheese.get(i);
                }
            }
        }
        else return String.format("404 Not Found!");
        return null;
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
        
    }
}


