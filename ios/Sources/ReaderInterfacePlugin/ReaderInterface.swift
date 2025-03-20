import Foundation
import CoreBluetooth

@objc public class ReaderInterface: NSObject, CBCentralManagerDelegate, CBPeripheralManagerDelegate {
    
    
    var centralManager: CBCentralManager!
    var peripheralManager: CBPeripheralManager!
    var discoveredPeripheral: CBPeripheral?
    var pairedDevices: Array<PairedDevices> = []
    var peripherals: [CBPeripheral] = []
    
    
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
                if((peripheral.name) != nil) {
                    var obj: PairedDevices;
                    obj = .init(macAddress: peripheral.identifier.uuidString, name: peripheral.name ?? "Unknown Device", batteryLevel: 10, batteryLevelString: "10%", status: "asss", whiteCalibration: true, blackCalibration: false)
                    pairedDevices.append(obj)
                }
            }
        }
        return pairedDevices;
    }
}
