import java.util.Scanner;

interface IPaymentStrategy {
    void pay(double amount);
}


class CreditCardPaymentStrategy implements IPaymentStrategy {
    private String cardNumber;

    public CreditCardPaymentStrategy(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " через банковскую карту: " + cardNumber);
    }
}


class PayPalPaymentStrategy implements IPaymentStrategy {
    private String email;

    public PayPalPaymentStrategy(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " через PayPal аккаунт: " + email);
    }
}


class CryptoPaymentStrategy implements IPaymentStrategy {
    private String walletAddress;

    public CryptoPaymentStrategy(String walletAddress) {
        this.walletAddress = walletAddress;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Оплата " + amount + " криптовалютой на кошелек: " + walletAddress);
    }
}


class PaymentContext {
    private IPaymentStrategy paymentStrategy;


    public void setPaymentStrategy(IPaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }


    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Стратегия оплаты не выбрана!");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}







public class Main {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();
        Scanner scanner = new Scanner(System.in);

        boolean vibor = true;
        while (vibor) {
            System.out.println("Выберите способ оплаты: 1 - Банковская карта, 2 - PayPal, 3 - Криптовалюта,4 - прекратить");
            int choice = scanner.nextInt();


                switch (choice) {
                    case 1:
                        System.out.println("Введите номер карты:");
                        String cardNumber = scanner.next();
                        context.setPaymentStrategy(new CreditCardPaymentStrategy(cardNumber));
                        break;
                    case 2:
                        System.out.println("Введите ваш PayPal email:");
                        String email = scanner.next();
                        context.setPaymentStrategy(new PayPalPaymentStrategy(email));
                        break;
                    case 3:
                        System.out.println("Введите адрес вашего крипто-кошелька:");
                        String walletAddress = scanner.next();
                        context.setPaymentStrategy(new CryptoPaymentStrategy(walletAddress));
                        break;
                    case 4:
                        vibor = false;
                        System.exit(0);
                    default:
                        System.out.println("Неверный выбор!");
                        return;
                }
            System.out.println("Введите сумму для оплаты:");
            double amount = scanner.nextDouble();

                context.executePayment(amount);

        }
    }
}


