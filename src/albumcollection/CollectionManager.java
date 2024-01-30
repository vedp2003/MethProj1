package albumcollection;

import java.util.Scanner;
public class CollectionManager {
    private Collection collection;
    private Scanner scanner;

    public CollectionManager(){
        collection = new Collection();
        scanner = new Scanner(System.in);
    }
    public void run(){
        System.out.println("Collection Manager is up running.");
        String inputStr;
        while(true){
            inputStr = scanner.nextLine().trim();
            //CONFIRM - what does program do when you enter without typing anything, currently its doing nothing

            while(inputStr.isEmpty()) {
                inputStr = scanner.nextLine();
            }
            if("Q".equals(inputStr)){
                System.out.println("Collection Manager terminated.");
                break;
            }
            processInputs(inputStr);
        }
    }

    private void processInputs(String input){
        String[] strSplit = input.split(",");


        if(strSplit.length == 0){
            System.out.println("Invalid command!");
            return;
        }


        String commandName = strSplit[0].trim();
        switch (commandName){
            case "A":
                addAlbum(strSplit);
                break;
            case "D":
                removeAlbum(strSplit);
                break;
            case "R":
                rateAlbum(strSplit);
                break;
            case "PD":
                collection.printByDate();
                break;
            case "PG":
                collection.printByGenre();
                break;
            case "PR":
                collection.printByRating();
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }

    private void addAlbum(String[] parts) {
        // Example input: A,Fearless,Taylor Swift,12/13/1989,pop,11/8/2008
        if (parts.length < 6) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[1].trim();
        String artistName = parts[2].trim();
        Date artistDob = new Date(parts[3].trim());
        if (!artistDob.isValid() || artistDob.isFutureDate() || artistDob.isBefore1900()) {
            System.out.println("Artist DOB: " + artistDob + " is invalid.");
            return;
        }
        Genre genre;
        try {
            genre = Genre.valueOf(parts[4].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            genre = Genre.UNKNOWN;
        }
        Date releaseDate = new Date(parts[5].trim());
        if (!releaseDate.isValid() || releaseDate.isFutureDate() || releaseDate.isBefore1900()) {
            System.out.println("Date Released: " + releaseDate + " is invalid.");
            return;
        }
        Album newAlbum = new Album(title, new Artist(artistName, artistDob), genre, releaseDate);
        if (collection.contains(newAlbum)) {
            System.out.println(title + "(" + artistName + ":" + artistDob + ") is already in the collection.");
        } else {
            collection.add(newAlbum);
            System.out.println(title + "(" + artistName + ":" + artistDob + ") added to the collection.");
        }
    }

    private void removeAlbum(String[] parts) {
        // Example input: D,Blue,April,1/11/2015
        if (parts.length < 3) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[1].trim();
        String artistName = parts[2].trim();

        /*
        if (!collection.remove(new Album(title, new Artist(artistName, null), null, null))) {
            System.out.println(newAlbum + " is not in the collection");
        } else {
            System.out.println(newAlbum + " removed from the collection.");
        }
         */


        //this doesnt even remove the Album - CHECK METHOD
    }

    private void rateAlbum(String[] parts) {
        // Example input: R,Fearless,Taylor Swift,12/13/1989,5
        if (parts.length < 5) {
            System.out.println("Invalid command!");
            return;
        }
        String title = parts[1].trim();
        String artistName = parts[2].trim();
        Date artistDob = new Date(parts[3].trim());
        int rating;
        try {
            rating = Integer.parseInt(parts[4].trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
            return;
        }
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating, rating scale is 1 to 5.");
            return;
        }
        collection.rate(new Album(title, new Artist(artistName, artistDob), null, null), rating);
    }

    public static void main(String[] args){
        CollectionManager manager = new CollectionManager();
        manager.run();
    }

}
