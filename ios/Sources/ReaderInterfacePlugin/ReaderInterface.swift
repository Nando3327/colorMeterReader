import Foundation
import CoreBluetooth
import UIKit
import ColorMeterKit
import RxSwift



public class PairedDevices {
    var macAddress = String()
    var name: String
    var batteryLevel: Int
    var batteryLevelString: String
    var status: String
    var whiteCalibration: Bool
    var blackCalibration: Bool

    init (macAddress: String, name: String, batteryLevel: Int, batteryLevelString: String, status: String, whiteCalibration: Bool, blackCalibration: Bool) {
        self.macAddress = macAddress
        self.name = name
        self.batteryLevel = batteryLevel
        self.batteryLevelString = batteryLevelString
        self.status = status
        self.whiteCalibration = whiteCalibration
        self.blackCalibration = blackCalibration
    }
}



@objc public class ReaderInterface: NSObject, CBCentralManagerDelegate, CBPeripheralManagerDelegate, CBPeripheralDelegate {
    
    
    var centralManager: CBCentralManager!
    var peripheralManager: CBPeripheralManager!
    var discoveredPeripheral: CBPeripheral?
    var pairedDevices: Array<PairedDevices> = []
    var peripherals: [CBPeripheral] = []
    var connectedPeripheral: CBPeripheral?
    var returnValue: ((_ value: Data)->())?
    
    
    var cm = CMKit()
    var disposable: Disposable?
    
    
    public func listPairedDevices() -> [PairedDevices] {
        pairedDevices = [];
        peripherals = [];
        var listedMacs: [String] = [];
        cm.startScan()
        disposable = cm.observeScanned()
            .subscribe(
                onNext: { [weak self] state in
                    if let peripheral = state.peripheral {
                        let filtered = listedMacs.filter { $0.elementsEqual(peripheral.identifier.uuidString) }
                        if(filtered.isEmpty && (peripheral.name) != nil) {
                            listedMacs.append(peripheral.identifier.uuidString);
                            self?.peripherals.append(peripheral)
                            var obj: PairedDevices;
                            obj = .init(macAddress: peripheral.identifier.uuidString, name: peripheral.name ?? peripheral.identifier.uuidString, batteryLevel: 10, batteryLevelString: "10%", status: "asss", whiteCalibration: true, blackCalibration: false);
                            self?.pairedDevices.append(obj)
                        }
                    }
                },
                onError: { print("scan error: \($0)") },
                onCompleted: { print("scan complete") },
                onDisposed: { print("scan disposed") }
            )
        do {
                   sleep(4)
        }
        cm.stopScan();
        disposable?.dispose()
        return pairedDevices;
    }
    
    public func centralManagerDidUpdateState(_ central: CBCentralManager) {
    }
    
    public func centralManager(_ central: CBCentralManager, didDiscover peripheral: CBPeripheral, advertisementData: [String : Any], rssi: NSNumber) {
    }
    
    public func peripheralManagerDidUpdateState(_ peripheral: CBPeripheralManager) {
    }
    
    
    func connect(address: String) -> Bool {
        var peripheralDevice: CBPeripheral? = nil;
        peripherals.forEach { peripheral in
            if((peripheral.identifier.uuidString) == address) {
                peripheralDevice = peripheral;
            }
        }
        var connected = false;
        cm.connect(peripheralDevice!).subscribe(
            onNext: {_ in 
                connected = true;
            },
            onError: { print("connection error: \($0)") },
            onDisposed: { print("dispose") }
        )
        do {
                   sleep(4)
        }
        return connected
     }
    
    public func centralManager(_ central: CBCentralManager, didConnect peripheral: CBPeripheral) {
        // Successfully connected. Store reference to peripheral if not already done.
//        self.connectedPeripheral = peripheral
//        peripheral.delegate = self
//        self.discoverServices(peripheral: peripheral);
    }
     
    public func centralManager(_ central: CBCentralManager, didFailToConnect peripheral: CBPeripheral, error: Error?) {
        print(error)
        // Handle error
    }
    
    func discoverServices(peripheral: CBPeripheral) {
//        peripheral.discoverServices(nil)
    }
    
    public func peripheral(_ peripheral: CBPeripheral, didDiscoverServices error: Error?) {
        guard let services = peripheral.services else {
            return
        }
//        discoverCharacteristics(peripheral: peripheral)
    }
     
