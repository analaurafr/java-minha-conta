package com.betrybe.minhaconta.presentation;

import com.betrybe.minhaconta.business.EnergyAccount;
import com.ions.lightdealer.sdk.model.Address;
import com.ions.lightdealer.sdk.model.Client;
import com.ions.lightdealer.sdk.model.ElectronicDevice;
import com.ions.lightdealer.sdk.service.LightDealerApi;


/**
 * The type Application.
 */
public class Application {

  ConsoleUserInterface ui;
  LightDealerApi api;

  /**
   * Constructor that instantiates a new Application.
   */
  public Application(ConsoleUserInterface ui) {
    this.ui = ui;
    this.api = new LightDealerApi();
  }

  /**
   * Req. 4 – Creates CLI menu.
   */
  public void run() {
    String[]  menu = {
        "1 - Cadastrar Cliente",
        "2 - Cadastrar imóvel do cliente",
        "3 - Cadastrar dispositivos em imóvel",
        "4 - Estimar conta de imóvel",
        "5 - Otimizar uso de energia",
        "6 - Sair",
    };

    char option = '0';
    while (option != '6') {
      option = ui.inputMenuOption(menu);
      runOptionAction(option);
    }
  }

  /**
   * Req. 5 – Run menu options.
   */
  public void runOptionAction(char option) {
    switch (option) {
      case '1':
        this.registerClient();
        break;
      case '2':
        this.registerClientAddress();
        break;
      case '3':
        this.registerAddressDevices();
        break;
      case '4':
        this.estimateAddressBill();
        break;
      case '5':
        this.optimizeEnergyBill();
        break;
      case '6':
        this.ui.showMessage("Volte sempre!");
        break;
      default:
        this.ui.showMessage("Opção inválida!");
    }
  }

  /**
   * Req. 6 – Register client.
   */
  public void registerClient() {
    Client client = new Client();
    this.ui.fillClientData(client);
    this.api.addClient(client);
  }

  /**
   * Req. 7 – Register client address.
   */
  public void registerClientAddress() {
    String clientCpf = this.ui.inputClientCpf();
    Client client = this.api.findClient(clientCpf);
    if (client == null) {
      this.ui.showMessage("Pessoa cliente não encontrada!");
    } else {
      Address address = new Address();
      this.ui.fillAddressData(address);
      this.api.addAddressToClient(address, client);
    }
  }

  /**
   * Req. 8 – Register address devices.
   */
  public void registerAddressDevices() {
    String addressRegistration = this.ui.inputAddressRegistration();
    Address address = this.api.findAddress(addressRegistration);

    if (address == null) {
      this.ui.showMessage("Endereço não encontrado!");
    } else {
      int numberOfDevices = this.ui.inputNumberOfDevices();
      for (int i = 0; i < numberOfDevices; i++) {
        ElectronicDevice electronicDevice = new ElectronicDevice();
        this.ui.fillDeviceData(electronicDevice);
        this.api.addDeviceToAddress(electronicDevice, address);
      }
    }
  }

  /**
   * Req. 9 – Estimates the address energy bill.
   */
  public void estimateAddressBill() {
  }

  /**
   * Req. 10 – Optimizes the energy bill.
   */
  public void optimizeEnergyBill() {
  }

  /**
   * Req 10 - Aux. Method to display high consumptions devices.
   */
  public void suggestReducedUsage(EnergyAccount energyAccount) {
  }
}
