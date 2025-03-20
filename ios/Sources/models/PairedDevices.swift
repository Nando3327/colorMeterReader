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
