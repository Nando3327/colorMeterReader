import Foundation

@objc public class ReaderInterface: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
    @objc public func listPairedDevices() -> [Any] {
        return [{
            id: 0,
            name: 'IOS CM2018920',
            batteryLevel: 85,
            batteryLevelString: '85%',
            status: 'disconnected',
            whiteCalibration: false,
            blackCalibration: true
          }]
    }
}
