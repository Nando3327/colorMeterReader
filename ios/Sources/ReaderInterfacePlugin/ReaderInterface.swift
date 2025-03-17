import Foundation

@objc public class ReaderInterface: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
    
    public func listPairedDevices() -> [PairedDevices] {
        var obj: PairedDevices;
        var obj1: PairedDevices;
        obj = .init(id: 0, name: "asd", batteryLevel: 10, batteryLevelString: "10%", status: "asss", whiteCalibration: true, blackCalibration: false)
        obj1 = .init(id: 0, name: "asdddd", batteryLevel: 80, batteryLevelString: "80%", status: "asss", whiteCalibration: true, blackCalibration: false)
        return [obj, obj1];
    }
}
