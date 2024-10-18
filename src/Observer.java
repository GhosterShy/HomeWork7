import java.util.ArrayList;
import java.util.List;

interface IObserver {
    void update(String currency, double rate);
}

interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}


class CurrencyExchange implements ISubject {
    private List<IObserver> observers;
    private String currency;
    private double rate;

    public CurrencyExchange() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(currency, rate);
        }
    }

    public void setRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
        notifyObservers();
    }
}




class ScreenDisplay implements IObserver {
    private String name;

    public ScreenDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(String currency, double rate) {
        System.out.println(name + ": Обновленный курс " + currency + " = " + rate);
    }
}




class EmailNotification implements IObserver {
    private String email;

    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void update(String currency, double rate) {
        System.out.println("Отправлено письмо на " + email + ": курс " + currency + " изменился до " + rate);
    }
}




public class Observer {
    public static void main(String[] args) {
        CurrencyExchange currencyExchange = new CurrencyExchange();

        ScreenDisplay screen1 = new ScreenDisplay("Экран 1");
        ScreenDisplay screen2 = new ScreenDisplay("Экран 2");
        EmailNotification emailNotification = new EmailNotification("example@example.com");

        currencyExchange.registerObserver(screen1);
        currencyExchange.registerObserver(screen2);
        currencyExchange.registerObserver(emailNotification);


        currencyExchange.setRate("USD", 78.50);
        currencyExchange.setRate("EUR", 90.20);


        currencyExchange.removeObserver(screen2);
        currencyExchange.setRate("USD", 79.00);

    }
    
}
