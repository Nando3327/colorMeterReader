import Foundation
import CoreBluetooth
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

    init (
        macAddress: String,
        name: String,
        batteryLevel: Int,
        batteryLevelString: String,
        status: String,
        whiteCalibration: Bool,
        blackCalibration: Bool
    ) {
        self.macAddress = macAddress
        self.name = name
        self.batteryLevel = batteryLevel
        self.batteryLevelString = batteryLevelString
        self.status = status
        self.whiteCalibration = whiteCalibration
        self.blackCalibration = blackCalibration
    }
}

public class LAB {
    var l: String
    var a: String
    var b: String
    
    init(l: String, a: String, b: String) {
        self.l = l
        self.a = a
        self.b = b
    }
}

public class CalibrationStatus {
    var black: Bool
    var white: Bool
    
    init(black: Bool, white: Bool) {
        self.black = black
        self.white = white
    }
}


@objc public class ReaderInterface: NSObject {
    
    
    var pairedDevices: Array<PairedDevices> = []
    var peripherals: [CBPeripheral] = []
    var returnValue: ((_ value: LAB)->())?
    var calibrationStatus: ((_ value: CalibrationStatus)->())?
    var calibrateBlackCallback: ((_ value: Bool)->())?
    var calibrateWhiteCallback: ((_ value: Bool)->())?
    var connectedDevice: CBPeripheral? = nil;
    
    
    var cm = CMKit()
    var disposable: Disposable?
    var disposableLectures: Disposable?
    
    
    public func listPairedDevices() -> [PairedDevices] {
        pairedDevices = [];
        peripherals = [];
        var listedMacs: [String] = [];
        cm.startScan()
        disposable = cm.observeScanned()
            .subscribe(
                onNext: { [weak self] state in
                    if let peripheral = state.peripheral {
                        let filtered = listedMacs.filter {
                            $0.elementsEqual(peripheral.identifier.uuidString)
                        }
                        if(filtered.isEmpty && (peripheral.name) != nil) {
                            listedMacs.append(peripheral.identifier.uuidString);
                            self?.peripherals.append(peripheral)
                            var obj: PairedDevices;
                            obj = .init(
                                macAddress: peripheral.identifier.uuidString,
                                name: peripheral.name ?? peripheral.identifier.uuidString,
                                batteryLevel: 10,
                                batteryLevelString: "10%",
                                status: "asss",
                                whiteCalibration: true,
                                blackCalibration: false
                            );
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
    
    func connect(address: String) -> Bool {
        var peripheralDevice: CBPeripheral? = nil;
        peripherals.forEach { peripheral in
            if((peripheral.identifier.uuidString) == address) {
                peripheralDevice = peripheral;
            }
        }
        var connected = false;
        disposable = cm.connect(peripheralDevice!).subscribe(
            onNext: {_ in
                connected = true;
                self.connectedDevice = peripheralDevice;
                self.disposable?.dispose()
            },
            onError: {
                print("connection error: \($0)")
            },
            onDisposed: { print("dispose") }
        )
        do {
            sleep(4)
        }
        disposable?.dispose()
        return connected
    }
    
    func disconnect(address: String) {
        disposable = cm.disconnect().subscribe(
            onNext: {_ in
                self.connectedDevice = nil;
                self.disposable?.dispose()
            },
            onError: {
                print("disconnection error: \($0)")
            },
            onDisposed: {
                print("dispose")
            });
    }
    
    
    @objc public func valueDetected() {
        disposableLectures = cm.observeMeasure()
            .concatMap { _ in
                return self.cm.getMeasureData()
            }
            .subscribe { data in
                if let data = data {
                    let color = CMColor(
                        spectral: data.refs.map({ Double($0) }),
                        waveStart: Int(data.waveStart),
                        lightSource: .init(angle: .deg10, category: .D65)
                    )
                    let (red, green, blue) = color.rgb
                    let (l, a, b) = color.lab
                    
                    var obj: LAB;
                    obj = .init(l: String(l), a: String(a), b: String(b))
                    self.disposableLectures?.dispose()
                    self.returnValue?(obj)
                }
            } onError: { error in
                print("measure error: \(error)")
            }
    }
    
    @objc public func getReaderCalibrationStatus() {
        disposable = cm.getCalibrationState().subscribe(
            onNext: {status in
                var obj: CalibrationStatus;
                obj = .init(black: status?.blackCalibrateTimestamp != nil, white: status?.whiteCalibrateTimestamp != nil)
                self.calibrationStatus?(obj);
                self.disposable?.dispose()
            },
            onError: {
                print("disconnection error: \($0)")
            },
            onDisposed: {
                print("dispose")
            });
    }
    
    @objc public func isReaderConnected() -> Bool {
        return (connectedDevice != nil)
    }
    
    @objc public func calibrateBlack() -> Bool {
        disposable = cm.blackCalibrate().subscribe(
            onNext: {status in
                self.calibrateBlackCallback?(true);
                self.disposable?.dispose()
            },
            onError: {
                print("disconnection error: \($0)")
                self.calibrateBlackCallback?(false);
            },
            onDisposed: {
                print("dispose")
            });
        return true
    }
    
    @objc public func calibrateWhite() -> Bool {
        disposable = cm.blackCalibrate().subscribe(
            onNext: {status in
                self.calibrateWhiteCallback?(true);
                self.disposable?.dispose()
            },
            onError: {
                print("disconnection error: \($0)")
                self.calibrateWhiteCallback?(false);
            },
            onDisposed: {
                print("dispose")
            });
        return true
    }
    
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
}
