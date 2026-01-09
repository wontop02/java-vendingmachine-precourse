package vendingmachine.controller;

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
        int amount = readAmount();
    }

    public int readAmount() {
        while (true) {
            try {
                String input = inputView.readAmount();
                InputValidator.validateAmount(input);
                return Integer.parseInt(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