    // Call after discovering services
    func discoverCharacteristics(peripheral: CBPeripheral) {
//        guard let services = peripheral.services else {
//            return
//        }
//        for service in services {
//            peripheral.discoverCharacteristics(nil, for: service)
//        }
    }
    
    public func peripheral(_ peripheral: CBPeripheral, didDiscoverCharacteristicsFor service: CBService, error: Error?) {
//        guard let characteristics = service.characteristics else {
//            return
//        }
//        for characteristic in characteristics {
//            discoverDescriptors(peripheral: peripheral, characteristic: characteristic)
//            do {
//                sleep(2)
//            }
//            subscribeToNotifications(peripheral: peripheral, characteristic: characteristic)
//            
//        }
        // Consider storing important characteristics internally for easy access and equivalency checks later.
        // From here, can read/write to characteristics or subscribe to notifications as desired.
    }
    
    
    
    func subscribeToNotifications(peripheral: CBPeripheral, characteristic: CBCharacteristic) {
//        peripheral.setNotifyValue(true, for: characteristic)
     }

    // In CBPeripheralDelegate class/extension
    public func peripheral(_ peripheral: CBPeripheral, didUpdateNotificationStateFor characteristic: CBCharacteristic, error: Error?) {
//        if let error = error {
//            // Handle error
//            NSLog("Error changing notification state: %@",
//                       [error]);
//            return
//        }
//        readValue(characteristic: characteristic)
        // Successfully subscribed to or unsubscribed from notifications/indications on a characteristic
    }
    
    func discoverDescriptors(peripheral: CBPeripheral, characteristic: CBCharacteristic) {
//        peripheral.discoverDescriptors(for: characteristic)
    }
    
    // In CBPeripheralDelegate class/extension
    public func peripheral(_ peripheral: CBPeripheral, didDiscoverDescriptorsFor characteristic: CBCharacteristic, error: Error?) {
//        guard let descriptors = characteristic.descriptors else { return }
//        let data = NSData(bytes: [0xbb, 0x02, 0 + 0x10, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0x00] as [UInt8], length: 20);
//        let charatceristicDefault = CBMutableCharacteristic.init(type: CBUUID(string: "FFE2"), properties: [.read, .notify, .write], value: data as Data, permissions: .writeable)
//        print(charatceristicDefault)
//        write(value: data as Data, characteristic: charatceristicDefault)
//        readValue(characteristic: charatceristicDefault)
//        peripheral.setNotifyValue(true, for: charatceristicDefault)
//        peripheral.setNotifyValue(true, for: characteristic)
//        peripheral.readValue(for: characteristic)
        
    }
     
    public func peripheral(_ peripheral: CBPeripheral, didUpdateValueFor descriptor: CBDescriptor, error: Error?) {
        // Get and print user description for a given characteristic
//        print("Characterstic \(descriptor.characteristic?.uuid.uuidString)")
    }

    func disconnect(address: String) {
        cm.disconnect();
//        var peripheralDevice: CBPeripheral? = nil;
//        peripherals.forEach { peripheral in
//            if((peripheral.identifier.uuidString) == address) {
//                peripheralDevice = peripheral;
//            }
//        }
//        centralManager.cancelPeripheralConnection(peripheralDevice!)
    }
    
    
    // In CBCentralManagerDelegate class/extension
    public func centralManager(_ central: CBCentralManager, didDisconnectPeripheral peripheral: CBPeripheral, error: Error?) {
//        if let error = error {
//            // Handle error
//            return
//        }
        // Successfully disconnected
    }
    
    func write(value: Data, characteristic: CBCharacteristic) {
//        self.connectedPeripheral?.writeValue(value, for: characteristic, type: .withResponse)
     }
    
    // In CBPeripheralDelegate class/extension
    // Only called if write type was .withResponse
    public func peripheral(_ peripheral: CBPeripheral, didWriteValueFor characteristic: CBCharacteristic, error: Error?) {
//        if let error = error {
//            // Handle error
//            print(error)
//            return
//        }
        // Successfully wrote value to characteristic
    }
    
    
    func readValue(characteristic: CBCharacteristic) {
//        self.connectedPeripheral?.readValue(for: characteristic)
    }

    // In CBPeripheralDelegate class/extension
    public func peripheral(_ peripheral: CBPeripheral, didUpdateValueFor characteristic: CBCharacteristic, error: Error?) {
        if let error = error {
            // Handle error
            return
        }
        guard let value = characteristic.value else {
            return
        }
        returnValue?(value)
        // Do something with data
    }
    
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
}
