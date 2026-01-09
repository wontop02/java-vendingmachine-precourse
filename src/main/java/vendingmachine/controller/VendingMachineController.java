package vendingmachine.controller;

import vendingmachine.domain.Coin;
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
}
