import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(ReaderInterfacePlugin)
public class ReaderInterfacePlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "ReaderInterfacePlugin"
    public let jsName = "ReaderInterface"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "reviewPermissions", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "initNueServiceBle", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "listPairedDevices", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "connect", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "disconnect", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "valueDetected", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "valueDetected", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "getReaderCalibrationStatus", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "calibrateWhite", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "calibrateBlack", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "isReaderConnected", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = ReaderInterface()

    @objc func echo(_ call: CAPPluginCall) {
        implementation.initNueServiceBle()
        call.resolve()
    }
    
    @objc func reviewPermissions(_ call: CAPPluginCall) {
        call.resolve(["value": implementation.reviewPermissions()])
    }
    
    @objc func initNueServiceBle(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
    
    @objc func calibrateWhite(_ call: CAPPluginCall) {
        implementation.calibrateWhite();
        implementation.calibrateWhiteCallback = { (value) -> Void in
            print(value);
            call.resolve([
                "calibrated": value
            ])
        }
    }
    
    @objc func calibrateBlack(_ call: CAPPluginCall) {
        implementation.calibrateBlack();
        implementation.calibrateBlackCallback = { (value) -> Void in
            print(value);
            call.resolve([
                "calibrated": value
            ])
        }
    }
    
    @objc func isReaderConnected(_ call: CAPPluginCall) {
        call.resolve([
            "value": implementation.isReaderConnected()
        ])
    }
    
    @objc func getReaderCalibrationStatus(_ call: CAPPluginCall) {
        implementation.getReaderCalibrationStatus();
        implementation.calibrationStatus = { (value) -> Void in
            print(value);
            call.resolve([
                "black": value.black,
                "white": value.white
            ])
        }
    }
    
    @objc func valueDetected(_ call: CAPPluginCall) {
        implementation.valueDetected();
        implementation.returnValue = { (value) -> Void in
            call.resolve([
                "l": value.l,
                "a": value.a,
                "b": value.b
            ])
        }
    }

    
    @objc func connect(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.connect(address: value)
        ])
    }
    
    @objc func disconnect(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.disconnect(address: value)
        ])
    }
    
    @objc func listPairedDevices(_ call: CAPPluginCall) {
        let readers = implementation.listPairedDevices();
        call.resolve([
            "devices": readers.map(){reader in
             return ["macAddress": reader.macAddress,
                     "name": reader.name,
                     "batteryLevel": reader.batteryLevel,
                     "batteryLevelString": reader.batteryLevelString,
                     "status": reader.status,
                     "whiteCalibration": reader.whiteCalibration,
                     "blackCalibration": reader.blackCalibration]
            }
        ])
    }
}
