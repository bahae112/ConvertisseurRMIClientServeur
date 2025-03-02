import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;

// Interface distante
interface InterfaceDeHello extends Remote {
    void sayIt() throws RemoteException;
}

// Implémentation de l'interface distante
class Hello extends UnicastRemoteObject implements InterfaceDeHello {
    public Hello() throws RemoteException {
        super(); // Appel au constructeur de UnicastRemoteObject
    }

    @Override
    public void sayIt() throws RemoteException {
        System.out.println("Hello, I am the first client.");
    }
}
interface Convertisseur extends Remote{
    public double DollarToMad(double montant) throws RemoteException;
    public double MadToDollar(double montant) throws RemoteException;
    public double Comission(double montant) throws RemoteException;
}
class ConvertisseurImpl extends UnicastRemoteObject implements Convertisseur{
    public ConvertisseurImpl() throws RemoteException{
        super();
    }
    @Override
    public double DollarToMad(double montant) throws RemoteException{
        double temp = montant * 9.95;
        return temp;
    }
    @Override
    public double MadToDollar(double montant) throws RemoteException{
        double temp = montant / 9.95;
        return temp;
    }
    @Override
    public double Comission(double montant) throws RemoteException{
        return 0;
    }
}

// Classe serveur
public class Server {
    public static void main(String[] args) {
        try {
            // Démarrer le registre RMI sur le port 3500
            LocateRegistry.createRegistry(3500);

            // Créer et publier l'objet distant
            ConvertisseurImpl objectLocal = new ConvertisseurImpl();
            Naming.rebind("rmi://localhost:3500/toto", objectLocal);

            System.out.println("Object published...");
        } catch (RemoteException e) {
            System.out.println("RemoteException: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
