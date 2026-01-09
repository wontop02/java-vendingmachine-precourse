package vendingmachine.service;

import vendingmachine.domain.VendingMachine;

public class VendingMachineService {
    public VendingMachine makeVendingMachine(String input) {
        return new VendingMachine(Integer.parseInt(input));
    }
}
