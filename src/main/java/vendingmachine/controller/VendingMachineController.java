package vendingmachine.controller;

import java.util.Map;
import vendingmachine.domain.Coin;
import vendingmachine.domain.InputAmount;
import vendingmachine.domain.VendingMachine;
import vendingmachine.service.VendingMachineService;
import vendingmachine.util.InputValidator;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private final InputView inputView;
    private final OutputView outputView;
    private final VendingMachineService vendingMachineService;

    public VendingMachineController(InputView inputView, OutputView outputView,
                                    VendingMachineService vendingMachineService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.vendingMachineService = vendingMachineService;
    }

    public void run() {
        VendingMachine vendingMachine = makeVendingMachine();
        printCoin(vendingMachine);
        addProduct();
        InputAmount inputAmount = makeInputAmount();
        buyProduct(inputAmount);
        printResult(inputAmount, vendingMachine);
    }

    public VendingMachine makeVendingMachine() {
        while (true) {
            try {
                String input = inputView.readAmount();
                InputValidator.validateAmount(input);
                return vendingMachineService.makeVendingMachine(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public void printCoin(VendingMachine vendingMachine) {
        outputView.printCoinStart();
        for (Coin coin : Coin.values()) {
            outputView.printCoin(coin.getAmount(), vendingMachine.getCoinCount(coin));
        }
    }

    public void addProduct() {
        while (true) {
            try {
                String input = inputView.readProducts();
                InputValidator.validateProducts(input);
                vendingMachineService.addProducts(input);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public InputAmount makeInputAmount() {
        while (true) {
            try {
                String input = inputView.readInputAmount();
                InputValidator.validateInputAmount(input);
                return vendingMachineService.makeInputAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public void buyProduct(InputAmount inputAmount) {
        while (!vendingMachineService.isEnd(inputAmount)) {
            try {
                outputView.printInputAmount(inputAmount.getAmount());
                String input = inputView.readBuyProduct();
                vendingMachineService.buyProduct(input, inputAmount);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    public void printResult(InputAmount inputAmount, VendingMachine vendingMachine) {
        outputView.printInputAmount(inputAmount.getAmount());
        outputView.printChangeStart();
        Map<Coin, Integer> result = vendingMachine.calculateChange(inputAmount.getAmount());
        if (inputAmount.getAmount() <= 0) {
            return;
        }
        for (Coin coin : Coin.values()) {
            if (result.getOrDefault(coin, 0) <= 0) {
                continue;
            }
            outputView.printCoin(coin.getAmount(), result.get(coin));
        }
    }
}
