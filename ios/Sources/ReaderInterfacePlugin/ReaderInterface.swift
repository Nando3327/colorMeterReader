import Foundation
import CoreBluetooth

@objc public class ReaderInterface: NSObject, CBCentralManagerDelegate, CBPeripheralManagerDelegate, CBPeripheralDelegate {
    
    
    var centralManager: CBCentralManager!
    var peripheralManager: CBPeripheralManager!
    var discoveredPeripheral: CBPeripheral?
    var pairedDevices: Array<PairedDevices> = []
    var peripherals: [CBPeripheral] = []
    var connectedPeripheral: CBPeripheral?
    var returnValue: ((_ value: Data)->())?
    
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
    
    
    func connect(address: String) {
        var peripheralDevice: CBPeripheral? = nil;
        peripherals.forEach { peripheral in
            if((peripheral.identifier.uuidString) == address) {
                peripheralDevice = peripheral;
            }
        }
        return centralManager.connect(peripheralDevice!)
     }
    
    public func centralManager(_ central: CBCentralManager, didConnect peripheral: CBPeripheral) {
        // Successfully connected. Store reference to peripheral if not already done.
        self.connectedPeripheral = peripheral
        peripheral.delegate = self
        self.discoverServices(peripheral: peripheral);
    }
     
    public func centralManager(_ central: CBCentralManager, didFailToConnect peripheral: CBPeripheral, error: Error?) {
        print(error)
        // Handle error
    }
    
    func discoverServices(peripheral: CBPeripheral) {
        peripheral.discoverServices(nil)
    }
    
    public func peripheral(_ peripheral: CBPeripheral, didDiscoverServices error: Error?) {
        guard let services = peripheral.services else {
            return
        }
        discoverCharacteristics(peripheral: peripheral)
    }
     
    // Call after discovering services
    func discoverCharacteristics(peripheral: CBPeripheral) {
        guard let services = peripheral.services else {
            return
        }
        for service in services {
            print(service)
            peripheral.discoverCharacteristics(nil, for: service)
        }
    }
    
    public func peripheral(_ peripheral: CBPeripheral, didDiscoverCharacteristicsFor service: CBService, error: Error?) {
        guard let characteristics = service.characteristics else {
            return
        }
        for characteristic in characteristics {
            print(characteristic)
            discoverDescriptors(peripheral: peripheral, characteristic: characteristic)
            subscribeToNotifications(peripheral: peripheral, characteristic: characteristic)
        }
        // Consider storing important characteristics internally for easy access and equivalency checks later.
        // From here, can read/write to characteristics or subscribe to notifications as desired.
    }
    
    
    
    func subscribeToNotifications(peripheral: CBPeripheral, characteristic: CBCharacteristic) {
        peripheral.setNotifyValue(true, for: characteristic)
     }

    // In CBPeripheralDelegate class/extension
    public func peripheral(_ peripheral: CBPeripheral, didUpdateNotificationStateFor characteristic: CBCharacteristic, error: Error?) {
        if let error = error {
            // Handle error
            return
        }
        readValue(characteristic: characteristic)
        // Successfully subscribed to or unsubscribed from notifications/indications on a characteristic
    }
    
    func discoverDescriptors(peripheral: CBPeripheral, characteristic: CBCharacteristic) {
        peripheral.discoverDescriptors(for: characteristic)
    }
    
    // In CBPeripheralDelegate class/extension
    public func peripheral(_ peripheral: CBPeripheral, didDiscoverDescriptorsFor characteristic: CBCharacteristic, error: Error?) {
        guard let descriptors = characteristic.descriptors else { return }
     
        // Get user description descriptor
        if let userDescriptionDescriptor = descriptors.first(where: {
            return $0.uuid.uuidString == CBUUIDCharacteristicUserDescriptionString
        }) {
            // Read user description for characteristic
            peripheral.readValue(for: userDescriptionDescriptor)
        }
    }
     
    public func peripheral(_ peripheral: CBPeripheral, didUpdateValueFor descriptor: CBDescriptor, error: Error?) {
        // Get and print user description for a given characteristic
        if descriptor.uuid.uuidString == CBUUIDCharacteristicUserDescriptionString,
            let userDescription = descriptor.value as? String {
            print("Characterstic \(descriptor.characteristic?.uuid.uuidString) is also known as \(userDescription)")
        }
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
    
    func readValue(characteristic: CBCharacteristic) {
        self.connectedPeripheral?.readValue(for: characteristic)
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
        print(value)
        returnValue?(value)
        // Do something with data
    }
    
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
}
