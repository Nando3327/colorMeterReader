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
        CAPPluginMethod(name: "initBluetooth", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "listPairedDevices", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "connect", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "disconnect", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = ReaderInterface()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
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
