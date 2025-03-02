import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.net.MalformedURLException;

public class Client {
    public static void main(String[] args) {
        try {
            // Recherche de l'objet distant
            Convertisseur hello = (Convertisseur) Naming.lookup("rmi://localhost:3500/toto");
            Scanner scanner = new Scanner(System.in);
            System.out.println("entrer votre montant :");
            double montant = Double.parseDouble(scanner.nextLine());
            while(true){
                System.out.println("entrer le mode de conversion :");
                String modeconversion = scanner.nextLine();
                switch (modeconversion) {
                    case "dollar vers mad":
                        double dollattomad = hello.DollarToMad(montant);
                        System.out.println("voici le resultat de conversion :"+dollattomad);
                        break;
                    case "mad vers dollar":
                        double madtodollar = hello.MadToDollar(montant);
                        System.out.println("voici le resultat de conversion :"+madtodollar);
                        break;
                    default:
                        continue;
                }
            }
            
        } catch (MalformedURLException e) {
            System.err.println("L'URL du serveur RMI est mal formée : " + e.getMessage());
        } catch (NotBoundException e) {
            System.err.println("Aucun objet distant trouvé à l'adresse spécifiée : " + e.getMessage());
        } catch (RemoteException e) {
            System.err.println("Problème de communication RMI : " + e.getMessage());
        }
    }
}
