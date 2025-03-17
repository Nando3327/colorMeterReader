public class PairedDevices {
    var id = Int()
    var name: String
    var batteryLevel: Int
    var batteryLevelString: String
    var status: String
    var whiteCalibration: Bool
    var blackCalibration: Bool

    init (id: Int, name: String, batteryLevel: Int, batteryLevelString: String, status: String, whiteCalibration: Bool, blackCalibration: Bool) {
        self.id = id
        self.name = name
        self.batteryLevel = batteryLevel
        self.batteryLevelString = batteryLevelString
        self.status = status
        self.whiteCalibration = whiteCalibration
        self.blackCalibration = blackCalibration
    }
}
