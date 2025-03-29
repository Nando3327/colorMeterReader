import Foundation
import CoreBluetooth

@objc public class ReaderInterface: NSObject, CBCentralManagerDelegate, CBPeripheralManagerDelegate {
    
    
    var centralManager: CBCentralManager!
    var peripheralManager: CBPeripheralManager!
    var discoveredPeripheral: CBPeripheral?
    var pairedDevices: Array<PairedDevices> = []
    var peripherals: [CBPeripheral] = []
    
    
    var connectedPeripheral: CBPeripheral?
    func connect(address: String) {
        var peripheralDevice: CBPeripheral? = nil;
        peripherals.forEach { peripheral in
            if((peripheral.identifier.uuidString) == address) {
                peripheralDevice = peripheral;
            }
        }
        return centralManager.connect(peripheralDevice!)
     }
    
    
    
    func disconnect(address: String) {
        var peripheralDevice: CBPeripheral? = nil;
        peripherals.forEach { peripheral in
            if((peripheral.identifier.uuidString) == address) {
                peripheralDevice = peripheral;
            }
        }
        centralManager.cancelPeripheralConnection(peripheralDevice!)
    }
    
    
    // In CBCentralManagerDelegate class/extension
    public func centralManager(_ central: CBCentralManager, didDisconnectPeripheral peripheral: CBPeripheral, error: Error?) {
        if let error = error {
            // Handle error
            return
        }
        // Successfully disconnected
    }
    

    // In CBCentralManagerDelegate class/extension
    public func centralManager(_ central: CBCentralManager, didConnect peripheral: CBPeripheral) {
        // Successfully connected. Store reference to peripheral if not already done.
        self.connectedPeripheral = peripheral
    }
     
    public func centralManager(_ central: CBCentralManager, didFailToConnect peripheral: CBPeripheral, error: Error?) {
        print(error)
        // Handle error
    }
    
    
    public func centralManagerDidUpdateState(_ central: CBCentralManager) {
        if central.state == .poweredOn {
            centralManager.scanForPeripherals(withServices: nil, options: nil)
        } else {
                    // Handle Bluetooth not available or powered off
        }
    }
    
    public func centralManager(_ central: CBCentralManager, didDiscover peripheral: CBPeripheral, advertisementData: [String : Any], rssi: NSNumber) {
        if !peripherals.contains(peripheral) {
            peripherals.append(peripheral)
        }
    }
    
    public func peripheralManagerDidUpdateState(_ peripheral: CBPeripheralManager) {
        
    }
    
    
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
    public func listPairedDevices() -> [PairedDevices] {
        centralManager = CBCentralManager(delegate: self, queue: nil)
        peripheralManager = CBPeripheralManager(delegate: self, queue: nil)
        do {
            sleep(4)
        }
        if(!peripherals.isEmpty) {
            peripherals.forEach { peripheral in
                var obj: PairedDevices;
                obj = .init(macAddress: peripheral.identifier.uuidString, name: peripheral.name ?? peripheral.identifier.uuidString, batteryLevel: 10, batteryLevelString: "10%", status: "asss", whiteCalibration: true, blackCalibration: false)
                pairedDevices.append(obj)
            }
        }
        return pairedDevices;
    }
}
